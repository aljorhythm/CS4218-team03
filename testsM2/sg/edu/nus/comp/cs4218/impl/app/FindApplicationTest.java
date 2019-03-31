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
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;

class FindApplicationTest {

    private static FindApplication application;
    private static InputStream mockIs;
    private static OutputStream mockOs;
    private static ByteArrayOutputStream mockBos;
    private static ArrayList<String> testFilePath;

    private static final String FOLDER_PATH = TestUtils.pathToTestDataSubdir("findTestDirTDD");
    private static final String BASIC_FOLDER = FOLDER_PATH + File.separator + "testFolder";
    private static final String ANOTHER_FOLDER = FOLDER_PATH + File.separator + "anotherTestFolder";
    private static final String EMPTY_FOLDER = FOLDER_PATH + File.separator + "emptyFolder";
    private static final String NESTED_FOLDER = "nestedFolder";
    private static final String NUMERIC_FOLDER = "123";
    private static final String INVALID_FOLDER = "nonExistentFolder";


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
        File baseFolder = new File(EMPTY_FOLDER);
        if (baseFolder.mkdirs()) {
        }
        baseFolder = new File(BASIC_FOLDER);
        File baseNestedFolder = new File(baseFolder.getPath() + File.separator + NESTED_FOLDER);
        if (baseNestedFolder.mkdirs()) {
            addFileToFolder(baseNestedFolder, ANOTHER_FILE_NAME);
        }
        File numericFolder = new File(baseFolder.getPath() + File.separator + NUMERIC_FOLDER);
        if (numericFolder.mkdirs()) {
            addFileToFolder(numericFolder, NUMERIC_FILE_NAME);
        }
        baseFolder = new File(ANOTHER_FOLDER);
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
        File baseFolder = new File(BASIC_FOLDER);
        File anotherBaseFolder = new File(ANOTHER_FOLDER);
        File baseNestedFolder = new File(baseFolder.getPath() + File.separator + NESTED_FOLDER);
        File numericFolder = new File(baseFolder.getPath() + File.separator + NUMERIC_FOLDER);
        File emptyFolder = new File(EMPTY_FOLDER);

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

    /** Hackathon Start **/

    @Test
    void testRunOnlyWithoutFilterInvalid() {
        String[] args = new String[]{BASIC_FOLDER, ANOTHER_FOLDER, BASIC_FILE_NAME};

        Exception actualException = assertThrows(FindException.class, () -> application.run(args, mockIs, mockOs));
        assertEquals(INVALID_SYN_MSG, actualException.getMessage());
    }

    /** Hackathon End **/

}

