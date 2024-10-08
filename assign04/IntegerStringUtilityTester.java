package assign04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains comprehensive tests for the IntegerStringUtility class
 * @author Isaac Buehner and Wallace McCarthy
 * @version 09/19/24
 */
public class IntegerStringUtilityTester {
    Comparator<String> stringNumericalCmp;
    Comparator<String> stringSimilarityCmp;
    Comparator<String[]> stringSimilarityGroupCmp;
    int[] array;


    @BeforeEach
    void setUp() {
        stringNumericalCmp = new IntegerStringUtility.StringNumericalValueComparator();
        stringSimilarityCmp = new IntegerStringUtility.StringSimilarityComparator();
        stringSimilarityGroupCmp = new IntegerStringUtility.StringSimilarityGroupComparator();
        array = new int[]{123, 321, 666, 456, 989, 213};
    }
    @Test
    public void testStringNumericalComparator(){
        assertTrue(stringNumericalCmp.compare("1234", "1233") > 0);
        assertTrue(stringNumericalCmp.compare("13", "012") > 0);
        assertTrue(stringNumericalCmp.compare("987", "1231") < 0);
        assertTrue(stringNumericalCmp.compare("11", "11") == 0);
        assertTrue(stringNumericalCmp.compare("", "11") < 0);
        assertTrue(stringNumericalCmp.compare("000000", "11") < 0);
        assertTrue(stringNumericalCmp.compare("000000", "0000") == 0);
        assertTrue(stringNumericalCmp.compare("9223372036854775808", "9223372036854775809") < 0);
    }
    @Test
    public void testStringSimilarityComparator(){
        assertTrue(stringSimilarityCmp.compare("1235", "3421") > 0);
        assertTrue(stringSimilarityCmp.compare("1234", "3421") == 0);
        assertTrue(stringSimilarityCmp.compare("3421", "11111") < 0);
        assertTrue(stringSimilarityCmp.compare("", "11111") < 0);
        assertTrue(stringSimilarityCmp.compare("3421", "") > 0);
        assertTrue(stringSimilarityCmp.compare("", "") == 0);
    }

    @Test
    public void testStringSimilarityGroupComparator(){
        assertTrue(stringSimilarityGroupCmp.compare(new String[]{"3421", "1234", "4321"}, new String[]{"987", "789"}) > 0);
        assertTrue(stringSimilarityGroupCmp.compare(new String[]{"3421", "1234", "4321"}, new String[]{"1235", "5321", "2153"}) < 0);
        assertTrue(stringSimilarityGroupCmp.compare(new String[]{"3421", "1234", "4321"}, new String[]{"3421", "1234", "4321"}) == 0);
    }

    @Test
    public void testInsertionSort(){
        String[] stringArray = new String[]{"123", "321", "223", "987", "998", "333"};
        Integer[] arr = new Integer[]{8, 6, 1, 0, 4};
        Character[] digits = {'8', '6', '1', '0', '4'};
        IntegerStringUtility.insertionSort(digits, Comparator.naturalOrder());
        assertEquals(Arrays.toString(new Character[]{'0', '1', '4', '6', '8'}), Arrays.toString(digits));

        IntegerStringUtility.insertionSort(digits,(x, y) -> y.compareTo(x));
        assertEquals(Arrays.toString(new Character[]{'8', '6', '4', '1', '0'}), Arrays.toString(digits));

        IntegerStringUtility.insertionSort(arr, Comparator.naturalOrder());
        assertEquals(Arrays.toString(new int[]{0, 1, 4, 6, 8}), Arrays.toString(arr));

        IntegerStringUtility.insertionSort(stringArray, Comparator.naturalOrder());
        assertEquals(Arrays.toString(new String[]{"123", "223", "321", "333", "987", "998"}), Arrays.toString(stringArray));

        stringArray = new String[]{"123", "321", "223", "987", "978", "333"};
        IntegerStringUtility.insertionSort(stringArray, new IntegerStringUtility.StringSimilarityComparator());
        assertEquals(Arrays.toString(new String[]{"123", "321", "223", "333", "987", "978"}), Arrays.toString(stringArray));
    }

    @Test
    public void testFindMax(){
        assertEquals("2431", IntegerStringUtility.findMax(new String[]{"2341", "2134", "2431", "2143"}, Comparator.naturalOrder()));
    }

    @Test
    public void findMaxInEmpty() {
        assertThrows(NoSuchElementException.class, () -> IntegerStringUtility.findMax(new String[]{}, Comparator.naturalOrder()));
    }

