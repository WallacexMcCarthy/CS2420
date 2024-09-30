package assign05;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * this class is for sorting an ArrayList of a generic type using the MergeSort technique
 * it uses the MergeSort technique and switches to Insertion sort if the sub-lists
 * are equal to or less than a given threshold
 * @param <T> the generic type
 * @version 09/23/24
 * @author Isaac Buehner and Wallace McCarthy
 */
public class MergeSorter <T extends Comparable<? super T>> implements Sorter<T>{
    private int threshold;

    /**
     * constructor for a MergeSorter
     * @param threshold is the size of sublist that will start using insertion sort instead of merge sort
     * @throws IllegalArgumentException
     */
    public MergeSorter(int threshold) throws IllegalArgumentException{
        if(threshold < 0){
            throw new IllegalArgumentException();
        }
        this.threshold = threshold;
    }

    /**
     * Sorts the given list.
     *
     * @param list - list to be sorted
     */
    @Override
    public void sort(ArrayList<T> list) {
        ArrayList<T> auxArray = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            auxArray.add(null);
        }
        mergeSort(list, auxArray, 0, list.size());
    }

    /**
     * private recursive method for splitting a list to be sorted
     * @param list list to be sorted
     * @param auxArray auxiliary array for sorting
     * @param left the left bound of a subsection of the list
     * @param right the right bound of a subsection of the list
     */
    private void mergeSort(ArrayList<T> list, ArrayList<T> auxArray, int left, int right){
        if(right - left < 2){
            return;
        }
        if(right - left <= threshold){
            insertionSort(list, left, right);
            for (int i = left; i < right; i++) {
                auxArray.set(i, list.get(i));
            }
            return;
        }
        int mid = (right + left) / 2;

        mergeSort(list, auxArray, left, mid);
        mergeSort(list, auxArray, mid , right);

        merge(list, auxArray, mid, left, right);
    }

    /**
     * private method that merges 2 subsections together in sorted form
     * @param list the list to be sorted
     * @param auxArray auxiliary array for sorting
     * @param mid the middle index of the subsection
     * @param left the left bound of the subsection
     * @param right the right bound of the subsection
     */
    private void merge(ArrayList<T> list, ArrayList<T> auxArray, int mid, int left, int right){
        int leftPoint = 0;
        int rightPoint = 0;

        while (leftPoint + left < mid && rightPoint + mid + 1 <= right) {
            if (list.get(leftPoint + left).compareTo(list.get(rightPoint + mid)) > 0) {
                auxArray.set(left + leftPoint + rightPoint, list.get( mid + rightPoint));
                rightPoint++;
            } else {
                auxArray.set(left + leftPoint + rightPoint, list.get(left + leftPoint));
                leftPoint++;
            }
        }
        if (left + leftPoint >= mid) {
            for (int i = mid + rightPoint; i < right; i++) {
                auxArray.set(i, list.get(mid + rightPoint));
                rightPoint++;
            }
        } else {
            for (int i = left + rightPoint + leftPoint; i < right; i++) {
                auxArray.set(i, list.get(left + leftPoint));
                leftPoint++;
            }
        }
        for (int i = left; i < right; i++) {
            list.set(i, auxArray.get(i));
        }
    }

    /**
     * private method that sorts a given array with insertion sort
     * @param arr the array to be sorted
     * @param left the left bounds of the list to be sorted
     * @param right the right bounds of the list to be sorted
     * @param <T> the generic type of the array
     */
    private <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr, int left, int right){
        for (int i = left + 1; i < right; i++) {
            int counter = 1;
            T element = arr.get(i);

            while(i-counter >= left && element.compareTo(arr.get(i - counter)) < 0){
                T temp = arr.get(i - counter);
                arr.set(i - counter, element);
                arr.set(i-counter + 1, temp);
                counter++;
            }
        }
    }
}
