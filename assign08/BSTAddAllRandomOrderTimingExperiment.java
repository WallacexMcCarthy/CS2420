package assign08;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this class contains a timing experiment for the BinarySearchTree class that tests
 * the growth rate of adding a specified number of elements in random order to a BinarySearchTree
 * @version 10/28/2024
 * @author Isaac Buehner
 */
public class BSTAddAllRandomOrderTimingExperiment extends TimingExperiment{
    private static String problemSizeDescription = "BST Randomly Sorted";
    private static int problemSizeMin = 25;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 25;
    private static int experimentIterationCount = 20;

    protected BinarySearchTree<Integer> sortedSet;
    protected List<Integer> elementsToAdd;

    /**
     * the constructor for the timing experiment
     */
    public BSTAddAllRandomOrderTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
        sortedSet = new BinarySearchTree<>();
        elementsToAdd = new ArrayList<>();
    }
    /**
     * method for setting up the infrastructure for the experiment
     * for a given problem size.
     *
     * @param problemSize - the problem size for one experiment
     */
    @Override
    protected void setupExperiment(int problemSize) {
        populateRandomList(problemSize);
    }

    /**
     * method to run the computation to be timed.
     */
    @Override
    protected void runComputation() {
        sortedSet.addAll(elementsToAdd);
    }

    /**
     * this method is a helper method for populating the elementsToAdd list with a specified number of random elements
     * @param problemSize the number of elements to add
     */
    private void populateRandomList(int problemSize) {
        Random rng = new Random();
        for (int i = 0; i < problemSize; i++) {
            elementsToAdd.add(rng.nextInt());
        }
    }

    public static void main(String[] args) {
        TimingExperiment experiment = new BSTAddAllRandomOrderTimingExperiment();
        experiment.printResults();
    }
}
