package sg.edu.nus.comp.cs4218.impl.cmd;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.CommandBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class QuotingTest {
    Command command;
    String cmdStringSingle = "echo \'test\'";
    String cmdStringDouble = "echo \"test\"";
    String cmdStrSingleOne = "echo \'test";
    String cmdStrDoubleOne = "echo \"test";
    String cmdStringBackOne = "echo `test";
    ByteArrayOutputStream baoStream = new ByteArrayOutputStream(1024);

    @Test
    void testSingleQuotes() throws ShellException, AbstractApplicationException, IOException {
        command = CommandBuilder.parseCommand(cmdStringSingle, new ApplicationRunner());
        command.evaluate(System.in, baoStream);
        assertEquals("test\n", baoStream.toString());
        baoStream.flush();
    }

    @Test
    void testDoubleQuotes() throws ShellException, AbstractApplicationException, IOException {
        command = CommandBuilder.parseCommand(cmdStringDouble, new ApplicationRunner());
        command.evaluate(System.in, baoStream);
        assertEquals("test\n", baoStream.toString());
        baoStream.flush();
    }

    @Test
    void testSingleQuoteFailure() throws ShellException, AbstractApplicationException, IOException {
        command = CommandBuilder.parseCommand(cmdStrSingleOne, new ApplicationRunner());
        command.evaluate(System.in, baoStream);
        assertEquals("\"test\n", baoStream.toString());
        baoStream.flush();
    }

    @Test
    void testDoubleQuoteFailure() throws ShellException, AbstractApplicationException, IOException {
        command = CommandBuilder.parseCommand(cmdStrDoubleOne, new ApplicationRunner());
        command.evaluate(System.in, baoStream);
        assertEquals("\'test\n", baoStream.toString());
        baoStream.flush();
    }

    @Test
    void testBackQuoteFailure() throws ShellException, AbstractApplicationException, IOException {
        command = CommandBuilder.parseCommand(cmdStringBackOne, new ApplicationRunner());
        command.evaluate(System.in, baoStream);
        assertEquals("`test\n", baoStream.toString());
        baoStream.flush();
    }
}