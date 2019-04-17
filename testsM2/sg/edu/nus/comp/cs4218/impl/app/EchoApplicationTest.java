/**
 * From Team 02 and 10
 */
package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.EchoException;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("PMD.LongVariable")
class EchoApplicationTest {

    private static EchoApplication application = new EchoApplication();
    private static FileOutputStream fileOutputStream;
    private static File file;

    private static final String FILE_NAME = "EchoUnitTest.txt";
    private static final String MIX_SYMBOL = "!@#$%^&*()";

    private static final String CHAR_A = "A";
    private static final String CHAR_B = "B";
    private static final String SMALL_CHAR_B = "b";
    private static final String CHAR_C = "C";

    private static final String NUM_1 = "1";
    private static final String NUM_2 = "2";
    private static final String NUM_3 = "3";

    private static final String ONE_WORD = "ABC";
    private static final String ECHO_WORD = "echo";
    private static final String ASTERISK_WORD = "A*B*C";

    private static final String WORD_SPACE = CHAR_A + " " + CHAR_B + " " + CHAR_C;
    private static final String SMALL_B_SPACE = CHAR_A + " " + SMALL_CHAR_B + " " + CHAR_C;
    private static final String WORD_SAME_A = CHAR_A + " " + CHAR_A + " " + CHAR_B;

    private static final String WORD_NUM = NUM_1 + " " + NUM_2 + " " + NUM_3;

    private InputStream stdin;
    private OutputStream stdout;
    private static String[] argListOneArgument;
    private static String[] argListMultiArgument;
    private static String[] argListMultiArgumentSpecialChar;
    private static String[] argListWithEchoOptions;
    private static String[] argListWithOptionsHelp;
    private static final String MULTI_CHAR_STRING = "ABC";
    private static final String SINGLE_CHAR_STRING_A = "a";
    private static final String SINGLE_CHAR_STRING_B = "B";
    private static final String SINGLE_CHAR_STRING_C = "C";
    private static final String SINGLE_CHAR_STRING_A_B_C = "a B C";
    private static final String MULTI_CHAR_ASTERISK_STRING = "A*b*C";
    private static final String ECHO_OPTION_MESSAGES = "-n ABC";
    private static final String ECHO_OPTION_HELP = "--help";

    public static final String ERR_OPTION_NOT_SUPPORT = "Echo option is currently not supported";
    public static final String ERR_EMPTY_ARG = "Empty arguments";

    @BeforeEach
    void initOutputStream() {
        stdout = new ByteArrayOutputStream();
    }

    @Test
    void testConstructResultEmpty() throws EchoException {
        String[] input = {};
        String expectedOutput = "";
        assertEquals(expectedOutput, application.constructResult(input));
    }

    @Test
    void testConstructResultSingleCharacter() throws EchoException {
        String expectedOutput = CHAR_A;
        String[] input = {CHAR_A};
        assertEquals(expectedOutput, application.constructResult(input));
    }

    @Test
    void testConstructResultSingleWord() throws EchoException {
        String expectedOutput = ONE_WORD;
        String[] input = {ONE_WORD};
        assertEquals(expectedOutput, application.constructResult(input));
    }

    @Test
    void testCtestonstructResultSeparateBySpace() throws EchoException {
        String expectedOutput = WORD_SPACE;
        String[] input = {CHAR_A, CHAR_B, CHAR_C};
        assertEquals(expectedOutput, application.constructResult(input));
    }

    @Test
    void testConstructResultSeparateBySymbol() throws EchoException {
        String expectedOutput = ASTERISK_WORD;
        String[] input = {ASTERISK_WORD};
        assertEquals(expectedOutput, application.constructResult(input));
    }

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void testConstructResultNull() throws EchoException {
        String expectedOutput = "";
        String[] input = null;
        assertEquals(expectedOutput, application.constructResult(input));
    }
     */

    @Test
    void testConstructResultCaseSensitivity() throws EchoException {
        String expectedOutput = SMALL_B_SPACE;
        String[] input = {CHAR_A, SMALL_CHAR_B, CHAR_C};
        assertEquals(expectedOutput, application.constructResult(input));
    }

    @Test
    void testConstructResultDigits() throws EchoException {
        String expectedOutput = WORD_NUM;
        String[] input = {NUM_1, NUM_2, NUM_3};
        assertEquals(expectedOutput, application.constructResult(input));
    }

    @Test
    void testConstructResultDuplicateCharacter() throws EchoException {
        String expectedOutput = WORD_SAME_A;
        String[] input = {CHAR_A, CHAR_A, CHAR_B};
        assertEquals(expectedOutput, application.constructResult(input));
    }

