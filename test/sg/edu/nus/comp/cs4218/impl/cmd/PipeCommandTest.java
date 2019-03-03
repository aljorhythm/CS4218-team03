package sg.edu.nus.comp.cs4218.impl.cmd;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static sg.edu.nus.comp.cs4218.exception.ShellException.INVALID_INPUT_STREAM;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHARSET_UTF8;

class PipeCommandTest {
    /**
     * Mocks a call command
     * Simply writes input from stdin and "abc" to output
     */
    class MockCommand extends CallCommand {

        public MockCommand() {
            super(null, null);
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
                throw new ShellException(INVALID_INPUT_STREAM);
            }
        }

        @Override
        public void terminate() {

        }
    }

    /**
     * Tests piping of two commands
     */
    @Test
    void evaluate_twoCommands() throws Exception {
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
    void evaluate_threeCommands() throws Exception {
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