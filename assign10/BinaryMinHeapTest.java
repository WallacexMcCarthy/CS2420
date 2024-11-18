package assign10;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryMinHeapTest {

    @Test
    public void testAddAndPeek() {
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>();
        heap.add(5);
        heap.add(2);
        heap.add(8);

        assertEquals(2, heap.peek()); // Min element should be 2
    }

    @Test
    public void testExtract() {
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>();
        heap.add(10);
        heap.add(3);
        heap.add(5);

        assertEquals(3, heap.extract()); // Min element (3) removed
        assertEquals(5, heap.peek());    // New min is 5
        assertEquals(2, heap.size());   // Size decreases after extract
    }

    @Test
    public void testSizeAndIsEmpty() {
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>();
        assertTrue(heap.isEmpty()); // Initially empty

        heap.add(1);
        assertEquals(1, heap.size());
        assertFalse(heap.isEmpty());

        heap.add(2);
        assertEquals(2, heap.size());
    }

    @Test
    public void testClear() {
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>();
        heap.add(5);
        heap.add(2);

        heap.clear();
        assertTrue(heap.isEmpty()); // Heap should be empty after clearing
        assertThrows(NoSuchElementException.class, heap::peek);
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
    public void testToArray() {
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>();
        heap.add(4);
        heap.add(1);
        heap.add(3);
        heap.add(2);

        Object[] expected = {1, 2, 3, 4};
        assertArrayEquals(expected, heap.toArray());
    }

    @Test
    public void testPeekOnEmptyHeap() {
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>();
        assertThrows(NoSuchElementException.class, heap::peek);
    }

    @Test
    public void testExtractOnEmptyHeap() {
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>();
        assertThrows(NoSuchElementException.class, heap::extract);
    }

    @Test
    public void testResize() {
        BinaryMinHeap<Integer> heap = new BinaryMinHeap<>();

        for (int i = 1; i <= 15; i++) {
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
