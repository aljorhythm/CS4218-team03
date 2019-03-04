package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.WcInterface;
import sg.edu.nus.comp.cs4218.exception.WcException;

import java.io.*;

public class WcApplication implements WcInterface {

    @Override
    public String countFromFiles(Boolean isBytes, Boolean isLines, Boolean isWords, String... fileName) throws WcException {
        return null;
    }

    @Override
    public String countFromStdin(Boolean isBytes, Boolean isLines, Boolean isWords, InputStream stdin) throws WcException, IOException {
        if (stdin == null) {
            throw new WcException("stdin is null!");
        }
        int numberOfBytes = 0;
        int numberOfLines = 0;
        int numberOfWords = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdin));
        for (String line; (line = reader.readLine()) != null; ) {
            numberOfLines += 1;
            numberOfBytes += line.getBytes().length;
            numberOfWords += line.split(" ").length;
        }
        String result = "";
        if (isLines) {
            result += numberOfLines + " ";
        }
        if (isWords) {
            result += numberOfWords + " ";
        }
        if (isBytes) {
            result += numberOfBytes  + " ";
        }
        return result.trim();
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws WcException {

    }
}
