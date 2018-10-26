package com.borovyksv.consumer_producer.blocking_queue;

import com.borovyksv.consumer_producer.Consumer;

import java.util.concurrent.BlockingQueue;

public class ConsumerBlockingQueue extends Consumer {

    @Override
    protected Integer consume() {
        return takeElementFromBuffer();
    }

    private Integer takeElementFromBuffer() {
        try {
            return ((BlockingQueue<Integer>) buffer).take();
        } catch (InterruptedException e) {
            throw new RuntimeException("Error while taking element from buffer");
        }
    }
}
