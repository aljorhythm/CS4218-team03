package sg.edu.nus.comp.cs4218.impl;

import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.Shell;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ExitException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.CommandBuilder;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.*;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class ShellImpl implements Shell {
    public static final String ERR_INVALID_APP = "Invalid app.";
    public static final String ERR_NOT_SUPPORTED = "Not supported yet.";
    public static final String ERR_SYNTAX = "Invalid syntax.";
    private InputStream inputStream;
    private OutputStream outputStream;

    /**
     * Initializes shell with stdin
     */
    public ShellImpl() {
        inputStream = System.in;
        outputStream = System.out;
    }

    /**
     * Initializes shell with specified streams
     */
    public ShellImpl(InputStream in, OutputStream out) {
        inputStream = in;
        outputStream = out;
    }

    /**
     * Main method for the Shell Interpreter program.
     *
     * @param args List of strings arguments, unused.
     */
    public static void main(String... args) {
        ShellImpl shell = new ShellImpl();
        try {
            shell.run();
        } catch (ShellException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts shell, will stop on "exit" command
     */
    public void run() throws ShellException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            try {
                String currentDirectory = Environment.currentDirectory;
                outputStream.write((currentDirectory + ">").getBytes());

                String commandString = reader.readLine();
                if (!StringUtils.isBlank(commandString)) {
                    parseAndEvaluate(commandString, outputStream);
                }

                outputStream.write(STRING_NEWLINE.getBytes());
            } catch (ExitException e) {
                IOUtils.closeInputStream(inputStream);
                IOUtils.closeOutputStream(outputStream);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void parseAndEvaluate(String commandString, OutputStream stdout)
            throws AbstractApplicationException, ShellException {
        Command command = CommandBuilder.parseCommand(commandString, new ApplicationRunner());
        command.evaluate(inputStream, outputStream);
    }
}
