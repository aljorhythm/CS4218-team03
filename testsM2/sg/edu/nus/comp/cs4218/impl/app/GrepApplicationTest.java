/**
 * From Team 10
 */
package sg.edu.nus.comp.cs4218.impl.app;import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.GrepException;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

class GrepApplicationTest {

    private static final String DIRECTORY = "directory";
    private static final String FILE_LONG = "file1.in";
    private static final String FILE_SHORT = "file2.in";
    private static final String FILE_NOT_EXIST = "file3.in";

    private static final String CONTENT_LINE_ONE = "1. Lorem ipsum is Aaa placeholder text.";
    private static final String CONTENT_LINE_TWO = "2. HELLO world!";
    private static final String CONTENT_LINE_THREE = "3. Hello world, can we get an A?";

    public static final String STRING_SEMICOLON_WITH_SPACE = ": ";
    public static final String ERR_FILE_NOT_EXIST = "File does not exist";
    public static final String ERR_IS_DIRECTORY = "Is a directory";

    private static final String CONTENT_LONG = CONTENT_LINE_ONE + STRING_NEWLINE +
            CONTENT_LINE_TWO + STRING_NEWLINE + CONTENT_LINE_THREE;
    private static final String CONTENT_SHORT = CONTENT_LINE_THREE;

    private static final String MSG_GREP_EXCEPTION = "grep: ";

    private File longFile;
    private File shortFile;

    private GrepApplication grep;
    private OutputStream stdout;

    private String constructExpectedOutput(String filename, boolean isCountLine, int... lineNumbers) {
        StringBuilder sb = new StringBuilder();

        if (isCountLine) {
            sb.append(lineNumbers.length);
            sb.append(STRING_NEWLINE);
            return sb.toString();
        }

        for (int index : lineNumbers) {
            sb.append(getContentLine(index));
            sb.append(STRING_NEWLINE);
        }

        return sb.toString().trim();
    }

    private String getContentLine(int lineNumber) {
        switch (lineNumber) {
            case 1:
                return CONTENT_LINE_ONE;
            case 2:
                return CONTENT_LINE_TWO;
            case 3:
                return CONTENT_LINE_THREE;
            default:
                return CONTENT_LINE_THREE;
        }
    }

