package telran.numbers.model;

import telran.numbers.task.OneGroupSum;

public class ThreadGroupSum extends GroupSum {
    // Homework, use pure Threads
    public ThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        int sum = 0;
        int nRows = numberGroups.length;
        OneGroupSum[] oneGroupSums = new OneGroupSum[nRows];
        Thread[] threads = new Thread[nRows];
        for (int i = 0; i < nRows; i++) {
            oneGroupSums[i] = new OneGroupSum(numberGroups[i]);
            threads[i] = new Thread(oneGroupSums[i]);
            threads[i].start();
        }
        for (int i = 0; i < nRows; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum += oneGroupSums[i].getSum();
        }
        return sum;
    }
}
