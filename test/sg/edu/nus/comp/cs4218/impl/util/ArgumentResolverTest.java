package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArgumentResolverTest extends DirectoryStructureTest {
    /**
     * Asserts resolution of one argument is correct
     *
     * @param arg
     * @param expectedArr
     * @throws ShellException
     */
    void assertResolveOneArgument(String arg, String... expectedArr) throws ShellException {
        ArgumentResolver argumentResolver = new ArgumentResolver();
        List<String> resolved = argumentResolver.resolveOneArgument(arg, new ApplicationRunner());
        List<String> expected = Arrays.asList(expectedArr);
        assertEquals(expected, resolved);
    }

    /**
     * Tests expansion of glob argument
     *
     * @throws ShellException
     */
    @Test
    void resolveOneArgumentGlobExpansionAbsolute() throws ShellException {
        assertResolveOneArgument(testRootDir + "/*", DirectoryStructureTest.allTestRootFiles);
    }

    /**
     * Tests expansion of glob argument
     *
     * @throws ShellException
     */
    @Test
    void resolveOneArgumentGlobExpansionAbsoluteWithNoise() throws ShellException {
        String arg = "asd " + testRootDir + "/*";
        assertResolveOneArgument(arg, arg);
    }

    /**
     * Tests expansion of glob argument
     *
     * @throws ShellException
     */
    @Test
    void resolveOneArgumentGlobExpansionRelativeAll(@TempDir Path dir) throws ShellException, IOException {
        String original = Environment.currentDirectory;
        Environment.currentDirectory = dir
                .toAbsolutePath()
                .toString();
        createTestFilesFolders(dir);
        assertResolveOneArgument("subdir/*", new String[]{
                "subdir/file1",
                "subdir/file2",
                "subdir/somedir",
                "subdir/subsubdir"
        });
        Environment.currentDirectory = original;
    }

    /**
     * Tests expansion of glob argument with multiple positions of asterisks
     *
     * @throws ShellException
     */
    @Test
    void resolveOneArgumentGlobExpansionRelativeComplex(@TempDir Path dir) throws ShellException, IOException {
        String original = Environment.currentDirectory;
        Environment.currentDirectory = dir
                .toAbsolutePath()
                .toString();
        createTestFilesFolders(dir);
        assertResolveOneArgument("subdir/subsub*/*1", new String[]{
                "subdir/subsubdir/file1", "subdir/subsubdir/file11"
        });
        Environment.currentDirectory = original;
    }

    static void createTestFilesFolders(Path dir) throws IOException {
        String file1 = dir + "/test1";
        String file2 = dir + "/test2";
        new File(file1).createNewFile();
        new File(file2).createNewFile();
        Path dir1 = Paths.get(dir + "/subdir");
        dir1
                .toFile()
                .mkdir();
        Path dir1File1 = dir1.resolve("file1");
        dir1File1
                .toFile()
                .createNewFile();
        Path dir1File2 = dir1.resolve("file2");
        dir1File2
                .toFile()
                .createNewFile();
        Path dir1dir1 = dir1.resolve("subsubdir");
        dir1dir1
                .toFile()
                .mkdir();
        Path dir1dir1file1 = dir1dir1.resolve("file1");
        dir1dir1file1
                .toFile()
                .createNewFile();
        Path dir1dir1file11 = dir1dir1.resolve("file11");
        dir1dir1file11
                .toFile()
                .createNewFile();
        Path dir1dir1file2 = dir1dir1.resolve("file2");
        dir1dir1file2
                .toFile()
                .createNewFile();

        Path dir1dir2 = dir1.resolve("somedir");
        dir1dir2
                .toFile()
                .mkdir();
    }
}
