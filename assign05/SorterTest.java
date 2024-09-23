package assign05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class contains tests for the MergeSorter and QuickSorter classes
 */
public class SorterTest {
    public MergeSorter<Integer> mergeSort = new MergeSorter<>(3);
    public FirstPivotChooser<Integer> firstPivotIntegerChooser = new FirstPivotChooser<>();
    public QuickSorter<Integer> quickSort = new QuickSorter<>(firstPivotIntegerChooser);
    public ArrayList<Integer> smallIntArray;
    public ArrayList<Integer> smallSortedIntArray;

    @BeforeEach
    void setup(){
        smallIntArray = new ArrayList<>();
        smallSortedIntArray = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            smallIntArray.add(i);
            smallSortedIntArray.add(i);
        }
        Collections.shuffle(smallIntArray);
    }

    @Test
    public void test3ElementMergeSortForward() {
        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(1, 2, 3));

        mergeSort.sort(integerArrayList);
        assertEquals(Arrays.asList(1, 2, 3), integerArrayList);
    }

    @Test
    public void test3ElementMergeSortBackward() {
        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(3, 2, 1));

        mergeSort.sort(integerArrayList);
        assertEquals(Arrays.asList(1, 2, 3), integerArrayList);
    }

    @Test
    public void test3ElementMergeSortSmallBigMiddle() {
        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(1, 3, 2));

        mergeSort.sort(integerArrayList);
        assertEquals(Arrays.asList(1, 2, 3), integerArrayList);
    }

    @Test
    public void test3ElementMergeSortMiddleBigSmall() {
        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(2, 3, 1));

        mergeSort.sort(integerArrayList);
        assertEquals(Arrays.asList(1, 2, 3), integerArrayList);
    }

    @Test
    public void test3ElementMergeSortBigSmallMiddle() {
        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(3, 1, 2));

        mergeSort.sort(integerArrayList);
        assertEquals(Arrays.asList(1, 2, 3), integerArrayList);
    }

    @Test
    public void test3ElementMergeSortMiddleSmallBig() {
        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(2, 1, 3));

        mergeSort.sort(integerArrayList);
        assertEquals(Arrays.asList(1, 2, 3), integerArrayList);
    }

    @Test
    public void testMergeSortNotConsecutive() {
        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(8, 6, 1, 0, 4));

        mergeSort.sort(integerArrayList);
        assertEquals(Arrays.asList(0, 1, 4, 6, 8), integerArrayList);
    }

    @Test
    public void testMergeSortVeryLargeBackwardsSorted() {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        ArrayList<Integer> integerArrayListSorted = new ArrayList<>();
        for (int i = 999; i >= 0; i--) {
            integerArrayList.add(i);
            integerArrayListSorted.add(999 - i);
        }

        mergeSort.sort(integerArrayList);
        assertEquals(integerArrayListSorted, integerArrayList);
    }

    @Test
    public void testMergeSortVeryLargeRandom() {
        ArrayList<Integer> integerArrayList = randomIntegerArrayList(1000);
        ArrayList<Integer> integerArrayListSorted = sorrtedIntegerArrayList(1000);

        mergeSort.sort(integerArrayList);
        assertEquals(integerArrayListSorted, integerArrayList);
    }

    @Test
    public void test3ElementQuickSortForward() {
        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(1, 2, 3));

        quickSort.sort(integerArrayList);
        assertEquals(Arrays.asList(1, 2, 3), integerArrayList);
    }

    @Test
    public void test3ElementQuickSortBackward() {
        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(3, 2, 1));

        quickSort.sort(integerArrayList);
        assertEquals(Arrays.asList(1, 2, 3), integerArrayList);
    }

    @Test
    public void test3ElementQuickSortSmallBigMiddle() {
        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(1, 3, 2));

        quickSort.sort(integerArrayList);
        assertEquals(Arrays.asList(1, 2, 3), integerArrayList);
    }

    @Test
    public void test3ElementQuickSortMiddleBigSmall() {
        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(2, 3, 1));

        quickSort.sort(integerArrayList);
        assertEquals(Arrays.asList(1, 2, 3), integerArrayList);
    }

    @Test
    public void test3ElementQuickSortBigSmallMiddle() {
        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(3, 1, 2));

        quickSort.sort(integerArrayList);
        assertEquals(Arrays.asList(1, 2, 3), integerArrayList);
    }

    @Test
    public void test3ElementQuickSortMiddleSmallBig() {
        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(2, 1, 3));

        quickSort.sort(integerArrayList);
        assertEquals(Arrays.asList(1, 2, 3), integerArrayList);
    }

    @Test
    public void testQuickSortNotConsecutive() {
        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(8, 6, 1, 0, 4));

        quickSort.sort(integerArrayList);
        assertEquals(Arrays.asList(0, 1, 4, 6, 8), integerArrayList);
    }

    @Test
    public void testQuickSortVeryLargeBackwardsSorted() {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        ArrayList<Integer> integerArrayListSorted = new ArrayList<>();
        for (int i = 999; i >= 0; i--) {
            integerArrayList.add(i);
            integerArrayListSorted.add(999 - i);
        }

        quickSort.sort(integerArrayList);
        assertEquals(integerArrayListSorted, integerArrayList);
    }

    @Test
    public void testQuickSortVeryLargeRandom() {
        ArrayList<Integer> integerArrayList = randomIntegerArrayList(1000);
        ArrayList<Integer> integerArrayListSorted = sorrtedIntegerArrayList(1000);

        quickSort.sort(integerArrayList);
        assertEquals(integerArrayListSorted, integerArrayList);
    }

    public ArrayList<Integer> randomIntegerArrayList(int size){
        ArrayList<Integer> arraylist = sorrtedIntegerArrayList(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int index1 = random.nextInt(0, 999);
            int index2 = random.nextInt(0, 999);
            int temp = arraylist.get(index1);
            arraylist.set(index1, arraylist.get(index2));
            arraylist.set(index2, temp);
        }
        return arraylist;
    }

    public ArrayList<Integer> sorrtedIntegerArrayList(int size){
        ArrayList<Integer> arraylist = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            arraylist.add(i);
        }
        return arraylist;
    }

}
