package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.SedInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;

import java.io.InputStream;
import java.io.OutputStream;

public class SedApplication implements SedInterface{
    @Override
    public String replaceSubstringInFile(String regexp, String replacement, int replacementIndex,
                                  String fileName) throws Exception{
        return null;
    }

    @Override
    public String replaceSubstringInStdin(String regexp, String replacement, int replacementIndex,
                                          InputStream stdin) throws Exception{
        return null;
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {

    }
}
