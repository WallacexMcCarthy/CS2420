package assign10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SimpleMethodsTiming extends TimingExperiment{
    private static String problemSizeDescription = "Simple Methods";
    private static int problemSizeMin = 1000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 1000;
    private static int experimentIterationCount = 20;
    protected BinaryMinHeap<Integer> heap;
    private Random rng;

    public SimpleMethodsTiming() {
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

    public static void main(String[] args) {
        TimingExperiment experiment = new SimpleMethodsTiming();
        experiment.printResults();
    }
}
