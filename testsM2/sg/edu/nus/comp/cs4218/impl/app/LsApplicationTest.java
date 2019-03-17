/**
 * From Team 01
 */
package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.exception.LsException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalMatchers.aryEq;
import static org.mockito.Mockito.*;

/**
 * SUT: {@link LsApplication}.
 */
@Tag("unit-tdd")
@SuppressWarnings({"PMD.MethodNamingConventions", "PMD.AvoidDuplicateLiterals", "PMD.LongVariable"})
public class LsApplicationTest {

    private LsApplication lsApplication;

    private static ArrayList<String> testFilePath;

    private static String FOLDER_PATH = TestUtils.pathToTestDataSubdir("lsTestDirTDD");
    private static final String FOLDER1 = FOLDER_PATH + File.separator + "folder1";
    private static final String FOLDER2 = FOLDER_PATH + File.separator + "folder2";
    private static final String FOLDER3 = FOLDER_PATH + File.separator + "folder3";
    private static final String FOLDER3_IN_FOLDER1 = FOLDER_PATH + File.separator + "folder1" + File.separator + "folder3";
    private static final String FOLDER4_IN_FOLDER2 = FOLDER_PATH + File.separator + "folder2" + File.separator + "folder4";

    @BeforeAll
    static void setUpAll() throws IOException {
        testFilePath = new ArrayList<>();
        //Create folders and files for testing
        File baseFolder = new File(FOLDER3);
        if (baseFolder.mkdirs()) {
        }
    }

    @AfterAll
    static void tearDownAll() {
        File emptyFolder = new File(FOLDER3);
        emptyFolder.delete();
    }

    @BeforeEach
    public void beforeEach() {
        lsApplication = spy(LsApplication.class);
    }

    @Test
    public void testRun_withoutArgumentsAndInputStream_shouldReturnCorrectly() throws Exception {
        String[] args = new String[]{};
        when(lsApplication.listFolderContent(false, false, args)).thenReturn("test1.txt");
        OutputStream outputStream = mock(OutputStream.class);
        lsApplication.run(args, null, outputStream);

        verify(outputStream).write(aryEq("test1.txt".getBytes()));
    }

    @Test
    public void testRun_withoutArgumentsButHaveInputStream_shouldIgnoreInputStream() throws Exception {
        String[] args = new String[]{};
        when(lsApplication.listFolderContent(false, false)).thenReturn("test2.txt");
        InputStream inputStream = mock(InputStream.class);
        OutputStream outputStream = mock(OutputStream.class);

        lsApplication.run(args, inputStream, outputStream);
        verify(outputStream).write(aryEq("test2.txt".getBytes()));
    }

    @Test
    public void testRun_withoutArgumentsAndInputStreamButErrorOutputStream_shouldThrowException() throws Exception {
        String[] args = new String[]{};
        OutputStream outputStream = mock(OutputStream.class);
        doThrow(IOException.class).when(outputStream).write(any());
        assertThrows(LsException.class, () -> {
            lsApplication.run(args, null, outputStream);
        });
    }

    @Test
    public void testRun_withoutFlagWithOneArgument_shouldReturnCorrectly() throws Exception {
        String[] args = new String[]{FOLDER_PATH};
        String expected = "file1.txt file2.txt directory1";
        OutputStream outputStream = mock(OutputStream.class);
        when(lsApplication.listFolderContent(false, false, args)).thenReturn(expected);

        lsApplication.run(args, null, outputStream);
        verify(outputStream).write(aryEq(expected.getBytes()));
    }

    @Test
    public void testRun_withoutFlagWithMultipleArguments_shouldReturnCorrectly() throws Exception {
        String[] args = new String[]{"folder1", "folder2"};
        String expected = "expected";
        OutputStream outputStream = mock(OutputStream.class);
        when(lsApplication.listFolderContent(false, false, args)).thenReturn(expected);

        lsApplication.run(args, null, outputStream);
        verify(outputStream).write(aryEq(expected.getBytes()));
    }

    @Test
    public void testRun_withInvalidFlag_shouldThrowException() throws Exception {
        String[] args = new String[]{"-invalid_flag", "folder1"};
        OutputStream outputStream = mock(OutputStream.class);
        assertThrows(LsException.class, () -> {
            lsApplication.run(args, null, outputStream);
        });
    }

