package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.CdException;
import sg.edu.nus.comp.cs4218.impl.app.CdApplication;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CdApplicationTest {
    private CdApplication cdApplication;

    /**
     * Original working directory before this test class is run
     */
    private static String origWorkingDir;

    /**
     * Testing directory
     */
    private static String testWorkingDir;

    /**
     * Initializes application for each test
     */
    @BeforeEach
    public void initApplication(){
        cdApplication = new CdApplication();
    }


    @BeforeAll
    public static void createDirectories() throws IOException {
        origWorkingDir = Environment.currentDirectory;
        testWorkingDir = TestUtils.TEST_DATA_DIR;
        Environment.currentDirectory = testWorkingDir;
    }

    /**
     * Sets working directory to temporary test directory before each test
     */
    @BeforeEach
    public void setWorkingDirectory() {
        Environment.currentDirectory = testWorkingDir;
    }

    /**
     * Reverts working directory
     */
    @AfterAll
    public static void revertCurrentDirectory(){
        Environment.currentDirectory = origWorkingDir;
    }

    @Test
    void cdFileExists() throws CdException, IOException {
        cdApplication.changeToDirectory("cdTestDir");
        assertEquals(testWorkingDir + File.separator + "cdTestDir", Environment.currentDirectory);
    }

    /**
     * Test if the cd throw an exception when meeting a fake path
     */
    @Test
    void cdEmptyFile(){
        assertThrows(CdException.class, () -> {cdApplication.changeToDirectory("non-existing");});
    }
}
