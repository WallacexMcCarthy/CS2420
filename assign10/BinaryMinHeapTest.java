package assign10;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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
    public void listConstructor() {

    }

    @Test
    public void comparatorConstructor() {

    }

    @Test
    public void listAndComparatorConstructor() {

    }

    @Test
    public void toArrayNormal() {

    }

    @Test
    public void toArrayEmpty() {

    }
}
