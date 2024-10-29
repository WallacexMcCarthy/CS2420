package assign08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class test {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Test isEmpty on a new tree
        System.out.println("Is empty (expect true): " + bst.isEmpty());

        // Test add method
        System.out.println("Adding 10: " + bst.add(10));
        System.out.println("Adding 5: " + bst.add(5));
        System.out.println("Adding 15: " + bst.add(15));
        System.out.println("Adding 10 again (duplicate, expect false): " + bst.add(10));
        System.out.println("Current size (expect 3): " + bst.size());

        // Test contains method
        System.out.println("Contains 10 (expect true): " + bst.contains(10));
        System.out.println("Contains 7 (expect false): " + bst.contains(7));

        // Test addAll method
        System.out.println("Adding [3, 7, 20] (expect true): " + bst.addAll(Arrays.asList(3, 7, 20)));
        System.out.println("Contains 3 (expect true): " + bst.contains(3));
        System.out.println("Contains 20 (expect true): " + bst.contains(20));
        System.out.println("Current size (expect 6): " + bst.size());

         //Test min and max methods
        try {
            System.out.println("Minimum (expect 3): " + bst.min());
            System.out.println("Maximum (expect 20): " + bst.max());
        } catch (NoSuchElementException e) {
            System.out.println("Tree is empty: " + e.getMessage());
        }

        // Test in-order traversal
        System.out.println("In-order traversal (expect [3, 5, 7, 10, 15, 20]): " + bst.toArrayList());

//        // Test remove method
//        System.out.println("Removing 7 (expect true): " + bst.remove(7));
//        System.out.println("Contains 7 (expect false): " + bst.contains(7));
//        System.out.println("In-order traversal after removal (expect [3, 5, 10, 15, 20]): " + bst.toArrayList());

//        // Test removeAll method
//        System.out.println("Removing [3, 15] (expect true): " + bst.removeAll(Arrays.asList(3, 15)));
//        System.out.println("In-order traversal after removing 3 and 15 (expect [5, 10, 20]): " + bst.toArrayList());
//        System.out.println("Current size (expect 3): " + bst.size());

        // Test clear method
        bst.clear();
        System.out.println("Is empty after clear (expect true): " + bst.isEmpty());
        System.out.println("Current size (expect 0): " + bst.size());
        System.out.println("In-order traversal after clear (expect []): " + bst.toArrayList());
    }
}
