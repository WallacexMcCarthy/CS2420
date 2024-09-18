package assign04;

import java.util.Comparator;

/**
 * This class times the Insertion Sort for its worst case which is doing an insertion sort on a backwards sorted array.
 * @author Wallace McCarthy
 * @version 09/17/24
 */
public class InsertionSortWorstCaseTimingExperiment extends ArraySortTimingExperiment{
    private static String problemSizeDescription = "Insertion Sort Worst Case";
    private static int problemSizeMin = 1000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 1000;
    private static int experimentIterationCount = 50;


    public static void main(String[] args) {
        InsertionSortWorstCaseTimingExperiment insertionSort = new InsertionSortWorstCaseTimingExperiment();
        insertionSort.printResults();
    }
    /**
     * Constructor to build a sort timing experiment.
     */
    public InsertionSortWorstCaseTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    /**
     * setup for running the experiment with a descending array
     * @param problemSize - the problem size for one experiment
     */
    @Override
    protected void setupExperiment(int problemSize) {
        populateDescendingArray(problemSize);

    }

    /**
     * run the insertion sort method from the IntegerStringUtility class using natural ordering
     */
    @Override
    protected void runComputation() {
        IntegerStringUtility.insertionSort(array, Comparator.naturalOrder());
    }
}
