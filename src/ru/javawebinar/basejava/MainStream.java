package ru.javawebinar.basejava;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MainStream {

    public int intValue(int[] values) {
        Queue<Integer> powsOfTen = new ArrayDeque<>();
        final int TEN = 10;
        int degree = 0;
        final int MAX_DEGREE = 9; // lg 1_000_000_000 = 9
        for (int i = 0; i < MAX_DEGREE; i++) {
            powsOfTen.add((int) Math.pow(TEN, degree++));
        }
        return Arrays.stream(values).distinct().mapToObj(e -> e).sorted((o1, o2) -> -o1.compareTo(o2)).map(x -> x * powsOfTen.poll()).reduce(0, Integer::sum);
    }

    public List<Integer> OddOrEven(List<Integer> integers) {
        AtomicInteger sum = new AtomicInteger();
        Map<Boolean, List<Integer>> collect = integers.stream().peek(sum::addAndGet).collect(Collectors.partitioningBy(p -> p % 2 == 0));
        return sum.get() % 2 == 0 ? collect.get(false) : collect.get(true);
    }

    public List<Integer> fillListIntegerToTwentyNumbers() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            list.add(random.nextInt(100));
        }
        System.out.println("Generate list : " + list);
        System.out.println("Sum = " + list.stream().mapToInt(x -> x).sum());
        return list;
    }

    public static void main(String[] args) {
        MainStream ms = new MainStream();
        System.out.println(ms.intValue(new int[]{9, 8}));
        System.out.println("====================================");
        List<Integer> integerList = ms.fillListIntegerToTwentyNumbers();
        System.out.println("====================================");
        System.out.println("Call OddorEven method -> ");
        System.out.println(ms.OddOrEven(integerList));

    }
}
