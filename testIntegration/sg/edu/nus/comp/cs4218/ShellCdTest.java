package sg.edu.nus.comp.cs4218;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import sg.edu.nus.comp.cs4218.exception.CdException;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.IOException;
import java.nio.file.Path;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_SHELL_ARROW;

/**
 * Tests working directory change
 */
public class ShellCdTest extends ShellTest {

    static String temporaryDir;
    static String oriWorkingDir;
    static String nonExistentDir;

    /**
     * Inits directory for cd testing
     * @param temp temporary directory
     */
    @BeforeAll
    static void initTempDir(@TempDir Path temp) {
        temporaryDir = temp.toAbsolutePath().toString();
        oriWorkingDir = Environment.currentDirectory;
        Environment.currentDirectory = temp.toAbsolutePath().toString();
        nonExistentDir = temp.resolve("non-existent").toAbsolutePath().toString();
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
            "exit"
        };
        String[] expectedLines = {
                temporaryDir + CHAR_SHELL_ARROW
        };
        assertInputOutput(input, expectedLines);
    }

    @Test
    void cdRelative() {
        String[] input = {
                "cd " + temporaryDir,
                "exit"
        };
        String[] expectedLines = {
                temporaryDir
        };
    }

    @Test
    void cdNonExistent() throws IOException, ShellException {
        String[] input = {
                "cd " + temporaryDir,
                "exit"
        };
        String[] expectedLines = {
                temporaryDir + CHAR_SHELL_ARROW,
                String.format(CdException.ERR_IS_NOT_DIR, nonExistentDir)
        };
        assertInputOutput(input, expectedLines);
    }

    @Test
    void cdAbsolute() {

    }
}
