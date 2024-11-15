package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryMinHeap<E> implements PriorityQueue<E> {
    private Object[] backingArray = new Object[10];
    private Comparator<? super E> comparator;
    private int size;

    public BinaryMinHeap() {
        size = 0;
    }

    public BinaryMinHeap(Comparator<? super E> comparator) {
        size = 0;
        this.comparator = comparator;
    }

    public BinaryMinHeap(List<? extends E> list) {
        size = 0;

    }

    public BinaryMinHeap(List<? extends E> list, Comparator<? super E> comparator) {
        size = 0;
        this.comparator = comparator;
    }
    /**
     * Adds the given element to this priority queue.
     * O(1) in the average case, O(log N) in the worst case
     *
     * @param element - element to be added to this priority queue
     */
    @Override
    public void add(E element) {

    }

    /**
     * Gets the highest-priority element this priority queue.
     * O(1)
     *
     * @return highest-priority element in this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        return null;
    }

    /**
     * Gets and removes the highest-priority element this priority queue.
     * O(log N)
     *
     * @return highest-priority element in this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public E extract() throws NoSuchElementException {
        return null;
    }

    /**
     * Gets the number of elements in this priority queue.
     * O(1)
     *
     * @return number of elements in this priority queue
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Determines whether this priority queue is empty.
     * O(1)
     *
     * @return true if this priority queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Empties this priority queue of elements.
     * O(1)
     */
    @Override
    public void clear() {

    }

    /**
     * Generates an array of the elements in this priority queue,
     * in the same order they appear in the backing array.
     * O(N)
     * <p>
     * (NOTE: This method is used for grading. The root element
     * must be stored at index 0 in the returned array, regardless of
     * whether it is in stored there in the backing array.)
     */
    @Override
    public Object[] toArray() {
        return new Object[0];
    }
}
