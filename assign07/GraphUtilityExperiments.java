package assign07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GraphUtilityExperiments extends TimingExperiment{
    private static String problemSizeDescription = "Graph Util";
    private static int problemSizeMin = 1000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 1000;
    private static int experimentIterationCount = 10;

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
            destinations.add(i + 1);
            weights.add(rng.nextDouble());
        }
//        Collections.shuffle(sources); // for cyclic
//        Collections.shuffle(destinations);
//        Collections.sort(sources); // for acyclic
//        Collections.sort(destinations);
        srcData = 0;
        destData = problemSize;
    }

    /**
     * Abstract method to run the computation to be timed.
     */
    @Override
    protected void runComputation() {
        try {
            GraphUtility.shortestPath(sources, destinations, srcData, destData);
//            GraphUtility.shortestWeightedPath(sources, destinations, weights, srcData, destData);
//            GraphUtility.sort(sources, destinations);
        } catch (IllegalArgumentException e) {}
    }

    public static void main(String[] args) {
        TimingExperiment experiment = new GraphUtilityExperiments();
        experiment.printResults();
    }
}
