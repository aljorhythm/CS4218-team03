package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.FindInterface;
import sg.edu.nus.comp.cs4218.exception.FindException;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.regex.Pattern;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

@SuppressWarnings("PMD.PositionLiteralsFirstInComparisons")
public class FindApplication implements FindInterface {
    public static final String ERR_NULL_ARGS = "Argument is null";
    public static final String ERR_NULL_STDOUT = "OutputStream is null";
    public static final String ERR_NUM_ARGS = "Incorrect number of argument";
    public static final String ERR_INVALID_DIR = " is not a valid folder";
    public static final String ERR_INVALID_TAG = "The tag -name is missing";

    @Override
    public String findFolderContent(String fileName, String... folderName) throws FindException {
        String result = "";
        Pattern pattern = Pattern.compile(fileName.replace("*", ".*"));
        LinkedList<String> folderList = new LinkedList<>();
        for (String folder: folderName) {
            folderList.offer(folder);
        }
        while (!folderList.isEmpty()) {
            String dir = "";
            String folder = folderList.poll();
            String[] token = folder.replace("\\\\", "/").split("/");
            for (int i = 0; i < token.length - 1; i++) {
                dir += token[i] + File.separator;
            }
            dir += token[token.length-1];
            File currDir = Paths.get(Environment.getCurrentDirectory()+ File.separator + dir).toFile();
            if (!currDir.exists()) {
                result += "find: " + dir + ERR_INVALID_DIR + STRING_NEWLINE;
                continue;
            }
            String[] list = null;
            if (currDir.list() != null) {
                list = sortCurrentDirectory(currDir);
            }
            if(pattern.matcher(dir).matches()) {
                result += dir + STRING_NEWLINE;
            }
            for (int i = 0; list != null && i < list.length; i++) {
                File file = Paths.get(Environment.getCurrentDirectory()+ File.separator + dir + File.separator + list[i]).toFile();
                if (file.isDirectory()) {
                    folderList.offer(dir + File.separator + list[i]);
                }
                else if (pattern.matcher(list[i]).matches()) {
                    result += dir + File.separator + list[i] + STRING_NEWLINE;
                }
            }
        }
        return result;
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws FindException {
        if(args == null) {
            throw new FindException(ERR_NULL_ARGS);
        }
        if (stdout == null) {
            throw new FindException(ERR_NULL_STDOUT);
        }
        LinkedList<String> folderNameList = new LinkedList<>();
        String fileName = "";
        boolean nameTagPresent = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-name")) {
                nameTagPresent = true;
                i++;
                if ( i == args.length - 1) {
                    fileName = args[i];
                    break;
                }
                break;
            }
            folderNameList.add(args[i]);
        }
        if (!nameTagPresent) {
            throw new FindException(ERR_INVALID_TAG);
        }
        if (folderNameList.isEmpty()) {
            throw new FindException(ERR_NUM_ARGS);
        }
        if (fileName.equals("")) {
            throw new FindException(ERR_NUM_ARGS);
        }
        String[] folderName = new String[folderNameList.size()];
        folderNameList.toArray(folderName);
        String result = findFolderContent(fileName,folderName);
        PrintWriter out = new PrintWriter(stdout);
        out.print(result);
        out.flush();
    }

    private String[] sortCurrentDirectory(File currDir) {
        LinkedList<String> list = new LinkedList<>();
        for (String s: currDir.list()) {
            list.add(s);
        }
        list.sort(String::compareTo);
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
