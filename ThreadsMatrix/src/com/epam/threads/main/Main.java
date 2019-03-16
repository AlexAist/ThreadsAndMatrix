package com.epam.threads.main;

import com.epam.threads.timertask.TimerTaskArray;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        TimerTask timerTask = new TimerTaskArray();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 5000);
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
    }
}
