package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.app.GrepInterface;
import sg.edu.nus.comp.cs4218.exception.GrepException;
import sg.edu.nus.comp.cs4218.impl.app.GrepApplication;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class GrepApplicationTest {
    private GrepApplication application;

    private final String[] linesList1 = {
            "aBcde on this line",
            "12345 on this line",
            "!@#$% on this line"
    };
    private final String[] linesList2 = {
            "aBcde on this line",
            "12345 on this line",
            "!@#$% on this Line"
    };
    private static final String string_aBcde = "aBcde";//NOPMD
    private static final String string_on_this_Line = "on this Line";//NOPMD

    @BeforeEach
    void initializeApp() {
        application = new GrepApplication();
    }

    /**
     * Helper class to abstract tests for GrepApplication.grepFromStdIn.
     * Will run assertThrows or assertEquals based on supplied argument in expected()
     */
    private class StdinTestCase implements Runnable {
        private String pattern;//NOPMD
        private Boolean isCaseInsensitive;//NOPMD
        private Boolean isLineCountsOnly;
        private InputStream inputStream;//NOPMD
        private String expected;//NOPMD
        private Class expectedException;

        StdinTestCase pattern(String pattern) {
            this.pattern = pattern;
            return this;
        }

        StdinTestCase isCaseInsensitive(Boolean val) {
            this.isCaseInsensitive = val;
            return this;
        }

        StdinTestCase isCountOfLinesOnly(Boolean val) {
            this.isLineCountsOnly = val;
            return this;
        }

        StdinTestCase inputStream(InputStream inputStream) {
            this.inputStream = inputStream;
            return this;
        }

        StdinTestCase inputStream(String inputString) {
            try {
                return this.inputStream(TestUtils.stringToInputStream(inputString));
            } catch (Exception e) {
                fail("Test case should not have invalid input string");
                return this;
            }
        }

        StdinTestCase inputStream(String... inputStrings) {
            return this.inputStream(TestUtils.stringsToInputStream(inputStrings));
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
            this.expectedException = expected;
            return this;
        }

        /**
         * Helper assertion method
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
                String actual = getApp().grepFromStdin(pattern, isCaseInsensitive, isLineCountsOnly, inputStream);
                assertEquals(expected, actual);
            } catch (Exception e) {
                fail(e);
            }
        }

        public GrepInterface getApp() {
            return GrepApplicationTest.this.application;//NOPMD
        }

        public void expectException() {
            Assertions.assertThrows(expectedException, () -> {
                getApp().grepFromStdin(pattern, isCaseInsensitive, isLineCountsOnly, inputStream);
            });
        }
    }

    /**
     * Null pattern is invalid
     */
    @Test
    void grepFromStdinTest3NullPatternFail() {
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
    void grepFromStdinEmptyPattern() {
        StdinTestCase[] testCases = {
                new StdinTestCase()
                        .expected("1")
                        .pattern("")
                        .inputStream("abcde")
                        .isCountOfLinesOnly(true),
                new StdinTestCase()
                        .expected("abcde")
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
    void grepFromStdinTest6CountOfLines() {

        String linesData = String.join(STRING_NEWLINE, linesList1);

        StdinTestCase[] testCases = {
                new StdinTestCase()
                        .expected("1")
                        .pattern(string_aBcde)
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
    void grepFromStdinTest7LinesContaining() {

        String linesData = String.join(STRING_NEWLINE, linesList1);

        StdinTestCase[] testCases = {
                new StdinTestCase()
                        .expected(linesList1[0])
                        .pattern(string_aBcde)
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
    void grepFromStdinNoMatches() {
        String linesData = String.join(STRING_NEWLINE, linesList1);

        StdinTestCase[] testCases = {
                new StdinTestCase()
                        .expected("0")
                        .pattern("sequence")
                        .inputStream(linesData)
                        .isCountOfLinesOnly(true),
                new StdinTestCase()
                        .expected("")
                        .pattern("sequence")
                        .inputStream(linesData)
                        .isCountOfLinesOnly(false)
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
    void grepFromStdinTest10CaseInsensitiveCount() {
        String linesData = String.join(STRING_NEWLINE, linesList1);

        StdinTestCase[] testCases = {
                new StdinTestCase()
                        .expected("1")
                        .pattern(string_aBcde)
                        .inputStream(linesData)
                        .isCaseInsensitive(true)
                        .isCountOfLinesOnly(true),
                new StdinTestCase()
                        .expected("3")
                        .pattern(string_on_this_Line)
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
    void grepFromStdinTest11CaseSensitiveCount() {
        String linesData = String.join(STRING_NEWLINE, linesList2);

        StdinTestCase[] testCases = {
                new StdinTestCase()
                        .expected("1")
                        .pattern(string_aBcde)
                        .inputStream(linesData)
                        .isCaseInsensitive(false)
                        .isCountOfLinesOnly(true),
                new StdinTestCase()
                        .expected("1")
                        .pattern(string_on_this_Line)
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
    void grepFromStdinTest11CaseInsensitiveLines() {
        String linesData = String.join(STRING_NEWLINE, linesList2);

        StdinTestCase[] testCases = {
                new StdinTestCase()
                        .expected(linesList2[0])
                        .pattern(string_aBcde)
                        .inputStream(linesData)
                        .isCaseInsensitive(true)
                        .isCountOfLinesOnly(false),
                new StdinTestCase()
                        .expected(String.join(STRING_NEWLINE, linesList2))
                        .pattern(string_on_this_Line)
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
    void grepFromStdinTest12CaseSensitiveLines() {
        String linesData = String.join(STRING_NEWLINE, linesList2);

        StdinTestCase[] testCases = {
                new StdinTestCase()
                        .expected(linesList2[0])
                        .pattern(string_aBcde)
                        .inputStream(linesData)
                        .isCountOfLinesOnly(false)
                        .isCaseInsensitive(false),
                new StdinTestCase()
                        .expected(linesList2[2])
                        .pattern(string_on_this_Line)
                        .inputStream(linesData)
                        .isCountOfLinesOnly(false)
                        .isCaseInsensitive(false),
        };

        for (StdinTestCase testCase : testCases) {
            testCase.run();
        }
    }
}