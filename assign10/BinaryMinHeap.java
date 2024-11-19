package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * this class is a Binary Min Heap data structure that uses the PriorityQueue interface in the same package
 * it uses percolation to perform its operations
 * @version 11/18/2024
 * @author Wallace McCarthy and Isaac Buehner
 */
public class BinaryMinHeap<E> implements PriorityQueue<E> {
    private Object[] backingArray;
    private Comparator<? super E> comparator;
    private int size;

    /**
     * the default constructor for a BinaryMinHeap
     */
    public BinaryMinHeap() {
        this.size = 0;
        backingArray = new Object[10];
    }

    /**
     * this constructor allows a comparator to be used for comparisons rather than the default comparison
     * @param comparator the comparator to be used
     */
    public BinaryMinHeap(Comparator<? super E> comparator) {
        this.size = 0;
        this.comparator = comparator;
        backingArray = new Object[10];
    }

    /**
     * this constructor creates a BinaryMinHeap from a given list using the private heapify method
     * @param list the list to create the heap from
     */
    public BinaryMinHeap(List<? extends E> list) {
        this.size = 0;
        backingArray = new Object[10];
        heapify(list);
    }

    /**
     * this constructor allows for a comparator to be used instead of default behavior
     * and creates the heap from a given list
     * @param list the list to create the heap from
     * @param comparator the comparator to be used
     */
    public BinaryMinHeap(List<? extends E> list, Comparator<? super E> comparator) {
        this.size = 0;
        this.comparator = comparator;
        backingArray = new Object[10];
        heapify(list);
    }

    /**
     * Adds the given element to this priority queue.
     * O(1) in the average case, O(log N) in the worst case
     *
     * @param element - element to be added to this priority queue
     */
    @Override
    public void add(E element) {
        if (size + 1 >= backingArray.length) {
            resize();
        }
        percolateUp(element);
        size++;
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
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return getFromArray(1);
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
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        E temp = getFromArray(1);
        backingArray[1] = backingArray[size];
        size--;
        percolateDown(1);
        return temp;
    }

    /**
     * Gets the number of elements in this priority queue.
     * O(1)
     *
     * @return number of elements in this priority queue
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Determines whether this priority queue is empty.
     * O(1)
     *
     * @return true if this priority queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Empties this priority queue of elements.
     * O(1)
     */
    @Override
    public void clear() {
        this.size = 0;
        this.backingArray = new Object[10];
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
        Object[] out = new Object[this.size];
        for(int i = 0; i < this.size; i++) {
            out[i] = backingArray[i + 1];
        }
        return out;
    }

    /**
     * helper method to avoid unnecessary warnings when getting from the backing array
     * @param index the index to retrieve from the backing array
     * @return the item at the given index
     */
    @SuppressWarnings("unchecked")
    private E getFromArray(int index){
        return (E) backingArray[index];
    }

    /**
     * helper method for creating the heap from a list
     * uses heapBuild algorithm to create the heap more efficiently than adding N times
     * @param list the list to build the heap from
     */
    private void heapify(List<? extends E> list) {
        if(!list.isEmpty()){
            this.backingArray = new Object[list.size() + 2];
            for (int i = 0; i < list.size(); i++) {
                this.backingArray[i + 1] = list.get(i);
                size++;
            }
            int parent = size;
            while(parent/2 >= 1){
                percolateDown(parent / 2);
                percolateDown(parent / 2 + 1);
                parent = parent / 2;
            }
        }
    }

    /**
     * helper method for adding elements to the heap
     * uses up percolation to move elements to the correct places
     * @param element the element to add
     */
    private void percolateUp(E element) {
        int currentIndex = this.size + 1;
        while (currentIndex > 1) {
            if (innerCompare(element, getFromArray(currentIndex / 2)) < 0) {
                backingArray[currentIndex] = getFromArray(currentIndex / 2);
                currentIndex = currentIndex / 2;
            } else {
                backingArray[currentIndex] = element;
                return;
            }
        }
        backingArray[1] = element;
    }

    /**
     * helper method for removing elements from the heap
     * uses down percolation to move the elements to the correct places
     * @param index the index to remove from the backing array
     */
    private void percolateDown(int index) {
        int currentIndex = index;
        while(2 * currentIndex <= size){
            int leftChild = 2 * currentIndex;
            int rightChild = 2 * currentIndex + 1;
            int smallestChild;
            if (getFromArray(rightChild) == null) {
                smallestChild = leftChild;
            }
            else if (innerCompare(getFromArray(leftChild), getFromArray(rightChild)) > 0){
                smallestChild = rightChild;
            }
            else {
                smallestChild = leftChild;
            }
            if(innerCompare(getFromArray(smallestChild), getFromArray(currentIndex)) >= 0){
                break;
            }else{
                E temp = getFromArray(currentIndex);
                backingArray[currentIndex] = getFromArray(smallestChild);
                backingArray[smallestChild] = temp;
                currentIndex = smallestChild;
            }
        }
    }

    /**
     * helper method to avoid code duplication and unnecessary warnings when comparing elements
     * uses the given comparator if available and uses default behavior otherwise
     * @param item1 the first element to be compared
     * @param item2 the first element to be compared
     * @return an int representing which element is bigger
     */
    @SuppressWarnings("unchecked")
    private int innerCompare(E item1, E item2) {
        if (!(comparator == null)) {
            return comparator.compare(item1, item2);
        }
        return ((Comparable< ? super E>)item1).compareTo(item2);
    }

    /**
     * helper method that doubles the size of the backing array when it is full
     */
    private void resize() {
        Object[] newArr = new Object[backingArray.length * 2];
        for (int i = 1; i < this.size + 1; i++) {
            newArr[i] = backingArray[i];
        }
        backingArray = newArr;
    }
}
