package com.borovyksv.consumer_producer;

import java.util.Queue;
import java.util.stream.IntStream;

public abstract class Producer extends Thread {
    protected Queue<Integer> buffer;
    protected static final int MAX_BUFFER_SIZE = 4;
    public final static int NUM_OF_ELEMENTS = 20;

    @Override
    public void run() {
        IntStream.range(0, NUM_OF_ELEMENTS)
                .peek(this::log)
                .forEach(this::produce);
    }
    private void log(int element) {
        System.out.println(this.getClass().getSimpleName().substring("producer".length())
                + " produced element: " + element);
    }

    protected abstract void produce(int element);

    public Queue<Integer> getBuffer() {
        return buffer;
    }
}