    @Test
    public void testRun_withFolderOnlyFlagAndOneArguments_shouldReturnCorrectly() throws Exception {
        String[] args = new String[]{"-d", "folder1"};
        String[] listArgs = new String[]{"folder1"};
        String expectedNotFolderOnly = "test1.txt folder1";
        String expected = "folder1";
        OutputStream outputStream = mock(OutputStream.class);
        when(lsApplication.listFolderContent(true, false, args)).thenReturn(expected);
        when(lsApplication.listFolderContent(false, false, args)).thenReturn(expectedNotFolderOnly);

        lsApplication.run(args, null, outputStream);
        verify(outputStream).write(aryEq(expected.getBytes()));
    }

    @Test
    public void testRun_withFolderOnlyFlagAndMultipleArguments_shouldReturnCorrectly() throws Exception {
        String[] args = new String[]{"-d", "folder1", "folder2"};
        String expectedNotFolderOnly = "Not expected";
        String expected = "expected";
        OutputStream outputStream = mock(OutputStream.class);
        when(lsApplication.listFolderContent(true, false, args)).thenReturn(expected);
        when(lsApplication.listFolderContent(false, false, args)).thenReturn(expectedNotFolderOnly);

        lsApplication.run(args, null, outputStream);
        verify(outputStream).write(aryEq(expected.getBytes()));
    }

    @Test
    public void testRun_withRecursiveFlagAndOneArguments_shouldReturnCorrectly() throws Exception {
        String[] args = new String[]{"-R", "folder1"};
        String expectedNotFolderOnly = "folder3";
        String expected = "folder3:" + StringUtils.STRING_NEWLINE + "test.txt";
        OutputStream outputStream = mock(OutputStream.class);
        when(lsApplication.listFolderContent(false, true, args)).thenReturn(expected);
        when(lsApplication.listFolderContent(false, false, args)).thenReturn(expectedNotFolderOnly);

        lsApplication.run(args, null, outputStream);
        verify(outputStream).write(aryEq(expected.getBytes()));
    }

    @Test
    public void testRun_withRecursiveFlagAndMultipleArguments_shouldReturnCorrectly() throws Exception {
        String[] args = new String[]{"-R", "folder1", "folder2"};
        String expectedNotFolderOnly = "Not expected";
        String expected = "expected";

        OutputStream outputStream = mock(OutputStream.class);
        when(lsApplication.listFolderContent(false, true, args)).thenReturn(expected);
        when(lsApplication.listFolderContent(false, false, args)).thenReturn(expectedNotFolderOnly);

        lsApplication.run(args, null, outputStream);
        verify(outputStream).write(aryEq(expected.getBytes()));
    }

    @Test
    public void testRun_withRecursiveAndFolderOnly_shouldReturnCorrectly() throws Exception {
        String[] argsTypeOne = new String[]{"-d", "-R", "folder1"};
        String[] argsTypeTwo = new String[]{"-R", "-d", "folder1"};
        String[] argsTypeThree = new String[]{"-dR", "folder1"};
        String[] argsTypeFour = new String[]{"-Rd", "folder1"};
        String[] args = new String[]{"folder1"};

        String expected = "some result";
        String notExpected = "not expected";
        OutputStream outputStream = mock(OutputStream.class);
        when(lsApplication.listFolderContent(true, true, args)).thenReturn(expected);
        when(lsApplication.listFolderContent(false, false, args)).thenReturn(notExpected);
        when(lsApplication.listFolderContent(false, true, args)).thenReturn(notExpected);
        when(lsApplication.listFolderContent(true, false, args)).thenReturn(notExpected);

        lsApplication.run(argsTypeOne, null, outputStream);
        verify(outputStream).write(aryEq(expected.getBytes()));

        lsApplication.run(argsTypeTwo, null, outputStream);
        verify(outputStream).write(aryEq(expected.getBytes()));

        lsApplication.run(argsTypeThree, null, outputStream);
        verify(outputStream).write(aryEq(expected.getBytes()));

        lsApplication.run(argsTypeFour, null, outputStream);
        verify(outputStream).write(aryEq(expected.getBytes()));
    }

