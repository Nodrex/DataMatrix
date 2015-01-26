package com.nodrex.datamatrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * This class contains useful methods to manipulate on data of matrix type.
 * </p>
 * <p>
 * Implements Serializable , Comparable , Cloneable interfaces.
 * </p>
 * <p>
 * Can be extended by any class.
 * </p>
 * 
 * @param <Data>Any type can be stored.
 * @author NODREX
 * @version 1.0
 * @since 2013
 */
public class DataMatrix<Data> implements IDataMatrix<Data> {

	private static final long serialVersionUID = 5879569147553716307L;

	protected interface ExceptionMessages extends java.io.Serializable {
		String POSITIVE_NON_ZERO = "Arguments must be positive and non zero!";
		String INDEXES = "Indexes must be in  Matrix dimensions range!";
		String POSITIVE = "Argument mus be positive!";
		String NON_NEGATIVE = "Argument mus be non negative!";
		String LINE_INDEX = "LineIndex must be in  Matrix line dimension range!";
		String COLUMN_INDEX = "ColumnIndex must be in  Matrix column dimension range!";
		String INDEX = "Index must be in Matrix dimensions range!";
		String COMPARABLE = "Given data must implement Comparable interface!";
		String SWAPDATA = "data_1 or data_2 or both does not exist in this matrix!";
		String NOT_SERIALIZABLE = "Given data must implement Serializable interface!";
		String PLEASE_FILL = "Please fill data at least with one element!";
		String ID = "id should be unique!";
		String ID_NOT_EMPTY = "id should not be empty!";
		String ONLY_ONE_LINE = "Can not delete this line, because only this line is left!";
		String ONLY_ONE_COLUMN = "Can not delete this column, because only this column is left!";
		String EVERYTHING_IS_NULL = "Everything is null! Please insert at least one value.";
		String INDEXES_IN_SUB_MATRIX = "Pleas check arguments in subMatrix.";
	}

	protected Data[][] data;
	protected int linedimension;
	protected int columndimension;
	protected String name = "";
	protected String id = "";
	protected static final List<String> ids = new ArrayList<String>();

	/**
	 * @return copy of this matrix.
	 */
	protected IDataMatrix<Data> clone() throws CloneNotSupportedException {
		return new DataMatrix<Data>(this);
	}

	/**
	 * Removes id of this matrix form ids list.
	 */
	protected void finalize() throws Throwable {
		if (this.id.equals(""))
			return;
		DataMatrix.ids.remove(this.id);
	}

	/**
	 * Changes dimensions and data reference. also calls garbage collector.</br>
	 * <b>If you want to change references of data i recommend to use this
	 * method.</b>
	 * <p>
	 * Content:</br> this.linedimension = tempDataMatrix.linedimension;</br>
	 * this.columndimension = tempDataMatrix.columndimension;</br> this.data =
	 * tempDataMatrix.data;</br> tempDataMatrix = null;</br> System.gc();</br>
	 * </p>
	 */
	protected void changeContentQuickly(IDataMatrix<Data> tempDataMatrix) {
		this.linedimension = tempDataMatrix.getLineDimension();
		this.columndimension = tempDataMatrix.getColumnDimension();
		//this.data = (Data[][]) tempDataMatrix.getData();
		//I guess for quick change there should be :
		this.data = ((DataMatrix<Data>)tempDataMatrix).data;
		tempDataMatrix = null;
		System.gc();
	}

