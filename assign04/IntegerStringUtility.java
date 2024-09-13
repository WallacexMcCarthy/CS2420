package assign04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class IntegerStringUtility {
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
    public static <T> T findMax(T[] arr, Comparator<? super T> cmp) throws NoSuchElementException {
        if(arr.length ==0){
            return null;
        }
        T[] clone = arr.clone();
        insertionSort(clone, cmp);
        return clone[clone.length-1];
    }
    public static String[][] getSimilarityGroups(String[] arr){
        String[] cloneArr = arr.clone();
        insertionSort(cloneArr, new StringSimilarityComparator());
        String temp = cloneArr[0];
        ArrayList<String> tempList = new ArrayList<>();
        int similarities = 1;
        for (int i = 0; i < cloneArr.length; i++) {
            if(new StringSimilarityComparator().compare(temp, cloneArr[i]) != 0) {
                similarities++;
            }
        }
        String[][] result = new String[similarities][];

    }
    public static String[] findMaximumSimilarityGroup(int[] arr) throws NoSuchElementException{

    }

    public static class StringNumericalValueComparator implements Comparator<String>{

        @Override
        public int compare(String s, String t1) {
            char[] arr1 = s.toCharArray();
            char[] arr2 = t1.toCharArray();
            char[]arr1NoZero = new char[0];
            char[]arr2NoZero = new char[0];

            for (int i = 0; i < arr1.length; i++) {
                if(arr1[i] == 0) {
                    continue;
                }
                arr1NoZero = new char[arr1.length - i];
                for (int j = i; j < arr1.length; j++) {
                    arr1NoZero[j-i] = arr1[j];
                }
                break;
            }

            for (int i = 0; i < arr2.length; i++) {
                if(arr2[i] == 0) {
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
                insertionSort(charArray1, (x , y) -> x.compareTo(y));
                insertionSort(charArray2, (x , y) -> x.compareTo(y));

                for (int i = 0; i < charArray1.length; i++) {
                    if(charArray1[i].compareTo(charArray2[i]) != 0){
                        return charArray1[i].compareTo(charArray2[i]);
                    }
                }
            }
            return 0;
        }
    }
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
                    return a.compareTo(b);
                }
            }
        }
    }

}
