package sg.edu.nus.comp.cs4218.bugfixes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.PwdException;
import sg.edu.nus.comp.cs4218.exception.SortException;
import sg.edu.nus.comp.cs4218.exception.WcException;
import sg.edu.nus.comp.cs4218.impl.app.DateApplication;
import sg.edu.nus.comp.cs4218.impl.app.PwdApplication;
import sg.edu.nus.comp.cs4218.impl.app.SortApplication;
import sg.edu.nus.comp.cs4218.impl.app.WcApplication;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class WcTest {
    WcApplication wcApp;

    String testPath1 = TestUtils.pathToTestDataSubdir("wcTest") + File.separator + "test1.txt";

    /**
     * Initializes application for each test
     */
    @BeforeEach
    public void initApplication(){
        wcApp = new WcApplication();
    }

    // wc -c test1.txt
    // Expected: 52
    // Result:: 46
    @Test
    public void runSuccessSingleByteTag() throws WcException, IOException {
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        InputStream stdin = IOUtils.stringToInputStream("");
        String expectedResult = "52 " + testPath1;
        String[] args = {"-c", testPath1};
        wcApp.run(args, stdin, stdout);
        assertEquals(expectedResult, stdout.toString());
    }

    // wc --
    @Test
    public void runSuccessTwoDash() throws WcException, IOException {
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        InputStream stdin = IOUtils.stringToInputStream("");
        String expectedResult = "0 1 52 " + testPath1;
        String[] args = {"--", testPath1};
        wcApp.run(args, stdin, stdout);
        assertEquals(expectedResult, stdout.toString());
    }

    // wc: Directory
    @Test
    public void runThrowsExceptionDirectory() throws IOException {
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        InputStream stdin = IOUtils.stringToInputStream("");
        String[] args = {"hackathon_tests" + File.separator + "resources" + File.separator + "wcTest"};
        WcException exception = assertThrows(WcException.class, () -> wcApp.run(args, stdin, stdout));
        assertTrue(exception.getMessage().contains("not found"));
    }
}
