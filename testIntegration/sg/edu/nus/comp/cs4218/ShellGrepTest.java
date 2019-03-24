package sg.edu.nus.comp.cs4218;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.app.GrepApplication;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.CommandBuilder;

import java.io.*;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHARSET_UTF8;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class ShellGrepTest {

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


    private ByteArrayOutputStream outputStream;
    private InputStream inputStream;

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
    void setUp() throws IOException {
        grep = new GrepApplication();
        inputStream = new ByteArrayInputStream("test".getBytes());
        outputStream = new ByteArrayOutputStream();
    }

    /**
     * Resets working directory after all tests
     */
    @AfterAll
    static void resetWorkingDir() {
        Environment.currentDirectory = oriWorkingDir;
    }


    @AfterEach
    void tearDown() throws IOException {
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        longFile.delete();
    }

    @Test
    void grepTestWithShell() throws AbstractApplicationException, ShellException, UnsupportedEncodingException {
        String expected = CONTENT_LINE_ONE + STRING_NEWLINE + CONTENT_LINE_THREE + STRING_NEWLINE;
        Command command = CommandBuilder.parseCommand(cmdstr, new ApplicationRunner());
        command.evaluate(inputStream, outputStream);
        String actual = outputStream.toString(CHARSET_UTF8);
        assertEquals(expected,actual);
    }

}