    @Test
    public void testRun_oneArgumentDoesNotExist_shouldThrowException() throws Exception {
        String[] args1 = new String[]{"folder2"};
        String[] args2 = new String[]{"-R", "folder2"};
        String[] args3 = new String[]{"-d", "folder2"};
        String[] args4 = new String[]{"-d -R", "folder2"};
        String[] args5 = new String[]{"-R -d", "folder2"};
        String[] args6 = new String[]{"-Rd", "folder2"};
        String[] args7 = new String[]{"-dR", "folder2"};
        OutputStream outputStream = mock(OutputStream.class);
        doThrow(Exception.class).when(lsApplication).listFolderContent(false, false, args1);
        doThrow(Exception.class).when(lsApplication).listFolderContent(true, false, args1);
        doThrow(Exception.class).when(lsApplication).listFolderContent(false, true, args1);
        doThrow(Exception.class).when(lsApplication).listFolderContent(true, true, args1);
        assertThrows(LsException.class, () -> {
            lsApplication.run(args1, null, outputStream);
        });

        assertThrows(LsException.class, () -> {
            lsApplication.run(args2, null, outputStream);
        });

        assertThrows(LsException.class, () -> {
            lsApplication.run(args3, null, outputStream);
        });

        assertThrows(LsException.class, () -> {
            lsApplication.run(args4, null, outputStream);
        });

        assertThrows(LsException.class, () -> {
            lsApplication.run(args5, null, outputStream);
        });

        assertThrows(LsException.class, () -> {
            lsApplication.run(args6, null, outputStream);
        });

        assertThrows(LsException.class, () -> {
            lsApplication.run(args7, null, outputStream);
        });
    }

    @Test
    public void testRun_noFlagWithMultipleArgumentSomeOfThemDoesNotExist_shouldThrowException() throws Exception {
        String[] args = new String[]{"folder2", "folder3", "folder4"};
        String[] args1 = new String[]{"folder2"};
        String[] args2 = new String[]{"folder3"};
        String[] args3 = new String[]{"folder4"};
        String expected = "Correct Result";
        OutputStream outputStream = mock(OutputStream.class);
        doThrow(Exception.class).when(lsApplication).listFolderContent(false, false, args1);
        doThrow(Exception.class).when(lsApplication).listFolderContent(false, false, args2);
        when(lsApplication.listFolderContent(false, false, args3)).thenReturn(expected);

        assertThrows(LsException.class, () -> {
            lsApplication.run(args, null, outputStream);
        });

        verify(outputStream).write(aryEq(expected.getBytes()));
    }

    @Test
    public void testRun_folderOnlyFlagWithMultipleArgumentSomeOfThemDoesNotExist_shouldThrowException() throws Exception {
        String[] args = new String[]{"folder1", "folder2", "folder3"};
        String[] args1 = new String[]{"folder1"};
        String[] args2 = new String[]{"folder2"};
        String[] args3 = new String[]{"folder3"};
        String expected = "Correct Result";
        OutputStream outputStream = mock(OutputStream.class);
        doThrow(Exception.class).when(lsApplication).listFolderContent(true, false, args1);
        when(lsApplication.listFolderContent(false, false, args3)).thenReturn(expected);

        assertThrows(LsException.class, () -> {
            lsApplication.run(args, null, outputStream);
        });

        verify(outputStream).write(aryEq(expected.getBytes()));
    }

    @Test
    public void testRun_recursiveFlagWithMultipleArgumentSomeOfThemDoesNotExist_shouldThrowException() throws Exception {
        String[] args = new String[]{"folder3", "folder4", "folder5"};
        String[] args1 = new String[]{"folder3"};
        String[] args2 = new String[]{"folder4"};
        String[] args3 = new String[]{"folder5"};
        String expected = "Correct Result";
        OutputStream outputStream = mock(OutputStream.class);
        doThrow(Exception.class).when(lsApplication).listFolderContent(false, true, args2);
        doThrow(Exception.class).when(lsApplication).listFolderContent(false, true, args3);
        when(lsApplication.listFolderContent(false, false, args1)).thenReturn(expected);

        assertThrows(LsException.class, () -> {
            lsApplication.run(args, null, outputStream);
        });

        verify(outputStream).write(aryEq(expected.getBytes()));
    }

