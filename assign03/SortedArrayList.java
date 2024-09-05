package assign03;

import java.util.Comparator;
import java.util.NoSuchElementException;


public class SortedArrayList <T> implements SortedList<T> {
    public SortedArrayList(Comparator<? super T> cmp){

    }

    /**
     * Removes all of the elements from this sorted list.
     */
    @Override
    public void clear() {
        
    }

    /**
     * Determines whether the specified element exists in this sorted list.
     *
     * @param element - the element whose existence is being checked
     * @return true if the element exists in this sorted list, false otherwise
     */
    @Override
    public boolean contains(T element) {
        return false;
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
        return 0;
    }

    /**
     * Inserts the specified element into this sorted list.
     *
     * @param element - the element to insert
     */
    @Override
    public void insert(T element) {

    }

    /**
     * Determines whether this sorted list contains any elements.
     *
     * @return true if this sorted list contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Gets the largest element in this sorted list.
     *
     * @return the largest element in this sorted list
     * @throws NoSuchElementException if this sorted list is empty
     */
    @Override
    public T max() throws NoSuchElementException {
        return null;
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
        return null;
    }

    /**
     * Gets the smallest element in this sorted list.
     *
     * @return the smallest element in this sorted list
     * @throws NoSuchElementException if this sorted list is empty
     */
    @Override
    public T min() throws NoSuchElementException {
        return null;
    }

    /**
     * Gets the number of elements in this sorted list.
     *
     * @return the number of elements in this sorted list
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Generates an array containing all of elements in this sorted list, in order.
     *
     * @return an array containing all of elements in this sorted list
     */
    @Override
    public Object[] toArray() {
        return new Object[0];
    }
}
