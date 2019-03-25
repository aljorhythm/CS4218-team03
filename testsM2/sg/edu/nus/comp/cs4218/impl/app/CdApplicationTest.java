/**
 * From Team 09
 */
package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.CdException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.exception.CdException.*;
import static sg.edu.nus.comp.cs4218.impl.util.IOUtils.resolveFilePath;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
class CdApplicationTest {

    private CdApplication cdApplication;

    /**
     * Original working directory before this test class is run
     */
    private static String origWorkingDir;

    /**
     * Working directory where temporary files are created
     */
    private static String testWorkingDir;

    /**
     * Initializes application for each test
     */
    @BeforeEach
    public void initApplication(){
        cdApplication = new CdApplication();
    }

    /**
     * Creates directories for tests, sets working directory
     * @param tempDir
     * @throws IOException
     */
    @BeforeAll
    public static void createDirectories(@TempDir Path tempDir) throws IOException {
        origWorkingDir = Environment.currentDirectory;
        System.out.println("Original dir was:" + Environment.currentDirectory);

        testWorkingDir = tempDir.toString();
        Environment.currentDirectory = testWorkingDir;

        // Create dummy files in currentDirectory
        String[] folders = {"folder1" + File.separator + "folder1-1",
                "folder2", StringUtils.STRING_PARENT_DIR + File.separator + "folder3", "f0lder",
                "f@ld%r", "..folder", "fol der",
                "thisisaveryveryveryveryveryveryveryveryveryveryveryveryveryvery" +
                        "veryveryveryveryveryveryveryveryveryverylongfolder"};
        String[] files = {"folder1" + File.separator + "file.txt"};
        for (String folder : folders) {
            File node = resolveFilePath(folder).toFile();
            node.mkdirs();
        }
        for (String file : files) {
            File node = resolveFilePath(file).toFile();
            node.createNewFile();
        }
    }

    /**
     * Sets working directory to temporary test directory before each test
     */
    @BeforeEach
    public void setWorkingDirectory() {
        Environment.currentDirectory = testWorkingDir;
        System.out.println("Current directory set to :" + Environment.currentDirectory);
    }

    /**
     * Reverts working directory
     */
    @AfterAll
    public static void revertCurrentDirectory(){
        Environment.currentDirectory = origWorkingDir;
        System.out.println("Current directory reverted to :" + Environment.currentDirectory);
    }

    /**
     * Change working directory to sub directory
     * @throws CdException
     */
    @Test
    public void testRunOneNextPath() throws CdException {
        String expected = Environment.currentDirectory + File.separator + "folder1";
        cdApplication.run(new String[]{"folder1"}, null, null);
        assertEquals(expected, Environment.currentDirectory);
    }

    /**
     * Change working directory to parent directory
     * @throws CdException
     */
    @Test
    public void testRunOnePrevPath() throws CdException {
        int index = Environment.currentDirectory.lastIndexOf(File.separator);
        String expected = Environment.currentDirectory.substring(0, index);
        cdApplication.run(new String[]{StringUtils.STRING_PARENT_DIR}, null, null);
        assertEquals(expected, Environment.currentDirectory);
    }

    /**
     * Change working directory to sub sub directory
     * @throws CdException
     */
    @Test
    public void testRunTwoNextPath() throws CdException {
        String expected = Environment.currentDirectory + File.separator + "folder1" +
                File.separator + "folder1-1";
        cdApplication.run(new String[]{"folder1"}, null, null);
        cdApplication.run(new String[]{"folder1-1"}, null, null);
        assertEquals(expected, Environment.currentDirectory);
    }

