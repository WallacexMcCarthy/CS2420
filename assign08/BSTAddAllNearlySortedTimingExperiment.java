package assign08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * this class contains experiments for the BinarySearchTree's addAll method for a list of nearly sorted elements.
 * @version 10/29/2024
 * @author Isaac Buehner
 */
public class BSTAddAllNearlySortedTimingExperiment extends TimingExperiment{
    private static String problemSizeDescription = "BST Nearly Sorted List";
    private static int problemSizeMin = 100;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 100;
    private static int experimentIterationCount = 50;

    protected BinarySearchTree<Integer> sortedSet;
    protected List<Integer> elementsToAdd;
    final Random random = new Random();

    /**
     * The constructor for the timing experiment
     */
    public BSTAddAllNearlySortedTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }
    /**
     * the method for setting up the experiment for a given problem size
     *
     * @param problemSize - the problem size for the experiment
     */
    @Override
    protected void setupExperiment(int problemSize) {
        elementsToAdd = new ArrayList<>();
        sortedSet = new BinarySearchTree<>();
        populateNearlySortedList(problemSize);
    }

    /**
     * this method runs the computation to be timed.
     */
    @Override
    protected void runComputation() {
        sortedSet.addAll(elementsToAdd);
    }

    /**
     * this is a public helper method that will populate the list with values in nearly sorted order
     * @param problemSize the number of elements to add
     */
    public void populateNearlySortedList(int problemSize) {
        for (int i = 0; i < problemSize; i++) {
            elementsToAdd.add(i);
            if(i % 500 == 0){
                Collections.swap(elementsToAdd, random.nextInt(elementsToAdd.size()), random.nextInt(elementsToAdd.size()));
            }
        }
    }

    public static void main(String[] args) {
        TimingExperiment experiment = new BSTAddAllNearlySortedTimingExperiment();
        experiment.printResults();
    }

}
