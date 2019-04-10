/**
 * From Team 02
 */
package sg.edu.nus.comp.cs4218.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public final class FileIOTestHelper {

    private FileIOTestHelper() {}

    /**
     * Read from a file line-by-line and concatenate the lines with a newline character.
     *
     * @param fileName The file to read from.
     * @return A String object formed by the concatenation of the lines within fileName. An empty String is returned
     * if fileName is empty.
     */
    public static String extractAndConcatenate(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder result = new StringBuilder();
        String currentLine = reader.readLine();
        while(currentLine != null) {
            currentLine = currentLine + STRING_NEWLINE;
            result.append(currentLine);
            currentLine = reader.readLine();
        }
        reader.close();
        if (result.length() == 0) {
            return "";
        } else {
            // To remove the trailing newline character
            result = result.deleteCharAt(result.length() - 1);
            return result.toString();
        }

    }

    /**
     * Delete a sequence of file, if they exist.
     *
     * @param filesToClose The name of the sequence of files to delete.
     */
    public static void deleteMultipleFiles(String...filesToClose) {
        for (String fileName : filesToClose) {
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * Read from a sequence of file, line-by-line, and insert each line to a String array.
     *
     * @param files The sequence of file to read from.
     * @return A String array that contains all the lines from the sequence of files.
     */
    public static String[] extractAsStringArray(String... files) throws IOException {
        List<String> result = new ArrayList<>();
        for (String fileName : files) {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String currentLine = reader.readLine();
            while (currentLine != null) {
                result.add(currentLine);
                currentLine = reader.readLine();
            }
        }

        return result.toArray(new String[0]);
    }
}
