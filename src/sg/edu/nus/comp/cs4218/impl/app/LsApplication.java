package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.LsInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.LsException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringJoiner;

import static sg.edu.nus.comp.cs4218.impl.ShellImpl.FOLDER_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.ShellImpl.ERR_SYNTAX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FLAG_PREFIX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_SPACE;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_CURR_DIR;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class LsApplication implements LsInterface {
    private static final int NUM_ARGS = 2;
    private static final char FOLDERS_ONLY = 'd';
    private static final char RECURSIVE = 'R';
    private static final int FOLDERS_ONLY_IDX = 0;
    private static final int RECURSIVE_IDX = 1;
    private static final String ESCAPED_PATH = "\\" + CHAR_FILE_SEP;
    public static final String ERR_GENERAL = "Exception Caught";

    /**
     * Lists only folder content of given folderName.
     * If folderName provided is a file, nothing is listed.
     * If folderName is empty, nothing is listed.
     */
    @Override
    public String listFolderContent(Boolean isFoldersOnly, Boolean isRecursive, String... folderName) throws Exception {
        ArrayList<String> listOfFileNames;
        ArrayList<String> listOfFolderNames;
        String temp;
        StringJoiner resultJoiner = new StringJoiner(STRING_NEWLINE);
        StringJoiner errFiles = new StringJoiner(STRING_NEWLINE);

        for (String f : folderName) {
            File folder = new File(Environment.currentDirectory + CHAR_FILE_SEP + f);
            if (!folder.exists()) {
                errFiles.add("ls: " + f + ": " + FOLDER_NOT_FOUND);
                continue;
            }
            if (!folder.isDirectory()) {
                continue;
            }

            listOfFileNames = getFilesFrom(folder);
            listOfFolderNames = getFoldersFrom(folder);
            if (folderName.length > 1 || isRecursive) {
                resultJoiner.add(f + ":");
            }

            temp = formatResult(isFoldersOnly, listOfFileNames, listOfFolderNames);
            if (!"".equals(temp)) {
                resultJoiner.add(temp);
            }
            resultJoiner.add("");

            if (isRecursive) {
                String[] folderNamesArr = updatePath(listOfFolderNames, f);
                temp = listFolderContent(isFoldersOnly, isRecursive, folderNamesArr);
                if (!"".equals(temp)) {
                    resultJoiner.add(temp);
                }
            }
        }

        String result = "";
        if (!StringUtils.isBlank(errFiles.toString()))
        {
            result = result + errFiles.toString() + STRING_NEWLINE;
        }
        if (!StringUtils.isBlank(resultJoiner.toString()))
        {
            result = result + resultJoiner.toString();
        }
        return result;
    }

    /**
     * Runs the ls application with the specified arguments.
     *
     * @param args   Array of arguments for the application. Each array element is the path to a
     *               folder. If no folders are specified the current directory is used.
     * @param stdin  An InputStream. The input for the command is read from this InputStream if no
     *               files are specified.
     * @param stdout An OutputStream. The output of the command is written to this OutputStream.
     *
     * @throws LsException If the folder(s) specified do not exist or are unreadable or invalid arguments are given.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        String[] argsCopy;
        try {
            argsCopy = replaceEmptyArgs(args);
            boolean[] lsArgs = getLsArguments(argsCopy);
            String[] fileNames = getFileNamesFromArgs(argsCopy);
            String[] folderNames = getFolderNamesFromArgs(argsCopy);

            String result = "";
            if (argsCopy.length == 0 || (fileNames.length == 0 && folderNames.length == 0)) {
                // If empty args, list folder content of current directory
                result = result + listFolderContent(lsArgs[FOLDERS_ONLY_IDX], lsArgs[RECURSIVE_IDX], STRING_CURR_DIR);
            } else {
                // Print list of files (if applicable)
                if (fileNames.length > 0) {
                    result = result + formatNames(new ArrayList<String>(Arrays.asList(fileNames))) + STRING_NEWLINE;

                    if (folderNames.length > 0 && !lsArgs[RECURSIVE_IDX] && folderNames.length == 1) {
                        result = result + STRING_NEWLINE;
                        result = result + folderNames[0] + ":" + STRING_NEWLINE;
                    } else if (folderNames.length > 0)
                    {
                        result = result + STRING_NEWLINE;
                    }
                }
                // Print folder content
                result = result + listFolderContent(lsArgs[FOLDERS_ONLY_IDX], lsArgs[RECURSIVE_IDX], folderNames);
            }

            stdout.write(result.getBytes());
        } catch (Exception e) {
            throw new LsException(e, ERR_GENERAL);
        }
    }

    /**
     * Checks if args contains a "" string. Replaces it with "."
     * @param args supplied by user for LS to replace
     * @return String array containing original arguments with "" replaced by "."
     */
    private String[] replaceEmptyArgs(String... args) {
        for (int i = 0; i < args.length; i++) {
            if (StringUtils.isBlank(args[i])) {
                args[i] = ".";
            }
        }
        return args;
    }

    /**
     * Checks if the arguments -R or -d appear in the args provided by user.
     * @param args supplied by user.
     * @return a bool array where true for index if the argument associated with the index is present, false otherwise.
     */
    private boolean[] getLsArguments(String... args) throws Exception {
        boolean[] lsArguments = new boolean[NUM_ARGS];

        for (String s : args) {
            char[] arg = s.toCharArray();
            if (arg[0] == CHAR_FLAG_PREFIX) {
                arg = Arrays.copyOfRange(arg, 1, arg.length);
                for (char c : arg) {
                    switch (c) {
                        case FOLDERS_ONLY:
                            lsArguments[FOLDERS_ONLY_IDX] = true;
                            break;
                        case RECURSIVE:
                            lsArguments[RECURSIVE_IDX] = true;
                            break;
                        default:
                            throw new Exception(ERR_SYNTAX);
                    }
                }
            }
        }
        return lsArguments;
    }

    /**
     * Checks if folder name(s) is provided by the user.
     * @param args supplied by user.
     * @return a String array of folder names.
     */
    public String[] getFolderNamesFromArgs(String... args) throws Exception {
        ArrayList<String> listOfFolderNames = new ArrayList<String>();

        for (String s : args) {
            char[] arg = s.toCharArray();
            if (arg[0] != CHAR_FLAG_PREFIX) {
                if (arg[arg.length-1] == CHAR_FILE_SEP) {
                    s = s.substring(0, s.length() - 1);
                }

                File file = new File(Environment.currentDirectory + CHAR_FILE_SEP + s);
                if (!file.exists() || file.isDirectory()) {
                    s = replacePathIdentifier(s);
                    listOfFolderNames.add(s);
                }
            }
        }
        return listOfFolderNames.toArray(new String[0]);
    }

    /**
     * Checks if file name(s) is provided by the user.
     * @param args supplied by user.
     * @return a String array of file names.
     */
    public String[] getFileNamesFromArgs(String... args) throws Exception {
        ArrayList<String> listOfFileNames = new ArrayList<String>();

        for (String s : args) {
            char[] arg = s.toCharArray();
            if (arg[0] != CHAR_FLAG_PREFIX) {
                if (arg[arg.length-1] == CHAR_FILE_SEP) {
                    s = s.substring(0, s.length() - 1);
                }
                File file = new File(Environment.currentDirectory + CHAR_FILE_SEP + s);
                if (file.exists() &&!file.isDirectory()) {
                    s = replacePathIdentifier(s);
                    listOfFileNames.add(s);
                }
            }
        }
        return listOfFileNames.toArray(new String[0]);
    }

    /**
     * Sorts the listOfNames in alphabetical order
     * @param listOfNames unsorted list of file names
     * @return Returns a string of all names concatenated and delimited by a space character
     */
    private String formatNames(ArrayList<String> listOfNames)
    {
        StringJoiner namesJoiner = new StringJoiner(Character.toString(CHAR_SPACE));
        Collections.sort(listOfNames);
        for (String name : listOfNames) {
            namesJoiner.add(name);
        }
        return namesJoiner.toString();
    }

    /**
     * Formats the listOfFileNames and listOfFolders
     * @param listOfFolderNames unsorted list of folder names
     * @param listOfFileNames unsorted list of file names
     * @return Returns a string of all folder names and all file names concatenated and delimited by a space character
     */
    private String formatResult(boolean isFoldersOnly, ArrayList<String> listOfFileNames, ArrayList<String> listOfFolderNames)
    {
        StringJoiner resultJoiner = new StringJoiner(Character.toString(CHAR_SPACE));
        if (!listOfFolderNames.isEmpty()) {
            resultJoiner.add(formatNames(listOfFolderNames));
        }
        if (!isFoldersOnly && !listOfFileNames.isEmpty()) {
            resultJoiner.add(formatNames(listOfFileNames));
        }
        return resultJoiner.toString();
    }

    /**
     * Pre-condition: currFolder exists
     * @param currFolder the folder to list all files
     * @return an ArrayList<String> containing all the file names
     */
    private ArrayList<String> getFilesFrom(File currFolder) {
        File[] listOfFiles = currFolder.listFiles();
        ArrayList<String> listOfFileNames = new ArrayList<String>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isHidden()) {
                continue;
            }
            if (listOfFiles[i].isFile()) {
                listOfFileNames.add(listOfFiles[i].getName().trim());
            }
        }
        return listOfFileNames;
    }

    /**
     * Pre-condition: currFolder exists
     * @param currFolder the folder to list all folders
     * @return an ArrayList<String> containing all the folder names
     */
    private ArrayList<String> getFoldersFrom(File currFolder) {
        File[] listOfFiles = currFolder.listFiles();
        ArrayList<String> listOfFolderNames = new ArrayList<String>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isHidden()) {
                continue;
            }
            if (listOfFiles[i].isDirectory()) {
                listOfFolderNames.add(listOfFiles[i].getName().trim());
            }
        }
        return listOfFolderNames;
    }

    /**
     * Update folder names to include entire subpath from pwd
     * @param listOfFolderNames to update
     * @param filepath subpath from pwd
     * @return String array containing updated paths
     */
    private String[] updatePath(ArrayList<String> listOfFolderNames, String filepath) {
        String[] folderNamesArr = new String[listOfFolderNames.size()];
        for (int i = 0; i < folderNamesArr.length; i++) {
            folderNamesArr[i] = filepath + CHAR_FILE_SEP + listOfFolderNames.get(i);
        }
        return folderNamesArr;
    }

    /**
     * Replaces all '/' and '\' in given String with CHAR_FILE_SEP
     * @param toReplace String to replace all '/' and '\'
     * @return String with replaced '/' and '\'
     */
    private String replacePathIdentifier(String toReplace) {
        String replaced = toReplace.replaceAll("\\\\", ESCAPED_PATH);
        replaced = replaced.replaceAll("/", ESCAPED_PATH);
        return replaced;
    }
}
