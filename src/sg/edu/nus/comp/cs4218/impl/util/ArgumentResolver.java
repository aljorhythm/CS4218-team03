package sg.edu.nus.comp.cs4218.impl.util;

import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;

@SuppressWarnings("PMD.ExcessiveMethodLength")
public final class ArgumentResolver {
    private ArgumentResolver() {
    }

    /**
     * Handle quoting + globing + command substitution for a list of arguments.
     *
     * @param argsList The original list of arguments.
     * @return The list of parsed arguments.
     * @throws ShellException If any of the arguments have an invalid syntax.
     */
    public static List<String> parseArguments(List<String> argsList) throws ShellException {
        List<String> parsedArgsList = new LinkedList<>();

        List<String> parsedArgsSegment = new LinkedList<>();
        for (String arg : argsList) {
            parsedArgsSegment.addAll(resolveOneArgument(arg));
        }
        parsedArgsList.addAll(parsedArgsSegment);
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
    public static List<String> resolveOneArgument(String arg) throws ShellException {
        Queue<Character> unmatchedQuotes = new LinkedList<>();
        LinkedList<RegexArgument> parsedArgsSegment = new LinkedList<>();
        RegexArgument parsedArg = new RegexArgument();
        StringBuilder subCommand = new StringBuilder();

        for (int i = 0; i < arg.length(); i++) {
            char chr = arg.charAt(i);

            if (chr == CHAR_BACK_QUOTE) {
                if (unmatchedQuotes.isEmpty() || unmatchedQuotes.peek() == CHAR_DOUBLE_QUOTE) {
                    // start of command substitution
                    if (unmatchedQuotes.isEmpty()){
                        unmatchedQuotes.add(chr);
                    }else {
                        unmatchedQuotes.remove();
                        unmatchedQuotes.add(chr);
                        unmatchedQuotes.add(CHAR_DOUBLE_QUOTE);
                    }
                    if (!parsedArg.isEmpty()) {
                        appendParsedArgIntoSegment(parsedArgsSegment, parsedArg);
                        parsedArg = new RegexArgument();
                    }
                } else if (unmatchedQuotes.peek() == chr) {
                    // end of command substitution
                    unmatchedQuotes.remove();
//                    while (unmatchedQuotes.size() > 0){
//                        subCommand.append(unmatchedQuotes.remove());
//                    }
//                    if (!unmatchedQuotes.isEmpty()){
//                        unmatchedQuotes.remove();
//                    }
                    // evaluate subCommand and get the output
                    String subCommandOutput = evaluateSubCommand(subCommand.toString());
                    subCommand = new StringBuilder();

                    // check if back quotes are nested
                    if (unmatchedQuotes.isEmpty()) {
                        List<RegexArgument> subOutputSegment = Stream
                                .of(StringUtils.tokenize(subCommandOutput))
                                .map(str -> new RegexArgument(str))
                                .collect(Collectors.toList());

                        // append the first token to the previous parsedArg
                        // e.g. arg: abc`1 2 3`xyz`4 5 6` (contents in `` is after command sub)
                        // expected: [abc1, 2, 3xyz4, 5, 6]
                        if (subOutputSegment.isEmpty()) {
                            RegexArgument firstOutputArg = subOutputSegment.remove(0);
                            appendParsedArgIntoSegment(parsedArgsSegment, firstOutputArg);
                        }

                        // add remaining tokens to parsedArgsSegment
                        parsedArgsSegment.addAll(subOutputSegment);
                    } else {
                        // don't tokenize subCommand output
                        appendParsedArgIntoSegment(parsedArgsSegment,
                                new RegexArgument(subCommandOutput));
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
                    while (!unmatchedQuotes.isEmpty()){
                        parsedArg.append(unmatchedQuotes.remove());
                    }
                    appendParsedArgIntoSegment(parsedArgsSegment, parsedArg);
                    parsedArg = new RegexArgument();
                } else if (unmatchedQuotes.peek() == CHAR_BACK_QUOTE) {
                    // ongoing back quote: add chr to subCommand
                    subCommand.append(chr);
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
                    subCommand.append(chr);
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
                    subCommand.append(chr);
                } else {
                    // ongoing single/double quote
                    parsedArg.append(chr);
                }
            }
        }

        if (!parsedArg.isEmpty()) {
            appendParsedArgIntoSegment(parsedArgsSegment, parsedArg);
        }
        if (subCommand.length() != 0){
            appendParsedArgIntoSegment(parsedArgsSegment,new RegexArgument(subCommand.toString()));
        }

        // perform globing

        return parsedArgsSegment.stream()
                .flatMap(regexArgument -> regexArgument.globFiles().stream())
                .collect(Collectors.toList());
    }

    private static String evaluateSubCommand(String commandString) {
        if (StringUtils.isBlank(commandString)) {
            return "";
        }

        OutputStream outputStream = new ByteArrayOutputStream();
        String output = null;

        try {
            Command command = CommandBuilder.parseCommand(commandString, new ApplicationRunner());
            command.evaluate(System.in, outputStream);
            output = outputStream.toString();
        } catch (AbstractApplicationException | ShellException e) {
            output = e.getMessage();
        }

        // replace newlines with spaces
        return output.replace(STRING_NEWLINE, String.valueOf(CHAR_SPACE));
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
            lastParsedArg.merge(parsedArg);
            parsedArgsSegment.add(lastParsedArg);
        }
    }
}
