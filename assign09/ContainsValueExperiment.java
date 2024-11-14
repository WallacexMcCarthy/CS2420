package assign09;

import java.util.Random;
import java.util.TreeMap;

public class ContainsValueExperiment extends TimingExperiment {
    private static String problemSizeDescription = "Contains Value Experiment";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 10000;
    private static int experimentIterationCount = 20;

    protected Map<String, Integer> hashMap;
    protected TreeMap<String, Integer> treeMap;
    protected Random rng;

    ContainsValueExperiment() {
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
        hashMap = new HashTable<>();
        treeMap = new TreeMap<>();
        rng = new Random();

        for (int i = 0; i < problemSize; i++) {
            hashMap.put("" + i, rng.nextInt());
            treeMap.put("" + i, rng.nextInt());
        }
    }

    /**
     * Abstract method to run the computation to be timed.
     */
    @Override
    protected void runComputation() {
        hashMap.containsValue(rng.nextInt());
//        treeMap.containsValue(rng.nextInt());
    }

    public static void main(String[] args) {
        TimingExperiment experiment = new ContainsValueExperiment();
        experiment.printResults();
    }
}
