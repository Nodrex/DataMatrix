package com.nodrex.datamatrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * <p>
 * This class contains useful methods to manipulate on data of mathematic matrix
 * type, such as plus , minus , multiply , divide and others ...
 * </p>
 * <p>
 * Implements Serializable , Comparable , Cloneable interfaces.
 * </p>
 * <p>
 * Can be extended by any class.
 * </p>
 * 
 * @author NODREX
 * @version 1.0
 * @since 2013
 */
public class Matrix extends DataMatrix<Double> {

	private static final long serialVersionUID = -1829129062581791051L;

	protected interface MatrixExceptionMessages {
		String DIAGONAL = "Invalid diagonalnumber. diagonalnumber should not be more or equal than 2 * this matrix's min dimension and diagonalnumber should not be even!";
		String CALCULATION = "Invalid dimensions. dimensions must be same!";
		String DIMENSION = "Invalid dimensions. this matrix's column dimension should be equal to second matrix's line dimension!";
		String SQUARE = "Invalid matrix type. matrix must be square!";
		String RANDOM = "start should be less than end!";
		String READ_DATA_FROM_FILE = "File is empty!";
	}

	/**
	 * Constructor: Creates Matrix with one data
	 */
	public Matrix() {
		super();
	}

	/**
	 * Creates Matrix with one data and fills with default value that you gave
	 */
	public Matrix(double defaultValue) {
		super((Double) defaultValue);
	}

	/**
	 * Creates Matrix with dimensions that you gave
	 * 
	 * @throws IllegalArgumentException
	 *             if given lineDimension or columnDimension are negative or
	 *             zero
	 */
	public Matrix(int lineDimension, int columnDimension)
			throws IllegalArgumentException {
		super(lineDimension, columnDimension);
	}

	/**
	 * Creates Matrix with dimensions that you gave and feels with default value
	 * 
	 * @throws IllegalArgumentException
	 *             if given lineDimension or columnDimension are negative or
	 *             zero
	 */
	public Matrix(int lineDimension, int columnDimension, double DefaultValue)
			throws IllegalArgumentException {
		super(lineDimension, columnDimension, (Double) DefaultValue);
	}

	/**
	 * Creates square matrix with dimension that you gave
	 * 
	 * @throws IllegalArgumentException
	 *             if given squareDimensioni is negative or zero
	 */
	public Matrix(int squareDimension) throws IllegalArgumentException {
		super(squareDimension);
	}

	/**
	 * Creates square matrix with dimension that you gave and fills with default
	 * value
	 * 
	 * @throws IllegalArgumentException
	 *             if given squareDimension is negative or zero
	 */
	public Matrix(int squareDimension, double defaultValue)
			throws IllegalArgumentException {
		super(squareDimension, (Double) defaultValue);
	}

	/**
	 * Creates copy of given matrix
	 * 
	 * @throws NullPointerException
	 *             if given dataMatrix is null
	 */
	public Matrix(Matrix matrix) throws NullPointerException {
		super(matrix);
	}

	/**
	 * Creates matrix and fills with data that you gave.
	 * <p>
	 * This constructor takes dimensions from given data automatically.
	 * </p>
	 * <p>
	 * Example:
	 * </p>
	 * <p>
	 * Matrix matrix = new Matrix(new Double[]{value_2,value_2,value_3},new
	 * Double[]{value_4,value_5,value_6})
	 * </p>
	 * <p>
	 * It will create dataMatrix with dimensions: line dimension 2 and column
	 * dimension 3
	 * </p>
	 * <p>
	 * Example 2:
	 * </p>
	 * <p>
	 * Matrix matrix = new Matrix(new Double[][]{new
	 * Double[]{value_1,value_2,value_3}, new Double[]{value_4,value_5,value_6},
	 * new Double[]{value_7,value_8,value_9}}
	 * </p>
	 * <p>
	 * It will create Matrix with dimensions: line dimension 3 and column
	 * dimension 3
	 * </p>
	 * 
	 * @param data
	 *            can be: (new Double[]{value_2,value_2,value_3}, ...) or (new
	 *            Double[][]{new Double[]{value_1,value_2,value_3}, ...})
	 * @throws NullPointerException
	 *             if given data is null.
	 */
	public Matrix(Double[]... data) throws NullPointerException {
		super(data);
	}

	/**
	 * Creates matrix and fills with data that you gave.
	 * <p>
	 * This constructor takes dimensions from given data automatically.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if given data is null.
	 */
	public Matrix(List<List<Double>> data)
			throws NullPointerException, IllegalArgumentException {
		super(data);
	}

	/**
	 * Creates matrix and fills with default value.
	 * <p>
	 * For example:
	 * </p>
	 * if you sent: "1,2,3" , "4,5" , "6,7,8"</br> Result will be Matrix with
	 * dimensions 3 X 3:</br> 1.0 2.0 3.0<br>
	 * 4.0 5.0 null<br>
	 * 6.0 7.0 8.0
	 * <p>
	 * or you can use: new String[]{"1,2","3"} as a parameter and you will
	 * get:</br> 1.0 2.0</br> 3.0 null</br>
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if defaultValues is null.
	 * @throws NumberFormatException
	 *             if you will sent something else rather then number.
	 */
	public Matrix(String... defaultValues) throws NullPointerException,
			NumberFormatException {
		super(defaultValues.length, getMaxColumnDimension(defaultValues.length,
				defaultValues));
		for (int i = 0; i < this.linedimension; i++) {
			String[] line = defaultValues[i].split(",");
			for (int j = 0; j < line.length; j++) {
				this.setValue(i, j, Double.parseDouble(line[j]));
			}
		}
	}

