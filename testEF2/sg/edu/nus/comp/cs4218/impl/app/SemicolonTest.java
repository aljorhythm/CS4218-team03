package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.CommandBuilder;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static sg.edu.nus.comp.cs4218.exception.ShellException.INVALID_INPUT_STREAM;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHARSET_UTF8;

class SemicolonTest {
    Command command;
    ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
    String echoSemicolonStringWithSpace = "echo a ; echo b";
    String echoSemicolonStringWithoutSpace = "echo a;echo b";

    /**
     * Mocks a call command
     * Simply writes input from stdin and "ABC" or "DEF" to output
     */
    static class MockCommand extends CallCommand{

        private static String input;

        public MockCommand(String word) {
            super(null, null);
            String input = word;
        }

        @Override
        public void evaluate(InputStream stdin, OutputStream stdout) throws ShellException {
            byte[] buffer = new byte[1024];
            int length;

            try {

                while ((length = stdin.read(buffer)) != -1) {
                    stdout.write(buffer, 0, length);
                }
                buffer = MockCommand.input.getBytes(CHARSET_UTF8);
                stdout.write(buffer);
            } catch (IOException e) {
                throw new ShellException(INVALID_INPUT_STREAM);
            }
        }

        @Override
        public void terminate() {

        }
    }

    /**
     * Tests sequence of two commands ABC first.
     */
    @Test
    void evaluate_twoCommandsAFDL() throws Exception {
        mock(CallCommand.class);
        InputStream inputStream = new ByteArrayInputStream("test data ".getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();

        CallCommand[] commands = {
                new MockCommand("ABC"), new MockCommand("DEF")
        };

        SequenceCommand sequenceCommand = new SequenceCommand(Arrays.asList(commands));
        sequenceCommand.evaluate(inputStream, outputStream);
        String expected = "test data ABC\nDEF\n";
        String actual = ((ByteArrayOutputStream) outputStream).toString("UTF-8");
        assertEquals(actual, expected);
    }

    /**
     * Tests sequence of two commands DEF first.
     */
    @Test
    void evaluate_twoCommandsDFAL() throws Exception {
        mock(CallCommand.class);
        InputStream inputStream = new ByteArrayInputStream("test data ".getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();

        CallCommand[] commands = {
                new MockCommand("DEF"), new MockCommand("ABC")
        };

        SequenceCommand sequenceCommand = new SequenceCommand(Arrays.asList(commands));
        sequenceCommand.evaluate(inputStream, outputStream);
        String expected = "test data DEF\nABC\n";
        String actual = ((ByteArrayOutputStream) outputStream).toString("UTF-8");
        assertEquals(actual, expected);
    }

    /**
     * Test if the shell can get the semicolon
     */
    @Test
    void semicolonWithoutSpace() throws AbstractApplicationException, ShellException, IOException {
        command = CommandBuilder.parseCommand(echoSemicolonStringWithoutSpace,new ApplicationRunner());
        command.evaluate(System.in,baoStream);
        assertEquals("a b",baoStream.toString());
        baoStream.flush();
    }

    @Test
    void semicolonWithSpace() throws AbstractApplicationException, ShellException, IOException {
        command = CommandBuilder.parseCommand(echoSemicolonStringWithSpace,new ApplicationRunner());
        command.evaluate(System.in,baoStream);
        assertEquals("a b",baoStream.toString());
        baoStream.flush();
    }
}
