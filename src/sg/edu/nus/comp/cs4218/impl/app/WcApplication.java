package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.WcInterface;
import sg.edu.nus.comp.cs4218.exception.WcException;

import java.io.InputStream;
import java.io.OutputStream;

public class WcApplication implements WcInterface {

    @Override
    public String countFromFiles(Boolean isBytes, Boolean isLines, Boolean isWords, String... fileName) throws WcException {
        return null;
    }

    @Override
    public String countFromStdin(Boolean isBytes, Boolean isLines, Boolean isWords, InputStream stdin) throws WcException {
        int numberOfBytes = 0;
        int numberOfLines = 0;
        int numberOfWords = 0;
        return null;
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws WcException {

    }
}
