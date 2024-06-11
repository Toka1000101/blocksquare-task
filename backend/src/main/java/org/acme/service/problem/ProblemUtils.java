package org.acme.service.problem;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.exception.custom.IncorrectMatrixFormatException;

import java.util.Arrays;
import java.util.List;

public class ProblemUtils {

    private ProblemUtils(){}

    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 8;

    protected static void validateMatrix(int[][] matrix) {
        if(matrix == null) {
            throw new IncorrectMatrixFormatException();
        }

        int rowsCount = matrix.length;

        // Check if the matrix is a square matrix and if size is within the acceptable range
        boolean isSquareAndCorrectSize =  (rowsCount >= MIN_SIZE && rowsCount <= MAX_SIZE) &&
                Arrays.stream(matrix)
                .allMatch(row -> row.length == rowsCount);

        // Check if every element in the matrix is either 1 or 0
        boolean allElementsValid = Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .allMatch(e -> e == 1 || e == 0);

        if (!isSquareAndCorrectSize || !allElementsValid) {
            throw new IncorrectMatrixFormatException();
        }
    }

    protected static List<Boolean> mapToBooleanList(int[][] matrix) {

        return Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .mapToObj(e -> e != 0)
                .toList();
    }

}
