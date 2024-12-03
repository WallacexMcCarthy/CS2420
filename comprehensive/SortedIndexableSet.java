package comprehensive;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * this class is a data structure that acts as a sorted set that is indexable
 * it uses a TreeSet to maintain order and set properties and an ArrayList to allow for indexing
 * @param <T> the generic type for the class
 * @version 12/2/2024
 * @author Isaac Buehner and Wallace McCarthy
 */
public class SortedIndexableSet<T extends Comparable<T>> {
    private final TreeSet<T> tree;
    private final ArrayList<T> array;

    /**
     * default constructor for a SortedIndexableSet object
     */
    public SortedIndexableSet() {
        this.tree = new TreeSet<>();
        this.array = new ArrayList<>();
    }

    /**
     * adds a given item to the structure
     * @param item the item to add
     */
    public void add(T item) {
        tree.add(item);
        if(!array.contains(item)){
            array.add(findInsertionIndex(item), item);
        }
    }

    /**
     * removes an item from the structure
     * @param item the item to remove
     */
    public void remove(T item) {
        tree.remove(item);
        array.remove(item);
    }

    /**
     * gets an item from the ArrayList backing structure using an index
     * @param index the index to get
     * @return the item at the index
     */
    public T getByIndex(int index) {
        if (index < 0 || index >= array.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        return array.get(index);
    }

    /**
     * gets the number of items in the structure
     * @return the size of the structure
     */
    public int size() {
        return array.size();
    }

    /**
     * helper method that finds the index for an item to be inserted at in the ArrayList backing structure
     * uses binary search to find the correct position to maintain sorted order
     * @param item the item to find an index for
     * @return the index for the item
     */
    private int findInsertionIndex(T item) {
        int low = 0;
        int high = array.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            T midKey = array.get(mid);
            int comparison = midKey.compareTo(item);

            if (comparison < 0) { low = mid + 1;
            } else if (comparison > 0) { high = mid - 1;
            } else { return mid; }
        }
        return low;
    }
}
