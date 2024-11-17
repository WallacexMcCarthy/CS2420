package assign10;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryMinHeapTest {
    BinaryMinHeap<Integer> intHeap;
    BinaryMinHeap<String> emptyHeap;

    @BeforeEach
    public void setUp() {
        intHeap = new BinaryMinHeap<>();
        emptyHeap = new BinaryMinHeap<>();
    }

    @Test
    public void testListConstructor() {

    }

    @Test
    public void testComparatorConstructor() {

    }

    @Test
    public void testListAndComparatorConstructor() {

    }
}
