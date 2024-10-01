package assign05;

/**
 * this class is for testing the runtime of the mergesort algorithm on a shuffled ArrayList
 * @version 09/30/24
 * @author Isaac Buehner
 */
public class MergeSortShuffledTimingExperiment extends ArrayListSortTimingExperiment{
    private static String problemSizeDescription = "Merge Sort Shuffled";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 5000;
    private static int experimentIterationCount = 20;
    protected static int threshold;
    private MergeSorter<Integer> mergeSort;

    /**
     * Constructor to build a general timing experiment.
     */
    public MergeSortShuffledTimingExperiment() {
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
        populateRandomArray(problemSize);
        this.mergeSort = new MergeSorter<>(threshold);
    }

    /**
     * method to run the computation to be timed.
     */
    @Override
    protected void runComputation() {
        mergeSort.sort(array);
    }

    public static void main(String[] args) {
        MergeSortShuffledTimingExperiment experiment = new MergeSortShuffledTimingExperiment();
        threshold = 16;
        experiment.printResults();
    }
}
