package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.WcInterface;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.exception.WcException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

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
                result.append(countFromStdin(isBytes, isLines, isWords, inputStream)).append(" " + fileName[i]).append(StringUtils.STRING_NEWLINE);
                if (i == fileName.length - 1 && fileName.length > 1) {
                    result.append(getTotalWc(isBytes, isLines, isWords, result.toString().trim()));
                }
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
    public String countFromStdin(Boolean isBytes, Boolean isLines, Boolean isWords, InputStream stdin) throws WcException {
        if (stdin == null) {
            throw new WcException("stdin is null!");
        }
        int numberOfLines = 0;
        int numberOfWords = 0;
        int numberOfBytes = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdin));
        try {
            for (String line; (line = reader.readLine()) != null; ) {
                numberOfLines += 1;
                numberOfBytes += line.getBytes().length;
                String[] words = line.split(" ");
                for (String w : words) {
                    if (!w.equals("") && !w.equals("\t")) {
                        numberOfWords += 1;
                    }
                }
            }
        } catch (IOException e) {
            throw (WcException) new WcException("Could not read file!").initCause(e);
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
                for (int j = 1; j < args[i].length(); j++) {

                    if (args[i].charAt(j) == 'c') {
                        isBytes = true;
                    } else if (args[i].charAt(j) == 'l') {
                        isLines = true;
                    } else if (args[i].charAt(j) == 'w') {
                        isWords = true;
                    }
                    else {
                        throw new WcException("Unknown option for wc!");
                    }
                }
            } else {
                fileNames.add(args[i]);
            }
        }
        if (!isBytes && !isLines && !isWords) {
            isBytes = true;
            isLines = true;
            isWords = true;
        }
        if (fileNames.isEmpty()) {
            if (stdin == null) {
                throw new WcException("Not correct number of arguments for grep!");
            } else {
                result = countFromStdin(isBytes, isLines, isWords, stdin);
            }
        } else {
            String[] fileNamesArray = fileNames.toArray(new String[0]);
            result = countFromFiles(isBytes, isLines, isWords, fileNamesArray);
        }

        try {
            int i = 0;
            stdout.write(result.getBytes(CHARSET_UTF8));
        } catch (IOException e) {
            throw (WcException) new WcException("wc failed to write!").initCause(e);
        }
    }

    private String getTotalWc(Boolean isBytes, Boolean isLines, Boolean isWords, String result) throws WcException {
        BufferedReader bufReader = new BufferedReader(new StringReader(result));
        String line;
        int totalLines = 0;
        int totalWords = 0;
        int totalBytes = 0;
        while(true)
        {
            try {
                if ((line=bufReader.readLine()) != null) {
                    String[] items = line.split(" ");
                    int item = 0;
                    if (isLines) {
                        totalLines += Integer.parseInt(items[item]);
                        item++;
                    }
                    if (isWords) {
                        totalWords += Integer.parseInt(items[item]);
                        item++;
                    }
                    if (isBytes) {
                        totalBytes += Integer.parseInt(items[item]);
                        item++;
                    }
                } else {
                    break;
                }
            } catch (IOException e) {
                throw (WcException) new WcException("IO not working").initCause(e);
            }

        }
        String total = "";
        if (isLines) {
            total += totalLines + " ";
        }
        if (isWords) {
            total += totalWords + " ";
        }
        if (isBytes) {
            total += totalBytes  + " ";
        }
        return total + "total";
    }
}
