package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.CommandBuilder;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;
import static sg.edu.nus.comp.cs4218m1.TestUtils.CHARSET_UTF8;
import static sg.edu.nus.comp.cs4218m1.TestUtils.stringToInputStream;

class SubstitutionTest {
    private final ApplicationRunner mockAppRunner = new MockAppRunner();

    /**
     * Mock application runner that does straightforward stuff without any commands
     */
    static class MockAppRunner extends ApplicationRunner {

        /**
         * Deletes first argument if not empty
         * @param argsArray
         * @return empty array if argsArray is null
         */
        public static String[] deleteDefaultArg(String... argsArray){
            if(argsArray == null) {
                return new String[]{};
            }
            String[] res = new String[argsArray.length-1];
            for(int i = 0;i<res.length;i++){
                res[i] = argsArray[i+1];
            }
            return res;
        }

        @Override
        public void runApp(String app, String[] argsArray, InputStream inputStream, OutputStream outputStream) throws ShellException {
            String output = "";
            String[] filteredArgsArr = deleteDefaultArg(argsArray);
            if ("expandABC".equals(app)) {
                output = "A B C";
            } else if ("repeat".equals(app) && filteredArgsArr != null) {//NOPMD
                String[] outputArr = new String[filteredArgsArr.length * 2];
                for(int i = 0; i < filteredArgsArr.length; i++) {
                    String str = filteredArgsArr[i];
                    outputArr[i * 2] = str;
                    outputArr[i * 2 +  1] = str;
                }
                output = String.join(" ", outputArr);
            }

            try {
                outputStream.write(output.getBytes(CHARSET_UTF8));
            } catch (IOException e) {
                fail("Should not fail writing to output stream for tests");
            }
        }
    }

    /**
     * Helper method to assert app
     */
    void assertMockRunner(String app, String[] args, String expected) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream input = null;
        try {
            input = stringToInputStream(app);
        } catch (IOException e) {
            fail("Test method should not fail");
        }
        try {
            mockAppRunner.runApp(app, args, input, outputStream);
        } catch (ShellException e) {
            fail("Test method should not fail");
        }
        assertEquals(expected, outputStream.toString());
    }

    /**
     * Tests mock app working as expected
     * @throws IOException
     * @throws ShellException
     * @throws AbstractApplicationException
     */
    @Test
    void runMockApp() throws IOException, ShellException, AbstractApplicationException {
        assertMockRunner("expandABC", null, "A B C");
        assertMockRunner("repeat", null,"");
        assertMockRunner("repeat", new String[]{"repeat", "abc"}, "abc abc");
        assertMockRunner("repeat", new String[]{"repeat", "abc", "123"}, "abc abc 123 123");
    }

    /**
     * Substitute simple command
     * @throws ShellException
     * @throws AbstractApplicationException
     */
    @Test
    void substitute() throws ShellException, AbstractApplicationException, UnsupportedEncodingException {
        CallCommand command = (CallCommand) CommandBuilder.parseCommand("repeat `expandABC`", mockAppRunner);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        command.evaluate(null, outputStream);
        String actual = outputStream.toString(CHARSET_UTF8);
        String expected = "A A B B C C" + STRING_NEWLINE;
        assertEquals(expected, actual);
    }

}