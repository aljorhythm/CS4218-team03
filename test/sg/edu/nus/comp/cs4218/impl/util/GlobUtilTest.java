package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_ASTERISK;
import static sg.edu.nus.comp.cs4218m1.TestUtils.NON_EXISTENT_DIR;

public class GlobUtilTest extends DirectoryStructureTest {

    /**
     * Pattern does not match any, should not return any files
     *
     * @throws IOException
     */
    @Test
    void globTestDirectoryPatternNone() throws IOException {
        List<String> res = GlobUtil.glob(Paths.get(testRootDir), "suubdir*");
        Assertions.assertArrayEquals(new String[]{}, res.toArray(new String[]{}));
    }

    /**
     * Asserts glob all direct descendant files in test root
     *
     * @throws IOException
     */
    @Test
    void globTestDirectoryAll() throws IOException {
        List<String> res = GlobUtil.glob(Paths.get(testRootDir), STRING_ASTERISK);
        TestUtils.assertArrayEqualsList(allTestRootFiles, res);
    }

    /**
     * Asserts all direct descendants with "subdir" prefixes are returned
     *
     * @throws IOException
     */
    @Test
    void globTestDirectoryPatternSubDir() throws IOException {
        List<String> res = GlobUtil.glob(Paths.get(testRootDir), "subdir*");
        String[] expected = Stream
                .of(allTestRootFiles)
                .filter(s -> s.contains("subdir"))
                .toArray(String[]::new);
        Assertions.assertArrayEquals(expected, res.toArray(new String[]{}));
    }

    /**
     * Asserts all direct descendants with ".txt" suffix are returned
     *
     * @throws IOException
     */
    @Test
    void globTestDirectoryPatternTxt() throws IOException {
        List<String> res = GlobUtil.glob(Paths.get(testRootDir), "*.txt");
        String[] expected = Stream
                .of(rootTxtFiles)
                .filter(s -> s.endsWith(".txt"))
                .toArray(String[]::new);
        Assertions.assertArrayEquals(expected, res.toArray(new String[]{}));
    }

    /**
     * Asserts no files returned for pattern that does not match any
     *
     * @throws IOException
     */
    @Test
    void globTestSubdirectoriesNone() throws IOException {
        List<String> res = GlobUtil.glob(Paths.get(testRootDir), "*" + CHAR_FILE_SEP + "suubdir*");
        Assertions.assertArrayEquals(new String[]{}, res.toArray(new String[]{}));
    }

    /**
     * Asserts all subdirectory files and folders are returned
     *
     * @throws IOException
     */
    @Test
    void globTestSubdirectoriesAll() throws IOException {
        List<String> res = GlobUtil.glob(Paths.get(testRootDir), "*" + CHAR_FILE_SEP + "*");
        Assertions.assertArrayEquals(allSubDirFiles, res.toArray(new String[]{}));
    }

    /**
     * Asserts all subdirectory files and folders with "file" prefix are returned
     *
     * @throws IOException
     */
    @Test
    void globTestSubdirectoriesPrefix() throws IOException {
        List<String> res = GlobUtil.glob(Paths.get(testRootDir), "*" + CHAR_FILE_SEP + "file*");
        Assertions.assertArrayEquals(new String[]{subDir_filesub}, res.toArray(new String[]{}));
    }

    /**
     * Asserts all subdirectory files and folders with ".txt" suffix are retrieved
     *
     * @throws IOException
     */
    @Test
    void globTestSubdirectoriesAllTxt() throws IOException {
        List<String> res = GlobUtil.glob(Paths.get(testRootDir), "*" + CHAR_FILE_SEP +  "*.txt");
        String[] expected = Stream
                .of(allSubDirFiles)
                .filter(s -> s.endsWith(".txt"))
                .toArray(String[]::new);
        Assertions.assertArrayEquals(expected, res.toArray(new String[]{}));
    }

    /**
     * Non existent folder should return None
     */
    @Test
    void globNonExistent() throws IOException {
        List<String> res = GlobUtil.glob(Paths.get(NON_EXISTENT_DIR), STRING_ASTERISK);
        assertTrue(res.isEmpty());
    }

    /**
     * Test glob on unix like root
     */
    @Test
    void globUnixRoot() throws IOException {
        if (!SystemUtil.isWindows()) {
            Iterable<Path> roots = FileSystems
                    .getDefault()
                    .getRootDirectories();
            for (Path root : roots) {
                List<String> res = GlobUtil.glob(root, "*ome");
                String[] expected = new String[]{"/home"};
                Assertions.assertArrayEquals(expected, res.toArray(new String[]{}));
            }
        }
    }
}