package com.nodrex.datamatrix;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * This interface contains useful methods which are implemented in DataMatrix
 * class.
 * </p>
 * <p>
 * Implements Serializable , Comparable , Cloneable interfaces.
 * </p>
 * <p>
 * Can be implemented by any class or extended by any class.
 * </p>
 * 
 * @param <Data>Any type can be stored.
 * @author NODREX
 * @version 1.0
 * @since 2013
 * 
 */
public interface IDataMatrix<Data> extends Cloneable,
		Comparable<IDataMatrix<Data>>, Serializable {

	/**
	 * Adds new column in to the end of this matrix.
	 */
	void addColumn();

	/**
	 * Adds new column in to the end of this matrix and copy values from given
	 * columnArray to newly created column.
	 * <p>
	 * If columnArray length is bigger then this matrix column dimension than
	 * copied will be only column dimension number of data, other data will be
	 * ignored.
	 * </p>
	 * <p>
	 * If columnArray length is less then this matrix column dimension than
	 * copied will be all columnArray and other places of this column will be
	 * null.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if given lineArray is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void addColumn(Data... columnArray);

	/**
	 * Adds new column in to the end of this matrix and fills with default
	 * value.
	 */
	void addColumn(Data defaultvalue);

	/**
	 * Adds new column in to the end of this matrix and copy values from given
	 * columnList to newly created column.
	 * <p>
	 * If columnList size is bigger then this matrix column dimension than
	 * copied will be only column dimension number of data, other data will be
	 * ignored.
	 * </p>
	 * <p>
	 * If columnList size is less then this matrix column dimension than copied
	 * will be all columnList and other places of this column will be null.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if given columnList is null.
	 */
	void addColumn(List<Data> columnList);

	/**
	 * Adds new line in to the end of this matrix.
	 */
	void addLine();

	/**
	 * Adds new line in to the end of this matrix and copy values from given
	 * lineArray to newly created row.
	 * <p>
	 * If lineArray length is bigger then this matrix line dimension than copied
	 * will be only line dimension number of data, other data will be ignored.
	 * </p>
	 * <p>
	 * If lineArray length is less then this matrix line dimension than copied
	 * will be all lineArray and other places of this line will be null.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if given lineArray is null
	 */
	@SuppressWarnings(value = "unchecked")
	void addLine(Data... lineArray);

	/**
	 * Adds new line in to the end of this matrix and fills with default value.
	 */
	void addLine(Data defaultvalue);

	/**
	 * Adds new line in to the end of this matrix and copy values from given
	 * lineList to newly created row.
	 * <p>
	 * If lineList size is bigger then this matrix line dimension than copied
	 * will be only line dimension number of data, other data will be ignored.
	 * </p>
	 * <p>
	 * If lineList size is less then this matrix line dimension than copied will
	 * be all lineList and other places of this line will be null.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if given lineArray is null
	 */
	void addLine(List<Data> lineList);

	/**
	 * Adds new matrix to the end of this matrix. Both matrix values will be
	 * copied and column dimension of this new matrix will be same , but line
	 * dimension will be changed : this.linedimension + matrix.linedimension.
	 * <p>
	 * For example:
	 * </p>
	 * <p>
	 * this matrix:
	 * </p>
	 * <p>
	 * 1,2,3
	 * </p>
	 * <p>
	 * Second matrix:
	 * </p>
	 * <p>
	 * 4,5,6
	 * </p>
	 * <p>
	 * After this method matrix will be 2 X 3 </br> </br> 1,2,3 </br> 4,5,6
	 * </p>
	 * <p>
	 * If there will be some incompatible dimensions than matrix will be resized
	 * automatically, so there may be some new values as null or less values if
	 * matrix column dimension is bigger then this matrix column dimension.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if matrix is null.
	 */
	void addMatrixAtLine(IDataMatrix<Data> matrix);

	/**
	 * Adds new matrix to the end of this matrix. Both matrix values will be
	 * copied and column dimension of this new matrix will be same , but line
	 * dimension will be changed : this.linedimension + matrix.linedimension.
	 * <p>
	 * For example:
	 * </p>
	 * <p>
	 * this matrix:
	 * </p>
	 * <p>
	 * 1,2,3
	 * </p>
	 * <p>
	 * Second matrix:
	 * </p>
	 * <p>
	 * 4,5,6
	 * </p>
	 * <p>
	 * After this method matrix will be 2 X 3 </br> </br> 1,2,3 </br> 4,5,6
	 * <p>
	 * If there will be some incompatible dimensions than matrix will be resized
	 * automatically, so there may be some new values filled with defaultValue
	 * or less values if matrix column dimension is bigger then this matrix
	 * column dimension.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if matrix is null.
	 */
	void addMatrixAtLine(IDataMatrix<Data> matrix, Data defaultValue);

	/**
	 * Adds new matrix at the las column of this matrix. Both matrix values will
	 * be copied and line dimension of this new matrix will be same , but column
	 * dimension will be changed : this.columndimension +
	 * matrix.columndimension.
	 * <p>
	 * For example:
	 * </p>
	 * <p>
	 * this matrix:
	 * </p>
	 * <p>
	 * 1,2,3
	 * </p>
	 * <p>
	 * Second matrix:
	 * </p>
	 * <p>
	 * 4,5,6
	 * </p>
	 * <p>
	 * After this method matrix will be 1 X 6
	 * </p>
	 * <p>
	 * 1,2,3,4,5,6
	 * </p>
	 * <p>
	 * If there will be some incompatible dimensions than matrix will be resized
	 * automatically, so there may be some new values as null or less values if
	 * matrix line dimension is bigger then this matrix line dimension.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if matrix is null.
	 */
	void addMatrixAtcolumn(IDataMatrix<Data> matrix);

	/**
	 * Adds new matrix at the las column of this matrix. Both matrix values will
	 * be copied and line dimension of this new matrix will be same , but column
	 * dimension will be changed : this.columndimension +
	 * matrix.columndimension.
	 * <p>
	 * For example:
	 * </p>
	 * <p>
	 * this matrix:
	 * </p>
	 * <p>
	 * 1,2,3
	 * </p>
	 * <p>
	 * Second matrix:
	 * </p>
	 * <p>
	 * 4,5,6
	 * </p>
	 * <p>
	 * After this method matrix will be 1 X 6
	 * </p>
	 * <p>
	 * 1,2,3,4,5,6
	 * </p>
	 * <p>
	 * If there will be some incompatible dimensions than matrix will be resized
	 * automatically, so there may be some new values filled with defaultValue
	 * or less values if matrix line dimension is bigger then this matrix line
	 * dimension.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if matrix is null.
	 */
	void addMatrixAtcolumn(IDataMatrix<Data> matrix, Data defaultValue);

	/**
	 * @param data
	 *            : Changes whole data of this matrix and dimensions
	 *            automatically.
	 *            <p>
	 *            You also can gave jagged data, it will automatically fit
	 *            dimensions: It will take max line and column dimensions.
	 *            </p>
	 * @param name
	 *            : Changes name of this data matrix.
	 * @param id
	 *            : Changes id of this data matrix.
	 * @throws NullPointerException
	 *             if given data or name is null.
	 * @throws IllegalArgumentException
	 *             if id is not unique and this kind of id already exists or id
	 *             is null or empty.
	 */
	void changeContent(Data[][] data, String name, String id);

	/**
	 * @param data
	 *            : Changes whole data of this matrix and dimensions
	 *            automatically.
	 *            <p>
	 *            You also can gave jagged data, it will automatically fit
	 *            dimensions: It will take max line and column dimensions.
	 *            </p>
	 * @param name
	 *            : Changes name of this data matrix.
	 * @param id
	 *            : Changes id of this data matrix.
	 * @throws NullPointerException
	 *             if given data or name is null.
	 * 
	 * @throws IllegalArgumentException
	 *             if data is not filled at least with one element or if id is
	 *             not unique and this kind of id already exists or id is null
	 *             or empty.
	 */
	void changeContent(List<List<Data>> data, String name,
                       String id);

	/**
	 * @param data
	 *            : Changes whole data of this matrix and dimensions
	 *            automatically.
	 *            <p>
	 *            You also can gave jagged data, it will automatically fit
	 *            dimensions: It will take max line and column dimensions.
	 *            </p>
	 * @param name
	 *            : Changes name of this data matrix.
	 * @throws NullPointerException
	 *             if given data or name is null.
	 */
	void changeContent(Data[][] data, String name);

	/**
	 * @param data
	 *            : Changes whole data of this matrix and dimensions
	 *            automatically.
	 *            <p>
	 *            You also can gave jagged data, it will automatically fit
	 *            dimensions: It will take max line and column dimensions.
	 *            </p>
	 * @param name
	 *            : Changes name of this data matrix.
	 * @throws NullPointerException
	 *             if given data or name is null.
	 * 
	 * @throws IllegalArgumentException
	 *             if data is not filled at least with one element.
	 */
	void changeContent(List<List<Data>> data, String name);

	/**
	 * @param data
	 *            : Changes whole data of this matrix and dimensions
	 *            automatically.
	 *            <p>
	 *            You also can gave jagged data, it will automatically fit
	 *            dimensions: It will take max line and column dimensions.
	 *            </p>
	 * @throws NullPointerException
	 *             if given data is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void changeContent(Data[]... data);

	/**
	 * @param data
	 *            : Changes whole data of this matrix and dimensions
	 *            automatically.
	 *            <p>
	 *            You also can gave jagged data, it will automatically fit
	 *            dimensions: It will take max line and column dimensions.
	 *            </p>
	 * @throws NullPointerException
	 *             if given data is null.
	 * 
	 * @throws IllegalArgumentException
	 *             if data is not filled at least with one element.
	 */
	void changeContent(List<List<Data>> data);

	/**
	 * Clears only data.</br>Name , id and dimensions are not touched.
	 */
	void clean();

	/**
	 * Copy values from given data.
	 * <p>
	 * This method makes auto correction of dimensions .
	 * </p>
	 * 
	 * <p>
	 * You also can gave jagged data, it will automatically fit dimensions: It
	 * will take max line and column dimensions.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if given data will be null
	 * 
	 * @throws IllegalArgumentException
	 *             if data is not filled at least with one element.
	 */
	void copy(List<List<Data>> data);

	/**
	 * Copy values from given matrix.
	 * <p>
	 * This method makes auto correction of dimensions.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if given matrix will be null
	 */
	void copy(IDataMatrix<Data> matrix);

	/**
	 * Copy values from given data.
	 * <p>
	 * This method makes auto correction of dimensions .
	 * </p>
	 * 
	 * <p>
	 * You also can gave jagged data, it will automatically fit dimensions: It
	 * will take max line and column dimensions.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if given data will be null
	 */
	@SuppressWarnings(value = "unchecked")
	void copy(Data[]... data);

	/**
	 * fills matrix all data with default value.
	 */
	void defaultFill(Data defaultValue);

	/**
	 * Fills places with given data which are null.
	 * 
	 * @throws NullPointerException
	 *             if given data is null.
	 */
	void fillEmptyPlaces(Data data);

	/**
	 * Fills places which are null with first data from this matrix which will
	 * not be null.
	 * 
	 * @throws NullPointerException
	 *             if all data is null in this matrix.
	 */
	void fillEmptyPlaces();

	/**
	 * @return column which is located on given place (columnIndex).
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if given columnIndex is out of this matrix column dimension
	 *             range.
	 */
	Object[] getColumn(int columnIndex);

	/**
	 * @return column stored in List which is located on given place
	 *         (columnIndex).
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if given columnIndex is out of this matrix column dimension
	 *             range.
	 */
	List<Data> getColumnAsList(int columnIndex);

	/**
	 * @return column dimension of this matrix.
	 */
	int getColumnDimension();

	/**
	 * @return column indexes of empty places (place which contains null).
	 */
	List<Integer> getColumnIndexesOfEmptyPlaces();

	/**
	 * @return data that is stored in this matrix.
	 */
	Object[][] getData();

	/**
	 * @return data which will be stored in List.
	 */
	List<List<Data>> getDataAsList();

	/**
	 * @return diagonal on given place (index). If index is 0 then it will
	 *         return main diagonal, if index is more than 0 it returns up
	 *         diagonal , if index is less then 0 it returns down diagonal.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if index is not in matrix diagonal dimension range.
	 */
	List<Data> getDiagonal(int index);

	/**
	 * @return diagonal on given place (index) from right to left direction. If
	 *         index is 0 then it will return main diagonal, if index is more
	 *         than 0 it returns up diagonal , if index is less then 0 it
	 *         returns down diagonal.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if index is not in matrix diagonal dimension range.
	 */
	List<Data> getDiagonal2(int index);

	/**
	 * @return first value form this matrix which is not null.
	 * @throws NullPointerException
	 *             if all values in this matrix are null.
	 */
	Data getFirstNotNullValue();

	/**
	 * @return id of this matrix.
	 */
	String getId();

	/**
	 * @return indexes of the given value, if data matrix contains this value.
	 *         <p>
	 *         First value of returned array contains line index and second
	 *         value contains column index.
	 *         </p>
	 *         <p>
	 *         If data matrix does not contains given value returns null.
	 *         </p>
	 *         <p>
	 *         If there is many values like given value, it will return indexes
	 *         of first matched value.
	 *         </p>
	 */
	int[] getIndexesOf(Data value);

	/**
	 * @return indexes of empty places (place which contains null).
	 *         <p>
	 *         GetIndexesOfEmptyPlaces.get(0) returns list of line indexes
	 *         </p>
	 *         <p>
	 *         GetIndexesOfEmptyPlaces.get(1) returns list of column indexes
	 *         </p>
	 */
	List<List<Integer>> getIndexesOfEmptyPlaces();

	/**
	 * @return indexes of maximum value of this matrix. Comparable should be
	 *         implemented by data which is stored in this matrix.
	 * @throws NullPointerException
	 *             if this matrix contains even one null.
	 * @throws ClassCastException
	 *             if stored data does not implements Comparable interface.
	 */
	int[] getIndexesOfMaxValue();

	/**
	 * @return indexes of maximum value of this matrix.
	 * 
	 * @throws NullPointerException
	 *             if this matrix contains even one null or given comparator is
	 *             null.
	 */
	int[] getIndexesOfMaxValue(Comparator<Data> comparator);

	/**
	 * @return indexes of minimum value of this matrix. Comparable should be
	 *         implemented by data which is stored in this matrix.
	 * 
	 * @throws NullPointerException
	 *             if this matrix contains even one null.
	 * @throws ClassCastException
	 *             if stored data does not implements Comparable interface.
	 */
	int[] getIndexesOfMinValue();

	/**
	 * @return indexes of minimum value of this matrix.
	 * 
	 * @throws NullPointerException
	 *             if this matrix contains even one null or given comparator is
	 *             null.
	 */
	int[] getIndexesOfMinValue(Comparator<Data> comparator);

	/**
	 * @return last element of this matrix.
	 */
	Data getLastElement();

	/**
	 * @return line array of this matrix which is stored on given place
	 *         (lineIndex).
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if given lineIndex is out of this matrix line dimension
	 *             range.
	 */
	Object[] getLine(int lineIndex);

	/**
	 * @return list of line of this matrix which is stored on given place
	 *         (lineIndex).
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if given lineIndex is out of this matrix line dimension
	 *             range.
	 */
	List<Data> getLineAsList(int lineIndex);

	/**
	 * @return line dimension of this matrix.
	 */
	int getLineDimension();

	/**
	 * @return line indexes of empty places (place which contains null).
	 */
	List<Integer> getLineIndexesOfEmptyPlaces();

	/**
	 * @return main diagonal of this matrix.
	 */
	Object[] getMainDiagonal();

	/**
	 * @return main diagonal of this matrix with will be stored in List.
	 */
	List<Data> getMainDiagonalAsList();

	/**
	 * @return biggest dimension of this matrix.
	 */
	int getMaxDimension();

	/**
	 * @return maximum value of this matrix. Comparable should be implemented by
	 *         data which is stored in this matrix.
	 * 
	 * @throws NullPointerException
	 *             if this matrix contains even one null.
	 * @throws ClassCastException
	 *             if stored data does not implements Comparable interface.
	 */
	Data getMaxValue();

	/**
	 * @return maximum value of this matrix.
	 * 
	 * @throws NullPointerException
	 *             if this matrix contains even one null or given comparator is
	 *             null.
	 */
	Data getMaxValue(Comparator<Data> comparator);

	/**
	 * @return maximum value of column which locates on given index
	 *         (columnIndex). Comparable should be implemented by data which is
	 *         stored in this matrix.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if columnIndex is out of this matrix column dimension range.
	 * @throws ClassCastException
	 *             if data witch is stored in this matrix does not implements
	 *             Comparable interface.
	 * @throws NullPointerException
	 *             if this matrix contains even one null.
	 */
	Data getMaxValueOfColumn(int columnIndex);

	/**
	 * @return maximum value of column which locates on given index
	 *         (columnIndex).
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if columnIndex is out of this matrix line dimension range.
	 * @throws NullPointerException
	 *             if this matrix contains even one null or given comparator is
	 *             null.
	 */
	Data getMaxValueOfColumn(int columnIndex, Comparator<Data> comparator);

	/**
	 * @return maximum value of line which locates on given index (lineIndex).
	 *         Comparable should be implemented by data which is stored in this
	 *         matrix.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if lineIndex is out of this matrix line dimension range.
	 * @throws ClassCastException
	 *             if data witch is stored in this matrix does not implements
	 *             Comparable interface.
	 * @throws NullPointerException
	 *             if this matrix contains even one null.
	 */
	Data getMaxValueOfLine(int lineIndex);

	/**
	 * @return maximum value of line which locates on given index (lineIndex).
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if lineIndex is out of this matrix line dimension range.
	 * @throws NullPointerException
	 *             if this matrix contains even one null or given comparator is
	 *             null.
	 */
	Data getMaxValueOfLine(int lineIndex, Comparator<Data> comparator);

	/**
	 * @return smallest dimension of this matrix.
	 */
	int getMinDimension();

	/**
	 * @return minimum value of this matrix. Comparable should be implemented by
	 *         data which is stored in this matrix.
	 * 
	 * @throws NullPointerException
	 *             if this matrix contains even one null.
	 * @throws ClassCastException
	 *             if stored data does not implements Comparable interface.
	 */
	Data getMinValue();

	/**
	 * @return minimum value of this matrix.
	 * 
	 * @throws NullPointerException
	 *             if this matrix contains even one null or given comparator is
	 *             null.
	 * @throws ClassCastException
	 */
	Data getMinValue(Comparator<Data> comparator);

	/**
	 * @return minimum value of column which locates on given index
	 *         (columnIndex). Comparable should be implemented by data which is
	 *         stored in this matrix.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if columnIndex is out of this matrix column dimension range.
	 * @throws ClassCastException
	 *             if data witch is stored in this matrix does not implements
	 *             Comparable interface.
	 * @throws NullPointerException
	 *             if this matrix contains even one null.
	 */
	Data getMinValueOfColumn(int columnIndex);

	/**
	 * @return minimum value of column which locates on given index
	 *         (columnIndex).
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if columnIndex is out of this matrix line dimension range.
	 * @throws NullPointerException
	 *             if this matrix contains even one null or given comparator is
	 *             null.
	 */
	Data getMinValueOfColumn(int columnIndex, Comparator<Data> comparator);

	/**
	 * @return minimum value of line which locates on given index (lineIndex).
	 *         Comparable should be implemented by data which is stored in this
	 *         matrix.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if lineIndex is out of this matrix line dimension range.
	 * @throws ClassCastException
	 *             if data witch is stored in this matrix does not implements
	 *             Comparable interface.
	 * @throws NullPointerException
	 *             if this matrix contains even one null.
	 */
	Data getMinValueOfLine(int lineIndex);

	/**
	 * @return minimum value of line which locates on given index (lineIndex).
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if lineIndex is out of this matrix line dimension range.
	 * @throws NullPointerException
	 *             if this matrix contains even one null or given comparator is
	 *             null.
	 */
	Data getMinValueOfLine(int lineIndex, Comparator<Data> comparator);

	/**
	 * @return name of this matrix.
	 */
	String getName();

	/**
	 * @return number of stored data.
	 */
	int getNumberOfElements();

	/**
	 * @return second diagonal of this matrix.
	 */
	Object[] getSecondDiagonal();

	/**
	 * @return second diagonal of this matrix with will be stored in List.
	 */
	List<Data> getSecondDiagonalAsList();

	/**
	 * @return value form the given place.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If given place is out of the range of this matrix dimensions.
	 */
	Data getValue(int lineIndex, int columnIndex);

	/**
	 * Inserts column in any places that you want.
	 * <p>
	 * Given index can be negative and it will add column at the head of this
	 * matrix.
	 * </p>
	 * <p>
	 * If index will be out of the range of dimension this method will resize
	 * matrix automatically.
	 * </p>
	 */
	void insertColumn(int index);

	/**
	 * Inserts column in any places that you want and fill with default value.
	 * <p>
	 * Given index can be negative and it will add column at the head of this
	 * matrix.
	 * </p>
	 * <p>
	 * If index will be out of the range of dimension this method will resize
	 * matrix automatically.
	 * </p>
	 * <p>
	 * Fills all newly created empty places.
	 * </p>
	 */
	void insertColumn(int index, Data defaultValue);

	/**
	 * Inserts column in any places that you want and copy values from given
	 * columnArray to newly created column.
	 * <p>
	 * Given index can be negative and it will add column at the head of this
	 * matrix.
	 * </p>
	 * <p>
	 * If columnArray length is bigger then this matrix column dimension than
	 * copied will be only column dimension number of data, other data will be
	 * ignored.
	 * </p>
	 * <p>
	 * If columnArray length is less then this matrix column dimension than
	 * copied will be all columnArray and other places of this line will be
	 * null.
	 * </p>
	 * <p>
	 * Fills all newly created empty places.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if given columnArray is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void insertColumn(int index, Data... columnArray);

	/**
	 * Inserts column in any places that you want and copy values from given
	 * columnList to newly created column.
	 * <p>
	 * Given index can be negative and it will add line at the head of this
	 * matrix.
	 * </p>
	 * <p>
	 * If columnList size is bigger then this matrix column dimension than
	 * copied will be only column dimension number of data, other data will be
	 * ignored.
	 * </p>
	 * <p>
	 * If columnList size is less then this matrix column dimension than copied
	 * will be all columnList and other places of this column will be null.
	 * </p>
	 * <p>
	 * Fills all newly created empty places.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if given columnList is null.
	 */
	void insertColumn(int index, List<Data> columnList);

	/**
	 * Inserts given data in this matrix in the given place (lineIndex) ,
	 * (columnIndex). If dimensions will not be in range this method will
	 * automatically resize this matrix.
	 * <p>
	 * </p>
	 * For example:</br>DataMatrix<Integer> dm = new DataMatrix<>(3, new
	 * Integer(0));</br> dm.insertData(1, 1, new Integer[]{1,1},new
	 * Integer[]{1,1});</br>Result:</br> Dimensions: 5 X 5</br> 0 null null 0 0
	 * </br> null 1 1 null null </br> null 1 1 null null </br> 0 null null 0 0
	 * </br> 0 null null 0 0 </br>
	 * <p>
	 * Example 2:</br>dm.insertData(0, 4, new Integer[]{1,1},new
	 * Integer[]{1,1});</br> Result:</br> Dimensions: 3 X 6</br> 0 0 0 null 1 1
	 * </br> 0 0 0 null 1 1 </br> 0 0 0 null null null </br>
	 * </p>
	 * <p>
	 * <p>
	 * Example 3:</br>dm.insertData(4, 0, new Integer[]{1,1},new
	 * Integer[]{1,1});</br> Dimensions: 6 X 3 </br> 0 0 0 </br> 0 0 0 </br> 0 0
	 * 0 </br> null null null </br> 1 1 null </br> 1 1 null </br>
	 * </p>
	 * 
	 * @throws IllegalArgumentException
	 *             if indexes are negative.
	 * @throws NullPointerException
	 *             if given data is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void insertData(int lineIndex, int columnIndex, Data[]... data);

	/**
	 * Inserts given data in this matrix in the given place (lineIndex) ,
	 * (columnIndex).If dimensions will not be in range this method will
	 * automatically resize this matrix.
	 * <p>
	 * </p>
	 * For example:</br>DataMatrix<Integer> dm = new DataMatrix<>(3, new
	 * Integer(0));</br> List<List<Integer>> data = new ArrayList<>();</br>
	 * List<Integer> innerData = new ArrayList<>();</br> innerData.add(1);</br>
	 * innerData.add(1);</br> data.add(innerData);</br>
	 * data.add(innerData);</br> dm.insertData(1, 1, data);</br>
	 * </br>Result:</br> Dimensions: 5 X 5</br> 0 null null 0 0 </br> null 1 1
	 * null null </br> null 1 1 null null </br> 0 null null 0 0 </br> 0 null
	 * null 0 0 </br>
	 * <p>
	 * Example 2:</br>dm.insertData(0, 4, data);</br> Result:</br> Dimensions: 3
	 * X 6</br> 0 0 0 null 1 1 </br> 0 0 0 null 1 1 </br> 0 0 0 null null null
	 * </br>
	 * </p>
	 * <p>
	 * <p>
	 * Example 3:</br>dm.insertData(4, 0, data);</br> Dimensions: 6 X 3 </br> 0
	 * 0 0 </br> 0 0 0 </br> 0 0 0 </br> null null null </br> 1 1 null </br> 1 1
	 * null </br>
	 * </p>
	 * 
	 * @throws IllegalArgumentException
	 *             if indexes are negative.
	 * @throws NullPointerException
	 *             if given data is null.
	 */
	void insertData(int lineIndex, int columnIndex,
                    List<List<Data>> data);

	/**
	 * Inserts given value in any place that you want.
	 * <p>
	 * If dimensions will be out of matrix dimensions range this method will
	 * resize matrix automatically (first will be add line then column).
	 * </p>
	 * <p>
	 * Absolute value is taken from line and column indexes: lineIndex =
	 * Math.abs(lineIndex) and columnINdex= Math.abs(columnIndex)
	 * </p>
	 */
	void insertElement(int lineIndex, int columnIndex, Data value);

	/**
	 * Inserts given value in any place that you want.
	 * <p>
	 * If dimensions will be out of matrix dimensions range this method will
	 * resize this matrix automatically (first will be add line then column) and
	 * fills newly created line(s) and column(s) with default value.
	 * </p>
	 * <p>
	 * Absolute value is taken from line and column indexes: lineIndex =
	 * Math.abs(lineIndex) and columnINdex= Math.abs(columnIndex)
	 * </p>
	 */
	void insertElement(int lineIndex, int columnIndex, Data value,
                       Data defaultValue);

	/**
	 * Inserts given value in any place that you want.
	 * <p>
	 * If dimensions will be out of matrix dimensions range this method will
	 * resize this matrix automatically (first will be add line then column) and
	 * fills newly created line(s) and column(s) with values from lineArray and
	 * columnArray.
	 * </p>
	 * <p>
	 * Absolute value is taken from line and column indexes: lineIndex =
	 * Math.abs(lineIndex) and columnINdex= Math.abs(columnIndex)
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if given lineArray or columnArray or both is null.
	 */
	void insertElement(int lineIndex, int columnIndex, Data value,
                       Data[] lineArray, Data[] columnArray);

	/**
	 * Inserts given value in any place that you want.
	 * <p>
	 * If dimensions will be out of matrix dimensions range this method will
	 * resize this matrix automatically (first will be add line then column) and
	 * fills newly created line(s) and column(s) with values from lineList and
	 * columnList.
	 * </p>
	 * <p>
	 * Absolute value is taken from line and column indexes: lineIndex =
	 * Math.abs(lineIndex) and columnINdex= Math.abs(columnIndex)
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if given lineList or columnList or both is null.
	 */
	void insertElement(int lineIndex, int columnIndex, Data value,
                       List<Data> lineList, List<Data> columnList);

	/**
	 * Inserts line in any places that you want.
	 * <p>
	 * Given index can be negative and it will add line at the head of this
	 * matrix.
	 * </p>
	 * <p>
	 * If index will be out of the range of dimension this method will resize
	 * matrix automatically.
	 * </p>
	 */
	void insertLine(int index);

	/**
	 * Inserts line in any places that you want and fill with default value.
	 * <p>
	 * Given index can be negative and it will add line at the head of this
	 * matrix.
	 * </p>
	 * <p>
	 * If index will be out of the range of dimension this method will resize
	 * matrix automatically.
	 * </p>
	 * <p>
	 * Fills all newly created empty places.
	 * </p>
	 */
	void insertLine(int index, Data defaultValue);

	/**
	 * Inserts line in any places that you want and copy values from given
	 * lineArray to newly created line.
	 * <p>
	 * Given index can be negative and it will add line at the head of this
	 * matrix.
	 * </p>
	 * <p>
	 * If lineArray length is bigger then this matrix line dimension than copied
	 * will be only line dimension number of data, other data will be ignored.
	 * </p>
	 * <p>
	 * If lineArray length is less then this matrix line dimension than copied
	 * will be all lineArray and other places of this line will be null.
	 * </p>
	 * <p>
	 * Fills all newly created empty places.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if given lineArray is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void insertLine(int index, Data... lineArray);

	/**
	 * Inserts line in any places that you want and copy values from given
	 * lineList to newly created row.
	 * <p>
	 * Given index can be negative and it will add line at the head of this
	 * matrix.
	 * </p>
	 * <p>
	 * If lineList size is bigger then this matrix line dimension than copied
	 * will be only line dimension number of data, other data will be ignored.
	 * </p>
	 * <p>
	 * If lineList size is less then this matrix line dimension than copied will
	 * be all lineList and other places of this line will be null.
	 * </p>
	 * <p>
	 * Fills all newly created empty places.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if given lineList is null.
	 */
	void insertLine(int index, List<Data> lineList);

	/**
	 * Inserts given matrix in this matrix in the given place (lineIndex) ,
	 * (columnIndex). If dimensions will not be in range this method will
	 * automatically resize this matrix.</br>
	 * <p>
	 * </p>
	 * For example:</br>DataMatrix<Integer> dm = new DataMatrix<>(3, new
	 * Integer(0));</br> dm.insertMatrix(1, 1, new DataMatrix<Integer>(2, new
	 * Integer(1)));</br>Result:</br> Dimensions: 5 X 5</br> 0 null null 0 0
	 * </br> null 1 1 null null </br> null 1 1 null null </br> 0 null null 0 0
	 * </br> 0 null null 0 0 </br>
	 * <p>
	 * Example 2:</br>dm.insertMatrix(0, 4, new DataMatrix<Integer>(2, new
	 * Integer(1)));</br> Result:</br> Dimensions: 3 X 6</br> 0 0 0 null 1 1
	 * </br> 0 0 0 null 1 1 </br> 0 0 0 null null null </br>
	 * </p>
	 * <p>
	 * <p>
	 * Example 3:</br>dm.insertMatrix(4, 0, new DataMatrix<Integer>(2, new
	 * Integer(1)));</br> Dimensions: 6 X 3 </br> 0 0 0 </br> 0 0 0 </br> 0 0 0
	 * </br> null null null </br> 1 1 null </br> 1 1 null </br>
	 * </p>
	 * 
	 * @throws IllegalArgumentException
	 *             if indexes are negative.
	 * @throws NullPointerException
	 *             if given matrix is null.
	 */
	void insertMatrix(int lineIndex, int columnIndex, IDataMatrix<Data> matrix);

	/**
	 * Equals method should be override , otherwise equaled will be only
	 * reference.
	 * 
	 * @return true if data matrix contains given value, otherwise returns
	 *         false.
	 * 
	 * @param value
	 *            that should be checked if exist in this matrix.
	 */
	boolean is(Data value);

	/**
	 * @return true if given column index is in range [ 0 , columnDimension ]
	 *         otherwise returns false.
	 */
	boolean isColumnIndex(int columnIndex);

	/**
	 * @return true if matrix have only one column and many lines , otherwise
	 *         returns false.
	 */
	boolean isColumnVector();

	/**
	 * @return true if matrix dimensions are equal to given dimensions:
	 *         <p>
	 *         lineDimension should be equal to this matrix line dimension and
	 *         columnDimension should be equal to this matrix column dimension ,
	 *         otherwise returns false.
	 *         </p>
	 */
	boolean isDimensionsEqualTo(int lineDimension, int columnDimension);

	/**
	 * @return true if every element in this data matrix equals to given
	 *         parameter (variable) , otherwise returns false.
	 *         <p>
	 *         Equals method should be override , otherwise equaled will be only
	 *         reference.
	 *         </p>
	 */
	boolean isEveryElementEqualTo(Data variable);

	/**
	 * @return true if in this matrix does not exists data like given parameter
	 *         (variable) , otherwise returns false.
	 *         <p>
	 *         Equals method should be override , otherwise equaled will be only
	 *         reference.
	 *         </p>
	 */
	boolean isEveryElementNotEqualTo(Data variable);

	/**
	 * @return true if given line index is in range [ 0 , lineDimension ] and
	 *         column index is in range [ 0 , columnDimension ] otherwise
	 *         returns false.
	 */
	boolean isIndexes(int lineIndex, int columnIndex);

	/**
	 * @return true if given line index is in range [ 0 , lineDimension ]
	 *         otherwise returns false.
	 */
	boolean isLineIndex(int lineIndex);

	/**
	 * @return true if matrix have many lines and columns (lineDimension > 1 &&
	 *         columnDimension > 1) , otherwise returns false.
	 */
	boolean isMatrix();

	/**
	 * @return true if this matrix contains even one null, otherwise returns
	 *         false.
	 */
	boolean isNull();

	/**
	 * @return true if matrix dimensions are not equal, otherwise returns false.
	 */
	boolean isRectangular();

	/**
	 * @return true if matrix contains only one data (dimensions are: 1x1),
	 *         otherwise returns false.
	 */
	boolean isScalar();

	/**
	 * @return true if matrix dimensions are equal, otherwise returns false.
	 */
	boolean isSquare();

	/**
	 * @return true if matrix have only one line and many columns, otherwise
	 *         returns false.
	 */
	boolean isVector();

	/**
	 * @return new matrix with dimensions this.columnDimension +
	 *         matrix.columnDimension X max(this.lineDimension,
	 *         matrix.lineDimension). All data of this matrices will be in to
	 *         this new matrix.
	 * @throws NullPointerException
	 *             if matrix is null.
	 */
	DataMatrix<Data> joinMatrixAtColumn(IDataMatrix<Data> matrix);

	/**
	 * @return new matrix with dimensions this.columnDimension +
	 *         matrix.columnDimension X max(this.lineDimension,
	 *         matrix.lineDimension). All data of this matrices will be in to
	 *         this new matrix and if there will be any empty place will fill
	 *         with defaultValue.
	 * @throws NullPointerException
	 *             if matrix is null.
	 */
	DataMatrix<Data> joinMatrixAtColumn(IDataMatrix<Data> matrix,
                                        Data defaultValue);

	/**
	 * @return new matrix with dimensions this.lineDimension +
	 *         matrix.lineDimension X max(this.columnDimension,
	 *         matrix.columnDimension). All data of this matrices will be in to
	 *         this new matrix.
	 * @throws NullPointerException
	 *             if matrix is null.
	 */
	DataMatrix<Data> joinMatrixAtLine(IDataMatrix<Data> matrix);

	/**
	 * @return new matrix with dimensions this.lineDimension +
	 *         matrix.lineDimension X max(this.columnDimension,
	 *         matrix.columnDimension). All data of this matrices will be in to
	 *         this new matrix and if there will be any empty place will fill
	 *         with defaultValue.
	 * @throws NullPointerException
	 *             if matrix is null.
	 */
	DataMatrix<Data> joinMatrixAtLine(IDataMatrix<Data> matrix,
                                      Data defaultValue);

	/**
	 * Removes column from given place (index).
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if given index is out of this matrix column dimension range.
	 * @throws IllegalArgumentException
	 *             if matrix has only one column.
	 */
	void removeColumn(int index);

	/**
	 * Removes data at given place and also deletes line and column of this
	 * matrix.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if given lineIndex or columnIndex are negative or out of this
	 *             matrix dimensions range.
	 * @throws IllegalArgumentException
	 *             if only one line or column is left.
	 */
	void removeElement(int lineIndex, int columnIndex);

	/**
	 * Removes last column of this matrix.
	 * 
	 * @throws IllegalArgumentException
	 *             if matrix has only one column.
	 */
	void removeColumn();

	/**
	 * Removes last line of this matrix.
	 * 
	 * @throws IllegalArgumentException
	 *             if matrix has only one line.
	 */
	void removeLine();

	/**
	 * Removes line from given place (index).
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if given index is out of this matrix line dimension range.
	 * @throws IllegalArgumentException
	 *             if matrix has only one line.
	 */
	void removeLine(int index);

	/**
	 * Resize matrix: Matrix can be decreased or increased, it depends on given
	 * dimensions.
	 * <p>
	 * You can gave any dimensions if it would not be zero or negative!
	 * </p>
	 * <p>
	 * If in this matrix will be any data it also will be in resized matrix.
	 * </p>
	 * <p>
	 * if you want to decrease size of this matrix and there is some data, after
	 * resize data which will be out of the given dimensions will be lost.
	 * </p>
	 * 
	 * @throws IllegalArgumentException
	 *             if given dimensions are negative or zero.
	 */
	void resize(int lineDimension, int columnDimension);

	/**
	 * Resize matrix: Matrix can be decreased or increased, it depends on given
	 * dimensions.
	 * <p>
	 * You can gave any dimension if it would not be zero or negative!
	 * </p>
	 * <p>
	 * If in this matrix will be any data it also will be in resized matrix.
	 * </p>
	 * <p>
	 * if you want to decrease size of this matrix and there is some data, after
	 * resize data which will be out of the given dimensions will be lost.
	 * </p>
	 * <p>
	 * After this resize you will get square matrix.
	 * </p>
	 * 
	 * @throws IllegalArgumentException
	 *             if given dimension is negative or zero.
	 */
	void resize(int squareDimension);

	/**
	 * Resize matrix: Matrix can be decreased or increased, it depends on given
	 * dimensions. Also fills new empty places with default value if you
	 * increase size and there will be empty places, otherwise it just ignores
	 * given default value.
	 * <p>
	 * You can gave any dimensions if it would not be zero or negative!
	 * </p>
	 * <p>
	 * If in this matrix will be any data it also will be in resized matrix.
	 * </p>
	 * <p>
	 * if you want to decrease size of this matrix and there is some data, after
	 * resize data which will be out of the given dimensions will be lost.
	 * </p>
	 * 
	 * @throws IllegalArgumentException
	 *             if given dimensions are negative or zero.
	 */
	void resize(int lineDimension, int columnDimension, Data defaultValue);

	/**
	 * Resize matrix: Matrix can be decreased or increased, it depends on given
	 * dimensions. Also fills new empty places with default value if you
	 * increase size and there will be empty places, otherwise it just ignores
	 * given default value.
	 * <p>
	 * You can gave any dimension if it would not be zero or negative!
	 * </p>
	 * <p>
	 * If in this matrix will be any data it also will be in resized matrix.
	 * </p>
	 * <p>
	 * if you want to decrease size of this matrix and there is some data, after
	 * resize data which will be out of the given dimensions will be lost.
	 * </p>
	 * <p>
	 * After this resize you will get square matrix.
	 * </p>
	 * 
	 * @throws IllegalArgumentException
	 *             if given dimension is negative or zero.
	 */
	void resize(int squareDimension, Data defaultValue);

	/**
	 * Reverse matrix's column on given place (columnIndex).
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if columnIndex is out of this matrix's column dimension
	 *             range.
	 */
	void reverseColumn(int columnIndex);

	/**
	 * Reverse matrix's columns.
	 */
	void reverseColumns();

	/**
	 * Reverse matrix's line on given place (lineIndex).
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if lineIndex is out of this matrix's line dimension range.
	 */
	void reverseLine(int lineIndex);

	/**
	 * Reverse matrix's lines.
	 */
	void reverseLines();

	/**
	 * Copy values from columnArray to given column (columnIndex).
	 * <p>
	 * If columnArray length is bigger then this matrix column dimension than
	 * copied will be only column dimension number of data, other data will be
	 * ignored.
	 * </p>
	 * <p>
	 * If columnArray length is less then this matrix column dimension than
	 * copied will be all columnArray and other places of this line will be
	 * null.
	 * </p>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if given columnIndex is out of this matrix column dimension
	 *             range.
	 * @throws NullPointerException
	 *             if given columnArray is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void setColumn(int columnIndex, Data... columnArray);

	/**
	 * Copy values from columnList to given column (columnIndex).
	 * <p>
	 * If columnList size is bigger then this matrix column dimension than
	 * copied will be only column dimension number of data, other data will be
	 * ignored.
	 * </p>
	 * <p>
	 * If columnList size is less then this matrix column dimension than copied
	 * will be all columnList and other places of this line will be null.
	 * </p>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if given columnIndex is out of this matrix column dimension
	 *             range.
	 * @throws NullPointerException
	 *             if given columnList is null.
	 */
	void setColumn(int columnIndex, List<Data> columnList);

	/**
	 * Fills column on given place (index) with given value.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if given index is out of this matrix column dimension range.
	 */
	void setColumn(int columnIndex, Data value);

	/**
	 * Stores data in to this data matrix and fills dimensions automatically.
	 * 
	 * <p>
	 * You also can gave jagged data, it will automatically fit dimensions: It
	 * will take max line and column dimensions.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if given data is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void setData(Data[]... data);

	/**
	 * Stores data in to this data matrix and fills dimensions automatically.
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
	void setData(List<List<Data>> data);

	/**
	 * Fills diagonal on given place (index). If index is 0 then it will fill
	 * main diagonal, if index is more than 0 it fills up diagonal , if index is
	 * less then 0 it fills down diagonal.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if index is not in matrix diagonal dimension range.
	 */
	void setDiagonal(int index, Data value);

	/**
	 * Fills diagonal on given place (index) from right to left direction. If
	 * index is 0 then it will fill main diagonal, if index is more than 0 it
	 * fills up diagonal , if index is less then 0 it fills down diagonal.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if index is not in matrix diagonal dimension range.
	 */
	void setDiagonal2(int index, Data value);

	/**
	 * Sets id to this matrix.
	 * 
	 * @throws IllegalArgumentException
	 *             if id is not unique and this kind of id already exists.
	 */
	void setId(String id);

	/**
	 * Sets value to the last element of this matrix.
	 */
	void setLastElement(Data value);

	/**
	 * Copy values from lineArray to given line (lineIndex).
	 * <p>
	 * If lineArray length is bigger then this matrix line dimension than copied
	 * will be only line dimension number of data, other data will be ignored.
	 * </p>
	 * <p>
	 * If lineArray length is less then this matrix line dimension than copied
	 * will be all lineArray and other places of this line will be null.
	 * </p>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if given lineIndex is out of this matrix line dimension
	 *             range.
	 * @throws NullPointerException
	 *             if given lineArray is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void setLine(int lineIndex, Data... lineArray);

	/**
	 * Copy values from lineList to given line (lineIndex).
	 * <p>
	 * If lineList size is bigger then this matrix line dimension than copied
	 * will be only line dimension number of data, other data will be ignored.
	 * </p>
	 * <p>
	 * If lineList size is less then this matrix line dimension than copied will
	 * be all lineArray and other places of this line will be null.
	 * </p>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if given lineIndex is out of this matrix line dimension
	 *             range.
	 * @throws NullPointerException
	 *             if given lineList is null.
	 */
	void setLine(int lineIndex, List<Data> lineList);

	/**
	 * Fills line on given place (index) with given value.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if given index is out of this matrix line dimension range.
	 */
	void setLine(int lineIndex, Data value);

	/**
	 * Fills main diagonal with given value.
	 */
	void setMainDiagonal(Data value);

	/**
	 * Copy values from diagonalArray to main diagonal.
	 * <p>
	 * If diagonalArray length is bigger then this matrix diagonal dimension
	 * than copied will be only diagonal dimension number of data, other data
	 * will be ignored.
	 * </p>
	 * <p>
	 * If diagonalArray size is less then this matrix diagonal dimension than
	 * copied will be all diagonalArray and other places of this diagonal will
	 * be null.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if diagonalArray is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void setMainDiagonal(Data... diagonalArray);

	/**
	 * Copy values from diagonalList to main diagonal.
	 * <p>
	 * If diagonalList size is bigger then this matrix diagonal dimension than
	 * copied will be only diagonal dimension number of data, other data will be
	 * ignored.
	 * </p>
	 * <p>
	 * If diagonalList size is less then this matrix diagonal dimension than
	 * copied will be all diagonalList and other places of this diagonal will be
	 * null.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if diagonalList is null.
	 */
	void setMainDiagonal(List<Data> diagonalList);

	/**
	 * Sets name to this matrix.
	 * 
	 * @throws NullPointerException
	 *             if name is null.
	 */
	void setName(String name);

	/**
	 * Fills second diagonal with given value.
	 */
	void setSecondDiagonal(Data value);

	/**
	 * Copy values from diagonalArray to second diagonal.
	 * <p>
	 * If diagonalArray length is bigger then this matrix diagonal dimension
	 * than copied will be only diagonal dimension number of data, other data
	 * will be ignored.
	 * </p>
	 * <p>
	 * If diagonalArray length is less then this matrix diagonal dimension than
	 * copied will be all diagonalArray and other places of this diagonal will
	 * be null.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if diagonalArray is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void setSecondDiagonal(Data... diagonalArray);

	/**
	 * Copy values from diagonalList to second diagonal.
	 * <p>
	 * If diagonalList size is bigger then this matrix diagonal dimension than
	 * copied will be only diagonal dimension number of data, other data will be
	 * ignored.
	 * </p>
	 * <p>
	 * If diagonalList size is less then this matrix diagonal dimension than
	 * copied will be all diagonalList and other places of this diagonal will be
	 * null.
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             if diagonalList is null.
	 */
	void setSecondDiagonal(List<Data> diagonalList);

	/**
	 * Sets value to the given place.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             If given place is out of the range of this matrix dimensions.
	 */
	void setValue(int lineIndex, int columnIndex, Data value);

	/**
	 * Sorts this matrix column which locates on given index by ascending order.
	 * Comparable should be implemented by data which is stored in this matrix.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if index is out of this matrix dimension range.
	 * @throws NullPointerException
	 * @throws ClassCastException
	 *             if data witch is stored in this matrix does not implements
	 *             Comparable interface.
	 */
	void sortColumnAsc(int index);

	/**
	 * Sorts this matrix column which locates on given index.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if index is out of this matrix dimension range.
	 * @throws NullPointerException
	 */
	void sortColumn(int index, Comparator<Data> comparator);

	/**
	 * Sorts this matrix column which locates on given index by descending
	 * order. Comparable should be implemented by data which is stored in this
	 * matrix.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if index is out of this matrix dimension range.
	 * @throws NullPointerException
	 * @throws ClassCastException
	 *             if data witch is stored in this matrix does not implements
	 *             Comparable interface.
	 */
	void sortColumnDesc(int index);

	/**
	 * Sorts this matrix line which locates on given index by ascending order.
	 * Comparable should be implemented by data which is stored in this matrix.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if index is out of this matrix dimension range.
	 * @throws NullPointerException
	 * @throws ClassCastException
	 *             if data witch is stored in this matrix does not implements
	 *             Comparable interface.
	 */
	void sortLineAsc(int index);

	/**
	 * Sorts this matrix line which locates on given index.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if index is out of this matrix dimension range.
	 * @throws NullPointerException
	 *             if this matrix contains even one null or given comparator is
	 *             null.
	 */
	void sortLine(int index, Comparator<Data> comparator);

	/**
	 * Sorts this matrix line which locates on given index by descending order.
	 * Comparable should be implemented by data which is stored in this matrix.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if index is out of this matrix dimension range.
	 * @throws NullPointerException
	 * @throws ClassCastException
	 *             if data witch is stored in this matrix does not implements
	 *             Comparable interface.
	 */
	void sortLineDesc(int index);

	/**
	 * Sorts all columns of this matrix by ascending order. Comparable should be
	 * implemented by data which is stored in this matrix.
	 * 
	 * @throws NullPointerException
	 *             if this matrix contains even one null.
	 * @throws ClassCastException
	 *             if data witch is stored in this matrix does not implements
	 *             Comparable interface.
	 */
	void sortColumnsAsc();

	/**
	 * Sorts all columns of this matrix.
	 * 
	 * @throws NullPointerException
	 *             if this matrix contains even one null or given comparator is
	 *             null.
	 */
	void sortColumns(Comparator<Data> comparator);

	/**
	 * Sorts all columns of this matrix by descending order. Comparable should
	 * be implemented by data which is stored in this matrix.
	 * 
	 * @throws NullPointerException
	 *             if this matrix contains even one null.
	 * @throws ClassCastException
	 *             if data witch is stored in this matrix does not implements
	 *             Comparable interface.
	 */
	void sortColumnsDesc();

	/**
	 * Sorts all lines of this matrix by ascending order. Comparable should be
	 * implemented by data which is stored in this matrix.
	 * 
	 * @throws NullPointerException
	 *             if this matrix contains even one null.
	 * @throws ClassCastException
	 *             if data witch is stored in this matrix does not implements
	 *             Comparable interface.
	 */
	void sortLinesAsc();

	/**
	 * Sorts all lines of this matrix.
	 * 
	 * @throws NullPointerException
	 *             if this matrix contains even one null or given comparator is
	 *             null.
	 */
	void sortLines(Comparator<Data> comparator);

	/**
	 * Sorts all lines of this matrix by descending order. Comparable should be
	 * implemented by data which is stored in this matrix.
	 * 
	 * @throws NullPointerException
	 *             if this matrix contains even one null.
	 * @throws ClassCastException
	 *             if data witch is stored in this matrix does not implements
	 *             Comparable interface.
	 */
	void sortLinesDesc();

	/**
	 * @return sub matrix of this matrix.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if id given indexes are out of range of this matrix
	 *             dimensions.
	 * @throws IllegalArgumentException
	 *             if indexes are given incorrectly.
	 */
	DataMatrix<Data> subMatrix(int startLineIndex, int startColumnIndex,
                               int endLineIndex, int endColumnIndex);

	/**
	 * Swaps this matrixes, even name and id.
	 * 
	 * @throws NullPointerException
	 *             if given matrix is null.
	 */
	void swap(IDataMatrix<Data> matrix);

	/**
	 * Changes place of tow column witch are stored on given places.
	 * <p>
	 * it means that column witch is stored in columnIndex_1 will stored in
	 * columnIndex_2 and column witch was stored in columnIndex_2 will stored in
	 * columnIndex_1.
	 * </p>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if columnIndex_1 or columnIndex_2 or both are out of this
	 *             matrix column dimension range.
	 */
	void swapColumns(int columnIndex_1, int columnIndex_2);

	/**
	 * Changes data between given and this matrix.
	 * 
	 * @throws NullPointerException
	 *             if given matrix is null.
	 */
	void swapData(IDataMatrix<Data> matrix);

	/**
	 * Changes places of tow data on given places.
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if given indexes are out of this matrix dimensions range.
	 */
	void swapData(int lineIndex_1, int columnIndex_1, int lineIndex_2,
                  int columnIndex_2);

	/**
	 * Changes places to given data_1 and data_2 if both of them exists in this
	 * matrix.
	 * <p>
	 * Equals method should be override for given data parameters.
	 * </p>
	 * 
	 * @throws IllegalArgumentException
	 *             if this matrix do not contains any of this given data or
	 *             both.
	 * @throws NullPointerException
	 *             if given data_1 or data_2 or both is null.
	 */
	void swapData(Data data_1, Data data_2);

	/**
	 * Changes place of tow line witch are stored on given places.
	 * <p>
	 * it means that line witch is stored in lineIndex_1 will stored in
	 * lineIndex_2 and line witch was stored in lineIndex_2 will stored in
	 * lineIndex_1.
	 * </p>
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if lineIndex_1 or lineIndex_2 or both are out of this matrix
	 *             line dimension range.
	 */
	void swapLines(int lineIndex_1, int lineIndex_2);

	/**
	 * @return list from this matrix, it means that: All lines will be located
	 *         side by side together in list started from first line.
	 */
	List<Data> toList();

	/**
	 * @return list from this matrix, it means that: All columns will be located
	 *         side by side together in list started from first column.
	 */
	List<Data> toListByColumn();

	/**
	 * Transpose this matrix.
	 * 
	 * @return Transposed matrix
	 */
	DataMatrix<Data> transpose();

	/**
	 * Writes data as DataMatrix in to given file. If data is not primitive than
	 * Serializable interface should be implemented by this data.
	 * 
	 * @throws FileNotFoundException
	 *             if given file does not exists.
	 * @throws IOException
	 *             if there will be some problem with input.
	 * @throws NullPointerException
	 *             if given fileName is null or if every element is null.
	 * @throws NotSerializableException
	 *             if stored data does not implements Serializable interface.
	 */
	void writeObject(String fileName) throws java.io.FileNotFoundException,
			java.io.IOException, NullPointerException,
			java.io.NotSerializableException;

	/**
	 * Writes data in to given file using toString method for data which is
	 * stored in this matrix.
	 * <p>
	 * Uses one space to separate data.
	 * </p>
	 * 
	 * @throws FileNotFoundException
	 *             if given file does not exists.
	 * @throws IOException
	 *             if there will be some problem with input.
	 * @throws NullPointerException
	 *             if given fileName is null or if every element is null.
	 */
	void write(String fileName) throws java.io.FileNotFoundException,
			java.io.IOException, NullPointerException;

	/**
	 * Writes data in to given file using toString method for data which is
	 * stored in this matrix.
	 * <p>
	 * Data will be separated by given separator.
	 * </p>
	 * 
	 * @throws FileNotFoundException
	 *             if given file does not exists.
	 * @throws IOException
	 *             if there will be some problem with input.
	 * @throws NullPointerException
	 *             if given fileName is null or if every element is null.
	 */
	void write(String fileName, char separator)
			throws java.io.FileNotFoundException, java.io.IOException,
			NullPointerException;

	/**
	 * Writes data in to the end of the given file if append argument will be
	 * true. Using toString method for data which is stored in this matrix.
	 * <p>
	 * Uses one space to separate data.
	 * </p>
	 * 
	 * @throws FileNotFoundException
	 *             if given file does not exists.
	 * @throws IOException
	 *             if there will be some problem with input.
	 * @throws NullPointerException
	 *             if given fileName is null or if every element is null.
	 */
	void write(String fileName, boolean append)
			throws java.io.FileNotFoundException, java.io.IOException,
			NullPointerException;

	/**
	 * Writes data in to the end of the given file if append argument will be
	 * true. Using toString method for data which is stored in this matrix.
	 * <p>
	 * Data will be separated by given separator.
	 * </p>
	 * 
	 * @throws FileNotFoundException
	 *             if given file does not exists.
	 * @throws IOException
	 *             if there will be some problem with input.
	 * @throws NullPointerException
	 *             if given fileName is null or if every element is null.
	 */
	void write(String fileName, char separator, boolean append)
			throws java.io.FileNotFoundException, java.io.IOException,
			NullPointerException;

}