package assign01;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

/**
 * This tester class assesses the correctness of the MathVector class.
 *
 * IMPORTANT NOTE: The tests provided to get you started rely heavily on a 
 *                 correctly implemented equals method.  Be careful of false
 *                 positives (i.e., tests that pass because your equals method
 *                 incorrectly returns true). 
 * 
 * @author CS 2420 course staff and Wallace McCarthy (u0838487)
 * @version August 22, 2024
 */
public class MathVectorTest {
	
	private MathVector rowVec, unitVec, colVec, smallColVec, zeroRowVector, zeroColVector, maxRowVector, maxColVector;

	@BeforeEach
	public void setUp() throws Exception {
		// Creates a row vector with three elements: 3.0, 1.0, 2.0
		rowVec = new MathVector(new double[][]{{3, 1, 2}});
		
		// Creates a row vector with three elements: 1.0, 1.0, 1.0
		unitVec = new MathVector(new double[][]{{1, 1, 1}});
		
		// Creates a column vector with five elements: -11.0, 2.5, 36.0, -3.4, 7.1
		colVec = new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.4}, {7.1}});

		smallColVec = new MathVector(new double[][]{{-11}, {2.5}});

		zeroRowVector = new MathVector(new double[][]{{0, 0, 0}});
		zeroColVector = new MathVector(new double[][]{{0}, {0}, {0}});

		maxRowVector = new MathVector(new double[][]{{Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE}});

		maxColVector = new MathVector(new double[][]{{Double.MAX_VALUE}, {Double.MAX_VALUE}, {Double.MAX_VALUE}});
	}
	// Testing the constructor
	@Test
	public void createVectorFromMatrix() {
	  double arr[][] = {{1, 2}, {3, 4}};
	  assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}
	
	@Test
	public void createVectorFromJaggedArray() {
	  double arr[][] = {{1}, {2}, {3}, {4, 5}, {6}, {7}};
	  assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
	}

	@Test
	public void createVectorFromEmptyArray(){
		double arr[][] = {};
		assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
	}

	// Tests the toString Method
	@Test
	public void smallColVectorToString() {
		String expected = "-11.0\n2.5\n36.0\n-3.4\n7.1";
		assertEquals(expected, colVec.toString());		
	}
	@Test
	public void smallRowVectorToString() {
		String expected = "3.0 1.0 2.0";
		assertEquals(expected, rowVec.toString());
	}

	// Tests Equality method

	@Test
	public void rowVectorOtherTypeInequality(){
		assertFalse(rowVec.equals(new Point(1,2)));
	}
	@Test
	public void columnRowEquality(){
		assertFalse(rowVec.equals(colVec));
	}
	@Test
	public void differingSizeEquality(){

		assertFalse(colVec.equals(smallColVec));
	}
	@Test
	public void smallRowVectorEquality() {
		assertTrue(rowVec.equals(new MathVector(new double[][]{{3, 1, 2}})));
	}
	
	@Test
	public void smallRowVectorInequality() {
		assertFalse(rowVec.equals(unitVec));
	}

	@Test
	public void maxVectorEquality(){
		assertFalse(maxColVector.equals(maxRowVector));
	}

	@Test
	public void maxColVectorEqualitySameVector(){
		assertTrue(maxColVector.equals(maxColVector));
	}

	// Test the scale method
	@Test
	public void scaleSmallColVector() {
		MathVector expected = new MathVector(new double[][]{{-27.5}, {6.25}, {90}, {-8.5}, {17.75}});
		colVec.scale(2.5);
		assertTrue(expected.equals(colVec));
	}
	@Test
	public void scaleSmallColVectorBy0() {
		MathVector expected = new MathVector(new double[][]{{0}, {0}, {0}, {0}, {0}});
		colVec.scale(0);
		assertTrue(expected.equals(colVec));
	}

	@Test
	public void scaleMaxColVector(){
		MathVector expected = new MathVector(new double[][]{{Double.MAX_VALUE / 2}, {Double.MAX_VALUE / 2}, {Double.MAX_VALUE / 2}});
		maxColVector.scale(0.5);
		assertTrue(expected.equals(maxColVector));
	}
	@Test
	public void scaleMaxRowVector(){
		MathVector expected = new MathVector(new double[][]{{Double.MAX_VALUE / 2, Double.MAX_VALUE / 2, Double.MAX_VALUE / 2}});
		maxRowVector.scale(0.5);
		assertTrue(expected.equals(maxRowVector));
	}


	// Test transpose method
	@Test
	public void transposeSmallRowVector() {
		MathVector expected = new MathVector(new double[][]{{3}, {1}, {2}});
		assertTrue(expected.equals(rowVec.transpose()));
	}

	@Test
	public void transposeSmallRowColumn() {
		MathVector expected = new MathVector(new double[][]{{-11, 2.5, 36, -3.4, 7.1}});

		assertTrue(expected.equals(colVec.transpose()));
	}
	@Test
	public void transposeMaxRowVector(){
		assertTrue(maxRowVector.equals(maxColVector.transpose()));
	}

	@Test
	public void transposeMaxColVector(){
		assertTrue(maxColVector.equals(maxRowVector.transpose()));
	}
	// Test add Method
	@Test
	public void addRowAndColVectors() {
	  assertThrows(IllegalArgumentException.class, () -> { rowVec.add(colVec); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}
	@Test
	public void addTowsOfDifferentLength(){
		MathVector row2 = new MathVector(new double[][]{{-11, 2.5, 36, -3.4, 7.1}});
		assertThrows(IllegalArgumentException.class, () -> { rowVec.add(row2); });
	}

	
	@Test
	public void addSmallRowVectors() {
		MathVector expected = new MathVector(new double[][]{{4, 2, 3}});
		assertTrue(expected.equals(rowVec.add(unitVec)));		
	}

	// Test Dot Product method

	@Test
	public void dotProductBadRowVectors() {
		MathVector row2 = new MathVector(new double[][]{{-11, 2.5, 36, -3.4, 7.1}});
		assertThrows(IllegalArgumentException.class, () -> { rowVec.dotProduct(row2); });
	}
	@Test
	public void dotProductDifferingTypeVectors() {
		assertThrows(IllegalArgumentException.class, () -> { rowVec.dotProduct(colVec); });
	}
	@Test
	public void dotProductSmallRowVectors() {
		assertEquals(3.0 * 1.0 + 1.0 * 1.0 + 2.0 * 1.0, rowVec.dotProduct(unitVec));		
	}
	// Test Magnitude method
	
	@Test
	public void smallRowVectorMagnitude() {
		assertEquals(Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0), rowVec.magnitude());		
	}
	@Test
	public void zeroRowVectorMagnitude() {
		MathVector zeroRow = new MathVector(new double[][]{{0, 0, 1, 0, 0}});

		assertEquals(1, zeroRow.magnitude());
	}
	@Test
	public void negetiveRowVectorMagnitude() {
		MathVector zeroRow = new MathVector(new double[][]{{-3, 0, -1, 1, 0}});

		assertEquals(Math.sqrt(11), zeroRow.magnitude());
	}

	@Test
	public void maxColVectorMagnitude(){
		assertEquals(Double.POSITIVE_INFINITY, maxColVector.magnitude());
	}
	@Test
	public void maxRowVectorMagnitude(){
		assertEquals(Double.POSITIVE_INFINITY, maxRowVector.magnitude());
	}
	// Test Normalize Method
	@Test
	public void smallRowVectorNormalize() {
		double length = Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0);
		MathVector expected = new MathVector(new double[][]{{3.0 / length, 1.0 / length, 2.0 / length}});
		rowVec.normalize();
		assertTrue(expected.equals(rowVec));		
	}
	@Test
	public void smallColVectorNormalize() {
		double length = Math.sqrt(-11.0 * -11.0 + 2.5 * 2.5);
		MathVector expected = new MathVector(new double[][]{{-11 / length}, {2.5 / length}});
		smallColVec.normalize();
		assertTrue(expected.equals(smallColVec));
	}

	// A vector of all zeros cannot be normalized and such doing this operation will result in a vector of all zeros.
	@Test
	public void zeroRowVectorNormalize(){
		double length = 0;
		MathVector expected = new MathVector(new double[][]{{0,0, 0}});
		zeroRowVector.normalize();
		assertTrue(expected.equals(zeroRowVector));
	}
	@Test
	public void zeroColVectorNormalize(){
		double length = 0;
		MathVector expected = new MathVector(new double[][]{{0}, {0}, {0}});
		zeroColVector.normalize();
		assertTrue(expected.equals(zeroColVector));
	}

	@Test
	public void maxRowVectorNormalize(){
		// The Length will be INFINITY and anything divided by infinity is Zero.
		double length = Math.sqrt(Double.MAX_VALUE * Double.MAX_VALUE + Double.MAX_VALUE * Double.MAX_VALUE + Double.MAX_VALUE * Double.MAX_VALUE);
		MathVector expected = new MathVector(new double[][]{{Double.MAX_VALUE / length,Double.MAX_VALUE / length, Double.MAX_VALUE / length}});
		MathVector expectedTrue = new MathVector(new double[][]{{0, 0, 0}});
		assertTrue(expected.equals(expectedTrue));
		maxRowVector.normalize();
		assertTrue(expectedTrue.equals(maxRowVector));
	}

	@Test
	public void maxColVectorNormalize(){
		// The Length will be INFINITY and anything divided by infinity is Zero.
		double length = Math.sqrt(Double.MAX_VALUE * Double.MAX_VALUE + Double.MAX_VALUE * Double.MAX_VALUE + Double.MAX_VALUE * Double.MAX_VALUE);
		MathVector expected = new MathVector(new double[][]{{Double.MAX_VALUE / length},{Double.MAX_VALUE / length}, {Double.MAX_VALUE / length}});
		MathVector expectedTrue = new MathVector(new double[][]{{0}, {0}, {0}});
		assertTrue(expected.equals(expectedTrue));
		maxColVector.normalize();
		assertTrue(expectedTrue.equals(maxColVector));
	}
}