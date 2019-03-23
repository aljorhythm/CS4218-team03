package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.FindException;
import sg.edu.nus.comp.cs4218.impl.app.FindApplication;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindApplicationTest {
    FindApplication findApplication = new FindApplication();
    String findTestDir = TestUtils.pathToTestDataSubdir("findTestDir");
    String here = findTestDir + File.separator + "here";
    String notHere = findTestDir + File.separator + "not_here";
    String andHere = findTestDir + File.separator + "and_here";
    String nested = findTestDir + File.separator + "nested";
    String findFileName = "find.txt";

    /**
     * Set up a new pwd application in between each test.
     */
    @BeforeEach
    void setUp() {
        findApplication = new FindApplication();
    }

    /**
     * Tests findFolderContent with null as fileName input, should throw an exception.
     * @throws FindException
     */
    @Test
    void testFindFolderContentNullFileInputFailure() throws FindException {
        assertThrows(FindException.class, () -> {findApplication.findFolderContent(null, findTestDir);});
    }

    /**
     * Tests findFolderContent with null as folderName input, should throw an exception.
     * @throws FindException
     */
    @Test
    void testFindFolderContentNullFolderInputFailure() throws FindException {
        assertThrows(FindException.class, () -> {findApplication.findFolderContent(findFileName, null);});
    }

    /**
     * Test to find a file that exists.
     * @throws FindException
     */
    @Test
    void testFindFolderDefaultInputSuccess() throws FindException {
        assertEquals(here + File.separator + findFileName, findApplication.findFolderContent(findFileName, here));
    }

    /**
     * Test to find a file by not providing the full name.
     * @throws FindException
     */
    @Test
    void testFindFolderWildcardInputSuccess() throws FindException {
        assertEquals(here + File.separator + findFileName, findApplication.findFolderContent("find.*", here));
    }

    /**
     * Test to find file that does not exist in the folder.
     * @throws FindException
     */
    @Test
    void testFindFolderNoPresentFileInputSuccess() throws FindException {
        assertEquals("", findApplication.findFolderContent(findFileName, notHere));
    }

    /**
     * Test to find a file that occurs in two different folder, should output both paths.
     * @throws FindException
     */
    @Test
    void testFindFolderTwoOccurrencesSuccess() throws FindException {
        assertEquals(here + File.separator + findFileName + StringUtils.STRING_NEWLINE
                        + andHere + File.separator + findFileName
                , findApplication.findFolderContent(findFileName, here, andHere));
    }

    /**
     * Try to find a file that is nested inside the folder.
     * @throws FindException
     */
    @Test
    void testFindFolderNestedSuccess() throws FindException {
        assertEquals(nested + File.separator + "in" + File.separator + findFileName
                , findApplication.findFolderContent(findFileName, nested));
    }

    /**
     * Test to find a file that is only in one of the provided folders.
     * @throws FindException
     */
    @Test
    void testFindFolderTwoFoldersOneOccurrenceSuccess() throws FindException {
        assertEquals(here + File.separator + findFileName,
                findApplication.findFolderContent(findFileName, notHere, here));
    }

    /**
     * Test to run the find application with null as args, should throw an exception.
     * @throws FindException
     */
    @Test
    void testRunNullArgsFailure() throws FindException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        assertThrows(FindException.class, () -> {findApplication.run(null, null, baos);});
    }

    /**
     * Try to run the find application with no args, should throw an exception.
     * @throws FindException
     */
    @Test
    void testRunEmptyArgsFailure() throws FindException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] emptyStringArray = new String[0];
        assertThrows(FindException.class, () -> {findApplication.run(emptyStringArray, null, baos);});
    }

    /**
     * Try to run the find application with no fileName provided, should throw an exception.
     * @throws FindException
     */
    @Test
    void testRunMissingFileNameFailure() throws FindException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] args = new String[1];
        args[0] = here;
        assertThrows(FindException.class, () -> {findApplication.run(args, null, baos);});
    }

    /**
     * Test to run the find application with standard input, find a file in a folder.
     * @throws FindException
     */
    @Test
    void testRunFindFileSuccess() throws FindException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] args = new String[3];
        args[0] = here;
        args[1] = "-name";
        args[2] = findFileName;
        findApplication.run(args, null, baos);
        byte[] byteArray = baos.toByteArray();
        assertEquals(here + File.separator + findFileName, new String(byteArray));
    }

    /**
     * Test to run the find application with a fileName that is not in the folder.
     * @throws FindException
     */
    @Test
    void testRunNoSuchFileSuccess() throws FindException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] args = new String[3];
        args[0] = notHere;
        args[1] = "-name";
        args[2] = findFileName;
        findApplication.run(args, null, baos);
        byte[] byteArray = baos.toByteArray();
        assertEquals("", new String(byteArray));
    }

    /**
     * Test to run the find application to find the file in multiple folders.
     * @throws FindException
     */
    @Test
    void testRunMultipleFoldersSuccess() throws FindException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] args = new String[4];
        args[0] = here;
        args[1] = andHere;
        args[2] = "-name";
        args[3] = findFileName;
        findApplication.run(args, null, baos);
        byte[] byteArray = baos.toByteArray();
        assertEquals(here + File.separator + findFileName + StringUtils.STRING_NEWLINE
                + andHere + File.separator + findFileName, new String(byteArray));
    }
}
