package com.ag.concurrency.main.forkjoin;

public class FindMaximumSequential {

    public long findMax(long[] nums, int endIndex) {

        long max = nums[0];

        for (int i = 0; i < endIndex; ++i)
            if (nums[i] > max)
                max = nums[i];

        return max;
    }

}
