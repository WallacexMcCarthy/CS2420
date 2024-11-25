package assign10;

import java.util.ArrayList;

public class ShortestPathTiming extends TimingExperiment{
    private static String problemSizeDescription = "Path";
    private static int problemSizeMin = 1000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 1000;
    private static int experimentIterationCount = 20;
    protected ArrayList<Integer> sources;
    protected ArrayList<Integer> destinations;
    protected ArrayList<Double> weights;
    protected int srcData;
    protected int endData;

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
        sources = new ArrayList<>();
        destinations = new ArrayList<>();
        weights = new ArrayList<>();
        for (int i = 0; i < problemSize; i++) {
            sources.add(i);
            destinations.add(i+1);
            weights.add((double)i);
        }
        srcData = 0;
        endData = problemSize;
    }

    /**
     * Abstract method to run the computation to be timed.
     */
    @Override
    protected void runComputation() {
        try {
            GraphUtility.shortestWeightedPathWithPriorityQueue(sources, destinations, weights, srcData, endData);
        } catch (IllegalArgumentException e) {}
    }

    public static void main(String[] args) {
        TimingExperiment experiment = new ShortestPathTiming();
        experiment.printResults();
    }
}
