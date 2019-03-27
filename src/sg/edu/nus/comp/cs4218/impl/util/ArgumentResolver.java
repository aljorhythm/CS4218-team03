package sg.edu.nus.comp.cs4218.impl.util;

import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static sg.edu.nus.comp.cs4218.impl.ShellImpl.ERR_SYNTAX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;

@SuppressWarnings("PMD.ExcessiveMethodLength")
public class ArgumentResolver {
    public ArgumentResolver() {
    }


    public List<String> parseArguments(List<String> argsList, ApplicationRunner appRunner) throws ShellException {
        return parseArg(argsList);
    }

    public List<String> resolveOneArgument(String args, ApplicationRunner appRunner) throws ShellException {
        return resolveOneArg(args);
    }

    /**
     * Handle quoting + globing + command substitution for a list of arguments.
     *
     * @param argsList The original list of arguments.
     * @return The list of parsed arguments.
     * @throws ShellException If any of the arguments have an invalid syntax.
     */
    public static List<String> parseArg(List<String> argsList) throws ShellException {
        List<String> parsedArgsList = new LinkedList<>();

        List<String> parsedArgsSegment;
        for (String arg : argsList) {
            parsedArgsSegment = resolveOneArg(arg);
            parsedArgsList.addAll(parsedArgsSegment);
        }

        return parsedArgsList;
    }

    /**
     * Unwraps single and double quotes from one argument.
     * Performs globing when there are unquoted asterisks.
     * Performs command substitution.
     * <p>
     * Single quotes disable the interpretation of all special characters.
     * Double quotes disable the interpretation of all special characters, except for back quotes.
     *
     * @param arg String containing one argument.
     * @return A list containing one or more parsed args, depending on the outcome of the parsing.
     * @throws ShellException If there are any mismatched quotes.
     */
    public static List<String> resolveOneArg(String arg) throws ShellException {
        LinkedList<Character> unmatchedQuotes = new LinkedList<>();
        LinkedList<RegexArgument> parsedArgsSegment = new LinkedList<>();
        RegexArgument parsedArg = new RegexArgument();
        StringBuilder subCommand = new StringBuilder();

        for (int i = 0; i < arg.length(); i++) {
            char chr = arg.charAt(i);

            if (chr == CHAR_BACK_QUOTE) {
                if (unmatchedQuotes.isEmpty() || unmatchedQuotes.peekLast() == CHAR_DOUBLE_QUOTE) {
                    // start of command substitution
                    unmatchedQuotes.add(chr);

                    if (!parsedArg.isEmpty()) {
                        appendParsedArgIntoSegment(parsedArgsSegment, parsedArg);
                        parsedArg = new RegexArgument();
                    }
                } else if (unmatchedQuotes.peekLast() == chr) {
                    // end of command substitution
                    unmatchedQuotes.removeLast();
                    subCommand.append(parsedArg.toString());
                    parsedArg = new RegexArgument();

                    // evaluate subCommand and get the output
                    String subCommandOutput = evaluateSubCommand(subCommand.toString());

                    // check if back quotes are nested
                    if (unmatchedQuotes.isEmpty()) {
                        // don't tokenize subCommand output
                        appendParsedArgIntoSegment(parsedArgsSegment,
                                new RegexArgument(subCommandOutput));
                    } else {
                        List<RegexArgument> subOutputSegment = Stream
                                .of(StringUtils.tokenize(subCommandOutput))
                                .map(str -> new RegexArgument(str))
                                .collect(Collectors.toList());

                        // append the first token to the previous parsedArg
                        // e.g. arg: abc`1 2 3`xyz`4 5 6` (contents in `` is after command sub)
                        // expected: [abc1, 2, 3xyz4, 5, 6]
                        if (!subOutputSegment.isEmpty()) {
                            RegexArgument firstOutputArg = subOutputSegment.remove(0);
                            appendParsedArgIntoSegment(parsedArgsSegment, firstOutputArg);
                        }

                        // add remaining tokens to parsedArgsSegment
                        parsedArgsSegment.addAll(subOutputSegment);
                    }
                } else {
                    // ongoing single quote
                    parsedArg.append(chr);
                }
            } else if (chr == CHAR_SINGLE_QUOTE || chr == CHAR_DOUBLE_QUOTE) {
                if (unmatchedQuotes.isEmpty()) {
                    // start of quote
                    unmatchedQuotes.add(chr);
                } else if (unmatchedQuotes.peek() == chr) {
                    // end of quote
                    unmatchedQuotes.remove();

                    // make sure parsedArgsSegment is not empty
                    appendParsedArgIntoSegment(parsedArgsSegment, new RegexArgument());
                } else if (unmatchedQuotes.peek() == CHAR_BACK_QUOTE) {
                    // ongoing back quote: add chr to subCommand
                    parsedArg.append(chr);
                } else {
                    // ongoing single/double quote
                    parsedArg.append(chr);
                }
            } else if (chr == CHAR_ASTERISK) {
                if (unmatchedQuotes.isEmpty()) {
                    // each unquoted * matches a (possibly empty) sequence of non-slash chars
                    parsedArg.appendAsterisk();
                } else if (unmatchedQuotes.peek() == CHAR_BACK_QUOTE) {
                    // ongoing back quote: add chr to subCommand
                    parsedArg.append(chr);
                } else {
                    // ongoing single/double quote
                    parsedArg.append(chr);
                }
            } else {
                if (unmatchedQuotes.isEmpty()) {
                    // not a special character
                    parsedArg.append(chr);
                } else if (unmatchedQuotes.peek() == CHAR_BACK_QUOTE) {
                    // ongoing back quote: add chr to subCommand
                    parsedArg.append(chr);
                } else {
                    // ongoing single/double quote
                    parsedArg.append(chr);
                }
            }
        }

        // check for unmatched quotes
        if (!unmatchedQuotes.isEmpty()) {
            throw new ShellException(ERR_SYNTAX);
        }

        appendParsedArgIntoSegment(parsedArgsSegment, parsedArg);

        // perform globing
        return parsedArgsSegment.stream()
                .flatMap(regexArgument -> regexArgument.globFiles().stream())
                .collect(Collectors.toList());
    }

