package sg.edu.nus.comp.cs4218.bugfixes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.DateException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.app.DateApplication;

import java.io.*;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateApplicationTest {
    DateApplication date;
    Date now;

    private InputStream stdin;
    private OutputStream stdout;
    private PrintWriter out;

    @BeforeEach
    public void setUp() {
        date = new DateApplication();
        now = new Date();
        stdout = new ByteArrayOutputStream();
        out = new PrintWriter(stdout);
    }

    @AfterEach
    public void tearDown() throws IOException {
        stdout.close();
        out.flush();
    }

    // date +"%m-%t"
    @Test
    public void runThrowsExceptionIncorrectTag() {
        String[] args = {"+%m-%t"};
        assertThrows(DateException.class, () -> date.run(args, stdin, stdout));
    }

    // date +"%%"
    @Test
    public void runThrowsExceptionTwoPercentArgument() throws AbstractApplicationException, ShellException {
        String[] args = {"+%%"};
        assertThrows(DateException.class, () -> date.run(args, stdin, stdout));
    }
}
