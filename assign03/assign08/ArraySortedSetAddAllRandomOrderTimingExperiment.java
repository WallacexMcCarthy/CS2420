package assign03.assign08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * this class contains an experiment for the ArraySortedSet addAll method for a list of randomly sorted elements
 * @version 10/29/2024
 * @author Isaac Buehner
 */
public class ArraySortedSetAddAllRandomOrderTimingExperiment extends TimingExperiment{
    private static String problemSizeDescription = "ArraySortedSet Randomly Sorted List";
    private static int problemSizeMin = 1000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 1000;
    private static int experimentIterationCount = 50;

    protected ArraySortedSet<Integer> sortedSet;
    protected List<Integer> elementsToAdd;

    /**
     * the constructor for the timing experiment
     */
    public ArraySortedSetAddAllRandomOrderTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }
    /**
     * the method for setting up the experiment for a given problem size
     *
     * @param problemSize - the problem size for one experiment
     */
    @Override
    protected void setupExperiment(int problemSize) {
        elementsToAdd = new ArrayList<>();
        sortedSet = new ArraySortedSet<>();
        populateRandomList(problemSize);
    }

    /**
     * this method runs the computation to be timed.
     */
    @Override
    protected void runComputation() {
        sortedSet.addAll(elementsToAdd);
    }

    /**
     * this is the public helper method that will populate the list with random elements
     * @param problemSize the number of elements to add
     */
    private void populateRandomList(int problemSize) {
        for (int i = 0; i < problemSize; i++) {
            elementsToAdd.add(i);
        }
        Collections.shuffle(elementsToAdd);
    }

    public static void main(String[] args) {
        TimingExperiment experiment = new ArraySortedSetAddAllRandomOrderTimingExperiment();
        experiment.printResults();
    }
}