    private static String evaluateSubCommand(String commandString) throws ShellException {
        if (StringUtils.isBlank(commandString)) {
            return "";
        }

        OutputStream outputStream = new ByteArrayOutputStream();
        String output;

        try {
            Command command = CommandBuilder.parseCommand(commandString, new ApplicationRunner());
            command.evaluate(System.in, outputStream);
            output = outputStream.toString();
        } catch (AbstractApplicationException | ShellException e) {
            throw new ShellException(e.getMessage(), e);
        }

        // remove newlines
        return output.replace(STRING_NEWLINE, "");
    }

    /**
     * Append current parsedArg to the last parsedArg in parsedArgsSegment.
     * If parsedArgsSegment is empty, then just add current parsedArg.
     */
    private static void appendParsedArgIntoSegment(LinkedList<RegexArgument> parsedArgsSegment,
                                                   RegexArgument parsedArg) {
        if (parsedArgsSegment.isEmpty()) {
            parsedArgsSegment.add(parsedArg);
        } else {
            RegexArgument lastParsedArg = parsedArgsSegment.removeLast();
            parsedArgsSegment.add(lastParsedArg);
            lastParsedArg.merge(parsedArg);
        }
    }

    public static void testEvaluationSubCommand(ArgumentResolverObserver argObserver) throws ShellException {
        argObserver.setCommandResult(evaluateSubCommand(argObserver.getCommandString()));
    }

    public static void testAppendParsedArgIntoSegment(ArgumentResolverObserver argsObserver) {
        appendParsedArgIntoSegment(argsObserver.getRegaxList(),argsObserver.getRegax());
    }
}
