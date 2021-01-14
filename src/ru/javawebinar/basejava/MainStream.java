package ru.javawebinar.basejava;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MainStream {

    public int intValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (x, y) -> x * 10 + y);
    }

    public List<Integer> OddOrEven(List<Integer> integers) {
        AtomicInteger sum = new AtomicInteger();
        Map<Boolean, List<Integer>> collect = integers.stream().peek(sum::addAndGet).collect(Collectors.partitioningBy(p -> p % 2 == 0));
        return collect.get(sum.get() % 2 != 0);
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
        System.out.println(ms.intValue(new int[]{3, 2, 1, 2, 3, 1, 3}));
        System.out.println("====================================");
        List<Integer> integerList = ms.fillListIntegerToTwentyNumbers();
        System.out.println("====================================");
        System.out.println("Call OddorEven method -> ");
        System.out.println(ms.OddOrEven(integerList));

    }
}
