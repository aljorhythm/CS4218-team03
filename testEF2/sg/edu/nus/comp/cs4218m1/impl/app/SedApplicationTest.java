package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.impl.app.SedApplication;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SedApplicationTest {

    /**
     * Test configurations
     */
    SedApplication application;

    @BeforeEach
    void beforeEach() {
        application = new SedApplication();
    }

    /**
     * Data
     */
    static String string_empty = "";
    static String string_empty_sed = "";
    static String string1_for_first = "first * test";
    static String string1_for_first_sed = "first replacement test";
    static String replacement = "replacement";
    static String regexp1 = ".*\\*.*";
    static String string2_for_second = String.join(StringUtils.STRING_NEWLINE, new String[]{"second * test","second * test"});
    static String string2_for_second_sed = String.join(StringUtils.STRING_NEWLINE, new String[]{"second replacement test","second replacement test"});
    static String string3_for_third = "third * test *";
    static String string3_for_third_sed = "third * test replacement";

    /**
     * Helper class for easier reading and writing of test cases.
     */
    class TestCase {
        private String regexp;
        private String replacement;
        private int replacementIndex;
        private String fileName;
        private InputStream stdIn;
        private String expected;

        public TestCase() {
//            String[] strings = string.split(" ");
//            setRegexp(strings[0]);
//            setReplacement(strings[1]);
//            setReplacementIndex(Integer.parseInt(strings[2]));
        }

        TestCase input(InputStream stdIn) {
            this.stdIn = stdIn;
            return this;
        }

        TestCase input(String... strings) {
            this.stdIn = IOUtils.stringsToInputStream(strings);
            return this;
        }

        TestCase inputFilenames(String fileName) {
            this.fileName = fileName;
            return this;
        }

        TestCase setRegexp(String regexp) {
            this.regexp = regexp;
            return this;
        }

        TestCase setReplacement(String replacement){
            this.replacement = replacement;
            return this;
        }

        TestCase setReplacementIndex(int replacementIndex){
            this.replacementIndex = replacementIndex;
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
            String actual = application.replaceSubstringInFile(regexp,replacement,replacementIndex,fileName);
            assertEquals(actual, expected);
        }

        void assertSortFromStdin() throws Exception {
            String actual = application.replaceSubstringInStdin(regexp,replacement,replacementIndex,stdIn);
            assertEquals(actual, expected);
        }
    }

    TestCase newCase() {
        return new TestCase();
    }

    /**
     * Tests
     */

//    @Test
//    void sortFromFiles() {
//        Assertions.fail("todo");
//    }

    @Test
    void sedFromStdin_test0() throws Exception {
        newCase()
                .input(string_empty)
                .expected(string_empty_sed)
                .run();
    }

    @Test
    void sedFromStdin_test1() throws Exception {
        newCase()
                .input(string1_for_first)
                .setRegexp(regexp1)
                .setReplacement(replacement)
                .setReplacementIndex(0)
                .expected(string1_for_first_sed)
                .run();
    }

    @Test
    void sedFromStdin_test2() throws Exception {
        newCase()
                .input(string2_for_second)
                .setRegexp(regexp1)
                .setReplacement(replacement)
                .setReplacementIndex(0)
                .expected(string2_for_second_sed)
                .run();
    }

    @Test
    void sedFromStdin_test3() throws Exception{
        newCase()
                .input(string3_for_third)
                .setRegexp(regexp1)
                .setReplacement(replacement)
                .setReplacementIndex(1)
                .expected(string3_for_third_sed)
                .run();
    }




}