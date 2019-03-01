package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.MkdirException;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class MkdirApplicationTest {
    MkdirApplication mkdirApplication;
    String pathToTestDir = System.getProperty("user.dir") + File.separator + "test" + File.separator +
            "sg" + File.separator + "edu" + File.separator + "nus" + File.separator + "comp" + File.separator +
            "cs4218" + File.separator + "inputFiles" + File.separator;
    String tempDir = pathToTestDir + "mkdirTest";
    String tempDir2 = pathToTestDir + "mkdirTest2";
    String tempParent = pathToTestDir + "mkdirTestParent";
    String tempChild = tempParent + File.separator + "mkdirTestChild";

    @BeforeEach
    void setUp() {
        mkdirApplication = new MkdirApplication();
    }

    @AfterEach
    void tearDown() throws Exception {
        deleteDirectory(null, new File(pathToTestDir).listFiles());
    }

    /**
     *
     * @param directory
     * @param files
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
    void testCreateFolderNullInputFailure() {
        assertThrows(MkdirException.class, () -> {mkdirApplication.createFolder(null);});
    }

    @Test
    void testCreateFolderEmptyInputSuccess() throws MkdirException {
        mkdirApplication.createFolder("");
        assertEquals(0, new File(pathToTestDir).list().length);
    }

    @Test
    void testCreateFolderOneNewDirectoryInputSuccess() throws MkdirException {
        mkdirApplication.createFolder(tempDir);
        assertTrue(new File(tempDir).exists());
    }

    @Test
    void testCreateFolderTwoNewDirectoryInputSuccess() throws MkdirException {
        mkdirApplication.createFolder(tempDir, tempDir2);
        assertTrue(new File(tempDir).exists());
        assertTrue(new File(tempDir2).exists());
    }

    @Test
    void testCreateFolderDirectoryInDirectoryInputSuccess() throws MkdirException {
        mkdirApplication.createFolder(tempParent, tempChild);
        assertTrue(new File(tempChild).exists());
    }

    @Test
    void testRunNullInputFailure() {
        assertThrows(MkdirException.class, () -> {mkdirApplication.run(null, null, null);});
    }

    @Test
    void testRunEmptyInputSuccess() throws MkdirException {
        String[] args = new String[0];
        mkdirApplication.run(args, null, null);
        assertEquals(0, new File(pathToTestDir).list().length);
    }

    @Test
    void testRunOneNewDirectoryInputSuccess() throws MkdirException {
        String[] args = new String[1];
        args[0] = tempDir;
        mkdirApplication.run(args, null, null);
        assertTrue(new File(tempDir).exists());
    }

    @Test
    void testRunTwoNewDirectoryInputSuccess() throws MkdirException {
        String[] args = new String[2];
        args[0] = tempDir;
        args[1] = tempDir2;
        mkdirApplication.run(args, null, null);
        assertTrue(new File(tempDir).exists());
        assertTrue(new File(tempDir2).exists());
    }

    @Test
    void testRunDirectoryInDirectoryInputSuccess() throws MkdirException {
        String[] args = new String[2];
        args[0] = tempParent;
        args[1] = tempChild;
        mkdirApplication.run(args, null, null);
        assertTrue(new File(tempChild).exists());
    }
}
