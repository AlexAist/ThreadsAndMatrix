package com.epam.threads.threadtask;

import com.epam.threads.action.MatrixLogic;
import com.epam.threads.storage.DataStorage;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadTask {

    private ReadWriteLock lock;
    private MatrixLogic matrixLogic;
    private int[][] matrix;
    private DataStorage storage = DataStorage.INSTANCE;
    private int THREAD_NUM;

    public ThreadTask(int[][] matrix, int threadNum) {
        lock = new ReentrantReadWriteLock();
        matrixLogic = new MatrixLogic();
        this.matrix = matrix;
        this.THREAD_NUM = threadNum;
    }

    public int[][] getMatrix() {
        return matrix;
    }


    public void calcLeftRight() {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUM);
        Random random = new Random();
        for (int i = 0; i < THREAD_NUM; i++) {
            int counter = i;
            executor.submit(() -> {
                try {
                    String id = String.valueOf(IdGenerator.getId());
                    Thread.currentThread().setName(id);
                    int currentId = Integer.parseInt(Thread.currentThread().getName());
                    lock.writeLock().lock();
                    matrixLogic.addThreadNumberToMatrix(matrix, counter, currentId);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(9));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.writeLock().unlock();
                }
                try{
                    int currentId = Integer.parseInt(Thread.currentThread().getName());
                    lock.readLock().lock();
                    int result = matrixLogic.strSum(matrix[counter], counter);
                    storage.add(currentId, result);
                }finally {
                    lock.readLock().unlock();
                }
            });
        }
        executor.shutdown();
    }
}
