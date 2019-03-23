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
    static String stringEmpty = "";
    static String stringEmptySed = "";
    static String string1ForFirst = "first * test";
    static String string1ForFirstSed = "first replacement test";
    static String replacement = "replacement";
    // You need to add [] around * to replace * itself.
    static String regexp1 = "[*]";
    static String string2ForSecond = String.join(StringUtils.STRING_NEWLINE, new String[]{"second * test","second * test"});
    static String string2ForSecondSed = String.join(StringUtils.STRING_NEWLINE, new String[]{"second replacement test","second replacement test"});
    static String string3ForThird = "third * test *";
    static String string3ForThirdSed = "third * test replacement";

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
                .input(string1ForFirst)
                .setRegexp(regexp1)
                .setReplacement(replacement)
                .setReplacementIndex(1)
                .expected(string1ForFirstSed)
                .run();
    }

    @Test
    void sedFromStdinTest2() throws Exception {
        newCase()
                .input(string2ForSecond)
                .setRegexp(regexp1)
                .setReplacement(replacement)
                .setReplacementIndex(1)
                .expected(string2ForSecondSed)
                .run();
    }

    @Test
    void sedFromStdinTest3() throws Exception{
        newCase()
                .input(string3ForThird)
                .setRegexp(regexp1)
                .setReplacement(replacement)
                .setReplacementIndex(2)
                .expected(string3ForThirdSed)
                .run();
    }




}