    @Test
    void testConstructResultEchoWord() throws EchoException {
        String expectedOutput = ECHO_WORD;
        String[] input = {ECHO_WORD};
        assertEquals(expectedOutput, application.constructResult(input));
    }

    @Test
    void testConstructResultSingleQuote() throws EchoException {
        String expectedOutput = "'";
        String[] input = {"'"};
        assertEquals(expectedOutput, application.constructResult(input));
    }

    @Test
    void testConstructResultTwoSingleQuote() throws EchoException {
        String expectedOutput = "''";
        String[] input = {"''"};
        assertEquals(expectedOutput, application.constructResult(input));
    }

    @Test
    void testConstructResultDoubleQuote() throws EchoException {
        String expectedOutput = "\"";
        String[] input = {"\""};
        assertEquals(expectedOutput, application.constructResult(input));
    }

    @Test
    void testConstructResultTwoDoubleQuote() throws EchoException {
        String expectedOutput = "\"\"";
        String[] input = {"\"\""};
        assertEquals(expectedOutput, application.constructResult(input));
    }

    @Test
    void testConstructResultSpecialSymbol() throws EchoException {
        String expectedOutput = MIX_SYMBOL;
        String[] input = {MIX_SYMBOL};
        assertEquals(expectedOutput, application.constructResult(input));
    }

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void testRunValidSingleArg() throws Exception {
        application.run(argListOneArgument, stdin, stdout);
        assertEquals(MULTI_CHAR_STRING, stdout.toString());
    }
     */

    @Test
    void testRunValidMultiArgs() throws Exception {
        application.run(argListMultiArgument, stdin, stdout);
        assertEquals(SINGLE_CHAR_STRING_A_B_C, stdout.toString());
    }

    @Test
    void testRunValidMultiArgsWithAsterick() throws Exception {
        application.run(argListMultiArgumentSpecialChar, stdin, stdout);
        assertEquals(MULTI_CHAR_ASTERISK_STRING, stdout.toString());
    }

    /**
     * Echo can run with flags. Flags are interpreted as strings
     * @throws Exception
     * @Test
     */
    void testRunValidArgsWithOptionThrowException() throws Exception {
        try {
            application.run(argListWithEchoOptions, stdin, stdout);
            String actual = stdout.toString();
            assertEquals("-n ABC", actual);
            fail("Should fail");
        }catch(EchoException e) {
            assertEquals(new EchoException(ERR_OPTION_NOT_SUPPORT).getMessage(), e.getMessage());
        }
    }

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void testRunArgsWithHelpOptionThrowException() throws Exception {
        Throwable thrown = assertThrows(EchoException.class, () -> {
            application.run(argListWithOptionsHelp, stdin, stdout);
        });
        assertEquals(new EchoException(ERR_OPTION_NOT_SUPPORT).getMessage(), thrown.getMessage());
    }
    */

    @Test
    void testRunArgsNullArgThrowException() {
        assertThrows(EchoException.class, () -> {
            application.run(null, stdin, stdout);
        });
    }

    @Test
    void testRunArgsEmptyArgThrowException() throws Exception {
        String[] emptyArg = new String[0];
        application.run(emptyArg, stdin, stdout);
        assertEquals("", stdout.toString());
    }


    @Test
    void runTest() throws AbstractApplicationException {
        String expectedOutput = MIX_SYMBOL;
        String[] input = {MIX_SYMBOL};
        application.run(input, System.in, fileOutputStream);


        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            assertEquals(expectedOutput, reader.readLine());
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @BeforeAll
    static void setUpOnce() {
        argListOneArgument = new String[1];
        argListOneArgument[0] = MULTI_CHAR_STRING;

        argListMultiArgument = new String[3];
        argListMultiArgument[0] = SINGLE_CHAR_STRING_A;
        argListMultiArgument[1] = SINGLE_CHAR_STRING_B;
        argListMultiArgument[2] = SINGLE_CHAR_STRING_C;

        argListMultiArgumentSpecialChar = new String[1];
        argListMultiArgumentSpecialChar[0] = MULTI_CHAR_ASTERISK_STRING;

        argListWithEchoOptions = new String[1];
        argListWithEchoOptions[0] = ECHO_OPTION_MESSAGES;

        argListWithOptionsHelp = new String[1];
        argListWithOptionsHelp[0] = ECHO_OPTION_HELP;
    }

    @BeforeEach
    void setUp() {
        try {
            file = new File(FILE_NAME);
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        try {
            fileOutputStream.close();
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
