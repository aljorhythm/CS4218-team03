package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.LsInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class LsApplication implements LsInterface {

    static final FilenameFilter directoryFilter = new FilenameFilter() {
        @Override
        public boolean accept(File current, String name) {
            // return new File(current, name).isDirectory();
            return true;
        }
    };

    @Override
    public String listFolderContent(Boolean isFoldersOnly, Boolean isRecursive, String... folderNames) throws Exception {
        Set<String> foldersContents = new LinkedHashSet<String>();
        for(String folderName: folderNames) {
            String[] folderContents = listFolderContent(isFoldersOnly, isRecursive, folderName);
            foldersContents.addAll(Arrays.asList(folderContents));
        }
        return String.join(StringUtils.STRING_NEWLINE, foldersContents);
    }

    private String[] listFolderContent(Boolean isFoldersOnly, Boolean isRecursive, String folderName) throws Exception {
        File file = new File(folderName);
        String[] content = file.list(directoryFilter);
        return content;
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {

    }
}
