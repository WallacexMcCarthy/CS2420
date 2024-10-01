package assign05;

/**
 * this class is for testing the runtime of the quicksort algorithm on a descending ArrayList
 * @version 09/30/24
 * @author Isaac Buehner
 */
public class QuickSortDescendingTimingExperiment extends ArrayListSortTimingExperiment{
    private static String problemSizeDescription = "Quick Sort Descending";
    private static int problemSizeMin = 1000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 500;
    private static int experimentIterationCount = 50;
    private QuickSorter<Integer> quickSort;
    protected static PivotChooser<Integer> pivotChooser = new MedianOfThreePivotChooser<>();

    /**
     * Constructor to build the timing experiment.
     */
    public QuickSortDescendingTimingExperiment() {
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
        populateDescendingArrayList(problemSize);
        this.quickSort = new QuickSorter<>(pivotChooser);
    }

    /**
     * method to run the computation to be timed.
     */
    @Override
    protected void runComputation() {
        quickSort.sort(list);
    }

    public static void main(String[] args) {
        QuickSortDescendingTimingExperiment experiment = new QuickSortDescendingTimingExperiment();

        experiment.printResults();

    }
}
