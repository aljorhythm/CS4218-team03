package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.PwdException;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class PwdApplicationTest {
    PwdApplication pwdApplication;

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
        assertEquals(System.getProperty("user.dir"), new String(byteArray));
    }

    /**
     * The the run function with null OutputStream input.
     * @throws PwdException
     */
    @Test
    void testRunNullOutputStreamFailure() throws PwdException {
        assertThrows(PwdException.class, () -> {pwdApplication.run(new String[0], null, null);});
    }

    /**
     * The the run function with a closed OutputStream input.
     * @throws PwdException
     * @throws IOException
     */
    @Test
    void testRunClosedOutputStreamFailure() throws PwdException, IOException {
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")
                + File.separator + "test" + File.separator + "sg" + File.separator + "edu" + File.separator +
                "nus" + File.separator + "comp" + File.separator + "cs4218" + File.separator +"inputFiles" +
                File.separator + "pwd.txt");
        fos.close();
        assertThrows(PwdException.class, () -> {pwdApplication.run(new String[0], null, fos);});
    }
}