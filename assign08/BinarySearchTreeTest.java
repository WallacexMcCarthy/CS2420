package assign08;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Random;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> intBst;
    private BinarySearchTree<Integer> randomIntBst;

    private Random rng = new Random();
    private ArrayList<Integer> list;
    private ArrayList<Integer> randomList;

    @BeforeEach
    void setUp() {
        intBst = new BinarySearchTree<Integer>();
//        randomIntBst = new BinarySearchTree<Integer>();

//        randomList = new ArrayList<>();

//        for (int i = 1; i <= 100; i++) {
//            randomIntBst.add(i);
//            randomList.add(i);
//        }

        list = new ArrayList<>();
    }

    @Test
    void testAddEmptyBST() {
        assertTrue(intBst.add(5));
        assertEquals(1, intBst.size());
        assertTrue(intBst.contains(5));
    }

    @Test
    void testAddDuplicate() {
        assertTrue(intBst.add(5));
        assertFalse(intBst.add(5));
        assertEquals(1, intBst.size());
    }

    @Test
    void testAddThenRemoveThenAdd() {
        assertTrue(intBst.add(5));
        assertTrue(intBst.remove(5));

        assertEquals(0, intBst.size());
        assertFalse(intBst.contains(5));

        assertTrue(intBst.add(5));
        assertEquals(1, intBst.size());
        assertTrue(intBst.contains(5));
    }

    @Test
    void testAddRandom() {
        for (int i = 0; i < 100; i++) {
            int rngInt = rng.nextInt();
            if (intBst.add(rngInt))
                list.add(rngInt);
        }

        Collections.sort(list);
        assertEquals(list.size(), intBst.size());
        assertEquals(list.get(0), intBst.min());
        assertEquals(list.get(list.size() - 1), intBst.max());
        assertEquals(intBst.toArrayList(), list);
    }

    @Test
    void testContainsNonexistentElement() {
        assertTrue(intBst.add(5));
        assertFalse(intBst.contains(6));
    }

    @Test
    void testContainsEmptyBST() {
        assertFalse(intBst.contains(5));
    }

    @Test
    void testContainsRandom() {
        for (int i = 0; i < 100; i++) {
            int rngInt = rng.nextInt();
            if (intBst.add(rngInt))
                list.add(rngInt);
        }

        for (int j = 0; j < list.size(); j++)
            assertTrue(intBst.contains(list.get(j)));

        Collections.sort(list);
        assertEquals(intBst.toArrayList(), list);
    }

    @Test
    void testRemoveOnce() {
        assertTrue(intBst.add(5));
        System.out.println(intBst.toArrayList());
        System.out.println(intBst.size());
        assertTrue(intBst.remove(5));
        System.out.println(intBst.toArrayList());


        assertEquals(0, intBst.size());
        assertFalse(intBst.contains(5));
    }

    @Test
    void testRemoveNoChild() {
        assertTrue(intBst.add(5));
        assertTrue(intBst.add(3));

        assertTrue(intBst.remove(3));
        assertEquals(1, intBst.size());
        assertFalse(intBst.contains(3));
    }

    @Test
    void testRemoveOneChild() {
        assertTrue(intBst.add(5));
        assertTrue(intBst.add(3));

        assertTrue(intBst.remove(5));
        assertEquals(1, intBst.size());
        assertTrue(intBst.contains(3));
    }

    @Test
    void testRemoveRootWithLeftSubtree() {
        assertTrue(intBst.add(5));
        assertTrue(intBst.add(3));
        assertTrue(intBst.add(1));
        assertTrue(intBst.add(4));

        assertTrue(intBst.remove(5));
        // tree should be
        //   3
        //  1 4
        assertTrue(intBst.contains(3));
        assertTrue(intBst.contains(1));
        assertTrue(intBst.contains(4));

        assertEquals(3, intBst.size());
    }

    @Test
    void testRemoveRandom() {
        randomList = new ArrayList<>();
        randomIntBst = new BinarySearchTree<>();

        for (int i = 0; i < 10000; i++) {
            int rngInt = rng.nextInt();
            while (randomList.contains(rngInt))
                rngInt = rng.nextInt();
            randomList.add(rngInt);
            randomIntBst.add(rngInt);
        }

        System.out.println();

        while (!randomList.isEmpty()) {
            int rngIndex = rng.nextInt(randomList.size());
            Integer rngElement = randomList.get(rngIndex);
            assertTrue(randomList.remove(rngElement));
            assertTrue(randomIntBst.remove(rngElement));
            assertFalse(randomIntBst.contains(rngElement));
            assertEquals(randomList.size(), randomIntBst.size());
        }
    }

    @Test
    void testRemovePrint() {
        randomIntBst = new BinarySearchTree<>();

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

    @Test
    void testMinEmptyBST() {
        assertThrows(NoSuchElementException.class, () -> intBst.min());
    }

    @Test
    void testMinRightHeavyBST() {
        intBst.add(5);
        intBst.add(6);
        intBst.add(7);

        assertEquals(5, intBst.min());
    }

    @Test
    void testMaxEmptyBST() {
        assertThrows(NoSuchElementException.class, () -> intBst.max());
    }

    @Test
    void testMaxLeftHeavyBST() {
        intBst.add(5);
        intBst.add(4);
        intBst.add(3);

        assertEquals(5, intBst.max());
    }
}
