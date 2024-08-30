package lab.lab02;

import java.util.Random;

/**
 * Timing experiment to measure the time it takes
 * to find the maximum integer in arrays of various sizes.
 *
 * @author CS 2420 Course Staff
 * @version 2024-08-30
 */
public class ArrayMaximumTimingExperimentV2 extends TimingExperiment {
    private static String problemSizeDescription = "arraySize";
    private static int problemSizeMin = 200000;
    private static int problemSizeCount = 30;
    private static int problemSizeStep = 10000;
    private static int experimentIterationCount = 50;

    private int[] array;
    private Random rng = new Random();

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new ArrayMaximumTimingExperimentV2();
        timingExperiment.printResults();
    }

    public ArrayMaximumTimingExperimentV2() {
        super(
            problemSizeDescription,
            problemSizeMin,
            problemSizeCount,
            problemSizeStep,
            experimentIterationCount
        );
    }

    /**
     * Populate the array with random integers.
     * @param problemSize - the size of the array
     */
    @Override
    protected void setupExperiment(int problemSize) {
        array = new int[problemSize];
        for (int i = 0; i < problemSize; i++) {
            array[i] = rng.nextInt();
        }
    }

    /**
     * Run the computeMaximum function on the array.
     */
    @Override
    protected void runComputation() {
        ArrayUtility.computeMaximum(array);
    }
}
