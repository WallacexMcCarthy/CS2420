package assign04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * this class uses an insertion sort method to perform several operations on string arrays
 * @author Isaac Buehner and Wallace McCarthy
 * @version 09/13/24
 */
public class IntegerStringUtility {

    /**
     * this method sorts an array according to the sorting pattern defined by an input comparator
     * @param arr the array to be sorted
     * @param cmp the comparator to sort with
     * @param <T> the generic type of the array
     */
    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp){
        for (int i = 1; i < arr.length; i++) {
            int counter = 1;
            T element = arr[i];
            while(i-counter >= 0 && cmp.compare(element, arr[i - counter]) < 0){
                T temp = arr[i - counter];
                arr[i - counter] = element;
                arr[i-counter + 1] = temp;
                counter++;
            }
        }
    }

    /**
     * this method uses insertion sort to return the max value of an array
     * @param arr the array to search
     * @param cmp the comparator to sort with
     * @return the maximum value according to the input comparator
     * @param <T> the generic type of the array
     * @throws NoSuchElementException if the element checked does not exist
     */
    public static <T> T findMax(T[] arr, Comparator<? super T> cmp) throws NoSuchElementException {
        if(arr.length ==0){
            return null;
        }
        T[] clone = arr.clone();
        insertionSort(clone, cmp);
        return clone[clone.length-1];
    }

    /**
     * this method uses the string similarity comparator to return a 2D array of similar string groups
     * @param arr a string array to be processed
     * @return a 2D array of strings
     */
    public static String[][] getSimilarityGroups(String[] arr){
        String[] cloneArr = arr.clone();
        insertionSort(cloneArr, new StringSimilarityComparator());
        String temp = cloneArr[0];
        int similarities = 1;
        for (int i = 0; i < cloneArr.length; i++) {
            if(new StringSimilarityComparator().compare(temp, cloneArr[i]) != 0) {
                similarities++;
                temp = cloneArr[i];
            }
        }
        String[][] result = new String[similarities][];
        ArrayList<String> tempList = new ArrayList<>();
        temp = cloneArr[0];
        int resultCount = 0;
        for (int i = 0; i <= cloneArr.length; i++) {
            if(i == cloneArr.length){
                result[resultCount] = new String[tempList.size()];
                for (int j = 0; j < tempList.size(); j++) {
                    result[resultCount][j] = tempList.get(j);
                }
            }else{
                if(new StringSimilarityComparator().compare(temp, cloneArr[i]) != 0){
                    result[resultCount] = new String[tempList.size()];
                    for (int j = 0; j < tempList.size(); j++) {
                        result[resultCount][j] = tempList.get(j);
                    }
                    tempList = new ArrayList<>();
                    tempList.add(cloneArr[i]);
                    temp = cloneArr[i];
                    resultCount++;
                }else{
                    tempList.add(cloneArr[i]);
                }
            }
        }
        return result;
    }

    /**
     * this method translates an input array of ints to an array of strings that is processed by
     * the getSimilarityGroups method. The resulting similarity groups are then processed by the findMax method
     * to determine the maximum similarity group.
     * @param arr an int array to be processed
     * @return a string array containing the maximum similarity group
     * @throws NoSuchElementException if the element checked does not exist
     */
    public static String[] findMaximumSimilarityGroup(int[] arr) throws NoSuchElementException{
        String[] array = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i] + "";
        }
        String[][] similarityGroups = getSimilarityGroups(array);
        return findMax(similarityGroups, new StringSimilarityGroupComparator());
    }

    /**
     * this comparator translates strings of integers to char arrays so that they may be compared
     * lexicographically without worry of leading 0s
     * a string is considered greater than another if
     * its integer interpretation is greater than the other's
     */
    public static class StringNumericalValueComparator implements Comparator<String>{

        @Override
        public int compare(String t0, String t1) {
            char[] arr1 = t0.toCharArray();
            char[] arr2 = t1.toCharArray();
            char[]arr1NoZero = new char[0];
            char[]arr2NoZero = new char[0];

            for (int i = 0; i < arr1.length; i++) {
                if(arr1[i] == '0') {
                    continue;
                }
                arr1NoZero = new char[arr1.length - i];
                for (int j = i; j < arr1.length; j++) {
                    arr1NoZero[j-i] = arr1[j];
                }
                break;
            }
            for (int i = 0; i < arr2.length; i++) {
                if(arr2[i] == '0') {
                    continue;
                }
                arr2NoZero = new char[arr2.length - i];
                for (int j = i; j < arr2.length; j++) {
                    arr2NoZero[j-i] = arr2[j];
                }
                break;
            }
            if(arr1NoZero.length > arr2NoZero.length) {
                return 1;
            }
            if(arr1NoZero.length < arr2NoZero.length) {
                return -1;
            }
            for (int i = 0; i < arr1NoZero.length; i++) {
                if (arr1NoZero[i] > arr2NoZero[i]) {
                    return 1;
                }
                if (arr1NoZero[i] < arr2NoZero[i]) {
                    return -1;
                }
            }
            return 0;
        }
    }

    /**
     * this comparator translates strings of integers to char arrays so that they may be compared
     * lexicographically without worry of leading 0s
     * strings are considered similar if they contain the same digits in any order and are the same length
     * a string is considered greater than another if its length is greater or
     * if its sorted integer interpretation is greater than the other's
     */
    public static class StringSimilarityComparator implements Comparator<String>{

        @Override
        public int compare(String s1, String s2) {
            if(s1.length() < s2.length()){
                return -1;
            }else if(s1.length() > s2.length()){
                return 1;
            }else{
                char[] arr1 = s1.toCharArray();
                char[] arr2 = s2.toCharArray();
                Character[] charArray1 = new Character[arr1.length];
                Character[] charArray2 = new Character[arr2.length];
                for (int i = 0; i < arr1.length; i++) {
                    charArray1[i] = arr1[i];
                    charArray2[i] = arr2[i];
                }
                insertionSort(charArray1, Comparator.naturalOrder());
                insertionSort(charArray2, Comparator.naturalOrder());

                for (int i = 0; i < charArray1.length; i++) {
                    if(charArray1[i].compareTo(charArray2[i]) != 0){
                        return charArray1[i].compareTo(charArray2[i]);
                    }
                }
            }
            return 0;
        }
    }

    /**
     * this comparator compares similar string groups together
     * a string group is considered greater than another if its length is greater or
     * if the maximum interpreted integer value in the string is greater than the other's
     */
    public static class StringSimilarityGroupComparator implements Comparator<String[]>{

        @Override
        public int compare(String[] s1, String[] s2) {
            if(s1.length < s2.length){
                return -1;
            }else if(s1.length > s2.length){
                return 1;
            }else{
                String a = findMax(s1, new StringNumericalValueComparator());
                String b = findMax(s2, new StringNumericalValueComparator());
                if(a == null || b == null){
                    return 0;
                }else{
                    return new StringNumericalValueComparator().compare(a, b);
                }
            }
        }
    }

}
