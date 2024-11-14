package assign09;

import java.util.ArrayList;
import java.util.Random;

public class HashTableExperiment extends TimingExperiment {
    private static String problemSizeDescription = "Array Randomly Sorted";
    private static int problemSizeMin = 1000;
    private static int problemSizeCount = 21;
    private static int problemSizeStep = 1000;
    private static int experimentIterationCount = 1;
    private HashTable<StudentGoodHash, String> table = new HashTable<>();
    private ArrayList<StudentBadHash> bad = new ArrayList<>();
    private ArrayList<StudentMediumHash> medium = new ArrayList<>();
    private ArrayList<StudentGoodHash> good = new ArrayList<>();
    public ArrayList<Integer> collisions = new ArrayList<>();


    /**
     * Constructor to build a general timing experiment.
     *
     */
    public HashTableExperiment() {
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
        if(table.getCollisions() != 0){
            collisions.add(table.getCollisions());
        }
        table = new HashTable<>();
        bad = buildStudentBadHash(problemSize);
        medium = buildStudentMediumHash(problemSize);
        good = buildStudentGoodHash(problemSize);
    }

    /**
     * Abstract method to run the computation to be timed.
     */
    @Override
    protected void runComputation() {
//        for (int i = 0; i < bad.size(); i++) {
//            table.put(bad.get(i), bad.get(i).getFirstName());
//        }

//        for (int i = 0; i < medium.size(); i++) {
//            table.put(medium.get(i), medium.get(i).getFirstName());
//        }

        for (int i = 0; i < good.size(); i++) {
            table.put(good.get(i), good.get(i).getFirstName());
        }
    }

    public static void main(String[] args) {
        HashTableExperiment experiment = new HashTableExperiment();
        experiment.printResults();
        for (int item : experiment.collisions){
            System.out.println(item);
        }
    }

    private ArrayList<StudentBadHash> buildStudentBadHash(int problemSize){
        ArrayList<StudentBadHash> array = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < problemSize; i++) {
            String str = "";
            for (int j = 0; j < random.nextInt(9); j++) {
                str = str + "l";
            }
            String last = "";
            for (int j = 0; j < random.nextInt(19); j++) {
                last = last + "l";
            }
            array.add(new StudentBadHash(random.nextInt(0, 1000000), str, last));
        }
        return array;
    }
    private ArrayList<StudentMediumHash> buildStudentMediumHash(int problemSize){
        ArrayList<StudentMediumHash> array = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < problemSize; i++) {
            String str = "";
            for (int j = 0; j < random.nextInt(9); j++) {
                str = str + "l";
            }
            String last = "";
            for (int j = 0; j < random.nextInt(19); j++) {
                last = last + "l";
            }
            array.add(new StudentMediumHash(random.nextInt(0, 1000000), str, last));
        }
        return array;
    }

    private ArrayList<StudentGoodHash> buildStudentGoodHash(int problemSize){
        ArrayList<StudentGoodHash> array = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < problemSize; i++) {
            String str = "";
            for (int j = 0; j < random.nextInt(9); j++) {
                str = str + "l";
            }
            String last = "";
            for (int j = 0; j < random.nextInt(19); j++) {
                last = last + "l";
            }
            array.add(new StudentGoodHash(random.nextInt(0,1000000), str, last));
        }
        return array;
    }
}
