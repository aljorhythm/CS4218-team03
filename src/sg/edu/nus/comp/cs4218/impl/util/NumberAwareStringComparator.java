package sg.edu.nus.comp.cs4218.impl.util;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberAwareStringComparator implements Comparator<CharSequence> {
    public static final NumberAwareStringComparator INSTANCE =
            new NumberAwareStringComparator();

    private static final Pattern PATTERN = Pattern.compile("(\\D*)(\\d*)");

    public NumberAwareStringComparator() {
        // not used
    }

    public int compare(CharSequence charSequence1, CharSequence charSequence2) {
        Matcher matcher1 = PATTERN.matcher(charSequence1);
        Matcher matcher2 = PATTERN.matcher(charSequence2);

        // The only way find() could fail is at the end of a string
        while (matcher1.find() && matcher2.find()) {
            // matcher.group(2) fetches any digits captured by the
            // second parentheses in PATTERN.
            if (matcher2.group(2).isEmpty()) {
                return matcher1.group(2).isEmpty() ? 0 : +1;
            } else if (matcher1.group(2).isEmpty()) {
                return -1;
            }

            // matcher.group(1) fetches any non-digits captured by the
            // first parentheses in PATTERN.
            int nonDigitCompare = matcher1.group(1).compareTo(matcher2.group(1));
            if (0 != nonDigitCompare) {
                return nonDigitCompare;
            }


            BigInteger number1 = new BigInteger(matcher1.group(2));
            BigInteger number2 = new BigInteger(matcher2.group(2));
            int numberCompare = number1.compareTo(number2);
            if (0 != numberCompare) {
                return numberCompare;
            }
        }

        // Handle if one string is a prefix of the other.
        // Nothing comes before something.
        return matcher1.hitEnd() && matcher2.hitEnd() ? 0 :
                matcher1.hitEnd() ? -1 : +1;
    }
}
