package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.LsInterface;
import sg.edu.nus.comp.cs4218.exception.LsException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class LsApplication implements LsInterface {

    static final FilenameFilter directoryFilter = (current, name) -> new File(current, name).isDirectory();

    @Override
    public String listFolderContent(Boolean isFoldersOnly, Boolean isRecursive, String... folderNames) throws LsException {
        if (isFoldersOnly == null || isRecursive == null || folderNames == null) {
            throw new LsException("Input arguments cannot be null!");
        }
        Set<String> foldersContents = new LinkedHashSet();
        for(String folderName: folderNames) {
            String[] folderContents = listFolderContent(isFoldersOnly, isRecursive, folderName);
            foldersContents.addAll(Arrays.asList(folderContents));
        }
        if (folderNames.length == 0) {
            foldersContents.add(".");
        }
        return String.join(StringUtils.STRING_NEWLINE, foldersContents);
    }

    private String[] listFolderContent(Boolean isFoldersOnly, Boolean isRecursive, String folderName) throws LsException {
        File file = new File(folderName);
        String[] content;
        if (isRecursive) {
            ArrayList<String> recursiveList = new ArrayList<>();
            File[] children = file.listFiles();
            for (File child : children) {
                recursiveList.add(child.getName());
            }
            content = recursiveList.toArray(new String[0]);
        }
        else {
            if(isFoldersOnly) {
                content = file.list(directoryFilter);
            } else {
                content = file.list();
            }
        }
        return content;
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws LsException {

    }
}
