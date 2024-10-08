package assign05;

/**
 * this class is for testing the runtime of the quicksort algorithm on a shuffled ArrayList
 * @version 09/30/24
 * @author Isaac Buehner
 */
public class QuickSortShuffledTimingExperiment extends ArrayListSortTimingExperiment{
    private static String problemSizeDescription = "Quick Sort Shuffled";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 5000;
    private static int experimentIterationCount = 20;
    private QuickSorter<Integer> quickSort;
    protected static PivotChooser<Integer> pivotChooser = new MedianOfThreePivotChooser<>();

    /**
     * Constructor to build the timing experiment.
     */
    public QuickSortShuffledTimingExperiment() {
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
        populateRandomArrayList(problemSize);
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
        QuickSortShuffledTimingExperiment experiment = new QuickSortShuffledTimingExperiment();
        pivotChooser = new MedianOfThreePivotChooser<>();
        experiment.printResults();
    }
}