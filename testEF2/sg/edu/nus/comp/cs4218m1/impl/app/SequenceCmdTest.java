package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static sg.edu.nus.comp.cs4218m1.TestUtils.*;

class SequenceCmdTest {

    /**
     * Mocks a call command
     * Simply writes input from stdin and input argument to output
     */
    static class MockCommand extends CallCommand{

        private final String input;

        public MockCommand(String word) {
            super(null, null, null);
            input = word;
        }

        @Override
        public void evaluate(InputStream stdin, OutputStream stdout) throws ShellException {
            byte[] buffer = new byte[1024];
            int length;
            try {
                while ((length = stdin.read(buffer)) != -1) {
                    stdout.write(buffer, 0, length);
                }
                buffer = (input + STRING_NEWLINE).getBytes(CHARSET_UTF8);
                stdout.write(buffer);
            } catch (IOException e) {
                throw new ShellException(INVALID_IO_STREAM);//NOPMD
            }
        }
    }

    /**
     * Tests sequence of two commands ABC first.
     */
    @Test
    void evaluateTwoCommandsAFDL() throws Exception {
        mock(CallCommand.class);
        InputStream inputStream = new ByteArrayInputStream("test data ".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        CallCommand[] commands = {
                new MockCommand("ABC"), new MockCommand("DEF")
        };

        SequenceCommand sequenceCommand = new SequenceCommand(Arrays.asList(commands));
        sequenceCommand.evaluate(inputStream, outputStream);
        String expected = "test data ABC" + STRING_NEWLINE + "DEF" + STRING_NEWLINE;
        String actual = outputStream.toString("UTF-8");
        assertEquals(expected, actual);
    }

    /**
     * Tests sequence of two commands DEF first.
     */
    @Test
    void evaluateTwoCommandsDFAL() throws Exception {
        mock(CallCommand.class);
        InputStream inputStream = new ByteArrayInputStream("test data ".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        CallCommand[] commands = {
                new MockCommand("DEF"), new MockCommand("ABC")
        };

        SequenceCommand sequenceCommand = new SequenceCommand(Arrays.asList(commands));
        sequenceCommand.evaluate(inputStream, outputStream);
        String expected = "test data DEF" + STRING_NEWLINE + "ABC" + STRING_NEWLINE;
        String actual = outputStream.toString("UTF-8");
        assertEquals(expected, actual);
    }
}
