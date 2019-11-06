package com.example.test.async;


import java.util.concurrent.TimeUnit;

public class TestAsync {
    private boolean running = true;

    private void stop() {
        running = false;
    }

    public void testVolatile() {
        new Thread(() -> {
            while (running) {

            }
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stop();

    }
}
