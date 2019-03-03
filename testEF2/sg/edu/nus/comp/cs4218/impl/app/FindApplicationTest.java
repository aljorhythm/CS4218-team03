package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.TestUtils;
import sg.edu.nus.comp.cs4218.exception.FindException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

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

    @Test
    void testFindFolderContentNullFileInputFailure() throws FindException {
        assertThrows(FindException.class, () -> {findApplication.findFolderContent(null, findTestDir);});
    }

    @Test
    void testFindFolderContentNullFolderInputFailure() throws FindException {
        assertThrows(FindException.class, () -> {findApplication.findFolderContent(findFileName, null);});
    }

    @Test
    void testFindFolderDefaultInputSuccess() throws FindException {
        assertEquals(here + File.separator + findFileName, findApplication.findFolderContent(findFileName, here));
    }

    @Test
    void testFindFolderWildcardInputSuccess() throws FindException {
        assertEquals(here + File.separator + findFileName, findApplication.findFolderContent("find*", here));
    }

    @Test
    void testFindFolderNoPresentFileInputSuccess() throws FindException {
        assertEquals("", findApplication.findFolderContent(findFileName, notHere));
    }

    @Test
    void testFindFolderTwoOccurrencesSuccess() throws FindException {
        assertEquals(here + File.separator + findFileName + StringUtils.STRING_NEWLINE
                        + andHere + File.separator + findFileName
                , findApplication.findFolderContent(findFileName, here, andHere));
    }

    @Test
    void testFindFolderNestedSuccess() throws FindException {
        assertEquals(nested + File.separator + "in" + File.separator + findFileName
                , findApplication.findFolderContent(findFileName, nested));
    }

    @Test
    void testFindFolderTwoFoldersOneOccurrenceSuccess() throws FindException {
        assertEquals(here + File.separator + findFileName,
                findApplication.findFolderContent(findFileName, notHere, here));
    }

    @Test
    void testRunNullArgsFailure() throws FindException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        assertThrows(FindException.class, () -> {findApplication.run(null, null, baos);});
    }

    @Test
    void testRunEmptyArgsFailure() throws FindException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] emptyStringArray = new String[0];
        assertThrows(FindException.class, () -> {findApplication.run(emptyStringArray, null, baos);});
    }

    @Test
    void testRunMissingFileNameFailure() throws FindException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] args = new String[1];
        args[0] = here;
        assertThrows(FindException.class, () -> {findApplication.run(args, null, baos);});
    }

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
