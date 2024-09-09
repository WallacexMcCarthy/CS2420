package assign03;

import java.util.Random;

/**
 * Timing experiment for finding the median element of a sorted array list.
 *
 * @author CS 2420 Course Staff
 * @version 2024-09-05
 */
public class SortedArrayListMedianTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "listSize";
    private static int problemSizeMin = 100000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 20000;
    private static int experimentIterationCount = 50;

    private SortedArrayList<Integer> sortedArrayList;
    private Random rng = new Random();

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new SortedArrayListMedianTimingExperiment();
        timingExperiment.printResults();
    }

    public SortedArrayListMedianTimingExperiment() {
        super(
            problemSizeDescription,
            problemSizeMin,
            problemSizeCount,
            problemSizeStep,
            experimentIterationCount
        );
    }

    /**
     * Set up a sorted array list of the given size.
     * @param problemSize - the size of the list
     *
     * @implNote By constructing a semi-random SortedArrayList, the compiler won't be able
     * to optimize this code which will make the timing experiment more reliable. About 75%
     * of the time, the element to be inserted will be incremented by 5; about 25% of the
     * time, the element will not change and a repeated element will be inserted.
     *
     * @implNote Since the setup is not being timed, the elements may be inserted in order
     * from smallest to largest in order to speed up the insertion process.
     */
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
    }

    /**
     * Run the median method on the sorted array list constructed above.
     */
    @Override
    protected void runComputation() {
        sortedArrayList.median();
    }
}
