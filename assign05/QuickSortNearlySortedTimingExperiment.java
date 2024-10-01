package assign05;

/**
 * this class is for testing the runtime of the quicksort algorithm on a nearly sorted ArrayList
 * @version 09/30/24
 * @author Isaac Buehner
 */
public class QuickSortNearlySortedTimingExperiment extends ArrayListSortTimingExperiment{
    private static String problemSizeDescription = "Quick Sort Nearly Sorted";
    private static int problemSizeMin = 1000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 500;
    private static int experimentIterationCount = 50;
    private QuickSorter<Integer> quickSort;
    protected static PivotChooser<Integer> pivotChooser;

    /**
     * Constructor to build a general timing experiment.
     */
    public QuickSortNearlySortedTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    /**
     * method for setting up the infrastructure for the experiment
     * for a given problem size.
     *
     * @param problemSize - the problem size for one experiment
     */
    @Override
    protected void setupExperiment(int problemSize) {
        populateNearlyAscendingArray(problemSize);
        this.quickSort = new QuickSorter<>(pivotChooser);
    }

    /**
     * method to run the computation to be timed.
     */
    @Override
    protected void runComputation() {
        quickSort.sort(array);
    }

    public static void main(String[] args) {
        QuickSortNearlySortedTimingExperiment experiment = new QuickSortNearlySortedTimingExperiment();
        pivotChooser = new MedianOfThreePivotChooser<>();
        experiment.printResults();
    }
}
