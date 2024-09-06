package assign03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SortedArrayListTest {
    private SortedArrayList<Integer> integerSortedArrayList = new SortedArrayList<>();
    private SortedArrayList<String> stringSortedArrayList = new SortedArrayList<>();



    @BeforeEach
    public void setUp(){
        for (int i = 0; i < 6; i++) {
            integerSortedArrayList.insert(i);
        }
    }

    @Test
    public void testing(){

        System.out.println(Arrays.toString(integerSortedArrayList.toArray()));
        System.out.println(integerSortedArrayList.max());
        System.out.println(integerSortedArrayList.contains(4));
        System.out.println(integerSortedArrayList.countEntries(3));
        System.out.println(integerSortedArrayList.countEntries(6));
    }

}
