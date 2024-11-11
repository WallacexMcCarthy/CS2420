package assign03.assign08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ArraySortedSetTest {
    private ArraySortedSet<Integer> set;
    private ArraySortedSet<Integer> randomIntBst;

    @BeforeEach
    void setUp() {
        set = new ArraySortedSet<>();
        randomIntBst = new ArraySortedSet<>();
    }

    // Test adding a single item to an empty set
    @Test
    void testAddSingleItem() {
        assertTrue(set.add(5));
        assertEquals(1, set.size());
        assertTrue(set.contains(5));
    }

    // Test adding duplicate items
    @Test
    void testAddDuplicateItems() {
        assertTrue(set.add(5));
        assertFalse(set.add(5)); // Duplicate should return false
        assertEquals(1, set.size());
    }

    // Test adding multiple items in sorted order
    @Test
    void testAddMultipleItemsSortedOrder() {
        assertTrue(set.add(1));
        assertTrue(set.add(3));
        assertTrue(set.add(2)); // Adding out of order to test sorting
        assertEquals(Arrays.asList(1, 2, 3), set.toArrayList());
    }

    // Test adding beyond initial array capacity to trigger resizing
    @Test
    void testAddTriggerResize() {
        for (int i = 0; i < 25; i++) {
            assertTrue(set.add(i));
        }
        assertEquals(25, set.size());
        assertEquals(0, set.min());
        assertEquals(24, set.max());
    }

    // Test adding a collection of items
    @Test
    void testAddAll() {
        Collection<Integer> items = Arrays.asList(1, 2, 3, 4, 5);
        assertTrue(set.addAll(items));
        assertEquals(5, set.size());
        assertTrue(set.containsAll(items));
    }

    // Test clear method
    @Test
    void testClear() {
        set.add(10);
        set.add(20);
        set.clear();
        assertEquals(0, set.size());
        assertTrue(set.isEmpty());
    }

    // Test contains method for existing and non-existing elements
    @Test
    void testContains() {
        set.add(10);
        set.add(20);
        assertTrue(set.contains(10));
        assertFalse(set.contains(5)); // Non-existing element
    }

    // Test containsAll for a subset
    @Test
    void testContainsAll() {
        set.add(1);
        set.add(2);
        set.add(3);
        Collection<Integer> subset = Arrays.asList(1, 2);
        assertTrue(set.containsAll(subset));
    }

    // Test min method for empty and non-empty sets
    @Test
    void testMin() {
        set.add(10);
        set.add(20);
        set.add(5);
        assertEquals(5, set.min());

        set.clear();
        assertThrows(NoSuchElementException.class, () -> set.min());
    }

    // Test max method for empty and non-empty sets
    @Test
    void testMax() {
        set.add(10);
        set.add(20);
        set.add(5);
        assertEquals(20, set.max());

        set.clear();
        assertThrows(NoSuchElementException.class, () -> set.max());
    }

    // Test toArrayList returns elements in sorted order
    @Test
    void testToArrayList() {
        System.out.println(set.toArrayList());
        set.add(5);
        System.out.println(set.toArrayList());
        set.add(1);
        System.out.println(set.toArrayList());
        set.add(3);
        System.out.println(set.toArrayList());
        ArrayList<Integer> sortedList = new ArrayList<>(Arrays.asList(1, 3, 5));
        assertEquals(sortedList, set.toArrayList());
    }


    // Test resize method with more than initial capacity
    @Test
    void testResize() {
        for (int i = 0; i < 21; i++) {
            set.add(i);
        }
        assertEquals(21, set.size());
        assertEquals(0, set.min());
        assertEquals(20, set.max());
    }
    @Test
    void testRemovePrint() {
        randomIntBst = new ArraySortedSet<>();

        randomIntBst.add(7);
        randomIntBst.add(3);
        randomIntBst.add(1);
        randomIntBst.add(4);
        randomIntBst.add(6);
        randomIntBst.add(2);
        randomIntBst.add(0);
        randomIntBst.add(8);
        randomIntBst.add(9);
        randomIntBst.add(5);
        System.out.println(randomIntBst.size());
        System.out.println(randomIntBst.toArrayList());

        assertTrue(randomIntBst.remove(3));
        System.out.println(randomIntBst.toArrayList());
        System.out.println(randomIntBst.size());
        assertTrue(randomIntBst.remove(9));
        System.out.println(randomIntBst.toArrayList());
        System.out.println(randomIntBst.size());
        assertTrue(randomIntBst.remove(2));
        System.out.println(randomIntBst.toArrayList());
        System.out.println(randomIntBst.size());
        assertTrue(randomIntBst.remove(6));
        System.out.println(randomIntBst.toArrayList());
        System.out.println(randomIntBst.size());
        assertFalse(randomIntBst.contains(6));

        assertEquals(6, randomIntBst.size());
    }
}