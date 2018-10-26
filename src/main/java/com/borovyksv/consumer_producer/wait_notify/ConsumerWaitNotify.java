package com.borovyksv.consumer_producer.wait_notify;

import com.borovyksv.consumer_producer.Consumer;

public class ConsumerWaitNotify extends Consumer {

    @Override
    protected Integer consume() {
        waitIfBufferIsEmpty();
        return takeElementFromBuffer();
    }

    private Integer takeElementFromBuffer() {
        Integer element;
        synchronized (buffer) {
            element = buffer.poll();
            buffer.notifyAll();
        }
        return element;
    }

    private void waitIfBufferIsEmpty() {
        while (buffer.isEmpty()) {
            synchronized (buffer) {
                try {
                    System.out.println("ConsumerWaitNotify: Buffer is empty, waiting...");
                    buffer.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException("Synchronization problem: ", e);
                }
            }
        }
    }

}
