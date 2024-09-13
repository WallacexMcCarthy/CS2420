package assign04;

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
        
    }
    public static String[][] getSimilarityGroups(String[] arr){

    }
    public static String[] findMaximumSimilarityGroup(int[] arr) throws NoSuchElementException{

    }

    public static class StringNumericalValueComparator implements Comparator<String>{

        @Override
        public int compare(String s, String t1) {
            return 0;
        }
    }
    public static class StringSimilarityComparator implements Comparator<String>{

        @Override
        public int compare(String s, String t1) {
            return 0;
        }
    }
    public static class StringSimilarityGroupComparator implements Comparator<String[]>{

        @Override
        public int compare(String[] strings, String[] t1) {
            return 0;
        }
    }

}
