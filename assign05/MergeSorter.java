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

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(3);
        arr.add(2);

        MergeSorter<Integer> merger = new MergeSorter<>(3);
        merger.sort(arr);
        System.out.println(arr);
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
//        if (list.size() < threshold) {
//            insertionSort(list);
//            System.out.println("insert");
//            System.out.println(list.toString());
//            return;
//        }
        mergeSort(list, auxArray, 0, list.size());
    }

    private void mergeSort(ArrayList<T> list, ArrayList<T> auxArray, int left, int right){
        if(right - left < 2){
            return;
        }
        int mid = (right + left) / 2;
        mergeSort(list, auxArray, left, mid);
        mergeSort(list, auxArray, mid , right);
        merge(list, auxArray, mid, left, right);

    }

    private void merge(ArrayList<T> list, ArrayList<T> auxArray, int mid, int left, int right){
//        if(right - left < threshold){
//            System.out.println("Insertion sort: ");
//            insertionSort(list, left , right);
//            for (int i = left; i < right; i++) {
//                auxArray.set(i, list.get(i));
//            }
//            System.out.println(list);
//            return;
//        }
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
        if (leftPoint + left >= mid) {
            for (int i = rightPoint + mid; i < right; i++) {
                auxArray.set(i, list.get(rightPoint + mid));
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
     * This method sorts a given array with insertion sort using a given comparator.
     * @param arr the array to be sorted
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
