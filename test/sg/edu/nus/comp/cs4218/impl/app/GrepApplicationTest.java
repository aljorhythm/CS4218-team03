package sg.edu.nus.comp.cs4218.impl.app;

import jdk.internal.util.xml.impl.Input;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.GrepException;
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
     * Helper class to abstract tests for GrepApplication.grepFromStdIn.
     * Will run assertThrows or assertEquals based on supplied argument in expected()
     */
    private class StdinTestCase implements Runnable {
        private String pattern;
        private Boolean isCaseInsensitive;
        private Boolean isCountOfLinesOnly;
        private InputStream inputStream;
        private String expected;
        private Class expectedExceptionClass;

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

        /**
         * Specifies expected string result
         *
         * @param expected
         * @return
         */
        StdinTestCase expected(String expected) {
            this.expected = expected;
            return this;
        }

        /**
         * Specifies expected Throwable
         *
         * @param expected
         * @return
         */
        StdinTestCase expected(Class expected) {
            this.expectedExceptionClass = expected;
            return this;
        }

        /**
         * Helper assertion method
         *
         * @param expected
         */
        public void run() {
            if (this.expected == null) {
                expectException();
            } else {
                assertExpected();
            }
        }

        public void assertExpected() {
            try {
                String actual = application.grepFromStdin(pattern, isCaseInsensitive, isCountOfLinesOnly, inputStream);
                assertEquals(actual, expected);
            } catch (Exception e) {
                fail(e);
            }
        }

        public void expectException() {
            Assertions.assertThrows(expectedExceptionClass, () -> {
                application.grepFromStdin(pattern, isCaseInsensitive, isCountOfLinesOnly, inputStream);
            });
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
    void grepFromStdin_test1_stdin() {
        fail("todo");
    }

    /**
     * Invalid File
     */
    @Test
    void grepFromFile_test2_invalidFile_fail() {
        fail("todo");
    }

    /**
     * Null pattern is invalid
     */
    @Test
    void grepFromStdin_test3_nullPattern_fail() {
        StdinTestCase[] testCases = {
                new StdinTestCase().expected(GrepException.class).pattern(null),
        };
        for (StdinTestCase testCase : testCases) {
            testCase.run();
        }
    }

    /**
     * Test4 and Test5
     * Empty patterns: ""
     */
    @Test
    void grepFromStdin_emptyPattern() {
        StdinTestCase[] testCases = {
                new StdinTestCase()
                        .expected("0")
                        .pattern("")
                        .inputStream("abcde")
                        .isCountOfLinesOnly(true),
                new StdinTestCase()
                        .expected("")
                        .pattern("")
                        .inputStream("abcde")
                        .isCountOfLinesOnly(false)
        };
        for (StdinTestCase testCase : testCases) {
            testCase.run();
        }
    }

    /**
     * Tests if number of output lines matching pattern are correct
     */
    @Test
    void grepFromStdin_test6_countOfLines() {

        String[] linesList = {
                "aBcde on this line",
                "12345 on this line",
                "!@#$% on this line"
        };

        String linesData = String.join("\n", linesList);

        StdinTestCase[] testCases = {
                new StdinTestCase()
                        .expected("1")
                        .pattern("aBcde")
                        .inputStream(linesData)
                        .isCountOfLinesOnly(true),
                new StdinTestCase()
                        .expected("3")
                        .pattern("on this line")
                        .inputStream(linesData)
                        .isCountOfLinesOnly(true),
        };
        for (StdinTestCase testCase : testCases) {
            testCase.run();
        }
    }

    /**
     * Tests if output lines matching pattern are correct
     */
    @Test
    void grepFromStdin_test7_linesContaining() {

        String[] linesList = {
                "aBcde on this line",
                "12345 on this line",
                "!@#$% on this line"
        };

        String linesData = String.join("\n", linesList);

        StdinTestCase[] testCases = {
                new StdinTestCase()
                        .expected(linesList[0])
                        .pattern("aBcde")
                        .inputStream(linesData)
                        .isCountOfLinesOnly(false),
                new StdinTestCase()
                        .expected(linesData)
                        .pattern("on this line")
                        .inputStream(linesData)
                        .isCountOfLinesOnly(false)
        };

        for (StdinTestCase testCase : testCases) {
            testCase.run();
        }
    }

    /**
     * Test8 and Test9
     * Tests if output lines matching pattern are correct
     */
    @Test
    void grepFromStdin_noMatches() {

        String[] linesList = {
                "aBcde on this line",
                "12345 on this line",
                "!@#$% on this line"
        };

        String linesData = String.join("\n", linesList);

        StdinTestCase[] testCases = {
                new StdinTestCase()
                        .expected("0")
                        .pattern("sequence")
                        .inputStream(linesData)
                        .isCountOfLinesOnly(false),
                new StdinTestCase()
                        .expected("")
                        .pattern("sequence")
                        .inputStream(linesData)
                        .isCountOfLinesOnly(true)
        };

        for (StdinTestCase testCase : testCases) {
            testCase.run();
        }
    }

    /**
     * Test10
     * Tests if output lines matching pattern are correct
     */
    @Test
    void grepFromStdin_test10_caseInsensi_count() {

        String[] linesList = {
                "abcde on this line",
                "12345 on this Line",
                "!@#$% on this line"
        };

        String linesData = String.join("\n", linesList);

        StdinTestCase[] testCases = {
                new StdinTestCase()
                        .expected("1")
                        .pattern("aBcde")
                        .inputStream(linesData)
                        .isCaseInsensitive(true)
                        .isCountOfLinesOnly(true),
                new StdinTestCase()
                        .expected("3")
                        .pattern("on this Line")
                        .inputStream(linesData)
                        .isCaseInsensitive(true)
                        .isCountOfLinesOnly(true)
        };

        for (StdinTestCase testCase : testCases) {
            testCase.run();
        }
    }

    /**
     * Test11
     * Tests if output lines matching pattern are correct
     */
    @Test
    void grepFromStdin_test11_caseSensi_count() {

        String[] linesList = {
                "aBcde on this line",
                "12345 on this line",
                "!@#$% on this Line"
        };

        String linesData = String.join("\n", linesList);

        StdinTestCase[] testCases = {
                new StdinTestCase()
                        .expected("1")
                        .pattern("aBcde")
                        .inputStream(linesData)
                        .isCaseInsensitive(false)
                        .isCountOfLinesOnly(true),
                new StdinTestCase()
                        .expected("1")
                        .pattern("on this Line")
                        .inputStream(linesData)
                        .isCaseInsensitive(false)
                        .isCountOfLinesOnly(true)
        };

        for (StdinTestCase testCase : testCases) {
            testCase.run();
        }
    }

    /**
     * Test12
     * Tests if output lines matching pattern are correct
     */
    @Test
    void grepFromStdin_test11_caseInsensi_lines() {

        String[] linesList = {
                "abcde on this line",
                "12345 on this line",
                "!@#$% on this Line"
        };

        String linesData = String.join("\n", linesList);

        StdinTestCase[] testCases = {
                new StdinTestCase()
                        .expected(linesList[0])
                        .pattern("aBcde")
                        .inputStream(linesData)
                        .isCaseInsensitive(true)
                        .isCountOfLinesOnly(false),
                new StdinTestCase()
                        .expected(String.join("\n", linesList))
                        .pattern("on this Line")
                        .inputStream(linesData)
                        .isCaseInsensitive(true)
                        .isCountOfLinesOnly(false)
        };

        for (StdinTestCase testCase : testCases) {
            testCase.run();
        }
    }

    /**
     * Test12
     * Tests if output lines matching pattern are correct
     */
    @Test
    void grepFromStdin_test12_caseSensi_lines() {

        String[] linesList = {
                "aBcde on this line",
                "12345 on this line",
                "!@#$% on this Line"
        };

        String linesData = String.join("\n", linesList);

        StdinTestCase[] testCases = {
                new StdinTestCase()
                        .expected(linesList[0])
                        .pattern("aBcde")
                        .inputStream(linesData)
                        .isCountOfLinesOnly(false)
                        .isCaseInsensitive(false),
                new StdinTestCase()
                        .expected(linesList[2])
                        .pattern("on this Line")
                        .inputStream(linesData)
                        .isCountOfLinesOnly(false)
                        .isCaseInsensitive(false),
        };

        for (StdinTestCase testCase : testCases) {
            testCase.run();
        }
    }
}