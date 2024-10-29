package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * this class is a Binary Search Tree meaning, it has a root node and any nodes with data smaller than
 * that of the root are to its left, and any nodes with larger data are to the right
 * @param <Type> the generic type of the data
 * @version 10/28/2024
 * @author Wallace McCarthy and Isaac Buehner
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
    private int size;
    private TreeNode<Type> root;

    /**
     * this nested class is used to represent each node in the tree
     * each node has data, a parent node(except the root), and children nodes left and right
     * @param <T> the generic type of the data
     */
    private static class TreeNode <T extends Comparable<? super T>>{
        private T data;
        protected TreeNode<T> parent;
        protected TreeNode<T> left = null;
        protected TreeNode<T> right = null;

        /**
         * constructor for a node
         * @param data the data to be assigned to the node
         */
        public TreeNode (T data) {
            this.data = data;
        }
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(Type item) {
        if(isEmpty()) {
            this.root = new TreeNode<>(item);
            this.size++;
            return true;
        }
        TreeNode<Type> current = traverseSearch(item);
        TreeNode<Type> newNode = new TreeNode<>(item);
        newNode.parent = current;
        if(current.data.compareTo(item) == 0){
            return false;
        }else if(current.data.compareTo(item) > 0){
            current.left = newNode;
        }else{
            current.right = newNode;
        }
        size++;
        return true;
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise,
     * returns false
     */
    @Override
    public boolean addAll(Collection<? extends Type> items) {
        boolean flag = false;
        for(Type item : items){
            if(add(item)){
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     * otherwise, returns false
     */
    @Override
    public boolean contains(Type item) {
        return !isEmpty() && traverseSearch(item).data.compareTo(item) == 0;
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     * in this set that is equal to it; otherwise, returns false
     */
    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        for(Type item : items){
            if(!contains(item)){
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the smallest item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type min() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        TreeNode<Type> current = this.root;
        while(current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    /**
     * Returns the largest item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type max() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        TreeNode<Type> current = this.root;
        while(current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns an ArrayList containing all the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList<Type> toArrayList() {
        ArrayList<Type> arr = new ArrayList<>();
        inOrderTraverse(arr, root);
        return arr;
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is,
     *         if the input item was actually removed); otherwise, returns false
     * @throws UnsupportedOperationException if the {@code remove} operation is
     *         not supported by the derived class
     */
    @Override
    public boolean remove(Type item) {
        if(!contains(item)){
            return false;
        }
        TreeNode<Type> element = traverseSearch(item);
        // The element has no children
        if(element.left == null && element.right == null){
            if(element.parent == null){ // There is only one node in the tree
                root = null;
            }else if(element.parent.left != null && element.parent.left.data.compareTo(element.data) == 0){ // The element is its parents left node
                element.parent.left = null;
            }else{ // The element is its parents right node
                element.parent.right = null;
            }
        }else if(element.left == null){
            if(element.parent == null){ // There is only one node in the tree with one child
                root = element.right;
                element.right.parent = null;
            }else if(element.parent.left != null && element.parent.left.data.compareTo(element.data) == 0){ // The element is its parents left node
                element.parent.left = element.right;
                element.right.parent = element.parent;
            }else{ // The element is its parents right node
                element.parent.right = element.right;
                element.right.parent = element.parent;
            }
        }else if(element.right == null){
            if(element.parent == null){ // There is only one node in the tree with one child
                root = element.left;
                element.left.parent = null;
            }else if(element.parent.left != null && element.parent.left.data.compareTo(element.data) == 0){ // The element is its parents left node
                element.parent.left = element.left;
                element.left.parent = element.parent;
            }else{ // The element is its parents right node
                element.parent.right = element.left;
                element.left.parent = element.parent;
            }
        }else{ // THe node has two children
            TreeNode<Type> leaf = element.left;
            while(leaf.right != null){
                leaf = leaf.right;
            }
            element.data = leaf.data;
            if (leaf.parent.left != null && leaf.parent.left.data.compareTo(leaf.data) == 0) {
                leaf.parent.left = leaf.left;
            } else {
                leaf.parent.right = leaf.left;
            }

            if (leaf.left != null) {
                leaf.left.parent = leaf.parent;
            }
        }
        size--;
        return true;
    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is,
     *         if any item in the input collection was actually removed); otherwise,
     *         returns false
     * @throws UnsupportedOperationException if the {@code removeAll} operation is
     *         not supported by the derived class
     */
    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        boolean flag = false;
        for(Type item : items){
            if(remove(item)){
                flag = true;
            }
        }
        return flag;
    }

    /**
     * this method is a driver method for the traverse method
     * @param item the data to be searched for
     * @return the node with data equal to item or closest to it
     */
    private TreeNode<Type> traverseSearch(Type item) {
        TreeNode<Type> current = this.root;
        return traverse(current, item);
    }

    /**
     * this method traverses the tree to find a node containing the data equal to item
     * if no node is found, then the node with the closest matching data is returned
     * @param current the node being processed
     * @param item the data being searched for
     * @return the node with the closest data to item
     */
    private TreeNode<Type> traverse(TreeNode<Type> current, Type item) {
        if (item.compareTo(current.data) > 0) {
            if(current.right == null){
                return current;
            }else{
                return traverse(current.right, item);
            }
        } else if (item.compareTo((current.data)) < 0) {
            if(current.left == null){
                return current;
            }else{
                return traverse(current.left, item);
            }
        } else {
            return current;
        }
    }

    /**
     * this method runs an in-order traversal of the tree to populate an array with the data in the tree
     * @param array the array to be populated
     * @param current the current node being added
     */
    private void inOrderTraverse(ArrayList<Type> array, TreeNode<Type> current) {
        if(current == null){
            return;
        }
        inOrderTraverse(array, current.left);
        array.add(current.data);
        inOrderTraverse(array, current.right);
    }


}
