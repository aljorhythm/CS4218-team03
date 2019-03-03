package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.TestUtils;
import sg.edu.nus.comp.cs4218.exception.FindException;

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

    /**
     * Set up a new pwd application in between each test.
     */
    @BeforeEach
    void setUp() {
        findApplication = new FindApplication();
    }

    @Test
    void testFindFolderContentNullFileInputFailure() throws FindException {
        assertThrows(FindException.class, () -> {findApplication.findFolderContent(
                null, findTestDir);});
    }

    @Test
    void testFindFolderContentNullFolderInputFailure() throws FindException {
        assertThrows(FindException.class, () -> {findApplication.findFolderContent("find.txt", null);});
    }

    @Test
    void testFindFolderDefaultInputSuccess() throws FindException {
        assertEquals(here + File.separator + "find.txt", findApplication.findFolderContent("find.txt", here));
    }

    @Test
    void testFindFolderWildcardInputSuccess() throws FindException {
        assertEquals(here + File.separator + "find.txt", findApplication.findFolderContent("find*", here));
    }

    @Test
    void testFindFolderNoPresentFileInputSuccess() throws FindException {
        assertEquals("", findApplication.findFolderContent("find.txt", notHere));
    }

    @Test
    void testFindFolderTwoOccurrencesSuccess() throws FindException {
        assertEquals(here + File.separator + "find.txt\n" + andHere + File.separator + "find.txt\n"
                , findApplication.findFolderContent("find.txt", here, andHere));
    }

    @Test
    void testFindFolderNestedSuccess() throws FindException {
        assertEquals(nested + File.separator + "in" + File.separator + "find.txt"
                , findApplication.findFolderContent("find.txt", nested));
    }

    @Test
    void testFindFolderTwoFoldersOneOccurrenceSuccess() throws FindException {
        assertEquals(here + File.separator + "find.txt\n",
                findApplication.findFolderContent("find.txt", notHere, here));
    }
}
