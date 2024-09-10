package assign03;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * the SortedArrayList class allows the construction of empty arrays where added elements are inserted in
 * the correct place either by the default sorting method, or by a sorting method defined by the user
 * all searching and inserting is done with the help of binary search
 * @version 9/7/2024
 * @author Wallace McCarthy and Isaac Buehner
 */
public class SortedArrayList <T> implements SortedList<T> {

    private Comparator<? super T> cmp;
    private T[] arr;
    private int size;

    @SuppressWarnings("unchecked")
    public SortedArrayList(){
        this.arr =  (T[]) new Object[5];
    }
    @SuppressWarnings("unchecked")
    public SortedArrayList(Comparator<? super T> cmp){
        this.cmp = cmp;
        this.arr =  (T[]) new Object[5];
    }

    /**
     * Removes all of the elements from this sorted list and resizes the backing array to be a size of 5.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        this.arr =  (T[]) new Object[5];
        this.size = 0;
    }

    /**
     * Determines whether the specified element exists in this sorted list.
     *
     * @param element - the element whose existence is being checked
     * @return true if the element exists in this sorted list, false otherwise
     */
    @Override
    public boolean contains(T element) {
        if (this.size == 0){
            return false;
        }
        return this.arr[binarySearch(this.arr, element, cmp)].equals(element);
    }

    /**
     * Determines the number of elements in this sorted list that are equal to the
     * specified target.
     *
     * @param target - the target whose existence is being counted
     * @return the number of elements in this sorted list that are equal to the
     * specified target
     */
    @Override
    public int countEntries(T target) {
        int count = 0;
        int idx = binarySearch(this.arr, target, cmp);

        if((this.size == 0) || !arr[idx].equals(target)){
            return count;
        }

        while(arr[idx].equals(target)){
            count++;
            idx --;
        }
        idx += count + 1;
        while(arr[idx] != null && arr[idx].equals(target)){
            count++;
            idx ++;
        }
        return count;
    }

    /**
     * Inserts the specified element into this sorted list in a sorted manner. If this list already has this element,
     * the new element will be placed in front of any entries that are the same as itself.
     *
     * @param element - the element to insert
     */
    @SuppressWarnings("unchecked")
    @Override
    public void insert(T element) {
        this.size++;
        if(size == 1){
            arr[0] = element;
            return;
        }
        int idx = binarySearch(this.arr, element, cmp);
        this.expandArrayCheck();
        for (int i = this.size - 1; i > idx; i--) {
            this.arr[i] = this.arr[i - 1];
        }
        arr[idx] = element;
    }

    /**
     * Determines whether this sorted list contains any elements.
     *
     * @return true if this sorted list contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gets the largest element in this sorted list.
     *
     * @return the largest element in this sorted list
     * @throws NoSuchElementException if this sorted list is empty
     */
    @Override
    public T max() throws NoSuchElementException {
        if (size == 0){
            throw new NoSuchElementException();
        }
        return arr[size - 1];
    }

    /**
     * Gets the median element in this sorted list. If this sorted list contains an
     * even number of elements, gets the larger of two middle elements.
     *
     * @return the median element in this sorted list
     * @throws NoSuchElementException if this sorted list is empty
     */
    @Override
    public T median() throws NoSuchElementException {
        if (size == 0){
            throw new NoSuchElementException();
        }
        return arr[size / 2];
    }

    /**
     * Gets the smallest element in this sorted list.
     *
     * @return the smallest element in this sorted list
     * @throws NoSuchElementException if this sorted list is empty
     */
    @Override
    public T min() throws NoSuchElementException {
        if (size == 0){
            throw new NoSuchElementException();
        }
        return arr[0];
    }

    /**
     * Gets the number of elements in this sorted list.
     *
     * @return the number of elements in this sorted list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Generates an array containing all of elements in this sorted list, in order.
     *
     * @return an array containing all of elements in this sorted list
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object[] toArray() {
        T[] output = (T[]) new Object[this.size];
        for (int i = 0; i < size; i++) {
            output[i] = this.arr[i];
        }
        return output;
    }

    /**
     * uses binary to return the index of an element if it is in the array.
     * if the element is not in the list, returns the closest index to the element.
     *
     * @param arr array to be searched
     * @param target element to be searched for
     * @param cmp custom comparator for searching with
     * @return an index for the array
     */
    @SuppressWarnings("unchecked")
    private int binarySearch(T[] arr, T target, Comparator<? super T> cmp) {
        // comparator search
        if (cmp == null) {
            int l = 0, r = this.size - 1, mid = (r - l) / 2 + l;

            while(l < r) {
                if (arr[mid].equals(target)) {
                    break;
                }
                if (((Comparable<? super T>)arr[mid]).compareTo(target) > 0) {
                    r = mid - 1;
                }
                else {
                    l = mid + 1;
                }
                mid = (r - l) / 2 + l;

            }
            return mid;
        // standard search
        } else {
            int l = 0, r = this.size - 1, mid = (r - l) / 2 + l;

            while (l < r) {
                if (arr[mid].equals(target)) {
                    break;
                }
                if (cmp.compare(this.arr[mid], target) > 0) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
                mid = (r - l) / 2 + l;

            }
            return mid;
        }
    }

    /**
     * checks for the array to be full and double the backing arrays size if it is
     */
    @SuppressWarnings("unchecked")
    private void expandArrayCheck() {
        if (this.size > arr.length) {
            T[] temp = (T[]) new Object[arr.length * 2];
            for (int i = 0; i < size - 1; i++) {
                temp[i] = arr[i];
            }
            this.arr = temp;
        }
    }
}
