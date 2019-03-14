/**
 * From Team 02
 */
package sg.edu.nus.comp.cs4218.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortApplicationSortingHelper {

    private SortApplicationNumericComparatorHelper numericSorter;
    private SortApplicationCaseInsensitiveComparatorHelper caseIgnoreSorter;
    private SortApplicationComparatorHelper normalSorter;

    private final boolean isFirstWordNumber;
    private final boolean isReverseOrder;
    private final boolean isCaseIndependent;

    private final List<String> tempList;

    public SortApplicationSortingHelper(String[] toSort, Boolean isFirstWordNumber, Boolean isReverseOrder, Boolean isCaseIndependent) {
        this.isFirstWordNumber = isFirstWordNumber;
        this.isCaseIndependent = isCaseIndependent;
        this.isReverseOrder = isReverseOrder;
        this.tempList = Arrays.asList(toSort);
    }

    /**
     * Sorts an array of String denoted by toSort.
     * How it is sorted depends on the Boolean options isFirstWordNumber, isReverseOrder and isCaseIndependent.
     * The hierarchy of sorting is as so: Small Letter > Capital Letter > Numbers > Others.
     *
     * @return A single String object where the String objects within toSort have been sorted and concatenated.
     * The different String objects are concatenated using the newline character.
     */
    public String sort() {

        if (isFirstWordNumber || isReverseOrder || isCaseIndependent) {
            if (isReverseOrder) {
                caseIgnoreSorter = new SortApplicationCaseInsensitiveComparatorHelper(true);
                numericSorter = new SortApplicationNumericComparatorHelper(true);

                if (isFirstWordNumber && isCaseIndependent) {
                    Collections.sort(tempList, caseIgnoreSorter);
                    Collections.sort(tempList, numericSorter);
                } else if (isFirstWordNumber) {
                    Collections.sort(tempList, numericSorter);
                } else {
                    Collections.sort(tempList, caseIgnoreSorter);
                }

            } else {

                caseIgnoreSorter = new SortApplicationCaseInsensitiveComparatorHelper(false);
                numericSorter = new SortApplicationNumericComparatorHelper(false);

                if (isFirstWordNumber && isCaseIndependent) {
                    Collections.sort(tempList, caseIgnoreSorter);
                    Collections.sort(tempList, numericSorter);
                } else if (isFirstWordNumber) {
                    Collections.sort(tempList, numericSorter);
                } else {
                    Collections.sort(tempList, caseIgnoreSorter);
                }
            }
        } else {
            normalSorter = new SortApplicationComparatorHelper(false);
            Collections.sort(tempList, normalSorter);
        }

        // Put everything back into a string
        StringBuilder result = new StringBuilder();
        for (String temp : tempList) {
            String temp2 = temp + "\n";
            result.append(temp2);
        }

        // To remove the trailing newline character
        result = result.deleteCharAt(result.length() - 1);

        return result.toString();
    }
}
