import assign02.PhoneNumber;

import java.util.ArrayList;

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
        }
        return false;
    }

    public static void main(String[] args) {

        System.out.println(power(3, 3));;
        Integer[] arr = new Integer[6];
        for (int i = 0; i < 6; i++) {
            arr[i] = i;
        }
        System.out.println(Test.contains(arr, new PhoneNumber("801.333.3333")));
    }
}
