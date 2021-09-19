package com.ag.concurrency.main.forkjoin;

import java.util.concurrent.RecursiveTask;

public class FindMaximumParallel extends RecursiveTask<Long> {
    private int threshold;
    private long[] nums;
    private int startIndex;
    private int endIndex;
    private FindMaximumSequential findMaximumSequential;


    public FindMaximumParallel(long[] nums, int startIndex, int endIndex) {
        this.endIndex = endIndex;
        this.startIndex = startIndex;
        this.nums = nums;
        this.findMaximumSequential = new FindMaximumSequential();
        this.threshold = ThresholdSingleton.getInstance(nums.length).getThreshold();
    }

    @Override
    protected Long compute() {

        if (endIndex - startIndex < this.threshold) {
            return this.findMaximumSequential.findMax(this.nums, this.endIndex);
        } else {

            int middleIndex = (startIndex + endIndex) / 2;

            FindMaximumParallel leftSubtask = new FindMaximumParallel(nums, startIndex, middleIndex);
            FindMaximumParallel rightSubtask = new FindMaximumParallel(nums, middleIndex, endIndex);

            invokeAll(leftSubtask, rightSubtask);

            return Math.max(leftSubtask.join(), rightSubtask.join());
        }
    }


}
