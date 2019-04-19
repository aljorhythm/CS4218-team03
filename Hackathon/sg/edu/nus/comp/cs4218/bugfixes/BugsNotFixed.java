package sg.edu.nus.comp.cs4218.bugfixes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.Shell;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ExitException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.exception.WcException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.app.*;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.*;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

/**
 * This test file contains all test cases we got from the hackathon
 * but that were not fixed.
 */
@SuppressWarnings("PMD")
public class BugsNotFixed {
    PwdApplication pwdApp;
    SortApplication sortApp;
    DateApplication dateApp;
    ExitApplication exitApp;
    WcApplication wcApp;

    String testPath1 = TestUtils.pathToTestDataSubdir("wcTest") + File.separator + "test1.txt";
    String testPath2 = TestUtils.pathToTestDataSubdir("wcTest") + File.separator + "test2.txt";

    /**
     * Initializes application for each test
     */
    @BeforeEach
    public void initApplication(){
        pwdApp = new PwdApplication();
        sortApp = new SortApplication();
        dateApp = new DateApplication();
        exitApp = new ExitApplication();
        wcApp = new WcApplication();
    }

    // pwd [/s] [/s] where [/s] are whitespaces
    // Throws ShellException: shell: Invalid syntax.
    @Test
    public void runWithWhitespaceArguments()
            throws AbstractApplicationException,
            ShellException {
        Shell shell = new ShellImpl();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String arg = "pwd ";
        shell.parseAndEvaluate(arg, out);
        String expectedResult = Environment.currentDirectory;
        String actualResult = out.toString();
        assertEquals(expectedResult, actualResult);
    }

    // exit
    @Test
    public void runSuccessCorrectExitStatus() throws ExitException, IOException {
        String[] args = {};
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        InputStream stdin = IOUtils.stringToInputStream("");
        try {
            exitApp.run(args, stdin, stdout);
            fail("Should throw exit exception!");
        } catch
        (ExitException e) {
            int status = -1;
            //int status = e.getStatus();
            assertEquals(0, status);
        }
    }

    // exit hello
    @Test
    public void runWithAdditionalArguments() throws ExitException, IOException {
        String[] args = {"exit hello"};
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        InputStream stdin = IOUtils.stringToInputStream("");
        try {
            exitApp.run(args, stdin, stdout);
            fail("Should throw exit exception!");
        } catch
        (ExitException e) {
            int status = -1;
            //int status = e.getStatus();
            assertEquals(0, status);
        }
    }

    // wc
    @Test
    public void runSuccessStdin() {
        assertTimeoutPreemptively(ofMillis(10),
                () -> {
                    String arg = "wc";
                    Shell shell = new ShellImpl();
                    ByteArrayOutputStream outstream = new
                            ByteArrayOutputStream();
                    InputStream in = System.in;
                    String expectedResult = "2 2 12 ";
                    String str = "hello" +
                            System.lineSeparator() + "world" +
                            System.lineSeparator();
                    System.setIn(new
                            ByteArrayInputStream(str.getBytes()));
                    shell.parseAndEvaluate(arg, outstream);
                    String actualResult =
                            outstream.toString();
                    assertEquals(expectedResult,
                            actualResult);
                });
    }

    // wc smiley.jpg
    // Expected: 595 2004 68351 smiley.jpg
    // Result: 753 893 120599 smiley.jpg
    @Test
    public void runSuccessDifferentFileFormat()
            throws WcException, IOException {
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        InputStream stdin = IOUtils.stringToInputStream("");
        String expectedResult = "595 2004 68351 " + testPath2;
        String[] args = {testPath2};
        wcApp.run(args, stdin, stdout);
        assertEquals(expectedResult, stdout.toString());
    }

    // wc test1.txt test100.txt where test1.txt is valid and test100.txt is invalid.
    @Test
    public void runSuccessValidWithInvalidFile() throws WcException, IOException {
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        InputStream stdin = IOUtils.stringToInputStream("");
        String expectedResult = "52 " + testPath1;
        String[] args = {"-c", testPath1, "test1000.txt"};
        wcApp.run(args, stdin, stdout);
        assertEquals(expectedResult,
                stdout.toString());
    }

    @Test
    public void LsTestSuccess() throws
            AbstractApplicationException, ShellException {
        Shell shell = new ShellImpl();
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        String expected = "findFile1.java findFile1.java";
        shell.parseAndEvaluate("ls findFolder1", stdout);
        assertEquals(expected, stdout.toString());
    }

    @Test
    public void LsTestThrowsErrorEmptyFolder()
            throws AbstractApplicationException,
            ShellException {
        Shell shell = new ShellImpl();
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        assertThrows(Exception.class,
                ()->shell.parseAndEvaluate("ls", stdout));
    }

    @Test
    public void LsTestSuccessGlobbing() throws
            AbstractApplicationException, ShellException {
        String PATH = "";
        Shell shell = new ShellImpl();
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        String expected = PATH + File.separator +
                "findFolder1:" + STRING_NEWLINE +
                "findFile1.java findFile1.java" + STRING_NEWLINE + PATH + File.separator +
                "findFolder2:" + STRING_NEWLINE +
                "findFile2.java findFile2.txt findFolder2-1";
        shell.parseAndEvaluate("ls " + PATH + File.separator + "*",stdout);
        assertEquals(expected, stdout.toString());
    }

    @Test
    public void
    FindTestSuccessGlobbingFileName() throws
            AbstractApplicationException, ShellException {
        String PATH = "";
        Shell shell = new ShellImpl();
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        String expected = PATH + File.separator +
                "findFolder1" + File.separator + "findFile1.java" + STRING_NEWLINE + PATH + File.separator +
                "findFolder1" + File.separator + "findFile1.txt" +
                STRING_NEWLINE;
        shell.parseAndEvaluate("find " + PATH + File.separator + "findFolder1 -name 'findFile1*'",stdout);
        assertEquals(expected, stdout.toString());
    }

    @Test
    public void PipeCommandTestSuccess()
            throws AbstractApplicationException,
            ShellException {
        Shell shell = new ShellImpl();
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        String expected = "2" +
                STRING_NEWLINE;
        shell.parseAndEvaluate("echo 'hackathon test' | wc -w",stdout);
        assertEquals(expected, stdout.toString());
    }

    @Test
    public void SequenceCommandTestSuccess()
            throws AbstractApplicationException,
            ShellException {
        Shell shell = new ShellImpl();
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        String oldDir =
                Environment.currentDirectory;
        shell.parseAndEvaluate("cd findFolder1; pwd", stdout);
                assertEquals(oldDir + File.separator +
                        "findFolder1", Environment.currentDirectory);
    }

    @Test
    public void runSuccessOneFileNFlag() throws
            AbstractApplicationException, IOException {
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        InputStream stdin = IOUtils.stringToInputStream("");
        String expectedResult = "&&**" +
                System.lineSeparator()+
                "0" + System.lineSeparator()+
                "0a" + System.lineSeparator()+
                "1" + System.lineSeparator()+
                "1a" + System.lineSeparator()+
                "2" + System.lineSeparator()+
                "2a" + System.lineSeparator()+
                "10" + System.lineSeparator()+
                "10a" + System.lineSeparator()+
                "AABB" + System.lineSeparator()+
                "ABCD" + System.lineSeparator()+
                "aabb" + System.lineSeparator()+
                "abcd";
        String[] args = {"-n", ""};
        sortApp.run(args, stdin, stdout);
        assertEquals(expectedResult, stdout.toString());
    }
}
