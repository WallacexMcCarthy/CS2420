package assign04;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class IntegerStringUtilityTester {

    public void setup(){

    }

    @Test
    public void test(){
        String[] arr =IntegerStringUtility.findMaximumSimilarityGroup(new int[]{123,789,321,987});
        System.out.println(Arrays.toString(arr));
    }
}
