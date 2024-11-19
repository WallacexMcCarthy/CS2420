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
    protected ArrayList<Integer> array;
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
        heap = new BinaryMinHeap<>();
        array = new ArrayList<>();
        rng = new Random();
        for (int i = problemSize; i > 0; i--) { // worst case add
            array.add(i);
        }
//        for (int i = 0; i < problemSize; i++) { // all others
//            array.add(rng.nextInt());
//        }

//        heap = new BinaryMinHeap<>(array); // extract and peek

    }

    /**
     * Abstract method to run the computation to be timed.
     */
    @Override
    protected void runComputation() {
        for (int i = 0; i < array.size(); i++) { // both adds
            heap.add(array.get(i));
        }
//        for (int i = 0; i < array.size(); i++) { // extract
//            heap.extract();
//        }
//        for (int i = 0; i < array.size(); i++) { // peek compiler kept optimizing only peek calls so extracting a little stops it
//            if (i % 1000 == 0) {
//                heap.extract();
//            }
//            heap.peek();
//        }

    }

    public static void main(String[] args) {
        TimingExperiment experiment = new SimpleMethodsTiming();
        experiment.printResults();
    }
}
