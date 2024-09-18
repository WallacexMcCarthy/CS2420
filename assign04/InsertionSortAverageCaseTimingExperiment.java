package assign04;

import java.util.Comparator;

/**
 * This class times Insertion Sort on the average array of which is randomly filled with values
 * @author Wallace McCarthy
 * @version 09/17/24
 */
public class InsertionSortAverageCaseTimingExperiment extends ArraySortTimingExperiment{
    private static String problemSizeDescription = "Insertion Sort Average Case";
    private static int problemSizeMin = 1000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 1000;
    private static int experimentIterationCount = 50;


    public static void main(String[] args) {
        InsertionSortAverageCaseTimingExperiment insertionSort = new InsertionSortAverageCaseTimingExperiment();
        insertionSort.printResults();
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
