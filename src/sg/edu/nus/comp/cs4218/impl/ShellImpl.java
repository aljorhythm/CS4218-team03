package sg.edu.nus.comp.cs4218.impl;

import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.Shell;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ExitException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.CommandBuilder;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.*;

@SuppressWarnings("PMD.LongVariable")
public class ShellImpl implements Shell {
    public static final String ERR_INACCESSIBLE_FILE = "Inaccessible file.";
    public static final String ERR_INVALID_APP = "Invalid app.";
    public static final String ERR_NOT_SUPPORTED = "Not supported yet.";
    public static final String ERR_SYNTAX = "Invalid syntax.";
    private InputStream inputStream = System.in;
    private OutputStream outputStream = System.out;

    /**
     * Initializes shell with stdin
     */
    public ShellImpl() {
        // Default function
        // System.in & out
    }

    /**
     * Initializes shell with specified streams
     */
    public ShellImpl(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    /**
     * Main method for the Shell Interpreter program.
     *
     * @param args List of strings arguments, unused.
     */
    public static void main(String... args) {
        ShellImpl shell = new ShellImpl();
            shell.run();
    }

    /**
     * Starts shell, will stop on "exit" command
     */
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Shell shell = new ShellImpl();

        while (true) {
            try {
                String currentDirectory = Environment.currentDirectory;
                System.out.print(currentDirectory + ">");

                String commandString = reader.readLine();
                if (!StringUtils.isBlank(commandString)) {
                    shell.parseAndEvaluate(commandString, outputStream);
                }
            } catch (ExitException e) {
                continue;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void parseAndEvaluate(String commandString, OutputStream stdout)
            throws AbstractApplicationException, ShellException {
        Command command = CommandBuilder.parseCommand(commandString, new ApplicationRunner());
        command.evaluate(null, stdout);
    }
}
