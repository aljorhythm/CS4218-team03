package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.FindException;
import sg.edu.nus.comp.cs4218.impl.app.FindApplication;
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
     * HACKATHON START
     */
    /**
     * Tests findFolderContent with null as folderName input, should throw an exception.
     * @throws FindException
     */
    @Test
    void testFindFolderContentNullFolderInputFailure() throws FindException {
        assertThrows(FindException.class, () -> {findApplication.findFolderContent(findFileName, null);});
    }



    /**
     * Test to run the find application with standard input, find a file in a folder.
     * @throws FindException
     */
    @Test
    void testRunFindFileSuccess() throws AbstractApplicationException {
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
     * END
     */

}
