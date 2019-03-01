package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class LsApplicationTest {
    /**
     * Configuration and data
     */
    LsApplication application;
    static String test1_output = String.join(StringUtils.STRING_NEWLINE, new String[]{"folder_b/folder_c", "folder_c/folder_e", "folder_d"});
    static String test2_output = String.join(StringUtils.STRING_NEWLINE, new String[]{"folder_c", "folder_d"});
    static String test3_output = String.join(StringUtils.STRING_NEWLINE, new String[]{"folder_a", "folder_a/file_a", "folder_b/folder_c", "folder_b/folder_c/folder_e", "folder_b/folder_c/file_a", "folder_b/folder_d/", "folder_b/folder_d/file_b"});
    static String test4_output = String.join(StringUtils.STRING_NEWLINE, new String[]{"folder_a", "folder_b"});
    static String TEST_DIR = "./lsTestDir";
    static String FOLDER_B = TEST_DIR + "/folder_b";

    @BeforeEach
    void init() {
        application = new LsApplication();
    }

    class TestCase {
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
            assertEquals(actual, expected);
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
                .isRecursive(false)
                .folderName(FOLDER_B)
                .expected(test1_output)
                .run();
    }

    @Test
    void run() {
        fail("todo");
    }
}