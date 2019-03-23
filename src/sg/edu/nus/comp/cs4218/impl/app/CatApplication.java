package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.CatInterface;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.*;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class CatApplication implements CatInterface {
    public static final String ERR_IS_DIR = "This is a directory";
    public static final String ERR_RD_FILE = "Could not read file";
    public static final String ERR_RD_STREAM = "Could not read stream";
    public static final String ERR_WRITE_STREAM = "Could not write to output stream";
    public static final String ERR_NULL_STREAMS = "Null Pointer Exception";
    public static final String ERR_NULL_FILENAME = "null input file provided";
    public static final String ERR_NULL_OS = "null output stream provided";
    public static final String ERR_GENERAL = "Exception Caught";
    public static final String ERR_NULL_ARGS = "null arguments";

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
        String catString;
        if (args == null || args.length == 0) {
            catString = catStdin(stdin);
        } else {
            catString = catFiles(args);
        }
        if(stdout == null) {
            throw new CatException(ERR_NULL_OS);
        }
        try {
            stdout.write(catString.getBytes());
        } catch (IOException e) {
            throw (CatException) new CatException(ERR_WRITE_STREAM).initCause(e);
        }
    }

    @Override
    public String catFiles(String... fileNames) throws CatException {
        if (fileNames == null) {
            throw new CatException(ERR_GENERAL);
        }

        InputStream[] fileInputStreams = new InputStream[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            String fileName = fileNames[i];
            if (new File(fileName).isDirectory()) {
                throw new CatException(ERR_IS_DIR);
            }
            try {
                InputStream fileInputStream = IOUtils.openInputStream(fileName);
                fileInputStreams[i] = fileInputStream;
            } catch (ShellException e) {
                throw (CatException) new CatException(ERR_RD_FILE).initCause(e);
            } catch (FileNotFoundException e) {
                throw (CatException) new CatException(ERR_RD_FILE).initCause(e);
            }
        }

        String[] outputs = new String[fileNames.length];

        for (int i = 0; i < fileInputStreams.length; i++) {
            InputStream inputStream = fileInputStreams[i];

            try {
                outputs[i] = IOUtils.stringFromInputStream(inputStream);
            } catch (IOException e) {
                throw (CatException) new CatException(ERR_RD_FILE).initCause(e);
            }

            try {
                IOUtils.closeInputStream(inputStream);
            } catch (ShellException e) {
                throw (CatException) new CatException(ERR_RD_FILE).initCause(e);
            } catch (IOException e) {
                throw (CatException) new CatException(ERR_RD_FILE).initCause(e);
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
            throw (CatException) new CatException(ERR_RD_STREAM).initCause(e);
        }

        return inputString;
    }
}
