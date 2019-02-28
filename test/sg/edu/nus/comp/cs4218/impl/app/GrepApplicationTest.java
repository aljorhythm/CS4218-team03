package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.InputStream;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class GrepApplicationTest {
    GrepApplication application;

    @BeforeEach
    void initializeApp() {
        application = new GrepApplication();
    }

    /**
     * Helper assertion method
     *
     * @param expected
     * @param pattern
     */
    void assertGrepFromFile(String expected, String pattern) {
        fail("TODO");
    }


    /**
     * Helper class to abstract tests for GrepApplication.grepFromStdIn
     */
    private class StdinTestCase implements Runnable {
        private String pattern;
        private Boolean isCaseInsensitive;
        private Boolean isCountOfLinesOnly;
        private InputStream inputStream;
        private String expected;

        public StdinTestCase() {
        }

        StdinTestCase pattern(String pattern) {
            this.pattern = pattern;
            return this;
        }

        StdinTestCase isCaseInsensitive(Boolean val) {
            this.isCaseInsensitive = val;
            return this;
        }

        StdinTestCase isCountOfLinesOnly(Boolean val) {
            this.isCountOfLinesOnly = val;
            return this;
        }

        StdinTestCase inputStream(InputStream inputStream) {
            this.inputStream = inputStream;
            return this;
        }

        StdinTestCase inputStream(String inputString) {
            return this.inputStream(IOUtils.stringToInputStream(inputString));
        }

        StdinTestCase inputStream(String... inputStrings) {
            return this.inputStream(IOUtils.stringsToInputStream(inputStrings));
        }

        StdinTestCase expected(String expected) {
            this.expected = expected;
            return this;
        }

        /**
         * Helper assertion method
         *
         * @param expected
         */
        public void run() {
            try {
                String actual = application.grepFromStdin(pattern, isCaseInsensitive, isCountOfLinesOnly, inputStream);
                assertEquals(actual, expected);
            } catch (Exception e) {
                fail(e);
            }
        }
    }

    /**
     *
     */
    @Test
    void grepFromFiles() {
        fail("TODO");
    }

    /**
     * todo description
     */
    @Test
    void grepFromStdin_oneLineInput() {
        StdinTestCase[] testCases = {
                new StdinTestCase().expected("abcde").pattern("abc").inputStream("abcde").isCaseInsensitive(false).isCountOfLinesOnly(false),
                new StdinTestCase().expected("1").pattern("abc").inputStream("abcde").isCaseInsensitive(false).isCountOfLinesOnly(true),
                new StdinTestCase().expected("abcde").pattern("aBc").inputStream("abcde").isCaseInsensitive(false).isCountOfLinesOnly(false),
                new StdinTestCase().expected("0").pattern("aBc").inputStream("abcde").isCaseInsensitive(true).isCountOfLinesOnly(false),
        };

        for (StdinTestCase testCase : testCases) {
            testCase.run();
        }
    }

    /**
     * todo description
     */
    @Test
    void grepFromStdin_multipleLinesInput() {
        String[] linesList = {
                "abcde on this line",
                "12345 on this line",
                "!@#$% on this line"
        };
        String lines = String.join("\n", linesList);
        Fail("todo MC/DC");
        StdinTestCase[] testCases = {
                new StdinTestCase().expected("12345").pattern("123").inputStream(linesList).isCaseInsensitive(false).isCountOfLinesOnly(false),
                new StdinTestCase().expected("1").pattern("abc").inputStream(linesList).isCaseInsensitive(false).isCountOfLinesOnly(true),
                new StdinTestCase().expected("abcde").pattern("aBc").inputStream(linesList).isCaseInsensitive(false).isCountOfLinesOnly(false),
                new StdinTestCase().expected("0").pattern("aBc").inputStream(linesList).isCaseInsensitive(false).isCountOfLinesOnly(false),
        };

        for (StdinTestCase testCase : testCases) {
            testCase.run();
        }
    }
}