    /**
     * Change working directory to parent's parent directory
     * @throws CdException
     */
    @Test
    public void testRunTwoPrevPath() throws CdException {
        int index = Environment.currentDirectory.lastIndexOf(File.separator);
        String expected = Environment.currentDirectory.substring(0, index) + File.separator + "folder3";
        cdApplication.run(new String[]{"folder1"}, null, null);
        cdApplication.run(new String[]{StringUtils.STRING_PARENT_DIR + File.separator +
                StringUtils.STRING_PARENT_DIR + File.separator + "folder3"}, null, null);
        assertEquals(expected, Environment.currentDirectory);
    }

    /**
     * Change working directory to absolute path
     * @throws CdException
     */
    @Test
    public void testRunAbsolutePath() throws CdException {
        String expected = Environment.currentDirectory + File.separator + "folder1" +
                File.separator + "folder1-1";
        cdApplication.run(new String[]{expected}, null, null);
        assertEquals(expected, Environment.currentDirectory);
    }

    /**
     * Change working directory to absolute path and then it's parent
     * @throws CdException
     */
    @Test
    public void testRunAbsolutePrevPath() throws CdException {
        String absolute = Environment.currentDirectory + File.separator + "folder1" +
                File.separator + "folder1-1";
        String expected = Environment.currentDirectory + File.separator + "folder1";
        cdApplication.run(new String[]{absolute}, null, null);
        cdApplication.run(new String[]{StringUtils.STRING_PARENT_DIR}, null, null);
        assertEquals(expected, Environment.currentDirectory);
    }

    /**
     * Change working directory to absolute path and then it's sub directory
     * @throws CdException
     */
    @Test
    public void testRunAbsoluteNextPath() throws CdException {
        String absolute = Environment.currentDirectory + File.separator + "folder1";
        String expected = Environment.currentDirectory + File.separator + "folder1" +
                File.separator + "folder1-1";
        cdApplication.run(new String[]{absolute}, null, null);
        cdApplication.run(new String[]{"folder1-1"}, null, null);
        assertEquals(expected, Environment.currentDirectory);
    }

    /**
     * Change working directory to sub sub directory and then it's parent
     * @throws CdException
     */
    @Test
    public void testRunNextPrevPath() throws CdException {
        String expected = Environment.currentDirectory + File.separator + "folder1";
        cdApplication.run(new String[]{"folder1"}, null, null);
        cdApplication.run(new String[]{"folder1-1"}, null, null);
        cdApplication.run(new String[]{StringUtils.STRING_PARENT_DIR}, null, null);
        assertEquals(expected, Environment.currentDirectory);
    }

    /**
     * Change working directory to sub directory then it's parent's sub directory
     * @throws CdException
     */
    @Test
    public void testRunPrevNextPath() throws CdException {
        String expected = Environment.currentDirectory + File.separator + "folder2";
        cdApplication.run(new String[]{"folder1"}, null, null);
        cdApplication.run(new String[]{StringUtils.STRING_PARENT_DIR}, null, null);
        cdApplication.run(new String[]{"folder2"}, null, null);
        assertEquals(expected, Environment.currentDirectory);
    }

    /**
     * Change working directory to absolute path
     * @throws Exception
     */
    @Test
    public void testChangeToAbsoluteDirectory() throws Exception {
        String absolutePath = Environment.currentDirectory + File.separator + "folder1" + File.separator +
                "folder1-1";
        String expected = absolutePath;
        cdApplication.changeToDirectory(absolutePath);
        assertEquals(expected, Environment.currentDirectory);
    }

    /**
     * Change working directory to sub folder
     * @throws Exception
     */
    @Test
    public void testChangeToRelativeNextFolderDirectory() throws Exception {
        String relativePath = "folder2";
        String expected = Environment.currentDirectory + File.separator + "folder2";
        cdApplication.changeToDirectory(relativePath);
        assertEquals(expected, Environment.currentDirectory);
    }

    /**
     * Change working directory to relative path
     * @throws Exception
     */
    @Test
    public void testChangeToRelativePrevFolderDirectory() throws Exception {
        String relativePath = StringUtils.STRING_PARENT_DIR + File.separator + "folder3";
        int index = Environment.currentDirectory.lastIndexOf(File.separator);
        String expected = Environment.currentDirectory.substring(0, index) + File.separator + "folder3";
        cdApplication.changeToDirectory(relativePath);
        assertEquals(expected, Environment.currentDirectory);
    }

