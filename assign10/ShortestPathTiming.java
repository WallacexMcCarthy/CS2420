package assign10;

public class ShortestPathTiming extends TimingExperiment{
    private static String problemSizeDescription = "Path";
    private static int problemSizeMin = 1000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 1000;
    private static int experimentIterationCount = 20;

    public ShortestPathTiming() {
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
