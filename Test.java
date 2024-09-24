import assign02.PhoneNumber;

import java.util.ArrayList;
import java.util.Comparator;

public class Test {

    public static int power (int x , int y){
        if(y < 0){
            throw new IllegalArgumentException();
        }
        return mathPower(x, y);
    }

    private static int mathPower(int x, int y){
        int z = 1;
        if(y == 0){
            return 1;
        }
        return mathPower(x, y - 1) * x;
    }



    public Test(){

    }

//    @SuppressWarnings("unchecked")
//    public static <Type> boolean contains(Object[] arr, Type a){
//        Type[] as = (Type[]) arr;
//        for (int i = 0; i < arr.length; i++) {
//            if(a.equals(arr[i])){
//                return true;
//            }
//        }
//        return false;
//    }
    public static <Type> boolean contains(Type[] arr, Type a){
        for(Type element : arr){
            if(element.equals(a)){
                return true;
            }
            long b = 1;
            long c = 2;
            int[] array = new int[]{ 1, 2, 3, 4, 5 };
            array[0]++;


        }
        return false;
    }

    public void selectionSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if(arr[i] < arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

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

    public static void main(String[] args) {
        int[] array = new int[]{ 0, 2, 3, 4, 5 };
        array[0]++;
        System.out.println(array[0]);

        System.out.println(power(3, 3));;
        Integer[] arr = new Integer[6];
        for (int i = 0; i < 6; i++) {
            arr[i] = i;
        }
        System.out.println(Test.contains(arr, new PhoneNumber("801.333.3333")));
    }

}
