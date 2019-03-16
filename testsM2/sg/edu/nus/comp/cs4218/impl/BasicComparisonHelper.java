/**
 * From Team 02
 */
package sg.edu.nus.comp.cs4218.impl;

public final class BasicComparisonHelper {
    private BasicComparisonHelper() {
    }


    /**
     * Compares two sequences of characters.
     * The hierarchy is as so: Small Letter > Capital Letter > Numbers > Others.
     *
     * @param string1CharArr The first sequence of characters to be compared.
     * @param string2CharArr The second sequence of characters to be compared.
     * @param string1Length  The length of the first sequence of characters.
     * @param string2Length  The length of the second sequence of characters.
     * @return 1 if string1CharArr is larger than string2CharArr, 0 if both are exactly identical,
     * and -1 if string2CharArr is larger than string1CharArr.
     */
    public static int compare(int string1Length, int string2Length, char[] string1CharArr, char... string2CharArr) {
        int index = 0;
        int result = 10;
        int temp;
        while (index < string1Length && index < string2Length) {
            char string1CurrChar = string1CharArr[index];
            char string2CurrChar = string2CharArr[index];
            if (string1CurrChar == string2CurrChar) {
                index++;
                continue;
            }
            if (!Character.isAlphabetic(string1CurrChar) && !Character.isDigit(string1CurrChar)) {
                if (Character.isAlphabetic(string2CurrChar) || Character.isDigit(string2CurrChar)) {
                    result = -1;
                } else {
                    temp = compareCharacter(string1CurrChar, string2CurrChar);
                    if (temp == 0) {
                        index++;
                        continue;
                    } else {
                        result = temp;
                    }
                }
                break;
            }
            if (Character.isDigit(string1CurrChar)) {
                if (Character.isDigit(string2CurrChar)) {
                    temp = compareCharacter(string1CurrChar, string2CurrChar);
                    if (temp == 0) {
                        index++;
                        continue;
                    } else {
                        result = temp;
                    }
                } else if (Character.isAlphabetic(string2CurrChar)) {
                    result = -1;
                } else {
                    result = 1;
                }
                break;
            } else {
                if (Character.isAlphabetic(string2CurrChar)) {
                    temp = compareCharacter(string1CurrChar, string2CurrChar);
                    if (temp == 0) {
                        index++;
                        continue;
                    } else {
                        result = temp;
                    }
                } else {
                    result = 1;
                }
                break;
            }
        }
        return result;
    }

    /**
     * Compares two characters from the same class (e.g. Numbers, Alphabets etc).
     * The comparison is done so using their ASCII value.
     *
     * @param string1CurrChar The first character to be compared.
     * @param string2CurrChar The second character to be compared.
     * @return 1 if string1CurrChar is larger than string2CurrChar, 0 if both are the same character,
     * and -1 if string2CurrChar is larger than string1CurrChar.
     */
    private static int compareCharacter(char string1CurrChar, char string2CurrChar) {
        if (string1CurrChar < string2CurrChar) {
            return -1;
        } else if (string1CurrChar > string2CurrChar) {
            return 1;
        } else {
            return 0;
        }
    }
}
