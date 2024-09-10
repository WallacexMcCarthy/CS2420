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

    private SortedArrayList<LibraryBookGeneric<Integer>> bookSortedArrayList = new SortedArrayList<>(new AuthorOrdering<Integer>());

    private SortedArrayList<Integer> integerSortedArrayList = new SortedArrayList<>();
    private SortedArrayList<Integer> comparatorIntegerSortedArrayList = new SortedArrayList<>((x, y) -> (y - x));

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
    public void testInsertingDuplicatesToTop(){
        assertEquals(Arrays.toString(new int[]{0,1,2,3,4}), Arrays.toString(integerSortedArrayList.toArray()));
        for (int i = 0; i < 5; i++) {
            integerSortedArrayList.insert(5);
        }
        assertEquals(10, integerSortedArrayList.size());
        assertEquals(Arrays.toString(new int[]{0,1,2,3,4,5,5,5,5,5}), Arrays.toString(integerSortedArrayList.toArray()));
        assertEquals(5, integerSortedArrayList.median());
        assertEquals(5, integerSortedArrayList.max());
    }
    @Test
    public void testInsertingDuplicatesToBottom(){
        assertEquals(Arrays.toString(new int[]{0,1,2,3,4}), Arrays.toString(integerSortedArrayList.toArray()));
        for (int i = 0; i < 5; i++) {
            integerSortedArrayList.insert(-1);
        }
        assertEquals(10, integerSortedArrayList.size());
        assertEquals(Arrays.toString(new int[]{-1,-1,-1,-1,-1,0,1,2,3,4}), Arrays.toString(integerSortedArrayList.toArray()));
        assertEquals(0, integerSortedArrayList.median());
        assertEquals(4, integerSortedArrayList.max());
        assertEquals(-1, integerSortedArrayList.min());
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
        assertEquals(5, integerSortedArrayList.size());
        int size = integerSortedArrayList.size();
        // Backing array doubles, should not affect size
        integerSortedArrayList.insert(5);
        assertEquals(6, integerSortedArrayList.size());
    }

    @Test
    public void testStringInsertions(){
        assertEquals(0, stringSortedArrayList.countEntries("07"));
        stringSortedArrayList.insert("07");
        assertEquals(6, stringSortedArrayList.size());
        assertEquals(1, stringSortedArrayList.countEntries("07"));
    }

    @Test
    public void testSize(){
        assertEquals(5, integerSortedArrayList.size());
        integerSortedArrayList.insert(8);
        assertEquals(6, integerSortedArrayList.size());
        integerSortedArrayList.clear();
        assertEquals(0, integerSortedArrayList.size());

    }

    @Test
    public void testToArrayGoodArray(){
        assertEquals(Arrays.toString(new Integer[]{0,1,2,3,4}), Arrays.toString( integerSortedArrayList.toArray()));
    }

    @Test
    public void testToArrayEmptyArray(){
        integerSortedArrayList.clear();
        assertEquals(Arrays.toString(new Integer[]{}), Arrays.toString( integerSortedArrayList.toArray()));
    }

    @Test
    public void testComparatorToArrayGoodArray(){
        assertEquals(Arrays.toString(new Integer[]{4,3,2,1,0}), Arrays.toString( comparatorIntegerSortedArrayList.toArray()));
    }

    @Test
    public void testComparatorWithNonPrimitiveTypes(){
        assertTrue(bookSortedArrayList.isEmpty());
        bookSortedArrayList.insert(new LibraryBookGeneric<Integer>(9780374292799L, "Friedman", "Thomas L.", "The World is Flat"));
        bookSortedArrayList.insert(new LibraryBookGeneric<Integer>(9780330351690L, "Krakauer", "Jon", "Into the Wild"));
        bookSortedArrayList.insert(new LibraryBookGeneric<Integer>(9780446580342L, "Baldacci", "David", "Simple Genius"));
        bookSortedArrayList.insert(new LibraryBookGeneric<Integer>(9780446580342L, "Baldacci", "David", "Complex Genius"));
        bookSortedArrayList.insert(new LibraryBookGeneric<Integer>(9780446580342L, "Baldacci", "Dave", "Complex Genius"));
        assertFalse(bookSortedArrayList.isEmpty());
        assertTrue(bookSortedArrayList.contains(new LibraryBookGeneric<Integer>(9780446580342L, "Baldacci", "David", "Complex Genius")));
        assertEquals(1, bookSortedArrayList.countEntries(new LibraryBookGeneric<Integer>(9780446580342L, "Baldacci", "David", "Complex Genius")));
        assertEquals(5, bookSortedArrayList.size());
        assertTrue(new LibraryBookGeneric<Integer>(9780446580342L, "Baldacci", "Dave", "Complex Genius").equals(bookSortedArrayList.min()));
        assertTrue(new LibraryBookGeneric<Integer>(9780330351690L, "Krakauer", "Jon", "Into the Wild").equals(bookSortedArrayList.max()));
        bookSortedArrayList.insert(new LibraryBookGeneric<Integer>(9780446580342L, "Baldacci", "Dave", "Complex Genius"));

    }

}
