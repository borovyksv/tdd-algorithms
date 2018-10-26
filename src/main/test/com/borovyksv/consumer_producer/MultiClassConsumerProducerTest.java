package com.borovyksv.consumer_producer;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@RunWith(Parameterized.class)
public abstract class MultiClassConsumerProducerTest {
    public Producer producer;
    public Consumer consumer;
    private static Set<Class<? extends Producer>> producerImplementations;
    private static Set<Class<? extends Consumer>> consumerImplementations;

    public MultiClassConsumerProducerTest(Object parameterClassName, Producer producer, Consumer consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    @Parameterized.Parameters(name = "{0} implementation")
    public static Collection<Object[]> getParameters() {
        Reflections reflections = new Reflections("com");
        producerImplementations = reflections.getSubTypesOf(Producer.class);
        consumerImplementations = reflections.getSubTypesOf(Consumer.class);
        Object[][] parameterInstances = producerImplementations.stream()
                .map(MultiClassConsumerProducerTest::getParameters)
                .toArray(Object[][]::new);
        System.out.println(Arrays.deepToString(parameterInstances));
        return Arrays.asList(parameterInstances);
    }

    @SuppressWarnings("deprecated")
    private static Object[] getParameters(Class<? extends Producer> producer) {
        try {
            String producerSimpleName = producer.getSimpleName();
            String producerSuffix = producerSimpleName.substring("producer".length());
            Class<? extends Consumer> consumer = getSimilarConsumerByClassName(producerSuffix);
            return new Object[]{producerSuffix, producer.newInstance(), consumer.newInstance()};
        } catch (InstantiationException | IllegalAccessException e) {
            throw  new RuntimeException("Can't instantiate a class for parameters ", e);
        }
    }

    private static Class<? extends Consumer> getSimilarConsumerByClassName(String producerSuffix) {
        return consumerImplementations.stream()
                .filter(c ->
                        c.getSimpleName().substring("consumer".length())
                        .equals(producerSuffix)).findAny().orElseThrow();
    }

    protected void waitForFinish(Producer producer, Consumer consumer) {
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
