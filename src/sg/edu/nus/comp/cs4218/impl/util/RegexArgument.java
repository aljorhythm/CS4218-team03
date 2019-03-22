package sg.edu.nus.comp.cs4218.impl.util;

import sg.edu.nus.comp.cs4218.Environment;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;

@SuppressWarnings("PMD.AvoidStringBufferField")
public final class RegexArgument {
    private StringBuilder plaintext;
    private StringBuilder regex;
    private boolean isRegex;

    public RegexArgument() {
        this.plaintext = new StringBuilder();
        this.regex = new StringBuilder();
        this.isRegex = false;
    }

    public RegexArgument(String str) {
        this();
        merge(str);
    }

    public void append(char chr) {
        plaintext.append(chr);
        regex.append(Pattern.quote(String.valueOf(chr)));
    }

    public void appendAsterisk() {
        plaintext.append(CHAR_ASTERISK);
        regex.append("[^" + CHAR_FILE_SEP + "]*");
        isRegex = true;
    }

    public void merge(RegexArgument other) {
        plaintext.append(other.plaintext);
        regex.append(other.regex);
        isRegex = isRegex || other.isRegex;
    }

    public void merge(String str) {
        plaintext.append(str);
        regex.append(Pattern.quote(str));
    }

    /**
     * Empty list returned if directory is invalid or no files are matched.
     *
     * @return list of files and directories with names that match glob pattern
     */
    public List<String> globFiles() {
        List<String> globbedFiles;
        String globPattern = plaintext.toString();
        try {
            if (globPattern.startsWith(STRING_FILE_SEP)) {
                globbedFiles = GlobUtil.glob(Paths.get(STRING_FILE_SEP), globPattern.replaceFirst(STRING_FILE_SEP, ""));
            } else {
                globbedFiles = GlobUtil.glob(Paths.get(Environment.currentDirectory), globPattern);
            }
        } catch (IOException e) {
            return new LinkedList<>();
        }

        if(globbedFiles.isEmpty()) {
            globbedFiles = new LinkedList<>();
        }
        return globbedFiles;
    }

    public boolean isEmpty() {
        return plaintext.length() == 0;
    }

    public String toString() {
        return plaintext.toString();
    }
}
