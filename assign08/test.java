package assign08;

public class test {
    public static void main(String[] args) {
        ArraySortedSet<Integer> ints = new ArraySortedSet<>();
        for (int i = 10; i> 0; i--) {
            System.out.println(ints.add(i));
        }
        System.out.println(ints.toArrayList());

    }
}
