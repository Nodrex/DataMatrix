package com.nodrex.datamatrix;

import java.io.Serializable;

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
public enum MatrixTypes implements Serializable {
	scalar, vector, columnVector, matrix, zero, eye, ones, hilbert
};