    private File createAndWriteToFile(String filename, String content) throws IOException {
        File file = new File(filename);
        file.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()));
        writer.write(content);
        writer.close();

        return file;
    }

    @BeforeEach
    void setUp() throws IOException {
        grep = new GrepApplication();
        stdout = new ByteArrayOutputStream();

        longFile = createAndWriteToFile(FILE_LONG, CONTENT_LONG);
        shortFile = createAndWriteToFile(FILE_SHORT, CONTENT_SHORT);
    }

    @AfterEach
    void tearDown() throws IOException {
        try {
            stdout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        longFile.delete();
        shortFile.delete();
    }

    // ====================== Test run() ======================

    @Test
    void nullArgsShouldThrowGrepException() {
        assertThrows(GrepException.class, () -> {
            grep.run(null, System.in, stdout);
        });
    }

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void emptyArgsShouldThrowGrepException() {
        assertThrows(GrepException.class, () -> {
            grep.run(new String[]{}, System.in, stdout);
        });
    }
     */

    @Test
    void nullOutputStreamShouldThrowGrepException() {
        assertThrows(GrepException.class, () -> {
            grep.run(new String[]{"-i", "WoRLD", FILE_LONG}, System.in, null);
        });
    }

    @Test
    void testRunWithCaseInsensitiveSingleFile() throws Exception {
        String[] args = {"-i", "WoRLD", FILE_LONG};
        grep.run(args, null, stdout);
        String expectedOutput = constructExpectedOutput(FILE_LONG, false, 2, 3);
        assertEquals(expectedOutput, stdout.toString());
    }

    @Test
    void testRunWithCaseSensitiveCountLinesSingleFile() throws Exception {
        String[] args = {"-c", "world", FILE_LONG};
        grep.run(args, null, stdout);
        String expectedOutput = constructExpectedOutput(FILE_LONG, true, 2, 3).trim();
        assertEquals(expectedOutput, stdout.toString());
    }

    @Test
    void testRunWithCaseInsensitiveCountLinesMultipleFiles() throws Exception {
        String[] args = {"-i", "-c", "world", FILE_LONG, FILE_SHORT};
        grep.run(args, null, stdout);
        String expectedOutput = constructExpectedOutput(FILE_LONG, true, 2, 3) +
                constructExpectedOutput(FILE_SHORT, true, 3).trim();
        assertEquals(expectedOutput, stdout.toString());
    }

    @Test
    void invalidOptionsShouldThrowGrepException() {
        String[] args = {"--i", "-c", "world", FILE_LONG};
        assertThrows(GrepException.class, () -> {
            grep.run(args, null, stdout);
        });
    }

    @Test
    void emptyPatternShouldReturnEmptyString() throws Exception {
        String[] args = {"-i", "-c", "", FILE_LONG};
        grep.run(args, null, stdout);
        assertEquals("3", stdout.toString());
    }

    @Test
    void testRunWithStdinCountLines() throws Exception {
        String[] args = {"-c", "test"};
        String input = "line 1 test" + STRING_NEWLINE + "line 2 test" + STRING_NEWLINE + "line 3 test";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        grep.run(args, inputStream, stdout);
        assertEquals("3", stdout.toString());
    }

    // ====================== Test grepFromFiles() ======================

    @Test
    void testCaseSensitiveOneFileNoMatch() throws Exception {
        String pattern = "no match";
        String output = grep.grepFromFiles(pattern, true,
                false, FILE_LONG);
        assertEquals("", output);
    }

    @Test
    void testCaseInsensitiveOneFileSingleMatch() throws Exception {
        String pattern = "Lor";
        String output = grep.grepFromFiles(pattern, true,
                false, FILE_LONG);
        String expectedOutput = constructExpectedOutput(FILE_LONG, false, 1);
        assertEquals(expectedOutput, output);
    }

    @Test
    void testCaseInsensitiveOneFileMultipleMatches() throws Exception {
        String pattern = "hello";
        String output = grep.grepFromFiles(pattern, true,
                false, FILE_LONG);
        String expectedOutput = constructExpectedOutput(FILE_LONG, false, 2, 3);
        assertEquals(expectedOutput, output);
    }

    @Test
    void testCaseInsensitiveCountLinesOneFileMultipleMatches() throws Exception {
        String pattern = "world";
        String output = grep.grepFromFiles(pattern, true,
                true, FILE_LONG);
        String expectedOutput = constructExpectedOutput(FILE_LONG, true, 2, 3).trim();
        assertEquals(expectedOutput, output);
    }

    @Test
    void testCaseSensitiveOneFileSingleMatch() throws Exception {
        String pattern = "Hello";
        String output = grep.grepFromFiles(pattern, false,
                false, FILE_LONG);
        String expectedOutput = constructExpectedOutput(FILE_LONG, false, 3);
        assertEquals(expectedOutput, output);
    }

    @Test
    void testCaseInsensitiveMultipleValidFiles() throws Exception {
        String pattern = "hELLO";
        String output = grep.grepFromFiles(pattern, true,
                false, FILE_LONG, FILE_SHORT);
        String expectedOutput =
                constructExpectedOutput(FILE_LONG, false, 2, 3) + STRING_NEWLINE +
                        constructExpectedOutput(FILE_SHORT, false, 3);
        assertEquals(expectedOutput, output);
    }

/** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void testCaseInsensitiveMultipleFilesWithNonExistentFile() throws Exception {
        String pattern = "WORLd";
        String output = grep.grepFromFiles(pattern, true,
                false, FILE_NOT_EXIST, FILE_LONG, FILE_SHORT);
        String expectedOutput =
                FILE_NOT_EXIST + STRING_SEMICOLON_WITH_SPACE + ERR_FILE_NOT_EXIST + STRING_NEWLINE +
                        constructExpectedOutput(FILE_LONG, false, 2, 3) +
                        constructExpectedOutput(FILE_SHORT, false, 3);
        assertEquals(expectedOutput, output);
    }
 */

    @Test
    void testCaseSensitiveCountLinesMultipleValidFiles() throws Exception {
        String pattern = "A";
        String output = grep.grepFromFiles(pattern, false,
                true, FILE_SHORT, FILE_LONG);
        String expectedOutput =
                constructExpectedOutput(FILE_SHORT, true, 3) +
                        constructExpectedOutput(FILE_LONG, true, 1, 3).trim();
        assertEquals(expectedOutput, output);
    }

/** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void testCaseInsensitiveMultipleFilesWithDirectory() throws Exception {
        String pattern = "text";
        File folder = new File(DIRECTORY);
        folder.mkdirs();
        String output = grep.grepFromFiles(pattern, true,
                false, FILE_SHORT, FILE_LONG, DIRECTORY);
        String expectedOutput =
                constructExpectedOutput(FILE_LONG, false, 1) +
                        DIRECTORY + STRING_SEMICOLON_WITH_SPACE +
                        ERR_IS_DIRECTORY + STRING_NEWLINE;
        assertEquals(expectedOutput, output);
        folder.delete();
    }
 */

    @Test
    void testGrepFromFileInvalidPattern() {
        String pattern = "[[[";
        assertThrows(GrepException.class, () -> {
            grep.grepFromFiles(pattern, false, false, null);
        });
    }

    // ====================== Test grepFromStdin() ======================

    @Test
    void nullStdinShouldThrowGrepException() throws GrepException {
        assertThrows(GrepException.class, () -> {
            grep.grepFromStdin("pattern", false, false, null);
        });
    }

    @Test
    void testEmptyStdin() throws Exception {
        String pattern = "pattern";
        String input = "";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        String output = grep.grepFromStdin(pattern, false,
                false, inputStream);
        assertEquals("", output);
    }

    @Test
    void testStdinCaseSensitiveNoMatch() throws Exception {
        String pattern = "CS4218";
        String input = "@#()!$!*)$";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        String output = grep.grepFromStdin(pattern, false,
                false, inputStream);
        assertEquals("", output);
    }

    @Test
    void testStdinCaseInsensitiveSingleMatch() throws Exception {
        String pattern = "softw@re";
        String input = "SOFTW@RE! testing";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        String output = grep.grepFromStdin(pattern, true,
                false, inputStream);
        assertEquals("SOFTW@RE! testing", output);
    }

    @Test
    void testStdinCaseInsensitiveCountLinesMultipleMatches() throws Exception {
        String pattern = "p@55wOrD";
        String input = "p@55wOrD is weak" + STRING_NEWLINE + "p@55wOrD is bad";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        String output = grep.grepFromStdin(pattern, true,
                true, inputStream);
        assertEquals("2", output);
    }

    @Test
    void testStdinInvalidPattern() {
        String pattern = "[";
        String input = "[mockito]";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        assertThrows(GrepException.class, () -> {
            grep.grepFromStdin(pattern, true,
                    true, inputStream);
        });
    }
}