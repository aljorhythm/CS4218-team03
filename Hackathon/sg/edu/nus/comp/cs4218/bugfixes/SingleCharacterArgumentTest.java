package sg.edu.nus.comp.cs4218.bugfixes;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleCharacterArgumentTest {

    ShellImpl shell = new ShellImpl();


    // pwd a
    @Test
    public void runWithSingleCharacterArgument()
            throws AbstractApplicationException,
            ShellException {
        String arg = "pwd a";
        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(arg, outstream);
        String expectedResult =
                Environment.currentDirectory + System.lineSeparator();
        String actualResult = outstream.toString();
        assertEquals(expectedResult,
                actualResult);
    }

    // date +
    @Test
    public void runSuccessOnlyPlusArgument()
            throws AbstractApplicationException,
            ShellException {
        String arg = "date +";
        OutputStream outstream = new ByteArrayOutputStream();
        shell.parseAndEvaluate(arg, outstream);
        String expectedResult = "";
        String actualResult = outstream.toString();
        assertEquals(expectedResult,
                actualResult);
    }

}
