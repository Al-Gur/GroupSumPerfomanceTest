package telran.numbers.test;

import telran.numbers.model.GroupSum;

public class GroupSumPerfomanceTest {
    private String name;
    private GroupSum groupSum;

    public GroupSumPerfomanceTest(String name, GroupSum groupSum) {
        this.name = name;
        this.groupSum = groupSum;
    }

    public void runTest() {
        long start = System.currentTimeMillis();
        int sum = groupSum.computeSum();
        long finish = System.currentTimeMillis();
        System.out.println("test name: " + name + ", duration: " + (finish - start) + ", sum = " + sum);
    }
}
