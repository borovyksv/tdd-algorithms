package com.borovyksv.consumer_producer.wait_notify;

import com.borovyksv.consumer_producer.Consumer;

public class ConsumerWaitNotify extends Consumer {

    @Override
    protected void consume() {
        waitIfBufferIsEmpty();
        synchronized (buffer) {
            consumeFromBuffer();
            buffer.notifyAll();
        }
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

    private void consumeFromBuffer() {
        Integer element = buffer.poll();
        System.out.println("WaitNotify consumed element: " + element);
        results.add(element);
    }

}
