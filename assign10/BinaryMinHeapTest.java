package assign10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class contains tests for the BinaryMinHeap class
 * @version 11/18/2024
 * @author Isaac Buehner and Wallace McCarthy
 */
public class BinaryMinHeapTest {
    BinaryMinHeap<Integer> intHeap;
    BinaryMinHeap<Integer> emptyHeap;
    Random rng;


    @BeforeEach
    public void setUp() {
        intHeap = new BinaryMinHeap<>();
        emptyHeap = new BinaryMinHeap<>();
        rng = new Random();

        intHeap.add(3);
        intHeap.add(4);
        intHeap.add(5);
    }

    @Test
    public void sizeNormal() {
        assertEquals(3, intHeap.size());
    }

    @Test
    public void sizeEmpty() {
        assertEquals(0, emptyHeap.size());
    }

    @Test
    public void toArrayNormal() {
        Object[] expected = {3, 4, 5};
        assertArrayEquals(expected, intHeap.toArray());
    }

    @Test
    public void toArrayEmpty() {
        Object[] expected = {};
        assertArrayEquals(expected, emptyHeap.toArray());
    }

    @Test
    public void clearNormal() {
        intHeap.clear();
        assertEquals(0, intHeap.size());
        Object[] expected = {};
        assertArrayEquals(expected, intHeap.toArray());
    }

    @Test
    public void clearEmpty() {
        emptyHeap.clear();
        assertEquals(0, emptyHeap.size());
        Object[] expected = {};
        assertArrayEquals(expected, emptyHeap.toArray());
    }

    @Test
    public void isEmptyFalse() {
        assertFalse(intHeap.isEmpty());
    }

    @Test
    public void isEmptyTrue() {
        assertTrue(emptyHeap.isEmpty());
    }

    @Test
    public void isEmptyAfterClear() {
        intHeap.clear();
        assertTrue(intHeap.isEmpty());
    }

    @Test
    public void addNormal() {
        intHeap.add(6);
        intHeap.add(2);
        assertEquals(2, intHeap.peek()); // Min element should be 2
        assertEquals(5, intHeap.size());
    }

    @Test
    public void addEmpty() {
        emptyHeap.add(0);
        assertEquals(0, emptyHeap.peek());
    }

    @Test
    public void addRandom() {
        int smallest = rng.nextInt();
        emptyHeap.add(smallest);
        for (int i = 0; i < 100; i++) {
            int item = rng.nextInt();
            if (item < smallest) {
                smallest = item;
            }
            emptyHeap.add(item);
        }
        assertEquals(smallest, emptyHeap.peek());
    }

    @Test
    public void extractNormal() {
        assertEquals(3, intHeap.extract()); // Min element (3) removed
        assertEquals(4, intHeap.peek());    // New min is 4
        assertEquals(2, intHeap.size());   // Size decreases after extract
    }

    @Test
    public void extractOnEmptyHeap() {
        assertThrows(NoSuchElementException.class, emptyHeap::extract);
    }

    @Test
    public void extractAll() {
        for (int i = 0; i < 2; i++) {
            assertEquals(i + 3, intHeap.extract());
            assertEquals(2 - i, intHeap.size());
        }
    }

    @Test
    public void peekNormal() {
        assertEquals(3, intHeap.peek());
    }

    @Test
    public void peekOnEmptyHeap() {
        assertThrows(NoSuchElementException.class, emptyHeap::peek);
    }

    @Test
    public void peekAll() {
        for (int i = 0; i < 2; i++) {
            assertEquals(i + 3, intHeap.peek());
            assertEquals(i + 3, intHeap.extract());
        }
    }

    @Test
    public void testConstructorWithComparator() {
        Comparator<Integer> reverseComparator = (a, b) -> b - a;
        BinaryMinHeap<Integer> maxHeap = new BinaryMinHeap<>(reverseComparator);

        maxHeap.add(5);
        maxHeap.add(2);
        maxHeap.add(8);

        assertEquals(8, maxHeap.peek()); // Reverse comparator makes it a max-heap
    }

    @Test
    public void testConstructorWithList() {
        List<Integer> list = Arrays.asList(7, 3, 1, 4, 6);
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>(list);
        System.out.println(Arrays.toString(heap.toArray()));

        assertEquals(1, heap.peek()); // Min element from the list is the root
        assertEquals(5, heap.size());
    }

    @Test
    public void testConstructorWithListAndComparator() {
        Comparator<Integer> reverseComparator = (a, b) -> b - a;
        List<Integer> list = Arrays.asList(7, 3, 1, 4, 6);
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>(list, reverseComparator);

        assertEquals(7, heap.peek()); // Max element for reverse comparator
        assertEquals(5, heap.size());
    }

    @Test
    public void testResize() {
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>();

        for (int i = 1; i <= 15; i++) { // base size is 10, so adding 15 items will force a resize
            heap.add(i);
        }

        assertEquals(15, heap.size());
        assertEquals(1, heap.peek()); // Min element should still be correct
    }

    @Test
    public void testCustomComparator() {
        Comparator<String> stringLengthComparator = Comparator.comparingInt(String::length);
        BinaryMinHeap<String> heap = new BinaryMinHeap<>(stringLengthComparator);

        heap.add("apple");
        heap.add("kiwi");
        heap.add("banana");

        assertEquals("kiwi", heap.peek()); // Shortest string should be at the root
    }

    @Test
    public void testHeapifyMaintainsOrder() {
        List<Integer> list = Arrays.asList(9, 5, 6, 2, 3);
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>(list);

        System.out.println(Arrays.toString(heap.toArray()));
        assertEquals(2, heap.extract());
        assertEquals(3, heap.extract());
        assertEquals(5, heap.extract());
        assertEquals(6, heap.extract());
        assertEquals(9, heap.extract());
    }

    @Test
    public void testHeapifyWithEmptyList() {
        List<Integer> emptyList = new ArrayList<>();
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>(emptyList);

        assertTrue(heap.isEmpty());
    }
}
