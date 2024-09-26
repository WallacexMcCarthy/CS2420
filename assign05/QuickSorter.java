package assign05;

import com.sun.jdi.InterfaceType;

import java.util.ArrayList;

/**
 * this class is for sorting an ArrayList of a generic type using the QuickSort technique
 * pivots can be chosen using an extended class of PivotChooser
 * @param <T> a generic type
 * @version 09/23/24
 * @author Wallace McCarthy and Isaac Buehner
 */
public class QuickSorter<T extends Comparable<? super T>> implements Sorter<T> {
    private PivotChooser<T> chooser;

    /**
     * constructor for a QuickSorter
     * @param chooser the pivot chooser for the quick sort process
     */
    public QuickSorter(PivotChooser<T> chooser){
        this.chooser = chooser;
    }

    /**
     * Sorts the given list.
     *
     * @param list - list to be sorted
     */
    @Override
    public void sort(ArrayList<T> list) {
        quickSort(list, 0, list.size() - 1);
    }

    /**
     * private recursive method that quick sorts sections of the list
     * @param list the list to be sorted
     * @param leftBound the left bound of the subsection
     * @param rightBound the right bound of the subsection
     */
    private void quickSort(ArrayList<T> list, int leftBound, int rightBound) {
        if(rightBound - leftBound < 1) {
            return;
        }
        int pivot = this.chooser.getPivotIndex(list, leftBound, rightBound);

        int partitionIndex = partition(list, pivot, leftBound, rightBound);
        quickSort(list, leftBound, partitionIndex - 1);
        quickSort(list, partitionIndex + 1, rightBound);
    }

    /**
     * private method that sorts a section of the list
     * @param list the list to be sorted
     * @param pivot the pivot point to sort by
     * @param leftBound the left bound of the subsection
     * @param rightBound the right bound of the subsection
     * @return the final place of the pivot element
     */
    private int partition(ArrayList<T> list, int pivot, int leftBound, int rightBound) {
        T temp = list.get(rightBound);
        list.set(rightBound, list.get(pivot));
        list.set(pivot, temp);

        int leftPointer = leftBound;
        int rightPointer = rightBound - 1;
        while(leftPointer < rightPointer) {

            while (list.get(leftPointer).compareTo(list.get(rightBound)) < 0) {
                leftPointer++;
            }
            while (list.get(rightPointer).compareTo(list.get(rightBound)) >= 0 && rightPointer > 0) {
                rightPointer--;
            }

            if (leftPointer > rightPointer) {
                break;
            }
            temp = list.get(leftPointer);
            list.set(leftPointer, list.get(rightPointer));
            list.set(rightPointer, temp);
        }
        if (list.get(leftPointer).compareTo(list.get(rightBound)) >= 0) {
            temp = list.get(leftPointer);
            list.set(leftPointer, list.get(rightBound));
            list.set(rightBound, temp);
        }
        return leftPointer;
    }

}
