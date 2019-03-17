package sg.edu.nus.comp.cs4218.impl.app;


import sg.edu.nus.comp.cs4218.app.FindInterface;
import sg.edu.nus.comp.cs4218.exception.FindException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHARSET_UTF8;

public class FindApplication implements FindInterface {

    /**
     * Return the string listing the names of the matched file/folder in the specified folder.
     *
     * @param fileName   String of a regular expression of the file name
     * @param folderName Array of String of given folder/folders' name
     * @throws FindException
     */
    @Override
    public String findFolderContent(String fileName, String... folderName) throws FindException {
        if (fileName == null || folderName == null) {
            throw new FindException("Arguments for findFolderContent are not allowed to be null!");
        }
        String result = "";
        for (String f: folderName) {
            File file = new File(f);
            if (file.isDirectory()) {
                String[] subFiles = file.list();
                result += getFolderContent(fileName, f, subFiles);
            }
        }
        return result.trim();
    }

    /**
     * Helper function for recursion.
     *
     * @param fileName   String of a regular expression of the file name
     * @param parentPath String path to the parent folder
     * @param folderName Array of String of given folder/folders' name
     * @return
     * @throws FindException
     */
    private String getFolderContent(String fileName, String parentPath, String... folderName) throws FindException {
        String result = "";
        for (String f: folderName) {
            String path = parentPath + File.separator + f;
            File file = new File(path);
            if (f.matches(fileName)) {
                result += path + StringUtils.STRING_NEWLINE;
            }
            if (file.isDirectory()) {
                String[] subFiles = file.list();
                result += getFolderContent(fileName, path, subFiles);
            }
        }
        return result;
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws FindException {
        if (args == null) {
            throw new FindException("Args cannot be null for find!");
        }
        String result;
        String fileName = null;
        List<String> folderNames = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) == '-') {

                if (args[i].contains("name")) {
                    fileName = args[i + 1];
                    i++;
                } else {
                    throw new FindException("Unknown option for find!");
                }
            } else{
                folderNames.add(args[i]);
            }
        }
        if (folderNames.isEmpty() || fileName == null) {
            throw new FindException("Not correct number of arguments for find!");
        }
        String[] folderNamesArray = folderNames.toArray(new String[0]);
        result = findFolderContent(fileName, folderNamesArray);
        try {
            stdout.write(result.getBytes(CHARSET_UTF8));
        } catch (IOException e) {
            throw (FindException) new FindException("wc failed to write!").initCause(e);
        }
    }
}
