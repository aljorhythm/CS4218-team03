package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.impl.app.SortApplication;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings({"PMD.LongVariable", "PMD.VariableNamingConventions", "PMD.MethodNamingConventions"})
class SortApplicationTest {

    /**
     * Data
     */
    static String string_empty = "";
    static String string_1 = "abcdefg";
    static String string_2 = String.join(StringUtils.STRING_NEWLINE, new String[]{"abc", "aac", "cde", "bcd"});
    static String string_2_sorted = String.join(StringUtils.STRING_NEWLINE, new String[]{"aac", "abc", "bcd", "cde"});
    static String string_2_sorted_reverse = String.join(StringUtils.STRING_NEWLINE, new String[]{"cde", "bcd", "abc", "aac"});
    static String string_3 = String.join(StringUtils.STRING_NEWLINE, new String[]{"2 abc", "10 sss", "3 cde", "10 asdas", "1 asd"});
    static String string_3_sorted_non_number = String.join(StringUtils.STRING_NEWLINE, new String[]{"1 asd", "10 asdas", "10 sss", "2 abc", "3 cde"});
    static String string_3_sorted_number = String.join(StringUtils.STRING_NEWLINE, new String[]{"1 asd", "2 abc", "3 cde", "10 asdas", "10 sss"});
    static String string_4 = String.join(StringUtils.STRING_NEWLINE, new String[]{"aAa", "aaa", "ccc", "cCc", "bbb"});
    static String string_4_sorted_depcase = String.join(StringUtils.STRING_NEWLINE, new String[]{"aAa","aaa","bbb", "cCc", "ccc"});
    static String string_4_sorted_indcase = String.join(StringUtils.STRING_NEWLINE, new String[]{"aAa", "aaa", "bbb", "cCc", "ccc"});


    /**
     * Test configurations
     */
    SortApplication application;

    @BeforeEach
    void beforeEach() {
        application = new SortApplication();
    }
    /**
     * Helper class for easier reading and writing of test cases.
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    class TestCase {
        private boolean isFirstWordNumber, isReverseOrder, isCaseIndependent;
        private String[] fileNames;
        private InputStream stdIn;
        private String expected;

        @SuppressWarnings("PMD.UncommentedEmptyConstructor")
        public TestCase() {
        }

        TestCase input(InputStream stdIn) {
            this.stdIn = stdIn;
            return this;
        }

        TestCase input(String... strings) {
            this.stdIn = IOUtils.stringsToInputStream(strings);
            return this;
        }

        TestCase inputFilenames(String... fileNames) {
            this.fileNames = fileNames;
            return this;
        }

        TestCase isFirstWordNumber(boolean arg) {
            this.isFirstWordNumber = arg;
            return this;
        }

        TestCase isReverseOrder(boolean arg) {
            this.isReverseOrder = arg;
            return this;
        }

        TestCase isCaseIndependent(boolean arg) {
            this.isCaseIndependent = arg;
            return this;
        }

        TestCase expected(String expected) {
            this.expected = expected;
            return this;
        }

        void run() throws Exception {
            if (stdIn == null) {
                assertSortFromFiles();
            } else {
                assertSortFromStdin();
            }
        }

        void assertSortFromFiles() throws Exception {
            String actual = application.sortFromFiles(isFirstWordNumber, isReverseOrder, isCaseIndependent, fileNames);
            assertEquals(expected, actual);
        }

        void assertSortFromStdin() throws Exception {
            String actual = application.sortFromStdin(isFirstWordNumber, isReverseOrder, isCaseIndependent, stdIn);
            assertEquals(expected, actual);
        }
    }

    TestCase newCase() {
        return new TestCase();
    }

    @Test
    void sortFromStdin_test0() throws Exception {
        newCase()
                .input(string_empty)
                .expected(string_empty)
                .run();
    }

    @Test
    void sortFromStdin_test1() throws Exception {
        newCase()
                .input(string_1)
                .expected(string_1)
                .run();
    }

    @Test
    void sortFromStdin_test2() throws Exception {
        newCase()
                .input(string_2)
                .isFirstWordNumber(false)
                .isReverseOrder(false)
                .expected(string_2_sorted)
                .run();
    }

    @Test
    void sortFromStdin_test3() throws Exception {
        newCase()
                .input(string_2)
                .isFirstWordNumber(false)
                .isReverseOrder(true)
                .expected(string_2_sorted_reverse)
                .run();
    }

    @Test
    void sortFromStdin_test4() throws Exception {
        newCase()
                .input(string_2)
                .isFirstWordNumber(true)
                .isReverseOrder(true)
                .expected(string_2_sorted_reverse)
                .run();
    }

    @Test
    void sortFromStdin_test5() throws Exception {
        newCase()
                .input(string_3)
                .isFirstWordNumber(false)
                .isReverseOrder(false)
                .expected(string_3_sorted_non_number)
                .run();
    }

    @Test
    void sortFromStdin_test6() throws Exception {
        newCase()
                .input(string_3)
                .isFirstWordNumber(true)
                .isReverseOrder(false)
                .expected(string_3_sorted_number)
                .run();
    }

    @Test
    void sortFromStdin_test7() throws Exception {
        newCase()
                .input(string_4)
                .isFirstWordNumber(false)
                .isReverseOrder(false)
                .isCaseIndependent(true)
                .expected(string_4_sorted_indcase)
                .run();
    }

    @Test
    void sortFromStdin_test8() throws Exception {
        newCase()
                .input(string_4)
                .isFirstWordNumber(false)
                .isReverseOrder(false)
                .isCaseIndependent(false)
                .expected(string_4_sorted_depcase)
                .run();
    }

}