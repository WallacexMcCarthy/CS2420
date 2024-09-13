package assign01;
/**
 * This class represents a simple row or column vector of numbers. In a row
 * vector, the numbers are written horizontally (i.e., along the columns). In a
 * column vector, the numbers are written vertically (i.e., along the rows).
 * 
 * @author CS 2420 course staff and Wallace McCarthy (u0838487)
 * @version August 22, 2024
 */
public class MathVector {

	// 2D array to hold the numbers of the vector, either along the columns or rows
	private double[][] data;
	// set to true for a row vector and false for a column vector
	private boolean isRowVector;
	// count of elements in the vector
	private int vectorSize;

	/**
	 * Creates a new row or column vector. For a row vector, the input array is
	 * expected to have 1 row and a positive number of columns, and this number of
	 * columns represents the vector's length. For a column vector, the input array
	 * is expected to have 1 column and a positive number of rows, and this number
	 * of rows represents the vector's length.
	 *
	 * @param data - a 2D array to hold the numbers of the vector
	 * @throws IllegalArgumentException if the numbers of rows and columns in the
	 *                                  input 2D array is not compatible with a row
	 *                                  or column vector
	 */
	public MathVector(double[][] data) {
		if (data.length == 0)
			throw new IllegalArgumentException("Number of rows must be positive.");
		if (data[0].length == 0)
			throw new IllegalArgumentException("Number of columns must be positive.");

		if (data.length == 1) {
			// This is a row vector with length = number of columns.
			this.isRowVector = true;
			this.vectorSize = data[0].length;
		} else if (data[0].length == 1) {
			for (int i = 1; i < data.length; i++)
				if (data[i].length != 1)
					throw new IllegalArgumentException("For each row, the number of columns must be 1.");
			// This is a column vector with length = number of rows.
			this.isRowVector = false;
			this.vectorSize = data.length;
		} else
			throw new IllegalArgumentException("Either the number of rows or the number of columns must be 1.");

		// Create the array and copy data over.
		if (this.isRowVector)
			this.data = new double[1][vectorSize];
		else
			this.data = new double[vectorSize][1];
		for (int i = 0; i < this.data.length; i++) {
			for (int j = 0; j < this.data[0].length; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}

	/**
	 * Generates a textual representation of this vector.
	 * <p>
	 * For example, "1.0 2.0 3.0 4.0 5.0" for a sample row vector of length 5 and
	 * "1.0\n2.0\n3.0\n4.0\n5.0" for a sample column vector of length 5. In both
	 * cases, notice the lack of a newline or space after the last number.
	 *
	 * @return textual representation of this vector
	 */
	public String toString() {
		String out = "";
		// Checks if it is a row vector or a column vector
		if (isRowVector) {
			//iterates through the vector
			for (int i = 0; i < this.data[0].length; i++) {
				// Concatenates the strings with a space
				if (i == 0) {
					out = out + this.data[0][i];
				} else {
					out = out + " " + this.data[0][i];
				}
			}
		} else {
			//iterates through the vector
			for (int i = 0; i < this.data.length; i++) {
				// Concatenates the strings with a new line
				if (i == 0) {
					out = out + this.data[i][0];
				} else {
					out = out + "\n" + this.data[i][0];
				}
			}
		}
		return out;
	}

	/**
	 * Determines whether this vector is "equal to" another vector, where equality
	 * is defined as both vectors being row (or both being column), having the same
	 * vector length, and containing the same numbers in the same positions.
	 *
	 * @param other - another vector to compare
	 * @return true if this vector is equivalent to other, false otherwise
	 */
	public boolean equals(Object other) {
		// Ensures this object is indeed a MathVector
		if (!(other instanceof MathVector))
			return false;

		MathVector otherVec = (MathVector) other;

		// Checks if it is a Row vector or a column vector
		if (this.isRowVector) {
			// Checks to ensure the sizing is also correct and that the other vector is also a row vector
			if (otherVec.isRowVector) {
				if (otherVec.vectorSize == this.vectorSize) {
					for (int i = 0; i < this.vectorSize; i++) {
						// At any point if an element is different, it will return false.
						if (this.data[0][i] != otherVec.data[0][i]) {
							return false;
						}
					}
					// if each element is the same, then it will return true;
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			// Checks to ensure the sizing is also correct and that the other vector is also a column vector
			if (!otherVec.isRowVector) {
				if (otherVec.vectorSize == this.vectorSize) {
					for (int i = 0; i < this.vectorSize; i++) {
						// At any point if an element is different, it will return false.
						if (this.data[i][0] != otherVec.data[i][0]) {
							return false;
						}
					}
					// if each element is the same, then it will return true;
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	/**
	 * Updates this vector by using a given scaling factor to multiply each entry.
	 *
	 * @param factor - the scaling factor
	 */
	public void scale(double factor) {
		// Identifies if it is a row or column vector
		if (isRowVector) {
			// Iterates through the vector and applies the scale.
			for (int i = 0; i < vectorSize; i++) {
				data[0][i] = data[0][i] * factor;
			}
		} else {
			// Iterates through the vector and applies the scale.
			for (int i = 0; i < vectorSize; i++) {
				data[i][0] = data[i][0] * factor;
			}
		}
	}

	/** badingii
	 * Generates a new vector that is the transposed version of this vector.
	 *
	 * @return transposed version of this vector
	 */
	public MathVector transpose() {
		double[][] transposed;
		// Identifies if it is a row or column vector
		if (isRowVector) {
			// makes a new double 2d array and then copies the data into a different format - column.
			transposed = new double[vectorSize][1];
			for (int i = 0; i < vectorSize; i++) {
				transposed[i][0] = this.data[0][i];
			}
		} else {
			// makes a new double 2d array and then copies the data into a different format - row.
			transposed = new double[1][vectorSize];
			for (int i = 0; i < vectorSize; i++) {
				transposed[0][i] = this.data[i][0];
			}
		}
		return new MathVector(transposed);
	}

	/**
	 * Generates a new vector representing the sum of this vector and another
	 * vector.
	 *
	 * @param other - another vector to be added to this vector
	 * @return sum of this vector and other
	 * @throws IllegalArgumentException if the other vector and this vector are not
	 *                                  both row vectors of the same length or
	 *                                  column vectors of the same length
	 */
	public MathVector add(MathVector other) {
		double[][] sum;
		// Throws Exceptions if they are incompatible types, AKA same types and sizes.
		if (isRowVector) {
			if (!other.isRowVector) {
				throw new IllegalArgumentException("Both vectors bust be of the same type!");
			} else {
				if (vectorSize != other.vectorSize) {
					throw new IllegalArgumentException("Both vectors bust be of the same length!");
				} else {
					// Adds the two vectors and stores the data in a double 2d array
					sum = new double[1][vectorSize];
					for (int i = 0; i < vectorSize; i++) {
						sum[0][i] = this.data[0][i] + other.data[0][i];
					}
				}
			}
		} else {
			if (other.isRowVector) {
				throw new IllegalArgumentException("Both vectors bust be of the same type!");
			} else {
				if (vectorSize != other.vectorSize) {
					throw new IllegalArgumentException("Both vectors bust be of the same length!");
				} else {
					// Adds the two vectors and stores the data in a double 2d array
					sum = new double[vectorSize][1];
					for (int i = 0; i < vectorSize; i++) {
						sum[i][0] = this.data[i][0] + other.data[i][0];
					}
				}
			}
		}
		// Returns the newly created MathVector as the other created double 2d array.
		return new MathVector(sum);
	}

	/**
	 * Computes the dot product of this vector and another vector.
	 *
	 * @param other - another vector to be combined with this vector to produce the
	 *              dot product
	 * @return dot product of this vector and other
	 * @throws IllegalArgumentException if the other vector and this vector are not
	 *                                  both row vectors of the same length or
	 *                                  column vectors of the same length
	 */
	public double dotProduct(MathVector other) {
		double dotProduct = 0.0;
		// Identifies if this vector and the other vector are of the same type nad size.
		if (isRowVector) {
			if (!other.isRowVector) {
				throw new IllegalArgumentException("Both vectors bust be of the same type!");
			} else {
				if (vectorSize != other.vectorSize) {
					throw new IllegalArgumentException("Both vectors bust be of the same length!");
				} else {
					// Multiplies each entry from each vector in the correct way and adds that number to the
					// stored variable.
					for (int i = 0; i < vectorSize; i++) {
						dotProduct += (this.data[0][i] * other.data[0][i]);
					}
				}
			}
		} else {
			if (other.isRowVector) {
				throw new IllegalArgumentException("Both vectors bust be of the same type!");
			} else {
				if (vectorSize != other.vectorSize) {
					throw new IllegalArgumentException("Both vectors bust be of the same length!");
				} else {
					// Multiplies each entry from each vector in the correct way and adds that number to the
					// stored variable.
					for (int i = 0; i < vectorSize; i++) {
						dotProduct += (this.data[i][0] * other.data[i][0]);
					}
				}
			}
		}
		return dotProduct;
	}

	/**
	 * Computes this vector's magnitude (also known as a vector's length).
	 *
	 * @return magnitude of this vector
	 */
	public double magnitude() {
		double product = 0.0;
		// Identifies which type this vector is
		if (isRowVector) {
			// Adds the square of each entry together.
			for (int i = 0; i < vectorSize; i++) {
				product += (this.data[0][i] * this.data[0][i]);
			}
		} else {
			// Adds the square of each entry together.
			for (int i = 0; i < vectorSize; i++) {
				product += (this.data[i][0] * this.data[i][0]);
			}
		}
		// Square roots the combined sum.
		return Math.sqrt(product);
	}

	/**
	 * Updates this vector by using standardizing it (AKA normalization).
	 */
	public void normalize() {
		double scale = this.magnitude();
		// Identifies if this vector is a row or column vector and then it divides each entry by some scale
		// in order to normalize this vector.
		// A Vector with length 0 must have all 0s in its entries and thus cannot be normalized.
		if(scale == 0){
			return;
		}
		if (isRowVector) {
			for (int i = 0; i < vectorSize; i++) {
				this.data[0][i] = this.data[0][i] / scale;
			}
		} else {
			for (int i = 0; i < vectorSize; i++) {
				this.data[i][0] = this.data[i][0] / scale;
			}
		}
	}
}