package telran.numbers.model;

import telran.numbers.task.OneGroupSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ExecutorService;

public class ParallelStreamGroupSum extends GroupSum{
    // Homework, use parallel streams
    public ParallelStreamGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        int sum = 0;
        int nRows = numberGroups.length;
        Collection<OneGroupSum> oneGroupSumCollection = new ArrayList<>();
        for (int i = 0; i < nRows; i++) {
            oneGroupSumCollection.add(new OneGroupSum(numberGroups[i]));
        }

        oneGroupSumCollection.parallelStream().forEach(OneGroupSum::run);

        return oneGroupSumCollection.stream().mapToInt(OneGroupSum::getSum).sum();
    }
}
