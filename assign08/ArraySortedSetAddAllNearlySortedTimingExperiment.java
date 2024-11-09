package assign08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * this class contains an experiment for the ArraySortedSet addAll method for a list of nearly sorted elements
 * @version 10/29/2024
 * @author Isaac Buehner
 */
public class ArraySortedSetAddAllNearlySortedTimingExperiment extends TimingExperiment{

    private static String problemSizeDescription = "ArraySortedSet Nearly Sorted List ";
    private static int problemSizeMin = 1000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 1000;
    private static int experimentIterationCount = 50;

    protected ArraySortedSet<Integer> sortedSet;
    protected List<Integer> elementsToAdd;
    final Random random = new Random();


    /**
     * the constructor for the timing experiment
     */
    public ArraySortedSetAddAllNearlySortedTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }
    /**
     * the method for setting up the experiment for a given problem size.
     *
     * @param problemSize - the problem size for one experiment
     */
    @Override
    protected void setupExperiment(int problemSize) {
        elementsToAdd = new ArrayList<>();
        sortedSet = new ArraySortedSet<>();
        populateNearlySortedList(problemSize);
    }

    /**
     * this method runs the computation to be timed
     */
    @Override
    protected void runComputation() {
        sortedSet.addAll(elementsToAdd);
    }

    /**
     * this is the public helper method that will populate the list in nearly sorted order
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
        TimingExperiment experiment = new ArraySortedSetAddAllNearlySortedTimingExperiment();
        experiment.printResults();
    }
}
