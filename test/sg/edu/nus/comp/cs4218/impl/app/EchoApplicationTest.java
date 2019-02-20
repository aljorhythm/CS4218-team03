package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 echo
 Description
 The echo command writes its arguments separated by spaces and terminates by a newline on the standard output.
 Command format
 echo [ARG]
 ARG – list of arguments
 Examples
 # Display A B C (separated by space characters)
 $ echo A B C
 # Display “A*B*C” (separated by *)
 $ echo “A*B*C”
 **/
class EchoApplicationTest {

    EchoApplication application;

    @BeforeEach
    public void initApplication(){
        application = new EchoApplication();
    }

    /**
     * TODO description
     * @param expect
     * @param params
     */
    private void assertParamsAndExpected(String expect, String... params){
        String actual = null;
        try {
            actual = application.constructResult(params);
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(actual, expect);
    }

    /**
     * TODO description
     */
    @Test
    public void echoNothing() {
        assertParamsAndExpected("", "");
        assertParamsAndExpected("", new String[]{});
    }

    /**
     * TODO description
     */
    @Test
    public void echoStrings() {
        assertParamsAndExpected("abc", "abc");
        assertParamsAndExpected("abc cde.", new String[]{ "abc", "cde." });
    }
}