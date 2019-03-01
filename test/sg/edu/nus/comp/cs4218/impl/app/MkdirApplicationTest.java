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
    public static void deleteDirectory(File directory, File[] files) {
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
        if(directory != null) directory.delete();
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
        mkdirApplication.createFolder(pathToTestDir + "mkdirTest");
        assertTrue(new File(pathToTestDir + "mkdirTest").exists());
    }

    @Test
    void testCreateFolderTwoNewDirectoryInputSuccess() throws MkdirException {
        mkdirApplication.createFolder(pathToTestDir + "mkdirTest1", pathToTestDir + "mkdirTest2");
        assertTrue(new File(pathToTestDir + "mkdirTest1").exists());
        assertTrue(new File(pathToTestDir + "mkdirTest2").exists());
    }

    @Test
    void testCreateFolderDirectoryInDirectoryInputSuccess() throws MkdirException {
        mkdirApplication.createFolder(pathToTestDir + "mkdirTestParent",
                pathToTestDir + "mkdirTestParent" + File.separator + "mkdirTestChild");
        assertTrue(new File(pathToTestDir + "mkdirTestParent" + File.separator + "mkdirTestChild").exists());
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
        args[0] = pathToTestDir + "mkdirTest";
        mkdirApplication.run(args, null, null);
        assertTrue(new File(pathToTestDir + "mkdirTest").exists());
    }

    @Test
    void testRunTwoNewDirectoryInputSuccess() throws MkdirException {
        String[] args = new String[2];
        args[0] = pathToTestDir + "mkdirTest1";
        args[1] = pathToTestDir + "mkdirTest2";
        mkdirApplication.run(args, null, null);
        assertTrue(new File(pathToTestDir + "mkdirTest1").exists());
        assertTrue(new File(pathToTestDir + "mkdirTest2").exists());
    }

    @Test
    void testRunDirectoryInDirectoryInputSuccess() throws MkdirException {
        String[] args = new String[2];
        args[0] = pathToTestDir + "mkdirTestParent";
        args[1] = pathToTestDir + "mkdirTestParent" + File.separator + "mkdirTestChild";
        mkdirApplication.run(args, null, null);
        assertTrue(new File(pathToTestDir + "mkdirTestParent" + File.separator + "mkdirTestChild").exists());
    }


}
