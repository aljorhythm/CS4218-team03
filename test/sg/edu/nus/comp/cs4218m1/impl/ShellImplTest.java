package sg.edu.nus.comp.cs4218.impl;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHARSET_UTF8;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

class ShellImplTest {
    /**
     * Shell should exit by 2 seconds
     */
    @Test
    void shellRunAndExit() throws IOException {
        InputStream inputStream = IOUtils.stringToInputStream("exit" + STRING_NEWLINE);
        ShellImpl shell = new ShellImpl(inputStream, null);
        assertTimeoutPreemptively(ofSeconds(2), () -> {
            shell.run();
        });
    }

    /**
     * Test simple shell command
     */
    @Test
    void shellRunEcho() throws IOException, ShellException {
        InputStream inputStream = IOUtils.stringsToInputStream(new String[]{
                "echo abc", "exit"
        });
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ShellImpl shell = new ShellImpl(inputStream, outputStream);
        shell.run();
        String currentDirectory = Environment.currentDirectory;
        String expected = currentDirectory + ">abc" + STRING_NEWLINE + currentDirectory + ">";
        String actual = outputStream.toString(CHARSET_UTF8);
        assertEquals(expected, actual);
    }

    /**
     * Test simple shell command
     */
    @Test
    void shellRunPipe() throws IOException, ShellException {
        InputStream inputStream = IOUtils.stringsToInputStream(new String[]{
                "echo abcde;echo abc", "exit"
        });
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ShellImpl shell = new ShellImpl(inputStream, outputStream);
        shell.run();
        String currentDirectory = Environment.currentDirectory;
        String expected = currentDirectory + ">abcde" + STRING_NEWLINE + "abc" + STRING_NEWLINE + currentDirectory + ">";
        String actual = outputStream.toString(CHARSET_UTF8);
        assertEquals(expected, actual);
    }
}