    @Test
    public void testRun_bothFlagsWithMultipleArgumentSomeOfThemDoesNotExist_shouldThrowException() throws Exception {
        String[] args = new String[]{"folder1", "folder2"};
        String[] args1 = new String[]{"folder1"};
        String[] args2 = new String[]{"folder2"};
        String expected = "Correct Result";
        OutputStream outputStream = mock(OutputStream.class);
        doThrow(Exception.class).when(lsApplication).listFolderContent(true, true, args1);
        when(lsApplication.listFolderContent(false, false, args2)).thenReturn(expected);

        assertThrows(LsException.class, () -> {
            lsApplication.run(args, null, outputStream);
        });

        verify(outputStream).write(aryEq(expected.getBytes()));
    }


    /**
     * Note: the following test cases will need the following file structure:
     * currentDirectory
     * |
     * |-- folder1
     * |       |
     * |       |-- folder3
     * |       |       |
     * |       |       |-- test3.txt
     * |       |
     * |       |-- test1.txt
     * |
     * |-- folder3
     * |
     * |-- folder4
     * |       |
     * |       |-- test4.txt
     * |
     * |-- test2.txt
     */
    @Test
    public void testListFolderContent_withNoArgumentAndFlag_shouldListCurrentDirectory() throws Exception {
        String[] args = new String[]{FOLDER_PATH};
        String expectedOutput = "folder1 folder2 folder3 test1.txt";
        assertEquals(expectedOutput, lsApplication.listFolderContent(false, false, args));
    }

    @Test
    public void testListFolderContent_withOneArgumentAndNoFlag_shouldReturnCorrectly() throws Exception {
        String[] args = new String[]{FOLDER1};
        String expectedOutput = "folder3 test1.txt";
        assertEquals(expectedOutput, lsApplication.listFolderContent(false, false, args));
    }

    @Test
    public void testListFolderContent_withMultipleArgumentsAndNoFlag_shouldReturnCorrectly() throws Exception {
        String[] args = new String[]{FOLDER1, FOLDER2};
        String expectedOutput = FOLDER1 + ":" + StringUtils.STRING_NEWLINE + "folder3 test1.txt" + StringUtils.STRING_NEWLINE + StringUtils.STRING_NEWLINE + FOLDER2 + ":" + StringUtils.STRING_NEWLINE + "folder4 test2.txt";
        assertEquals(expectedOutput, lsApplication.listFolderContent(false, false, args));
    }

    @Test
    public void testListFolderContent_withNoArgumentsAndFolderOnlyFlag_shouldReturnCorrectly() throws Exception {
        String[] args = new String[]{};
        String expectedOutput = ".";
        assertEquals(expectedOutput, lsApplication.listFolderContent(true, false, args));
    }

    @Test
    public void testListFolderContent_withNoArgumentsAndRecursiveFlag_shouldReturnCorrectly() throws Exception {
        String[] args = new String[]{FOLDER_PATH};
        String expectedOutput = FOLDER_PATH + ":" + StringUtils.STRING_NEWLINE + "folder1 folder2 folder3 test1.txt" + StringUtils.STRING_NEWLINE + StringUtils.STRING_NEWLINE
                + FOLDER1 + ":" + StringUtils.STRING_NEWLINE + "folder3 test1.txt" + StringUtils.STRING_NEWLINE + StringUtils.STRING_NEWLINE
                + FOLDER3_IN_FOLDER1 + ":" + StringUtils.STRING_NEWLINE + "test3.txt" + StringUtils.STRING_NEWLINE + StringUtils.STRING_NEWLINE
                + FOLDER2 + ":" + StringUtils.STRING_NEWLINE + "folder4 test2.txt" + StringUtils.STRING_NEWLINE + StringUtils.STRING_NEWLINE
                + FOLDER4_IN_FOLDER2 + ":" + StringUtils.STRING_NEWLINE + "test4.txt" + StringUtils.STRING_NEWLINE + StringUtils.STRING_NEWLINE
                + FOLDER3 + ":";
        assertEquals(expectedOutput, lsApplication.listFolderContent(false, true, args));
    }

    @Test
    public void testListFolderContent_withNoArgumentsAndBothFlags_shouldReturnCorrectly() throws Exception {
        String[] args = new String[]{};
        String expectedOutput = ".";
        assertEquals(expectedOutput, lsApplication.listFolderContent(true, true, args));
    }

