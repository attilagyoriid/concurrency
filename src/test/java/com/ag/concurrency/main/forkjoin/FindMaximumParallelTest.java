package com.ag.concurrency.main.forkjoin;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class FindMaximumParallelTest {


    @Test
    public void parallelComputation() {
        long[] longs = generateRandomIntArray();
        long expectedMax = Arrays.stream(longs).max().orElse(0);

        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        FindMaximumParallel findTask = new FindMaximumParallel(longs, 0, longs.length);
        Long actualMax = forkJoinPool.invoke(findTask);

        System.out.println("Maximum found: " + actualMax);
        Assertions.assertThat(actualMax).isEqualTo(expectedMax);

    }

    private static long[] generateRandomIntArray() {
        return new Random().longs(300000000, 1, 200).toArray();
    }


}