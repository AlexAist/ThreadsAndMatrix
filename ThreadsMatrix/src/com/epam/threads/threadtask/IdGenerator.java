package com.epam.threads.threadtask;

public final class IdGenerator {

    private static long id = 0;

    private IdGenerator() {
    }

    public static long getId() {
        return ++id;
    }
}