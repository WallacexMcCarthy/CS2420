package assign07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphUtilityExperiments extends TimingExperiment{
    private static String problemSizeDescription = "Graph Util";
    private static int problemSizeMin = 100;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 100;
    private static int experimentIterationCount = 20;

    protected List<Integer> sources = new ArrayList<>();
    protected List<Integer> destinations = new ArrayList<>();
    protected List<Double> weights = new ArrayList<>();

    protected Integer srcData;
    protected Integer destData;

    protected Random rng = new Random();

    /**
     * Constructor to build the timing experiment.
     */
    public GraphUtilityExperiments() {
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
        for (int i = 0; i < problemSize; i++) {
            sources.add(i);
            sources.add(rng.nextInt(problemSize));
            destinations.add(i);
            destinations.add(rng.nextInt(problemSize));
        }
        srcData = rng.nextInt(problemSize);
        destData = rng.nextInt(problemSize);
    }

    /**
     * Abstract method to run the computation to be timed.
     */
    @Override
    protected void runComputation() {
        try {
            GraphUtility.shortestPath(sources, destinations, srcData, destData);
        } catch (IllegalArgumentException e) {

        }
    }

    public static void main(String[] args) {
        TimingExperiment experiment = new GraphUtilityExperiments();
        experiment.printResults();
    }
}
