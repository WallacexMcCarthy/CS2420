package assign08;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this class contains a timing experiment for the ArraySortedSet class that tests
 * the growth rate of adding a specified number of elements in nearly sorted order to an ArraySortedSet
 * @version 10/28/2024
 * @author Isaac Buehner
 */
public class ArraySortedSetAddAllNearlySortedTimingExperiment extends TimingExperiment{

    private static String problemSizeDescription = "Array Nearly Sorted";
    private static int problemSizeMin = 50;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 50;
    private static int experimentIterationCount = 20;

    protected ArraySortedSet<Integer> sortedSet;
    protected List<Integer> elementsToAdd;

    /**
     * the constructor for the timing experiment
     */
    public ArraySortedSetAddAllNearlySortedTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
        elementsToAdd = new ArrayList<>();
        sortedSet = new ArraySortedSet<>();
    }
    /**
     * method for setting up the infrastructure for the experiment
     * for a given problem size.
     *
     * @param problemSize - the problem size for one experiment
     */
    @Override
    protected void setupExperiment(int problemSize) {
        populateNearlySortedList(problemSize);
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
    public void populateNearlySortedList(int problemSize) {
        Random rng = new Random();
        int currentElement = rng.nextInt(25);

        for (int i = 0; i < problemSize; i++) {
            elementsToAdd.add(currentElement);
            currentElement += rng.nextInt(-problemSize, problemSize) + i;
        }
    }

    public static void main(String[] args) {
        TimingExperiment experiment = new ArraySortedSetAddAllNearlySortedTimingExperiment();
        experiment.printResults();
    }
}
