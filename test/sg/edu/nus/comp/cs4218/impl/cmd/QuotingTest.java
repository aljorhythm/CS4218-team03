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
    String commandStringSingle = "echo \'test\'";
    String commandStringDouble = "echo \"test\"";
    String commandStringBack = "echo `test`";
    String commandStringSingleOne = "echo \'test";
    String commandStringDoubleOne = "echo \"test";
    String commandStringBackOne = "echo `test";
    ByteArrayOutputStream baoStream = new ByteArrayOutputStream(1024);

    @Test
    void testSingleQuotes() throws ShellException, AbstractApplicationException, IOException {
        command = CommandBuilder.parseCommand(commandStringSingle, new ApplicationRunner());
        command.evaluate(System.in,baoStream);
        assertEquals("test\n",baoStream.toString());
        baoStream.flush();
    }

    @Test
    void testDoubleQuotes() throws ShellException, AbstractApplicationException, IOException {
        command = CommandBuilder.parseCommand(commandStringDouble, new ApplicationRunner());
        command.evaluate(System.in,baoStream);
        assertEquals("test\n",baoStream.toString());
        baoStream.flush();
    }

    @Test
    void testBackQuotes() throws ShellException, AbstractApplicationException, IOException {
        command = CommandBuilder.parseCommand(commandStringBack, new ApplicationRunner());
        command.evaluate(System.in,baoStream);
        assertEquals("test\n",baoStream.toString());
        baoStream.flush();
    }

    @Test
    void testSingleQuoteFailure() throws ShellException, AbstractApplicationException, IOException {
        command = CommandBuilder.parseCommand(commandStringSingleOne, new ApplicationRunner());
        command.evaluate(System.in,baoStream);
        assertEquals("\"test\n",baoStream.toString());
        baoStream.flush();
    }

    @Test
    void testDoubleQuoteFailure() throws ShellException, AbstractApplicationException, IOException {
        command = CommandBuilder.parseCommand(commandStringDoubleOne, new ApplicationRunner());
        command.evaluate(System.in,baoStream);
        assertEquals("\'test\n",baoStream.toString());
        baoStream.flush();
    }

    @Test
    void testBackQuoteFailure() throws ShellException, AbstractApplicationException, IOException {
        command = CommandBuilder.parseCommand(commandStringBackOne, new ApplicationRunner());
        command.evaluate(System.in,baoStream);
        assertEquals("`test\n",baoStream.toString());
        baoStream.flush();
    }
}