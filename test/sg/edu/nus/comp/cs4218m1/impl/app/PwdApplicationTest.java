package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sg.edu.nus.comp.cs4218.exception.PwdException;
import sg.edu.nus.comp.cs4218.impl.app.PwdApplication;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

class PwdApplicationTest {
    PwdApplication pwdApplication;
    private OutputStream output;
    private InputStream input;

    @BeforeEach
    void setUp() {
        pwdApplication = new PwdApplication();
    }

    /**
     * Test to get the absolute path with the getAbsolutePath function.
     * @throws PwdException
     */
    @Test
    void testGetAbsolutePathSuccess() throws PwdException {
        assertEquals(System.getProperty("user.dir"), pwdApplication.getAbsolutePath());
    }

    /**
     * Test the run function for pwd with default input.
     * @throws PwdException
     */
    @Test
    void testRunDefaultSuccess() throws PwdException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        pwdApplication.run(new String[0], null, baos);
        byte[] byteArray = baos.toByteArray();
        assertEquals(System.getProperty("user.dir") + System.lineSeparator(), new String(byteArray));
    }

    /**
     * The the run function with null OutputStream input.
     * @throws PwdException
     */
    @Test
    void testRunNullOutputStreamFailure(){
        assertThrows(PwdException.class, () -> {pwdApplication.run(new String[0], null, null);});
    }

    /**
     * The the run function with a closed OutputStream input.
     * @throws PwdException
     * @throws IOException
     */
    @Test
    void testRunNullInputStreamFailure() throws IOException {
        FileOutputStream fos = new FileOutputStream(TestUtils.TEST_DATA_DIR +
                File.separator + "pwd.txt");
        fos.close();
        assertThrows(PwdException.class, () -> {pwdApplication.run(new String[0], null, fos);});
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
    }
}