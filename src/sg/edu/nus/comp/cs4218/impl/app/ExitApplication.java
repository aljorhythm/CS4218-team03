package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.ExitInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ExitException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ExitApplication implements ExitInterface {
    public static final String EXIT_REMINDER = "Terminate process.";

    @Override
    public void terminateExecution() throws ExitException {
        throw new ExitException(EXIT_REMINDER);
    }

    /**
     * Runs the exit application with the specified arguments.
     *
     * @param args   Array of arguments for the application,"exit" exactly.
     * @param stdin  An InputStream. Not used.
     * @param stdout An OutputStream. Not used.
     *
     * @throws ExitException If the shell doesn't work.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws ExitException {
        if(args.length == 0){
            terminateExecution();
        }
        else{
            try {
                stdout.write(("exit: Invalid syntax.\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
