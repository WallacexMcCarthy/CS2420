package assign03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SortedArrayListTest {
    private SortedArrayList<Integer> arrayList = new SortedArrayList<>();


    @BeforeEach
    public void setUp(){
        for (int i = 0; i < 6; i++) {
            arrayList.insert(i);
        }
    }

    @Test
    public void testing(){

        System.out.println(Arrays.toString(arrayList.toArray()));
        System.out.println(arrayList.max());
        System.out.println(arrayList.contains(4));
        System.out.println(arrayList.countEntries(3));
        System.out.println(arrayList.countEntries(6));
    }

}
