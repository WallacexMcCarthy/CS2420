package assign02;

import java.util.Random;

/**
 * Experiment to measure the running time for the lookup method in libraries of
 * various sizes.
 *
 * @author CS 2420 course staff and ??
 * @version August 29, 2024
 */
public class LibraryLookupTimingExperiment extends TimingExperiment {
	private static String problemSizeDescription = "This timing experiment will be used with libraries of sizes 10,000 to 100,000 by steps of 10,000. \n";
	private static int problemSizeMin = 10000;  //
	private static int problemSizeCount = 10;  //
	private static int problemSizeStep = 10000;  //
	private static int experimentIterationCount = 50;

	private Library randomLibrary;
	private long randomIsbn;
	private Random rng = new Random();

	public static void main(String[] args) {
		TimingExperiment timingExperiment = new LibraryLookupTimingExperiment();

		System.out.println("\n---Computing timing results---\n");
		timingExperiment.printResults();
	}

	public LibraryLookupTimingExperiment() {
		super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
	}

	/**
	 * Populates the library with random library books.
	 * 
	 * @param problemSize - the size of the library
	 */
	@Override
	protected void setupExperiment(int problemSize) {
		randomLibrary = new Library();
		for(int i = 0; i < problemSize; i++)
			randomLibrary.add(generateIsbn(), "Surname", "Other name", "Title");
		randomIsbn = generateIsbn();
	}

	/**
	 * Runs the lookup method for the library.
	 */
	@Override
	protected void runComputation() {
		randomLibrary.lookup(randomIsbn);
	}

	/**
	 * Generates a random ISBN (a long with 13 digits).
	 *
	 * @return randomly-generated ISBN
	 */
	private long generateIsbn() {
		String isbn = "";
		for(int j = 0; j < 13; j++)
			isbn += rng.nextInt(10);
		return Long.parseLong(isbn);
	}
}