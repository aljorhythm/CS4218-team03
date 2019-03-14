/**
 * From Team 09
 */
package sg.edu.nus.comp.cs4218.impl.app;

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
import static sg.edu.nus.comp.cs4218.impl.util.IOUtils.resolveFilePath;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
class CdApplicationTest {

    private CdApplication cdApplication;

    public static final String ERR_NO_ARGS = "Insufficient arguments";
    public static final String ERR_NULL_ARGS = "Null arguments";
    public static final String ERR_TOO_MANY_ARGS = "Too many arguments";
    public static final String ERR_FILE_NOT_FOUND = "No such file or directory";
    public static final String ERR_IS_NOT_DIR = "Not a directory";


    @BeforeEach
    public void setUp(@TempDir Path tempDir) throws IOException {
        cdApplication = new CdApplication();
        Environment.currentDirectory = tempDir.toString();

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

    @Test
    public void testRunOneNextPath() throws CdException {
        String expected = Environment.currentDirectory + File.separator + "folder1";
        cdApplication.run(new String[]{"folder1"}, System.in, System.out);
        assertEquals(expected, Environment.currentDirectory);
    }

    @Test
    public void testRunOnePrevPath() throws CdException {
        int index = Environment.currentDirectory.lastIndexOf(File.separator);
        String expected = Environment.currentDirectory.substring(0, index);
        cdApplication.run(new String[]{StringUtils.STRING_PARENT_DIR}, System.in, System.out);
        assertEquals(expected, Environment.currentDirectory);
    }

    @Test
    public void testRunTwoNextPath() throws CdException {
        String expected = Environment.currentDirectory + File.separator + "folder1" +
                File.separator + "folder1-1";
        cdApplication.run(new String[]{"folder1"}, System.in, System.out);
        cdApplication.run(new String[]{"folder1-1"}, System.in, System.out);
        assertEquals(expected, Environment.currentDirectory);
    }

    @Test
    public void testRunTwoPrevPath() throws CdException {
        int index = Environment.currentDirectory.lastIndexOf(File.separator);
        String expected = Environment.currentDirectory.substring(0, index) + File.separator + "folder3";
        cdApplication.run(new String[]{"folder1"}, System.in, System.out);
        cdApplication.run(new String[]{StringUtils.STRING_PARENT_DIR + File.separator +
                StringUtils.STRING_PARENT_DIR + File.separator + "folder3"}, System.in, System.out);
        assertEquals(expected, Environment.currentDirectory);
    }

    @Test
    public void testRunAbsolutePath() throws CdException {
        String expected = Environment.currentDirectory + File.separator + "folder1" +
                File.separator + "folder1-1";
        cdApplication.run(new String[]{expected}, System.in, System.out);
        assertEquals(expected, Environment.currentDirectory);
    }

    @Test
    public void testRunAbsolutePrevPath() throws CdException {
        String absolute = Environment.currentDirectory + File.separator + "folder1" +
                File.separator + "folder1-1";
        String expected = Environment.currentDirectory + File.separator + "folder1";
        cdApplication.run(new String[]{absolute}, System.in, System.out);
        cdApplication.run(new String[]{StringUtils.STRING_PARENT_DIR}, System.in, System.out);
        assertEquals(expected, Environment.currentDirectory);
    }

    @Test
    public void testRunAbsoluteNextPath() throws CdException {
        String absolute = Environment.currentDirectory + File.separator + "folder1";
        String expected = Environment.currentDirectory + File.separator + "folder1" +
                File.separator + "folder1-1";
        cdApplication.run(new String[]{absolute}, System.in, System.out);
        cdApplication.run(new String[]{"folder1-1"}, System.in, System.out);
        assertEquals(expected, Environment.currentDirectory);
    }

    @Test
    public void testRunNextPrevPath() throws CdException {
        String expected = Environment.currentDirectory + File.separator + "folder1";
        cdApplication.run(new String[]{"folder1"}, System.in, System.out);
        cdApplication.run(new String[]{"folder1-1"}, System.in, System.out);
        cdApplication.run(new String[]{StringUtils.STRING_PARENT_DIR}, System.in, System.out);
        assertEquals(expected, Environment.currentDirectory);
    }

    @Test
    public void testRunPrevNextPath() throws CdException {
        String expected = Environment.currentDirectory + File.separator + "folder2";
        cdApplication.run(new String[]{"folder1"}, System.in, System.out);
        cdApplication.run(new String[]{StringUtils.STRING_PARENT_DIR}, System.in, System.out);
        cdApplication.run(new String[]{"folder2"}, System.in, System.out);
        assertEquals(expected, Environment.currentDirectory);
    }

    @Test
    public void testChangeToAbsoluteDirectory() throws Exception {
        String absolutePath = Environment.currentDirectory + File.separator + "folder1" + File.separator +
                "folder1-1";
        String expected = absolutePath;
        cdApplication.changeToDirectory(absolutePath);
        assertEquals(expected, Environment.currentDirectory);
    }

    @Test
    public void testChangeToRelativeNextFolderDirectory() throws Exception {
        String relativePath = "folder2";
        String expected = Environment.currentDirectory + File.separator + "folder2";
        cdApplication.changeToDirectory(relativePath);
        assertEquals(expected, Environment.currentDirectory);
    }

    @Test
    public void testChangeToRelativePrevFolderDirectory() throws Exception {
        String relativePath = StringUtils.STRING_PARENT_DIR + File.separator + "folder3";
        int index = Environment.currentDirectory.lastIndexOf(File.separator);
        String expected = Environment.currentDirectory.substring(0, index) + File.separator + "folder3";
        cdApplication.changeToDirectory(relativePath);
        assertEquals(expected, Environment.currentDirectory);
    }

    @Test
    public void testPathWithNumber() throws Exception {
        String absolutePath = Environment.currentDirectory + File.separator + "f0lder";
        String expected = absolutePath;
        cdApplication.changeToDirectory(absolutePath);
        assertEquals(expected, Environment.currentDirectory);
    }

    @Test
    public void testPathWithSpecialCharacters() throws Exception {
        String absolutePath = Environment.currentDirectory + File.separator + "f@ld%r";
        String expected = absolutePath;
        cdApplication.changeToDirectory(absolutePath);
        assertEquals(expected, Environment.currentDirectory);
    }

    @Test
    public void testPathWithDotDot() throws Exception {
        String absolutePath = Environment.currentDirectory + File.separator + "..folder";
        String expected = absolutePath;
        cdApplication.changeToDirectory(absolutePath);
        assertEquals(expected, Environment.currentDirectory);
    }

    @Test
    public void testPathWithSpace() throws Exception {
        String absolutePath = Environment.currentDirectory + File.separator + "fol der";
        String expected = absolutePath;
        cdApplication.changeToDirectory(absolutePath);
        assertEquals(expected, Environment.currentDirectory);
    }

    @Test
    public void testPathWithLongName() throws Exception {
        String absolutePath = Environment.currentDirectory
                + File.separator + "thisisaveryveryveryveryveryveryveryveryveryveryveryveryvery" +
                "veryveryveryveryveryveryveryveryveryveryverylongfolder";
        String expected = absolutePath;
        cdApplication.changeToDirectory(absolutePath);
        assertEquals(expected, Environment.currentDirectory);
    }

    @Test
    public void testThrowExceptionIfPathDirectoryNotExist() {
        CdException exception = assertThrows(CdException.class, () -> {
            cdApplication.run(new String[]{"folder8"}, System.in, System.out);
        });
        assertTrue(exception.getMessage().contains(ERR_FILE_NOT_FOUND));
    }

    @Test
    public void testThrowExceptionIfPathIsNotDirectory() {
        CdException exception = assertThrows(CdException.class, () -> {
            cdApplication.run(new String[]{"folder1" + File.separator + "file.txt"}, System.in, System.out);
        });
        assertTrue(exception.getMessage().contains(ERR_IS_NOT_DIR));
    }

    @Test
    public void testThrowExceptionIfArgNull() {
        CdException exception = assertThrows(CdException.class, () -> {
            cdApplication.run(null, System.in, System.out);
        });
        assertTrue(exception.getMessage().contains(ERR_NULL_ARGS));
    }

    @Test
    public void testThrowExceptionIfArgEmpty() {
        CdException exception = assertThrows(CdException.class, () -> {
            cdApplication.run(new String[0], System.in, System.out);
        });
        assertTrue(exception.getMessage().contains(ERR_NO_ARGS));
    }

    @Test
    public void testThrowExceptionIfArgMoreThanOne() {
        CdException exception = assertThrows(CdException.class, () -> {
            cdApplication.run(new String[2], System.in, System.out);
        });
        assertTrue(exception.getMessage().contains(ERR_TOO_MANY_ARGS));
    }
}
