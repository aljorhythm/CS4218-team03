package sg.edu.nus.comp.cs4218.bugfixes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.PwdException;
import sg.edu.nus.comp.cs4218.exception.SortException;
import sg.edu.nus.comp.cs4218.impl.app.DateApplication;
import sg.edu.nus.comp.cs4218.impl.app.PwdApplication;
import sg.edu.nus.comp.cs4218.impl.app.SortApplication;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class NullArgsTest {
    PwdApplication pwdApp;
    SortApplication sortApp;
    DateApplication dateApp;

    /**
     * Initializes application for each test
     */
    @BeforeEach
    public void initApplication(){
        pwdApp = new PwdApplication();
        sortApp = new SortApplication();
        dateApp = new DateApplication();
    }

    // pwd with null args
    @Test
    public void runPwdThrowsExceptionNullArguments() throws IOException {
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        InputStream stdin = IOUtils.stringToInputStream("");
        assertThrows(PwdException.class, () ->
                pwdApp.run(null, stdin, stdout));
    }
    //sort with null args
    @Test
    public void runSortThrowsExceptionNullArguments() throws IOException {
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        InputStream stdin = IOUtils.stringToInputStream("");
        assertThrows(SortException.class, () ->
                sortApp.run(null, stdin, stdout), "");
    }

}
