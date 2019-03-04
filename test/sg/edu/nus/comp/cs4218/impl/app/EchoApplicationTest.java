package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHARSET_UTF8;

/**
 * echo
 * Description
 * The echo command writes its arguments separated by spaces and terminates by a newline on the standard output.
 * Command format
 * echo [ARG]
 * ARG – list of arguments
 * Examples
 * # Display A B C (separated by space characters)
 * $ echo A B C
 * # Display “A*B*C” (separated by *)
 * $ echo “A*B*C”
 **/
class EchoApplicationTest {

    EchoApplication application;

    @BeforeEach
    public void initApplication() {
        application = new EchoApplication();
    }

    /**
     * Asserts echo with input parameters produces expected output
     *
     * @param expect expected result
     * @param params input strings
     */
    private void assertParamsAndExpected(String expect, String... params) {
        String actual = null;
        try {
            actual = application.constructResult(params);
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(actual, expect);
    }

    /**
     * Echo nothing
     */
    @Test
    public void echoNothing() {
        assertParamsAndExpected("", "");
        assertParamsAndExpected("", new String[]{});
    }

    /**
     * Echo strings
     */
    @Test
    public void echoStrings() {
        assertParamsAndExpected("abc", "abc");
        assertParamsAndExpected("abc cde.", new String[]{"abc", "cde."});
    }

    /**
     * Tests with no args and input and output streams
     *
     * @throws Exception
     */
    @Test
    public void run_noArgs_noInputOutput() {
        assertThrows(Exception.class, () -> {
            application.run(null, null, null);
        });
    }

    /**
     * Test running of echo
     * @throws Exception
     */
    @Test
    public void run() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String[] stringList = new String[]{"abc", "cde", "fgh"};
        application.run(stringList, null, outputStream);
        String actual = outputStream.toString(CHARSET_UTF8);
        String expected = "abc cde fgh";
        assertEquals(actual, expected);
    }
}