package sg.edu.nus.comp.cs4218.impl.util;

import sg.edu.nus.comp.cs4218.Environment;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_ASTERISK;

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
        regex.append(String.valueOf(chr));
    }

    public void appendAsterisk() {
        regex.append(".*");
        plaintext.append(CHAR_ASTERISK);
        isRegex = true;
    }

    public void merge(RegexArgument other) {
        plaintext.append(other.plaintext);
        regex.append(other.regex);
        isRegex = isRegex || other.isRegex;
    }

    public void merge(String str) {
        plaintext.append(str);
        regex.append(str);
    }

    public List<String> globFiles() {
        List<String> globbedFiles = new LinkedList<>();

        if (isRegex) {
            // fix file separator in regex
            String regexString = regex.toString();
            if ("/".equals(File.separator)) { // mac
                regexString = regexString.replace("\\", "/");
                regexString = regexString.replace("/", "\\" + File.separator);
            } else { // windows
                regexString = regexString.replace("/", "\\");
                regexString = regexString.replace("\\", "\\" + File.separator);
            }
            Pattern regexPattern = Pattern.compile(regexString);

            String dir = "";
            // tokenise the directories regardless of File separator type
            // change all backslash to forward slash
            String tokens[] = plaintext.toString().replaceAll("\\\\", "/").split("/");
            for (int i = 0; i < tokens.length; i++) {
                if (!tokens[i].contains("*")) {
                    dir += tokens[i] + File.separator;
                }
            }

            File currentDir = Paths.get(Environment.currentDirectory + File.separator + dir).toFile();

            if (currentDir.exists()) {
                for (File candidate : currentDir.listFiles()) {
                    if (!candidate.isHidden() && regexPattern.matcher(dir + candidate.getName()).matches()) {
                        globbedFiles.add(dir + candidate.getName());
                    }
                }
            }

            Collections.sort(globbedFiles);
        }

        if (globbedFiles.isEmpty()) {
            globbedFiles.add(plaintext.toString());
        }

        return globbedFiles;
    }

    public boolean isEmpty() {
        return plaintext.length() == 0;
    }

    public String toString() {
        return plaintext.toString();
    }

    public boolean isEquals(RegexArgument other) {
        boolean hasSameRegex = other.getRegex().toString().equals(regex.toString());
        boolean hasSamePlaintext = other.getPlaintext().toString().equals(plaintext.toString());
        boolean hasSameIsRegex = other.hasRegex() == isRegex;

        return hasSameRegex && hasSamePlaintext && hasSameIsRegex;
    }

    public StringBuilder getPlaintext() {
        return plaintext;
    }

    public StringBuilder getRegex() {
        return regex;
    }

    public boolean hasRegex() {
        return isRegex;
    }

    public void setRegex(String regex) {
        this.regex = new StringBuilder(regex);
    }

    public void setIsRegex(boolean isRegex) {
        this.isRegex = isRegex;
    }

    public void setPlaintext(String plaintText) {
        this.plaintext = new StringBuilder(plaintText);
    }
}
