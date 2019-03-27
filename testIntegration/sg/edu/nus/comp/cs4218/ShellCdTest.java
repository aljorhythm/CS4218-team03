package sg.edu.nus.comp.cs4218;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import sg.edu.nus.comp.cs4218.exception.CdException;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static sg.edu.nus.comp.cs4218m1.TestUtils.CHAR_SHELL_ARROW;

/**
 * Tests working directory change
 */
public class ShellCdTest extends ShellTest {

    private static String temporaryDir;
    private static String oriWorkingDir;
    private static String nonExistentDir;
    private static String subDirName = "subDir";
    private static String subDirPath;
    private static String nonExistDirName = "non-existent";
    private static final String STR_EXIT = "exit";

    /**
     * Inits directory for cd testing
     * @param temp temporary directory
     */
    @BeforeAll
    static void initTempDir(@TempDir Path temp) {
        temporaryDir = temp.toAbsolutePath().toString();
        oriWorkingDir = Environment.currentDirectory;
        Environment.currentDirectory = temp.toAbsolutePath().toString();
        nonExistentDir = temp.resolve(nonExistDirName).toAbsolutePath().toString();
        subDirPath = temp.resolve(subDirName).toAbsolutePath().toString();
        File subDir = new File(subDirPath);
        subDir.mkdirs();
    }

    /**
     * Resets working directory after all tests
     */
    @AfterAll
    static void resetWorkingDir() {
        Environment.currentDirectory = oriWorkingDir;
    }

    /**
     * Shell should output working directory
     */
    @Test
    void initShell() throws IOException, ShellException {
        String[] input = {
            STR_EXIT
        };
        String[] expectedLines = {
                temporaryDir + CHAR_SHELL_ARROW
        };
        assertInputOutput(input, expectedLines);
    }

    @Test
    void cdRelative() throws IOException, ShellException {
        String[] input = {
                "cd " + subDirName,
                STR_EXIT
        };
        String[] expectedLines = {
                temporaryDir + CHAR_SHELL_ARROW,
                subDirPath + CHAR_SHELL_ARROW
        };
        assertInputOutput(input, expectedLines);
    }

    @Test
    void cdNonExistent() throws IOException, ShellException {
        String[] input = {
                "cd " + nonExistentDir,
                STR_EXIT
        };
        String[] expectedLines = {
                temporaryDir + CHAR_SHELL_ARROW + "cd: " + String.format("%s: No such file or directory", nonExistentDir),
                temporaryDir + CHAR_SHELL_ARROW
        };
        assertInputOutput(input, expectedLines);
    }

    @Test
    void cdAbsolute() throws IOException, ShellException {
        String[] input = {
                "cd " + temporaryDir,
                STR_EXIT
        };
        String[] expectedLines = {
                temporaryDir + CHAR_SHELL_ARROW,
                temporaryDir + CHAR_SHELL_ARROW
        };
        assertInputOutput(input, expectedLines);
    }
}
