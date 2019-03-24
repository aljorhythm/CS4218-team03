package sg.edu.nus.comp.cs4218;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.DateException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.app.DateApplication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_SHELL_ARROW;

public class ShellSubstitutionTest extends ShellTest {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    String currentDirectory = Environment.currentDirectory;

    /**
     * shell should show the date(with no arguments)
     */
    @Test
    void dateNoArgSubstitutionEcho() throws IOException, ShellException, DateException {
        DateApplication dateApplication = new DateApplication();
        String date = dateApplication.getDate("");
        String[] input = {"echo `date`", "exit"};
        String[] output = {
                currentDirectory + CHAR_SHELL_ARROW + date,
                currentDirectory + CHAR_SHELL_ARROW
        };
        this.assertInputOutput(input, output);
    }

    /**
     * shell should show the date(with one arguments)
     */
    @Test
    void dateWithOneArgSubstitutionEcho() throws IOException, ShellException, DateException {
        DateApplication dateApplication = new DateApplication();
        String date = dateApplication.getDate("+%m-%d-%y");
        String[] input = {"echo `date +\"%m-%d-%y\"`", "exit"};
        String[] output = {
                currentDirectory + CHAR_SHELL_ARROW + date,
                currentDirectory + CHAR_SHELL_ARROW
        };
        this.assertInputOutput(input, output);
    }

    /**
     * shell echo substitute the date. provide format to the date
     */
    @Test
    void echoWithOneArgSubstitutionDate() throws IOException, ShellException, DateException {
        DateApplication dateApplication = new DateApplication();
        String date = dateApplication.getDate("+%m-%d-%y");
        String[] input = {"date `echo +\"%m-%d-%y\"`", "exit"};
        String[] output = {
                currentDirectory + CHAR_SHELL_ARROW + date,
                currentDirectory + CHAR_SHELL_ARROW
        };
        this.assertInputOutput(input, output);
    }

    /**
     * shell should exit
     */
    @Test
    void exitSubstitution() throws IOException, ShellException, DateException {
                String[] input = {"echo `exit`"};
        String[] output = {
                currentDirectory + CHAR_SHELL_ARROW
        };
        assertTimeoutPreemptively(ofSeconds(1), () -> {
            this.assertInputOutput(input, output);
        });
    }
}
