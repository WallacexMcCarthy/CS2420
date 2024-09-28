package assign05;

public class QuickSortShuffledTimingExperiment extends ArrayListSortTimingExperiment{
    protected static PivotChooser<Integer> pivotChooser;
    /**
     * Constructor to build a general timing experiment.
     *
     * @param problemSizeDescription   - description of the problem size for the experiment
     * @param problemSizeMin           - minimum array size
     * @param problemSizeCount         - number of array sizes to use in the experiment
     * @param problemSizeStep          - Step size between consecutive array sizes
     * @param experimentIterationCount - Number of times to run computation for a given array size
     */
    public QuickSortShuffledTimingExperiment(String problemSizeDescription, int problemSizeMin, int problemSizeCount, int problemSizeStep, int experimentIterationCount) {
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

    }

    /**
     * Abstract method to run the computation to be timed.
     */
    @Override
    protected void runComputation() {

    }
}
