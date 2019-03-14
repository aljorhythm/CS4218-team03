/**
 * From Team 09
 */
package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.PwdException;
import sg.edu.nus.comp.cs4218.impl.app.PwdApplication;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

class PwdApplicationTest {

    private static final String INPUT_DATA = "Input Data";
    private InputStream input;
    private OutputStream output;
    private PwdApplication pwdApplication;

    public static final String ERR_WRITE_STREAM = "Could not write to output stream";
    public static final String ERR_NULL_STREAMS = "Null Pointer Exception";

    @BeforeEach
    public void setUp() {
        pwdApplication = new PwdApplication();
    }

    @Test
    public void testRun() throws PwdException {
        input = new ByteArrayInputStream(INPUT_DATA.getBytes());
        output = new ByteArrayOutputStream();
        String expected = Environment.currentDirectory + System.lineSeparator();
        pwdApplication.run(new String[]{""}, input, output);
        assertEquals(expected, output.toString());
    }

    @Test
    public void testGetAbsolutePath() throws PwdException {
        String curDir = pwdApplication.getAbsolutePath();
        String expected = Environment.currentDirectory;
        assertEquals(expected, curDir);
    }

    @Test
    public void testGetAbsolutePathChangeEnvironmentCurDir() throws PwdException {
        Environment.currentDirectory = "Test" + File.separator + "Test2";
        String curDir = pwdApplication.getAbsolutePath();
        String expected = Environment.currentDirectory;
        assertEquals(expected, curDir);
    }

    @Test
    public void testThrowPwdExceptionIfStdoutNull() {
        PwdException exception = assertThrows(PwdException.class, () -> {
            pwdApplication.run(new String[0], System.in, null);
        });
        assertTrue(exception.getMessage().contains(ERR_NULL_STREAMS));
    }

    @Test
    public void testThrowPwdExceptionIfStdoutClosed() throws IOException {
        final boolean[] closed = {false};
        output = mock(ByteArrayOutputStream.class);
        doAnswer(invocation -> {
            closed[0] = true;
            return null;
        }).when(output).close();
        doAnswer(invocation -> {
            if (closed[0]) {
                throw new IOException("Streams closed");
            }
            // do nothing
            return null;
        }).when(output).write(Mockito.any());

        PwdException exception = assertThrows(PwdException.class, () -> {
            output.close();
            pwdApplication.run(new String[0], input, output);
        });
        assertTrue(exception.getMessage().contains(ERR_WRITE_STREAM));
    }
}
