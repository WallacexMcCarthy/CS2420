package comprehensive;

import java.io.FileWriter;
import java.util.Random;

public class GlossaryExperiment extends TimingExperiment {
    private static String problemSizeDescription = "Simple Methods";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 10000;
    private static int experimentIterationCount = 1;

    protected Glossary empty;
    protected Random rng;

    public GlossaryExperiment(){
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
        rng = new Random();
        try {
            FileWriter writer = new FileWriter("/Users/isaacdbeans/desktop/school/github/cs2420/comprehensive/timing.txt");
            StringBuilder data = new StringBuilder();
            for (int i = 0; i < problemSize; i++) {
                if (i!=0) {
                    data.append("\n").append(i).append("::noun::").append(i);
                } else {
                    data.append(i).append("::noun::").append(i);
                }
            }
            writer.write(String.valueOf(data));
            writer.close();
        } catch (Exception e) {
            System.out.println("bad");
        }
        // comment this line out for constructor test
        empty = new Glossary("/Users/isaacdbeans/desktop/school/github/cs2420/comprehensive/timing.txt");
    }

    /**
     * Abstract method to run the computation to be timed.
     */
    @Override
    protected void runComputation() {
//        empty.getFirstWord();
//        empty.getLastWord();
//        empty.updateWordDefinition("" + rng.nextInt(0, 10000), 1, "poo");
//        empty.deleteWordDefinition("" + rng.nextInt(0, 10000), 1);
//        empty.addDefinitionToWord("" + rng.nextInt(0, 10000), "noun", "poo");
        empty.getWordsInRange("0", "" + rng.nextInt(0, 200000));
//        empty.getWord("" + rng.nextInt(0, 20000));
//        empty = new Glossary("/Users/isaacdbeans/desktop/school/github/cs2420/comprehensive/timing.txt");
    }

    public static void main(String[] args) {
        TimingExperiment experiment = new GlossaryExperiment();
        experiment.printResults();
    }
}