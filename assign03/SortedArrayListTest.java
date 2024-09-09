package assign03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * this class contains test cases for the SortedArrayList class
 *
 * @version 9/7/2024
 * @author Wallace McCarthy and Isaac Buehner
 */
public class SortedArrayListTest {
    Comparator<Integer> intCMP = new Comparator<Integer>() {
        @Override
        public int compare(Integer integer1, Integer integer2) {
            return integer2.compareTo(integer1);
        }
    };

    private SortedArrayList<Integer> integerSortedArrayList = new SortedArrayList<>();
    private SortedArrayList<Integer> comparatorIntegerSortedArrayList = new SortedArrayList<>(intCMP);

    private SortedArrayList<String> stringSortedArrayList = new SortedArrayList<>();
    private SortedArrayList<Double> emptySortedArrayList = new SortedArrayList<>();

    @BeforeEach
    public void setUp(){
        for (int i = 0; i < 5; i++) {
            integerSortedArrayList.insert(i);
            comparatorIntegerSortedArrayList.insert(i);
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

    @Test
    public void insertIntegerToEndTest(){
        integerSortedArrayList.insert(5);
        assertEquals(Arrays.toString(new int[]{0,1,2,3,4,5}), Arrays.toString(integerSortedArrayList.toArray()));
    }
    @Test
    public void insertIntegerToBeginningTest(){
        integerSortedArrayList.insert(-1);
        assertEquals(Arrays.toString(new int[]{-1,0,1,2,3,4}), Arrays.toString(integerSortedArrayList.toArray()));
    }
    @Test
    public void insertIntegerToMiddleTest(){
        integerSortedArrayList.insert(2);
        assertEquals(Arrays.toString(new int[]{0,1,2,2,3,4}), Arrays.toString(integerSortedArrayList.toArray()));
    }
    @Test
    public void insertMultipleIntegerToMiddleTest(){
        integerSortedArrayList.insert(2);
        integerSortedArrayList.insert(2);
        assertEquals(Arrays.toString(new int[]{0,1,2,2,2,3,4}), Arrays.toString(integerSortedArrayList.toArray()));
    }
    @Test
    public void insertIntegerInReverseOrderTest(){
        integerSortedArrayList.clear();
        assertTrue(integerSortedArrayList.isEmpty());
        for (int i = 5; i > 0; i--) {
            integerSortedArrayList.insert(i);
        }
        assertEquals(Arrays.toString(new int[]{1,2,3,4,5}), Arrays.toString(integerSortedArrayList.toArray()));
    }

    @Test
    public void comparatorInsertIntegerToEndTest(){
        comparatorIntegerSortedArrayList.insert(5);
        assertEquals(Arrays.toString(new int[]{5,4,3,2,1,0}), Arrays.toString(comparatorIntegerSortedArrayList.toArray()));
    }
    @Test
    public void comparatorInsertIntegerToBeginningTest(){
        comparatorIntegerSortedArrayList.insert(-1);
        assertEquals(Arrays.toString(new int[]{4,3,2,1,0,-1}), Arrays.toString(comparatorIntegerSortedArrayList.toArray()));
    }
    @Test
    public void comparatorInsertIntegerToMiddleTest(){
        comparatorIntegerSortedArrayList.insert(2);
        assertEquals(Arrays.toString(new int[]{4,3,2,2,1,0}), Arrays.toString(comparatorIntegerSortedArrayList.toArray()));
    }
    @Test
    public void comparatorInsertMultipleIntegerToMiddleTest(){
        comparatorIntegerSortedArrayList.insert(2);
        comparatorIntegerSortedArrayList.insert(2);
        assertEquals(Arrays.toString(new int[]{4,3,2,2,2,1,0}), Arrays.toString(comparatorIntegerSortedArrayList.toArray()));
    }
    @Test
    public void comparatorInsertIntegerInReverseOrderTest(){
        comparatorIntegerSortedArrayList.clear();
        assertTrue(comparatorIntegerSortedArrayList.isEmpty());
        for (int i = 0; i < 5; i++) {
            comparatorIntegerSortedArrayList.insert(i);
        }
        assertEquals(Arrays.toString(new int[]{4,3,2,1,0}), Arrays.toString(comparatorIntegerSortedArrayList.toArray()));
    }

    @Test
    public void testSizeWhenInserting(){
        assertTrue(integerSortedArrayList.size() == 5);
        int size = integerSortedArrayList.size();
        // Backing array doubles, should not affect size
        integerSortedArrayList.insert(5);
        assertTrue(integerSortedArrayList.size() == 6);
    }

    @Test
    public void testStringInsertions(){
        assertEquals(0, stringSortedArrayList.countEntries("07"));
        stringSortedArrayList.insert("07");
        assertTrue(stringSortedArrayList.size() == 6);
        assertEquals(1, stringSortedArrayList.countEntries("07"));
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
