/**
 * From Team 10
 */
package sg.edu.nus.comp.cs4218.impl.app;import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

@SuppressWarnings({"PMD.AvoidDuplicateLiterals", "PMD.LongVariable"})
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
        StringBuilder stringBuilder = new StringBuilder();

        if (isCountLine) {
            stringBuilder.append(lineNumbers.length);
            stringBuilder.append(STRING_NEWLINE);
            return stringBuilder.toString();
        }

        for (int index : lineNumbers) {
            stringBuilder.append(getContentLine(index));
            stringBuilder.append(STRING_NEWLINE);
        }

        return stringBuilder.toString().trim();
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
    void testRunWithCaseInsensitiveSingleFile() throws Exception {
        String[] args = {"-i", "WoRLD", FILE_LONG};
        grep.run(args, null, stdout);
        String expectedOutput = constructExpectedOutput(FILE_LONG, false, 2, 3);
        assertEquals(expectedOutput, stdout.toString());
    }

}