    @Test
    public void findMaxInLengthOne() {
        assertEquals("2341", IntegerStringUtility.findMax(new String[]{"2341"}, Comparator.naturalOrder()));
    }
    @Test
    public void findMaxSimilarityGroupSameIntSizeAndLengthGroups() {
        int[] intArr = new int[]{123, 456, 789, 321, 654, 987};
        assertTrue(Arrays.equals(new String[]{"789", "987"}, IntegerStringUtility.findMaximumSimilarityGroup(intArr)));
    }

    @Test
    public void findMaxSimilarityGroupDifferentIntSizeGroups() {
        int[] intArr = new int[]{43, 3, 9, 100};
        assertTrue(Arrays.equals(new String[]{"100"}, IntegerStringUtility.findMaximumSimilarityGroup(intArr)));
    }
    @Test
    public void findMaxSimilarityGroupEmpty() {
        Integer[] intArr = new Integer[]{};

        assertThrows(NoSuchElementException.class, () -> IntegerStringUtility.findMax(intArr, Comparator.naturalOrder()));
    }
    @Test
    public void findMaxSimilarityGroupDifferentIntEmpty() {
        int[] intArr = new int[]{};
        assertThrows(NoSuchElementException.class, () -> IntegerStringUtility.findMaximumSimilarityGroup(intArr));
    }

    @Test
    public void findMaxSimilarityGroupDifferentLengthGroups() {
        int[] intArr = new int[]{988, 889, 765, 567, 657, 234};
        assertTrue(Arrays.equals(new String[]{"765", "567", "657"}, IntegerStringUtility.findMaximumSimilarityGroup(intArr)));
    }

    @Test
    public void findMaxStringNumericalCmp(){
        assertEquals("321", IntegerStringUtility.findMax(new String[]{"123", "321", "231"}, stringNumericalCmp));
        assertEquals("999", IntegerStringUtility.findMax(new String[]{"123", "321", "999"}, stringNumericalCmp));
        assertEquals("999", IntegerStringUtility.findMax(new String[]{"999", "321", "231"}, stringNumericalCmp));
        assertEquals("231", IntegerStringUtility.findMax(new String[]{"123", "321", "231"}, stringSimilarityCmp));
        assertEquals("123", IntegerStringUtility.findMax(new String[]{"123", "121", "112"}, stringSimilarityCmp));
    }

    @Test
    public void getSimilarityGroupsShort() {
        String[] stringArray = new String[]{"0", "00", "101", "011", "0100", "00"};
        String[][] compareArr = new  String[][]{{"0"}, {"00","00"}, {"101","011"}, {"0100"}};
        for (int i = 0; i < compareArr.length; i++) {
            for (int j = 0; j < compareArr[i].length; j++) {
                assertEquals(compareArr[i][j], IntegerStringUtility.getSimilarityGroups(stringArray)[i][j]);
            }
        }
    }
    @Test
    public void getSimilarityGroupsEmpty() {
        String[][] arr = new String[][]{};
        assertEquals(Arrays.toString(arr), Arrays.toString(IntegerStringUtility.getSimilarityGroups(new String[]{})));
    }

    @Test
    public void getSimilarityGroupsSameLengths() {
        String[] stringArray = new String[]{"123", "456", "321", "654", "987"};
        String[][] compareArr = new  String[][]{{"123", "321"}, {"456","654"}, {"987"}};
        for (int i = 0; i < compareArr.length; i++) {
            for (int j = 0; j < compareArr[i].length; j++) {
                assertEquals(compareArr[i][j], IntegerStringUtility.getSimilarityGroups(stringArray)[i][j]);
            }
        }
    }

    @Test
    public void findMaxSimilarityGroupTest(){
        String[] compareArr = new String[]{"123", "321", "213"};
        assertEquals(Arrays.toString(compareArr), Arrays.toString(IntegerStringUtility.findMaximumSimilarityGroup(array)));
    }
    @Test
    public void findMaxSimilarityGroupAllSameSizeTest(){
        int[] arr = new int[]{101, 101, 101};
        String[] compareArr = new String[]{"101", "101", "101"};
        assertEquals(Arrays.toString(compareArr), Arrays.toString(IntegerStringUtility.findMaximumSimilarityGroup(arr)));
    }
    @Test
    public void findMaxSimilarityGroupAllDifferentSizeTest(){
        int[] arr = new int[]{123, 234, 345};
        String[] compareArr = new String[]{"345"};
        assertEquals(Arrays.toString(compareArr), Arrays.toString(IntegerStringUtility.findMaximumSimilarityGroup(arr)));
    }
}