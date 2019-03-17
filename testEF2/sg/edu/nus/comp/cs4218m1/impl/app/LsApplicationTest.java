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
    private static String TEST_DIR = pathToTestDataSubdir("lsTestDir");
    private static String test1_and_2_output = String.join(StringUtils.STRING_NEWLINE, new String[]{TEST_DIR + File.separator + "folder_b"});
    private static String test3_output = String.join(StringUtils.STRING_NEWLINE, new String[]{
            TEST_DIR + ":", "folder_a folder_b" + StringUtils.STRING_NEWLINE,
            TEST_DIR + File.separator + "folder_a:", "file_a" + StringUtils.STRING_NEWLINE,
            TEST_DIR + File.separator + "folder_b:", "folder_c folder_d" + StringUtils.STRING_NEWLINE,
            TEST_DIR + File.separator + "folder_b" + File.separator + "folder_c:", "file_a" + StringUtils.STRING_NEWLINE,
            TEST_DIR + File.separator + "folder_b" + File.separator + "folder_d:", "file_b"});
    private static String test4_output = String.join(" ", new String[]{"folder_a", "folder_b"});
    private static String test5_output = TEST_DIR + File.separator + "folder_a" + File.separator + "file_a";
    private static String FOLDER_B = TEST_DIR + File.separator + "folder_b";
    private static String FOLDER_A = TEST_DIR + File.separator + "folder_a";
    private static String FILE_A = FOLDER_A + File.separator + "file_a";
    private static String FOLDER_C = FOLDER_B + File.separator + "folder_c";
    private static String FOLDER_E = FOLDER_C + File.separator + "folder_e";
    private static String FOLDER_F = TEST_DIR + File.separator + "folder_f";

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
    void listFolderContent_test1() throws Exception {
        newCase()
                .isFoldersOnly(true)
                .isRecursive(true)
                .folderName(FOLDER_B)
                .expected(test1_and_2_output)
                .run();
    }

    @Test
    void listFolderContent_test2() throws Exception {
        newCase()
                .isFoldersOnly(true)
                .isRecursive(false)
                .folderName(FOLDER_B)
                .expected(test1_and_2_output)
                .run();
    }

    @Test
    void listFolderContent_test3() throws Exception {
        newCase()
                .isFoldersOnly(false)
                .isRecursive(true)
                .folderName(TEST_DIR)
                .expected(test3_output)
                .run();
    }

    @Test
    void listFolderContent_test4() throws Exception {
        newCase()
                .isFoldersOnly(false)
                .isRecursive(false)
                .folderName(TEST_DIR)
                .expected(test4_output)
                .run();
    }

    /**
     * Input is a file
     */
    @Test
    void listFolderContent_test5_fileInput() throws Exception {
        newCase()
                .isFoldersOnly(false)
                .isRecursive(false)
                .folderName(FILE_A)
                .expected(test5_output)
                .run();
    }

    /**
     * Folder not found
     */
    @Test
    void listFolderContent_test6_nonExistentFolder() {
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
    void listFolderContent_test7_emptyDirectory() throws Exception {
        newCase()
                .isFoldersOnly(false)
                .isRecursive(false)
                .folderName(FOLDER_E)
                .assertException(LsException.class);
    }

}