package assign08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class times the ArraySortedSet addAll method for a list of randomly sorted elements.
 * @version 10/29/2024
 * @author Wallace McCarthy
 */
public class ArraySortedSetAddAllRandomOrderTimingExperiment extends TimingExperiment{
    private static String problemSizeDescription = "ArraySortedSet Randomly Sorted List";
    private static int problemSizeMin = 1000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 1000;
    private static int experimentIterationCount = 20;

    protected ArraySortedSet<Integer> sortedSet;
    protected List<Integer> elementsToAdd;

    /**
     * The constructor for the timing experiment
     */
    public ArraySortedSetAddAllRandomOrderTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }
    /**
     * The method for setting up the infrastructure for the experiment
     * for a given problem size.
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
     * THis method runs the computation to be timed.
     */
    @Override
    protected void runComputation() {
        sortedSet.addAll(elementsToAdd);
    }

    /**
     * This is the public helper method that will populate the list that we will use when timing our addAll method.
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
