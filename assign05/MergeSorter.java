package assign05;

import java.util.ArrayList;
import java.util.Comparator;

public class MergeSorter <T extends Comparable<? super T>> implements Sorter<T>{
    private int threshold;
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

    private void mergeSort(ArrayList<T> list, ArrayList<T> auxArray, int left, int right){
        if(right - left < 2){
            return;
        }
        int mid = (right- left) / 2;
        mergeSort(list, auxArray, left, mid);
        mergeSort(list, auxArray, mid + 1, right);
        merge(auxArray, mid, left, right);
    }
    private void merge(ArrayList<T> auxArray, int mid, int left, int right){

    }

    /**
     * This method sorts a given array with insertion sort using a given comparator.
     * @param arr the array to be sorted
     * @param <T> the generic type of the array
     */
    private <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr){
        for (int i = 1; i < arr.size(); i++) {
            int counter = 1;
            T element = arr.get(i);
            while(i-counter >= 0 && element.compareTo(arr.get(i - counter)) < 0){
                T temp = arr.get(i - counter);
                arr.set(i - counter, element);
                arr.set(i-counter + 1, temp);
                counter++;
            }
        }
    }
}
