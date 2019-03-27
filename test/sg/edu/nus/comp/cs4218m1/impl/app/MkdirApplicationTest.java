package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.MkdirException;
import sg.edu.nus.comp.cs4218.impl.app.MkdirApplication;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MkdirApplicationTest {
    MkdirApplication mkdirApplication;
    String pathToTestDir = TestUtils.TEST_DATA_DIR + File.separator + "mkdirTestDir" + File.separator;
    String tempDir = pathToTestDir + "mkdirTest";
    String tempDir2 = pathToTestDir + "mkdirTest2";
    String tempParent = pathToTestDir + "mkdirTestParent";
    String tempChild = tempParent + File.separator + "mkdirTestChild";

    /**
     * Set up mkdir application for each test. Reset the file structure between the tests.
     */
    @BeforeEach
    void setUp() {
        mkdirApplication = new MkdirApplication();
        deleteDirectory(null, new File(pathToTestDir).listFiles());
    }

    /**
     *  Add empty txt file so git tracks the directory.
     */
    @AfterEach
    void tearDown() throws IOException {
        File file = new File(pathToTestDir + File.separator + "EmptyFileForGitTracking.txt");
        file.createNewFile();
    }

    /**
     * Helper function used to delete the a directory and its content.
     * @param directory The directory to be deleted.
     * @param files The content to be deleted.
     */
    public static void deleteDirectory(File directory, File... files) {
        if(null != files){
            for(int i=0; i< files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteDirectory(files[i], files[i].listFiles());
                }
                else {
                    files[i].delete();
                }
            }
        }
        if(directory != null) {
            directory.delete();
        }
    }

    /**
     * Test createFolder with null input, should throw an exception.
     */
    @Test
    void testCreateFolderNullInputFailure() {
        assertThrows(MkdirException.class, () -> {mkdirApplication.createFolder(null);});
    }

    /**
     * Test createFolder with empty directory name, which should do nothing.
     * @throws MkdirException
     */
    @Test
    void testCreateFolderEmptyInputSuccess() throws Exception {
        mkdirApplication.createFolder("");
        assertEquals(0, new File(pathToTestDir).list().length);
    }

    /**
     * Test to create one directory with createFolder.
     * @throws MkdirException
     */
    @Test
    void testCreateFolderOneNewDirectoryInputSuccess() throws Exception {
        mkdirApplication.createFolder(tempDir);
        assertTrue(new File(tempDir).exists());
    }

    /**
     * Test to create two directories with createFolder.
     * @throws MkdirException
     */
    @Test
    void testCreateFolderTwoNewDirectoryInputSuccess() throws Exception {
        mkdirApplication.createFolder(tempDir, tempDir2);
        assertTrue(new File(tempDir).exists());
        assertTrue(new File(tempDir2).exists());
    }

    /**
     * Test creating a directory and then a directory within it.
     * @throws MkdirException
     */
    @Test
    void testCreateFolderDirectoryInDirectoryInputSuccess() throws Exception {
        mkdirApplication.createFolder(tempParent, tempChild);
        assertTrue(new File(tempChild).exists());
    }

    /**
     * Test to run the mkdir application with null as args, should throw an exception.
     */
    @Test
    void testRunNullInputFailure() {
        assertThrows(MkdirException.class, () -> {mkdirApplication.run(null, null, null);});
    }

    /**
     * Test to run the mkdir application with empty string as only argument, should to nothing.
     * @throws MkdirException
     */
    @Test
    void testRunEmptyInputSuccess() throws MkdirException {
        String[] args = new String[0];
        assertThrows(MkdirException.class, () -> {mkdirApplication.run(args, null, null);});
    }

    /**
     * Test to run the mkdir application to create a directory.
     * @throws MkdirException
     */
    @Test
    void testRunOneNewDirectoryInputSuccess() throws AbstractApplicationException {
        String[] args = new String[1];
        args[0] = tempDir;
        mkdirApplication.run(args, null, null);
        assertTrue(new File(tempDir).exists());
    }


    /**
     * Test to run the mkdir application to create two directories.
     * @throws MkdirException
     */
    @Test
    void testRunTwoNewDirectoryInputSuccess() throws AbstractApplicationException {
        String[] args = new String[2];
        args[0] = tempDir;
        args[1] = tempDir2;
        mkdirApplication.run(args, null, null);
        assertTrue(new File(tempDir).exists());
        assertTrue(new File(tempDir2).exists());
    }

    /**
     * Test to run the mkdir application to create a directory and then another
     * directory within the first one.
     * @throws MkdirException
     */
    @Test
    void testRunDirectoryInDirectoryInputSuccess() throws AbstractApplicationException {
        String[] args = new String[2];
        args[0] = tempParent;
        args[1] = tempChild;
        mkdirApplication.run(args, null, null);
        assertTrue(new File(tempChild).exists());
    }
}
