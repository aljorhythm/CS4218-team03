package sg.edu.nus.comp.cs4218;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.LsException;
import sg.edu.nus.comp.cs4218.exception.MkdirException;
import sg.edu.nus.comp.cs4218.impl.app.LsApplication;
import sg.edu.nus.comp.cs4218.impl.app.MkdirApplication;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppAppTest {

    MkdirApplication mkdirApplication;
    LsApplication lsApplication;
    String pathToTestDir = TestUtils.TEST_DATA_DIR + File.separator + "appAppTestDir" + File.separator;
    String tempDir = pathToTestDir + "lsMkdirTest";

    @BeforeEach
    void setUp() {
        mkdirApplication = new MkdirApplication();
        lsApplication = new LsApplication();
    }

    @AfterEach
    void tearDown() throws IOException {
        File tempFile = new File(tempDir);
        deleteDirectory(tempFile, tempFile.listFiles());
    }

    /**
     * Helper function used to delete the a directory and its content.
     * @param directory The directory to be deleted.
     * @param files The content to be deleted.
     */
    public static void deleteDirectory(File directory, File... files) {
        if(null != files){
            for(int i=0; i< files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteDirectory(files[i], files[i].listFiles());
                }
                else {
                    files[i].delete();
                }
            }
        }
        if(directory != null) {
            directory.delete();
        }
    }

    @Test
    void testLsMkdirCheckNewFolderCreatedSuccess() throws LsException, MkdirException {
        String expectedOutput = "ls-mkdir.txtls-mkdir.txt lsMkdirTest";
        String[] lsArgs = new String[]{pathToTestDir};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        lsApplication.run(lsArgs, null, baos);

        String[] mkdirArgs = new String[]{tempDir};
        mkdirApplication.run(mkdirArgs, null, null);
        lsApplication.run(lsArgs, null, baos);

        byte[] byteArray = baos.toByteArray();
        assertEquals(expectedOutput, new String(byteArray));
    }

}
