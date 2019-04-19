package sg.edu.nus.comp.cs4218.bugfixes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.app.CdApplication;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CdTest {
    CdApplication cdApp;

    /**
     * Initializes application for each test
     */
    @BeforeEach
    public void initApplication(){
        cdApp = new CdApplication();
    }

    @Test
    public void runCdThrowsExceptionMultipleArguments() {
        ShellImpl shell = new ShellImpl();
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        assertThrows(Exception.class, () -> shell.parseAndEvaluate("cd a b c", stdout));
    }

    @Test
    public void runCdThrowsExceptionNoSuchDir() {
        ShellImpl shell = new ShellImpl();
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        assertThrows(Exception.class, () -> shell.parseAndEvaluate("cd a", stdout));
    }
}
