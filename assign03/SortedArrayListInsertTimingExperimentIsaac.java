package assign03;

import java.util.Random;

public class SortedArrayListInsertTimingExperimentIsaac extends TimingExperiment{
    private static String problemSizeDescription = "listSize";
    private static int problemSizeMin = 1000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 1000;
    private static int experimentIterationCount = 1000;

    private SortedArrayList<Integer> sortedArrayList;
    private Random rng = new Random();
    private int insertee;

    public SortedArrayListInsertTimingExperimentIsaac() {
        super(
            problemSizeDescription,
            problemSizeMin,
            problemSizeCount,
            problemSizeStep,
            experimentIterationCount);
    }

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new SortedArrayListInsertTimingExperimentIsaac();
        timingExperiment.printResults();
    }

    @Override
    protected void setupExperiment(int problemSize) {
        sortedArrayList = new SortedArrayList<Integer>();
        int element = 100;
        for (int i = 0; i < problemSize; i++) {
            if (rng.nextInt(4) < 3) {  // True about 75% of the time
                element += rng.nextInt(4);
            }
            sortedArrayList.insert(element);
        }
        insertee = rng.nextInt(sortedArrayList.max());
    }

    @Override
    protected void runComputation() {
        sortedArrayList.insert(insertee);
    }
}
