package sg.edu.nus.comp.cs4218;

import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.IOUtils.stringsToInputStream;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class ShellTest {
    /**
     * Tests running of shell with input and output
     */
    void assertInputOutput(String[] commands, String... expectedLines) throws IOException, ShellException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream inputStream = stringsToInputStream(commands);
        Shell shell = new ShellImpl(inputStream, outputStream);
        shell.run();
        String actual = outputStream.toString();
        String expected = String.join(STRING_NEWLINE, expectedLines);
        assertEquals(expected, actual);
    }
}
