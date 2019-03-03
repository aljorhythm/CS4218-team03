package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.PwdInterface;
import sg.edu.nus.comp.cs4218.exception.PwdException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

public class PwdApplication implements PwdInterface {

    /**
     * The absolute path is the full path to the current working directory.
     *
     * @return The absolute pathname of the current working directory.
     * @throws PwdException
     */
    @Override
    public String getAbsolutePath() throws PwdException {
        return Paths.get("").toAbsolutePath().toString();
    }

    /**
     * Runs the pwd application with specified input data and specified output stream.
     *
     * @param args Additional arguments (ignored by pwd).
     * @param stdin Inputstream (ignored by pwd).
     * @param stdout Outputstream that the result will be written to.
     * @throws PwdException If outputstream cannot be written to.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws PwdException {
        try {
            stdout.write(getAbsolutePath().getBytes());
        } catch (IOException e) {
            throw (PwdException) new PwdException("could not write to outputstream").initCause(e);
        } catch (NullPointerException e) {
            throw (PwdException) new PwdException("outputstream is null").initCause(e);
        }
    }
}