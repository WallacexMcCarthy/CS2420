package assign05;

import java.util.*;

/**
 * Abstract class to perform timing experiments for sorting ArrayLists with varying degrees of "sortedness".
 *
 * @author CS 2420 Course Staff and Isaac Buehner
 * @version 2024-09-12
 */
public abstract class ArrayListSortTimingExperiment extends TimingExperiment {
    protected ArrayList<Integer> list;
    protected Random rng = new Random();

    /**
     * Constructor to build a general timing experiment.
     *
     * @param problemSizeDescription   - description of the problem size for the experiment
     * @param problemSizeMin           - minimum array size
     * @param problemSizeCount         - number of array sizes to use in the experiment
     * @param problemSizeStep          - Step size between consecutive array sizes
     * @param experimentIterationCount - Number of times to run computation for a given array size
     */
    public ArrayListSortTimingExperiment(String problemSizeDescription, int problemSizeMin, int problemSizeCount, int problemSizeStep, int experimentIterationCount) {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    /**
     * Helper method to populate the ArrayList with random integers in ascending order.
     *
     * @implNote integers are bounded between 0 and 20 + 10 * problemSize
     * @param problemSize - size of the array
     */
    protected void populateAscendingArrayList(int problemSize) {
        list = new ArrayList<>();
        int currentElement = rng.nextInt(20);
        for (int i = 0; i < problemSize; i++) {
            list.add(currentElement);
            currentElement += rng.nextInt(10);
        }
    }

    /**
     * Helper method to populate the ArrayList with random integers in random order.
     *
     * @implNote method must call populateAscendingArrayList and then shuffle the contents of the ArrayList
     * @param problemSize - size of the array
     */
    protected void populateRandomArrayList(int problemSize) {
        this.populateAscendingArrayList(problemSize);
        Collections.shuffle(list);
    }

    /**
     * Helper method to populate the ArrayList with random integers in descending order.
     *
     * @implNote method must call populateAscendingArrayList and then reverse the contents of the array
     * @param problemSize - size of the array
     */
    protected void populateDescendingArrayList(int problemSize) {
        this.populateAscendingArrayList(problemSize);
        int temp;
        for (int i = list.size() - 1; i >= list.size()/2; i--) {
            temp = this.list.get(i);
            this.list.set(i, list.size() - i - 1);
            list.set((this.list.size() - i - 1), temp);
        }
    }

    /**
     * Helper method to populate the ArrayList with random integers in nearly ascending order.
     *
     * @implNote method must call populateAscendingArrayList and then swap a small number of random
     * pairs of nearby elements
     */
    protected void populateNearlyAscendingArrayList(int problemSize) {
        populateAscendingArrayList(problemSize);
        // Choose a random number of pairs to swap, between 5 and 19
        int swapCount = 5 + rng.nextInt(15);
        for (int i = 0; i < swapCount; i++) {
            // Choose a random index, excluding the final 11 indices
            int idx1 = rng.nextInt(problemSize - 11);
            // Choose an index between 1 and 10 to the right of idx1
            int idx2 = idx1 + 1 + rng.nextInt(10);
            // Swap the entries at those two indices
            swapEntries(idx1, idx2);
        }
    }

    /**
     * Helper method to swap two entries of the ArrayList.
     *
     * @param idx1 - first index to swap
     * @param idx2 - second index to swap
     * @throws IndexOutOfBoundsException if either index is out of bounds
     */
    private void swapEntries(int idx1, int idx2) throws IndexOutOfBoundsException {
        if (idx1 < 0 || idx1 >= list.size() || idx2 < 0 || idx2 >= list.size()) {
            throw new IndexOutOfBoundsException();
        }
        int temp = list.get(idx1);
        list.set(idx1, list.get(idx2));
        list.set(idx2, temp);
    }

}


