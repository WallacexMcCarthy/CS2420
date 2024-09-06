package assign03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SortedArrayListTest {
    private SortedArrayList<Integer> integerSortedArrayList = new SortedArrayList<>();
    private SortedArrayList<String> stringSortedArrayList = new SortedArrayList<>();



    @BeforeEach
    public void setUp(){
        for (int i = 0; i < 5; i++) {
            integerSortedArrayList.insert(i);
        }
    }

    // comparator constructors
    // clear
    // contains for correct and incorrect elements
    // count entries for small, big, not in array
    // insert for good size and resize and duplicates at bottom at top of array
    // isEmpty
    // max
    // median for odd and even array sizes
    // min
    // size and after resizing
    // toString


}
