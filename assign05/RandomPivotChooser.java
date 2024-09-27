package assign05;

import java.util.ArrayList;
import java.util.Random;

/**
 * this class is a pivot chooser for the quicksort algorithm
 * it returns a random index in the array
 * @param <T> a generic type
 * @version 09/27/24
 * @author Isaac Buehner and Wallace McCarthy
 */
public class RandomPivotChooser<T extends Comparable<? super T>> implements PivotChooser<T>{
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
        Random rng = new Random();
        return rng.nextInt(leftIndex, rightIndex);
    }
}
