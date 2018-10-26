package com.borovyksv.consumer_producer.wait_notify;

import com.borovyksv.consumer_producer.Producer;

import java.util.ArrayDeque;

public class ProducerWaitNotify extends Producer {
    public ProducerWaitNotify() {
        buffer = new ArrayDeque<>(MAX_BUFFER_SIZE);
    }

    @Override
    protected void produce(int element) {
        waitIfBufferIsFull();
        addElementToBuffer(element);
    }

    private void addElementToBuffer(int element) {
        synchronized (buffer) {
            buffer.add(element);
            buffer.notifyAll();
        }
    }

    private void waitIfBufferIsFull() {
        while (buffer.size() == MAX_BUFFER_SIZE) {
            synchronized (buffer) {
                try {
                    System.out.println("ProducerWaitNotify: Buffer is full, waiting...");
                    buffer.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException("Synchronization problem: ", e);
                }
            }
        }
    }
}
