package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.IOUtils.*;
import static sg.edu.nus.comp.cs4218m1.TestUtils.CHAR_REDIR_INPUT;
import static sg.edu.nus.comp.cs4218m1.TestUtils.CHAR_REDIR_OUTPUT;

class IORedirectionHandlerTest {
    private static String tempDir;
    private static Path tempDirPath;
    private static Path inputPath;
    private static String inputName = "input.txt";
    private static String outputName = "output.txt";
    private static MockArgumentResolver mockArgResolver = new MockArgumentResolver();
    private static String inputText = TestUtils.generateRandomString(1234);
    private static String outputText = TestUtils.generateRandomString(123);
    private static Path outputPath;
    private ApplicationRunner mockAppRunner;

    public static class MockArgumentResolver extends ArgumentResolver {

        @Override
        public List<String> parseArguments(List<String> argsList, ApplicationRunner appRunner) throws ShellException {
            return argsList;
        }

        @Override
        public List<String> resolveOneArgument(String arg, ApplicationRunner appRunner) throws ShellException {
            return Arrays.asList(new String[]{arg});
        }
    }

    /**
     * Create temp directory and files
     *
     * @param tempDirPath
     * @throws IOException
     */
    @BeforeAll
    static void initTempDir(@TempDir Path tempDirPath) throws IOException {
        IORedirectionHandlerTest.tempDirPath = tempDirPath;
        IORedirectionHandlerTest.tempDir = tempDirPath
                .toAbsolutePath()
                .toString();
        inputPath = tempDirPath.resolve(inputName);
        outputPath = tempDirPath.resolve(outputName);
        IOUtils.createAndWriteToFile(inputPath, inputText);
    }

    /**
     * Extract input and keep no redirection args
     * @throws IOException
     */
    @Test
    void extractRedirInput() throws IOException, ShellException, AbstractApplicationException {
        String[] args = {"print", "" + CHAR_REDIR_INPUT, inputPath.toString()};
        List<String> argsList = Arrays.asList(args);
        IORedirectionHandler handler = new IORedirectionHandler(argsList, null, null, mockArgResolver);
        handler.extractRedirOptions(mockAppRunner);
        FileInputStream redirInputStream = (FileInputStream) handler.getInputStream();
        String actual = stringFromInputStream(redirInputStream);
        closeInputStream(redirInputStream);
        assertEquals(inputText, actual);
        String[] noRedirectArgs = {"print"};
        assertArrayEquals(noRedirectArgs, handler.getNoRedirArgsList().toArray(new String[]{}));
    }

    /**
     * Extract output stream
     * @throws IOException
     */
    @Test
    void extractRedirOutputFileStream() throws IOException, ShellException, AbstractApplicationException {
        String[] args = {"print ", "" + CHAR_REDIR_OUTPUT, outputPath.toString()};
        List<String> argsList = Arrays.asList(args);
        IORedirectionHandler handler = new IORedirectionHandler(argsList, null, null, mockArgResolver);
        handler.extractRedirOptions(mockAppRunner);
        OutputStream outputStream = handler.getOutputStream();
        outputStream.write(outputText.getBytes());
        closeOutputStream(outputStream);
        String actual = new String(Files.readAllBytes(outputPath));
        assertEquals(outputText, actual);
    }
}