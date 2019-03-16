package com.epam.threads.reporter;

public class Reporter {

    public void printArray(int[][] ar){
        for (int[] ints : ar) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }

    public void printMes(String mes){
        System.out.println(mes);
    }
}
