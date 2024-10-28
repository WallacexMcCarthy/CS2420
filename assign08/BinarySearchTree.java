package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
    private int size;
    private TreeNode<Type> root;

    private static class TreeNode <T extends Comparable<? super T>>{
        private T data;
        protected TreeNode<T> parent;
        protected TreeNode<T> left = null;
        protected TreeNode<T> right = null;

        public TreeNode (T data) {
            this.data = data;
        }

        protected void setParent(TreeNode<T> parent) {
            this.parent = parent;
        }

        protected void setLeft(TreeNode<T> left) {
            this.left = left;
        }

        protected void setRight(TreeNode<T> right) {
            this.right = right;
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
        TreeNode<Type> current = traverseDriver(item);
        TreeNode<Type> addThis = new TreeNode<>(item);
        addThis.parent = current;
        if(current.data.compareTo(item) == 0){
            return false;
        }else if(current.data.compareTo(item) > 0){
            current.left = addThis;
        }else{
            current.right = addThis;
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
        return !isEmpty() && traverseDriver(item).data.compareTo(item) == 0;
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
        return inOrderTraverseDriver();
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
        TreeNode<Type> element = traverseDriver(item);
        if(element.left == null){
            if(element.right == null){
                if(element.parent.left.data.compareTo(element.data) == 0){
                    element.parent.left = null;
                }else{
                    element.parent.right = null;
                }
            }else{
                if(element.parent.left.data.compareTo(element.data) == 0){
                    element.parent.left = element.right;
                }else{
                    element.parent.right = element.right;
                }
            }
        }else{
            System.out.println("");
            TreeNode<Type> leaf = element.left;
            while(leaf.right != null){
                leaf = leaf.right;
            }
            leaf.parent.right = null;
            element.data = leaf.data;
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

    private TreeNode<Type> traverseDriver(Type item) {
        TreeNode<Type> current = this.root;
        return traverse(current, item);
    }

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

    private ArrayList<Type> inOrderTraverseDriver() {
        ArrayList<Type> arr = new ArrayList<>();
        inOrderTraverse(arr, root);
        return arr;
    }
    private void inOrderTraverse(ArrayList<Type> array, TreeNode<Type> current) {
        if(current == null){
            return;
        }
        inOrderTraverse(array, current.left);
        array.add(current.data);
        inOrderTraverse(array, current.right);
    }


}
