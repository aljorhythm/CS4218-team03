package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.DateException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.app.DateApplication;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHARSET_UTF8;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

class SubstitutionTest {
    
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    String currentDirectory = Environment.currentDirectory;

    /**
     * shell should show the date(with no arguments)
     */
    @Test
    void date_noArg_substitution_echo() throws IOException, ShellException, DateException {
        InputStream inputStream = IOUtils.stringToInputStream("echo `date`" + STRING_NEWLINE);
        ShellImpl shell = new ShellImpl(inputStream,outputStream);
        shell.run();
        DateApplication dateApplication = new DateApplication();
        String date = dateApplication.getDate(null);
        String expect = currentDirectory + ">" + date + STRING_NEWLINE + ">";
        String actual = outputStream.toString(CHARSET_UTF8);
        assertEquals(expect,actual);
    }

    /**
     * shell should show the date(with one arguments)
     */
    @Test
    void date_withOneArg_substitution_echo() throws IOException, ShellException, DateException {
        InputStream inputStream = IOUtils.stringToInputStream("echo `date +\"%m-%d-%y\"`" + STRING_NEWLINE);
        ShellImpl shell = new ShellImpl(inputStream,outputStream);
        shell.run();
        DateApplication dateApplication = new DateApplication();
        String date = dateApplication.getDate("%m-%d-%y");
        String expect = currentDirectory + ">" + date + STRING_NEWLINE + ">";
        String actual = outputStream.toString(CHARSET_UTF8);
        assertEquals(expect,actual);
    }

    /**
     * shell echo substitute the date. provide format to the date
     */
    @Test
    void echo_withOneArg_substitution_date() throws IOException, ShellException, DateException {
        InputStream inputStream = IOUtils.stringToInputStream("date `echo +\"%m-%d-%y\"`" + STRING_NEWLINE);
        ShellImpl shell = new ShellImpl(inputStream,outputStream);
        shell.run();
        DateApplication dateApplication = new DateApplication();
        String date = dateApplication.getDate("%m-%d-%y");
        String expect = currentDirectory + ">" + date + STRING_NEWLINE + ">";
        String actual = outputStream.toString(CHARSET_UTF8);
        assertEquals(expect,actual);
    }

    /**
     * shell should exit
     */
    @Test
    void exit_substitution() throws IOException, ShellException, DateException {
        InputStream inputStream = IOUtils.stringToInputStream("echo `exit`" + STRING_NEWLINE);
        ShellImpl shell = new ShellImpl(inputStream,outputStream);
        assertTimeout(ofSeconds(2), () -> {
            shell.run();
        });
    }

}