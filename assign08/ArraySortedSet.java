package assign08;

import java.util.*;

/**
 * this class is a sorted set of values
 * every value in the ArraySortedSet is unique
 * @param <Type> the generic type
 * @version 10/28/2024
 * @author Isaac Buehner and Wallace McCarthy
 */
public class ArraySortedSet<Type extends Comparable<? super Type>> implements SortedSet<Type>{
    private Object[] backingArray = new Object[5];
    private int size = 0;

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(Type item) {
        if(isEmpty()){
            backingArray[0] = item;
            size++;
            return true;
        }
        int index = binarySearch(item);
        // if the array is full we want to expand, otherwise we check for a duplicate and add accordingly
        if (size() < backingArray.length && index >= size()) {
            backingArray[index] = item;
            size++;
            return true;
        }else if(size() >= backingArray.length && index >= size()){
            resize(item, index);
            size++;
            return true;
        }
        if(getAtIndex(index).compareTo(item) == 0) {
            return false;
        }
        if(size() >= backingArray.length){
            resize(item, index);
        }
        else{
            shiftElements(item, index);
        }
        size++;
        return true;
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise,
     * returns false
     */
    @Override
    public boolean addAll(Collection<? extends Type> items) {
        boolean flag = false;
        for(Type item : items){
            if(add(item)){
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        backingArray = new Object[20];
        size = 0;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     * otherwise, returns false
     */
    @Override
    public boolean contains(Type item) {
        return getAtIndex(binarySearch(item)).compareTo(item) == 0;
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     * in this set that is equal to it; otherwise, returns false
     */
    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        for(Type item : items){
            if(!contains(item)){
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Returns the smallest item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type min() throws NoSuchElementException {
        if(this.size() == 0){
            throw new NoSuchElementException();
        }else{
            return getAtIndex(0);
        }
    }

    /**
     * Returns the largest item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type max() throws NoSuchElementException {
        if(this.size() == 0){
            throw new NoSuchElementException();
        }else{
            return getAtIndex(this.size() - 1);
        }
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an ArrayList containing all the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList<Type> toArrayList() {
        ArrayList<Type> arr = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            arr.add(getAtIndex(i));
        }
        return arr;
    }

    /**
     * this method gets an item from the backing array at a specified index
     * this is used instead of a normal get method to avoid suppressing additional warnings
     * @throws IllegalArgumentException if the provided index is outside the arrays range
     * @param index the index of the desired element
     * @return the element at the index
     */
    @SuppressWarnings("unchecked")
    private Type getAtIndex(int index){
        if(index >= size() || index < 0) {
            throw new IllegalArgumentException();
        }
        return (Type) backingArray[index];
    }

    /**
     * this method shifts all the elements after a specified index up one index and inserts an element in the gap
     * @throws IllegalArgumentException if the provided index is outside the arrays range
     * @param item the element to be inserted
     * @param index the index to shift and insert at
     */
    private void shiftElements(Type item, int index){
        if(index >= size() || index < 0) {
            throw new IllegalArgumentException();
        }
        for (int i = size(); i > index ; i--) {
            backingArray[i] = backingArray[i - 1];
        }
        backingArray[index] = item;
    }

    /**
     * this method resizes the array to provide more space for elements
     * it also inserts an element at the specified index during the expansion
     * @throws IllegalArgumentException if the provided index is outside the arrays range
     * @param item the element to be inserted
     * @param index the index to insert the element at
     */
    private void resize(Type item, int index){
        if(index < 0) {
            throw new IllegalArgumentException();
        }
        Object[] arr = Arrays.copyOf(backingArray, backingArray.length);
        backingArray = new Object[backingArray.length * 2];
        int j = 0;
        for (int i = 0; i <= arr.length; i++) {
            if (i == index) {
                backingArray[i] = item;
                i++;
            } else {
                backingArray[i] = arr[j];
                j++;
            }
        }
    }

    /**
     * uses binary to return the index of an element if it is in the array.
     * if the element is not in the list, returns the closest index to the element.
     *
     * @param target element to be searched for
     * @return an index for the array
     */
    private int binarySearch(Type target) {
        int l = 0;
        int r = this.size() - 1;

        while(l <= r) {
            int mid = (l + r) / 2;
            if (getAtIndex(mid).equals(target)) {
                return mid;
            }
            if (getAtIndex(mid).compareTo(target) > 0) {
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return l;
    }
}
