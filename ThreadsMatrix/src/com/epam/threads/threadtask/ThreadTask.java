package com.epam.threads.threadtask;

import com.epam.threads.action.MatrixLogic;
import com.epam.threads.storage.DataStorage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadTask {

    private ExecutorService executor;
    private ReadWriteLock lock;
    private MatrixLogic matrixLogic;
    private int[][] array;
    private DataStorage storage = DataStorage.INSTANCE;
    private final int THREAD_NUM = 10;

    public ThreadTask(int[][] array) {
        executor = Executors.newFixedThreadPool(THREAD_NUM);
        lock = new ReentrantReadWriteLock();
        matrixLogic = new MatrixLogic();
        this.array = array;
    }

    public int[][] getArray() {
        return array;
    }


    public void calcLeftRight() {
        for (int i = 0; i < THREAD_NUM; i++) {
            int counter = i;
            executor.submit(() -> {
                try {
                    String id = String.valueOf(IdGenerator.getId());
                    Thread.currentThread().setName(id);
                    int currentId = Integer.parseInt(Thread.currentThread().getName());
                    lock.writeLock().lock();
                    matrixLogic.addThreadNumber(array, counter, currentId);
                    TimeUnit.MILLISECONDS.sleep(7);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.writeLock().unlock();
                }
                try{
                    int currentId = Integer.parseInt(Thread.currentThread().getName());
                    lock.readLock().lock();
                    int result = matrixLogic.calcSum(array[counter], counter);
                    storage.add(currentId, result);
                }finally {
                    lock.readLock().unlock();
                }
            });
        }
        executor.shutdown();
    }
}
