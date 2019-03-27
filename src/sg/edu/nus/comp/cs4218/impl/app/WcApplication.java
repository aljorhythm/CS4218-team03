package sg.edu.nus.comp.cs4218.impl.app;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_SPACE;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.comp.cs4218.app.WcInterface;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.exception.WcException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

@SuppressWarnings({"PMD.ShortVariable", "PMD.LongVariable", "PMD.ExcessiveMethodLength"})
public class WcApplication implements WcInterface {
    public static final String ERR_NULL_ARGS = "Argument is null";
    public static final String ERR_INVALID_TAG = "tag is invalid";
    public static final String ERR_INVALID_FILEFORMAT = "file format is invalid";
    public static final String ERR_NULL_STDOUT = "Output Stream is null";
    public static final String CHAR_SET = "UTF-8";

    @Override
    public String countFromFiles(Boolean isBytes, Boolean isLines, Boolean isWords, String... fileNames) throws WcException {
        StringBuilder totalRes = new StringBuilder();
        int totalLineCount = 0;
        int totalWordCount = 0;
        int totalByteCount = 0;
        String result = "";

        for (String fileName: fileNames) {
            InputStream in = null;
            try {
                in = IOUtils.openInputStream(fileName);
            } catch (ShellException e) {
                throw new WcException(e.getMessage());
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            int wordCount = 0;
            int lineCount = 0;
            int byteCount = 0;

            StringBuilder res = new StringBuilder();
            String line;

            try {
                while ((line = reader.readLine()) != null) {
                    //Word Count
                    String[] words = StringUtils.tokenize(line);
                    wordCount += words.length;
                    //Line Count
                    lineCount++;
                    //Byte Count
                    byteCount += line.getBytes(CHAR_SET).length + 1;
                }
            } catch (IOException e) {
                throw new WcException(e.getMessage());
            }

            //Generate display line
            generateDisplayName(isBytes, isLines, isWords, wordCount, lineCount, byteCount, res);
            res.append(fileName);

            result = res.toString();

            totalLineCount += lineCount;
            totalWordCount += wordCount;
            totalByteCount += byteCount;

            totalRes.append(res);
            totalRes.append(STRING_NEWLINE);
        }

        //Generate total display line for more than 2 files
        if (fileNames.length >= 2) {
            generateDisplayName(isBytes, isLines, isWords, totalWordCount, totalLineCount, totalByteCount, totalRes);
            totalRes.append("total");
            result = totalRes.toString();
        }

        return result;
    }

    @Override
    public String countFromStdin(Boolean isBytes, Boolean isLines, Boolean isWords, InputStream stdin) throws WcException {

        int wordCount = 0;
        int lineCount = 0;
        int byteCount = 0;
        StringBuilder res = new StringBuilder();
        String line;
        BufferedReader reader;

        if (stdin == null) {
            reader = new BufferedReader(new InputStreamReader(System.in));
        } else {
            reader = new BufferedReader(new InputStreamReader(stdin));
        }

        try {
            while ((line = reader.readLine()) != null) {
                //Word Count
                String[] words = StringUtils.tokenize(line);
                wordCount += words.length;
                //Line Count
                lineCount++;
                //Byte Count
                byteCount += line.getBytes(CHAR_SET).length + 1;
                //Terminate stdin from user input with newline
                if (stdin == null && line.equals("")) {
                    lineCount--;
                    byteCount--;
                    break;
                }
            }
        } catch (IOException e) {
            throw new WcException(e.getMessage());
        }

        //Generate display line
        generateDisplayName(isBytes, isLines, isWords, wordCount, lineCount, byteCount, res);
        return res.toString().trim();
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws WcException {
        if (args == null) {
            throw new WcException(ERR_NULL_ARGS);
        }

        if (stdout == null) {
            throw new WcException(ERR_NULL_STDOUT);
        }

        /*
         * boolVal[0]: isBytes
         * boolVal[1]: isLines
         * boolVal[2]: isWords
         * boolVal[3]: containsTags
         */
        boolean[] boolVal = {false, false, false, false};
        String[] fileNames = parseArguments(args, boolVal);

        String res = "";

        if (fileNames.length != 0) {
            res = countFromFiles(boolVal[0], boolVal[1], boolVal[2], fileNames);
        } else {
            res = countFromStdin(boolVal[0], boolVal[1], boolVal[2], stdin);
        }

        PrintWriter out = new PrintWriter(stdout);
        out.println(res);
        out.flush();
    }

    /*
     * Parse arguments provided.
     * Extract filename(s).
     * Parse tags to determine isBytes, isLines and isWords.
     * return list of filenames.
     */
    public String[] parseArguments(String[] args, boolean... boolVal) throws WcException {
        List<String> list = new ArrayList<>();
        for (String arg: args) {
            if (arg.startsWith("-")) {
                int numDash = 0;
                boolVal[3] = true;
                char[] tags = arg.toCharArray();
                for (char c : tags) {
                    switch (c) {
                        case '-':
                            numDash++;
                            break;
                        case 'c':
                            boolVal[0] = true;
                            break;
                        case 'l':
                            boolVal[1] = true;
                            break;
                        case 'w':
                            boolVal[2] = true;
                            break;
                        default:
                            throw new WcException(arg + ": " + ERR_INVALID_TAG);
                    }
                }
                if ((numDash > 0) && (!boolVal[0] && !boolVal[1] && !boolVal[2])) {
                    if (numDash == 1 || numDash == 2) {
                        boolVal[0] = true;
                        boolVal[1] = true;
                        boolVal[2] = true;
                    } else {
                        throw new WcException(arg + ": " + ERR_INVALID_TAG);
                    }
                }
            } else {
                if (arg.endsWith(".txt")) {
                    list.add(arg);
                } else {
                    throw new WcException(ERR_INVALID_FILEFORMAT);
                }
            }
        }

        if (!boolVal[3]) {
            boolVal[0] = true;
            boolVal[1] = true;
            boolVal[2] = true;
        }

        return list.toArray(new String[0]);
    }

    /*
     * Generate display from results.
     */
    public void generateDisplayName(Boolean isBytes, Boolean isLines, Boolean isWords, int wordCount, int lineCount,
        int byteCount, StringBuilder res) {
        if (isLines) {
            res.append(lineCount);
            res.append(CHAR_SPACE);
        }
        if (isWords) {
            res.append(wordCount);
            res.append(CHAR_SPACE);
        }
        if (isBytes) {
            res.append(byteCount);
            res.append(CHAR_SPACE);
        }
    }
}
