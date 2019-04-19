package sg.edu.nus.comp.cs4218.bugfixes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.Shell;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class GlobDotTest {

    /**
     * Tests expansion and evaluate of command with "." argument
     * @param tempPath
     * @throws AbstractApplicationException
     * @throws IOException
     * @throws ShellException
     */
    @Test
    public void testDot(@TempDir Path tempPath) throws AbstractApplicationException, IOException, ShellException {
        String originalWorkingDirectory = Environment.currentDirectory;
        Environment.currentDirectory = tempPath.toAbsolutePath().toString();
        File dir = tempPath.toFile();

        String filename = "temp temp.txt";
        File newFile = new File(dir, filename);
        newFile.createNewFile();

        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        Shell shell = new ShellImpl(null, stdout);

        shell.parseAndEvaluate("ls .", stdout);

        Environment.currentDirectory = originalWorkingDirectory;
        assertEquals(filename + STRING_NEWLINE, stdout.toString());
    }
}
