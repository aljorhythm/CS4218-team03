package sg.edu.nus.comp.cs4218;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.*;
import java.nio.file.Path;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;
import static sg.edu.nus.comp.cs4218m1.TestUtils.CHAR_SHELL_ARROW;

@SuppressWarnings("PMD.LongVariable")
public class ShellGrepTest extends ShellTest{

    static String temporaryDir;
    static String oriWorkingDir;
    private static File longFile;
    private static String cmdstr;

    private static final String FILE_LONG = "test.txt";
    private static final String LINE_ONE = "1. test abc";
    private static final String LINE_TWO = "2. HELLO world!";
    private static final String LINE_THREE = "3. test tttt";
    private static final String CONTENT_LONG = LINE_ONE + STRING_NEWLINE +
            LINE_TWO + STRING_NEWLINE + LINE_THREE;


    @BeforeAll
    static void initTempDir(@TempDir Path temp) throws IOException {
        temporaryDir = temp.toAbsolutePath().toString();
        oriWorkingDir = Environment.currentDirectory;
        Environment.currentDirectory = temp.toAbsolutePath().toString();
        longFile = TestUtils.createAndWriteToFile(FILE_LONG, CONTENT_LONG);
        cmdstr = "grep test " + longFile.getAbsolutePath();
    }

    /**
     * Resets working directory after all tests
     */
    @AfterAll
    static void resetWorkingDir() {
        Environment.currentDirectory = oriWorkingDir;
    }


    /**
     * Shell should output working directory
     */
    @Test
    void initShell() throws IOException, ShellException {
        String[] input = {
                "exit"
        };
        String[] expectedLines = {
                temporaryDir + CHAR_SHELL_ARROW
        };
        assertInputOutput(input, expectedLines);
    }

    @Test
    void grepTestWithShell() throws AbstractApplicationException, ShellException, IOException {
        String[] input = {
                cmdstr,
                "exit"
        };
        String[] expectedLines = {
                temporaryDir + CHAR_SHELL_ARROW + LINE_ONE,
                LINE_THREE,
                temporaryDir + CHAR_SHELL_ARROW
        };
        assertInputOutput(input, expectedLines);
    }

}
