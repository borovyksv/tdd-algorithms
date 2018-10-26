package com.borovyksv.consumer_producer;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.stream.IntStream;

import static com.borovyksv.consumer_producer.Producer.NUM_OF_ELEMENTS;
import static org.hamcrest.CoreMatchers.is;

public class ConsumerProducerTest extends MultiClassConsumerProducerTest{

    public ConsumerProducerTest(Object parameterClassName, Producer producer, Consumer consumer) { super(parameterClassName, producer, consumer); }

    @Test
    public void testProblem() {
        consumer.setBuffer(producer.getBuffer());
        producer.start();
        consumer.start();
        waitForFinish(producer, consumer);

        ArrayDeque<Integer> results = consumer.getResults();
        Assert.assertThat(0, is(results.getFirst()));
        Assert.assertThat(NUM_OF_ELEMENTS - 1, is(results.getLast()));
        Assert.assertThat(20, is(results.size()));
        IntStream.range(0, NUM_OF_ELEMENTS)
                .forEach(i -> Assert.assertThat(i, is(results.poll())));
    }
}