package com.borovyksv.consumer_producer;

import java.util.ArrayDeque;
import java.util.Queue;

public abstract class Consumer extends Thread {
    private static final int SECONDS_TO_SLEEP_AFTER_CONSUME = 1;
    protected Queue<Integer> buffer;
    protected ArrayDeque<Integer> results = new ArrayDeque<>();

    public Consumer() { }

    @Override
    public void run() {
        sleep();
        while (results.size() != Producer.NUM_OF_ELEMENTS) {
            int element = consume();
            log(element);
            results.add(element);
        }
    }

    protected abstract Integer consume();

    private void log(Integer element) {
        System.out.println(this.getClass().getSimpleName().substring("consumer".length())
                + " consumed element: " + element);
    }

    public void setBuffer(Queue<Integer> buffer) {
        this.buffer = buffer;
    }

    public ArrayDeque<Integer> getResults() {
        return results;
    }

    protected void sleep() {
        try {
            Thread.sleep(SECONDS_TO_SLEEP_AFTER_CONSUME * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