	/**
	 * Reads data from file and uses given separator to separate data, it means
	 * that all data should be separated only with this separator and nothing
	 * else, otherwise NumberFormatException will be thrown.
	 * <p>
	 * Data in file should be like that for example if you use comma for
	 * separation:</br> 1.5 , 2.3</br> 5.7 , 8.9</br> 3.3 , 5</br>
	 * </p>
	 * There should not be enter at the last.
	 * 
	 * @param fileName
	 *            should be like this: "C:/Users/.../File name.extension".
	 * @param separator
	 *            should be: ',' or ':' or '/' and so on .... <i><b>Important:
	 *            this parameter should not be '.' !</b></i>
	 * @throws IllegalArgumentException
	 *             if file is empty.
	 * @throws NumberFormatException
	 *             if data is incorrect or separated incorrectly in the file.
	 * @throws java.io.FileNotFoundException
	 *             if given file does not exists.
	 */
	public Matrix(String fileName, char separator)
			throws IllegalArgumentException, NumberFormatException,
			java.io.FileNotFoundException {
		super(readFromFile(fileName, String.valueOf(separator)));
	}

	/**
	 * Reads data from file and uses one space to separate data, it means that
	 * all data should be separated only with one space and nothing else,
	 * otherwise NumberFormatException will be thrown.
	 * <p>
	 * Data in file should be like that:</br> 1.5 2.3</br> 5.7 8.9</br> 3.3
	 * 5</br>
	 * </p>
	 * There should not be enter at the last.
	 * 
	 * @param fileName
	 *            should be like this: "C:/Users/.../File name.extension".
	 * @throws IllegalArgumentException
	 *             if file is empty.
	 * @throws NumberFormatException
	 *             if data is incorrect or separated incorrectly in the file.
	 * @throws java.io.FileNotFoundException
	 *             if given file does not exists.
	 */
	public Matrix(String fileName) throws IllegalArgumentException,
			NumberFormatException, java.io.FileNotFoundException {
		this(fileName, ' ');
	}

