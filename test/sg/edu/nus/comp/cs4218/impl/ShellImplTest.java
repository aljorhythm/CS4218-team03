package sg.edu.nus.comp.cs4218.impl;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHARSET_UTF8;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

class ShellImplTest {
    /**
     * Shell should exit by 2 seconds
     */
    @Test
    void shell_run_Exit() throws IOException {
        InputStream inputStream = IOUtils.stringToInputStream("exit" + STRING_NEWLINE);
        ShellImpl shell = new ShellImpl(inputStream, System.out);
        assertTimeout(ofSeconds(2), () -> {
            shell.run();
        });
    }

    /**
     * Test simple shell command
     */
    @Test
    void shell_run_echo() throws IOException, ShellException {
        InputStream inputStream = IOUtils.stringsToInputStream(new String[] {
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
}