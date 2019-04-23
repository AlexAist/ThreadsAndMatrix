package com.epam.threads.timertask;

import com.epam.threads.creator.MatrixCreator;
import com.epam.threads.reporter.Reporter;
import com.epam.threads.storage.DataStorage;
import com.epam.threads.threadtask.ThreadTask;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerTaskArray extends TimerTask {

    @Override
    public void run() {
        completeTask();
    }

    private void completeTask(){
        int[][] matrix;
        DataStorage storage = DataStorage.INSTANCE;
        Reporter reporter = new Reporter();
        MatrixCreator matrixCreator = new MatrixCreator();
            matrix = matrixCreator.randomMatrix(10);
            ThreadTask threadTask = new ThreadTask(matrix, 10);
            threadTask.calcLeftRight();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            matrix = threadTask.getMatrix();
            reporter.printArray(matrix);
            reporter.printMes(storage.getMap().toString());
    }
}
