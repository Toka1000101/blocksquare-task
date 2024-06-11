package org.acme.utils;

import lombok.Getter;
import org.acme.exception.custom.SolutionDoesNotExistException;
import org.acme.service.problem.ProblemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Getter
public  class LightsOutSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProblemServiceImpl.class);
    private int[][] initialBoard;
    private int size;
    private long numberOfMoves;
    private long timeToRun;

    public  LightsOutSolver(int[][] initialBoard) {
        this.initialBoard = initialBoard;
        this.size = initialBoard.length;
    }

    public int[] solve() throws SolutionDoesNotExistException {
        long startTime = System.nanoTime();
        int[][] A = createMatrixA();
        int[] b = boardToVector(initialBoard);
        int[] solution = gaussJordanElimination(A, b);
        long endTime = System.nanoTime();
        this.timeToRun = (endTime - startTime)/ 1_000_000;
        return solution;
    }

    private void addToggleEffect(int[][] A, int row, int col) {
        int index = row * size + col;

        A[index][index] = 1;

        // Up
        if (row > 0) A[index][index - size] = 1;
        // Down
        if (row < size - 1) A[index][index + size] = 1;
        // Left
        if (col > 0) A[index][index - 1] = 1;
        // Right
        if (col < size - 1) A[index][index + 1] = 1;
    }

    private int[] boardToVector(int[][] board) {
        int[] vector = new int[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                vector[i * size + j] = board[i][j];
            }
        }
        return vector;
    }


    private int[][] createMatrixA() {
        int n = size * size;
        int[][] A = new int[n][n];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                addToggleEffect(A, i, j);
            }
        }
        return A;
    }

    private int[] gaussJordanElimination(int[][] A, int[] b) throws SolutionDoesNotExistException {
        int n = b.length;
        int[][] augmented = new int[n][n + 1];

        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, augmented[i], 0, n);
            augmented[i][n] = b[i];
        }

        for (int i = 0; i < n; i++) {
            if (augmented[i][i] == 0) {
                for (int j = i + 1; j < n; j++) {
                    if (augmented[j][i] == 1) {
                        int[] temp = augmented[i];
                        augmented[i] = augmented[j];
                        augmented[j] = temp;
                        break;
                    }
                }
            }

            for (int j = 0; j < n; j++) {
                if (i != j && augmented[j][i] == 1) {
                    for (int k = 0; k <= n; k++) {
                        augmented[j][k] ^= augmented[i][k];
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (Arrays.stream(augmented[i], 0, n).allMatch(x -> x == 0) && augmented[i][n] == 1) {
                LOGGER.warn("Solution does not exist");
                throw new SolutionDoesNotExistException();
            }
        }

        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = augmented[i][n];
        }

        this.numberOfMoves = Arrays.stream(x).filter(el -> el == 1).count();

        return x;
    }
}
