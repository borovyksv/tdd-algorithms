package com.borovyksv.consumer_producer.blocking_queue;

import com.borovyksv.consumer_producer.Producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerBlockingQueue extends Producer {
    public ProducerBlockingQueue() {
        buffer = new ArrayBlockingQueue<>(MAX_BUFFER_SIZE);
    }

    @Override
    protected void produce(int element) {
        putElementToBuffer(element);
    }

    private void putElementToBuffer(int element) {
        try {
            ((BlockingQueue<Integer>) buffer).put(element);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
