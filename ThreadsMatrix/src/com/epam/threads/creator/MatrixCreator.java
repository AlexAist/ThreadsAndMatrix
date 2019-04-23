package com.epam.threads.creator;

import java.util.Random;

public class MatrixCreator {

    private int[][] matrix;

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int[][] randomMatrix(int size){
        final int DIAGONAL_NUM = 0;
        final int RANDOM_MAX = 91;
        int[][] matrix = new int[size][size];
        Random random = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(i != j){
                    matrix[i][j] = random.nextInt(RANDOM_MAX);
                }else {
                    matrix[i][j] = DIAGONAL_NUM;
                }
            }
        }
        this.matrix = matrix;
        return matrix;
    }
}
