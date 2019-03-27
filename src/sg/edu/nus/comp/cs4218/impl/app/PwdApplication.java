package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.PwdInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.PwdException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class PwdApplication implements PwdInterface {

    /**
     * Runs the pwd application with no specified arguments.
     * Assumption: The application does not take in any arguments.
     *
     * @param args   Array of arguments for the application, not used.
     * @param stdin  An InputStream, not used.
     * @param stdout An OutputStream, not used.
     *
     * @throws PwdException
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        String path = getAbsolutePath() + STRING_NEWLINE;
        try {
            stdout.write(path.getBytes());
        } catch (IOException e) {
            throw new PwdException(e, "Cannot write to output stream");
        } catch (NullPointerException e) {
            throw new PwdException(e, "Null Pointer Exception");
        }
    }

    @Override
    public String getAbsolutePath() throws PwdException {
        return Environment.currentDirectory;
    }
}
