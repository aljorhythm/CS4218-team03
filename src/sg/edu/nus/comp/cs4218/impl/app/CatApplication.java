package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.CatInterface;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@SuppressWarnings("PMD.ShortVariable")
public class CatApplication implements CatInterface {
    public static final String ERR_IS_DIR = "This is a directory";
    public static final String ERR_READING_FILE = "Could not read file";
    public static final String ERR_WRITE_STREAM = "Could not write to output stream";
    public static final String ERR_NULL_STREAMS = "Null Pointer Exception";
    public static final String ERR_GENERAL = "Exception Caught";

    /**
     * Runs the cat application with the specified arguments.
     *
     * @param args   Array of arguments for the application. Each array element is the path to a
     *               file. If no files are specified stdin is used.
     * @param stdin  An InputStream. The input for the command is read from this InputStream if no
     *               files are specified.
     * @param stdout An OutputStream. The output of the command is written to this OutputStream.
     *
     * @throws CatException If the file(s) specified do not exist or are unreadable.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws CatException {

        if (stdout == null) {
            throw new CatException(ERR_NULL_STREAMS);
        }

        if (args == null || args.length == 0) {
            if (stdin == null) {
                stdin = System.in;
            }
            try {
                stdout.write(catStdin(stdin).getBytes());
            } catch (IOException ioe) {
                throw new CatException(ERR_WRITE_STREAM);
            } catch (Exception e) {
                throw new CatException(ERR_GENERAL);
            }
        } else {
            try {
                stdout.write(catFiles(args).getBytes());
            } catch (IOException ioe) {
                throw new CatException(ERR_WRITE_STREAM);
            } catch (Exception e) {
                throw new CatException(ERR_GENERAL);
            }
        }
    }

    @Override
    public String catFiles(String... fileName) throws Exception {
        StringBuilder sb = new StringBuilder();
        int content;

        for (String file : fileName) {
            try {
                checkIfFileIsReadable(file);
                InputStream fis = IOUtils.openInputStream(file);
                while ((content = fis.read()) != -1) {
                    sb.append((char) content);
                }
                IOUtils.closeInputStream(fis);
            } catch (CatException cate) {
                System.out.println(cate.getMessage());
            } catch (ShellException se) {
                System.out.println(new CatException(file + ": " + ERR_READING_FILE).getMessage());
            }
        }
        return sb.toString();
    }

    @Override
    public String catStdin(InputStream stdin) throws Exception {
        StringBuilder sb = new StringBuilder();
        String line;

        BufferedReader bf = new BufferedReader(new InputStreamReader(stdin));
        while ((line = bf.readLine()) != null) {
            if (line.equals("")) {
                break;
            }
            sb.append(line + System.lineSeparator());
        }
        return sb.toString();
    }

    private void checkIfFileIsReadable(String fileName) throws CatException {
        Path filePath = IOUtils.resolveFilePath(fileName);
        if (Files.isDirectory(filePath)) {
            throw new CatException(fileName + ": " + ERR_IS_DIR);
        }
        if (!Files.isReadable(filePath)) {
            throw new CatException(fileName + ": " + ERR_READING_FILE);
        }
    }
}
