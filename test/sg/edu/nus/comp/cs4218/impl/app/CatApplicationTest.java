package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.TestUtils;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class CatApplicationTest {
    CatApplication catApplication;
    InputStream defaultIStream;
    String defaultString = "ab abc" + STRING_NEWLINE + "b ccc";
    InputStream emptyIStream;
    String nonExistentFile = "wrong.txt";
    String test_dir = TestUtils.pathToTestDataSubdir("inputFiles");
    String emptyFile = test_dir + CHAR_FILE_SEP + "catEmpty.txt";
    String testFileName1 = test_dir + CHAR_FILE_SEP + "catContent1.txt";
    String testFileName2 = test_dir + CHAR_FILE_SEP + "catContent2.txt";
    String testFile1Content = "test string for\ntesting cat\nwith junit 5.\n";
    String testFile2Content = "another file, number 2\nsecond for testing that\ncat \nworks like \nit should\n. 1234 %&#!$@\n";

    /**
     * Set up a new cat application and mock inputstreams in between each test.
     *
     * @throws Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        catApplication = new CatApplication();
        defaultIStream = IOUtils.stringToInputStream(defaultString);
        emptyIStream = mock(InputStream.class);
        when(emptyIStream.read()).thenReturn(-1);
    }

    /**
     * Test catStdin with null input which should throw an exception.
     */
    @Test
    public void testCatStdinNullInputFailure() {
        assertThrows(CatException.class, () -> {
            catApplication.catStdin(null);
        });
    }

    /**
     * Test catStdin with empty inputstream which should return an empty string.
     *
     * @throws CatException
     */
    @Test
    public void testCatStdinEmptyInputSuccess() throws CatException {
        assertEquals("", catApplication.catStdin(emptyIStream));
    }

    /**
     * Test catStdin with an inputstream with content.
     *
     * @throws CatException
     */
    @Test
    public void testCatStdinDefaultInputSuccess() throws CatException {
        assertEquals("ab abc\nb ccc", catApplication.catStdin(defaultIStream));
    }

    /**
     * Test catFiles with null input which should throw an exception.
     */
    @Test
    public void testCatFilesNullInputFailure() {
        assertThrows(CatException.class, () -> {
            catApplication.catFiles(null);
        });
    }

    /**
     * Test catFiles with a file that does not exist, should throw an exception.
     */
    @Test
    public void testCatFilesNonExistingFileFailure() {
        assertThrows(CatException.class, () -> {
            catApplication.catFiles(nonExistentFile);
        });
    }

    /**
     * Test catFiles with an empty input file, should return an empty string.
     *
     * @throws CatException
     */
    @Test
    public void testCatFilesEmptyInputSuccess() throws CatException {
        assertEquals("", catApplication.catFiles(emptyFile));
    }

    /**
     * Test catFiles with a file with content.
     *
     * @throws CatException
     */
    @Test
    public void testCatFilesDefaultInputSuccess() throws CatException {
        assertEquals(testFile1Content, catApplication.catFiles(testFileName1));
    }

    /**
     * Test catFiles with two files as input.
     *
     * @throws CatException
     */
    @Test
    public void testCatFilesTwoInputFilesSuccess() throws CatException {
        assertEquals(testFile1Content + testFile2Content,
                catApplication.catFiles(testFileName1, testFileName2));
    }

    /**
     * Test run function with null args, should throw an exception.
     */
    @Test
    public void testRunNullArgsFailure() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        assertThrows(CatException.class, () -> {
            catApplication.run(null, emptyIStream, baos);
        });
    }

    /**
     * Test run function with null inputstream when args is an empty string, should throw an exception.
     */
    @Test
    public void testRunNullInputStreamFailure() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] emptyStringArray = new String[0];
        assertThrows(CatException.class, () -> {
            catApplication.run(emptyStringArray, null, baos);
        });
    }

    /**
     * Test run function with null a null outputstream, should throw an exception.
     */
    @Test
    public void testRunNullOutputStreamFailure() {
        String[] emptyStringArray = new String[0];
        assertThrows(CatException.class, () -> {
            catApplication.run(emptyStringArray, emptyIStream, null);
        });
    }

    /**
     * Test run function with no args, so should write inputstream content to the outputstream.
     */
    @Test
    public void testRunNoArgsSuccess() throws CatException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] emptyArgs = new String[0];
        catApplication.run(emptyArgs, defaultIStream, baos);
        byte[] byteArray = baos.toByteArray();
        assertEquals("ab abc\nb ccc", new String(byteArray));
    }

    /**
     * Test run function with a file as argument.
     *
     * @throws CatException
     */
    @Test
    public void testRunOneArgSuccess() throws CatException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] args = new String[1];
        args[0] = testFileName1;
        catApplication.run(args, null, baos);
        byte[] byteArray = baos.toByteArray();
        assertEquals(testFile1Content, new String(byteArray));
    }

    /**
     * Test run function with two files as args.
     *
     * @throws CatException
     */
    @Test
    public void testRunTwoArgSuccess() throws CatException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] args = new String[2];
        args[0] = testFileName1;
        args[1] = testFileName2;
        catApplication.run(args, null, baos);
        byte[] byteArray = baos.toByteArray();
        assertEquals(testFile1Content + testFile2Content, new String(byteArray));
    }

}
