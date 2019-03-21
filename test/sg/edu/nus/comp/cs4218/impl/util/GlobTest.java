package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class GlobTest extends DirectoryStructureTest {

    @Test
    void glob() throws IOException {
        List<String> res = Glob.glob(Paths.get(testDir), "*");
        Assertions.assertEquals(allFiles, res.toArray(new String[]{}));
    }

    @Test
    void globDirectory() throws IOException {
        List<String> res = Glob.glob(Paths.get(testDir), "*");
        Assertions.assertArrayEquals(allFiles, res.toArray(new String[]{}));
    }

    @Test
    void globDirectoryAndFiles() {

    }

}