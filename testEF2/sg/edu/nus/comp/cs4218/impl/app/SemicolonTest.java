package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static sg.edu.nus.comp.cs4218.exception.ShellException.INVALID_INPUT_STREAM;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHARSET_UTF8;

class SemicolonTest {
    /**
     * Mocks a call command
     * Simply writes input from stdin and "ABC" to output
     */
    class MockCommandABC extends CallCommand{

        public MockCommandABC() {
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
                buffer = "ABC".getBytes(CHARSET_UTF8);
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
     * Mocks a call command
     * Simply writes input from stdin and "DEF" to output
     */
    class MockCommandDEF extends CallCommand{

        public MockCommandDEF() {
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
                buffer = "DEF".getBytes(CHARSET_UTF8);
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
                new MockCommandABC(), new MockCommandDEF()
        };

        SequenceCommand sequenceCommand = new SequenceCommand(Arrays.asList(commands));
        sequenceCommand.evaluate(inputStream, outputStream);
        String expected = "test data ABCDEF";
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
                new MockCommandDEF(), new MockCommandABC()
        };

        SequenceCommand sequenceCommand = new SequenceCommand(Arrays.asList(commands));
        sequenceCommand.evaluate(inputStream, outputStream);
        String expected = "test data DEFABC";
        String actual = ((ByteArrayOutputStream) outputStream).toString("UTF-8");
        assertEquals(actual, expected);
    }
}