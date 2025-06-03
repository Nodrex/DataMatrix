package com.nodrex.datamatrix;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * Defines the contract for a data matrix, a 2D data structure.
 * This interface provides methods for manipulating the matrix, such as adding/removing rows and columns,
 * accessing and modifying elements, performing mathematical operations, and managing matrix properties.
 * <p>
 * Implementations are expected to handle generic data types {@code <Data>}.
 * </p>
 * <p>
 * This interface extends {@link Cloneable}, {@link Comparable} (for comparing with other {@code IDataMatrix} instances
 * based on the number of elements), and {@link Serializable} to support cloning, comparison, and serialization.
 * </p>
 *
 * @param <Data> The type of data stored in the matrix.
 * @author NODREX
 * @version 1.0
 * @since 2013
 * @see DataMatrix
 * @see java.lang.Cloneable
 * @see java.lang.Comparable
 * @see java.io.Serializable
 */
public interface IDataMatrix<Data> extends Cloneable,
		Comparable<IDataMatrix<Data>>, Serializable {

	/**
	 * Adds a new, empty column (filled with nulls) to the end of this matrix.
	 * This increases the column dimension by one.
	 */
	void addColumn();

	/**
	 * Adds a new column to the end of this matrix and populates it with values from the given {@code columnArray}.
	 * <p>
	 * If the length of {@code columnArray} is greater than the matrix's current line dimension,
	 * only a number of elements equal to the line dimension will be copied; excess elements are ignored.
	 * </p>
	 * <p>
	 * If the length of {@code columnArray} is less than the matrix's current line dimension,
	 * all elements from {@code columnArray} will be copied, and the remaining cells in the new column will be null.
	 * </p>
	 *
	 * @param columnArray The array of data to populate the new column.
	 * @throws NullPointerException if {@code columnArray} is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void addColumn(Data... columnArray);

	/**
	 * Adds a new column to the end of this matrix and fills all its cells with the specified {@code defaultValue}.
	 *
	 * @param defaultValue The value to fill the new column with.
	 */
	void addColumn(Data defaultValue);

	/**
	 * Adds a new column to the end of this matrix and populates it with values from the given {@code columnList}.
	 * <p>
	 * If the size of {@code columnList} is greater than the matrix's current line dimension,
	 * only a number of elements equal to the line dimension will be copied; excess elements are ignored.
	 * </p>
	 * <p>
	 * If the size of {@code columnList} is less than the matrix's current line dimension,
	 * all elements from {@code columnList} will be copied, and the remaining cells in the new column will be null.
	 * </p>
	 *
	 * @param columnList The list of data to populate the new column.
	 * @throws NullPointerException if {@code columnList} is null.
	 */
	void addColumn(List<Data> columnList);

	/**
	 * Adds a new, empty line (row, filled with nulls) to the end of this matrix.
	 * This increases the line dimension by one.
	 */
	void addLine();

	/**
	 * Adds a new line to the end of this matrix and populates it with values from the given {@code lineArray}.
	 * <p>
	 * If the length of {@code lineArray} is greater than the matrix's current column dimension,
	 * only a number of elements equal to the column dimension will be copied; excess elements are ignored.
	 * </p>
	 * <p>
	 * If the length of {@code lineArray} is less than the matrix's current column dimension,
	 * all elements from {@code lineArray} will be copied, and the remaining cells in the new line will be null.
	 * </p>
	 *
	 * @param lineArray The array of data to populate the new line.
	 * @throws NullPointerException if {@code lineArray} is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void addLine(Data... lineArray);

	/**
	 * Adds a new line to the end of this matrix and fills all its cells with the specified {@code defaultValue}.
	 *
	 * @param defaultValue The value to fill the new line with.
	 */
	void addLine(Data defaultValue);

	/**
	 * Adds a new line to the end of this matrix and populates it with values from the given {@code lineList}.
	 * <p>
	 * If the size of {@code lineList} is greater than the matrix's current column dimension,
	 * only a number of elements equal to the column dimension will be copied; excess elements are ignored.
	 * </p>
	 * <p>
	 * If the size of {@code lineList} is less than the matrix's current column dimension,
	 * all elements from {@code lineList} will be copied, and the remaining cells in the new line will be null.
	 * </p>
	 *
	 * @param lineList The list of data to populate the new line.
	 * @throws NullPointerException if {@code lineList} is null.
	 */
	void addLine(List<Data> lineList);

	/**
	 * Appends the rows of the specified {@code matrix} to the end of this matrix.
	 * The column dimension of this matrix may be adjusted (potentially adding nulls or truncating data from the appended matrix)
	 * to match the column dimension of this matrix if they are different. The line dimension of this matrix
	 * will be increased by the line dimension of the appended matrix.
	 * <p>
	 * Example:
	 * </p>
	 * <p>This matrix (A):<br>1 2 3</p>
	 * <p>Specified matrix (B):<br>4 5 6</p>
	 * <p>After {@code A.addMatrixAtLine(B)}, A becomes:<br>1 2 3<br>4 5 6</p>
	 * <p>
	 * If column dimensions are incompatible, this matrix's column dimension is preserved. If the specified matrix
	 * has fewer columns, the missing cells in the appended rows are filled with null. If it has more,
	 * excess columns from the specified matrix are ignored.
	 * </p>
	 *
	 * @param matrix The matrix whose rows are to be added to this matrix.
	 * @throws NullPointerException if {@code matrix} is null.
	 */
	void addMatrixAtLine(IDataMatrix<Data> matrix);

	/**
	 * Appends the rows of the specified {@code matrix} to the end of this matrix, using {@code defaultValue}
	 * to fill any new cells created due to dimension incompatibilities.
	 * The column dimension of this matrix may be adjusted. If the specified matrix has fewer columns,
	 * the missing cells in the appended rows are filled with {@code defaultValue}. If it has more,
	 * excess columns from the specified matrix are ignored. The line dimension of this matrix
	 * will be increased by the line dimension of the appended matrix.
	 *
	 * @param matrix       The matrix whose rows are to be added to this matrix.
	 * @param defaultValue The value to use for filling new cells if dimensions are incompatible.
	 * @throws NullPointerException if {@code matrix} is null.
	 */
	void addMatrixAtLine(IDataMatrix<Data> matrix, Data defaultValue);

	/**
	 * Appends the columns of the specified {@code matrix} to the end (right side) of this matrix.
	 * The line dimension of this matrix may be adjusted (potentially adding nulls or truncating data from the appended matrix)
	 * to match the line dimension of this matrix if they are different. The column dimension of this matrix
	 * will be increased by the column dimension of the appended matrix.
	 * <p>
	 * If line dimensions are incompatible, this matrix's line dimension is preserved. If the specified matrix
	 * has fewer lines, the missing cells in the appended columns are filled with null. If it has more,
	 * excess lines from the specified matrix are ignored.
	 * </p>
	 *
	 * @param matrix The matrix whose columns are to be added to this matrix.
	 * @throws NullPointerException if {@code matrix} is null.
	 */
	void addMatrixAtcolumn(IDataMatrix<Data> matrix);

	/**
	 * Appends the columns of the specified {@code matrix} to the end (right side) of this matrix, using {@code defaultValue}
	 * to fill any new cells created due to dimension incompatibilities.
	 * The line dimension of this matrix may be adjusted. If the specified matrix has fewer lines,
	 * the missing cells in the appended columns are filled with {@code defaultValue}. If it has more,
	 * excess lines from the specified matrix are ignored. The column dimension of this matrix
	 * will be increased by the column dimension of the appended matrix.
	 *
	 * @param matrix       The matrix whose columns are to be added to this matrix.
	 * @param defaultValue The value to use for filling new cells if dimensions are incompatible.
	 * @throws NullPointerException if {@code matrix} is null.
	 */
	void addMatrixAtcolumn(IDataMatrix<Data> matrix, Data defaultValue);

	/**
	 * Replaces the entire content of this matrix (data, name, and ID) with the provided values.
	 * The matrix dimensions will be inferred from the {@code data} array.
	 * Jagged arrays are supported; the column dimension will be the maximum length of any row, with shorter rows padded by nulls.
	 *
	 * @param data The new 2D array data for the matrix.
	 * @param name The new name for the matrix.
	 * @param id   The new unique ID for the matrix.
	 * @throws NullPointerException     if {@code data} or {@code name} is null.
	 * @throws IllegalArgumentException if {@code id} is null, empty, or not unique (if applicable to implementation).
	 */
	void changeContent(Data[][] data, String name, String id);

	/**
	 * Replaces the entire content of this matrix (data, name, and ID) with the provided values.
	 * The matrix dimensions will be inferred from the {@code data} list of lists.
	 * Jagged lists (inner lists of different sizes) are supported; the column dimension will be the maximum size of any inner list,
	 * with shorter rows effectively padded by nulls.
	 *
	 * @param data The new list of lists data for the matrix.
	 * @param name The new name for the matrix.
	 * @param id   The new unique ID for the matrix.
	 * @throws NullPointerException     if {@code data} (or any inner list) or {@code name} is null.
	 * @throws IllegalArgumentException if {@code data} is empty, or if {@code id} is null, empty, or not unique (if applicable to implementation).
	 */
	void changeContent(List<List<Data>> data, String name, String id);

	/**
	 * Replaces the data and name of this matrix. The ID remains unchanged.
	 * The matrix dimensions will be inferred from the {@code data} array.
	 *
	 * @param data The new 2D array data for the matrix.
	 * @param name The new name for the matrix.
	 * @throws NullPointerException if {@code data} or {@code name} is null.
	 */
	void changeContent(Data[][] data, String name);

	/**
	 * Replaces the data and name of this matrix. The ID remains unchanged.
	 * The matrix dimensions will be inferred from the {@code data} list of lists.
	 *
	 * @param data The new list of lists data for the matrix.
	 * @param name The new name for the matrix.
	 * @throws NullPointerException     if {@code data} (or any inner list) or {@code name} is null.
	 * @throws IllegalArgumentException if {@code data} is empty.
	 */
	void changeContent(List<List<Data>> data, String name);

	/**
	 * Replaces the data of this matrix with the given 2D array. Name and ID remain unchanged.
	 * The matrix dimensions will be inferred from the {@code data} array.
	 *
	 * @param data The new 2D array data for the matrix.
	 * @throws NullPointerException if {@code data} is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void changeContent(Data[]... data);

	/**
	 * Replaces the data of this matrix with the given list of lists. Name and ID remain unchanged.
	 * The matrix dimensions will be inferred from the {@code data} list.
	 *
	 * @param data The new list of lists data for the matrix.
	 * @throws NullPointerException     if {@code data} or any inner list is null.
	 * @throws IllegalArgumentException if {@code data} is empty.
	 */
	void changeContent(List<List<Data>> data);

	/**
	 * Clears the matrix by setting all its elements to {@code null}.
	 * The dimensions, name, and ID of the matrix remain unchanged.
	 */
	void clean();

	/**
	 * Copies the data from the given list of lists into this matrix, resizing it accordingly.
	 * If the inner lists have different sizes, the matrix column dimension will be
	 * set to the maximum size found; shorter rows will effectively be padded with nulls.
	 *
	 * @param data The list of lists to copy data from.
	 * @throws NullPointerException     if {@code data} or any inner list is null.
	 * @throws IllegalArgumentException if {@code data} is empty (contains no rows).
	 */
	void copy(List<List<Data>> data);

	/**
	 * Copies the data from the given {@code IDataMatrix} into this matrix, resizing it accordingly.
	 * Name and ID of this matrix are not changed.
	 *
	 * @param matrix The matrix to copy data from.
	 * @throws NullPointerException if {@code matrix} is null.
	 */
	void copy(IDataMatrix<Data> matrix);

	/**
	 * Copies the data from the given 2D array into this matrix, resizing it accordingly.
	 * If the input array is jagged, the matrix column dimension will be set to the maximum length found;
	 * shorter rows will effectively be padded with nulls.
	 *
	 * @param data The 2D array to copy data from.
	 * @throws NullPointerException if {@code data} is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void copy(Data[]... data);

	/**
	 * Fills the entire matrix with the specified {@code defaultValue}.
	 *
	 * @param defaultValue The value to set for all elements in the matrix.
	 */
	void defaultFill(Data defaultValue);

	/**
	 * Fills all {@code null} (empty) places in the matrix with the specified {@code data} value.
	 *
	 * @param data The value to insert into null cells.
	 * @throws NullPointerException if {@code data} itself is null (unless the intention is to fill with explicit nulls, which this method prevents for clarity).
	 */
	void fillEmptyPlaces(Data data);

	/**
	 * Fills all {@code null} (empty) places in the matrix with the first non-null value found within the matrix.
	 * The search for the first non-null value typically proceeds row by row, then column by column.
	 *
	 * @throws NullPointerException if all elements in the matrix are currently {@code null}, as no value can be found to fill with.
	 */
	void fillEmptyPlaces();

	/**
	 * Retrieves the column at the specified {@code columnIndex} as an array of {@code Object}.
	 *
	 * @param columnIndex The index of the column to retrieve.
	 * @return An array containing the elements of the specified column.
	 * @throws ArrayIndexOutOfBoundsException if {@code columnIndex} is out of the matrix's column dimension range.
	 */
	Object[] getColumn(int columnIndex);

	/**
	 * Retrieves the column at the specified {@code columnIndex} as a {@code List<Data>}.
	 *
	 * @param columnIndex The index of the column to retrieve.
	 * @return A list containing the elements of the specified column.
	 * @throws ArrayIndexOutOfBoundsException if {@code columnIndex} is out of the matrix's column dimension range.
	 */
	List<Data> getColumnAsList(int columnIndex);

	/**
	 * Returns the number of columns in this matrix.
	 *
	 * @return The column dimension of this matrix.
	 */
	int getColumnDimension();

	/**
	 * Returns a list of column indexes where at least one cell in that column is {@code null}.
	 *
	 * @return A list of integer indexes for columns containing empty (null) places.
	 */
	List<Integer> getColumnIndexesOfEmptyPlaces();

	/**
	 * Returns the internal data of the matrix as a 2D array of {@code Object}.
	 * Implementations should clarify if this is a copy or a direct reference.
	 *
	 * @return A 2D {@code Object} array representing the matrix data.
	 */
	Object[][] getData();

	/**
	 * Returns the content of the matrix as a {@code List} of {@code List}s of {@code Data}.
	 * Each inner list represents a row.
	 *
	 * @return A list of lists representing the matrix data.
	 */
	List<List<Data>> getDataAsList();

	/**
	 * Retrieves a diagonal from the matrix based on the specified {@code index}.
	 * An {@code index} of 0 returns the main diagonal (top-left to bottom-right).
	 * A positive {@code index} returns an upper diagonal (above the main diagonal).
	 * A negative {@code index} returns a lower diagonal (below the main diagonal).
	 *
	 * @param index The index of the diagonal to retrieve.
	 * @return A list containing the elements of the specified diagonal.
	 * @throws ArrayIndexOutOfBoundsException if {@code index} is not within the valid range of diagonals for the matrix dimensions.
	 */
	List<Data> getDiagonal(int index);

	/**
	 * Retrieves a diagonal from the matrix, proceeding from top-right to bottom-left (anti-diagonal direction),
	 * based on the specified {@code index}.
	 * An {@code index} of 0 returns the main anti-diagonal.
	 * A positive {@code index} refers to anti-diagonals "above" (further from the main anti-diagonal towards top-right) it.
	 * A negative {@code index} refers to anti-diagonals "below" it.
	 *
	 * @param index The index of the anti-diagonal to retrieve.
	 * @return A list containing the elements of the specified anti-diagonal.
	 * @throws ArrayIndexOutOfBoundsException if {@code index} is not within the valid range for anti-diagonals.
	 */
	List<Data> getDiagonal2(int index);

	/**
	 * Retrieves the first non-null value found in this matrix.
	 * The search order is typically row by row, then column by column.
	 *
	 * @return The first non-null data element.
	 * @throws NullPointerException if all values in this matrix are {@code null}.
	 */
	Data getFirstNotNullValue();

	/**
	 * Returns the unique identifier (ID) of this matrix.
	 *
	 * @return The ID string of this matrix.
	 */
	String getId();

	/**
	 * Finds the first occurrence of the specified {@code value} in the matrix and returns its indices.
	 * The comparison is typically done using the {@code equals} method of the elements.
	 *
	 * @param value The value to search for in the matrix.
	 * @return An integer array {@code [lineIndex, columnIndex]} of the first occurrence, or {@code null} if the value is not found.
	 */
	int[] getIndexesOf(Data value);

	/**
	 * Returns a list containing two lists: the first is a list of line indexes of all empty (null) places,
	 * and the second is a list of corresponding column indexes.
	 * {@code getIndexesOfEmptyPlaces().get(0)} returns line indexes.
	 * {@code getIndexesOfEmptyPlaces().get(1)} returns column indexes.
	 *
	 * @return A list of two lists, representing the line and column indexes of empty cells.
	 */
	List<List<Integer>> getIndexesOfEmptyPlaces();

	/**
	 * Finds the maximum value in the matrix and returns its indices.
	 * Requires that the data type {@code Data} implements {@link Comparable}.
	 *
	 * @return An integer array {@code [lineIndex, columnIndex]} of the maximum value.
	 * @throws NullPointerException if the matrix contains any {@code null} values, as they cannot be compared.
	 * @throws ClassCastException   if the data stored in the matrix does not implement {@code Comparable}.
	 */
	int[] getIndexesOfMaxValue();

	/**
	 * Finds the maximum value in the matrix using the provided {@code comparator} and returns its indices.
	 *
	 * @param comparator The comparator to use for comparing data elements.
	 * @return An integer array {@code [lineIndex, columnIndex]} of the maximum value.
	 * @throws NullPointerException if the matrix contains any {@code null} values (unless the comparator handles them) or if {@code comparator} is null.
	 */
	int[] getIndexesOfMaxValue(Comparator<Data> comparator);

	/**
	 * Finds the minimum value in the matrix and returns its indices.
	 * Requires that the data type {@code Data} implements {@link Comparable}.
	 *
	 * @return An integer array {@code [lineIndex, columnIndex]} of the minimum value.
	 * @throws NullPointerException if the matrix contains any {@code null} values, as they cannot be compared.
	 * @throws ClassCastException   if the data stored in the matrix does not implement {@code Comparable}.
	 */
	int[] getIndexesOfMinValue();

	/**
	 * Finds the minimum value in the matrix using the provided {@code comparator} and returns its indices.
	 *
	 * @param comparator The comparator to use for comparing data elements.
	 * @return An integer array {@code [lineIndex, columnIndex]} of the minimum value.
	 * @throws NullPointerException if the matrix contains any {@code null} values (unless the comparator handles them) or if {@code comparator} is null.
	 */
	int[] getIndexesOfMinValue(Comparator<Data> comparator);

	/**
	 * Returns the last element of this matrix (element at {@code [lineDimension-1, columnDimension-1]}).
	 *
	 * @return The data element at the last position.
	 * @throws ArrayIndexOutOfBoundsException if the matrix is empty (0x0 or uninitialized).
	 */
	Data getLastElement();

	/**
	 * Retrieves the line (row) at the specified {@code lineIndex} as an array of {@code Object}.
	 *
	 * @param lineIndex The index of the line to retrieve.
	 * @return An array containing the elements of the specified line.
	 * @throws ArrayIndexOutOfBoundsException if {@code lineIndex} is out of the matrix's line dimension range.
	 */
	Object[] getLine(int lineIndex);

	/**
	 * Retrieves the line (row) at the specified {@code lineIndex} as a {@code List<Data>}.
	 *
	 * @param lineIndex The index of the line to retrieve.
	 * @return A list containing the elements of the specified line.
	 * @throws ArrayIndexOutOfBoundsException if {@code lineIndex} is out of the matrix's line dimension range.
	 */
	List<Data> getLineAsList(int lineIndex);

	/**
	 * Returns the number of lines (rows) in this matrix.
	 *
	 * @return The line dimension of this matrix.
	 */
	int getLineDimension();

	/**
	 * Returns a list of line indexes where at least one cell in that line is {@code null}.
	 *
	 * @return A list of integer indexes for lines containing empty (null) places.
	 */
	List<Integer> getLineIndexesOfEmptyPlaces();

	/**
	 * Retrieves the main diagonal (top-left to bottom-right) of this matrix as an array of {@code Object}.
	 * The length of the array will be {@code min(lineDimension, columnDimension)}.
	 *
	 * @return An array containing the elements of the main diagonal.
	 */
	Object[] getMainDiagonal();

	/**
	 * Retrieves the main diagonal (top-left to bottom-right) of this matrix as a {@code List<Data>}.
	 * The size of the list will be {@code min(lineDimension, columnDimension)}.
	 *
	 * @return A list containing the elements of the main diagonal.
	 */
	List<Data> getMainDiagonalAsList();

	/**
	 * Returns the greater of the line dimension and column dimension of this matrix.
	 *
	 * @return The maximum dimension (line or column) of this matrix.
	 */
	int getMaxDimension();

	/**
	 * Finds and returns the maximum value in the entire matrix.
	 * Requires that the data type {@code Data} implements {@link Comparable}.
	 *
	 * @return The maximum data element in the matrix.
	 * @throws NullPointerException if the matrix contains any {@code null} values.
	 * @throws ClassCastException   if the data stored does not implement {@code Comparable}.
	 */
	Data getMaxValue();

	/**
	 * Finds and returns the maximum value in the entire matrix using the provided {@code comparator}.
	 *
	 * @param comparator The comparator to use for comparing data elements.
	 * @return The maximum data element in the matrix according to the comparator.
	 * @throws NullPointerException if the matrix contains any {@code null} values (unless handled by comparator) or if {@code comparator} is null.
	 */
	Data getMaxValue(Comparator<Data> comparator);

	/**
	 * Finds and returns the maximum value in the specified column.
	 * Requires that the data type {@code Data} implements {@link Comparable}.
	 *
	 * @param columnIndex The index of the column to search.
	 * @return The maximum data element in the specified column.
	 * @throws ArrayIndexOutOfBoundsException if {@code columnIndex} is out of range.
	 * @throws NullPointerException           if the column contains any {@code null} values.
	 * @throws ClassCastException             if the data stored does not implement {@code Comparable}.
	 */
	Data getMaxValueOfColumn(int columnIndex);

	/**
	 * Finds and returns the maximum value in the specified column using the provided {@code comparator}.
	 *
	 * @param columnIndex The index of the column to search.
	 * @param comparator  The comparator to use for comparing data elements.
	 * @return The maximum data element in the specified column according to the comparator.
	 * @throws ArrayIndexOutOfBoundsException if {@code columnIndex} is out of range.
	 * @throws NullPointerException           if the column contains any {@code null} values (unless handled by comparator) or if {@code comparator} is null.
	 */
	Data getMaxValueOfColumn(int columnIndex, Comparator<Data> comparator);

	/**
	 * Finds and returns the maximum value in the specified line (row).
	 * Requires that the data type {@code Data} implements {@link Comparable}.
	 *
	 * @param lineIndex The index of the line to search.
	 * @return The maximum data element in the specified line.
	 * @throws ArrayIndexOutOfBoundsException if {@code lineIndex} is out of range.
	 * @throws NullPointerException           if the line contains any {@code null} values.
	 * @throws ClassCastException             if the data stored does not implement {@code Comparable}.
	 */
	Data getMaxValueOfLine(int lineIndex);

	/**
	 * Finds and returns the maximum value in the specified line (row) using the provided {@code comparator}.
	 *
	 * @param lineIndex  The index of the line to search.
	 * @param comparator The comparator to use for comparing data elements.
	 * @return The maximum data element in the specified line according to the comparator.
	 * @throws ArrayIndexOutOfBoundsException if {@code lineIndex} is out of range.
	 * @throws NullPointerException           if the line contains any {@code null} values (unless handled by comparator) or if {@code comparator} is null.
	 */
	Data getMaxValueOfLine(int lineIndex, Comparator<Data> comparator);

	/**
	 * Returns the smaller of the line dimension and column dimension of this matrix.
	 *
	 * @return The minimum dimension (line or column) of this matrix.
	 */
	int getMinDimension();

	/**
	 * Finds and returns the minimum value in the entire matrix.
	 * Requires that the data type {@code Data} implements {@link Comparable}.
	 *
	 * @return The minimum data element in the matrix.
	 * @throws NullPointerException if the matrix contains any {@code null} values.
	 * @throws ClassCastException   if the data stored does not implement {@code Comparable}.
	 */
	Data getMinValue();

	/**
	 * Finds and returns the minimum value in the entire matrix using the provided {@code comparator}.
	 *
	 * @param comparator The comparator to use for comparing data elements.
	 * @return The minimum data element in the matrix according to the comparator.
	 * @throws NullPointerException if the matrix contains any {@code null} values (unless handled by comparator) or if {@code comparator} is null.
	 * @throws ClassCastException if elements are not mutually comparable with the given comparator.
	 */
	Data getMinValue(Comparator<Data> comparator);

	/**
	 * Finds and returns the minimum value in the specified column.
	 * Requires that the data type {@code Data} implements {@link Comparable}.
	 *
	 * @param columnIndex The index of the column to search.
	 * @return The minimum data element in the specified column.
	 * @throws ArrayIndexOutOfBoundsException if {@code columnIndex} is out of range.
	 * @throws NullPointerException           if the column contains any {@code null} values.
	 * @throws ClassCastException             if the data stored does not implement {@code Comparable}.
	 */
	Data getMinValueOfColumn(int columnIndex);

	/**
	 * Finds and returns the minimum value in the specified column using the provided {@code comparator}.
	 *
	 * @param columnIndex The index of the column to search.
	 * @param comparator  The comparator to use for comparing data elements.
	 * @return The minimum data element in the specified column according to the comparator.
	 * @throws ArrayIndexOutOfBoundsException if {@code columnIndex} is out of range.
	 * @throws NullPointerException           if the column contains any {@code null} values (unless handled by comparator) or if {@code comparator} is null.
	 */
	Data getMinValueOfColumn(int columnIndex, Comparator<Data> comparator);

	/**
	 * Finds and returns the minimum value in the specified line (row).
	 * Requires that the data type {@code Data} implements {@link Comparable}.
	 *
	 * @param lineIndex The index of the line to search.
	 * @return The minimum data element in the specified line.
	 * @throws ArrayIndexOutOfBoundsException if {@code lineIndex} is out of range.
	 * @throws NullPointerException           if the line contains any {@code null} values.
	 * @throws ClassCastException             if the data stored does not implement {@code Comparable}.
	 */
	Data getMinValueOfLine(int lineIndex);

	/**
	 * Finds and returns the minimum value in the specified line (row) using the provided {@code comparator}.
	 *
	 * @param lineIndex  The index of the line to search.
	 * @param comparator The comparator to use for comparing data elements.
	 * @return The minimum data element in the specified line according to the comparator.
	 * @throws ArrayIndexOutOfBoundsException if {@code lineIndex} is out of range.
	 * @throws NullPointerException           if the line contains any {@code null} values (unless handled by comparator) or if {@code comparator} is null.
	 */
	Data getMinValueOfLine(int lineIndex, Comparator<Data> comparator);

	/**
	 * Returns the name of this matrix.
	 *
	 * @return The name string of this matrix.
	 */
	String getName();

	/**
	 * Returns the total number of elements in this matrix (i.e., {@code lineDimension * columnDimension}).
	 *
	 * @return The total number of elements.
	 */
	int getNumberOfElements();

	/**
	 * Retrieves the secondary diagonal (top-right to bottom-left, also known as anti-diagonal or counter-diagonal)
	 * of this matrix as an array of {@code Object}.
	 * The length of the array will be {@code min(lineDimension, columnDimension)}.
	 *
	 * @return An array containing the elements of the secondary diagonal.
	 */
	Object[] getSecondDiagonal();

	/**
	 * Retrieves the secondary diagonal (top-right to bottom-left) of this matrix as a {@code List<Data>}.
	 * The size of the list will be {@code min(lineDimension, columnDimension)}.
	 *
	 * @return A list containing the elements of the secondary diagonal.
	 */
	List<Data> getSecondDiagonalAsList();

	/**
	 * Retrieves the value at the specified {@code lineIndex} and {@code columnIndex}.
	 *
	 * @param lineIndex   The line (row) index of the desired element.
	 * @param columnIndex The column index of the desired element.
	 * @return The data element at the specified position.
	 * @throws ArrayIndexOutOfBoundsException if {@code lineIndex} or {@code columnIndex} is out of the matrix's dimension range.
	 */
	Data getValue(int lineIndex, int columnIndex);

	/**
	 * Inserts a new, empty column (filled with nulls) at the specified {@code index}.
	 * Existing columns at or after {@code index} are shifted to the right.
	 * If {@code index} is negative, columns are added at the beginning (e.g., index -1 adds one column at the start).
	 * If {@code index} is greater than the current column dimension, the matrix is resized, and columns are added at the end.
	 *
	 * @param index The index at which to insert the new column.
	 */
	void insertColumn(int index);

	/**
	 * Inserts a new column filled with {@code defaultValue} at the specified {@code index}.
	 * Existing columns at or after {@code index} are shifted to the right.
	 * Handles negative {@code index} and out-of-range {@code index} by resizing and appropriately placing the new column,
	 * filling any newly created empty cells in other parts of the matrix due to resizing with nulls, and the specified column with {@code defaultValue}.
	 *
	 * @param index        The index at which to insert the new column.
	 * @param defaultValue The value to fill the new column with.
	 */
	void insertColumn(int index, Data defaultValue);

	/**
	 * Inserts a new column populated with values from {@code columnArray} at the specified {@code index}.
	 * Existing columns are shifted. Handles negative/out-of-range {@code index} by resizing.
	 * If {@code columnArray} length differs from line dimension, behavior is similar to {@link #addColumn(Data...)}.
	 * Other newly created cells (due to resizing for index placement) are filled with nulls.
	 *
	 * @param index       The index at which to insert.
	 * @param columnArray The data for the new column.
	 * @throws NullPointerException if {@code columnArray} is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void insertColumn(int index, Data... columnArray);

	/**
	 * Inserts a new column populated with values from {@code columnList} at the specified {@code index}.
	 * Similar behavior to {@link #insertColumn(int, Data...)} regarding index handling and list size differences.
	 *
	 * @param index      The index at which to insert.
	 * @param columnList The data for the new column.
	 * @throws NullPointerException if {@code columnList} is null.
	 */
	void insertColumn(int index, List<Data> columnList);

	/**
	 * Inserts the given 2D {@code data} array as a block into this matrix, starting at {@code [lineIndex, columnIndex]}.
	 * The current matrix may be resized to accommodate the inserted data. Cells outside the inserted block
	 * that are part of the resized area (if any) are filled with nulls.
	 * <p>
	 * Example: Inserting a 2x2 block at (1,1) in a 3x3 matrix might result in a 5x5 matrix if the original
	 * content is pushed out.
	 * </p>
	 * @param lineIndex   The starting line index for insertion.
	 * @param columnIndex The starting column index for insertion.
	 * @param data        The 2D array of data to insert.
	 * @throws IllegalArgumentException if {@code lineIndex} or {@code columnIndex} are negative.
	 * @throws NullPointerException     if {@code data} is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void insertData(int lineIndex, int columnIndex, Data[]... data);

	/**
	 * Inserts the given {@code List<List<Data>>} as a block into this matrix, starting at {@code [lineIndex, columnIndex]}.
	 * Behavior regarding resizing and filling of new cells is similar to {@link #insertData(int, int, Data[][])}.
	 *
	 * @param lineIndex   The starting line index for insertion.
	 * @param columnIndex The starting column index for insertion.
	 * @param data        The list of lists of data to insert.
	 * @throws IllegalArgumentException if {@code lineIndex} or {@code columnIndex} are negative.
	 * @throws NullPointerException     if {@code data} or any inner list is null.
	 */
	void insertData(int lineIndex, int columnIndex, List<List<Data>> data);

	/**
	 * Inserts the given {@code value} at the specified {@code [lineIndex, columnIndex]}.
	 * If {@code lineIndex} or {@code columnIndex} are outside the current dimensions, the matrix is resized
	 * automatically to accommodate the new element. New cells created by resizing (other than the one for {@code value})
	 * are filled with nulls. Absolute values of indexes are used for placement if negative.
	 *
	 * @param lineIndex   The line index for the new element.
	 * @param columnIndex The column index for the new element.
	 * @param value       The value to insert.
	 */
	void insertElement(int lineIndex, int columnIndex, Data value);

	/**
	 * Inserts the given {@code value} at {@code [lineIndex, columnIndex]}, resizing if necessary.
	 * New cells created by resizing (other than the one for {@code value}) are filled with {@code defaultValue}.
	 * Absolute values of indexes are used.
	 *
	 * @param lineIndex    The line index for the new element.
	 * @param columnIndex  The column index for the new element.
	 * @param value        The value to insert.
	 * @param defaultValue The value for other new cells created by resizing.
	 */
	void insertElement(int lineIndex, int columnIndex, Data value, Data defaultValue);

	/**
	 * Inserts {@code value} at {@code [lineIndex, columnIndex]}, resizing if necessary.
	 * New lines/columns created by resizing are filled using {@code lineArray} and {@code columnArray} respectively.
	 * If these arrays are shorter than the required dimension, remaining cells are null. Absolute values of indexes are used.
	 *
	 * @param lineIndex   The line index.
	 * @param columnIndex The column index.
	 * @param value       The value to insert.
	 * @param lineArray   Data for newly created parts of the inserted line.
	 * @param columnArray Data for newly created parts of the inserted column.
	 * @throws NullPointerException if {@code lineArray} or {@code columnArray} is null.
	 */
	void insertElement(int lineIndex, int columnIndex, Data value, Data[] lineArray, Data[] columnArray);

	/**
	 * Inserts {@code value} at {@code [lineIndex, columnIndex]}, resizing if necessary.
	 * New lines/columns created by resizing are filled using {@code lineList} and {@code columnList}.
	 * Similar behavior to the array version regarding list sizes. Absolute values of indexes are used.
	 *
	 * @param lineIndex   The line index.
	 * @param columnIndex The column index.
	 * @param value       The value to insert.
	 * @param lineList    Data for newly created parts of the inserted line.
	 * @param columnList  Data for newly created parts of the inserted column.
	 * @throws NullPointerException if {@code lineList} or {@code columnList} is null.
	 */
	void insertElement(int lineIndex, int columnIndex, Data value, List<Data> lineList, List<Data> columnList);

	/**
	 * Inserts a new, empty line (filled with nulls) at the specified {@code index}.
	 * Existing lines at or after {@code index} are shifted down.
	 * Handles negative/out-of-range {@code index} by resizing and appropriately placing the new line.
	 *
	 * @param index The index at which to insert the new line.
	 */
	void insertLine(int index);

	/**
	 * Inserts a new line filled with {@code defaultValue} at the specified {@code index}.
	 * Existing lines are shifted. Handles negative/out-of-range {@code index} by resizing.
	 * Other newly created cells (due to resizing for index placement) are filled with nulls, and the specified line with {@code defaultValue}.
	 *
	 * @param index        The index at which to insert.
	 * @param defaultValue The value to fill the new line with.
	 */
	void insertLine(int index, Data defaultValue);

	/**
	 * Inserts a new line populated with values from {@code lineArray} at the specified {@code index}.
	 * Existing lines are shifted. Handles negative/out-of-range {@code index} by resizing.
	 * If {@code lineArray} length differs from column dimension, behavior is similar to {@link #addLine(Data...)}.
	 * Other newly created cells (due to resizing for index placement) are filled with nulls.
	 *
	 * @param index     The index at which to insert.
	 * @param lineArray The data for the new line.
	 * @throws NullPointerException if {@code lineArray} is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void insertLine(int index, Data... lineArray);

	/**
	 * Inserts a new line populated with values from {@code lineList} at the specified {@code index}.
	 * Similar behavior to {@link #insertLine(int, Data...)} regarding index handling and list size differences.
	 *
	 * @param index    The index at which to insert.
	 * @param lineList The data for the new line.
	 * @throws NullPointerException if {@code lineList} is null.
	 */
	void insertLine(int index, List<Data> lineList);

	/**
	 * Inserts the given {@code matrix} as a block into this matrix, starting at {@code [lineIndex, columnIndex]}.
	 * The current matrix may be resized to accommodate the inserted matrix. Cells outside the inserted block
	 * that are part of the resized area (if any) are filled with nulls.
	 * <p>
	 * Example: Inserting a 2x2 matrix B at (1,1) in a 3x3 matrix A might result in A becoming a 5x5 matrix
	 * if the original content of A needs to be shifted to make space.
	 * </p>
	 * @param lineIndex   The starting line index for insertion.
	 * @param columnIndex The starting column index for insertion.
	 * @param matrix      The matrix to insert.
	 * @throws IllegalArgumentException if {@code lineIndex} or {@code columnIndex} are negative.
	 * @throws NullPointerException     if {@code matrix} is null.
	 */
	void insertMatrix(int lineIndex, int columnIndex, IDataMatrix<Data> matrix);

	/**
	 * Checks if the matrix contains the specified {@code value}.
	 * Equality is typically determined by the {@code equals} method of the data elements.
	 *
	 * @param value The value to search for.
	 * @return {@code true} if the matrix contains the value, {@code false} otherwise.
	 */
	boolean is(Data value);

	/**
	 * Checks if the given {@code columnIndex} is a valid column index for this matrix.
	 * (i.e., {@code 0 <= columnIndex < columnDimension}).
	 *
	 * @param columnIndex The column index to check.
	 * @return {@code true} if valid, {@code false} otherwise.
	 */
	boolean isColumnIndex(int columnIndex);

	/**
	 * Checks if this matrix is a column vector (i.e., has one column and more than one line).
	 *
	 * @return {@code true} if it is a column vector, {@code false} otherwise.
	 */
	boolean isColumnVector();

	/**
	 * Checks if the dimensions of this matrix are equal to the specified {@code lineDimension} and {@code columnDimension}.
	 *
	 * @param lineDimension   The line dimension to compare against.
	 * @param columnDimension The column dimension to compare against.
	 * @return {@code true} if dimensions are equal, {@code false} otherwise.
	 */
	boolean isDimensionsEqualTo(int lineDimension, int columnDimension);

	/**
	 * Checks if every element in this matrix is equal to the specified {@code variable}.
	 * Equality is determined by the {@code equals} method of the data elements.
	 *
	 * @param variable The value to compare every element against.
	 * @return {@code true} if all elements are equal to {@code variable}, {@code false} otherwise.
	 */
	boolean isEveryElementEqualTo(Data variable);

	/**
	 * Checks if no element in this matrix is equal to the specified {@code variable}.
	 * Equality is determined by the {@code equals} method of the data elements.
	 *
	 * @param variable The value to compare against.
	 * @return {@code true} if no element is equal to {@code variable}, {@code false} otherwise.
	 */
	boolean isEveryElementNotEqualTo(Data variable);

	/**
	 * Checks if the given {@code [lineIndex, columnIndex]} are valid indices for this matrix.
	 *
	 * @param lineIndex   The line index to check.
	 * @param columnIndex The column index to check.
	 * @return {@code true} if both indices are valid, {@code false} otherwise.
	 */
	boolean isIndexes(int lineIndex, int columnIndex);

	/**
	 * Checks if the given {@code lineIndex} is a valid line index for this matrix.
	 * (i.e., {@code 0 <= lineIndex < lineDimension}).
	 *
	 * @param lineIndex The line index to check.
	 * @return {@code true} if valid, {@code false} otherwise.
	 */
	boolean isLineIndex(int lineIndex);

	/**
	 * Checks if this is a "true" matrix (i.e., has more than one line AND more than one column).
	 *
	 * @return {@code true} if {@code lineDimension > 1 && columnDimension > 1}, {@code false} otherwise.
	 */
	boolean isMatrix();

	/**
	 * Checks if this matrix contains at least one {@code null} element.
	 *
	 * @return {@code true} if there is at least one null element, {@code false} otherwise.
	 */
	boolean isNull();

	/**
	 * Checks if this matrix is rectangular (i.e., line dimension is not equal to column dimension).
	 *
	 * @return {@code true} if dimensions are not equal, {@code false} otherwise.
	 */
	boolean isRectangular();

	/**
	 * Checks if this matrix is a scalar (i.e., has dimensions 1x1).
	 *
	 * @return {@code true} if it is a 1x1 matrix, {@code false} otherwise.
	 */
	boolean isScalar();

	/**
	 * Checks if this matrix is square (i.e., line dimension equals column dimension).
	 *
	 * @return {@code true} if dimensions are equal, {@code false} otherwise.
	 */
	boolean isSquare();

	/**
	 * Checks if this matrix is a row vector (i.e., has one line and more than one column).
	 *
	 * @return {@code true} if it is a row vector, {@code false} otherwise.
	 */
	boolean isVector();

	/**
	 * Creates and returns a new matrix by joining the columns of the specified {@code matrix} to the right of this matrix.
	 * The resulting matrix will have a line dimension equal to {@code max(this.lineDimension, matrix.lineDimension)}
	 * and a column dimension equal to {@code this.columnDimension + matrix.columnDimension}.
	 * Cells in the expanded area not covered by either original matrix are filled with nulls.
	 *
	 * @param matrix The matrix to join with this one.
	 * @return A new {@code DataMatrix} instance representing the joined matrix.
	 * @throws NullPointerException if {@code matrix} is null.
	 */
	DataMatrix<Data> joinMatrixAtColumn(IDataMatrix<Data> matrix);

	/**
	 * Creates and returns a new matrix by joining the columns of the specified {@code matrix} to the right of this matrix.
	 * Uses {@code defaultValue} to fill cells in the expanded area not covered by either original matrix.
	 *
	 * @param matrix       The matrix to join with this one.
	 * @param defaultValue The value for new cells created by differing dimensions.
	 * @return A new {@code DataMatrix} instance representing the joined matrix.
	 * @throws NullPointerException if {@code matrix} is null.
	 */
	DataMatrix<Data> joinMatrixAtColumn(IDataMatrix<Data> matrix, Data defaultValue);

	/**
	 * Creates and returns a new matrix by joining the rows of the specified {@code matrix} below this matrix.
	 * The resulting matrix will have a column dimension equal to {@code max(this.columnDimension, matrix.columnDimension)}
	 * and a line dimension equal to {@code this.lineDimension + matrix.lineDimension}.
	 * Cells in the expanded area not covered by either original matrix are filled with nulls.
	 *
	 * @param matrix The matrix to join with this one.
	 * @return A new {@code DataMatrix} instance representing the joined matrix.
	 * @throws NullPointerException if {@code matrix} is null.
	 */
	DataMatrix<Data> joinMatrixAtLine(IDataMatrix<Data> matrix);

	/**
	 * Creates and returns a new matrix by joining the rows of the specified {@code matrix} below this matrix.
	 * Uses {@code defaultValue} to fill cells in the expanded area not covered by either original matrix.
	 *
	 * @param matrix       The matrix to join with this one.
	 * @param defaultValue The value for new cells created by differing dimensions.
	 * @return A new {@code DataMatrix} instance representing the joined matrix.
	 * @throws NullPointerException if {@code matrix} is null.
	 */
	DataMatrix<Data> joinMatrixAtLine(IDataMatrix<Data> matrix, Data defaultValue);

	/**
	 * Removes the column at the specified {@code index}. Subsequent columns are shifted to the left.
	 *
	 * @param index The index of the column to remove.
	 * @throws ArrayIndexOutOfBoundsException if {@code index} is out of the matrix's column dimension range.
	 * @throws IllegalArgumentException       if the matrix has only one column and removal would make it empty (0 columns).
	 */
	void removeColumn(int index);

	/**
	 * Removes the element at the specified {@code [lineIndex, columnIndex]} by removing both the entire line
	 * and the entire column that intersect at this element. This significantly reduces matrix dimensions.
	 *
	 * @param lineIndex   The line index of the element whose line and column are to be removed.
	 * @param columnIndex The column index of the element.
	 * @throws ArrayIndexOutOfBoundsException if {@code lineIndex} or {@code columnIndex} are out of range.
	 * @throws IllegalArgumentException       if removing the line or column would result in an invalid matrix (e.g., 0 lines or 0 columns).
	 */
	void removeElement(int lineIndex, int columnIndex);

	/**
	 * Removes the last column of this matrix.
	 *
	 * @throws IllegalArgumentException if the matrix has only one column and removal would make it empty (0 columns).
	 */
	void removeColumn();

	/**
	 * Removes the last line (row) of this matrix.
	 *
	 * @throws IllegalArgumentException if the matrix has only one line and removal would make it empty (0 lines).
	 */
	void removeLine();

	/**
	 * Removes the line (row) at the specified {@code index}. Subsequent lines are shifted up.
	 *
	 * @param index The index of the line to remove.
	 * @throws ArrayIndexOutOfBoundsException if {@code index} is out of the matrix's line dimension range.
	 * @throws IllegalArgumentException       if the matrix has only one line and removal would make it empty (0 lines).
	 */
	void removeLine(int index);

	/**
	 * Resizes the matrix to the specified {@code lineDimension} and {@code columnDimension}.
	 * If new dimensions are smaller, data is truncated.
	 * If new dimensions are larger, new cells are filled with {@code null}.
	 *
	 * @param lineDimension   The new number of lines (rows).
	 * @param columnDimension The new number of columns.
	 * @throws IllegalArgumentException if {@code lineDimension} or {@code columnDimension} are not positive.
	 */
	void resize(int lineDimension, int columnDimension);

	/**
	 * Resizes the matrix to a square matrix of the specified {@code squareDimension}.
	 * Behavior regarding data truncation or null-filling for new cells is the same as {@link #resize(int, int)}.
	 *
	 * @param squareDimension The new number of lines and columns.
	 * @throws IllegalArgumentException if {@code squareDimension} is not positive.
	 */
	void resize(int squareDimension);

	/**
	 * Resizes the matrix to the specified dimensions.
	 * If new dimensions are smaller, data is truncated.
	 * If new dimensions are larger, new cells are filled with the {@code defaultValue}.
	 *
	 * @param lineDimension   The new number of lines.
	 * @param columnDimension The new number of columns.
	 * @param defaultValue    The value for new cells if the matrix is enlarged.
	 * @throws IllegalArgumentException if {@code lineDimension} or {@code columnDimension} are not positive.
	 */
	void resize(int lineDimension, int columnDimension, Data defaultValue);

	/**
	 * Resizes the matrix to a square matrix of {@code squareDimension}.
	 * New cells (if any) are filled with {@code defaultValue}.
	 *
	 * @param squareDimension The new number of lines and columns.
	 * @param defaultValue    The value for new cells if the matrix is enlarged.
	 * @throws IllegalArgumentException if {@code squareDimension} is not positive.
	 */
	void resize(int squareDimension, Data defaultValue);

	/**
	 * Reverses the order of elements in the column at the specified {@code columnIndex}.
	 *
	 * @param columnIndex The index of the column to reverse.
	 * @throws ArrayIndexOutOfBoundsException if {@code columnIndex} is out of range.
	 */
	void reverseColumn(int columnIndex);

	/**
	 * Reverses the order of elements in all columns of this matrix.
	 */
	void reverseColumns();

	/**
	 * Reverses the order of elements in the line (row) at the specified {@code lineIndex}.
	 *
	 * @param lineIndex The index of the line to reverse.
	 * @throws ArrayIndexOutOfBoundsException if {@code lineIndex} is out of range.
	 */
	void reverseLine(int lineIndex);

	/**
	 * Reverses the order of elements in all lines (rows) of this matrix.
	 */
	void reverseLines();

	/**
	 * Sets the content of the column at {@code columnIndex} with values from {@code columnArray}.
	 * If {@code columnArray} length is shorter than line dimension, remaining cells are null.
	 * If longer, excess values are ignored.
	 *
	 * @param columnIndex The index of the column to modify.
	 * @param columnArray The data to set in the column.
	 * @throws ArrayIndexOutOfBoundsException if {@code columnIndex} is out of range.
	 * @throws NullPointerException           if {@code columnArray} is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void setColumn(int columnIndex, Data... columnArray);

	/**
	 * Sets the content of the column at {@code columnIndex} with values from {@code columnList}.
	 * Similar behavior to the array version regarding list size.
	 *
	 * @param columnIndex The index of the column to modify.
	 * @param columnList  The data to set in the column.
	 * @throws ArrayIndexOutOfBoundsException if {@code columnIndex} is out of range.
	 * @throws NullPointerException           if {@code columnList} is null.
	 */
	void setColumn(int columnIndex, List<Data> columnList);

	/**
	 * Fills all cells in the column at {@code columnIndex} with the specified {@code value}.
	 *
	 * @param columnIndex The index of the column to fill.
	 * @param value       The value to set for all cells in the column.
	 * @throws ArrayIndexOutOfBoundsException if {@code columnIndex} is out of range.
	 */
	void setColumn(int columnIndex, Data value);

	/**
	 * Replaces the entire data of this matrix with the data from the given 2D array {@code data}.
	 * Dimensions are automatically adjusted. Jagged arrays are supported.
	 *
	 * @param data The new 2D array data for the matrix.
	 * @throws NullPointerException if {@code data} is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void setData(Data[]... data);

	/**
	 * Replaces the entire data of this matrix with the data from the given {@code List<List<Data>>}.
	 * Dimensions are automatically adjusted. Jagged lists are supported.
	 *
	 * @param data The new list of lists data for the matrix.
	 * @throws NullPointerException     if {@code data} or any inner list is null.
	 * @throws IllegalArgumentException if {@code data} is empty.
	 */
	void setData(List<List<Data>> data);

	/**
	 * Fills the specified diagonal with {@code value}.
	 * See {@link #getDiagonal(int)} for {@code index} interpretation.
	 *
	 * @param index The index of the diagonal to fill.
	 * @param value The value to set for elements on the diagonal.
	 * @throws ArrayIndexOutOfBoundsException if {@code index} is not a valid diagonal index.
	 */
	void setDiagonal(int index, Data value);

	/**
	 * Fills the specified anti-diagonal (right-to-left) with {@code value}.
	 * See {@link #getDiagonal2(int)} for {@code index} interpretation.
	 *
	 * @param index The index of the anti-diagonal to fill.
	 * @param value The value to set for elements on the anti-diagonal.
	 * @throws ArrayIndexOutOfBoundsException if {@code index} is not a valid anti-diagonal index.
	 */
	void setDiagonal2(int index, Data value);

	/**
	 * Sets the unique identifier (ID) for this matrix.
	 *
	 * @param id The new ID string.
	 * @throws IllegalArgumentException if {@code id} is null, empty, or not unique (if applicable to implementation).
	 */
	void setId(String id);

	/**
	 * Sets the value of the last element of this matrix (element at {@code [lineDimension-1, columnDimension-1]}).
	 *
	 * @param value The new value for the last element.
	 * @throws ArrayIndexOutOfBoundsException if the matrix is empty.
	 */
	void setLastElement(Data value);

	/**
	 * Sets the content of the line at {@code lineIndex} with values from {@code lineArray}.
	 * If {@code lineArray} length is shorter than column dimension, remaining cells are null.
	 * If longer, excess values are ignored.
	 *
	 * @param lineIndex The index of the line to modify.
	 * @param lineArray The data to set in the line.
	 * @throws ArrayIndexOutOfBoundsException if {@code lineIndex} is out of range.
	 * @throws NullPointerException           if {@code lineArray} is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void setLine(int lineIndex, Data... lineArray);

	/**
	 * Sets the content of the line at {@code lineIndex} with values from {@code lineList}.
	 * Similar behavior to the array version regarding list size.
	 *
	 * @param lineIndex The index of the line to modify.
	 * @param lineList  The data to set in the line.
	 * @throws ArrayIndexOutOfBoundsException if {@code lineIndex} is out of range.
	 * @throws NullPointerException           if {@code lineList} is null.
	 */
	void setLine(int lineIndex, List<Data> lineList);

	/**
	 * Fills all cells in the line at {@code lineIndex} with the specified {@code value}.
	 *
	 * @param lineIndex The index of the line to fill.
	 * @param value     The value to set for all cells in the line.
	 * @throws ArrayIndexOutOfBoundsException if {@code lineIndex} is out of range.
	 */
	void setLine(int lineIndex, Data value);

	/**
	 * Fills all cells on the main diagonal (top-left to bottom-right) with {@code value}.
	 *
	 * @param value The value to set for elements on the main diagonal.
	 */
	void setMainDiagonal(Data value);

	/**
	 * Sets the main diagonal with values from {@code diagonalArray}.
	 * If array length is shorter than min dimension, remaining diagonal cells are null.
	 * If longer, excess values are ignored.
	 *
	 * @param diagonalArray The data for the main diagonal.
	 * @throws NullPointerException if {@code diagonalArray} is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void setMainDiagonal(Data... diagonalArray);

	/**
	 * Sets the main diagonal with values from {@code diagonalList}.
	 * Similar behavior to the array version regarding list size.
	 *
	 * @param diagonalList The data for the main diagonal.
	 * @throws NullPointerException if {@code diagonalList} is null.
	 */
	void setMainDiagonal(List<Data> diagonalList);

	/**
	 * Sets the name for this matrix.
	 *
	 * @param name The new name string.
	 * @throws NullPointerException if {@code name} is null.
	 */
	void setName(String name);

	/**
	 * Fills all cells on the secondary diagonal (top-right to bottom-left) with {@code value}.
	 *
	 * @param value The value to set for elements on the secondary diagonal.
	 */
	void setSecondDiagonal(Data value);

	/**
	 * Sets the secondary diagonal with values from {@code diagonalArray}.
	 * Similar behavior to {@link #setMainDiagonal(Data...)}.
	 *
	 * @param diagonalArray The data for the secondary diagonal.
	 * @throws NullPointerException if {@code diagonalArray} is null.
	 */
	@SuppressWarnings(value = "unchecked")
	void setSecondDiagonal(Data... diagonalArray);

	/**
	 * Sets the secondary diagonal with values from {@code diagonalList}.
	 * Similar behavior to {@link #setMainDiagonal(List)}.
	 *
	 * @param diagonalList The data for the secondary diagonal.
	 * @throws NullPointerException if {@code diagonalList} is null.
	 */
	void setSecondDiagonal(List<Data> diagonalList);

	/**
	 * Sets the value at the specified {@code [lineIndex, columnIndex]}.
	 *
	 * @param lineIndex   The line index of the element to set.
	 * @param columnIndex The column index of the element to set.
	 * @param value       The new value for the element.
	 * @throws ArrayIndexOutOfBoundsException if {@code lineIndex} or {@code columnIndex} is out of range.
	 */
	void setValue(int lineIndex, int columnIndex, Data value);

	/**
	 * Sorts the column at the specified {@code index} in ascending order.
	 * Requires that data type {@code Data} implements {@link Comparable}.
	 *
	 * @param index The index of the column to sort.
	 * @throws ArrayIndexOutOfBoundsException if {@code index} is out of range.
	 * @throws NullPointerException           if the column contains {@code null} values.
	 * @throws ClassCastException             if data does not implement {@code Comparable}.
	 */
	void sortColumnAsc(int index);

	/**
	 * Sorts the column at the specified {@code index} using the provided {@code comparator}.
	 *
	 * @param index      The index of the column to sort.
	 * @param comparator The comparator to determine the order.
	 * @throws ArrayIndexOutOfBoundsException if {@code index} is out of range.
	 * @throws NullPointerException           if {@code comparator} is null or the column contains nulls not handled by comparator.
	 */
	void sortColumn(int index, Comparator<Data> comparator);

	/**
	 * Sorts the column at the specified {@code index} in descending order.
	 * Requires that data type {@code Data} implements {@link Comparable}.
	 *
	 * @param index The index of the column to sort.
	 * @throws ArrayIndexOutOfBoundsException if {@code index} is out of range.
	 * @throws NullPointerException           if the column contains {@code null} values.
	 * @throws ClassCastException             if data does not implement {@code Comparable}.
	 */
	void sortColumnDesc(int index);

	/**
	 * Sorts the line (row) at the specified {@code index} in ascending order.
	 * Requires that data type {@code Data} implements {@link Comparable}.
	 *
	 * @param index The index of the line to sort.
	 * @throws ArrayIndexOutOfBoundsException if {@code index} is out of range.
	 * @throws NullPointerException           if the line contains {@code null} values.
	 * @throws ClassCastException             if data does not implement {@code Comparable}.
	 */
	void sortLineAsc(int index);

	/**
	 * Sorts the line (row) at the specified {@code index} using the provided {@code comparator}.
	 *
	 * @param index      The index of the line to sort.
	 * @param comparator The comparator to determine the order.
	 * @throws ArrayIndexOutOfBoundsException if {@code index} is out of range.
	 * @throws NullPointerException           if {@code comparator} is null or the line contains nulls not handled by comparator.
	 */
	void sortLine(int index, Comparator<Data> comparator);

	/**
	 * Sorts the line (row) at the specified {@code index} in descending order.
	 * Requires that data type {@code Data} implements {@link Comparable}.
	 *
	 * @param index The index of the line to sort.
	 * @throws ArrayIndexOutOfBoundsException if {@code index} is out of range.
	 * @throws NullPointerException           if the line contains {@code null} values.
	 * @throws ClassCastException             if data does not implement {@code Comparable}.
	 */
	void sortLineDesc(int index);

	/**
	 * Sorts all columns of this matrix in ascending order.
	 * Requires that data type {@code Data} implements {@link Comparable}.
	 *
	 * @throws NullPointerException if any column contains {@code null} values.
	 * @throws ClassCastException   if data does not implement {@code Comparable}.
	 */
	void sortColumnsAsc();

	/**
	 * Sorts all columns of this matrix using the provided {@code comparator}.
	 *
	 * @param comparator The comparator to determine the order for each column.
	 * @throws NullPointerException if {@code comparator} is null or any column contains nulls not handled by comparator.
	 */
	void sortColumns(Comparator<Data> comparator);

	/**
	 * Sorts all columns of this matrix in descending order.
	 * Requires that data type {@code Data} implements {@link Comparable}.
	 *
	 * @throws NullPointerException if any column contains {@code null} values.
	 * @throws ClassCastException   if data does not implement {@code Comparable}.
	 */
	void sortColumnsDesc();

	/**
	 * Sorts all lines (rows) of this matrix in ascending order.
	 * Requires that data type {@code Data} implements {@link Comparable}.
	 *
	 * @throws NullPointerException if any line contains {@code null} values.
	 * @throws ClassCastException   if data does not implement {@code Comparable}.
	 */
	void sortLinesAsc();

	/**
	 * Sorts all lines (rows) of this matrix using the provided {@code comparator}.
	 *
	 * @param comparator The comparator to determine the order for each line.
	 * @throws NullPointerException if {@code comparator} is null or any line contains nulls not handled by comparator.
	 */
	void sortLines(Comparator<Data> comparator);

	/**
	 * Sorts all lines (rows) of this matrix in descending order.
	 * Requires that data type {@code Data} implements {@link Comparable}.
	 *
	 * @throws NullPointerException if any line contains {@code null} values.
	 * @throws ClassCastException   if data does not implement {@code Comparable}.
	 */
	void sortLinesDesc();

	/**
	 * Extracts a sub-matrix from this matrix.
	 * The sub-matrix includes elements from {@code startLineIndex} to {@code endLineIndex} (inclusive)
	 * and from {@code startColumnIndex} to {@code endColumnIndex} (inclusive).
	 *
	 * @param startLineIndex   The starting line index of the sub-matrix.
	 * @param startColumnIndex The starting column index of the sub-matrix.
	 * @param endLineIndex     The ending line index of the sub-matrix.
	 * @param endColumnIndex   The ending column index of the sub-matrix.
	 * @return A new {@code DataMatrix} instance representing the sub-matrix.
	 * @throws ArrayIndexOutOfBoundsException if any index is out of range.
	 * @throws IllegalArgumentException       if {@code startLineIndex > endLineIndex} or {@code startColumnIndex > endColumnIndex}.
	 */
	DataMatrix<Data> subMatrix(int startLineIndex, int startColumnIndex, int endLineIndex, int endColumnIndex);

	/**
	 * Swaps the entire content (data, dimensions, name, ID) of this matrix with the specified {@code matrix}.
	 *
	 * @param matrix The matrix to swap content with.
	 * @throws NullPointerException if {@code matrix} is null.
	 */
	void swap(IDataMatrix<Data> matrix);

	/**
	 * Swaps the columns at {@code columnIndex_1} and {@code columnIndex_2}.
	 *
	 * @param columnIndex_1 The index of the first column to swap.
	 * @param columnIndex_2 The index of the second column to swap.
	 * @throws ArrayIndexOutOfBoundsException if either index is out of range.
	 */
	void swapColumns(int columnIndex_1, int columnIndex_2);

	/**
	 * Swaps only the data (and dimensions) of this matrix with the specified {@code matrix}.
	 * Name and ID of both matrices remain unchanged.
	 *
	 * @param matrix The matrix to swap data with.
	 * @throws NullPointerException if {@code matrix} is null.
	 */
	void swapData(IDataMatrix<Data> matrix);

	/**
	 * Swaps the data elements at {@code [lineIndex_1, columnIndex_1]} and {@code [lineIndex_2, columnIndex_2]}.
	 *
	 * @param lineIndex_1   The line index of the first element.
	 * @param columnIndex_1 The column index of the first element.
	 * @param lineIndex_2   The line index of the second element.
	 * @param columnIndex_2 The column index of the second element.
	 * @throws ArrayIndexOutOfBoundsException if any index is out of range.
	 */
	void swapData(int lineIndex_1, int columnIndex_1, int lineIndex_2, int columnIndex_2);

	/**
	 * Finds the first occurrences of {@code data_1} and {@code data_2} in the matrix and swaps them.
	 * Element equality is determined by their {@code equals} method.
	 *
	 * @param data_1 The first data element to swap.
	 * @param data_2 The second data element to swap.
	 * @throws IllegalArgumentException if either {@code data_1} or {@code data_2} (or both) are not found in the matrix.
	 * @throws NullPointerException     if {@code data_1} or {@code data_2} is null.
	 */
	void swapData(Data data_1, Data data_2);

	/**
	 * Swaps the lines (rows) at {@code lineIndex_1} and {@code lineIndex_2}.
	 *
	 * @param lineIndex_1 The index of the first line to swap.
	 * @param lineIndex_2 The index of the second line to swap.
	 * @throws ArrayIndexOutOfBoundsException if either index is out of range.
	 */
	void swapLines(int lineIndex_1, int lineIndex_2);

	/**
	 * Converts the matrix into a single {@code List<Data>}, with elements added row by row.
	 *
	 * @return A list containing all elements of the matrix in row-major order.
	 */
	List<Data> toList();

	/**
	 * Converts the matrix into a single {@code List<Data>}, with elements added column by column.
	 *
	 * @return A list containing all elements of the matrix in column-major order.
	 */
	List<Data> toListByColumn();

	/**
	 * Transposes this matrix (swaps rows with columns). This operation modifies the current matrix.
	 *
	 * @return A reference to this transposed matrix.
	 */
	DataMatrix<Data> transpose();

	/**
	 * Writes the matrix object to a file using Java serialization.
	 * The data type {@code Data} (if not primitive) must implement {@link java.io.Serializable}.
	 *
	 * @param fileName The name of the file to write the serialized object to.
	 * @throws java.io.FileNotFoundException    if the file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason.
	 * @throws java.io.IOException              if an I/O error occurs while writing to the file.
	 * @throws NullPointerException             if {@code fileName} is null.
	 * @throws java.io.NotSerializableException if any object to be serialized does not implement the {@code java.io.Serializable} interface.
	 */
	void writeObject(String fileName) throws java.io.FileNotFoundException, java.io.IOException, NullPointerException, java.io.NotSerializableException;

	/**
	 * Writes the string representation of the matrix data to the specified file.
	 * Elements are separated by a single space. Each row is on a new line.
	 *
	 * @param fileName The name of the file to write to.
	 * @throws java.io.FileNotFoundException if the file cannot be opened or created.
	 * @throws java.io.IOException           if an I/O error occurs.
	 * @throws NullPointerException          if {@code fileName} is null.
	 */
	void write(String fileName) throws java.io.FileNotFoundException, java.io.IOException, NullPointerException;

	/**
	 * Writes the string representation of the matrix data to the specified file, using {@code separator} between elements.
	 * Each row is on a new line.
	 *
	 * @param fileName  The name of the file to write to.
	 * @param separator The character to use for separating elements in a row.
	 * @throws java.io.FileNotFoundException if the file cannot be opened or created.
	 * @throws java.io.IOException           if an I/O error occurs.
	 * @throws NullPointerException          if {@code fileName} is null.
	 */
	void write(String fileName, char separator) throws java.io.FileNotFoundException, java.io.IOException, NullPointerException;

	/**
	 * Writes the string representation of the matrix data to the specified file.
	 * If {@code append} is true, data is written to the end of the file.
	 * Elements are separated by a single space. Each row is on a new line.
	 *
	 * @param fileName The name of the file to write to.
	 * @param append   If {@code true}, then data will be written to the end of the file rather than the beginning.
	 * @throws java.io.FileNotFoundException if the file cannot be opened or created.
	 * @throws java.io.IOException           if an I/O error occurs.
	 * @throws NullPointerException          if {@code fileName} is null.
	 */
	void write(String fileName, boolean append) throws java.io.FileNotFoundException, java.io.IOException, NullPointerException;

	/**
	 * Writes the string representation of the matrix data to the specified file, using {@code separator}.
	 * If {@code append} is true, data is written to the end of the file.
	 *
	 * @param fileName  The name of the file to write to.
	 * @param separator The character for separating elements.
	 * @param append    If {@code true}, data is written to the end of the file.
	 * @throws java.io.FileNotFoundException if the file cannot be opened or created.
	 * @throws java.io.IOException           if an I/O error occurs.
	 * @throws NullPointerException          if {@code fileName} is null.
	 */
	void write(String fileName, char separator, boolean append) throws java.io.FileNotFoundException, java.io.IOException, NullPointerException;

}