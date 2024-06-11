package org.acme.exception.custom;

public class IncorrectMatrixFormatException extends RuntimeException{
    public IncorrectMatrixFormatException() {
        super("Format of matrix is incorrect. Correct formats (3x3, 4x4, 5x5, 6x6, 7x7 and 8x8)");
    }
}
