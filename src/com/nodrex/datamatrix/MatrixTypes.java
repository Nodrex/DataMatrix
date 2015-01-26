package com.nodrex.datamatrix;

/**
 * <p>
 * This enum contains type of matrices
 * </p>
 * <p>
 * Implements Serializable interfaces.
 * </p>
 * 
 * @author NODREX
 * @version 1.0
 * @since 2013
 * 
 */
public enum MatrixTypes implements java.io.Serializable {
	scalar, vector, columnVector, matrix, zero, eye, ones, hilbert
};