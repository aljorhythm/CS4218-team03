package sg.edu.nus.comp.cs4218.bugfixes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.GrepException;
import sg.edu.nus.comp.cs4218.impl.app.GrepApplication;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class GrepTest {
    GrepApplication grepApp;

    /**
     * Initializes application for each test
     */
    @BeforeEach
    public void initApplication() {
        grepApp = new GrepApplication();
    }

    @Test
    public void runInvalidFlagDash() throws IOException {
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        InputStream stdin = IOUtils.stringToInputStream("");
        String[] args = {"-c", "-i", "-", ".*LINE.*", "file1"};
        assertThrows(GrepException.class, () -> grepApp.run(args, stdin, stdout));
    }

    @Test
    public void runInvalidFlagAfterValidOne() throws IOException {
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        InputStream stdin = IOUtils.stringToInputStream("");
        String[] args = {"-ia", ".*LINE.*", "file1"};
        assertThrows(GrepException.class, () ->
                grepApp.run(args, stdin, stdout), "");
    }

}
