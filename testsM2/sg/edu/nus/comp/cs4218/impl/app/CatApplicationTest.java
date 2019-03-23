/**
 * From Team 09
 */
package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.condition.OS.WINDOWS;
import static org.mockito.Mockito.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

class CatApplicationTest {

    private static final String INPUT_DATA = "Input Data";
    private CatApplication catApplication;
    private InputStream input;
    private OutputStream output;

    public static final String ERR_WRITE_STREAM = "Could not write to output stream";
    public static final String ERR_NULL_STREAMS = "Null Pointer Exception";
    public static final String ERR_FILE_NOT_FOUND = "No such file or directory";
    public static final String ERR_IS_DIR = "This is a directory";
    public static final String ERR_NO_PERM = "Permission denied";
    public static final String ERR_GENERAL = "Exception Caught";

    public static final String pathToTestDataDir = System.getProperty("user.dir") + File.separator + "TestResources";

    @BeforeEach
    void setUp() {
        catApplication = new CatApplication();
        input = new ByteArrayInputStream(INPUT_DATA.getBytes());
        output = new ByteArrayOutputStream();
    }

    @Test
    public void testCatFilesThrowExceptionIfFilenameNull() throws Exception {
        catApplication = mock(CatApplication.class);
        when(catApplication.catFiles(Mockito.any())).thenCallRealMethod();
        Exception exception = assertThrows(Exception.class, () -> {
            catApplication.catFiles((String[]) null);
        });
        assertTrue(exception.getMessage().contains(ERR_GENERAL));
    }

    @Test
    public void testCatFilesWithNoFiles() throws Exception {
        catApplication = mock(CatApplication.class);
        when(catApplication.catFiles(Mockito.any())).thenCallRealMethod();
        assertThrows(CatException.class, () -> {catApplication.catFiles("");});
    }

    @Test
    public void testCatFilesWithSingleFile() throws Exception {
        catApplication = mock(CatApplication.class);
        when(catApplication.catFiles(Mockito.any())).thenCallRealMethod();
        String dummy = pathToTestDataDir +"/wc/wc_file1.txt";
        String expected = "Hello World" + STRING_NEWLINE;
        assertEquals(expected, catApplication.catFiles(dummy));
    }

    @Test
    public void testCatFilesWithMissingFileAndNormal() throws Exception {
        catApplication = mock(CatApplication.class);
        when(catApplication.catFiles(Mockito.any(), Mockito.any())).thenCallRealMethod();
        String filename1 = "invalid";
        String filename2 = pathToTestDataDir + "/wc/wc_file1.txt";
        assertThrows(CatException.class, () -> {catApplication.catFiles(filename1, filename2);});
    }

    @Test
    public void testCatFilesWithDirectory() throws Exception {
        catApplication = mock(CatApplication.class);
        when(catApplication.catFiles(Mockito.any())).thenCallRealMethod();
        String filename = Paths.get(getClass().getResource("/sg/").toURI()).toString();
        assertThrows(CatException.class, () -> {catApplication.catFiles(filename);});
    }

    @Test
    public void testCatFilesWithMissingFile() throws Exception {
        catApplication = mock(CatApplication.class);
        when(catApplication.catFiles(Mockito.any())).thenCallRealMethod();
        String filename = "invalid";
        assertThrows(CatException.class, () -> {catApplication.catFiles(filename);});
    }

    @Test
    @DisabledOnOs(WINDOWS)
    public void testCatFilesWithNoPermissions(@TempDir Path tempDir) throws Exception {
        // Create temporary file
        String filename = tempDir.toAbsolutePath().toString() + File.separator + "noperm.txt";
        File node = IOUtils.resolveFilePath(filename).toFile();
        node.createNewFile();
        node.setReadable(false, false);

        catApplication = mock(CatApplication.class);
        when(catApplication.catFiles(Mockito.any())).thenCallRealMethod();
        assertTrue(catApplication.catFiles(filename).contains(ERR_NO_PERM));
    }

    @Test
    public void testCatStdinThrowExceptionIfInputStreamNull() throws Exception {
        catApplication = mock(CatApplication.class);
        when(catApplication.catStdin(Mockito.any())).thenCallRealMethod();
        Exception exception = assertThrows(Exception.class, () -> {
            catApplication.catStdin(null);
        });
        assertTrue(exception.getMessage().contains(ERR_NULL_STREAMS));
    }

    @Test
    public void testCatStdin() throws Exception {
        catApplication = mock(CatApplication.class);
        when(catApplication.catStdin(Mockito.any(InputStream.class))).thenCallRealMethod();
        String expected = "Input Data";
        assertEquals(expected, catApplication.catStdin(input));
    }

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    public void testThrowCatExceptionIfStdoutNull() {
        fail("this test is redundant, program should still run if there is no stdout");
        CatException exception = assertThrows(CatException.class, () -> {
            catApplication.run(new String[0], System.in, null);
        });
        assertTrue(exception.getMessage().contains(ERR_NULL_STREAMS));
    }
    */

    @Test
    public void testThrowExceptionIfStdinNull() {
        CatException exception = assertThrows(CatException.class, () -> {
            catApplication.run(new String[0], null, output);
        });
        assertTrue(exception.getMessage().contains(ERR_NULL_STREAMS));
    }

    @Test
    public void testThrowCatExceptionIfStdoutClosed() throws IOException {
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

        CatException exception = assertThrows(CatException.class, () -> {
            output.close();
            catApplication.run(new String[0], input, output);
        });
        assertTrue(exception.getMessage().contains(ERR_WRITE_STREAM));
    }

    @Test
    public void testRunApplicationWithStdin() throws CatException {
        catApplication.run(null, input, output);
        String expected = "Input Data";
        assertEquals(expected, output.toString());
    }

    @Test
    public void testRunApplicationWithFiles() throws Exception {
        String filename1 = pathToTestDataDir + "/wc/wc_file1.txt";
        String filename2 = pathToTestDataDir + "/wc/wc_file2.txt";
        catApplication.run(new String[]{filename1, filename2}, input, output);
        String expected = "Hello World" + STRING_NEWLINE + STRING_NEWLINE + "EmptyFile";
        assertEquals(expected, output.toString());
    }
}
