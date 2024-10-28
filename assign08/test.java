package assign08;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        ArraySortedSet<Integer> ints = new ArraySortedSet<>();
        for (int i = 10; i> 0; i--) {
            System.out.println(ints.add(i));
        }
        System.out.println(ints.toArrayList());
        System.out.println(ints.contains(0));
        System.out.println(ints.contains(4));
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(4);
        arr.add(9);
        System.out.println(ints.containsAll(arr));
        System.out.println(ints.isEmpty());
        System.out.println(ints.min());
        System.out.println(ints.max());
        System.out.println(ints.add(11));

        System.out.println(ints.toArrayList());

    }
}
