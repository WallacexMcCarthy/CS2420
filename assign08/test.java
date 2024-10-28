package assign08;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(5);
        tree.add(3);
        tree.add(1);
        tree.add(2);
        tree.add(6);
        System.out.println(tree.max());
        System.out.println(tree.min());
        System.out.println(tree.contains(5));
        System.out.println(tree.contains(1));
        System.out.println(tree.contains(2));
        System.out.println();
        System.out.println(tree.toArrayList());

    }
}
