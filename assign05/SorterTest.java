package assign05;

import assign04.IntegerStringUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class SorterTest {
    public MergeSorter<Integer> mergeSort = new MergeSorter<>(3);
    public ArrayList<Integer> smallIntArray;
    public ArrayList<Integer> smallSortedIntArray;

    public ArrayList<String> smallStringArray;
    public ArrayList<Integer> largeIntArray;
    public ArrayList<String> largeStringArray;

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
    public void testMergeSort() {
        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(8, 6, 1, 0, 4));

        mergeSort.sort(integerArrayList);
        assertEquals(Arrays.asList(0, 1, 4, 6, 8), integerArrayList);
    }

}
