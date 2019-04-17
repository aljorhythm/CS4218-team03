package sg.edu.nus.comp.cs4218.bugfixes;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Shell;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.FileIOTestHelper;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.*;
import java.nio.file.Files;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;

public class FirstCharUnreadTest {

    ShellImpl shell = new ShellImpl();
    public static String pathToProject = System.getProperty("user.dir") + File.separator;

    @AfterAll
    static void tearDown() {
        // Delete the created files when testing is done.
        String[] fileNames = new String[]{"output.txt"};
        String[] pathForFiles = new String[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            pathForFiles[i] = pathToProject + fileNames[i];
        }
        FileIOTestHelper.deleteMultipleFiles(pathForFiles);
    }

    @Test
    public void inputRedirectionTest() throws
            AbstractApplicationException, ShellException {
        String expected = "";
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        shell.parseAndEvaluate("cat < resources"
                + File.separator + "pwd.txt",stdout);
        assertEquals(expected, stdout.toString());
    }

    @Test
    public void outputRedirectionTest() throws
            AbstractApplicationException, ShellException {
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        shell.parseAndEvaluate("cat resources" +
                        File.separator + "pwd.txt > resources/output.txt",stdout);
        assertTrue(Files.exists(IOUtils.resolveFilePath("resources/output.txt")));
    }

    @Test
    public void runSuccessStdinFile() {
        assertTimeoutPreemptively(ofMillis(1000),
                () -> {
                    String arg = "wc < " + "resources/catQuotingTest/testFile1.txt";
                    Shell shell = new ShellImpl();
                    ByteArrayOutputStream outstream = new
                            ByteArrayOutputStream();
                    shell.parseAndEvaluate(arg, outstream);
                    String expectedResult = "4 9 46" + System.lineSeparator();
                    String actualResult =
                            outstream.toString();
                    assertEquals(expectedResult,
                            actualResult);
                });
    }


}
