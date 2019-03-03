package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.CatInterface;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.*;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class CatApplication implements CatInterface {
    public static final String ERR_IS_DIR = "This is a directory";
    public static final String ERR_READING_FILE = "Could not read file";
    public static final String ERR_READING_STREAM = "Could not read stream";
    public static final String ERR_WRITE_STREAM = "Could not write to output stream";
    public static final String ERR_NULL_STREAMS = "null input stream provided";
    public static final String ERR_NULL_FILENAME = "null input file provided";
    public static final String ERR_GENERAL = "Exception Caught";

    /**
     * Runs the cat application with the specified arguments.
     *
     * @param args   Array of arguments for the application. Each array element is the path to a
     *               file. If no files are specified stdin is used.
     * @param stdin  An InputStream. The input for the command is read from this InputStream if no
     *               files are specified.
     * @param stdout An OutputStream. The output of the command is written to this OutputStream.
     * @throws CatException If the file(s) specified do not exist or are unreadable.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws CatException {
    }

    @Override
    public String catFiles(String... fileNames) throws CatException {
        if (fileNames == null) {
            throw new CatException(ERR_NULL_FILENAME);
        }

        InputStream[] fileInputStreams = new InputStream[fileNames.length];
        Exception toThrow;
        for (int i = 0; i < fileNames.length; i++) {
            String fileName = fileNames[i];
            try {
                InputStream fileInputStream = IOUtils.openInputStream(fileName);
                fileInputStreams[i] = fileInputStream;
            } catch (ShellException e) {
                toThrow = e;
                break;
            } catch (FileNotFoundException e) {
                toThrow = e;
                break;
            }
        }

        String[] outputs = new String[fileNames.length];

        for (int i = 0; i < fileInputStreams.length; i++) {
            InputStream in = fileInputStreams[0];
            try {
                outputs[i] = IOUtils.stringFromInputStream(in);
            } catch (IOException e) {
                throw new CatException(ERR_READING_FILE);
            }
            try {
                IOUtils.closeInputStream(in);
            } catch (ShellException e) {
            } catch (IOException e) {
            }
        }
        return String.join(STRING_NEWLINE, outputs);
    }

    @Override
    public String catStdin(InputStream stdin) throws CatException {
        if (stdin == null) {
            throw new CatException(ERR_NULL_STREAMS);
        }

        String inputString;
        try {
            inputString = IOUtils.stringFromInputStream(stdin);
        } catch (IOException e) {
            throw new CatException(ERR_READING_STREAM);
        }

        return inputString;
    }
}
