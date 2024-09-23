package assign05;

import java.util.ArrayList;

public class QuickSorter<T extends Comparable<? super T>> implements Sorter<T> {

    private PivotChooser<T> chooser;
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

    private void quickSort(ArrayList<T> list, int leftBound, int rightBound) {
        if(rightBound - leftBound < 1) {
            return;
        }
        int pivot = this.chooser.getPivotIndex(list, leftBound, rightBound);

        int partitionIndex = partition(list, pivot, leftBound, rightBound);
        quickSort(list, leftBound, partitionIndex - 1);
        quickSort(list, partitionIndex + 1, rightBound);
    }

    private int partition(ArrayList<T> list, int pivot, int leftBound, int rightBound) {
        T temp = list.get(rightBound);
        list.set(rightBound, list.get(pivot));
        list.set(pivot, temp);

        int leftPointer = leftBound;
        int rightPointer = rightBound;

        while(leftPointer < rightPointer) {

            while (list.get(leftPointer).compareTo(list.get(rightBound)) < 0) {
                leftPointer++;
            }

            while (list.get(rightPointer).compareTo(list.get(rightBound)) > 0) {
                rightPointer--;
            }

            if (leftPointer > rightPointer) {
                break;
            }

            temp = list.get(leftPointer);
            list.set(leftPointer, list.get(rightPointer));
            list.set(rightPointer, temp);
        }

        temp = list.get(leftPointer);
        list.set(leftPointer, list.get(rightBound));
        list.set(rightBound, temp);

        return leftPointer;
    }

}
