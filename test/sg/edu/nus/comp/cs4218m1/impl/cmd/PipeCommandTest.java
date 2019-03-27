package sg.edu.nus.comp.cs4218m1.impl.cmd;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static sg.edu.nus.comp.cs4218m1.TestUtils.CHARSET_UTF8;

class PipeCommandTest {
    /**
     * Mocks a call command
     * Simply writes input from stdin and "abc" to output
     */
    class MockCommand extends CallCommand {

        public MockCommand() {
            super(null,  null);
        }

        @Override
        public void evaluate(InputStream stdin, OutputStream stdout) throws ShellException {
            byte[] buffer = new byte[1024];
            int length;

            try {

                while ((length = stdin.read(buffer)) != -1) {
                    stdout.write(buffer, 0, length);
                }
                buffer = "abc".getBytes(CHARSET_UTF8);
                stdout.write(buffer);
            } catch (IOException e) {
                throw new ShellException("");//NOPMD
            }
        }

        @Override
        public void terminate() {
            //override, useless here
        }
    }

    /**
     * Tests piping of two commands
     */
    @Test
    void evaluateTwoCommands() throws Exception {
        CallCommand command1 = mock(CallCommand.class);
        InputStream inputStream = new ByteArrayInputStream("test data ".getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();

        CallCommand[] commands = {
                new MockCommand(), new MockCommand()
        };

        PipeCommand pipeCommand = new PipeCommand((List<CallCommand>) Arrays.asList(commands));
        pipeCommand.evaluate(inputStream, outputStream);
        String expected = "test data abcabc";
        String actual = ((ByteArrayOutputStream) outputStream).toString("UTF-8");
        assertEquals(actual, expected);
    }

    /**
     * Tests piping of three commands
     */
    @Test
    void evaluateThreeCommands() throws Exception {
        CallCommand command1 = mock(CallCommand.class);
        InputStream inputStream = new ByteArrayInputStream("test data ".getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();

        CallCommand[] commands = {
                new MockCommand(), new MockCommand(), new MockCommand()
        };

        PipeCommand pipeCommand = new PipeCommand((List<CallCommand>) Arrays.asList(commands));
        pipeCommand.evaluate(inputStream, outputStream);
        String expected = "test data abcabcabc";
        String actual = ((ByteArrayOutputStream) outputStream).toString("UTF-8");
        assertEquals(actual, expected);
    }
}