package com.ag.concurrency.main.forkjoin;

public class ThresholdSingleton {

    private static ThresholdSingleton instance;
    private static int threshold;


    public int getThreshold() {
        return ThresholdSingleton.threshold;
    }

    public static ThresholdSingleton getInstance(int length){

        if(instance == null){
            synchronized (ThresholdSingleton.class) {
                if(instance == null){
                    instance = new ThresholdSingleton();
                    ThresholdSingleton.threshold = length / Runtime.getRuntime().availableProcessors();
                }
            }
        }
        return instance;
    }

}
