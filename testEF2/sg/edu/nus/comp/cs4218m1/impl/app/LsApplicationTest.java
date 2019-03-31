package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.LsException;
import sg.edu.nus.comp.cs4218.impl.app.LsApplication;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218m1.TestUtils.pathToTestDataSubdir;

@SuppressWarnings({"PMD.LongVariable", "PMD.VariableNamingConventions", "PMD.AvoidDuplicateLiterals"})
class LsApplicationTest {
    /**
     * Configuration and data
     */
    LsApplication application;
    private static final String testDir = pathToTestDataSubdir("lsTestDir");
    private static final String test1And2Output = String.join(StringUtils.STRING_NEWLINE, new String[]{testDir + File.separator + "folder_b"});
    private static final String test3Output = String.join(StringUtils.STRING_NEWLINE, new String[]{
            testDir + ":", "folder_a folder_b" + StringUtils.STRING_NEWLINE,
            testDir + File.separator + "folder_a:", "file_a" + StringUtils.STRING_NEWLINE,
            testDir + File.separator + "folder_b:", "folder_c folder_d" + StringUtils.STRING_NEWLINE,
            testDir + File.separator + "folder_b" + File.separator + "folder_c:", "file_a" + StringUtils.STRING_NEWLINE,
            testDir + File.separator + "folder_b" + File.separator + "folder_d:", "file_b"});
    private static final String test4Output = String.join(" ", new String[]{"folder_a", "folder_b"});
    private static final String test5Output = testDir + File.separator + "folder_a" + File.separator + "file_a";
    private static final String FOLDER_B = testDir + File.separator + "folder_b";
    private static final String FOLDER_A = testDir + File.separator + "folder_a";
    private static final String FILE_A = FOLDER_A + File.separator + "file_a";
    private static final String FOLDER_C = FOLDER_B + File.separator + "folder_c";
    private static final String FOLDER_E = FOLDER_C + File.separator + "folder_e";
    private static final String FOLDER_F = testDir + File.separator + "folder_f";

    @BeforeEach
    void init() {
        application = new LsApplication();
    }

    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private class TestCase {
        boolean isFoldersOnly, isRecursive;
        String folder, expected;

        TestCase isFoldersOnly(boolean arg) {
            isFoldersOnly = arg;
            return this;
        }

        TestCase isRecursive(boolean arg) {
            this.isRecursive = arg;
            return this;
        }

        TestCase folderName(String folder) {
            this.folder = folder;
            return this;
        }

        TestCase expected(String expected) {
            this.expected = expected;
            return this;
        }

        void run() throws Exception {
            String actual = application.listFolderContent(isFoldersOnly, isRecursive, folder);
            assertEquals(expected, actual);
        }

        void assertException(Class exceptionClass) {
            assertThrows(exceptionClass, () -> {
                assertException();
            });
        }

        private void assertException() throws Exception {
            String actual = application.listFolderContent(isFoldersOnly, isRecursive, folder);
            assertEquals(expected, actual);
        }
    }

    TestCase newCase() {
        return new TestCase();
    }

    /**
     * Tests
     */

    @Test
    void listFolderContentTest1() throws Exception {
        newCase()
                .isFoldersOnly(true)
                .isRecursive(true)
                .folderName(FOLDER_B)
                .expected(test1And2Output)
                .run();
    }

    @Test
    void listFolderContentTest2() throws Exception {
        newCase()
                .isFoldersOnly(true)
                .isRecursive(false)
                .folderName(FOLDER_B)
                .expected(test1And2Output)
                .run();
    }

    @Test
    void listFolderContentTest3() throws Exception {
        newCase()
                .isFoldersOnly(false)
                .isRecursive(true)
                .folderName(testDir)
                .expected(test3Output)
                .run();
    }

    @Test
    void listFolderContentTest4() throws Exception {
        newCase()
                .isFoldersOnly(false)
                .isRecursive(false)
                .folderName(testDir)
                .expected(test4Output)
                .run();
    }

    /**
     * Input is a file
     */
    @Test
    void listFolderContentTest5FileInput() throws Exception {
        newCase()
                .isFoldersOnly(false)
                .isRecursive(false)
                .folderName(FILE_A)
                .expected(test5Output)
                .run();
    }

    /**
     * Folder not found
     */
    @Test
    void listFolderContentTest6NonExistentFolder() {
        newCase()
                .isFoldersOnly(false)
                .isRecursive(false)
                .folderName(FOLDER_F)
                .assertException(LsException.class);
    }

    /**
     * Folder not found
     */
    @Test
    void listFolderContentTest7EmptyDirectory() throws Exception {
        newCase()
                .isFoldersOnly(false)
                .isRecursive(false)
                .folderName(FOLDER_E)
                .assertException(LsException.class);
    }

    /**
     * If no directories are specified list current directory
     * @param tempPath
     */
    @Test
    void listCurrentDirectory(@TempDir Path tempPath) throws LsException, IOException {
        String oriWorkingDir = Environment.currentDirectory;
        Environment.currentDirectory = tempPath.toAbsolutePath().toString();
        LsApplication app = new LsApplication();
        String actual = app.listFolderContent(false, false);
        String expected = "";
        assertEquals(expected, actual);
        Path newFile = tempPath.resolve("newFile");
        newFile.toFile().createNewFile();
        actual = app.listFolderContent(false, false);
        expected = String.join(StringUtils.STRING_NEWLINE, new String[]{
                tempPath.relativize(newFile).toString()
        });
        assertEquals(expected, actual);
        Path newDir1 = tempPath.resolve("1");
        newDir1.toFile().mkdir();
        Path newFile1 = newDir1.resolve("2");
        newFile1.toFile().createNewFile();
        actual = app.listFolderContent(false, false, "1");
        expected = String.join(StringUtils.STRING_NEWLINE, new String[]{
                tempPath.relativize(newFile1).toString()
        });
        Environment.currentDirectory = oriWorkingDir;
    }
}