package com.franklin.mars.guava;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.primitives.Doubles;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summarizingDouble;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

public class OrderingTest {

    enum CaloricLevel {LOW, NORMAL, FAT}

    private static <T> List<T> filter(List<T> list, Predicate<T> p) {
        Objects.requireNonNull(list);
        List<T> result = new ArrayList<>();
        list.forEach((i) -> {
            if (p.test(i)) {
                result.add(i);
            }
        });
        return result;
    }

    @Test
    public void testOrdering() {
        Double first = 0.10D;
        Double[] second = {0.2, 0.5, 0.3};
        List<Double> numbers = Lists.asList(first, second);
        Ordering<Double> orders = new Ordering<Double>() {
            @Override
            public int compare(Double left, Double right) {
                return Doubles.compare(left, right);
            }
        };
        List<Double> list = orders.reverse().sortedCopy(numbers);
        System.out.println(list.contains(0.1));
        list.add(1, 1000D);
    }

    @Test
    public void testImmutable() {

        ImmutableList<String> list = ImmutableList.<String>builder().add("alpha").add("beta", "gama").build();
        ImmutableSet<String> set = ImmutableSet.<String>builder().add("alpha").add("beta").add("gama").build();
        ImmutableMap<String, String> map = ImmutableMap.<String, String>builder().put("1", "alpha").put("2", "beta").put("3", "gama").build();
        ImmutableList<String> a = ImmutableList.of("a", "c", "ac");
        ImmutableList<String> b = ImmutableList.copyOf(a);
        System.out.println(a == b);
        assertThat(a.get(0), equalTo("a"));
        assertThat(list.get(1), equalTo("beta"));
        assertThat(list.get(0), startsWith("a"));
        assertThat(set.isEmpty(), equalTo(false));
        assertThat(map.get("1"), equalTo("alpha"));
        ImmutableBiMap<String, String> biMap = ImmutableBiMap.of("a1", "a1", "a22", "a2");
        ImmutableSet<String> values = biMap.values();
        assertThat(values.contains("a2"), equalTo(true));

    }

    @Test
    public void testSplitter() {
        String str = "a,,b,   c,,d";
        Iterable<String> result = Splitter.on(",")
                .omitEmptyStrings()
                .trimResults()
                .split(str);
        List<String> newList = filter(Lists.newArrayList(result), (s) -> !"a".equals(s));
        newList.forEach(System.out::println);

        final Map<String, String> join = Splitter.on("&")
                .withKeyValueSeparator("=")
                .split("id=123&name=green");
        for (Map.Entry<String, String> entry : join.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        List<String> nList = Lists.newArrayList("a", "b", "c", "f", "d");
        nList.sort(String::compareToIgnoreCase);
        nList.forEach(System.out::println);
        nList.add("i");
        nList.forEach(System.out::println);
    }

    @Test
    public void testStream() {
        List<Integer> list1 = Lists.newArrayList(1, 2, 3);
        List<Integer> list2 = Lists.newArrayList(1, 3, 4);
        List<Integer[]> pairs = list1.stream()
                .flatMap(i -> list2.stream().map(j -> new Integer[]{i, j}))
                .collect(Collectors.toList());

        Integer res = list1.stream().reduce(0, Integer::sum);
        System.out.println(res);

        IntStream st = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println(st.count());


        Stream<String> stream = Stream.of("java", "8", "is", "very", "interesting");
        List<String> list = stream.map(String::toUpperCase).collect(Collectors.toList());
        list.forEach(System.out::println);

        int[] n = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(n).sum();
        assertThat(sum, equalTo(41));


        Stream.iterate(0, item -> item + 2).limit(10).forEach(System.out::println);
        Stream.iterate(new int[]{1, 1}, t -> new int[]{
                t[1], t[0] + t[1]
        }).limit(10).map(l -> l[0]).forEach(System.out::println);

        Stream.generate(Math::random).limit(10).forEach(System.out::println);
        List<Integer> collect = Lists.newArrayList(1, 2, 10, 3, 4, 5, 6).stream().filter(num -> num % 2 == 0).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @Test
    public void testCombined() {

        List<Dish> menu = Lists.newArrayList(
                new Dish("pork", 1000),
                new Dish("beef", 1500),
                new Dish("vega", 550),
                new Dish("sugar", 770),
                new Dish("bean", 660));
        Map<CaloricLevel, List<Dish>> dishLevel = menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() < 600) {
                        return CaloricLevel.LOW;
                    } else if (dish.getCalories() >= 600 && dish.getCalories() < 1000) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                })
        );
    }

    @Test
    public void testFuture() {
        BigDecimal a = new BigDecimal("0.1");
        BigDecimal b = new BigDecimal("0.2");
        System.out.println(a.add(b));
    }

    @Test
    public void testMyGuava() {
        List<String> strList = Lists.newArrayList("a", null, "hdkas", " jkaksl ", "hsjkas    ", " hsjkas");
        String join = Joiner.on("").skipNulls().join(strList);
        System.out.println(join);
    }

    @Test
    public void testCH() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String chenhao = objectMapper.writeValueAsString("chenhao");
            System.out.println(chenhao);
            if ("start".equals(chenhao)) {
                System.out.println("hello");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test20180401() {
        String[] strs = "guava".split("");
        Lists.newArrayList(strs).forEach(System.out::println);
        assertThat(Lists.newArrayList(strs).size(), equalTo(5));
        String alpha = "my future is very good.";
        alpha = alpha.toUpperCase();
        alpha = alpha.toUpperCase().charAt(0) + alpha.toLowerCase().substring(1);

        System.out.println(alpha);
        
    }

}