    @Test
    public void testListFolderContent_withOneArgumentsAndFolderOnlyFlag_shouldReturnCorrectly() throws Exception {
        String[] args = new String[]{FOLDER1};
        String expectedOutput = FOLDER1;
        assertEquals(expectedOutput, lsApplication.listFolderContent(true, false, args));
    }

    @Test
    public void testListFolderContent_withOneArgumentsAndRecursiveFlag_shouldReturnCorrectly() throws Exception {
        String[] args = new String[]{FOLDER2};
        String expectedOutput = FOLDER2 + ":" + StringUtils.STRING_NEWLINE + "folder4 test2.txt" + StringUtils.STRING_NEWLINE + StringUtils.STRING_NEWLINE
                + FOLDER4_IN_FOLDER2 + ":" + StringUtils.STRING_NEWLINE + "test4.txt";
        assertEquals(expectedOutput, lsApplication.listFolderContent(false, true, args));
    }

    @Test
    public void testListFolderContent_withOneArgumentsAndBothFlags_shouldReturnCorrectly() throws Exception {
        String[] args = new String[]{FOLDER1};
        String expectedOutput = FOLDER1;
        assertEquals(expectedOutput, lsApplication.listFolderContent(true, true, args));
    }

    @Test
    public void testListFolderContent_withMultipleArgumentsAndFolderOnlyFlag_shouldReturnCorrectly() throws Exception {
        String[] args = new String[]{FOLDER1, FOLDER2};
        String expectedOutput = FOLDER1 + StringUtils.STRING_NEWLINE + FOLDER2;
        assertEquals(expectedOutput, lsApplication.listFolderContent(true, false, args));
    }

    @Test
    public void testListFolderContent_withMultipleArgumentsAndRecursiveFlag_shouldReturnCorrectly() throws Exception {
        String[] args = new String[]{FOLDER1, FOLDER2};
        String expectedOutput = FOLDER1 + ":" + StringUtils.STRING_NEWLINE + "folder3 test1.txt" + StringUtils.STRING_NEWLINE + StringUtils.STRING_NEWLINE
                + FOLDER3_IN_FOLDER1 + ":" + StringUtils.STRING_NEWLINE + "test3.txt" + StringUtils.STRING_NEWLINE + StringUtils.STRING_NEWLINE
                + FOLDER2 + ":" + StringUtils.STRING_NEWLINE + "folder4 test2.txt" + StringUtils.STRING_NEWLINE + StringUtils.STRING_NEWLINE
                + FOLDER4_IN_FOLDER2 + ":" + StringUtils.STRING_NEWLINE + "test4.txt";
        assertEquals(expectedOutput, lsApplication.listFolderContent(false, true, args));
    }

    @Test
    public void testListFolderContent_withMultipleArgumentsAndBothFlags_shouldReturnCorrectly() throws Exception {
        String[] args = new String[]{FOLDER1, FOLDER2};
        String expectedOutput = FOLDER1 + StringUtils.STRING_NEWLINE + FOLDER2;
        assertEquals(expectedOutput, lsApplication.listFolderContent(true, true, args));
    }

    @Test
    public void testListFolderContent_withOneArgumentAndDoesNotExist_shouldReturnCorrectly() {
        String[] args = new String[]{"folder5"};
        assertThrows(Exception.class, () -> {
            lsApplication.listFolderContent(true, true, args);
        });

        assertThrows(Exception.class, () -> {
            lsApplication.listFolderContent(false, false, args);
        });

        assertThrows(Exception.class, () -> {
            lsApplication.listFolderContent(true, false, args);
        });

        assertThrows(Exception.class, () -> {
            lsApplication.listFolderContent(false, true, args);
        });
    }

    @Test
    public void testListFolderContent_withMultipleArgumentsAndSomeOfThemDoesNotExist_shouldReturnCorrectly() {
        String[] args = new String[]{"folder5", "folder6", "folder2"};
        assertThrows(Exception.class, () -> {
            lsApplication.listFolderContent(true, true, args);
        });

        assertThrows(Exception.class, () -> {
            lsApplication.listFolderContent(false, false, args);
        });

        assertThrows(Exception.class, () -> {
            lsApplication.listFolderContent(true, false, args);
        });

        assertThrows(Exception.class, () -> {
            lsApplication.listFolderContent(false, true, args);
        });
    }
}
