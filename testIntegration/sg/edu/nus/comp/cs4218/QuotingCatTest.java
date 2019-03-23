package sg.edu.nus.comp.cs4218;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.app.CatApplication;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.CommandBuilder;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class QuotingCatTest {
    CatApplication catApplication;
    InputStream defaultIStream;
    String defaultString = "ab abc\nb ccc";
    InputStream emptyIStream;
    String nonExistentFile = "wrong.txt";
    String testDir = TestUtils.pathToTestDataSubdir("catQuotingTest");
    String emptyFile = testDir + CHAR_FILE_SEP + "empty.txt";
    String testFileName1 = testDir + CHAR_FILE_SEP + "testFile1.txt";
    String testFileName2 = testDir + CHAR_FILE_SEP + "testFile2.txt";
    String testFile1Content = String.join(StringUtils.STRING_NEWLINE, new String[]{"test1 For Cat Quoting",
    "easy","for characters","4 lines"})+STRING_NEWLINE ;
    String testFile2Content = String.join(StringUtils.STRING_NEWLINE, new String[]{"test2 For Cat Quoting test",
    "!@#$%^&*()","for number and symbols","1324567890"})+STRING_NEWLINE;
    String testFileTogether = testFile1Content + STRING_NEWLINE +testFile2Content + STRING_NEWLINE;
    String catStringEmpty = "cat `echo "+emptyFile+"`";
    String catSthWrong = "cat `echo "+"wrong"+"`";
    String catString1 = "cat `echo "+testFileName1+"`";
    String catString2 = "cat `echo "+testFileName2+"`";
    String catStringForTwoFile = "cat `echo "+testFileName1+"`"+" `echo "+testFileName2+"`";

    /**
     * Set up a new cat application and mock inputstreams in between each test.
     *
     * @throws Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        catApplication = new CatApplication();
        defaultIStream = mock(InputStream.class, Mockito.CALLS_REAL_METHODS);
        when(defaultIStream.read()).thenReturn(97, 98, 32, 97, 98, 99, 10, 98, 32, 99, 99, 99, - 1);
        emptyIStream = mock(InputStream.class, Mockito.CALLS_REAL_METHODS);
        when(emptyIStream.read()).thenReturn(-1);
    }

    /**
     * Test catFiles with an empty input file, should return an empty string.
     *
     * @throws CatException
     */
    @Test
    public void testCatFilesEmptyInputSuccess() throws CatException {
        assertEquals("", catApplication.catFiles(emptyFile));
    }

    /**
     * Test catFiles with a file with content.
     *
     * @throws CatException
     */
    @Test
    public void testCatFilesDefaultInputSuccess() throws CatException {
        String expected = testFile1Content;
        String actual = catApplication.catFiles(testFileName1);
        assertEquals(expected, actual);
    }

    /**
     * Test catFiles with two files as input.
     *
     * @throws CatException
     */
    @Test
    public void testCatFilesTwoInputFilesSuccess() throws CatException {
        assertEquals(testFile1Content + STRING_NEWLINE + testFile2Content,
                catApplication.catFiles(testFileName1, testFileName2));
    }


    /**
     * Test run function with no args, so should write inputstream content to the outputstream.
     */
    @Test
    public void testRunNoArgsSuccess() throws CatException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] emptyArgs = new String[0];
        catApplication.run(emptyArgs, defaultIStream, baos);
        byte[] byteArray = baos.toByteArray();
        assertEquals("ab abc\nb ccc", new String(byteArray));
    }

    /**
     * Test run function with a file as argument.
     *
     * @throws CatException
     */
    @Test
    public void testRunOneArgSuccess() throws CatException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] args = new String[1];
        args[0] = testFileName1;
        catApplication.run(args, null, baos);
        byte[] byteArray = baos.toByteArray();
        assertEquals(testFile1Content, new String(byteArray));
    }

    /**
     * Test run function with two files as args.
     *
     * @throws CatException
     */
    @Test
    public void testRunTwoArgSuccess() throws CatException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] args = new String[2];
        args[0] = testFileName1;
        args[1] = testFileName2;
        catApplication.run(args, null, baos);
        byte[] byteArray = baos.toByteArray();
        assertEquals(testFile1Content + STRING_NEWLINE + testFile2Content, new String(byteArray));
    }

    /**
     * Test command with cat quoting with echo file1 as args
     *
     * @throws AbstractApplicationException,ShellException
     */
    @Test
    public void testCatEchoTestFile1() throws AbstractApplicationException,ShellException{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Command command = CommandBuilder.parseCommand(catString1,new ApplicationRunner());
        command.evaluate(null,byteArrayOutputStream);
        assertEquals(testFile1Content,new String(byteArrayOutputStream.toByteArray()));
    }

    /**
     * Test command with cat quoting with echo file2 as args
     *
     * @throws AbstractApplicationException,ShellException
     */
    @Test
    public void testCatEchoTestFile2() throws AbstractApplicationException,ShellException{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Command command = CommandBuilder.parseCommand(catString2,new ApplicationRunner());
        command.evaluate(null,byteArrayOutputStream);
        assertEquals(testFile2Content,new String(byteArrayOutputStream.toByteArray()));
    }

    /**
     * Test for wrong file in cat quoting
     *
     * @throws CatException,ShellException
     */
    @Test
    public void testCatEchoWrongFile() throws CatException,ShellException{
        assertThrows(CatException.class,()->{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
           Command command = CommandBuilder.parseCommand(catSthWrong,new ApplicationRunner());
           command.evaluate(null,byteArrayOutputStream);
        });
    }

    /**
     * Test for cat a empty file
     *
     * @throws CatException,ShellException
     */
    @Test
    public void testCatEchoEmptyFile() throws AbstractApplicationException,ShellException{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Command command = CommandBuilder.parseCommand(catStringEmpty,new ApplicationRunner());
        command.evaluate(null,byteArrayOutputStream);
        assertEquals(StringUtils.STRING_NEWLINE,new String(byteArrayOutputStream.toByteArray()));
    }

    /**
     * Test two file cat together
     *
     * @throws AbstractApplicationException,ShellException
     */
    @Test
    public void testCatEchoTwoFile() throws AbstractApplicationException, ShellException{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Command command = CommandBuilder.parseCommand(catStringForTwoFile,new ApplicationRunner());
        command.evaluate(null, byteArrayOutputStream);
        assertEquals(testFileTogether,new String(byteArrayOutputStream.toByteArray()));
    }
}
