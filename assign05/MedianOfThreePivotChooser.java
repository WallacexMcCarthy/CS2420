package assign05;

import java.util.ArrayList;

/**
 * this class is a pivot chooser for the quicksort algorithm
 * it returns the index of the median value between the first, last, and middle values in the array
 * @param <T> a generic type
 * @version 09/27/24
 * @author Isaac Buehner and Wallace McCarthy
 */
public class MedianOfThreePivotChooser <T extends Comparable<? super T>> implements PivotChooser<T>{
    /**
     * Selects an element in the given ArrayList to serve as the quicksort pivot.
     *
     * @param list       - list containing a portion to be sorted
     * @param leftIndex  - position of first item in the sublist to be sorted
     * @param rightIndex - position of the last item in the sublist to be sorted
     * @return index of the list element selected to serve as the pivot
     */
    @Override
    public int getPivotIndex(ArrayList<T> list, int leftIndex, int rightIndex) {
        T element1 = list.get(leftIndex);
        T element2 = list.get((rightIndex + leftIndex) / 2);
        T element3 = list.get(rightIndex);

        if ((element1.compareTo(element2) >= 0 && element2.compareTo(element3) >= 0) ||
                element3.compareTo(element2) >= 0 && element2.compareTo(element1) >= 0) {
            return (rightIndex + leftIndex) / 2;
        } else if ((element1.compareTo(element3) >= 0 && element3.compareTo(element2) >= 0) ||
                element2.compareTo(element3) >= 0 && element3.compareTo(element1) >= 0) {
            return rightIndex;
        } else {
            return leftIndex;
        }

    }
}
