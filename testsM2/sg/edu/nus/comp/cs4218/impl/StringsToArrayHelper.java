/**
 * From Team 02
 */
package sg.edu.nus.comp.cs4218.impl;

import java.util.ArrayList;
import java.util.List;

public final class StringsToArrayHelper {

    private StringsToArrayHelper() {
    }

    /**
     * Insert a sequence of String into a String array.
     *
     * @param toAppend The sequence of String objects to insert into a String array.
     * @return A String array which contains the sequence of String.
     */
    public static String[] appendStringsToArray(String... toAppend) {
        List<String> result = new ArrayList<>();
        for (String args : toAppend) {
            result.add(args);
        }
        return result.toArray(new String[0]);
    }
}
