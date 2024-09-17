package assign04;

import java.util.Comparator;

/**
 * this class is a timing experiment for testing the runtime efficiency of InsertionSort
 * from the assignment 4 IntegerStringUtility in the average case scenario
 * @author Isaac Buehner
 * @version 09/17/24
 */
public class InsertionSortAverageCaseTimingExperiment extends ArraySortTimingExperiment{
    private static String problemSizeDescription = "Insertion Sort Average Case";
    private static int problemSizeMin = 500;
    private static int problemSizeCount = 50;
    private static int problemSizeStep = 100;
    private static int experimentIterationCount = 50;


    public static void main(String[] args) {
        InsertionSortAverageCaseTimingExperiment experiment = new InsertionSortAverageCaseTimingExperiment();
        experiment.printResults();
    }

    /**
     * Constructor to build a sort timing experiment.
     */
    public InsertionSortAverageCaseTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    /**
     * setup for running the experiment with a randomly generated array
     * @param problemSize - the problem size for one experiment
     */
    @Override
    protected void setupExperiment(int problemSize) {
        populateRandomArray(problemSize);

    }

    /**
     * run the insertion sort method from the IntegerStringUtility class using natural ordering
     */
    @Override
    protected void runComputation() {
        IntegerStringUtility.insertionSort(array, Comparator.naturalOrder());
    }
}
