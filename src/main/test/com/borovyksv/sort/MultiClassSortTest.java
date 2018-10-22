package com.borovyksv.sort;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public abstract class MultiClassSortTest {

    Sortable sorter;

    public MultiClassSortTest(Object parameterClassName, Sortable sorter) {
        this.sorter = sorter;
    }

    @Parameterized.Parameters(name = "{0}.class")
    public static Collection<Object[]> getParameters() {
        Reflections reflections = new Reflections("com");
        Set<Class<? extends Sortable>> sortableImplementations = reflections.getSubTypesOf(Sortable.class);
        Object[][] parameterInstances = sortableImplementations.stream()
                .map(MultiClassSortTest::getSortable)
                .toArray(Object[][]::new);
        return Arrays.asList(parameterInstances);
    }

    @SuppressWarnings("deprecated")
    private static Object[] getSortable(Class<? extends Sortable> c) {
        try {
            return new Object[]{c.getSimpleName(), c.newInstance()};
        } catch (InstantiationException | IllegalAccessException e) {
            throw  new RuntimeException("Can't instantiate the class: " + c, e);
        }
    }

    void assertNaturalOrder(int[] integers) {
        for (int i = 0; i < integers.length - 1; i++) {
            assertTrue(integers[i] + " should be lower than " + integers[i + 1],
                    integers[i] < integers[i + 1]);
        }
    }
}
