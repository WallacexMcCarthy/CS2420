package assign05;

public class MergeSortDescendingTimingExperiment extends ArrayListSortTimingExperiment{
    private static String problemSizeDescription = "Merge Sort Shuffled";
    private static int problemSizeMin = 1000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 1000;
    private static int experimentIterationCount = 50;
    protected static int threshold;
    private MergeSorter<Integer> mergeSort;

    /**
     * Constructor to build a general timing experiment.
     */
    public MergeSortDescendingTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    /**
     * Abstract method for setting up the infrastructure for the experiment
     * for a given problem size.
     *
     * @param problemSize - the problem size for one experiment
     */
    @Override
    protected void setupExperiment(int problemSize) {
        populateDescendingArray(problemSize);
        this.mergeSort = new MergeSorter<>(threshold);
    }

    /**
     * Abstract method to run the computation to be timed.
     */
    @Override
    protected void runComputation() {
        mergeSort.sort(array);
    }

    public static void main(String[] args) {
        MergeSortShuffledTimingExperiment experiment = new MergeSortShuffledTimingExperiment();
        threshold = 3;
        experiment.printResults();
    }
}