	/**
	 * @return name , dimensions , id and data as string.
	 */
	public String toString() {
		String result = "Matrix: " + this.name + "\nDimensions: "
				+ this.linedimension + " X " + this.columndimension;
		if (this.id.isEmpty()) {
			result += "\n";
		} else {
			result += "\nid: " + this.id + "\n";
		}
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				if (this.data[i][j] == null) {
					result += this.data[i][j] + " ";
				} else {
					result += this.data[i][j].toString() + " ";
				}
			}
			result += "\n";
		}
		return result;
	}

	/**
	 * Compares 2 matrixes.
	 * 
	 * @return true if both matrixes have same dimension and same data,
	 *         otherwise returns false.
	 * @throws NullPointerException
	 *             if given parameter is null.
	 */
	public boolean equals(Object obj) throws NullPointerException {
		if (obj == null)
			throw new NullPointerException();
		if (obj instanceof DataMatrix) {
			try {
				@SuppressWarnings("unchecked")
				DataMatrix<Data> variable = (DataMatrix<Data>) obj;
				if (!isDimensionsEqualTo(variable.linedimension,
						variable.columndimension))
					return false;
				for (int i = 0; i < variable.linedimension; i++) {
					for (int j = 0; j < variable.columndimension; j++) {
						if (!(this.data[i][j].equals(variable.data[i][j]))) {
							return false;
						}
					}
				}
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Compares with number of elements.
	 * <p>
	 * If this matrix elements are less then given matrix returns -1 , if both
	 * matrix elements are equal returns 0 , otherwise returns 1.
	 * </p>
	 */
	public int compareTo(IDataMatrix<Data> iDataMatrix) {
		return this.getNumberOfElements() - iDataMatrix.getNumberOfElements();
	}

	@SuppressWarnings("unchecked")
	private void init(int linedimension, int columndimension)
			throws IllegalArgumentException {
		checkArguments(linedimension, columndimension);
		this.data = (Data[][]) new Object[linedimension][columndimension];
		this.linedimension = linedimension;
		this.columndimension = columndimension;
	}

	@SuppressWarnings("unchecked")
	private void fill(Data[]... data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}

	private void checkIndexes(int lineindex, int columnindex)
			throws ArrayIndexOutOfBoundsException {
		if (!this.isIndexes(lineindex, columnindex))
			throw new ArrayIndexOutOfBoundsException(ExceptionMessages.INDEXES);
	}

	private void checkLineIndex(int index)
			throws ArrayIndexOutOfBoundsException {
		if (index < 0 || index >= this.linedimension)
			throw new ArrayIndexOutOfBoundsException(
					ExceptionMessages.LINE_INDEX);
	}

	private void checkColumnIndex(int index)
			throws ArrayIndexOutOfBoundsException {
		if (index < 0 || index >= this.columndimension)
			throw new ArrayIndexOutOfBoundsException(
					ExceptionMessages.COLUMN_INDEX);
	}

	private void checkArguments(int linedimension, int columndimension)
			throws IllegalArgumentException {
		if (linedimension <= 0 || columndimension <= 0)
			throw new IllegalArgumentException(
					ExceptionMessages.POSITIVE_NON_ZERO);
	}

	private void checkForLess(int higher, int less)
			throws IllegalArgumentException {
		if (higher < less) {
			throw new IllegalArgumentException(
					ExceptionMessages.INDEXES_IN_SUB_MATRIX);
		}
	}

	/**
	 * Deletes objects
	 * <p>
	 * Example: setNull(new Object(), new Object(), ...);
	 * </p>
	 * <p>
	 * So you can send as match arguments as you want.
	 * </p>
	 * 
	 * @param eny
	 *            objects
	 */
	private void setNull(Object... objects) {
		for (int i = 0; i < objects.length; i++) {
			objects[i] = null;
		}
		objects = null;
		System.gc();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List toList(Data... data) {
		List tempList = new ArrayList(data.length);
		for (int i = 0; i < data.length; i++) {
			tempList.add(data[i]);
		}
		return tempList;
	}

	@SuppressWarnings("unchecked")
	private List<Data> toParameterizedList(Object... data) {
		List<Data> tempList = new ArrayList<Data>(
				data.length);
		for (int i = 0; i < data.length; i++) {
			tempList.add((Data) data[i]);
		}
		return tempList;
	}

	private void checkIfImplementsComparable() throws ClassCastException {
		Data data = null;
		iteration: for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				if (this.data[i][j] != null) {
					data = this.data[i][j];
					break iteration;
				}
			}
		}
		if (data == null) {
			throw new NullPointerException(ExceptionMessages.EVERYTHING_IS_NULL);
		}
		if (!(data instanceof Comparable))
			throw new ClassCastException(ExceptionMessages.COMPARABLE);
	}

	private void checkIfImplementsComparable(Data data)
			throws ClassCastException, NullPointerException {
		if (data == null)
			throw new NullPointerException();
		if (!(data instanceof Comparable))
			throw new ClassCastException(ExceptionMessages.COMPARABLE);
	}

	private void fillLeftLines(int oldLineDimension, int index,
			Data defaultValue) {
		if (index < oldLineDimension && index >= 0)
			return;
		if (index < 0) {
			index *= -1;
			for (int i = 0; i < index; i++) {
				setLine(i, defaultValue);
			}
			return;
		}
		for (int i = oldLineDimension; i < this.linedimension; i++) {
			setLine(i, defaultValue);
		}
	}

	@SuppressWarnings("unchecked")
	private void fillLeftLines(int oldLineDimension, int index,
			Data... lineArray) {
		if (index < oldLineDimension && index >= 0)
			return;
		if (index < 0) {
			index *= -1;
			for (int i = 0; i < index; i++) {
				setLine(i, lineArray);
			}
			return;
		}
		for (int i = oldLineDimension; i < this.linedimension; i++) {
			setLine(i, lineArray);
		}
	}

	private void fillLeftLines(int oldLineDimension, int index,
			List<Data> lineList) {
		if (index < oldLineDimension && index >= 0)
			return;
		if (index < 0) {
			index *= -1;
			for (int i = 0; i < index; i++) {
				setLine(i, lineList);
			}
			return;
		}
		for (int i = oldLineDimension; i < this.linedimension; i++) {
			setLine(i, lineList);
		}
	}

	private void fillLeftColumns(int oldColumnDimension, int index,
			Data defaultValue) {
		if (index < oldColumnDimension && index >= 0)
			return;
		if (index < 0) {
			index *= -1;
			for (int i = 0; i < index; i++) {
				setColumn(i, defaultValue);
			}
			return;
		}
		for (int i = oldColumnDimension; i < this.columndimension; i++) {
			setColumn(i, defaultValue);
		}
	}

	@SuppressWarnings("unchecked")
	private void fillLeftColumns(int oldColumnDimension, int index,
			Data... columnArray) {
		if (index < oldColumnDimension && index >= 0)
			return;
		if (index < 0) {
			index *= -1;
			for (int i = 0; i < index; i++) {
				setColumn(i, columnArray);
			}
			return;
		}
		for (int i = oldColumnDimension; i < this.columndimension; i++) {
			setColumn(i, columnArray);
		}
	}

	private void fillLeftColumns(int oldColumnDimension, int index,
			List<Data> columnList) {
		if (index < oldColumnDimension && index >= 0)
			return;
		if (index < 0) {
			index *= -1;
			for (int i = 0; i < index; i++) {
				setColumn(i, columnList);
			}
			return;
		}
		for (int i = oldColumnDimension; i < this.columndimension; i++) {
			setColumn(i, columnList);
		}
	}

	private void fillTempDataMatrixWithGivenDataOnGivenIndexes(
			DataMatrix<Data> tempDataMatrix, Data[][] data, int lineIndex,
			int columnIndex) {
		for (int i = 0, helpi = lineIndex; i < data.length; i++, helpi++) {
			for (int j = 0, helpj = columnIndex; j < data[i].length; j++, helpj++) {
				tempDataMatrix.data[helpi][helpj] = data[i][j];
			}
		}
	}

	private void fillTempDataMatrixWithGivenDataOnGivenIndexes(
			DataMatrix<Data> tempDataMatrix,
			List<List<Data>> data, int lineIndex,
			int columnIndex) {
		for (int i = 0, helpi = lineIndex; i < data.size(); i++, helpi++) {
			for (int j = 0, helpj = columnIndex; j < data.get(i).size(); j++, helpj++) {
				tempDataMatrix.data[helpi][helpj] = data.get(i).get(j);
			}
		}
	}

	/**
	 * Creates Matrix with one data which will be null.
	 */
	public DataMatrix() {
		init(1, 1);
	}

	/**
	 * Creates Matrix with one data and fills with default value that you gave.
	 */
	public DataMatrix(Data defaultValue) {
		init(1, 1);
		this.data[0][0] = defaultValue;
	}

	/**
	 * Creates Matrix with dimensions that you gave and all data automatically
	 * will be null.
	 * 
	 * @throws IllegalArgumentException
	 *             if given lineDimension or columnDimension are negative or
	 *             zero.
	 */
	public DataMatrix(int lineDimension, int columnDimension)
			throws IllegalArgumentException {
		init(lineDimension, columnDimension);
	}

	/**
	 * Creates Matrix with dimensions that you gave and feels with default value
	 * that you gave.
	 * 
	 * @throws IllegalArgumentException
	 *             if given lineDimension or columnDimension are negative or
	 *             zero.
	 */
	public DataMatrix(int lineDimension, int columnDimension, Data DefaultValue)
			throws IllegalArgumentException {
		init(lineDimension, columnDimension);
		defaultFill(DefaultValue);
	}

	/**
	 * Creates square matrix with dimension that you gave and all data
	 * automatically will be null.
	 * 
	 * @throws IllegalArgumentException
	 *             if given squareDimensioni is negative or zero.
	 */
	public DataMatrix(int squareDimension) throws IllegalArgumentException {
		init(squareDimension, squareDimension);
	}

	/**
	 * Creates square matrix with dimension that you gave and fills with default
	 * value that you gave.
	 * 
	 * @throws IllegalArgumentException
	 *             if given squareDimension is negative or zero.
	 */
	public DataMatrix(int squareDimension, Data defaultValue)
			throws IllegalArgumentException {
		init(squareDimension, squareDimension);
		defaultFill(defaultValue);
	}

	/**
	 * Creates copy of given data matrix.
	 * <p>
	 * This constructor does not copy id of given matrix, because id should be
	 * unique.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if given matrix is null.
	 */
	@SuppressWarnings("unchecked")
	public DataMatrix(IDataMatrix<Data> matrix) throws NullPointerException {
		this.init(matrix.getLineDimension(), matrix.getColumnDimension());
		this.fill((Data[][]) matrix.getData());
		this.name = new String(matrix.getName());
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
	 * DataMatrix<<Data>> dataMatrix = new DataMatrix<<Data>>(new
	 * Data[]{value_2,value_2,value_3},new Data[]{value_4,value_5,value_6})
	 * </p>
	 * <p>
	 * It will create dataMatrix with dimensions: line dimension 2 and column
	 * dimension 3
	 * </p>
	 * <p>
	 * Example 2:
	 * </p>
	 * <p>
	 * DataMatrix<<Data>> dataMatrix = new DataMatrix<<Data>>(new Data[][]{new
	 * Data[]{value_1,value_2,value_3}, new Data[]{value_4,value_5,value_6}, new
	 * Data[]{value_7,value_8,value_9}}
	 * </p>
	 * <p>
	 * It will create dataMatrix with dimensions: line dimension 3 and column
	 * dimension 3
	 * </p>
	 * 
	 * <p>
	 * You also can gave jagged array, it will automatically fit dimensions: It
	 * will take max line and column dimensions.
	 * </p>
	 * 
	 * <p>
	 * For example if you gave: new
	 * Data[][]{{value_1},{value_2,value_3,value_4},{value_5,value_6}} then
	 * result will be :
	 * <p>
	 * Matrix 3 X 3 </br> value_1 null null </br> value_2 value_3 value_4 </br>
	 * value_5 value_6 null
	 * </p>
	 * 
	 * @param data
	 *            can be:
	 *            <p>
	 *            (new Data[]{value_1,value_2,value_3}, ...)
	 *            </p>
	 *            or
	 *            <p>
	 *            (new Data[][]{new Data[]{value_1,value_2,value_3}, ...})
	 *            </p>
	 *            or
	 *            <p>
	 *            (new Data[][]{new Data[]{value_1,value_2,value_3},new
	 *            Data[]{value_4,value_5,value_6},...})
	 *            </p
	 * @throws NullPointerException
	 *             if given data is null.
	 */
	@SuppressWarnings("unchecked")
	public DataMatrix(Data[]... data) throws NullPointerException {
		List<Integer> lengthList = new ArrayList<Integer>(
				data.length);
		for (int i = 0; i < data.length; i++) {
			lengthList.add(data[i].length);
		}
		init(data.length, Collections.max(lengthList));
		fill(data);
	}

	/**
	 * Creates matrix and fills with data that you gave.
	 * <p>
	 * This constructor takes dimensions from given data automatically.
	 * </p>
	 * 
	 * <p>
	 * You also can gave jagged data, it will automatically fit dimensions: It
	 * will take max line and column dimensions.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if given data is null.
	 * 
	 * @throws IllegalArgumentException
	 *             if data is not filled at least with one element.
	 */
	public DataMatrix(List<List<Data>> data)
			throws NullPointerException, IllegalArgumentException {
		copy(data);
	}

	/**
	 * Reads data from file as DataMatrix , so Serializable data in file should
	 * be DataMatrix type.
	 * 
	 * @throws NullPointerException
	 *             if fileName or readDataType is null.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 *             if data is not DataMatrix type.
	 */
	@SuppressWarnings("unchecked")
	public DataMatrix(String fileName) throws NullPointerException,
			java.io.IOException, ClassNotFoundException {
		java.io.InputStream file = new java.io.FileInputStream(fileName);
		java.io.InputStream buffer = new java.io.BufferedInputStream(file);
		java.io.ObjectInput input = new java.io.ObjectInputStream(buffer);
		DataMatrix<Data> dm = (DataMatrix<Data>) input.readObject();
		changeContentQuickly(dm);
		this.name = dm.name;
		try {
			setId(dm.getId());
		} catch (IllegalArgumentException e) {
			// id was not unique.
		} finally {
			file.close();
			buffer.close();
			input.close();
		}
	}

	public boolean is(Data value) {
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				if (this.data[i][j].equals(value))
					return true;
			}
		}
		return false;
	}

	public boolean isSquare() {
		return this.linedimension == this.columndimension;
	}

	public boolean isRectangular() {
		return this.linedimension != this.columndimension;
	}

	public boolean isLineIndex(int lineIndex) {
		return (lineIndex >= 0 && lineIndex < this.linedimension);
	}

	public boolean isColumnIndex(int columnIndex) {
		return (columnIndex >= 0 && columnIndex < this.columndimension);
	}

	public boolean isIndexes(int lineIndex, int columnIndex) {
		return (isLineIndex(lineIndex) && isColumnIndex(columnIndex));
	}

	public boolean isScalar() {
		return (this.linedimension * this.columndimension) == 1;
	}

	public boolean isVector() {
		return (this.linedimension == 1 && this.columndimension > 1);
	}

	public boolean isMatrix() {
		return (this.linedimension > 1 && this.columndimension > 1);
	}

	public boolean isColumnVector() {
		return (this.columndimension == 1 && this.linedimension > 1);
	}

	public boolean isDimensionsEqualTo(int lineDimension, int columnDimension) {
		return (this.linedimension == lineDimension && this.columndimension == columnDimension);
	}

	public boolean isEveryElementEqualTo(Data variable) {
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				if (!(this.data[i][j].equals(variable))) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isEveryElementNotEqualTo(Data variable) {
		for (int i = 0; i < this.data.length; i++) {
			for (int j = 0; j < this.data[i].length; j++) {
				if (this.data[i][j].equals(variable)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isNull() {
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				if (this.data[i][j] == null) {
					return true;
				}
			}
		}
		return false;
	}

	public void setName(String name) throws NullPointerException {
		if (name == null)
			throw new NullPointerException();
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setId(String id) throws IllegalArgumentException {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException(ExceptionMessages.ID_NOT_EMPTY);
		}
		if (this.id.equals(id)) {
			return;
		}
		if (DataMatrix.ids.contains(id)) {
			throw new IllegalArgumentException(ExceptionMessages.ID);
		}
		if (!this.id.equals("")) {
			DataMatrix.ids.remove(this.id);
		}
		this.id = id;
		DataMatrix.ids.add(id);
	}

	public Data getFirstNotNullValue() throws NullPointerException {
		Data data = null;
		iteration: for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				if (this.data[i][j] != null) {
					data = this.data[i][j];
					break iteration;
				}
			}
		}
		if (data == null)
			throw new NullPointerException(ExceptionMessages.EVERYTHING_IS_NULL);
		return data;
	}

	public String getId() {
		return new String(this.id);
	}

	public Object[][] getData() {
		//correct will be return this.data and other code should be commented
		Object[][] newData = new Object[this.linedimension][this.columndimension];
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				newData[i][j] = this.data[i][j];
			}
		}
		return newData;
	}

	@SuppressWarnings("unchecked")
	public void setData(Data[]... data) throws NullPointerException {
		List<Integer> lengthList = new ArrayList<Integer>(
				data.length);
		for (int i = 0; i < data.length; i++) {
			lengthList.add(data[i].length);
		}
		init(data.length, Collections.max(lengthList));
		//i think here should be this.data = data;
		//this.fill(data) should be commented.
		this.fill(data);
		System.gc();
	}

	public void setData(List<List<Data>> data)
			throws NullPointerException, IllegalArgumentException {
		copy(data);
	}

	public void setValue(int lineIndex, int columnIndex, Data value)
			throws ArrayIndexOutOfBoundsException {
		checkIndexes(lineIndex, columnIndex);
		this.data[lineIndex][columnIndex] = value;
	}

	public Data getValue(int lineIndex, int columnIndex)
			throws ArrayIndexOutOfBoundsException {
		checkIndexes(lineIndex, columnIndex);
		return this.data[lineIndex][columnIndex];
	}

	public int[] getIndexesOf(Data value) {
		int[] indexes = new int[2];
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				if (this.data[i][j] == value) {
					indexes[0] = i;
					indexes[1] = j;
					return indexes;
				}
			}
		}
		return null;
	}

	public int getNumberOfElements() {
		return this.linedimension * this.columndimension;
	}

	public int getLineDimension() {
		return this.linedimension;
	}

	public int getColumnDimension() {
		return this.columndimension;
	}

	public int getMaxDimension() {
		return Math.max(this.linedimension, this.columndimension);
	}

	public int getMinDimension() {
		return Math.min(this.linedimension, this.columndimension);
	}

	public Data getLastElement() {
		return this.data[this.linedimension - 1][this.columndimension - 1];
	}

	public void setLastElement(Data value) {
		this.data[this.linedimension - 1][this.columndimension - 1] = value;
	}

	public void defaultFill(Data defaultValue) {
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				this.data[i][j] = defaultValue;
			}
		}
	}

	public void changeContent(Data[][] data, String name, String id)
			throws NullPointerException, IllegalArgumentException {
		this.setData(data);
		this.name = name;
		this.setId(id);
	}

	public void changeContent(List<List<Data>> data,
			String name, String id) throws NullPointerException,
			IllegalArgumentException {
		copy(data);
		this.name = name;
		this.setId(id);
	}

	public void changeContent(Data[][] data, String name)
			throws NullPointerException {
		this.setData(data);
		this.name = name;
	}

	public void changeContent(List<List<Data>> data,
			String name) throws NullPointerException, IllegalArgumentException {
		this.copy(data);
		this.name = name;
	}

	@SuppressWarnings("unchecked")
	public void changeContent(Data[]... data) throws NullPointerException {
		this.changeContent(data, this.name);
	}

	public void changeContent(List<List<Data>> data)
			throws NullPointerException, IllegalArgumentException {
		this.changeContent(data, this.name);
	}

	public void clean() {
		init(this.linedimension, this.columndimension);
	}

	public void resize(int lineDimension, int columnDimension)
			throws IllegalArgumentException {
		checkArguments(lineDimension, columnDimension);
		if (isDimensionsEqualTo(lineDimension, columnDimension))
			return;
		DataMatrix<Data> tempDataMatrix = new DataMatrix<Data>(lineDimension,
				columnDimension);
		for (int i = 0; i < Math.min(lineDimension, this.linedimension); i++) {
			for (int j = 0; j < Math.min(columnDimension, this.columndimension); j++) {
				tempDataMatrix.data[i][j] = this.data[i][j];
			}
		}
		changeContentQuickly(tempDataMatrix);
	}

	public void resize(int squareDimension) throws IllegalArgumentException {
		resize(squareDimension, squareDimension);
	}

	public void resize(int lineDimension, int columnDimension, Data defaultValue)
			throws IllegalArgumentException {
		int startlinedimension = this.linedimension;
		int startcolumndimension = this.columndimension;
		resize(lineDimension, columnDimension);

		if (startlinedimension >= this.linedimension
				&& startcolumndimension >= this.columndimension) {
			return;
		}

		if (startlinedimension <= this.linedimension
				&& startcolumndimension <= this.columndimension) {
			for (int i = 0; i < this.linedimension; i++) {
				for (int j = startcolumndimension; j < this.columndimension; j++) {
					this.data[i][j] = defaultValue;
				}
				if (i == (startlinedimension - 1))
					startcolumndimension = 0;
			}
			return;
		}

		else if (startlinedimension >= this.linedimension
				&& startcolumndimension <= this.columndimension) {
			for (int i = 0; i < this.linedimension; i++)
				for (int j = startcolumndimension; j < this.columndimension; j++)
					this.data[i][j] = defaultValue;
			return;
		}

		else {
			for (int i = startlinedimension; i < this.linedimension; i++)
				for (int j = 0; j < this.columndimension; j++)
					this.data[i][j] = defaultValue;
		}
		System.gc();
	}

	public void resize(int squareDimension, Data defaultValue)
			throws IllegalArgumentException {
		resize(squareDimension, squareDimension, defaultValue);
	}

	public List<List<Data>> getDataAsList() {
		List<List<Data>> listDataMatrix = new ArrayList<List<Data>>(
				this.linedimension);
		List<Data> innerlist = null;
		for (int i = 0; i < this.linedimension; i++) {
			innerlist = new ArrayList<Data>(this.columndimension);
			for (int j = 0; j < this.columndimension; j++) {
				innerlist.add(this.data[i][j]);
			}
			listDataMatrix.add(innerlist);
		}
		return listDataMatrix;
	}

	public void copy(List<List<Data>> data)
			throws NullPointerException, IllegalArgumentException {
		if (data.size() == 0) {
			throw new IllegalArgumentException(ExceptionMessages.PLEASE_FILL);
		}
		List<Integer> lengthList = new ArrayList<Integer>(
				data.size());
		for (int i = 0; i < data.size(); i++) {
			lengthList.add(data.get(i).size());
		}
		init(data.size(), Collections.max(lengthList));
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < data.get(i).size(); j++) {
				this.data[i][j] = data.get(i).get(j);
			}
		}
		System.gc();
	}

	@SuppressWarnings("unchecked")
	public void copy(IDataMatrix<Data> matrix) throws NullPointerException {
		this.setData((Data[][]) matrix.getData());
		System.gc();
	}

	@SuppressWarnings("unchecked")
	public void copy(Data[]... data) throws NullPointerException {
		this.setData(data);
		System.gc();
	}

	public void addLine() {
		resize(this.linedimension + 1, this.columndimension);
	}

	@SuppressWarnings("unchecked")
	public void addLine(Data... lineArray) throws NullPointerException {
		addLine();
		setLine(this.linedimension - 1, lineArray);
	}

	public void addLine(Data defaultvalue) {
		addLine();
		setLine(this.linedimension - 1, defaultvalue);
	}

	public void addLine(List<Data> lineList)
			throws NullPointerException {
		addLine();
		setLine(this.linedimension - 1, lineList);
	}

	public void removeLine() throws IllegalArgumentException {
		if (this.linedimension - 1 <= 0) {
			throw new IllegalArgumentException(ExceptionMessages.ONLY_ONE_LINE);
		}
		resize(this.linedimension - 1, this.columndimension);
	}

	public void addColumn() {
		resize(this.linedimension, this.columndimension + 1);
	}

	@SuppressWarnings("unchecked")
	public void addColumn(Data... columnArray) throws NullPointerException {
		addColumn();
		setColumn(this.columndimension - 1, columnArray);
	}

	public void addColumn(Data defaultvalue) {
		addColumn();
		setColumn(this.columndimension - 1, defaultvalue);
	}

	public void addColumn(List<Data> columnList)
			throws NullPointerException {
		addColumn();
		setColumn(this.columndimension - 1, columnList);
	}

	public void removeColumn() throws IllegalArgumentException {
		if (this.columndimension - 1 <= 0) {
			throw new IllegalArgumentException(
					ExceptionMessages.ONLY_ONE_COLUMN);
		}
		resize(this.linedimension, this.columndimension - 1);
	}

	public void insertLine(int index) {
		if (index < 0) {
			DataMatrix<Data> tempDataMatrix = new DataMatrix<Data>(
					(index * -1), columndimension);
			tempDataMatrix.addMatrixAtLine(this);
			changeContentQuickly(tempDataMatrix);
			return;
		}
		int addlineindex = 1;
		if (index > this.linedimension)
			addlineindex = index - (this.linedimension - 1);
		DataMatrix<Data> tempDataMatrix = new DataMatrix<Data>(
				this.linedimension + addlineindex, this.columndimension);
		for (int i = 0; i < this.linedimension; i++)
			for (int j = 0; j < tempDataMatrix.columndimension; j++) {
				if (i >= index) {
					tempDataMatrix.setValue(i + 1, j, this.getValue(i, j));
					continue;
				}
				tempDataMatrix.setValue(i, j, this.getValue(i, j));
			}
		changeContentQuickly(tempDataMatrix);
	}

	public void insertLine(int index, Data defaultValue) {
		int oldLineDimension = this.linedimension;
		insertLine(index);
		if (index >= 0) {
			setLine(index, defaultValue);
		}
		fillLeftLines(oldLineDimension, index, defaultValue);
	}

	@SuppressWarnings("unchecked")
	public void insertLine(int index, Data... lineArray)
			throws NullPointerException {
		int oldLineDimension = this.linedimension;
		insertLine(index);
		if (index >= 0) {
			setLine(index, lineArray);
		}
		fillLeftLines(oldLineDimension, index, lineArray);
	}

	public void insertLine(int index, List<Data> lineList)
			throws NullPointerException {
		int oldLineDimension = this.linedimension;
		insertLine(index);
		if (index >= 0) {
			setLine(index, lineList);
		}
		fillLeftLines(oldLineDimension, index, lineList);
	}

	public void removeLine(int index) throws ArrayIndexOutOfBoundsException,
			IllegalArgumentException {
		checkLineIndex(index);
		if (this.getLineDimension() - 1 <= 0) {
			throw new IllegalArgumentException(ExceptionMessages.ONLY_ONE_LINE);
		}
		DataMatrix<Data> tempDataMatrix = new DataMatrix<Data>(
				this.getLineDimension() - 1, this.getColumnDimension());
		for (int i = 0; i < tempDataMatrix.getLineDimension(); i++)
			for (int j = 0; j < tempDataMatrix.getColumnDimension(); j++) {
				if (i >= index) {
					tempDataMatrix.setValue(i, j, this.getValue(i + 1, j));
					continue;
				}
				tempDataMatrix.setValue(i, j, this.getValue(i, j));
			}
		changeContentQuickly(tempDataMatrix);
	}

	public void insertColumn(int index) {
		if (index < 0) {
			DataMatrix<Data> tempDataMatrix = new DataMatrix<Data>(
					linedimension, (index * -1));
			tempDataMatrix.addMatrixAtcolumn(this);
			changeContentQuickly(tempDataMatrix);
			return;
		}
		int addcolumnindex = 1;
		if (index > this.getColumnDimension())
			addcolumnindex = index - (this.getColumnDimension() - 1);
		DataMatrix<Data> tempDataMatrix = new DataMatrix<Data>(
				this.getLineDimension(), this.getColumnDimension()
						+ addcolumnindex);
		for (int i = 0; i < tempDataMatrix.getLineDimension(); i++)
			for (int j = 0; j < this.getColumnDimension(); j++) {
				if (j >= index) {
					tempDataMatrix.setValue(i, j + 1, this.getValue(i, j));
					continue;
				}
				tempDataMatrix.setValue(i, j, this.getValue(i, j));
			}
		changeContentQuickly(tempDataMatrix);
	}

	public void insertColumn(int index, Data defaultValue) {
		int oldColumnDimension = this.columndimension;
		insertColumn(index);
		if (index >= 0) {
			setColumn(index, defaultValue);
		}
		fillLeftColumns(oldColumnDimension, index, defaultValue);
	}

	@SuppressWarnings("unchecked")
	public void insertColumn(int index, Data... columnArray)
			throws NullPointerException {
		int oldColumnDimension = this.columndimension;
		insertColumn(index);
		if (index >= 0) {
			setColumn(index, columnArray);
		}
		fillLeftColumns(oldColumnDimension, index, columnArray);
	}

	public void insertColumn(int index, List<Data> columnList)
			throws NullPointerException {
		int oldColumnDimension = this.columndimension;
		insertColumn(index);
		if (index >= 0) {
			setColumn(index, columnList);
		}
		fillLeftColumns(oldColumnDimension, index, columnList);
	}

	public void removeColumn(int index) throws ArrayIndexOutOfBoundsException,
			IllegalArgumentException {
		checkColumnIndex(index);
		if (this.getColumnDimension() - 1 <= 0) {
			throw new IllegalArgumentException(
					ExceptionMessages.ONLY_ONE_COLUMN);
		}
		DataMatrix<Data> tempDataMatrix = new DataMatrix<Data>(
				this.getLineDimension(), (this.getColumnDimension() - 1));
		for (int i = 0; i < tempDataMatrix.getLineDimension(); i++)
			for (int j = 0; j < tempDataMatrix.getColumnDimension(); j++) {
				if (j >= index) {
					tempDataMatrix.setValue(i, j, this.getValue(i, j + 1));
					continue;
				}
				tempDataMatrix.setValue(i, j, this.getValue(i, j));
			}
		changeContentQuickly(tempDataMatrix);
	}

	public void insertElement(int lineIndex, int columnIndex, Data value) {
		if (lineIndex >= this.getLineDimension()
				&& columnIndex < this.getColumnDimension()) {
			this.insertLine(lineIndex);
			this.setValue(Math.abs(lineIndex), Math.abs(columnIndex), value);
			return;
		}
		if (lineIndex < this.getLineDimension()
				&& columnIndex >= this.getColumnDimension()) {
			this.insertColumn(columnIndex);
			this.setValue(Math.abs(lineIndex), Math.abs(columnIndex), value);
			return;
		}
		this.insertLine(lineIndex);
		this.insertColumn(columnIndex);
		this.setValue(Math.abs(lineIndex), Math.abs(columnIndex), value);
	}

	public void insertElement(int lineIndex, int columnIndex, Data value,
			Data defaultValue) {
		if (lineIndex >= this.getLineDimension()
				&& columnIndex < this.getColumnDimension()) {
			this.insertLine(lineIndex, defaultValue);
			this.setValue(Math.abs(lineIndex), Math.abs(columnIndex), value);
			return;
		}
		if (lineIndex < this.getLineDimension()
				&& columnIndex >= this.getColumnDimension()) {
			this.insertColumn(columnIndex, defaultValue);
			this.setValue(Math.abs(lineIndex), Math.abs(columnIndex), value);
			return;
		}
		this.insertLine(lineIndex, defaultValue);
		this.insertColumn(columnIndex, defaultValue);
		this.setValue(Math.abs(lineIndex), Math.abs(columnIndex), value);
	}

	public void insertElement(int lineIndex, int columnIndex, Data value,
			Data[] lineArray, Data[] columnArray) throws NullPointerException {
		if (lineIndex >= this.getLineDimension()
				&& columnIndex < this.getColumnDimension()) {
			this.insertLine(lineIndex, lineArray);
			this.setValue(Math.abs(lineIndex), Math.abs(columnIndex), value);
			return;
		}
		if (lineIndex < this.getLineDimension()
				&& columnIndex >= this.getColumnDimension()) {
			this.insertColumn(columnIndex, columnArray);
			this.setValue(Math.abs(lineIndex), Math.abs(columnIndex), value);
			return;
		}
		this.insertLine(lineIndex, lineArray);
		this.insertColumn(columnIndex, columnArray);
		this.setValue(Math.abs(lineIndex), Math.abs(columnIndex), value);
	}

	public void insertElement(int lineIndex, int columnIndex, Data value,
			List<Data> lineList, List<Data> columnList)
			throws NullPointerException {
		if (lineIndex >= this.getLineDimension()
				&& columnIndex < this.getColumnDimension()) {
			this.insertLine(lineIndex, lineList);
			this.setValue(Math.abs(lineIndex), Math.abs(columnIndex), value);
			return;
		}
		if (lineIndex < this.getLineDimension()
				&& columnIndex >= this.getColumnDimension()) {
			this.insertColumn(columnIndex, columnList);
			this.setValue(Math.abs(lineIndex), Math.abs(columnIndex), value);
			return;
		}
		this.insertLine(lineIndex, lineList);
		this.insertColumn(columnIndex, columnList);
		this.setValue(Math.abs(lineIndex), Math.abs(columnIndex), value);
	}

	public void removeElement(int lineIndex, int columnIndex)
			throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
		checkIndexes(lineIndex, columnIndex);
		this.removeLine(lineIndex);
		this.removeColumn(columnIndex);
	}

	@SuppressWarnings("unchecked")
	public void insertMatrix(int lineIndex, int columnIndex,
			IDataMatrix<Data> matrix) throws IllegalArgumentException,
			NullPointerException {
		insertData(lineIndex, columnIndex, (Data[][]) matrix.getData());
	}

	@SuppressWarnings("unchecked")
	public void insertData(int lineIndex, int columnIndex, Data[]... data)
			throws IllegalArgumentException, NullPointerException {
		if (lineIndex < 0 || columnIndex < 0) {
			throw new IllegalArgumentException(ExceptionMessages.NON_NEGATIVE);
		}
		int newLineDimension = this.linedimension;
		int newColumnDimension = this.columndimension;
		DataMatrix<Data> tempDataMatrix = null;
		List<Integer> sizeList = new ArrayList<Integer>(
				data.length);
		for (int i = 0; i < data.length; i++) {
			sizeList.add(data[i].length);
		}
		int parameterMatrixMaxColumnDimension = Collections
				.max(sizeList);

		if (isIndexes(lineIndex, columnIndex)) {
			newLineDimension += data.length;
			newColumnDimension += parameterMatrixMaxColumnDimension;
			tempDataMatrix = new DataMatrix<Data>(newLineDimension,
					newColumnDimension);
			for (int i = 0; i < lineIndex; i++) {
				for (int j = 0; j < columnIndex; j++) {
					tempDataMatrix.data[i][j] = this.data[i][j];
				}
			}
			for (int i = lineIndex + data.length, helpi = 0; i < tempDataMatrix.linedimension; i++, helpi++) {
				for (int j = 0; j < columnIndex; j++) {
					tempDataMatrix.data[i][j] = this.data[helpi][j];
				}
			}
			for (int i = 0; i < lineIndex; i++) {
				for (int j = columnIndex + parameterMatrixMaxColumnDimension, helpj = 0; j < tempDataMatrix.columndimension; j++, helpj++) {
					tempDataMatrix.data[i][j] = this.data[i][helpj];
				}
			}
			for (int i = lineIndex + data.length, helpi = 0; i < tempDataMatrix.linedimension; i++, helpi++) {
				for (int j = columnIndex + parameterMatrixMaxColumnDimension, helpj = 0; j < tempDataMatrix.columndimension; j++, helpj++) {
					tempDataMatrix.data[i][j] = this.data[helpi][helpj];
				}
			}
			fillTempDataMatrixWithGivenDataOnGivenIndexes(tempDataMatrix, data,
					lineIndex, columnIndex);
			changeContentQuickly(tempDataMatrix);
			return;
		} else if (isLineIndex(lineIndex)) {
			newLineDimension = Math.max(lineIndex + data.length,
					this.linedimension);
			newColumnDimension = columnIndex
					+ parameterMatrixMaxColumnDimension;
		} else if (isColumnIndex(columnIndex)) {
			newLineDimension = lineIndex + data.length;
			newColumnDimension = Math.max(columnIndex
					+ parameterMatrixMaxColumnDimension, this.columndimension);
		} else if (!isIndexes(lineIndex, columnIndex)) {
			newLineDimension = lineIndex + data.length;
			newColumnDimension = columnIndex
					+ parameterMatrixMaxColumnDimension;
		}
		tempDataMatrix = new DataMatrix<Data>(newLineDimension,
				newColumnDimension);
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				tempDataMatrix.data[i][j] = this.data[i][j];
			}
		}
		fillTempDataMatrixWithGivenDataOnGivenIndexes(tempDataMatrix, data,
				lineIndex, columnIndex);
		changeContentQuickly(tempDataMatrix);
	}

	public void insertData(int lineIndex, int columnIndex,
			List<List<Data>> data)
			throws IllegalArgumentException, NullPointerException {
		if (lineIndex < 0 || columnIndex < 0) {
			throw new IllegalArgumentException(ExceptionMessages.NON_NEGATIVE);
		}
		int newLineDimension = this.linedimension;
		int newColumnDimension = this.columndimension;
		DataMatrix<Data> tempDataMatrix = null;
		List<Integer> sizeList = new ArrayList<Integer>(
				data.size());
		for (int i = 0; i < data.size(); i++) {
			sizeList.add(data.get(i).size());
		}
		int parameterMatrixMaxColumnDimension = Collections
				.max(sizeList);

		if (isIndexes(lineIndex, columnIndex)) {
			newLineDimension += data.size();
			newColumnDimension += parameterMatrixMaxColumnDimension;
			tempDataMatrix = new DataMatrix<Data>(newLineDimension,
					newColumnDimension);
			for (int i = 0; i < lineIndex; i++) {
				for (int j = 0; j < columnIndex; j++) {
					tempDataMatrix.data[i][j] = this.data[i][j];
				}
			}
			for (int i = lineIndex + data.size(), helpi = 0; i < tempDataMatrix.linedimension; i++, helpi++) {
				for (int j = 0; j < columnIndex; j++) {
					tempDataMatrix.data[i][j] = this.data[helpi][j];
				}
			}
			for (int i = 0; i < lineIndex; i++) {
				for (int j = columnIndex + parameterMatrixMaxColumnDimension, helpj = 0; j < tempDataMatrix.columndimension; j++, helpj++) {
					tempDataMatrix.data[i][j] = this.data[i][helpj];
				}
			}
			for (int i = lineIndex + data.size(), helpi = 0; i < tempDataMatrix.linedimension; i++, helpi++) {
				for (int j = columnIndex + parameterMatrixMaxColumnDimension, helpj = 0; j < tempDataMatrix.columndimension; j++, helpj++) {
					tempDataMatrix.data[i][j] = this.data[helpi][helpj];
				}
			}
			fillTempDataMatrixWithGivenDataOnGivenIndexes(tempDataMatrix, data,
					lineIndex, columnIndex);
			changeContentQuickly(tempDataMatrix);
			return;
		} else if (isLineIndex(lineIndex)) {
			newLineDimension = Math.max(lineIndex + data.size(),
					this.linedimension);
			newColumnDimension = columnIndex
					+ parameterMatrixMaxColumnDimension;
		} else if (isColumnIndex(columnIndex)) {
			newLineDimension = lineIndex + data.size();
			newColumnDimension = Math.max(columnIndex
					+ parameterMatrixMaxColumnDimension, this.columndimension);
		} else if (!isIndexes(lineIndex, columnIndex)) {
			newLineDimension = lineIndex + data.size();
			newColumnDimension = columnIndex
					+ parameterMatrixMaxColumnDimension;
		}
		tempDataMatrix = new DataMatrix<Data>(newLineDimension,
				newColumnDimension);
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				tempDataMatrix.data[i][j] = this.data[i][j];
			}
		}
		fillTempDataMatrixWithGivenDataOnGivenIndexes(tempDataMatrix, data,
				lineIndex, columnIndex);
		changeContentQuickly(tempDataMatrix);
	}

	public Object[] getLine(int lineIndex)
			throws ArrayIndexOutOfBoundsException {
		checkLineIndex(lineIndex);
		Object[] newarray = new Object[this.getColumnDimension()];
		for (int i = 0; i < this.getColumnDimension(); i++)
			newarray[i] = this.getValue(lineIndex, i);
		return newarray;
	}

	public List<Data> getLineAsList(int lineIndex)
			throws ArrayIndexOutOfBoundsException {
		return toParameterizedList(getLine(lineIndex));
	}

	@SuppressWarnings("unchecked")
	public void setLine(int lineIndex, Data... lineArray)
			throws ArrayIndexOutOfBoundsException, NullPointerException {
		checkLineIndex(lineIndex);
		for (int i = 0; i < Math.min(this.columndimension, lineArray.length); i++) {
			data[lineIndex][i] = lineArray[i];
		}
	}

	public void setLine(int lineIndex, List<Data> lineList)
			throws ArrayIndexOutOfBoundsException, NullPointerException {
		checkLineIndex(lineIndex);
		for (int i = 0; i < Math.min(this.columndimension, lineList.size()); i++) {
			data[lineIndex][i] = lineList.get(i);
		}
	}

	public void setLine(int lineIndex, Data value)
			throws ArrayIndexOutOfBoundsException {
		checkLineIndex(lineIndex);
		for (int i = 0; i < this.columndimension; i++) {
			data[lineIndex][i] = value;
		}
	}

	public Object[] getColumn(int columnIndex)
			throws ArrayIndexOutOfBoundsException {
		checkColumnIndex(columnIndex);
		Object[] columnarray = new Object[this.getLineDimension()];
		for (int i = 0; i < this.getLineDimension(); i++)
			columnarray[i] = data[i][columnIndex];
		return columnarray;
	}

	public List<Data> getColumnAsList(int columnIndex)
			throws ArrayIndexOutOfBoundsException {
		return toParameterizedList(getColumn(columnIndex));
	}

	@SuppressWarnings("unchecked")
	public void setColumn(int columnIndex, Data... columnArray)
			throws ArrayIndexOutOfBoundsException, NullPointerException {
		checkColumnIndex(columnIndex);
		for (int i = 0; i < Math.min(this.linedimension, columnArray.length); i++)
			data[i][columnIndex] = columnArray[i];
	}

	public void setColumn(int columnIndex, List<Data> columnList)
			throws ArrayIndexOutOfBoundsException, NullPointerException {
		checkColumnIndex(columnIndex);
		for (int i = 0; i < Math.min(this.linedimension, columnList.size()); i++)
			data[i][columnIndex] = columnList.get(i);
	}

	public void setColumn(int columnIndex, Data value)
			throws ArrayIndexOutOfBoundsException {
		checkColumnIndex(columnIndex);
		for (int i = 0; i < this.linedimension; i++) {
			data[i][columnIndex] = value;
		}
	}

	public void addMatrixAtLine(IDataMatrix<Data> matrix)
			throws NullPointerException {
		DataMatrix<Data> tempDataMatrix = new DataMatrix<Data>(
				this.getLineDimension() + matrix.getLineDimension(),
				this.getColumnDimension());

		for (int i = 0; i < this.getLineDimension(); i++) {
			for (int j = 0; j < this.getColumnDimension(); j++) {
				tempDataMatrix.setValue(i, j, this.getValue(i, j));
			}
		}

		int iindex = 0, jindex = 0;
		if (this.getColumnDimension() >= matrix.getColumnDimension()) {
			for (int i = this.getLineDimension(); i < tempDataMatrix
					.getLineDimension(); i++) {
				for (int j = 0; j < matrix.getColumnDimension(); j++) {
					tempDataMatrix.setValue(i, j,
							matrix.getValue(iindex, jindex));
					jindex++;
				}
				iindex++;
				jindex = 0;
			}
		} else {
			for (int i = this.getLineDimension(); i < tempDataMatrix
					.getLineDimension(); i++) {
				for (int j = 0; j < this.getColumnDimension(); j++) {
					tempDataMatrix.setValue(i, j,
							matrix.getValue(iindex, jindex));
					jindex++;
				}
				iindex++;
				jindex = 0;
			}
		}
		changeContentQuickly(tempDataMatrix);
	}

	public void addMatrixAtLine(IDataMatrix<Data> matrix, Data defaultValue)
			throws NullPointerException {
		DataMatrix<Data> tempDataMatrix = new DataMatrix<Data>(
				this.getLineDimension() + matrix.getLineDimension(),
				this.getColumnDimension());

		for (int i = 0; i < this.getLineDimension(); i++) {
			for (int j = 0; j < this.getColumnDimension(); j++) {
				tempDataMatrix.setValue(i, j, this.getValue(i, j));
			}
		}

		int iindex = 0, jindex = 0;
		if (this.getColumnDimension() >= matrix.getColumnDimension()) {
			for (int i = this.getLineDimension(); i < tempDataMatrix
					.getLineDimension(); i++) {
				for (int j = 0; j < matrix.getColumnDimension(); j++) {
					tempDataMatrix.setValue(i, j,
							matrix.getValue(iindex, jindex));
					jindex++;
				}
				iindex++;
				jindex = 0;
			}
			for (int i = this.getLineDimension(); i < tempDataMatrix
					.getLineDimension(); i++) {
				for (int j = matrix.getColumnDimension(); j < this
						.getColumnDimension(); j++) {
					tempDataMatrix.setValue(i, j, defaultValue);
				}
			}
		} else {
			for (int i = this.getLineDimension(); i < tempDataMatrix
					.getLineDimension(); i++) {
				for (int j = 0; j < this.getColumnDimension(); j++) {
					tempDataMatrix.setValue(i, j,
							matrix.getValue(iindex, jindex));
					jindex++;
				}
				iindex++;
				jindex = 0;
			}
		}
		changeContentQuickly(tempDataMatrix);
	}

	public void addMatrixAtcolumn(IDataMatrix<Data> matrix)
			throws NullPointerException {
		DataMatrix<Data> tempDataMatrix = new DataMatrix<Data>(
				this.getLineDimension(), this.getColumnDimension()
						+ matrix.getColumnDimension());

		for (int i = 0; i < this.getLineDimension(); i++) {
			for (int j = 0; j < this.getColumnDimension(); j++) {
				tempDataMatrix.setValue(i, j, this.getValue(i, j));
			}
		}

		int iindex = 0, jindex = 0;
		if (this.getLineDimension() >= matrix.getLineDimension()) {
			for (int i = 0; i < matrix.getLineDimension(); i++) {
				for (int j = this.getColumnDimension(); j < tempDataMatrix
						.getColumnDimension(); j++) {
					tempDataMatrix.setValue(i, j,
							matrix.getValue(iindex, jindex));
					jindex++;
				}
				iindex++;
				jindex = 0;
			}
		} else {
			for (int i = 0; i < this.getLineDimension(); i++) {
				for (int j = this.getColumnDimension(); j < tempDataMatrix
						.getColumnDimension(); j++) {
					tempDataMatrix.setValue(i, j,
							matrix.getValue(iindex, jindex));
					jindex++;
				}
				iindex++;
				jindex = 0;
			}
		}
		changeContentQuickly(tempDataMatrix);
	}

	public void addMatrixAtcolumn(IDataMatrix<Data> matrix, Data defaultValue)
			throws NullPointerException {
		DataMatrix<Data> tempDataMatrix = new DataMatrix<Data>(
				this.getLineDimension(), this.getColumnDimension()
						+ matrix.getColumnDimension());

		for (int i = 0; i < this.getLineDimension(); i++) {
			for (int j = 0; j < this.getColumnDimension(); j++) {
				tempDataMatrix.setValue(i, j, this.getValue(i, j));
			}
		}

		int iindex = 0, jindex = 0;
		if (this.getLineDimension() >= matrix.getLineDimension()) {
			for (int i = 0; i < matrix.getLineDimension(); i++) {
				for (int j = this.getColumnDimension(); j < tempDataMatrix
						.getColumnDimension(); j++) {
					tempDataMatrix.setValue(i, j,
							matrix.getValue(iindex, jindex));
					jindex++;
				}
				iindex++;
				jindex = 0;
			}
			for (int i = matrix.getLineDimension(); i < this.getLineDimension(); i++) {
				for (int j = this.getColumnDimension(); j < tempDataMatrix
						.getColumnDimension(); j++) {
					tempDataMatrix.setValue(i, j, defaultValue);
				}
			}
		} else {
			for (int i = 0; i < this.getLineDimension(); i++) {
				for (int j = this.getColumnDimension(); j < tempDataMatrix
						.getColumnDimension(); j++) {
					tempDataMatrix.setValue(i, j,
							matrix.getValue(iindex, jindex));
					jindex++;
				}
				iindex++;
				jindex = 0;
			}
		}
		changeContentQuickly(tempDataMatrix);
	}

	public void swapLines(int lineIndex_1, int lineIndex_2)
			throws ArrayIndexOutOfBoundsException {
		if (lineIndex_1 == lineIndex_2)
			return;
		checkLineIndex(lineIndex_1);
		checkLineIndex(lineIndex_2);
		Data[] helpArray = this.data[lineIndex_1];
		this.data[lineIndex_1] = this.data[lineIndex_2];
		this.data[lineIndex_2] = helpArray;
	}

	public void swapColumns(int columnIndex_1, int columnIndex_2)
			throws ArrayIndexOutOfBoundsException {
		if (columnIndex_1 == columnIndex_2)
			return;
		checkColumnIndex(columnIndex_1);
		checkColumnIndex(columnIndex_2);
		@SuppressWarnings("unchecked")
		Data[] helpArray = (Data[]) new Object[this.getLineDimension()];
		for (int i = 0; i < this.getLineDimension(); i++)
			helpArray[i] = this.getValue(i, columnIndex_2);
		for (int i = 0; i < this.getLineDimension(); i++)
			this.setValue(i, columnIndex_2, this.getValue(i, columnIndex_1));
		for (int i = 0; i < this.getLineDimension(); i++)
			this.setValue(i, columnIndex_1, helpArray[i]);
		helpArray = null;
		System.gc();
	}

	public void swap(IDataMatrix<Data> matrix) throws NullPointerException {
		DataMatrix<Data> tempDataMatrix = new DataMatrix<Data>(matrix);
		matrix.copy(this);
		changeContentQuickly(tempDataMatrix);
		String tempId = new String(this.id);
		this.id = new String(matrix.getId());
		matrix.setId(tempId);
		String tempName = new String(this.name);
		this.name = new String(matrix.getName());
		matrix.setName(tempName);
	}

	public void swapData(IDataMatrix<Data> matrix) throws NullPointerException {
		DataMatrix<Data> tempDataMatrix = new DataMatrix<Data>(matrix);
		matrix.copy(this);
		changeContentQuickly(tempDataMatrix);
	}

	public void swapData(int lineIndex_1, int columnIndex_1, int lineIndex_2,
			int columnIndex_2) throws ArrayIndexOutOfBoundsException {
		if (lineIndex_1 == lineIndex_2 && columnIndex_1 == columnIndex_2)
			return;
		checkIndexes(lineIndex_1, columnIndex_1);
		checkIndexes(lineIndex_2, columnIndex_2);
		Data tempdata = null;
		tempdata = this.data[lineIndex_1][columnIndex_1];
		this.data[lineIndex_1][columnIndex_1] = this.data[lineIndex_2][columnIndex_2];
		this.data[lineIndex_2][columnIndex_2] = tempdata;
		tempdata = null;
	}

	public void swapData(Data data_1, Data data_2)
			throws IllegalArgumentException, NullPointerException {
		if (data_1 == null || data_2 == null)
			throw new NullPointerException();
		List<Integer> dataindexes_1 = new ArrayList<Integer>();
		List<Integer> dataindexes_2 = new ArrayList<Integer>();
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				if (this.data[i][j].equals(data_1)) {
					dataindexes_1.add(i);
					dataindexes_1.add(j);
				} else if (this.data[i][j].equals(data_2)) {
					dataindexes_2.add(i);
					dataindexes_2.add(j);
				}
			}
		}
		if (dataindexes_1.size() == 0 || dataindexes_2.size() == 0) {
			throw new IllegalArgumentException(ExceptionMessages.SWAPDATA);
		}
		Data tempData = null;
		tempData = this.data[dataindexes_1.get(0)][dataindexes_1.get(1)];
		this.data[dataindexes_1.get(0)][dataindexes_1.get(1)] = this.data[dataindexes_2
				.get(0)][dataindexes_2.get(1)];
		this.data[dataindexes_2.get(0)][dataindexes_2.get(1)] = tempData;
		setNull(tempData, dataindexes_1, dataindexes_2);
	}

	public void setMainDiagonal(Data value) {
		for (int i = 0; i < this.getMinDimension(); i++) {
			data[i][i] = value;
		}
	}

	@SuppressWarnings("unchecked")
	public void setMainDiagonal(Data... diagonalArray)
			throws NullPointerException {
		for (int i = 0; i < Math.min(this.getMinDimension(),
				diagonalArray.length); i++) {
			data[i][i] = diagonalArray[i];
		}
	}

	public void setMainDiagonal(List<Data> diagonalList)
			throws NullPointerException {
		for (int i = 0; i < Math.min(this.getMinDimension(),
				diagonalList.size()); i++) {
			data[i][i] = diagonalList.get(i);
		}
	}

	public void setDiagonal(int index, Data value)
			throws ArrayIndexOutOfBoundsException {
		if (index >= 0 && index < this.getColumnDimension()) {
			for (int i = 0; i < this.getLineDimension(); i++) {
				if (this.isIndexes(i, index + i)) {
					this.setValue(i, index + i, value);
				}
			}
			return;
		}
		if (index < 0 && index > (this.getLineDimension() * (-1))) {
			index *= -1;
			for (int i = 0; i < this.getLineDimension(); i++) {
				if (this.isIndexes(index + i, i)) {
					this.setValue(index + i, i, value);
				}
			}
			return;
		}
		throw new ArrayIndexOutOfBoundsException(ExceptionMessages.INDEX);
	}

	public void setDiagonal2(int index, Data value)
			throws ArrayIndexOutOfBoundsException {
		if (index >= 0 && index < this.getColumnDimension()) {
			for (int i = 0; i < this.getLineDimension(); i++) {
				if (this.isIndexes(i, this.getColumnDimension() - 1 - index - i)) {
					this.setValue(i, this.getColumnDimension() - 1 - index - i,
							value);
				}
			}
			return;
		}
		if (index < 0 && index > (this.getLineDimension() * (-1))) {
			index *= -1;
			for (int i = 0; i < this.getLineDimension(); i++) {
				if (this.isIndexes(index + i, this.getColumnDimension() - 1 - i)) {
					this.setValue(index + i, this.getColumnDimension() - 1 - i,
							value);
				}
			}
			return;
		}
		throw new ArrayIndexOutOfBoundsException(ExceptionMessages.INDEX);
	}

	public void setSecondDiagonal(Data value) {
		for (int i = 0; i < this.getMinDimension(); i++) {
			this.setValue(i, this.getColumnDimension() - 1 - i, value);
		}
	}

	@SuppressWarnings("unchecked")
	public void setSecondDiagonal(Data... diagonalArray)
			throws NullPointerException {
		for (int i = 0; i < Math.min(this.getMinDimension(),
				diagonalArray.length); i++) {
			this.setValue(i, this.getColumnDimension() - 1 - i,
					diagonalArray[i]);
		}
	}

	public void setSecondDiagonal(List<Data> diagonalList)
			throws NullPointerException {
		for (int i = 0; i < Math.min(this.getMinDimension(),
				diagonalList.size()); i++) {
			this.setValue(i, this.getColumnDimension() - 1 - i,
					diagonalList.get(i));
		}
	}

	public List<Data> getDiagonal(int index)
			throws ArrayIndexOutOfBoundsException {
		List<Data> diag = new ArrayList<Data>();
		if (index >= 0 && index < this.getColumnDimension()) {
			for (int i = 0; i < this.getLineDimension(); i++) {
				if (this.isIndexes(i, index + i)) {
					diag.add(this.getValue(i, index + i));
				}
			}
			return diag;
		}
		if (index < 0 && index > (this.getLineDimension() * (-1))) {
			index *= -1;
			for (int i = 0; i < this.getLineDimension(); i++) {
				if (this.isIndexes(index + i, i)) {
					diag.add(this.getValue(index + i, i));
				}
			}
			return diag;
		}
		throw new ArrayIndexOutOfBoundsException(ExceptionMessages.INDEX);
	}

	public List<Data> getDiagonal2(int index)
			throws ArrayIndexOutOfBoundsException {
		List<Data> diag = new ArrayList<Data>();
		if (index >= 0 && index < this.getColumnDimension()) {
			for (int i = 0; i < this.getLineDimension(); i++) {
				if (this.isIndexes(i, this.getColumnDimension() - 1 - index - i)) {
					diag.add(this.getValue(i, this.getColumnDimension() - 1
							- index - i));
				}
			}
			return diag;
		}
		if (index < 0 && index > (this.getLineDimension() * (-1))) {
			index *= -1;
			for (int i = 0; i < this.getLineDimension(); i++) {
				if (this.isIndexes(index + i, this.getColumnDimension() - 1 - i)) {
					diag.add(this.getValue(index + i, this.getColumnDimension()
							- 1 - i));
				}
			}
			return diag;
		}
		throw new ArrayIndexOutOfBoundsException(ExceptionMessages.INDEX);
	}

	public Object[] getMainDiagonal() {
		Object[] newArray = new Object[this.getMinDimension()];
		for (int i = 0; i < this.getMinDimension(); i++)
			newArray[i] = data[i][i];
		return newArray;
	}

	public List<Data> getMainDiagonalAsList() {
		return toParameterizedList(getMainDiagonal());
	}

	public Object[] getSecondDiagonal() {
		Object[] newArray = new Object[this.getMinDimension()];
		for (int i = 0; i < this.getMinDimension(); i++) {
			newArray[i] = this.getValue(i, this.getColumnDimension() - 1 - i);
		}
		return newArray;
	}

	public List<Data> getSecondDiagonalAsList() {
		return toParameterizedList(getSecondDiagonal());
	}

	public DataMatrix<Data> joinMatrixAtLine(IDataMatrix<Data> matrix)
			throws NullPointerException {
		DataMatrix<Data> newDataMatrix = new DataMatrix<Data>();
		if (this.getColumnDimension() >= matrix.getColumnDimension())
			newDataMatrix.resize(
					this.getLineDimension() + matrix.getLineDimension(),
					this.getColumnDimension());
		else
			newDataMatrix.resize(
					this.getLineDimension() + matrix.getLineDimension(),
					matrix.getColumnDimension());
		for (int i = 0; i < this.getLineDimension(); i++) {
			for (int j = 0; j < this.getColumnDimension(); j++) {
				newDataMatrix.setValue(i, j, this.getValue(i, j));
			}
		}
		int iindex = 0, jindex = 0;
		for (int i = this.getLineDimension(); i < newDataMatrix
				.getLineDimension(); i++) {
			for (int j = 0; j < matrix.getColumnDimension(); j++) {
				newDataMatrix.setValue(i, j, matrix.getValue(iindex, jindex));
				jindex++;
			}
			iindex++;
			jindex = 0;
		}
		return newDataMatrix;
	}

	public DataMatrix<Data> joinMatrixAtLine(IDataMatrix<Data> matrix,
			Data defaultValue) throws NullPointerException {
		DataMatrix<Data> newDataMatrix = new DataMatrix<Data>();
		if (this.getColumnDimension() >= matrix.getColumnDimension())
			newDataMatrix.resize(
					this.getLineDimension() + matrix.getLineDimension(),
					this.getColumnDimension());
		else
			newDataMatrix.resize(
					this.getLineDimension() + matrix.getLineDimension(),
					matrix.getColumnDimension());
		for (int i = 0; i < this.getLineDimension(); i++) {
			for (int j = 0; j < this.getColumnDimension(); j++) {
				newDataMatrix.setValue(i, j, this.getValue(i, j));
			}
		}
		int iindex = 0, jindex = 0;
		for (int i = this.getLineDimension(); i < newDataMatrix
				.getLineDimension(); i++) {
			for (int j = 0; j < matrix.getColumnDimension(); j++) {
				newDataMatrix.setValue(i, j, matrix.getValue(iindex, jindex));
				jindex++;
			}
			iindex++;
			jindex = 0;
		}
		if (this.getColumnDimension() > matrix.getColumnDimension()) {
			for (int i = this.getLineDimension(); i < newDataMatrix
					.getLineDimension(); i++) {
				for (int j = matrix.getColumnDimension(); j < newDataMatrix
						.getColumnDimension(); j++) {
					newDataMatrix.setValue(i, j, defaultValue);
				}
			}
		} else {
			for (int i = 0; i < this.getLineDimension(); i++) {
				for (int j = this.getColumnDimension(); j < newDataMatrix
						.getColumnDimension(); j++) {
					newDataMatrix.setValue(i, j, defaultValue);
				}
			}
		}
		return newDataMatrix;
	}

	public DataMatrix<Data> joinMatrixAtColumn(IDataMatrix<Data> matrix)
			throws NullPointerException {
		DataMatrix<Data> newDataMatrix = new DataMatrix<Data>();
		if (this.getLineDimension() >= matrix.getLineDimension()) {
			newDataMatrix.resize(this.getLineDimension(),
					this.getColumnDimension() + matrix.getColumnDimension());
		} else {
			newDataMatrix.resize(matrix.getLineDimension(),
					this.getColumnDimension() + matrix.getColumnDimension());
		}
		for (int i = 0; i < this.getLineDimension(); i++) {
			for (int j = 0; j < this.getColumnDimension(); j++) {
				newDataMatrix.setValue(i, j, this.getValue(i, j));
			}
		}
		int iindex = 0, jindex = 0;
		for (int i = 0; i < matrix.getLineDimension(); i++) {
			for (int j = this.getColumnDimension(); j < newDataMatrix
					.getColumnDimension(); j++) {
				newDataMatrix.setValue(i, j, matrix.getValue(iindex, jindex));
				jindex++;
			}
			iindex++;
			jindex = 0;
		}
		return newDataMatrix;
	}

	public DataMatrix<Data> joinMatrixAtColumn(IDataMatrix<Data> matrix,
			Data defaultValue) throws NullPointerException {
		DataMatrix<Data> newDataMatrix = new DataMatrix<Data>();
		if (this.getLineDimension() >= matrix.getLineDimension()) {
			newDataMatrix.resize(this.getLineDimension(),
					this.getColumnDimension() + matrix.getColumnDimension());
		} else {
			newDataMatrix.resize(matrix.getLineDimension(),
					this.getColumnDimension() + matrix.getColumnDimension());
		}
		for (int i = 0; i < this.getLineDimension(); i++) {
			for (int j = 0; j < this.getColumnDimension(); j++) {
				newDataMatrix.setValue(i, j, this.getValue(i, j));
			}
		}
		int iindex = 0, jindex = 0;
		for (int i = 0; i < matrix.getLineDimension(); i++) {
			for (int j = this.getColumnDimension(); j < newDataMatrix
					.getColumnDimension(); j++) {
				newDataMatrix.setValue(i, j, matrix.getValue(iindex, jindex));
				jindex++;
			}
			iindex++;
			jindex = 0;
		}
		if (this.getLineDimension() > matrix.getLineDimension()) {
			for (int i = matrix.getLineDimension(); i < newDataMatrix
					.getLineDimension(); i++) {
				for (int j = this.getColumnDimension(); j < newDataMatrix
						.getColumnDimension(); j++) {
					newDataMatrix.setValue(i, j, defaultValue);
				}
			}
		} else {
			for (int i = this.getLineDimension(); i < newDataMatrix
					.getLineDimension(); i++) {
				for (int j = 0; j < this.getColumnDimension(); j++) {
					newDataMatrix.setValue(i, j, defaultValue);
				}
			}
		}
		return newDataMatrix;
	}

	public DataMatrix<Data> subMatrix(int startLineIndex, int startColumnIndex,
			int endLineIndex, int endColumnIndex)
			throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
		checkIndexes(startLineIndex, startColumnIndex);
		checkIndexes(endLineIndex, endColumnIndex);
		checkForLess(endLineIndex, startLineIndex);
		checkForLess(endColumnIndex, startColumnIndex);
		DataMatrix<Data> subDataMatrix = new DataMatrix<Data>(endLineIndex
				- startLineIndex + 1, endColumnIndex - startColumnIndex + 1);
		for (int i = 0; i < subDataMatrix.linedimension; i++) {
			for (int j = 0; j < subDataMatrix.columndimension; j++) {
				subDataMatrix.data[i][j] = this.data[startLineIndex + i][startColumnIndex
						+ j];
			}
		}
		return subDataMatrix;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Data getMaxValue() throws NullPointerException, ClassCastException {
		checkIfImplementsComparable();
		List maxVariablesOfLines = new ArrayList(
				this.linedimension);
		for (int i = 0; i < this.linedimension; i++)
			maxVariablesOfLines.add(Collections
					.max(toList(this.data[i])));
		return (Data) Collections.max(maxVariablesOfLines);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Data getMaxValue(Comparator<Data> comparator)
			throws NullPointerException, ClassCastException {
		List maxVariablesOfLines = new ArrayList(
				this.linedimension);
		for (int i = 0; i < this.linedimension; i++) {
			maxVariablesOfLines.add(Collections.max(
					toList(this.data[i]), comparator));
		}
		return (Data) Collections
				.max(maxVariablesOfLines, comparator);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int[] getIndexesOfMaxValue() throws NullPointerException,
			ClassCastException {
		checkIfImplementsComparable();
		int[] maxIndexes = new int[2];
		List maxVariablesOfLines = new ArrayList(
				this.linedimension);
		for (int i = 0; i < this.linedimension; i++) {
			maxVariablesOfLines.add(Collections
					.max(toList(this.data[i])));
		}
		maxIndexes[0] = maxVariablesOfLines.indexOf(Collections
				.max(maxVariablesOfLines));
		List tempListForColumIndex = toList(this.data[maxIndexes[0]]);
		maxIndexes[1] = tempListForColumIndex.indexOf(Collections
				.max(tempListForColumIndex));
		return maxIndexes;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int[] getIndexesOfMaxValue(Comparator<Data> comparator)
			throws NullPointerException, ClassCastException {
		int[] maxIndexes = new int[2];
		List maxVariablesOfLines = new ArrayList(
				this.linedimension);
		for (int i = 0; i < this.linedimension; i++) {
			maxVariablesOfLines.add(Collections.max(
					toList(this.data[i]), comparator));
		}
		maxIndexes[0] = maxVariablesOfLines.indexOf(Collections.max(
				maxVariablesOfLines, comparator));
		List tempListForColumIndex = toList(this.data[maxIndexes[0]]);
		maxIndexes[1] = tempListForColumIndex.indexOf(Collections
				.max(tempListForColumIndex, comparator));
		return maxIndexes;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Data getMinValue() throws NullPointerException, ClassCastException {
		checkIfImplementsComparable();
		List minVariablesOfLines = new ArrayList(
				this.linedimension);
		for (int i = 0; i < this.linedimension; i++) {
			minVariablesOfLines.add(Collections
					.min(toList(this.data[i])));
		}
		return (Data) Collections.min(minVariablesOfLines);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Data getMinValue(Comparator<Data> comparator)
			throws NullPointerException, ClassCastException {
		List minVariablesOfLines = new ArrayList(
				this.linedimension);
		for (int i = 0; i < this.linedimension; i++) {
			minVariablesOfLines.add(Collections.min(
					toList(this.data[i]), comparator));
		}
		return (Data) Collections
				.min(minVariablesOfLines, comparator);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int[] getIndexesOfMinValue() throws NullPointerException,
			ClassCastException {
		checkIfImplementsComparable();
		int[] minIndexes = new int[2];
		List minVariablesOfLines = new ArrayList(
				this.linedimension);
		for (int i = 0; i < this.linedimension; i++) {
			minVariablesOfLines.add(Collections
					.min(toList(this.data[i])));
		}
		minIndexes[0] = minVariablesOfLines.indexOf(Collections
				.min(minVariablesOfLines));
		List tempListForColumIndex = toList(this.data[minIndexes[0]]);
		minIndexes[1] = tempListForColumIndex.indexOf(Collections
				.min(tempListForColumIndex));
		return minIndexes;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int[] getIndexesOfMinValue(Comparator<Data> comparator)
			throws NullPointerException, ClassCastException {
		int[] minIndexes = new int[2];
		List minVariablesOfLines = new ArrayList(
				this.linedimension);
		for (int i = 0; i < this.linedimension; i++) {
			minVariablesOfLines.add(Collections.min(
					toList(this.data[i]), comparator));
		}
		minIndexes[0] = minVariablesOfLines.indexOf(Collections.min(
				minVariablesOfLines, comparator));
		List tempListForColumIndex = toList(this.data[minIndexes[0]]);
		minIndexes[1] = tempListForColumIndex.indexOf(Collections
				.min(tempListForColumIndex, comparator));
		return minIndexes;
	}

	@SuppressWarnings("unchecked")
	public Data getMaxValueOfLine(int lineIndex)
			throws ArrayIndexOutOfBoundsException, ClassCastException,
			NullPointerException {
		checkLineIndex(lineIndex);
		checkIfImplementsComparable(this.data[lineIndex][0]);
		return (Data) Collections.max(toList(this.data[lineIndex]));
	}

	@SuppressWarnings("unchecked")
	public Data getMaxValueOfLine(int lineIndex,
			Comparator<Data> comparator)
			throws ArrayIndexOutOfBoundsException, NullPointerException,
			ClassCastException {
		checkLineIndex(lineIndex);
		return (Data) Collections.max(toList(this.data[lineIndex]),
				comparator);
	}

	@SuppressWarnings("unchecked")
	public Data getMinValueOfLine(int lineIndex)
			throws ArrayIndexOutOfBoundsException, ClassCastException,
			NullPointerException {
		checkLineIndex(lineIndex);
		checkIfImplementsComparable(this.data[lineIndex][0]);
		return (Data) Collections.min(toList(this.data[lineIndex]));
	}

	@SuppressWarnings("unchecked")
	public Data getMinValueOfLine(int lineIndex,
			Comparator<Data> comparator)
			throws ArrayIndexOutOfBoundsException, NullPointerException,
			ClassCastException {
		checkLineIndex(lineIndex);
		return (Data) Collections.min(toList(this.data[lineIndex]),
				comparator);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Data getMaxValueOfColumn(int columnIndex)
			throws ArrayIndexOutOfBoundsException, ClassCastException,
			NullPointerException {
		checkColumnIndex(columnIndex);
		checkIfImplementsComparable(this.data[0][columnIndex]);
		List tempColumnList = new ArrayList(
				this.columndimension);
		for (int i = 0; i < this.linedimension; i++) {
			tempColumnList.add(this.data[i][columnIndex]);
		}
		return (Data) Collections.max(tempColumnList);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Data getMaxValueOfColumn(int columnIndex,
			Comparator<Data> comparator)
			throws ArrayIndexOutOfBoundsException, NullPointerException,
			ClassCastException {
		checkColumnIndex(columnIndex);
		List tempColumnList = new ArrayList(
				this.columndimension);
		for (int i = 0; i < this.linedimension; i++) {
			tempColumnList.add(this.data[i][columnIndex]);
		}
		return (Data) Collections.max(tempColumnList, comparator);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Data getMinValueOfColumn(int columnIndex)
			throws ArrayIndexOutOfBoundsException, ClassCastException,
			NullPointerException {
		checkColumnIndex(columnIndex);
		checkIfImplementsComparable(this.data[0][columnIndex]);
		List tempColumnList = new ArrayList(
				this.columndimension);
		for (int i = 0; i < this.linedimension; i++) {
			tempColumnList.add(this.data[i][columnIndex]);
		}
		return (Data) Collections.min(tempColumnList);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Data getMinValueOfColumn(int columnIndex,
			Comparator<Data> comparator)
			throws ArrayIndexOutOfBoundsException, NullPointerException,
			ClassCastException {
		checkColumnIndex(columnIndex);
		List tempColumnList = new ArrayList(
				this.columndimension);
		for (int i = 0; i < this.linedimension; i++) {
			tempColumnList.add(this.data[i][columnIndex]);
		}
		return (Data) Collections.min(tempColumnList, comparator);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sortLineAsc(int index) throws ArrayIndexOutOfBoundsException,
			NullPointerException, ClassCastException {
		checkLineIndex(index);
		checkIfImplementsComparable(this.data[index][0]);
		List tempList = toList(this.data[index]);
		Collections.sort(tempList);
		for (int i = 0; i < this.columndimension; i++) {
			this.data[index][i] = (Data) tempList.get(i);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sortLine(int index, Comparator<Data> comparator)
			throws ArrayIndexOutOfBoundsException, NullPointerException,
			ClassCastException {
		checkLineIndex(index);
		List tempList = toList(this.data[index]);
		Collections.sort(tempList, comparator);
		for (int i = 0; i < this.columndimension; i++) {
			this.data[index][i] = (Data) tempList.get(i);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sortLineDesc(int index) throws ArrayIndexOutOfBoundsException,
			NullPointerException, ClassCastException {
		checkLineIndex(index);
		checkIfImplementsComparable(this.data[index][0]);
		List tempList = toList(this.data[index]);
		Collections.sort(tempList);
		Collections.reverse(tempList);
		for (int i = 0; i < this.columndimension; i++) {
			this.data[index][i] = (Data) tempList.get(i);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sortColumnAsc(int index) throws ArrayIndexOutOfBoundsException,
			NullPointerException, ClassCastException {
		checkColumnIndex(index);
		checkIfImplementsComparable(this.data[0][index]);
		List tempList = new ArrayList(this.columndimension);
		for (int i = 0; i < this.linedimension; i++) {
			tempList.add(this.data[i][index]);
		}
		Collections.sort(tempList);
		for (int i = 0; i < this.linedimension; i++) {
			this.data[i][index] = (Data) tempList.get(i);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sortColumn(int index, Comparator<Data> comparator)
			throws ArrayIndexOutOfBoundsException, NullPointerException,
			ClassCastException {
		checkColumnIndex(index);
		List tempList = new ArrayList(this.columndimension);
		for (int i = 0; i < this.linedimension; i++) {
			tempList.add(this.data[i][index]);
		}
		Collections.sort(tempList, comparator);
		for (int i = 0; i < this.linedimension; i++) {
			this.data[i][index] = (Data) tempList.get(i);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sortColumnDesc(int index)
			throws ArrayIndexOutOfBoundsException, NullPointerException,
			ClassCastException {
		checkColumnIndex(index);
		checkIfImplementsComparable(this.data[0][index]);
		List tempList = new ArrayList(this.columndimension);
		for (int i = 0; i < this.linedimension; i++) {
			tempList.add(this.data[i][index]);
		}
		Collections.sort(tempList);
		Collections.reverse(tempList);
		for (int i = 0; i < this.linedimension; i++) {
			this.data[i][index] = (Data) tempList.get(i);
		}
	}

	public void sortLinesAsc() throws NullPointerException, ClassCastException {
		for (int i = 0; i < this.linedimension; i++) {
			sortLineAsc(i);
		}
	}

	public void sortLines(Comparator<Data> comparator)
			throws NullPointerException, ClassCastException {
		for (int i = 0; i < this.linedimension; i++) {
			sortLine(i, comparator);
		}
	}

	public void sortLinesDesc() throws NullPointerException, ClassCastException {
		for (int i = 0; i < this.linedimension; i++) {
			sortLineDesc(i);
		}
	}

	public void sortColumnsAsc() throws NullPointerException,
			ClassCastException {
		for (int i = 0; i < this.columndimension; i++) {
			sortColumnAsc(i);
		}
	}

	public void sortColumns(Comparator<Data> comparator)
			throws NullPointerException, ClassCastException {
		for (int i = 0; i < this.columndimension; i++) {
			sortColumn(i, comparator);
		}
	}

	public void sortColumnsDesc() throws NullPointerException,
			ClassCastException {
		for (int i = 0; i < this.columndimension; i++) {
			sortColumnDesc(i);
		}
	}

	public List<Data> toList() {
		List<Data> list = new ArrayList<Data>();
		for (int i = 0; i < this.data.length; i++) {
			for (int j = 0; j < this.data[i].length; j++) {
				list.add(this.data[i][j]);
			}
		}
		return list;
	}

	public List<Data> toListByColumn() {
		List<Data> list = new ArrayList<Data>();
		for (int i = 0; i < this.columndimension; i++) {
			for (int j = 0; j < this.linedimension; j++) {
				list.add(this.data[j][i]);
			}
		}
		return list;
	}

	public void fillEmptyPlaces(Data data) throws NullPointerException {
		if (data == null)
			throw new NullPointerException();
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				if (this.data[i][j] == null) {
					this.data[i][j] = data;
				}
			}
		}
	}

	public void fillEmptyPlaces() throws NullPointerException {
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				if (this.data[i][j] != null) {
					fillEmptyPlaces(this.data[i][j]);
					return;
				}
			}
		}
		throw new NullPointerException(ExceptionMessages.EVERYTHING_IS_NULL);
	}

	public List<Integer> getLineIndexesOfEmptyPlaces() {
		List<Integer> lineIndexlist = new ArrayList<Integer>();
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				if (this.data[i][j] == null) {
					lineIndexlist.add(i);
				}
			}
		}
		return lineIndexlist;
	}

	public List<Integer> getColumnIndexesOfEmptyPlaces() {
		List<Integer> columnIndexlist = new ArrayList<Integer>();
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				if (this.data[i][j] == null) {
					columnIndexlist.add(j);
				}
			}
		}
		return columnIndexlist;
	}

	public List<List<Integer>> getIndexesOfEmptyPlaces() {
		List<Integer> lineIndexlist = new ArrayList<Integer>();
		List<Integer> columnIndexlist = new ArrayList<Integer>();
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				if (this.data[i][j] == null) {
					lineIndexlist.add(i);
					columnIndexlist.add(j);
				}
			}
		}
		List<List<Integer>> indexes = new ArrayList<List<Integer>>(
				2);
		indexes.add(lineIndexlist);
		indexes.add(columnIndexlist);
		return indexes;
	}

	public void writeObject(String fileName)
			throws java.io.FileNotFoundException, java.io.IOException,
			NullPointerException, java.io.NotSerializableException {
		if (!(data instanceof java.io.Serializable))
			throw new java.io.NotSerializableException(
					ExceptionMessages.NOT_SERIALIZABLE);
		java.io.File file = new java.io.File(fileName);
		java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(
				new java.io.FileOutputStream(file));
		out.writeObject(this);
		out.flush();
		out.close();
	}

	public void write(String fileName) throws java.io.FileNotFoundException,
			java.io.IOException, NullPointerException {
		write(fileName, false);
	}

	public void write(String fileName, char separator)
			throws java.io.FileNotFoundException, java.io.IOException,
			NullPointerException {
		write(fileName, separator, false);
	}

	public void write(String fileName, boolean append)
			throws java.io.FileNotFoundException, java.io.IOException,
			NullPointerException {
		write(fileName, ' ', append);
	}

	public void write(String fileName, char separator, boolean append)
			throws java.io.FileNotFoundException, java.io.IOException,
			NullPointerException {
		java.io.File file = new java.io.File(fileName);
		java.io.BufferedWriter writer = new java.io.BufferedWriter(
				new java.io.FileWriter(file, append));
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				if (this.data[i][j] == null) {
					writer.write("null ");
					continue;
				}
				writer.write(this.data[i][j].toString() + separator);
			}
			if (i != this.linedimension - 1)
				writer.newLine();
		}
		writer.close();
	}

	public DataMatrix<Data> transpose() {
		DataMatrix<Data> transposedMatrix = new DataMatrix<Data>(
				this.columndimension, this.linedimension);
		for (int i = 0; i < this.linedimension; i++) {
			for (int j = 0; j < this.columndimension; j++) {
				transposedMatrix.data[j][i] = this.data[i][j];
			}
		}
		this.linedimension = transposedMatrix.linedimension;
		this.columndimension = transposedMatrix.columndimension;
		this.data = transposedMatrix.data;
		System.gc();
		return this;
	}

	@SuppressWarnings("unchecked")
	public void reverseLine(int lineIndex)
			throws ArrayIndexOutOfBoundsException {
		Data[] tempLine = (Data[]) new Object[this.columndimension];
		for (int i = 0, j = this.columndimension - 1; i < this.columndimension; i++, j--) {
			tempLine[i] = this.data[lineIndex][j];
		}
		this.data[lineIndex] = tempLine;
	}

	public void reverseLines() {
		for (int i = 0; i < this.columndimension; i++) {
			this.reverseLine(i);
		}
	}

	@SuppressWarnings("unchecked")
	public void reverseColumn(int columnIndex)
			throws ArrayIndexOutOfBoundsException {
		Data[] tempLine = (Data[]) new Object[this.linedimension];
		for (int i = 0, j = this.linedimension - 1; i < this.linedimension; i++, j--) {
			tempLine[i] = this.data[j][columnIndex];
		}
		for (int i = 0; i < this.linedimension; i++) {
			this.data[i][columnIndex] = tempLine[i];
		}
	}

	public void reverseColumns() {
		for (int i = 0; i < this.linedimension; i++) {
			this.reverseColumn(i);
		}
	}

	/**
	 * Sorts matrix by ascending order. Matrixes are compared by number of
	 * elements.
	 * 
	 * @throws NullPointerException
	 *             if some elements in given list are null.
	 */
	public static <Data> void sortAsc(List<IDataMatrix<Data>> dataList)
			throws NullPointerException, ClassCastException {
		Collections.sort(dataList);
	}

	/**
	 * Sorts matrix by ascending order by your compare expression.
	 * <p>
	 * You can define your compare parameters by implementing compare method of
	 * Comparator interface for this matrix.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if some elements in given list are null or comparator is
	 *             null.
	 */
	public static <Data> void sort(List<DataMatrix<Data>> dataList,
			Comparator<DataMatrix<Data>> comparator)
			throws NullPointerException, ClassCastException {
		Collections.sort(dataList, comparator);
	}

	/**
	 * Sorts matrix by descending order. Matrixes are compared by number of
	 * elements.
	 * 
	 * @throws NullPointerException
	 *             if some elements in given list are null.
	 */
	public static <Data> void sortDesc(
			List<IDataMatrix<Data>> dataList)
			throws NullPointerException, ClassCastException {
		Collections.sort(dataList);
		Collections.reverse(dataList);
	}

	/**
	 * @return list of ids which can not be changed.
	 *         <p>
	 *         returned will be copy of this values and you can change that but,
	 *         original value will not be changed.
	 *         </p>
	 */
	public static List<String> getIds() {
		return new ArrayList<String>(DataMatrix.ids);
	}

	/**
	 * @return true if this kind of id exists in ids list, otherwise returns
	 *         false.
	 */
	public static boolean isId(String id) {
		return DataMatrix.ids.contains(id);
	}

	
	
	
}


//TODO shenishvnebi shemdegi versistvis

//equals metodshi to parametri iqneba null , mashin unda daabrunos false

//defaultFill metodi unda iyos final radgan konstruqtorhis da bagebi ro avicilot tavidan
//changeContentQuickly metodi unda iyos final igive mizezebis gamo
//copy(List<List<Data>> data) metoidic unda iyos final
//klasebis dokumentaciashi davamato romel jdk zea dawerili. yvela klasisi dokumentaciashi unda davamato. esaa dawerili 1.7 ze.
//Datamatrix shi equals metodidan null ze exceptionis srola wavshalo da false davabrunebino da shesabamisad dokumentaciac unda shevcvalo.


