package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.PwdInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.PwdException;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class PwdApplication implements PwdInterface {
    @Override
    public String getAbsolutePath() throws PwdException {
        File directory = new File("");
        return directory.getAbsolutePath();
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws PwdException {
        System.out.println(getAbsolutePath());
    }
}
