package com.epam.threads.action;

import com.epam.threads.creator.MatrixCreator;

public class MatrixLogic {

    public void addThreadNumber(int[][] ar, int number, int value){
        MatrixCreator creator = new MatrixCreator();
        ar[number][number] = value;
        creator.setMatrix(ar);
    }

    public int calcSum(int[] array, int position){
        int sum = 0;
        int avg = 1;
        int result;
        int counter = 0;
        for (int i = 0; i < position; i++) {
            sum += array[i];
        }
        for (int i = position; i < array.length; i++) {
            avg += array[i];
            counter++;
        }
        avg /= counter;
        result = avg + sum;
        return result;
    }

}
