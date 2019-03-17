/**
 * From Team 02
 */
package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sg.edu.nus.comp.cs4218.exception.FindException;
import sg.edu.nus.comp.cs4218.impl.app.FindApplication;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class FindApplicationTest {

    private static FindApplication application;
    private static InputStream mockIs;
    private static OutputStream mockOs;
    private static ByteArrayOutputStream mockBos;
    private static ArrayList<String> testFilePath;

    private static final String FOLDER_PATH = TestUtils.pathToTestDataSubdir("findTestDirTDD");
    private static final String BASIC_FOLDER = "testFolder";
    private static final String ANOTHER_FOLDER = "anotherTestFolder";
    private static final String NESTED_FOLDER = "nestedFolder";
    private static final String NUMERIC_FOLDER = "123";
    private static final String INVALID_FOLDER = "nonExistentFolder";
    private static final String EMPTY_FOLDER = "emptyFolder";

    private static final String BASIC_FILE_NAME = "test.txt";
    private static final String ANOTHER_FILE_NAME = "anothertest.txt";
    private static final String NUMERIC_FILE_NAME = "123.txt";
    private static final String INVALID_FILE_NAME = "nonExistentFile.txt";

    private static final String FIND_FILTER = "-name";
    private static final String OUTPUT_ERROR_MSG = "find: output stream is null";
    private static final String INVALID_SYN_MSG = "find: Invalid syntax. Check that an argument is given after -name";
    private static final String NOT_FOUND_MSG = ": No such file or directory";

    @BeforeAll
    static void setUpAll() throws IOException {
        testFilePath = new ArrayList<>();
        //Create folders and files for testing
        File baseFolder = new File(FOLDER_PATH + File.separator + EMPTY_FOLDER);
        if (baseFolder.mkdirs()) {
        }
        baseFolder = new File(FOLDER_PATH + File.separator + BASIC_FOLDER);
        File baseNestedFolder = new File(baseFolder.getPath() + File.separator + NESTED_FOLDER);
        if (baseNestedFolder.mkdirs()) {
            addFileToFolder(baseNestedFolder, ANOTHER_FILE_NAME);
        }
        File numericFolder = new File(baseFolder.getPath() + File.separator + NUMERIC_FOLDER);
        if (numericFolder.mkdirs()) {
            addFileToFolder(numericFolder, NUMERIC_FILE_NAME);
        }
        baseFolder = new File(FOLDER_PATH + File.separator + ANOTHER_FOLDER);
        if (baseFolder.mkdirs()) {
            addFileToFolder(baseFolder, ANOTHER_FILE_NAME);
        }
    }

    @AfterAll
    static void tearDownAll() {
        //Delete all files
        for (String path : testFilePath) {
            File file = new File(path);
            file.delete();
        }
        //Delete all folders, starting from nested folders
        File baseFolder = new File(FOLDER_PATH + File.separator + BASIC_FOLDER);
        File anotherBaseFolder = new File(FOLDER_PATH + File.separator + ANOTHER_FOLDER);
        File baseNestedFolder = new File(baseFolder.getPath() + File.separator + NESTED_FOLDER);
        File numericFolder = new File(baseFolder.getPath() + File.separator + NUMERIC_FOLDER);
        File emptyFolder = new File(FOLDER_PATH + File.separator + EMPTY_FOLDER);

        emptyFolder.delete();
        numericFolder.delete();
        baseNestedFolder.delete();
        anotherBaseFolder.delete();
    }

    @BeforeEach
    void setUp() {
        application = spy(new FindApplication());
        mockIs = Mockito.mock(InputStream.class);
        mockOs = Mockito.mock(OutputStream.class);
        mockBos = Mockito.mock(ByteArrayOutputStream.class);
    }

    //Helper function to create a file using given fileName and add to the current folder
    private static void addFileToFolder(File folder, String fileName) throws IOException {
        File file = new File(folder.getPath(), fileName);
        if (file.createNewFile()) {
            testFilePath.add(file.getPath());
        }
    }

    @Test
    void testFindSingleFolderContentFileExists() throws Exception {
        String expectedResult = BASIC_FOLDER + File.separator + BASIC_FILE_NAME;

        assertEquals(expectedResult, application.findFolderContent(BASIC_FILE_NAME, BASIC_FOLDER));
    }

    @Test
    void testFindMultipleFoldersContentSameFileExists() throws Exception {
        String[] folders = new String[]{BASIC_FOLDER, ANOTHER_FOLDER};
        String expectedResult = ANOTHER_FOLDER + File.separator + ANOTHER_FILE_NAME + StringUtils.STRING_NEWLINE +
                BASIC_FOLDER + File.separator + NESTED_FOLDER + File.separator + ANOTHER_FILE_NAME;

        assertEquals(expectedResult, application.findFolderContent(ANOTHER_FILE_NAME, folders));
    }

    @Test
    void testFindMultipleFoldersContentOnlyOneContainsFile() throws Exception {
        String[] folders = new String[]{BASIC_FOLDER, ANOTHER_FOLDER, EMPTY_FOLDER};
        String expectedResult = BASIC_FOLDER + File.separator + NUMERIC_FILE_NAME + StringUtils.STRING_NEWLINE +
                BASIC_FOLDER + File.separator + NUMERIC_FOLDER + File.separator + NUMERIC_FILE_NAME;

        assertEquals(expectedResult, application.findFolderContent(NUMERIC_FILE_NAME, folders));
    }

    @Test
    void testFindMultipleFoldersContentFileNotExists() throws Exception {
        String[] folders = new String[]{BASIC_FOLDER, ANOTHER_FOLDER, EMPTY_FOLDER};
        String expectedResult = "";

        assertEquals(expectedResult, application.findFolderContent(INVALID_FILE_NAME, folders));
    }

    @Test
    void testFindSingleFolderContentMultipleFileExists() throws Exception {
        String expectedResult = BASIC_FOLDER + File.separator + NUMERIC_FILE_NAME + StringUtils.STRING_NEWLINE +
                BASIC_FOLDER + File.separator + NUMERIC_FOLDER + File.separator + NUMERIC_FILE_NAME;

        assertEquals(expectedResult, application.findFolderContent(NUMERIC_FILE_NAME, BASIC_FOLDER));
    }

    @Test
    void testFindFolderContentFileExistsUsingRegex() throws Exception {
        String fileRegex = ".+" + BASIC_FILE_NAME;
        String expectedResult = BASIC_FOLDER + File.separator + NESTED_FOLDER +
                File.separator + ANOTHER_FILE_NAME;

        assertEquals(expectedResult, application.findFolderContent(fileRegex, BASIC_FOLDER));
    }

    @Test
    void testFindFolderContentFileNotExistsUsingRegex() throws Exception {
        String fileRegex = "test.[txt]+";
        String expectedResult = "";
        //Exception actualException = assertThrows(FindException.class, () -> application.findFolderContent(fileRegex, ANOTHER_FOLDER));

        assertEquals(expectedResult, application.findFolderContent(fileRegex, ANOTHER_FOLDER));
    }

    @Test
    void testFindFolderNotExistsContent() throws Exception {
        String expectedResult = "find: " + INVALID_FOLDER + NOT_FOUND_MSG;

        assertEquals(expectedResult, application.findFolderContent(BASIC_FILE_NAME, INVALID_FOLDER));
    }

    @Test
    void testRunOnlyWithoutFilterInvalid() {
        String[] args = new String[]{BASIC_FOLDER, ANOTHER_FOLDER, BASIC_FILE_NAME};

        Exception actualException = assertThrows(FindException.class, () -> application.run(args, mockIs, mockOs));
        assertEquals(INVALID_SYN_MSG, actualException.getMessage());
    }

    @Test
    void testRunOnlyWithoutFolderSpecifiedInvalid() {
        String[] args = new String[]{FIND_FILTER, BASIC_FILE_NAME};

        Exception actualException = assertThrows(FindException.class, () -> application.run(args, mockIs, mockOs));
        assertEquals(INVALID_SYN_MSG, actualException.getMessage());
    }

    @Test
    void testRunOnlyWithoutFileSpecifiedInvalid() {
        String[] args = new String[]{BASIC_FOLDER, ANOTHER_FOLDER, FIND_FILTER};

        Exception actualException = assertThrows(FindException.class, () -> application.run(args, mockIs, mockOs));
        assertEquals(INVALID_SYN_MSG, actualException.getMessage());
    }

    @Test
    void testRunWithoutFolderAndFileSpecifiedInvalid() {
        String[] args = new String[]{FIND_FILTER};

        Exception actualException = assertThrows(FindException.class, () -> application.run(args, mockIs, mockOs));
        assertEquals(INVALID_SYN_MSG, actualException.getMessage());
    }

    @Test
    void testRunWithoutFolderAndFilterSpecifiedInvalid() {
        String[] args = new String[]{NUMERIC_FILE_NAME};

        Exception actualException = assertThrows(FindException.class, () -> application.run(args, mockIs, mockOs));
        assertEquals(INVALID_SYN_MSG, actualException.getMessage());
    }

    @Test
    void testRunWithoutFileAndFilterSpecifiedInvalid() {
        String[] args = new String[]{EMPTY_FOLDER};

        Exception actualException = assertThrows(FindException.class, () -> application.run(args, mockIs, mockOs));
        assertEquals(INVALID_SYN_MSG, actualException.getMessage());
    }

    @Test
    void testRunWithoutAnyArgumentInvalid() {
        String[] args = new String[]{};

        Exception actualException = assertThrows(FindException.class, () -> application.run(args, mockIs, mockOs));
        assertEquals(INVALID_SYN_MSG, actualException.getMessage());
    }

    @Test
    void testRunWithSingleFolderSingleFileValid() throws Exception {
        String[] args = new String[]{BASIC_FOLDER, FIND_FILTER, BASIC_FILE_NAME};

        application.run(args, mockIs, mockOs);
        verify(application, Mockito.times(1))
                .findFolderContent(BASIC_FILE_NAME, BASIC_FOLDER);
    }

    @Test
    void testRunWithMultipleFoldersSingleFileValid() throws Exception {
        String[] args = new String[]{BASIC_FOLDER, ANOTHER_FOLDER, EMPTY_FOLDER,
                FIND_FILTER, BASIC_FILE_NAME};

        application.run(args, mockIs, mockOs);
        verify(application, Mockito.times(1)).findFolderContent(BASIC_FILE_NAME,
                BASIC_FOLDER, ANOTHER_FOLDER, EMPTY_FOLDER);
    }

    @Test
    void testRunWithMultipleFoldersMultipleFilesInvalid() {
        String[] args = new String[]{BASIC_FOLDER, ANOTHER_FOLDER, FIND_FILTER, BASIC_FILE_NAME,
                ANOTHER_FILE_NAME};

        Exception actualException = assertThrows(FindException.class, () -> application.run(args, mockIs, mockOs));
        assertEquals(INVALID_SYN_MSG, actualException.getMessage());
    }

    @Test
    void testRunWithSingleFolderMultipleFilesInvalid() {
        String[] args = new String[]{EMPTY_FOLDER, FIND_FILTER, BASIC_FILE_NAME,
                ANOTHER_FILE_NAME};

        Exception actualException = assertThrows(FindException.class, () -> application.run(args, mockIs, mockOs));
        assertEquals(INVALID_SYN_MSG, actualException.getMessage());
    }

    @Test
    void testRunWithNullStdoutInvalid() {
        String[] args = new String[]{ANOTHER_FOLDER, FIND_FILTER, ANOTHER_FILE_NAME};

        Exception actualException = assertThrows(FindException.class, () ->
                application.run(args, mockIs, null));
        assertEquals(actualException.getMessage(), OUTPUT_ERROR_MSG);
    }

    @Test
    void testRunWithNullStdinValid() throws Exception {
        String[] args = new String[]{BASIC_FOLDER, FIND_FILTER, BASIC_FILE_NAME};

        application.run(args, null, mockOs);
        verify(application, Mockito.times(1)).findFolderContent(BASIC_FILE_NAME,
                BASIC_FOLDER);
    }

    /*********************************************
     * Set of test cases to verify output result
     *********************************************/
    @Test
    void testRunOutputFileLocationResult() throws Exception {
        String[] args = new String[]{BASIC_FOLDER, FIND_FILTER, NUMERIC_FILE_NAME};
        String expectedResult = BASIC_FOLDER + File.separator + NUMERIC_FILE_NAME + StringUtils.STRING_NEWLINE +
                BASIC_FOLDER + File.separator + NUMERIC_FOLDER + File.separator + NUMERIC_FILE_NAME +
                StringUtils.STRING_NEWLINE;
        mockBos = new ByteArrayOutputStream();

        application.run(args, mockIs, mockBos);
        assertEquals(expectedResult, new String(mockBos.toByteArray()));
    }

    @Test
    void testRunOutputEmptyResult() throws Exception {
        String[] args = new String[]{BASIC_FOLDER, FIND_FILTER, INVALID_FILE_NAME};
        String expectedResult = StringUtils.STRING_NEWLINE;
        mockBos = new ByteArrayOutputStream();

        application.run(args, mockIs, mockBos);
        assertEquals(expectedResult, new String(mockBos.toByteArray()));
    }

    @Test
    void testRunOutputFolderNotExistsResult() throws Exception {
        String[] args = new String[]{INVALID_FOLDER, FIND_FILTER, BASIC_FILE_NAME};
        String expectedResult = "find: " + INVALID_FOLDER + NOT_FOUND_MSG + StringUtils.STRING_NEWLINE;
        mockBos = new ByteArrayOutputStream();

        application.run(args, mockIs, mockBos);
        assertEquals(expectedResult, new String(mockBos.toByteArray()));
    }

    @Test
    void testRunOutputMixedResults() throws Exception {
        String[] args = new String[]{INVALID_FOLDER, BASIC_FOLDER, FIND_FILTER, BASIC_FILE_NAME};
        String expectedResult = "find: " + INVALID_FOLDER + NOT_FOUND_MSG + StringUtils.STRING_NEWLINE +
                BASIC_FOLDER + File.separator + BASIC_FILE_NAME + StringUtils.STRING_NEWLINE;
        mockBos = new ByteArrayOutputStream();

        application.run(args, mockIs, mockBos);
        assertEquals(expectedResult, new String(mockBos.toByteArray()));
    }
}

