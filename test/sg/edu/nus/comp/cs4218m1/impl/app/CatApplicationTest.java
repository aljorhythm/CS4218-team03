package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.impl.app.CatApplication;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class CatApplicationTest {
    CatApplication catApplication;
    InputStream defaultIStream;
    String defaultString = "ab abc\nb ccc";
    InputStream emptyIStream;
    String nonExistentFile = "wrong.txt";
    String testDir = TestUtils.pathToTestDataSubdir("inputFiles");
    String emptyFile = testDir + CHAR_FILE_SEP + "catEmpty.txt";
    String testFileName1 = testDir + CHAR_FILE_SEP + "catContent1.txt";
    String testFileName2 = testDir + CHAR_FILE_SEP + "catContent2.txt";
    String testFile1Content = "test string for" + STRING_NEWLINE + "testing cat" + STRING_NEWLINE
            + "with junit 5." + STRING_NEWLINE;
    String testFile2Content = "another file, number 2" + STRING_NEWLINE + "second for testing that"
            + STRING_NEWLINE + "cat " + STRING_NEWLINE + "works like " + STRING_NEWLINE + "it should" + STRING_NEWLINE + ". 1234 %&#!$@" + STRING_NEWLINE;

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
     * Test catStdin with null input which should throw an exception.
     */
    @Test
    public void testCatStdinNullInputFailure() {
        assertThrows(CatException.class, () -> {
            catApplication.catStdin(null);
        });
    }
}
