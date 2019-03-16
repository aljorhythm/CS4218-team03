/**
 * From Team 02
 */
package sg.edu.nus.comp.cs4218.impl;

import java.util.Comparator;
import java.util.Locale;

public class SortApplicationCaseInsensitiveComparatorHelper implements Comparator<String> {

    private final boolean isReversed;

    public SortApplicationCaseInsensitiveComparatorHelper(boolean isReversed) {
        this.isReversed = isReversed;
    }

    @Override
    public int compare(String string1, String string2) {
        int result;

        // This way the actual String object that is to be sorted using Collections.sort will not be altered
        String newString1 = string1.toUpperCase(Locale.getDefault());
        String newString2 = string2.toUpperCase(Locale.getDefault());

        char[] string1CharArr = newString1.toCharArray();
        char[] string2CharArr = newString2.toCharArray();

        int string1Length = string1CharArr.length;
        int string2Length = string2CharArr.length;
        int index = 0;

        result = BasicComparisonHelper.compare(string1Length, string2Length, string1CharArr, string2CharArr);

        if (result == 10) {
            if (index == string1Length && index == string2Length) {
                result = 0;
            } else if (index == string1Length) {
                result = -1;
            } else {
                result = 1;
            }
        }

        if (isReversed) {
            return (-1 * result);
        } else {
            return result;
        }
    }
}
