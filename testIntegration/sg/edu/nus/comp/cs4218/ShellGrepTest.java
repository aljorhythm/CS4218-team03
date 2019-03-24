package sg.edu.nus.comp.cs4218;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.app.GrepApplication;

import java.io.*;
import java.nio.file.Path;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_SHELL_ARROW;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class ShellGrepTest extends ShellTest{

    static String temporaryDir;
    static String oriWorkingDir;
    private static File longFile;
    private static String cmdstr;

    GrepApplication grep;

    private static final String FILE_LONG = "test.txt";
    private static final String CONTENT_LINE_ONE = "1. test abc";
    private static final String CONTENT_LINE_TWO = "2. HELLO world!";
    private static final String CONTENT_LINE_THREE = "3. test tttt";
    private static final String CONTENT_LONG = CONTENT_LINE_ONE + STRING_NEWLINE +
            CONTENT_LINE_TWO + STRING_NEWLINE + CONTENT_LINE_THREE;


    private static File createAndWriteToFile(String filename, String content) throws IOException {
        File file = new File(filename);
        file.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()));
        writer.write(content);
        writer.close();

        return file;
    }

    @BeforeAll
    static void initTempDir(@TempDir Path temp) throws IOException {
        temporaryDir = temp.toAbsolutePath().toString();
        oriWorkingDir = Environment.currentDirectory;
        Environment.currentDirectory = temp.toAbsolutePath().toString();
        longFile = createAndWriteToFile(FILE_LONG, CONTENT_LONG);
        cmdstr = "grep test " + longFile.getAbsolutePath();
    }

    @BeforeEach
    void setUp() {
        grep = new GrepApplication();
    }

    /**
     * Resets working directory after all tests
     */
    @AfterAll
    static void resetWorkingDir() {
        Environment.currentDirectory = oriWorkingDir;
    }


    @AfterEach
    void tearDown(){
        longFile.delete();
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
                temporaryDir + CHAR_SHELL_ARROW + CONTENT_LINE_ONE,
                CONTENT_LINE_THREE,
                temporaryDir + CHAR_SHELL_ARROW
        };
        assertInputOutput(input, expectedLines);
    }

}
