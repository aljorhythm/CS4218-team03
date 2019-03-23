package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.impl.app.LsApplication;
import sg.edu.nus.comp.cs4218.exception.LsException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218m1.TestUtils.pathToTestDataSubdir;

class LsApplicationTest {
    /**
     * Configuration and data
     */
    LsApplication application;
    private static String testDir = pathToTestDataSubdir("lsTestDir");
    private static String test1And2Output = String.join(StringUtils.STRING_NEWLINE, new String[]{testDir + File.separator + "folder_b"});
    private static String test3Output = String.join(StringUtils.STRING_NEWLINE, new String[]{
            testDir + ":", "folder_a folder_b" + StringUtils.STRING_NEWLINE,
            testDir + File.separator + "folder_a:", "file_a" + StringUtils.STRING_NEWLINE,
            testDir + File.separator + "folder_b:", "folder_c folder_d" + StringUtils.STRING_NEWLINE,
            testDir + File.separator + "folder_b" + File.separator + "folder_c:", "file_a" + StringUtils.STRING_NEWLINE,
            testDir + File.separator + "folder_b" + File.separator + "folder_d:", "file_b"});
    private static String test4Output = String.join(" ", new String[]{"folder_a", "folder_b"});
    private static String test5Output = testDir + File.separator + "folder_a" + File.separator + "file_a";
    private static String FOLDER_B = testDir + File.separator + "folder_b";
    private static String FOLDER_A = testDir + File.separator + "folder_a";
    private static String FILE_A = FOLDER_A + File.separator + "file_a";
    private static String FOLDER_C = FOLDER_B + File.separator + "folder_c";
    private static String FOLDER_E = FOLDER_C + File.separator + "folder_e";
    private static String FOLDER_F = testDir + File.separator + "folder_f";

    @BeforeEach
    void init() {
        application = new LsApplication();
    }

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

}