    /**
     * Change working directory to path with digits
     * @throws Exception
     */
    @Test
    public void testPathWithNumber() throws Exception {
        String absolutePath = Environment.currentDirectory + File.separator + "f0lder";
        String expected = absolutePath;
        cdApplication.changeToDirectory(absolutePath);
        assertEquals(expected, Environment.currentDirectory);
    }

    /**
     * Change working directory to path with special characters
     * @throws Exception
     */
    @Test
    public void testPathWithSpecialCharacters() throws Exception {
        String absolutePath = Environment.currentDirectory + File.separator + "f@ld%r";
        String expected = absolutePath;
        cdApplication.changeToDirectory(absolutePath);
        assertEquals(expected, Environment.currentDirectory);
    }

    /**
     * Change working directory to path with ..
     * @throws Exception
     */
    @Test
    public void testPathWithDotDot() throws Exception {
        String absolutePath = Environment.currentDirectory + File.separator + "..folder";
        String expected = absolutePath;
        cdApplication.changeToDirectory(absolutePath);
        assertEquals(expected, Environment.currentDirectory);
    }

    /**
     * Change working directory to path with space
     * @throws Exception
     */
    @Test
    public void testPathWithSpace() throws Exception {
        String absolutePath = Environment.currentDirectory + File.separator + "fol der";
        String expected = absolutePath;
        cdApplication.changeToDirectory(absolutePath);
        assertEquals(expected, Environment.currentDirectory);
    }

    /**
     * Change working directory to path with long name
     * @throws Exception
     */
    @Test
    public void testPathWithLongName() throws Exception {
        String absolutePath = Environment.currentDirectory
                + File.separator + "thisisaveryveryveryveryveryveryveryveryveryveryveryveryvery" +
                "veryveryveryveryveryveryveryveryveryveryverylongfolder";
        String expected = absolutePath;
        cdApplication.changeToDirectory(absolutePath);
        assertEquals(expected, Environment.currentDirectory);
    }

    /**
     * Attempt to change working directory to path that does not exist throws exception
     */
    @Test
    public void testThrowExceptionIfPathDirectoryNotExist() {
        assertThrows(CdException.class, () -> {
            cdApplication.run(new String[]{"folder8"}, null, null);
        });
    }

    /**
     * Attempt to change working directory to path that is not directory throws exception
     */
    @Test
    public void testThrowExceptionIfPathIsNotDirectory() {
        CdException exception = assertThrows(CdException.class, () -> {
            cdApplication.run(new String[]{"folder1" + File.separator + "file.txt"}, null, null);
        });
        assertTrue(exception.getMessage().contains(ERR_IS_NOT_DIR));
    }

    /**
     * Running with null arguments throw exception
     */
    @Test
    public void testThrowExceptionIfArgNull() {
        CdException exception = assertThrows(CdException.class, () -> {
            cdApplication.run(null, null, null);
        });
        assertTrue(exception.getMessage().contains(ERR_NULL_ARGS));
    }

    /**
     * Running without arguments throws exception
     */
    @Test
    public void testThrowExceptionIfArgEmpty() {
        CdException exception = assertThrows(CdException.class, () -> {
            cdApplication.run(new String[0], null, null);
        });
        assertTrue(exception.getMessage().contains(ERR_NO_ARGS));
    }

    /**
     * Running with more than one throws exception
     */
    @Test
    public void testThrowExceptionIfArgMoreThanOne() {
        CdException exception = assertThrows(CdException.class, () -> {
            cdApplication.run(new String[2], null, null);
        });
        assertTrue(exception.getMessage().contains(ERR_ARGS_LENGTH));
    }
}
