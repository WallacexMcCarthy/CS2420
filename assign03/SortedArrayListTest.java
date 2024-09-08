package assign03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * this class contains test cases for the SortedArrayList class
 *
 * @version 9/7/2024
 * @author Wallace McCarthy and Isaac Buehner
 */
public class SortedArrayListTest {

    private SortedArrayList<Integer> integerSortedArrayList = new SortedArrayList<>();
    private SortedArrayList<String> stringSortedArrayList = new SortedArrayList<>();
    private SortedArrayList<Double> emptySortedArrayList = new SortedArrayList<>();

    @BeforeEach
    public void setUp(){
        for (int i = 0; i < 5; i++) {
            integerSortedArrayList.insert(i);
            stringSortedArrayList.insert("0" + i);
        }

    }

    @Test
    public void isEmptyTrue(){
        assertTrue(emptySortedArrayList.isEmpty());
    }

    @Test
    public void isEmptyFalse(){
        assertFalse(integerSortedArrayList.isEmpty());
    }

    @Test
    public void clearForFullList(){
        integerSortedArrayList.clear();
        assertTrue(integerSortedArrayList.isEmpty());
    }

    @Test
    public void clearForEmptyList(){
        emptySortedArrayList.clear();
        assertTrue(emptySortedArrayList.isEmpty());
    }

    @Test
    public void clearForExpandedList(){
        integerSortedArrayList.insert(6);
        integerSortedArrayList.clear();
        assertTrue(integerSortedArrayList.isEmpty());
    }

    @Test
    public void trueContains() {
        assertTrue(integerSortedArrayList.contains(3));
    }

    @Test
    public void falseContains() {
        assertFalse(integerSortedArrayList.contains(5));
    }

    @Test
    public void containsEmpty() {
        assertFalse(emptySortedArrayList.contains(3.0));
    }

    @Test
    public void countEntriesLow() {
        assertEquals(1, stringSortedArrayList.countEntries("01"));
    }

    @Test
    public void countEntriesHigh() {
        stringSortedArrayList.insert("01");
        stringSortedArrayList.insert("01");
        stringSortedArrayList.insert("01");
        stringSortedArrayList.insert("01");
        stringSortedArrayList.insert("01");
        assertEquals(6, stringSortedArrayList.countEntries("01"));
    }

    @Test
    public void countEntriesNone() {
        assertEquals(0, stringSortedArrayList.countEntries("06"));
    }

    @Test
    public void maxInSmallList() {
        assertEquals(4, integerSortedArrayList.max());
    }

    @Test
    public void minInSmallList() {
        assertEquals(0, integerSortedArrayList.min());
    }

    @Test
    public void medianInSmallOddList() {
        assertEquals(2, integerSortedArrayList.median());
    }

    @Test
    public void medianInSmallEvenList() {
        integerSortedArrayList.insert(5);
        assertEquals(3, integerSortedArrayList.median());
    }

    @Test
    public void maxInEmptyList() {
        assertThrows(NoSuchElementException.class, () -> emptySortedArrayList.max());
    }

    @Test
    public void minInEmptyList() {
        assertThrows(NoSuchElementException.class, () -> emptySortedArrayList.min());
    }

    @Test
    public void medianInEmptyList() {
        assertThrows(NoSuchElementException.class, () -> emptySortedArrayList.median());
    }

    @Test
    public void maxInListOfSameValues() {
        integerSortedArrayList.clear();
        integerSortedArrayList.insert(0);
        integerSortedArrayList.insert(0);
        integerSortedArrayList.insert(0);
        integerSortedArrayList.insert(0);
        integerSortedArrayList.insert(0);

        assertEquals(0, integerSortedArrayList.max());
    }

    @Test
    public void minInListOfSameValues() {
        integerSortedArrayList.clear();
        integerSortedArrayList.insert(0);
        integerSortedArrayList.insert(0);
        integerSortedArrayList.insert(0);
        integerSortedArrayList.insert(0);
        integerSortedArrayList.insert(0);

        assertEquals(0, integerSortedArrayList.min());
    }

    @Test
    public void medianInListOfSameValues() {
        integerSortedArrayList.clear();
        integerSortedArrayList.insert(0);
        integerSortedArrayList.insert(0);
        integerSortedArrayList.insert(0);
        integerSortedArrayList.insert(0);
        integerSortedArrayList.insert(0);

        assertEquals(0, integerSortedArrayList.median());
    }


    // clear | 3 methods done
    // contains for correct and incorrect elements | 3 methods done
    // count entries for small, big, not in array | 3 methods done
    // isEmpty | 2 methods done
    // max | 3 methods done
    // median for odd and even array sizes | 4 methods done
    // min | 3 methods done

    // comparator constructors
    // i bet we can use library books for the comparator list cause the test cases are gonna
    // be looked over by a person and not the auto grader

    // size and after resizing
    // toArray
    // insert for good size and resize and duplicates at bottom at top of array


}
