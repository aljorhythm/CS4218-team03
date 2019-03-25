package sg.edu.nus.comp.cs4218;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.CommandBuilder;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.IOUtils.stringsToInputStream;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class QuotingGlobingTest {

    public static String quotingCommand1 = "echo \"This is space:`echo \" \"`.\"";
    public static String quotingExpect1 = "This is space: ."+STRING_NEWLINE;
    public static String quotingCommand2 = "echo \'This is space:`echo \" \"`.\'";
    public static String quotingExpect2 = "This is space:`echo \" \"`."+STRING_NEWLINE;
    public static String quotingCommand3 = "echo \"This is space:\' \'.\"";
    public static String quotingExpect3 = "This is space:\' \'."+STRING_NEWLINE;
    public static String quotingCommand4 = "echo \"\'This is space `echo \" \"`\'\"";
    public static String quotingExpect4 = "\'This is space \'"+STRING_NEWLINE;
    public static String quotingCommand5 = "echo \'\"This is space `echo \" \"`\"\'";
    public static String quotingExpect5 = "\"This is space `echo \" \"`\""+STRING_NEWLINE;


    /**
     * Tests running of shell with input and output
     */
    void assertInputOutput(String commands, String expected) throws AbstractApplicationException, ShellException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream inputStream = stringsToInputStream(commands);
        Shell shell = new ShellImpl(inputStream, outputStream);
        shell.parseAndEvaluate(commands,outputStream);
        String actual = outputStream.toString();
        assertEquals(expected, actual);
    }

    /**
     * Test for quoting1
     */
    @Test
    void quotingTest1() throws Exception{
        assertInputOutput(quotingCommand2,quotingExpect2);
    }
    /**
     * Test for quoting2
     */
    @Test
    void quotingTest2() throws Exception{
        assertInputOutput(quotingCommand3,quotingExpect3);
    }

    /**
     * Test for quoting3
     */
    @Test
    void quotingTest3() throws Exception{
        assertInputOutput(quotingCommand5,quotingExpect5);
    }


}
