package sg.edu.nus.comp.cs4218.bugfixes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.app.MkdirApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class MkdirTest {
    MkdirApplication mkdirApp;

    /**
     * Initializes application for each test
     */
    @BeforeEach
    public void initApplication(){
        mkdirApp = new MkdirApplication();
    }

    @Test
    public void
    mkdirTestThrowExceptionExistingDir() {
        ShellImpl shell = new ShellImpl();
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        assertThrows(Exception.class, () -> shell.parseAndEvaluate(
                "mkdir resources/mkdirTestDir", stdout));
    }

    @Test
    public void
    mkdirTestThrowExceptionInvalidParentDir() {
        ShellImpl shell = new ShellImpl();
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        assertThrows(Exception.class, () -> shell.parseAndEvaluate(
                "mkdir resources/mk" + File.separator + "new", stdout));
    }

}
