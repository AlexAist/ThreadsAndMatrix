package com.epam.threads.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public enum DataStorage {
    INSTANCE;

    private Map<Integer, Integer> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Integer> map) {
        this.map = map;
    }

    public void add(int key, int value){
        try {
            lock.writeLock().lock();
            map.put(key, value);
        }finally {
            lock.writeLock().unlock();
        }
    }
}
