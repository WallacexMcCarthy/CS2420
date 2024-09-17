package assign04;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class contains tests for the IntegerStringUtility class
 */
public class IntegerStringUtilityTester {
    Comparator<String> stringNumericalCmp = new IntegerStringUtility.StringNumericalValueComparator();
    Comparator<String> stringSimilarityCmp = new IntegerStringUtility.StringSimilarityComparator();
    Comparator<String[]> stringSimilarityGroupCmp = new IntegerStringUtility.StringSimilarityGroupComparator();
    private int[] intArray = new int[]{123, 456, 789, 321, 654, 987, 00011, 0011, 1100, 0110, 1010, 213, 123, 321, 312};

    public void setup() {

    }

    @Test
    public void test() {
        String[] arr = IntegerStringUtility.findMaximumSimilarityGroup(new int[]{001, 100, 007, 700});

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testIntArray() {
        String[] array = IntegerStringUtility.findMaximumSimilarityGroup(intArray);
    }

    @Test
    public void testStringNumericalComparator(){
        assertTrue(stringNumericalCmp.compare("1234", "1233") > 0);
        assertTrue(stringNumericalCmp.compare("13", "012") > 0);
        assertTrue(stringNumericalCmp.compare("987", "1231") < 0);
        assertTrue(stringNumericalCmp.compare("9223372036854775808", "9223372036854775809") < 0);
    }
    @Test
    public void testStringSimilarityComparator(){
        assertTrue(stringSimilarityCmp.compare("1235", "3421") > 0);
        assertTrue(stringSimilarityCmp.compare("1234", "3421") == 0);
        assertTrue(stringSimilarityCmp.compare("3421", "11111") < 0);
    }

    @Test
    public void testStringSimilarityGroupComparator(){
        assertTrue(stringSimilarityGroupCmp.compare(new String[]{"3421", "1234", "4321"}, new String[]{"987", "789"}) > 0);
        assertTrue(stringSimilarityGroupCmp.compare(new String[]{"3421", "1234", "4321"}, new String[]{"1235", "5321", "2153"}) < 0);
    }

    @Test
    public void testInsertionSort(){
        Character[] digits = {'8', '6', '1', '0', '4'};
        IntegerStringUtility.insertionSort(digits, (char1, char2) -> char1.compareTo(char2));
        assertEquals(Arrays.toString(new Character[]{'0', '1', '4', '6', '8'}), Arrays.toString(digits));
    }

    @Test
    public void testFindMax(){
        assertEquals("2431", IntegerStringUtility.findMax(new String[]{"2341", "2134", "2431", "2143"}, (x1, x2) -> x1.compareTo(x2)));
    }
}