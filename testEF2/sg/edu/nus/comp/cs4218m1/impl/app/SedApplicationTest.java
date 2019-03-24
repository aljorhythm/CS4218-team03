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
     * Data
     */
    static String stringEmpty = "";
    static String stringEmptySed = "";
    static String str1ForFirst = "first * test";
    static String str1ForFirstSed = "first replacement test";
    static String replacement = "replacement";
    // You need to add [] around * to replace * itself.
    static String regexp1 = "[*]";
    static String string2ForSecond = String.join(StringUtils.STRING_NEWLINE, new String[]{"second * test","second * test"});
    static String str2ForSecondSed = String.join(StringUtils.STRING_NEWLINE, new String[]{"second replacement test","second replacement test"});
    static String str3ForThird = "third * test *";
    static String str3ForThirdSed = "third * test replacement";

    /**
     * Test configurations
     */
    SedApplication application;

    @BeforeEach
    void beforeEach() {
        application = new SedApplication();
    }

    /**
     * Helper class for easier reading and writing of test cases.
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    class TestCase {
        private String regexp;
        private String replacement;
        private int replacementIndex;
        private String fileName;
        private InputStream stdIn;
        private String expected;

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
    void sedFromStdinTest0() throws Exception {
        newCase()
                .input(stringEmpty)
                .setRegexp(regexp1)
                .setReplacement(replacement)
                .setReplacementIndex(1)
                .expected(stringEmptySed)
                .run();
    }

    @Test
    void sedFromStdinTest1() throws Exception {
        newCase()
                .input(str1ForFirst)
                .setRegexp(regexp1)
                .setReplacement(replacement)
                .setReplacementIndex(1)
                .expected(str1ForFirstSed)
                .run();
    }

    @Test
    void sedFromStdinTest2() throws Exception {
        newCase()
                .input(string2ForSecond)
                .setRegexp(regexp1)
                .setReplacement(replacement)
                .setReplacementIndex(1)
                .expected(str2ForSecondSed)
                .run();
    }

    @Test
    void sedFromStdinTest3() throws Exception{
        newCase()
                .input(str3ForThird)
                .setRegexp(regexp1)
                .setReplacement(replacement)
                .setReplacementIndex(2)
                .expected(str3ForThirdSed)
                .run();
    }




}