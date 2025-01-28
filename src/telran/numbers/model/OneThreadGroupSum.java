package telran.numbers.model;

import telran.numbers.task.OneGroupSum;

public class OneThreadGroupSum extends GroupSum {
    // обычный подсчет с одним тредом для сравнения быстродействия
    public OneThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        int sum = 0;
        int nRows = numberGroups.length;
        for (int i = 0; i < nRows; i++) {
            OneGroupSum oneGroupSum = new OneGroupSum(numberGroups[i]);
            oneGroupSum.run();
            sum += oneGroupSum.getSum();
        }
        return sum;
    }
}
