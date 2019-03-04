package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.WcInterface;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.exception.WcException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHARSET_UTF8;

public class WcApplication implements WcInterface {

    @Override
    public String countFromFiles(Boolean isBytes, Boolean isLines, Boolean isWords, String... fileName) throws WcException {
        if (fileName == null || isBytes == null || isLines == null || isWords == null) {
            throw new WcException("no argument can be null!");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fileName.length; i++) {
            try {
                InputStream inputStream = IOUtils.openInputStream(fileName[i]);
                result.append(countFromStdin(isBytes, isLines, isWords, inputStream)).append(" " + fileName[i]).append('\n');
                IOUtils.closeInputStream(inputStream);
            } catch (IOException e) {
                throw (WcException) new WcException("IO not working").initCause(e);
            } catch (ShellException e) {
                throw (WcException) new WcException("Shell exception").initCause(e);
            }
        }
        return result.toString().trim();
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
        String result;
        Boolean isBytes = false;
        Boolean isLines = false;
        Boolean isWords = false;
        List<String> fileNames = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) == '-') {

                if (args[i].charAt(1) == 'c') {
                    isBytes = true;
                } else if (args[i].charAt(1) == 'l') {
                    isLines = true;
                } else if (args[i].charAt(1) == 'w') {
                    isWords = true;
                } else {
                    throw new WcException("Unknown option for wc!");
                }
            } else{
                fileNames.add(args[i]);
            }
        }
        if (fileNames.isEmpty()) {
            throw new WcException("Not correct number of arguments for wc!");
        }
        String[] fileNamesArray = fileNames.toArray(new String[0]);
        result = countFromFiles(isBytes, isLines, isWords, fileNamesArray);
        try {
            stdout.write(result.getBytes(CHARSET_UTF8));
        } catch (IOException e) {
            throw (WcException) new WcException("wc failed to write!").initCause(e);
        }
    }
}