	private static List<List<Double>> readFromFile(
			String fileName, String separator) throws NumberFormatException,
			IllegalArgumentException, java.io.FileNotFoundException {
		List<String> list = new ArrayList<String>();
		java.io.File file = new java.io.File(fileName);
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			String str = scanner.nextLine();
			list.add(str);
		}
		scanner.close();
		if (list.size() == 0) {
			throw new IllegalArgumentException(
					MatrixExceptionMessages.READ_DATA_FROM_FILE);
		}
		List<List<Double>> dataList = new ArrayList<List<Double>>();
		for (int i = 0; i < list.size(); i++) {
			String[] separated = list.get(i).split(separator);
			List<Double> data = new ArrayList<Double>();
			for (int j = 0; j < separated.length; j++) {
				data.add(Double.parseDouble(separated[j]));
			}
			dataList.add(data);
		}
		return dataList;
	}

	private static int getMaxColumnDimension(int lineDimension,
			String... defaultValues) {
		List<Integer> columnDimensionsList = new ArrayList<>(
				lineDimension);
		for (int i = 0; i < lineDimension; i++) {
			columnDimensionsList.add(defaultValues[i].split(",").length);
		}
		return Collections.max(columnDimensionsList);
	}

	private void checkDimensionsForCalculations(Matrix matrix)
			throws IllegalArgumentException {
		if (this.linedimension != matrix.linedimension
				|| this.columndimension != matrix.columndimension)
			throw new IllegalArgumentException(
					MatrixExceptionMessages.CALCULATION);
	}

	private void checkDimensionsForMultiply(Matrix matrix) {
		if (this.columndimension != matrix.linedimension)
			throw new IllegalArgumentException(
					MatrixExceptionMessages.DIMENSION);
	}

	private Matrix degreeOperation(int degree) {
		Matrix m = new Matrix(this);
		for (int i = 1; i < degree; i++) {
			m = m.multiply(this);
		}
		return m;
	}

	/**
	 * @return true if all values are negative.
	 */
	public boolean isAllValuesNegative() {
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				if (!(this.getValue(i, j) < 0)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @return true if all values are positive
	 */
	public boolean isAllValuesPositive() {
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				if (!(this.getValue(i, j) > 0)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @return true if this matrix is zero.
	 */
	public boolean isZero() {
		return isEveryElementEqualTo(new Double(0));
	}

	/**
	 * @return true if this matrix is eye.
	 */
	public boolean isEye() {
		return this
				.equals(Matrix.eye(this.linedimension, this.columndimension));
	}

	/**
	 * @return true if all values in this matrix are 1.
	 */
	public boolean isOnes() {
		return isEveryElementEqualTo(new Double(1));
	}

	/**
	 * @return eye matrix with existed dimensions and other data which was in
	 *         this matrix will be lost.
	 */
	public Matrix eyeRebuild() {
		this.fillWith0();
		this.setMainDiagonal(new Double(1));
		return this;
	}

	/**
	 * @return true if matrix=matrix.transpose()
	 */
	public boolean isSymmetrical() {
		return this.equals(new Matrix(this).transpose());
	}

	/**
	 * @return true if matrix.transpose() * matrix = eye
	 */
	public boolean isOrthogonal() {
		Matrix m = new Matrix(this);
		return (m.transpose().multiply(this)).equals(Matrix.eye(
				this.linedimension, this.columndimension));
	}

	/**
	 * @return abs all values.
	 */
	public Matrix abs() {
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				this.setValue(i, j, Math.abs(this.getValue(i, j)));
			}
		}
		return this;
	}

	/**
	 * @return ceil all values.
	 */
	public Matrix ceil() {
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				this.setValue(i, j, Math.ceil(this.getValue(i, j)));
			}
		}
		return this;
	}

	/**
	 * @return floor all values.
	 */
	public Matrix floor() {
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				this.setValue(i, j, Math.floor(this.getValue(i, j)));
			}
		}
		return this;
	}

	/**
	 * @return round all values.
	 */
	public Matrix round() {
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				this.setValue(i, j, (double) Math.round(this.getValue(i, j)));
			}
		}
		return this;
	}

	/**
	 * @return sqrt all values.
	 */
	public Matrix sqrt() {
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				this.setValue(i, j, Math.sqrt(this.getValue(i, j)));
			}
		}
		return this;
	}

	/**
	 * @return pow all values.
	 */
	public Matrix pow(double degree) {
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				this.setValue(i, j, Math.pow(this.getValue(i, j), degree));
			}
		}
		return this;
	}

	/**
	 * @return added matrix (Mathematic operation).
	 * @throws IllegalArgumentException
	 *             if matrix dimensions are incompatible for this operation.
	 * @throws NullPointerException
	 *             if given matrix is null.
	 */
	public Matrix plus(Matrix matrix) throws IllegalArgumentException,
			NullPointerException {
		checkDimensionsForCalculations(matrix);
		Matrix summatrix = new Matrix(matrix.linedimension,
				matrix.columndimension);
		for (int i = 0; i < matrix.linedimension; i++) {
			for (int j = 0; j < matrix.columndimension; j++) {
				summatrix.setValue(i, j,
						matrix.getValue(i, j) + this.getValue(i, j));
			}
		}
		return summatrix;
	}

	/**
	 * @return added matrix (Mathematic operation).
	 */
	public Matrix plus(double value) {
		Matrix summatrix = new Matrix(this.linedimension, this.columndimension);
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				summatrix.setValue(i, j, this.getValue(i, j) + value);
			}
		}
		return summatrix;
	}

	/**
	 * @return subtracted matrix (Mathematic operation).
	 * @throws IllegalArgumentException
	 *             if matrix dimensions are incompatible for this operation.
	 * @throws NullPointerException
	 *             if given matrix is null.
	 */
	public Matrix minus(Matrix matrix) throws IllegalArgumentException,
			NullPointerException {
		checkDimensionsForCalculations(matrix);
		Matrix summatrix = new Matrix(matrix.linedimension,
				matrix.columndimension);
		for (int i = 0; i < matrix.linedimension; i++) {
			for (int j = 0; j < matrix.columndimension; j++) {
				summatrix.setValue(i, j,
						this.getValue(i, j) - matrix.getValue(i, j));
			}
		}
		return summatrix;
	}

	/**
	 * @return subtracted matrix (Mathematic operation).
	 */
	public Matrix minus(double value) {
		Matrix summatrix = new Matrix(this.linedimension, this.columndimension);
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				summatrix.setValue(i, j, this.getValue(i, j) - value);
			}
		}
		return summatrix;
	}

	/**
	 * @return matrix which all values will be multiplied -1.
	 */
	public Matrix minus() {
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				this.setValue(i, j, this.getValue(i, j) * (-1));
			}
		}
		return this;
	}

	/**
	 * @return multiplied matrix (Mathematic operation).
	 * @throws IllegalArgumentException
	 *             if matrix dimensions are incompatible for this operation.
	 * @throws NullPointerException
	 *             if given matrix is null.
	 */
	public Matrix multiply(Matrix matrix) throws IllegalArgumentException,
			NullPointerException {
		checkDimensionsForMultiply(matrix);
		Matrix ans = new Matrix(this.linedimension, matrix.columndimension);
		double sum = 0;
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < matrix.columndimension; j++) {
				sum = 0;
				for (int k = 0; k < matrix.linedimension; k++) {
					sum += (this.getValue(i, k) * matrix.getValue(k, j));
				}
				ans.setValue(i, j, sum);
			}
		}
		return ans;
	}

	/**
	 * @return multiplied matrix (Mathematic operation).
	 */
	public Matrix multiply(double value) {
		Matrix ans = new Matrix(this.linedimension, this.columndimension);
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				ans.setValue(i, j, this.getValue(i, j) * value);
			}
		}
		return ans;
	}

	/**
	 * @return divided matrix (Mathematic operation).
	 * @throws IllegalArgumentException
	 *             if matrix is rectangular or this matrix's column dimension is
	 *             not equal to matrix's line dimension.
	 * @throws NullPointerException
	 *             if matrix is null.
	 */
	public Matrix divide(Matrix matrix) throws IllegalArgumentException,
			NullPointerException {
		if (matrix.isRectangular())
			throw new IllegalArgumentException(MatrixExceptionMessages.SQUARE);
		if (this.columndimension != matrix.linedimension)
			throw new IllegalArgumentException(
					MatrixExceptionMessages.DIMENSION);
		Matrix helpm = new Matrix(matrix);
		helpm.inverse();
		Matrix divide = this.multiply(helpm);
		helpm = null;
		System.gc();
		return divide;
	}

	/**
	 * @return divided matrix (Mathematic operation).
	 */
	public Matrix divide(double value) {
		Matrix ans = new Matrix(this.linedimension, this.columndimension);
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				ans.setValue(i, j, this.getValue(i, j) / value);
			}
		}
		return ans;
	}

	// TODO if parameter is (1/2) ?
	/**
	 * @return degree matrix (Mathematic operation).
	 * @throws IllegalArgumentException
	 *             if this matrix is rectangular.
	 */
	public Matrix degree(int degree) throws IllegalArgumentException {
		if (degree == 0) {
			return Matrix.eye(this.linedimension, this.columndimension);
		}
		if (degree == 1) {
			return new Matrix(this);
		}
		if (this.isRectangular())
			throw new IllegalArgumentException(MatrixExceptionMessages.SQUARE);
		if (degree < 0) {
			Matrix m = degreeOperation(Math.abs(degree));
			return m.inverse();
		}
		return degreeOperation(degree);
	}

	/**
	 * fills matrix all data with 0.0
	 */
	public void fillWith0() {
		this.defaultFill(0.0);
	}

	/**
	 * fills matrix all data with 1.0
	 */
	public void fillWith1() {
		this.defaultFill(1.0);
	}

	/**
	 * @return this matrix with increased values by 1.
	 */
	public Matrix increment() {
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				this.setValue(i, j, this.getValue(i, j) + 1);
			}
		}
		return this;
	}

	/**
	 * @return this matrix with decreased values by 1.
	 */
	public Matrix decrement() {
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				this.setValue(i, j, this.getValue(i, j) - 1);
			}
		}
		return this;
	}

	/**
	 * @return first norm of this matrix (Mathematic operation).
	 */
	public double norm1() {
		Matrix m = new Matrix(this);
		m.abs();
		List<Double> lastarray = new ArrayList<Double>(
				this.columndimension);
		double sum = 0;
		for (int i = 0; i < this.columndimension; i++) {
			Object[] helparray = m.getColumn(i);
			for (int j = 0; j < this.linedimension; j++) {
				sum += (double) helparray[j];
			}
			lastarray.add(sum);
			sum = 0;
		}
		if (m.isVector() || m.isColumnVector()) {
			double lastSum = 0;
			for (int i = 0; i < lastarray.size(); i++) {
				lastSum += lastarray.get(i);
			}
			return lastSum;
		}
		return Collections.max(lastarray);
	}

	/**
	 * @return F norm of this matrix (Mathematic operation).
	 */
	public double normF() {
		Matrix m = new Matrix(this);
		m.transpose();
		Matrix m1 = m.multiply(this);
		return Math.sqrt(m1.trace());
	}

	/**
	 * @return inf norm of this matrix (Mathematic operation).
	 */
	public double normInf() {
		Matrix m = new Matrix(this);
		m.abs();
		if (m.isVector())
			return Collections.max(m.getLineAsList(0));
		if (m.isColumnVector())
			return Collections.max(m.getColumnAsList(0));
		List<Double> lastarray = new ArrayList<Double>(
				this.linedimension);
		double sum = 0;
		for (int i = 0; i < this.linedimension; i++) {
			Object[] helparray = m.getLine(i);
			for (int j = 0; j < this.columndimension; j++) {
				sum += (double) helparray[j];
			}
			lastarray.add(sum);
			sum = 0;
		}
		return Collections.max(lastarray);
	}

	/**
	 * @return sum main diagonal of this matrix.
	 * @throws IllegalArgumentException
	 *             if this matrix is not square.
	 */
	public double trace() throws IllegalArgumentException {
		if (this.isRectangular())
			throw new IllegalArgumentException(MatrixExceptionMessages.SQUARE);
		double sum = 0;
		for (int i = 0; i < this.linedimension; i++)
			sum += this.getValue(i, i);
		return sum;
	}

	/**
	 * @return minor matrix (in returned matrix will not be one line and column
	 *         according to given indexes).
	 * @throws ArrayIndexOutOfBoundsException
	 *             if given indexes are out of this matrix dimensions range.
	 * @throws IllegalArgumentException
	 *             if this matrix's is scalar or vector or column vector.
	 */
	public Matrix minor(int lineindex, int columnindex)
			throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
		Matrix m = new Matrix(this.linedimension - 1, this.columndimension - 1);
		for (int helpi = 0, i = 0; i < this.linedimension; i++, helpi++) {
			if (i == lineindex) {
				helpi--;
				continue;
			}
			for (int helpj = 0, j = 0; j < this.columndimension; j++, helpj++) {
				if (j == columnindex) {
					helpj--;
					continue;
				}
				m.setValue(helpi, helpj, this.getValue(i, j));
			}
		}
		return m;
	}

	/**
	 * @return determinant of this matrix.
	 * @throws IllegalArgumentException
	 *             if this matrix is not square.
	 */
	public double determinant() throws IllegalArgumentException {
		if (this.isRectangular())
			throw new IllegalArgumentException(MatrixExceptionMessages.SQUARE);
		if (this.linedimension == 1) {
			return this.getValue(0, 0);
		}
		if (this.linedimension == 2) {
			return ((this.getValue(0, 0) * this.getValue(1, 1)) - (this
					.getValue(0, 1) * this.getValue(1, 0)));
		}
		double d = 0.0;
		for (int i = 0; i < this.linedimension; i++) {
			d += (Math.pow(-1.0, (double) i + 2)) * this.getValue(0, i)
					* this.minor(0, i).determinant();
		}
		System.gc();
		return d;
	}

	/**
	 * @return A* , where A* is matrix with data : determinant(minor(i,j)).
	 * @throws IllegalArgumentException
	 *             if this matrix is rectangular.
	 */
	public Matrix cofactor() throws IllegalArgumentException {
		if (this.isRectangular())
			throw new IllegalArgumentException(MatrixExceptionMessages.SQUARE);
		Matrix m = new Matrix(this.linedimension, this.columndimension);
		for (int i = 0; i < m.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				m.setValue(
						i,
						j,
						Math.pow(-1.0, (i + j + 2))
								* (this.minor(i, j).determinant()));
			}
		}
		return m;
	}

	/**
	 * @return inverse matrix.
	 * @throws IllegalArgumentException
	 *             if matrix is rectangular.
	 */
	public Matrix inverse() throws IllegalArgumentException {
		if (this.isRectangular())
			throw new IllegalArgumentException(MatrixExceptionMessages.SQUARE);
		if (this.isScalar()) {
			return new Matrix(1.0 / this.getValue(0, 0));
		}
		double d = this.determinant();
		Matrix m = new Matrix(this.linedimension, this.columndimension);
		for (int i = 0; i < m.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				Matrix minormatrix = this.minor(i, j);
				m.setValue(
						i,
						j,
						Math.pow(-1.0, (i + j + 2))
								* (minormatrix.determinant()));
			}
		}
		m.transpose();
		Matrix temp = m.divide(d);
		// TODO i need to look it
		// this.data = m.data;
		changeContentQuickly(temp);
		return this;
	}

	/**
	 * @return matrix which contains values of variables.
	 * @throws IllegalArgumentException
	 *             if this matrix is rectangular, or this matrix's column
	 *             dimension is not equal to answerMatrix's line dimension.
	 */
	public Matrix solveLinearSystem(Matrix answersMatrix)
			throws IllegalArgumentException {
		if (this.isRectangular())
			throw new IllegalArgumentException(MatrixExceptionMessages.SQUARE);
		if (this.columndimension != answersMatrix.linedimension)
			throw new IllegalArgumentException(
					MatrixExceptionMessages.DIMENSION);
		Matrix helpm = new Matrix(this);
		helpm.inverse();
		Matrix divide = helpm.multiply(answersMatrix);
		helpm = null;
		System.gc();
		return divide;
	}

	/**
	 * Reads data from file and uses given separator to separate data, it means
	 * that all data should be separated only with this separator and nothing
	 * else, otherwise NumberFormatException will be thrown.<br>
	 * <b>Matrix will be resized automatically.</b>
	 * <p>
	 * Data in file should be like that for example if you use comma for
	 * separation:</br> 1.5 , 2.3</br> 5.7 , 8.9</br> 3.3 , 5</br>
	 * </p>
	 * There should not be enter at the last.
	 * 
	 * @param fileName
	 *            should be like this: "C:/Users/.../File name.extension".
	 * @param separator
	 *            should be: ',' or ':' or '/' and so on .... <i><b>Important:
	 *            this parameter should not be '.' !</b></i>
	 * @throws IllegalArgumentException
	 *             if file is empty.
	 * @throws NumberFormatException
	 *             if data is incorrect or separated incorrectly in the file.
	 * @throws java.io.FileNotFoundException
	 *             if given file does not exists.
	 */
	public void readDataFromFile(String fileName, char separator)
			throws IllegalArgumentException, NumberFormatException,
			java.io.FileNotFoundException {
		this.copy(readFromFile(fileName, String.valueOf(separator)));
	}

	/**
	 * Reads data from file and uses one space to separate data, it means that
	 * all data should be separated only with one space and nothing else,
	 * otherwise NumberFormatException will be thrown.</br> <b>Matrix will be
	 * resized automatically.</b>
	 * <p>
	 * Data in file should be like that:</br> 1.5 2.3</br> 5.7 8.9</br> 3.3
	 * 5</br>
	 * </p>
	 * There should not be enter at the last.
	 * 
	 * @param fileName
	 *            should be like this: "C:/Users/.../File name.extension".
	 * @throws IllegalArgumentException
	 *             if file is empty.
	 * @throws NumberFormatException
	 *             if data is incorrect or separated incorrectly in the file.
	 * @throws java.io.FileNotFoundException
	 *             if given file does not exists.
	 */
	public void readDataFromFile(String fileName)
			throws IllegalArgumentException, NumberFormatException,
			java.io.FileNotFoundException {
		readDataFromFile(fileName, ' ');
	}

	@Override
	public Matrix joinMatrixAtColumn(IDataMatrix<Double> matrix)
			throws NullPointerException {
		return (Matrix) super.joinMatrixAtColumn(matrix);
	}

	@Override
	public Matrix joinMatrixAtColumn(IDataMatrix<Double> matrix,
			Double defaultValue) throws NullPointerException {
		return (Matrix) super.joinMatrixAtColumn(matrix, defaultValue);
	}

	@Override
	public Matrix joinMatrixAtLine(IDataMatrix<Double> matrix)
			throws NullPointerException {
		return (Matrix) super.joinMatrixAtLine(matrix);
	}

	@Override
	public Matrix joinMatrixAtLine(IDataMatrix<Double> matrix,
			Double defaultValue) throws NullPointerException {
		return (Matrix) super.joinMatrixAtLine(matrix, defaultValue);
	}

	@Override
	public Matrix subMatrix(int startLineIndex, int startColumnIndex,
			int endLineIndex, int endColumnIndex)
			throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
		return (Matrix) super.subMatrix(startLineIndex, startColumnIndex,
				endLineIndex, endColumnIndex);
	}

	@Override
	public Matrix transpose() {
		return (Matrix) super.transpose();
	}
	
	/**
	 * Creates an identity matrix (eye matrix) with the specified dimensions.
	 * An identity matrix has 1.0s on its main diagonal and 0.0s elsewhere.
	 *
	 * @param linedimension   The number of lines (rows).
	 * @param columndimension The number of columns.
	 * @return A new identity {@code Matrix}.
	 * @throws IllegalArgumentException if {@code linedimension} or {@code columndimension} are not positive.
	 */
	public static Matrix eye(int linedimension, int columndimension) throws IllegalArgumentException {
		Matrix matrix = new Matrix(linedimension, columndimension, 0.0);
		matrix.setMainDiagonal(1.0);
		return matrix;
	}

	/**
	 * Creates a square identity matrix (eye matrix) with the specified dimension.
	 *
	 * @param squareDimension The number of lines and columns.
	 * @return A new square identity {@code Matrix}.
	 * @throws IllegalArgumentException if {@code squareDimension} is not positive.
	 */
	public static Matrix eye(int squareDimension) throws IllegalArgumentException {
		return Matrix.eye(squareDimension, squareDimension);
	}

	/**
	 * Creates a matrix of the specified dimensions filled entirely with zeros.
	 *
	 * @param linedimension   The number of lines (rows).
	 * @param columndimension The number of columns.
	 * @return A new zero {@code Matrix}.
	 * @throws IllegalArgumentException if {@code linedimension} or {@code columndimension} are not positive.
	 */
	public static Matrix zero(int linedimension, int columndimension) throws IllegalArgumentException {
		return new Matrix(linedimension, columndimension, 0.0);
	}

	/**
	 * Creates a square matrix of the specified dimension filled entirely with zeros.
	 *
	 * @param squareDimension The number of lines and columns.
	 * @return A new square zero {@code Matrix}.
	 * @throws IllegalArgumentException if {@code squareDimension} is not positive.
	 */
	public static Matrix zero(int squareDimension) throws IllegalArgumentException {
		return Matrix.zero(squareDimension, squareDimension);
	}

	/**
	 * Creates a matrix of the specified dimensions filled entirely with ones.
	 *
	 * @param linedimension   The number of lines (rows).
	 * @param columndimension The number of columns.
	 * @return A new {@code Matrix} filled with 1.0s.
	 * @throws IllegalArgumentException if {@code linedimension} or {@code columndimension} are not positive.
	 */
	public static Matrix ones(int linedimension, int columndimension) throws IllegalArgumentException {
		return new Matrix(linedimension, columndimension, 1.0);
	}

	/**
	 * Creates a square matrix of the specified dimension filled entirely with ones.
	 *
	 * @param squareDimension The number of lines and columns.
	 * @return A new square {@code Matrix} filled with 1.0s.
	 * @throws IllegalArgumentException if {@code squareDimension} is not positive.
	 */
	public static Matrix ones(int squareDimension) throws IllegalArgumentException {
		return Matrix.ones(squareDimension, squareDimension);
	}

	/**
	 * Creates a Hilbert matrix of the specified square dimension.
	 * A Hilbert matrix has elements H(i,j) = 1 / (i + j - 1) (using 1-based indexing for formula, adjusted for 0-based in code).
	 *
	 * @param squareDimension The number of lines and columns.
	 * @return A new Hilbert {@code Matrix}.
	 * @throws IllegalArgumentException if {@code squareDimension} is not positive.
	 */
	public static Matrix hilbert(int squareDimension) throws IllegalArgumentException {
		Matrix matrix = new Matrix(squareDimension);
		for (int i = 0; i < matrix.linedimension; i++) {
			for (int j = 0; j < matrix.columndimension; j++) {
				matrix.setValue(i, j, 1.0 / (i + j + 1)); // 0-based indexing (i+1) + (j+1) - 1 = i+j+1
			}
		}
		return matrix;
	}

	/**
	 * Creates a new matrix where each element is {@code value - matrix[i][j]}.
	 *
	 * @param value  The scalar value from which matrix elements are subtracted.
	 * @param matrix The matrix whose elements are subtracted.
	 * @return A new {@code Matrix} instance with the results.
	 * @throws NullPointerException if {@code matrix} is null or contains null elements.
	 */
	public static Matrix minus(double value, Matrix matrix) throws NullPointerException {
		if (matrix == null) throw new NullPointerException("Input matrix cannot be null.");
		Matrix resultMatrix = new Matrix(matrix.linedimension, matrix.columndimension);
		for (int i = 0; i < matrix.linedimension; i++) {
			for (int j = 0; j < matrix.columndimension; j++) {
				Double val = matrix.getValue(i,j);
				if (val == null) throw new NullPointerException("Cannot perform subtraction with null element in input matrix at [" + i + "," + j + "]");
				resultMatrix.setValue(i, j, value - val);
			}
		}
		return resultMatrix;
	}

	/**
	 * Creates a new matrix where each element is {@code value / matrix[i][j]}.
	 *
	 * @param value  The scalar value that is divided by matrix elements.
	 * @param matrix The matrix whose elements are the divisors.
	 * @return A new {@code Matrix} instance with the results.
	 * @throws NullPointerException if {@code matrix} is null or contains null elements.
	 * @throws ArithmeticException  if any element in {@code matrix} is zero.
	 */
	public static Matrix divide(double value, Matrix matrix) throws NullPointerException, ArithmeticException {
		if (matrix == null) throw new NullPointerException("Input matrix cannot be null.");
		Matrix ans = new Matrix(matrix.linedimension, matrix.columndimension);
		for (int i = 0; i < matrix.linedimension; i++) {
			for (int j = 0; j < matrix.columndimension; j++) {
				Double val = matrix.getValue(i,j);
				if (val == null) throw new NullPointerException("Cannot perform division with null element in input matrix at [" + i + "," + j + "]");
				if (val == 0) {
					throw new ArithmeticException("Division by zero at index [" + i + "," + j + "]");
				}
				ans.setValue(i, j, value / val);
			}
		}
		return ans;
	}

	/**
	 * Creates a matrix of specified dimensions filled with random double values between 0.0 (inclusive) and 1.0 (exclusive).
	 *
	 * @param linedimension   The number of lines (rows).
	 * @param columndimension The number of columns.
	 * @return A new {@code Matrix} with random values.
	 * @throws IllegalArgumentException if dimensions are not positive.
	 */
	public static Matrix random(int linedimension, int columndimension) throws IllegalArgumentException {
		Matrix m = new Matrix(linedimension, columndimension);
		Random random = new Random();
		for (int i = 0; i < m.linedimension; i++) {
			for (int j = 0; j < m.columndimension; j++) {
				m.setValue(i, j, random.nextDouble());
			}
		}
		return m;
	}

	/**
	 * Creates a square matrix of specified dimension filled with random double values.
	 *
	 * @param squareDimension The number of lines and columns.
	 * @return A new square {@code Matrix} with random values.
	 * @throws IllegalArgumentException if dimension is not positive.
	 */
	public static Matrix random(int squareDimension) throws IllegalArgumentException {
		return Matrix.random(squareDimension, squareDimension);
	}

	/**
	 * Creates a matrix with random dimensions (each up to 5, at least 1) filled with random double values.
	 *
	 * @return A new {@code Matrix} with random dimensions and values.
	 */
	public static Matrix random() {
		Random random = new Random();
		int lineDimension = random.nextInt(5) + 1; // Ensure at least 1, max 5
		int columnDimension = random.nextInt(5) + 1; // Ensure at least 1, max 5
		return Matrix.random(lineDimension, columnDimension);
	}

	/**
	 * Creates a matrix of specified dimensions filled with random double values within the range [start, end).
	 *
	 * @param linedimension   The number of lines.
	 * @param columndimension The number of columns.
	 * @param start           The inclusive lower bound of the random range.
	 * @param end             The exclusive upper bound of the random range.
	 * @return A new {@code Matrix} with random values in the specified range.
	 * @throws IllegalArgumentException if dimensions are not positive, or if {@code start >= end}.
	 */
	public static Matrix randomIn(int linedimension, int columndimension, int start, int end)
			throws IllegalArgumentException {
		if (end <= start) {
			throw new IllegalArgumentException(MatrixExceptionMessages.RANDOM);
		}
		Random r = new Random();
		Matrix m = new Matrix(linedimension, columndimension);
		for (int i = 0; i < m.linedimension; i++) {
			for (int j = 0; j < m.columndimension; j++) {
				m.setValue(i, j, start + (end - start) * r.nextDouble());
			}
		}
		return m;
	}

	/**
	 * Creates a square matrix of specified dimension filled with random double values within the range [start, end).
	 *
	 * @param squareDimension The number of lines and columns.
	 * @param start           The inclusive lower bound.
	 * @param end             The exclusive upper bound.
	 * @return A new square {@code Matrix} with random values in the range.
	 * @throws IllegalArgumentException if dimension is not positive, or if {@code start >= end}.
	 */
	public static Matrix randomIn(int squareDimension, int start, int end) throws IllegalArgumentException {
		return Matrix.randomIn(squareDimension, squareDimension, start, end);
	}

	/**
	 * Creates a matrix with random dimensions (each up to 5, at least 1) filled with random values in [start, end).
	 *
	 * @param start The inclusive lower bound.
	 * @param end   The exclusive upper bound.
	 * @return A new {@code Matrix} with random dimensions and values in the range.
	 * @throws IllegalArgumentException if {@code start >= end}.
	 */
	public static Matrix randomIn(int start, int end) throws IllegalArgumentException {
		Random random = new Random();
		int lineDimension = random.nextInt(5) + 1;
		int columnDimension = random.nextInt(5) + 1;
		return Matrix.randomIn(lineDimension, columnDimension, start, end);
	}

	/**
	 * Creates a matrix of a specific type (e.g., scalar, vector, zero, eye) with specified dimensions.
	 *
	 * @param matrixType The type of matrix to create (from {@link MatrixTypes}).
	 * @param dimensions Variable argument for dimensions:
	 *                   <ul>
	 *                   <li>For {@code SCALAR}: no dimension needed.</li>
	 *                   <li>For {@code VECTOR}, {@code COLUMN_VECTOR}, {@code HILBERT}, or square {@code MATRIX}, {@code ZERO}, {@code EYE}: one dimension (length/size).</li>
	 *                   <li>For rectangular {@code MATRIX}, {@code ZERO}, {@code EYE}: two dimensions (lines, columns).</li>
	 *                   </ul>
	 * @return The created {@code Matrix}.
	 * @throws NullPointerException     if {@code matrixType} is null, or if {@code dimensions} is null when expected.
	 * @throws IllegalArgumentException if dimensions are invalid for the given matrix type (e.g., negative, zero, or wrong number of dimensions).
	 */
	public static Matrix create(MatrixTypes matrixType, int... dimensions)
			throws NullPointerException, IllegalArgumentException {
		if (matrixType == null) throw new NullPointerException("MatrixType cannot be null.");
		// Basic dimension validation
		if (dimensions != null) {
			for (int dim : dimensions) {
				if (dim <= 0) throw new IllegalArgumentException("Dimensions must be positive.");
			}
		}

		switch (matrixType) {
		case SCALAR:
			return new Matrix(); // 1x1 null
		case VECTOR:
			if (dimensions == null || dimensions.length != 1) throw new IllegalArgumentException("Vector requires one dimension parameter.");
			return new Matrix(1, dimensions[0]);
		case COLUMN_VECTOR:
			if (dimensions == null || dimensions.length != 1) throw new IllegalArgumentException("Column vector requires one dimension parameter.");
			return new Matrix(dimensions[0], 1);
		case HILBERT:
			if (dimensions == null || dimensions.length != 1) throw new IllegalArgumentException("Hilbert matrix requires one dimension (square).");
			return Matrix.hilbert(dimensions[0]);
		case MATRIX:
			if (dimensions == null || dimensions.length == 0) throw new IllegalArgumentException("Matrix type requires at least one dimension.");
			if (dimensions.length > 1) {
				return new Matrix(dimensions[0], dimensions[1]);
			}
			return new Matrix(dimensions[0]); // Square matrix
		case ZERO:
			if (dimensions == null || dimensions.length == 0) throw new IllegalArgumentException("Zero matrix type requires at least one dimension.");
			if (dimensions.length > 1) {
				return Matrix.zero(dimensions[0], dimensions[1]);
			}
			return Matrix.zero(dimensions[0]); // Square zero matrix
		case EYE:
			if (dimensions == null || dimensions.length == 0) throw new IllegalArgumentException("Eye matrix type requires at least one dimension.");
			if (dimensions.length > 1) {
				return Matrix.eye(dimensions[0], dimensions[1]);
			}
			return Matrix.eye(dimensions[0]); // Square eye matrix
		default: // ONES or any other, treat as ONES as per original logic
			if (dimensions == null || dimensions.length == 0) throw new IllegalArgumentException("This matrix type requires at least one dimension.");
			if (dimensions.length > 1) {
				return Matrix.ones(dimensions[0], dimensions[1]);
			}
			return Matrix.ones(dimensions[0]); // Square ones matrix
		}
	}

	/**
	 * Creates a matrix of a specific type, filled with a {@code defaultValue}.
	 *
	 * @param matrixType   The type of matrix (currently supports {@code SCALAR}, {@code VECTOR}, {@code COLUMN_VECTOR}, or defaults to general {@code MATRIX}).
	 * @param defaultValue The value to fill the matrix elements with.
	 * @param dimensions   Variable argument for dimensions, similar to {@link #create(MatrixTypes, int...)}.
	 * @return The created {@code Matrix}.
	 * @throws NullPointerException     if {@code matrixType} is null, or if {@code dimensions} is null when expected.
	 * @throws IllegalArgumentException if dimensions are invalid.
	 */
	public static Matrix create(MatrixTypes matrixType, double defaultValue, int... dimensions)
			throws NullPointerException, IllegalArgumentException {
		if (matrixType == null) throw new NullPointerException("MatrixType cannot be null.");
		if (dimensions != null) {
			for (int dim : dimensions) {
				if (dim <= 0) throw new IllegalArgumentException("Dimensions must be positive.");
			}
		}

		switch (matrixType) {
		case SCALAR:
			return new Matrix(defaultValue);
		case VECTOR:
			if (dimensions == null || dimensions.length != 1) throw new IllegalArgumentException("Vector requires one dimension parameter.");
			return new Matrix(1, dimensions[0], defaultValue);
		case COLUMN_VECTOR:
			if (dimensions == null || dimensions.length != 1) throw new IllegalArgumentException("Column vector requires one dimension parameter.");
			return new Matrix(dimensions[0], 1, defaultValue);
		default: // MATRIX, HILBERT, ZERO, EYE, ONES (Hilbert, Zero, Eye, Ones are special cases of filled matrices)
			if (dimensions == null || dimensions.length == 0) throw new IllegalArgumentException("This matrix type requires at least one dimension.");
			if (dimensions.length > 1) {
				return new Matrix(dimensions[0], dimensions[1], defaultValue);
			}
			return new Matrix(dimensions[0], defaultValue); // Square matrix
		}
	}

	/**
	 * Creates a block diagonal matrix where a specified number of diagonals around the main diagonal are filled with {@code value},
	 * and other elements are zero.
	 * <p>
	 * Example: {@code Matrix.diagonal(3, 3, 3, 1.0)} (3x3 matrix, 3 diagonals including main) might produce:
	 * <pre>
	 * 1.0  1.0  0.0
	 * 1.0  1.0  1.0
	 * 0.0  1.0  1.0
	 * </pre>
	 *
	 * @param linedimension    Number of lines.
	 * @param columndimension  Number of columns.
	 * @param diagonalnumber   Total number of diagonals to be filled (must be odd, e.g., 1 for main only, 3 for main and adjacent).
	 * @param value            The value to fill the specified diagonals with.
	 * @return A new block diagonal {@code Matrix}.
	 * @throws IllegalArgumentException if dimensions are not positive, or if {@code diagonalnumber} is invalid
	 *                                  (e.g., even, too large for matrix dimensions, or not positive).
	 */
	public static Matrix diagonal(int linedimension, int columndimension, int diagonalnumber, double value)
			throws IllegalArgumentException {
		Matrix m = new Matrix(linedimension, columndimension); // Initializes with nulls
		if (diagonalnumber >= (2 * m.getMinDimension()) || (diagonalnumber % 2 == 0) || diagonalnumber < 1) {
			throw new IllegalArgumentException(MatrixExceptionMessages.DIAGONAL + " Number of diagonals must be odd, positive, and within matrix limits.");
		}
		m.fillWith0(); // Fill with zeros first
		m.setDiagonal(0, value); // Main diagonal
		for (int i = 1; i <= (diagonalnumber / 2); i++) {
			m.setDiagonal(i, value);  // Upper diagonals
			m.setDiagonal(-i, value); // Lower diagonals
		}
		return m;
	}

	/**
	 * Creates a square block diagonal matrix.
	 *
	 * @param squareDimension The number of lines and columns.
	 * @param diagonalnumber  Total number of diagonals to fill (must be odd).
	 * @param value           The value for the diagonals.
	 * @return A new square block diagonal {@code Matrix}.
	 * @throws IllegalArgumentException if arguments are invalid as per {@link #diagonal(int, int, int, double)}.
	 */
	public static Matrix diagonal(int squareDimension, int diagonalnumber, double value)
			throws IllegalArgumentException {
		return Matrix.diagonal(squareDimension, squareDimension, diagonalnumber, value);
	}

	/**
	 * Creates a block anti-diagonal matrix (diagonals perpendicular to the main anti-diagonal).
	 * <p>
	 * Example: {@code Matrix.diagonal2(3, 3, 3, 1.0)} might produce:
	 * <pre>
	 * 0.0  1.0  1.0
	 * 1.0  1.0  1.0
	 * 1.0  1.0  0.0
	 * </pre>
	 *
	 * @param linedimension   Number of lines.
	 * @param columndimension Number of columns.
	 * @param diagonalnumber  Total number of anti-diagonals to fill (must be odd and positive).
	 * @param value           The value for these anti-diagonals.
	 * @return A new block anti-diagonal {@code Matrix}.
	 * @throws IllegalArgumentException if arguments are invalid (e.g. dimensions not positive, diagonalnumber even/too large/not positive).
	 */
	public static Matrix diagonal2(int linedimension, int columndimension, int diagonalnumber, double value)
			throws IllegalArgumentException {
		Matrix m = new Matrix(linedimension, columndimension);
		if (diagonalnumber >= (2 * m.getMinDimension()) || (diagonalnumber % 2 == 0) || diagonalnumber < 1) {
			throw new IllegalArgumentException(MatrixExceptionMessages.DIAGONAL + " Number of diagonals must be odd, positive, and within matrix limits.");
		}
		m.fillWith0();
		m.setDiagonal2(0, value); // Main anti-diagonal
		for (int i = 1; i <= (diagonalnumber / 2); i++) {
			m.setDiagonal2(i, value);  // Anti-diagonals "above" (towards top-right)
			m.setDiagonal2(-i, value); // Anti-diagonals "below" (towards bottom-left)
		}
		return m;
	}

	/**
	 * Creates a square block anti-diagonal matrix.
	 *
	 * @param squareDimension The number of lines and columns.
	 * @param diagonalnumber  Total number of anti-diagonals to fill (must be odd and positive).
	 * @param value           The value for these anti-diagonals.
	 * @return A new square block anti-diagonal {@code Matrix}.
	 * @throws IllegalArgumentException if arguments are invalid.
	 */
	public static Matrix diagonal2(int squareDimension, int diagonalnumber, double value)
			throws IllegalArgumentException {
		return Matrix.diagonal2(squareDimension, squareDimension, diagonalnumber, value);
	}

	/**
	 * Main method for demonstration or testing of Matrix functionalities.
	 * @param argss Command line arguments (not used by this demonstration).
	 */
	public static void main(String [] argss) {
		// Example usage of the Matrix class
		Matrix matrix = new Matrix(3);
		matrix.setValue(0, 0, 3.0);