package com.ag.concurrency.main.forkjoin;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;


public class FindMaximumParallelTest {


    @Test
    public void parallelComputation() {
        int expectedPerformanceInMillis = 900;
        long[] longs = generateRandomIntArray();
        long expectedMax = Arrays.stream(longs).max().orElse(0);

        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        FindMaximumParallel findTask = new FindMaximumParallel(longs, 0, longs.length);

        long start = System.currentTimeMillis();
        Long actualMax = forkJoinPool.invoke(findTask);
        long performanceInMillis = System.currentTimeMillis() - start;
        System.out.println("Time taken: " + performanceInMillis + "ms");

        System.out.println("Maximum found: " + actualMax);
        Assertions.assertThat(actualMax).isEqualTo(expectedMax);
        Assertions.assertThat(performanceInMillis < expectedPerformanceInMillis).isTrue().withFailMessage("Performance is slow than " + performanceInMillis + "ms");

    }

    private static long[] generateRandomIntArray() {
        return new Random().longs(300000000, 1, 200).toArray();
    }


}