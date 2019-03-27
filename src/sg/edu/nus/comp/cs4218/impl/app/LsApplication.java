package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.LsInterface;
import sg.edu.nus.comp.cs4218.exception.LsException;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.LinkedList;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

@SuppressWarnings("PMD.ExcessiveMethodLength")
public class LsApplication implements LsInterface {
    public static final String ERR_INVALID_DIR = "invalid dir";
    public static final String ERR_INVALID_TAG = "Invalid tag";
    public static final String ERR_INVALID_ORDER = "The order of the tag is wrong";
    public static final String ERR_NULL_STDOUT = "stdout is null";
    public static final String ERR_NULL_ARGS = "Argument is null";

    @Override
    public String listFolderContent(Boolean isFoldersOnly, Boolean isRecursive, String... folderName) {
        boolean multipleFile = (folderName.length > 1);
        String result = "";
        LinkedList<String> folderList = new LinkedList<>();
        for (String folder: folderName) {
            folderList.offer(folder);
        }
        if (folderName.length == 0) {
            folderList.offer("");
        }
        while (!folderList.isEmpty()) {
            String dir = "";
            String fileList = "";
            String folder = folderList.poll();
            String[] token = folder.replace("\\\\", "/").split("/");
            for (int i = 0; i < token.length; i++) {
                if (i == token.length - 1) {
                    dir += token[token.length-1];
                } else {
                    dir += token[i] + File.separator;
                }
            }
            try {
                File currDir = Paths.get(Environment.getCurrentDirectory() + File.separator + dir).toFile();
                if (!currDir.exists()) {
                    result += "ls: " + dir + " " + ERR_INVALID_DIR + STRING_NEWLINE;
                    continue;
                }
                if (currDir.isFile()) {
                    result += dir + STRING_NEWLINE;
                    continue;
                }
                if (isFoldersOnly) {
                    result += (dir.isEmpty()) ? "." + STRING_NEWLINE : dir + STRING_NEWLINE;
                    continue;
                }
                String[] list = sortCurrentDirectory(currDir);
                for (int i = 0; list != null && i < list.length; i++) {
                    File file = Paths.get(Environment.getCurrentDirectory() + File.separator + dir + File.separator + currDir.list()[i]).toFile();
                    if (file.isDirectory() && isRecursive) {
                        folderList.offer((dir.isEmpty()) ? list[i] : dir + File.separator + list[i]);
                        multipleFile = true;
                    }
                    if (file.isDirectory()) {
                        fileList += list[i] + " ";
                    } else if (!isFoldersOnly) {
                        fileList += list[i] + " ";
                    }
                }
                if (dir.isEmpty() || (folderName.length == 1 && dir.equals(folderName[0]) && !fileList.isEmpty())) {
                    result += fileList + STRING_NEWLINE;
                    continue;
                }
                if (multipleFile) {
                    result += dir + ":" + STRING_NEWLINE + fileList + STRING_NEWLINE;
                } else if (!fileList.isEmpty()) {
                    result += fileList + STRING_NEWLINE;
                }
            } catch (InvalidPathException e){
            result += "ls: " + dir + " " + ERR_INVALID_DIR + STRING_NEWLINE;
            continue;
            }
        }
        return result;
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

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws LsException {
        if (args == null) {
            throw new LsException(ERR_NULL_ARGS);
        }
        if (stdout == null) {
            throw new LsException(ERR_NULL_STDOUT);
        }
        boolean isFoldersOnly = false, isRecursive = false;
        String result;
        String[] folderName = new String[0];
        if (args.length > 0) {
            int start = 0, end = args.length;
            if (args[start].equals("-d")) {
                isFoldersOnly = true;
                start += 1;
            }
            if (args[end - 1].equals("-R")) {
                isRecursive = true;
                end -= 1;
            }
            folderName = new String[end - start];
            for (int i = start; i < end; i++) {
                if (args[i].equals("-d") || args[i].equals("-R")) {
                    throw new LsException(ERR_INVALID_ORDER);
                }
                if (!args[i].isEmpty() && args[i].charAt(0) == '-') {
                    throw new LsException(ERR_INVALID_TAG);
                }
                folderName[i - start] = args[i];
            }
        }
        result = listFolderContent(isFoldersOnly, isRecursive, folderName);
        PrintWriter out = new PrintWriter(stdout);
        out.print(result);
        out.flush();
    }
}
