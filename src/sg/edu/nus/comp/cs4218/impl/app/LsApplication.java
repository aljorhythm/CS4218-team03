package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.LsInterface;
import sg.edu.nus.comp.cs4218.exception.LsException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHARSET_UTF8;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_FILE_SEP;

public class LsApplication implements LsInterface {

    @Override
    public String listFolderContent(Boolean isFoldersOnly, Boolean isRecursive, String... folderName) throws LsException {
        if (isFoldersOnly == null || isRecursive == null) {
            throw new LsException("Input arguments cannot be null!");
        }
        if (folderName == null || folderName.length == 0) {
            folderName = new String[]{Environment.currentDirectory};
        }
        Set<String> foldersContents = new LinkedHashSet();
        for (String f : folderName) {
            if ((folderName.length > 1 || isRecursive) && !isFoldersOnly) {
                foldersContents.add(f + ":");
            }
            String folderContents = getFolderContent(isFoldersOnly, isRecursive, f);
            foldersContents.add(folderContents + StringUtils.STRING_NEWLINE);
        }
        if (folderName.length == 0 && isFoldersOnly) {
            foldersContents.add(".");
        }
        String result;
        if (isFoldersOnly) {
            result = String
                    .join("", foldersContents)
                    .trim();
        } else {
            result = String
                    .join(StringUtils.STRING_NEWLINE, foldersContents)
                    .trim();
        }
        return result;
    }

    private String getFolderContent(Boolean isFoldersOnly, Boolean isRecursive, String folderName) throws LsException {
        Path path;
        if(folderName.startsWith(STRING_FILE_SEP)) {
            path = Paths.get(folderName);
        } else {
            path = Paths.get(Environment.currentDirectory).resolve(folderName);
        }
        File file = path.toFile();
        if (file == null || !file.exists()) {
            throw new LsException("File does not exist, make sure the path is correct!");
        }
        String result;
        if (isFoldersOnly) {
            result = file.getPath();
        } else if (file.isDirectory()) {
            String[] content = file.list();
            result = String.join(" ", content);
            if (isRecursive && content.length > 0) {
                for (String c : content) {
                    String recursivePath = folderName + File.separator + c;
                    File subFolder = new File(recursivePath);
                    if (subFolder.isDirectory()) {
                        result += StringUtils.STRING_NEWLINE + StringUtils.STRING_NEWLINE + recursivePath + ":"
                                + StringUtils.STRING_NEWLINE + getFolderContent(isFoldersOnly, true, recursivePath);
                    }
                }
            }
        } else {
            result = folderName;
        }
        return result;
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws LsException {
        String result;
        Boolean isFoldersOnly = false;
        Boolean isRecursive = false;
        List<String> fileNames = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) == '-') {

                if (args[i].contains("d")) {
                    isFoldersOnly = true;
                } else if (args[i].contains("R")) {
                    isRecursive = true;
                } else {
                    throw new LsException("Unknown option for ls!");
                }
            } else {
                fileNames.add(args[i]);
            }
        }

        String[] fileNamesArray = fileNames.toArray(new String[0]);
        result = listFolderContent(isFoldersOnly, isRecursive, fileNamesArray);
        try {
            stdout.write(result.getBytes(CHARSET_UTF8));
        } catch (IOException e) {
            throw (LsException) new LsException("ls failed to write!").initCause(e);
        }
    }
}
