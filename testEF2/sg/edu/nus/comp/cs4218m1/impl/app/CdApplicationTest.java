package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.CdException;
import sg.edu.nus.comp.cs4218.impl.app.CdApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.app.MkdirApplicationTest.deleteDirectory;

class CdApplicationTest {
    CdApplication cdApplication;
    String pathToTestDir = System.getProperty("user.dir") + File.separator + "testEF2" + File.separator +
            "sg" + File.separator + "edu" + File.separator + "nus" + File.separator + "comp" + File.separator +
            "cs4218m1" + File.separator + "cdTestDir";
    String pathNoFile = pathToTestDir  + File.separator + "cdEmpty";

    /**
     * create the aim file
     */
    @BeforeEach
    void setup() throws IOException {
        cdApplication = new CdApplication();
        deleteDirectory(null, new File(pathToTestDir).listFiles());
        File file = new File(pathToTestDir);
        file.mkdirs();
    }

    @AfterEach
    void tearDown() throws IOException {
        deleteDirectory(null, new File(pathToTestDir).listFiles());
        File f = new File(pathToTestDir + File.separator + "EmptyFileForGitTracking.txt");
        f.createNewFile();
    }

    /**
     * TODO
     * test if the cd works
     * @throws CdException
     */
    @Test
    void cd_exist_file() throws CdException {
        cdApplication.changeToDirectory(pathToTestDir);
        String currentDir = Paths.get("").toAbsolutePath().toString();
        assertEquals(currentDir,pathToTestDir);
    }

    /**
     * TODO
     * test if the cd throw an exception when meeting a fake path
     */
    @Test
    void cd_empty_file(){
        assertThrows(CdException.class, () -> {cdApplication.changeToDirectory(pathNoFile);});
    }
}
