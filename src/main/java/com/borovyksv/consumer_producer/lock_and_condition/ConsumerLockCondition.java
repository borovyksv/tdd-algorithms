package com.borovyksv.consumer_producer.lock_and_condition;

import com.borovyksv.consumer_producer.Consumer;

import java.util.concurrent.TimeUnit;

import static com.borovyksv.consumer_producer.lock_and_condition.ProducerLockCondition.*;

public class ConsumerLockCondition extends Consumer {

    @Override
    protected Integer consume() {
        waitIfBufferIsEmpty();
        return takeElementFromBuffer();
    }

    private Integer takeElementFromBuffer() {
        try {
            LOCK.lockInterruptibly();
            Integer element = buffer.poll();
            IS_FULL.signal();
            return element;
        } catch (InterruptedException e) {
            throw new RuntimeException("Synchronization problem: ", e);
        } finally {
            LOCK.unlock();
        }
    }

    private void waitIfBufferIsEmpty() {
        while (buffer.isEmpty()) {
            try {
                System.out.println("ConsumerLockCondition: Buffer is empty, waiting...");
                LOCK.lockInterruptibly();
                IS_EMPTY.await(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException("Synchronization problem: ", e);
            } finally {
                LOCK.unlock();
            }
        }
    }


}
