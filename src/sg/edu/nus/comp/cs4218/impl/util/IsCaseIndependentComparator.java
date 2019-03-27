package sg.edu.nus.comp.cs4218.impl.util;

import java.util.Comparator;

/**
 * This comparator is ued to sort strings ignoring case.
 */
@SuppressWarnings({"PMD.ShortVariable", "PMD.UseLocaleWithCaseConversions"})
public class IsCaseIndependentComparator implements Comparator<Object> {
    public int compare(Object o1, Object o2) {
        String s1 = (String) o1;
        String s2 = (String) o2;
        int result;

        result = s1.toUpperCase().compareTo(s2.toUpperCase());
        if (result == 0) return s1.compareTo(s2);
        else return result;
    }
}
