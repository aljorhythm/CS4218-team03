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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ShellImpl implements Shell {
    public static final String ERR_INVALID_APP = "Invalid app.";
    public static final String ERR_NOT_SUPPORTED = "Not supported yet.";
    public static final String ERR_SYNTAX = "Invalid syntax.";
    public static final String FILE_NOT_FOUND = "File not found.";
    public static final String FOLDER_NOT_FOUND = "Folder not found.";
    public static final String MISSING_STREAM = "Missing input stream or input file";

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    /**
     * Main method for the Shell Interpreter program.
     *
     * @param args List of strings arguments, unused.
     */
    public static void main(String... args) {
        Shell shell = new ShellImpl();

        while (true) {
            try {
                String currentDirectory = Environment.currentDirectory;
                System.out.print(currentDirectory + ">");

                String commandString = reader.readLine();
                if (StringUtils.isBlank(commandString)) {
                    break;
                } else {
                    shell.parseAndEvaluate(commandString, System.out);
                }
            } catch (ExitException e) {
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
        command.evaluate(System.in, stdout); //TODO: Fix IO input/output
    }
}
