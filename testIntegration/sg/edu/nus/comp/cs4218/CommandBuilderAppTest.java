package sg.edu.nus.comp.cs4218;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.CommandBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.IOUtils.stringToInputStream;

public class CommandBuilderAppTest {
    ApplicationRunner runner = new ApplicationRunner();

    /**
     * Parses and executes command
     * @throws ShellException
     * @throws IOException
     * @throws AbstractApplicationException
     */
    @Test
    void parseAndExecuteEchoCommand() throws ShellException, IOException, AbstractApplicationException {
        String commandString = "echo asd";
        CallCommand command = (CallCommand) CommandBuilder.parseCommand(commandString, runner);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        command.evaluate(stringToInputStream(commandString), outputStream);
        String actual = outputStream.toString();
        assertEquals("asd", actual);
    }
}
