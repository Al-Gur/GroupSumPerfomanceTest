package telran.numbers.model;

import telran.numbers.task.OneGroupSum;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorGroupSum extends GroupSum {
    // TODO Homework use ExecutorService
    public ExecutorGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        int sum = 0;
        int nRows = numberGroups.length;
        OneGroupSum[] oneGroupSums = new OneGroupSum[nRows];
        for (int i = 0; i < nRows; i++) {
            oneGroupSums[i] = new OneGroupSum(numberGroups[i]);
        }

        int poolSize = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
//        ExecutorService executorService = Executors.newWorkStealingPool();
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < nRows; i++) {
            executorService.execute(oneGroupSums[i]);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < nRows; i++) {
            sum += oneGroupSums[i].getSum();
        }
        return sum;
    }
}
