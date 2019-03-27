package sg.edu.nus.comp.cs4218.impl.util;

import java.util.Comparator;

/**
 * This comparator is ued to sort strings numerically.
 */
@SuppressWarnings("PMD.ShortVariable")
public class IsFirstWordNumberComparator implements Comparator<Object> {
    public int compare(Object o1, Object o2) {
        String s1 = (String) o1;
        String s2 = (String) o2;

        double difference = StringUtils.parseDoubleContainedInString(s1) -
                StringUtils.parseDoubleContainedInString(s2);

        if (difference < 0) {
            return (int) Math.floor(difference);
        } else if (difference > 0) {
            return (int) Math.ceil(difference);
        } else {
            return s1.compareTo(s2);
        }
    }
}
