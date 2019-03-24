package sg.edu.nus.comp.cs4218m1.impl.util;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.CommandBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.mock;

class CommandBuilderTest {
    ApplicationRunner mockAppRunner = mock(ApplicationRunner.class);
    private static final String STR_ECHO = "echo";

    /**
     * Parse command string without sequence or pipe operators
     */
    @Test
    void parseCommandSimpleTokens() {
        ApplicationRunner runner = mock(ApplicationRunner.class);
        String stringCommand = "echo abc";
        Command command = null;
        try {
            command = CommandBuilder.parseCommand(stringCommand, runner);
        } catch (ShellException e) {
            e.printStackTrace();
        }
        CallCommand callCommand = (CallCommand) command;
        String[] expected = {STR_ECHO, "abc"};
        String[] actual = callCommand
                .getArgsList()
                .toArray(new String[]{});
        assertArrayEquals(expected, actual);
    }

    /**
     * Pipe sub commands have arguments
     */
    @Test
    void parseCommandSeqMultipleArgs() {
        String stringCommand = "echo abc;echo abcde 1234;ls";
        Command command = null;
        try {
            command = CommandBuilder.parseCommand(stringCommand, mockAppRunner);
        } catch (ShellException e) {
            e.printStackTrace();
        }
        SequenceCommand seqCommand = (SequenceCommand) command;
        String[][] expected = {{STR_ECHO, "abc"}, {STR_ECHO, "abcde", "1234"}, {"ls"}};
        assertSequenceHasCalls(seqCommand, expected);
    }

    /**
     * Pipe sub commands have no arguments
     */
    @Test
    void parseCommandSeqNoArgs() {
        String stringCommand = "echo;ls";
        Command command = null;
        try {
            command = CommandBuilder.parseCommand(stringCommand, mockAppRunner);
        } catch (ShellException e) {
            e.printStackTrace();
        }
        SequenceCommand seqCommand = (SequenceCommand) command;
        String[][] expected = {{STR_ECHO}, {"ls"}};
        assertSequenceHasCalls(seqCommand, expected);
    }

    /**
     * Asserts pipe command has call commands with args
     */
    static void assertPipe(PipeCommand pipeCommand, String[]... expected) {
        String[][] actual = new String[pipeCommand.getCallCommands().size()][];
        List<CallCommand> callCommands = pipeCommand.getCallCommands();
        for(int i = 0; i < actual.length; i++) {
            CallCommand cmd = callCommands.get(i);
            List<String> argsList = cmd.getArgsList();
            actual[i] = argsList.toArray(new String[]{});
        }
        assertArrayEquals(expected, actual);
    }

    /**
     * Asserts sequence command has call commands with args
     */
    static void assertSequenceHasCalls(SequenceCommand seqCommand, String[]... expected) {
        List<Command> callCommands = seqCommand.getCommands();
        String[][] actual = new String[seqCommand.getCommands().size()][];
        for(int i = 0; i < actual.length; i++) {
            CallCommand cmd = (CallCommand) callCommands.get(i);
            List<String> argsList = cmd.getArgsList();
            actual[i] = argsList.toArray(new String[]{});
        }
        assertArrayEquals(expected, actual);
    }
}