package assign03;

import java.util.Comparator;
import java.util.NoSuchElementException;


public class SortedArrayList <T> implements SortedList<T> {
    public SortedArrayList(Comparator<? super T> cmp){

    }
    @Override
    public void clear() {

    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public int countEntries(T target) {
        return 0;
    }

    @Override
    public void insert(T element) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public T max() throws NoSuchElementException {
        return null;
    }

    @Override
    public T median() throws NoSuchElementException {
        return null;
    }

    @Override
    public T min() throws NoSuchElementException {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }
}
