package com.gfnqueuechecker.backend;

public class ProcessThreads {

    public static void lastSearchedThread(){
        System.out.println("test");

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        lastSearchedThread();
                    }
                },
                5000
        );
    }

    public static void checkQueueThread(){
        System.out.println("x");
    }
}
