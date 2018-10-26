package com.borovyksv.consumer_producer.lock_and_condition;

import com.borovyksv.consumer_producer.Producer;

import java.util.ArrayDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerLockCondition extends Producer {

    static final ReentrantLock LOCK = new ReentrantLock();
    static final Condition IS_FULL = LOCK.newCondition();
    static final Condition IS_EMPTY = LOCK.newCondition();

    public ProducerLockCondition() {
        buffer = new ArrayDeque<>(MAX_BUFFER_SIZE);
    }

    @Override
    protected void produce(int element) {
        waitIfBufferIsFull();
        addElementToBuffer(element);
    }

    private void addElementToBuffer(int element) {
        try {
            LOCK.lock();
            buffer.add(element);
            IS_EMPTY.signal();
        } finally {
            LOCK.unlock();
        }
    }

    private void waitIfBufferIsFull() {
        while (buffer.size() == MAX_BUFFER_SIZE) {
            try {
                System.out.println("ProducerLockCondition: Buffer is full, waiting...");
                LOCK.lockInterruptibly();
                IS_FULL.await();
            } catch (InterruptedException e) {
                throw new RuntimeException("Synchronization problem: ", e);
            } finally {
                LOCK.unlock();
            }
        }
    }
}
