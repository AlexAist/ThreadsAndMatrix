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
        int[][] array;
        DataStorage storage = DataStorage.INSTANCE;
        Reporter reporter = new Reporter();
        MatrixCreator matrixCreator = new MatrixCreator();
            array = matrixCreator.randomArray(10);
            ThreadTask threadTask = new ThreadTask(array);
            threadTask.calcLeftRight();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            array = threadTask.getArray();
            reporter.printArray(array);
            reporter.printMes(storage.getMap().toString());
    }
}
