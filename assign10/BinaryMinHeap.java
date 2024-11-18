package assign10;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryMinHeap<E extends Comparable<? super E>> implements PriorityQueue<E> {
    private Object[] backingArray;
    private Comparator<? super E> comparator;
    private int size;

    public BinaryMinHeap() {
        this.size = 0;
        backingArray = new Object[10];
    }

    public BinaryMinHeap(Comparator<? super E> comparator) {
        this.size = 0;
        this.comparator = comparator;
        backingArray = new Object[10];
    }

    public BinaryMinHeap(List<? extends E> list) {
        this.size = 0;
        backingArray = new Object[10];
        heapify(list);
    }

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
        size++;
        percolateUp(element);
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
        percolateDown(getFromArray(1));
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

    @SuppressWarnings("unchecked")
    private E getFromArray(int index){
        return (E) backingArray[index];
    }

    private void heapify(List<? extends E> list) {
        if(list.size() == 0){
            return;
        }else{
            this.backingArray = new Object[list.size() + 1];
            for (int i = 0; i < list.size(); i++) {
                this.backingArray[i + 1] = list.get(i);
            }
            this.size = list.size();
            heapifyDown(1);
        }

    }

    private void heapifyDown(int index) {
        int smallest = index;
        int leftChild = index * 2;
        int rightChild = index * 2 + 1;
        if(leftChild <= this.size && innerCompare(getFromArray(leftChild), getFromArray(smallest)) < 0) {
            smallest = leftChild;
        }
        if(rightChild <= this.size && innerCompare(getFromArray(rightChild), getFromArray(smallest)) < 0) {
            smallest = rightChild;
        }
        if(smallest != index) {
            E temp = getFromArray(index);
            backingArray[index] = getFromArray(smallest);
            backingArray[smallest] = temp;
            heapifyDown(smallest);
        }
    }

    private void percolateUp(E element) {
        int currentIndex = this.size;
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
    private void percolateDown(E element) {
        int currentIndex = 1;
        while(2 * currentIndex + 1 >= size){
            int leftChild = 2 * currentIndex;
            int rightChild = 2 * currentIndex + 1;
            int smallestChild = leftChild;
            if(innerCompare(getFromArray(leftChild), getFromArray(rightChild)) > 0){
                smallestChild = rightChild;
            }
            if(innerCompare(getFromArray(smallestChild), element) >= 0){
                break;
            }else{
                E temp = getFromArray(smallestChild);
                backingArray[currentIndex] = element;
                backingArray[smallestChild] = temp;
                currentIndex = smallestChild;
            }
        }
    }

    private int innerCompare(E item1, E item2) {
        if (!(comparator == null)) {
            return comparator.compare(item1, item2);
        }
        return item1.compareTo(item2);
    }

    private void resize() {
        Object[] newArr = new Object[backingArray.length * 2];
        for (int i = 1; i < this.size; i++) {
            newArr[i] = backingArray[i];
        }
        backingArray = newArr;
    }
}
