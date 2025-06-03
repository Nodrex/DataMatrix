package com.nodrex.datamatrix;

import java.io.Serializable;

/**
 * Represents predefined types or categories of matrices.
 * This enum is primarily used to specify the desired matrix structure or content
 * in factory methods, such as those found in the {@link Matrix} class.
 * <p>
 * Implements {@link java.io.Serializable} interface.
 * </p>
 *
 * @author NODREX
 * @version 1.0
 * @since 2013
 * @see Matrix#create(MatrixTypes, int...)
 * @see Matrix#create(MatrixTypes, double, int...)
 * @see java.io.Serializable
 */
public enum MatrixTypes implements Serializable {
	/** Represents a scalar matrix, typically a 1x1 matrix. */
	scalar,
	/** Represents a row vector, a matrix with 1 row and N columns. */
	vector,
	/** Represents a column vector, a matrix with M rows and 1 column. */
	columnVector,
	/** Represents a general matrix with M rows and N columns. */
	matrix,
	/** Represents a zero matrix, where all elements are 0. */
	zero,
	/** Represents an identity matrix (eye matrix), with 1s on the main diagonal and 0s elsewhere. Must be square. */
	eye,
	/** Represents a matrix where all elements are 1. */
	ones,
	/** Represents a Hilbert matrix, a square matrix with elements H(i,j) = 1/(i+j-1). */
	hilbert
};