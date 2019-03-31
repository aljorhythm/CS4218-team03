package sg.edu.nus.comp.cs4218.impl.util;

import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static sg.edu.nus.comp.cs4218.impl.ShellImpl.ERR_SYNTAX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;

@SuppressWarnings("PMD.ExcessiveMethodLength")
public final class CommandBuilder {//NOPMD
    /**
     * Regular expression for extracting valid arguments from the command string:
     * (NO_QUOTE | SINGLE_QUOTE | NESTED_BACK_QUOTE | DOUBLE_QUOTE | BACK_QUOTE)+
     * <p>
     * The order matters because it affects the matching priority.
     * <p>
     * NO_QUOTE: [^'\"`|<>;\\s]+
     * SINGLE_QUOTE: '[^']*'
     * NESTED_BACK_QUOTE: \"([^\"`]*`.*?`[^\"`]*)+\"
     * DOUBLE_QUOTE: \"[^\"]*\"
     * BACK_QUOTE: `[^`]*`
     */
    private static final Pattern ARGUMENT_REGEX = Pattern
            .compile("([^'\"`|<>;\\s]+|'[^']*'|\"([^\"`]*`.*?`[^\"`]*)+\"|\"[^\"]*\"|`[^`]*`)+");
    private static ArgumentResolver argumentResolver = new ArgumentResolver();

    private CommandBuilder() {
    }

    /**
     * Parses and tokenizes the provided command string into command(s) and arguments.
     * <p>
     * CallCommand takes in a list of tokens, PipeCommand takes in a list of CallCommands,
     * and SequenceCommand takes in a list of CallCommands / PipeCommands.
     *
     * @return Final command to be evaluated.
     * @throws ShellException If the provided command string has an invalid syntax.
     */
    public static Command parseCommand(String commandString, ApplicationRunner appRunner)
            throws ShellException {
        if (StringUtils.isBlank(commandString)) {
            throw new ShellException(ERR_SYNTAX);
        }
        if (commandString.contains(STRING_NEWLINE)){
            throw new ShellException(ERR_SYNTAX);
        }
        List<Command> cmdsForSequence = new LinkedList<>();
        List<CallCommand> callCmdsForPipe = new LinkedList<>();
        List<Command> cmdsForIORedirection = new LinkedList<>();//NOPMD
        List<String> tokens = new LinkedList<>();

        String commandSubstring = commandString;
        while (!commandSubstring.isEmpty()) {
            Matcher matcher = ARGUMENT_REGEX.matcher(commandSubstring);

            // no valid arguments found
            if (!matcher.find()) {
                throw new ShellException(ERR_SYNTAX);
            }

            // found a valid argument at the start of the command substring
            if (matcher.start() == 0) {
                tokens.add(matcher.group());
//                System.out.println("matcher.group(): "+matcher.group());
                commandSubstring = commandSubstring.substring(matcher.end());
//                System.out.println("commandSubstring: "+commandSubstring);
                continue;
            }

            // found a valid argument but not at the start of the command substring
            char firstChar = commandSubstring.charAt(0);
            commandSubstring = commandSubstring.substring(1);
//            System.out.println("commandSubstring: "+commandSubstring);
//            System.out.println("firstChar: "+firstChar);

            switch (firstChar) {

                case CHAR_REDIR_INPUT:
//                    tokens.add(String.valueOf(firstChar));
//                    if (cmdsForIORedirection.isEmpty()){
//                        int index = commandSubstring.indexOf(CHAR_SPACE,1);
//                        if (index < 1) {
//                            tokens.add(commandSubstring);
//                            commandSubstring = "";
//                        }
//                        else {
//                            tokens.add(commandSubstring.substring(1,index+1));
//                            commandSubstring = commandSubstring.substring(index+1);
//                        }
//                        cmdsForIORedirection.add(new CallCommand(tokens,appRunner));
//                    }
//                    commandSubstring = commandSubstring.substring(1);
//                    break;
                case CHAR_REDIR_OUTPUT:
                    // add as a separate token on its own
                    tokens.add(String.valueOf(firstChar));
                    if (cmdsForIORedirection.isEmpty()) {
                        int index = commandSubstring.indexOf(CHAR_SPACE, 2);
                        if (index < 2) {
                            tokens.add(commandSubstring.substring(2));
                            commandSubstring = "";
                        } else {
                            tokens.add(commandSubstring.substring(2, index + 1));
                            commandSubstring = commandSubstring.substring(index + 1);
                        }
                        cmdsForIORedirection.add(new CallCommand(tokens, argumentResolver, appRunner));
                    }
                    break;
                case CHAR_SPACE:
                    if (tokens.isEmpty()) {
                        throw new ShellException(ERR_SYNTAX);
                    }
//                    if (commandSubstring.length() > 1) {
//                        commandSubstring = commandSubstring.substring(0);
//                    } else {
//                        commandSubstring = "";
//                    }
                    break;
                case CHAR_PIPE:
                    if (tokens.isEmpty()) {
                        // cannot start a new command with pipe
                        throw new ShellException(ERR_SYNTAX);
                    } else {
                        // add CallCommand as part of a PipeCommand
                        callCmdsForPipe.add(new CallCommand(tokens, argumentResolver, appRunner));
                    }
                    break;

                case CHAR_SEMICOLON:
                    // CHAR_SEMICOLON = ';';
                    if (tokens.isEmpty()) {
                        // cannot start a new command with semicolon
                        throw new ShellException(ERR_SYNTAX);
                    } else if (callCmdsForPipe.isEmpty()) {
                        // add CallCommand as part of a SequenceCommand
                        cmdsForSequence.add(new CallCommand(tokens, argumentResolver, appRunner));
                        tokens = new LinkedList<>();
                    } else {
                        // add CallCommand as part of ongoing PipeCommand
                        callCmdsForPipe.add(new CallCommand(tokens, argumentResolver, appRunner));
                        // add PipeCommand as part of a SequenceCommand
                        cmdsForSequence.add(new PipeCommand(callCmdsForPipe));
                        callCmdsForPipe = new LinkedList<>();
                    }
                    break;

                default:
                    // encountered a mismatched quote
                    throw new ShellException(ERR_SYNTAX);
            }
        }

        Command finalCommand = new CallCommand(tokens, argumentResolver, appRunner);
        if (!callCmdsForPipe.isEmpty()) {
            // add CallCommand as part of ongoing PipeCommand
            callCmdsForPipe.add((CallCommand) finalCommand);
            finalCommand = new PipeCommand(callCmdsForPipe);
        }
        if (!cmdsForSequence.isEmpty()) {
            // add CallCommand / PipeCommand as part of ongoing SequenceCommand
            cmdsForSequence.add(finalCommand);
            finalCommand = new SequenceCommand(cmdsForSequence);
        }
        return finalCommand;
    }
}
