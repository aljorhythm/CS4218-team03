package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.mock;

class CommandBuilderTest {

    private static String[] apply(CallCommand call) {
        return call
                .getArgsList()
                .toArray(new String[]{});
    }

    @Test
    void parseCommand_simpleTokens() {
        ApplicationRunner runner = mock(ApplicationRunner.class);
        String stringCommand = "echo abc";
        Command command = null;
        try {
            command = CommandBuilder.parseCommand(stringCommand, runner);
        } catch (ShellException e) {
            e.printStackTrace();
        }
        CallCommand callCommand = (CallCommand) command;
        String[] expected = {"echo", "abc"};
        String[] actual = callCommand
                .getArgsList()
                .toArray(new String[]{});
        assertArrayEquals(expected, actual);
    }

    @Test
    void parseCommand_pipe() {
        ApplicationRunner runner = mock(ApplicationRunner.class);
        String stringCommand = "echo abc; echo abcde";
        Command command = null;
        try {
            command = CommandBuilder.parseCommand(stringCommand, runner);
        } catch (ShellException e) {
            e.printStackTrace();
        }
        PipeCommand callCommand = (PipeCommand) command;
        String[][] expected = {{"echo", "abc"}, {"echo", "abcde"}};
        List<String[]> actual = (List<String[]>) callCommand
                .getCallCommands().stream().map(CommandBuilderTest::apply);
        assertArrayEquals(expected, actual.toArray(new String[][]{}));
    }
}