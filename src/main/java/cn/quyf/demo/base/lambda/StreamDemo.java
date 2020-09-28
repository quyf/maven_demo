package cn.quyf.demo.base.lambda;

import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo {

    private static List<Apple> appleList = new ArrayList<>();

    static{
        appleList.add(new Apple(4,"yellow", 400, "湖北"));
        appleList.add(new Apple(2,"green", 200, "湖南"));
        appleList.add(new Apple(1,"red", 100, "江西"));
        appleList.add(new Apple(3,"red", 300, "广东"));

        appleList.add(new Apple(5,"green", 500, "江西"));
    }

    public static void main(String[] args) {
        final int[] i = new int[1];
        Comparator<Apple> comparator = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                int w1 = o1.getWeight();
                int w2 = o2.getWeight();
                int st = w1 - w2;
                System.out.println("==="+st/100);
                return st;
            }
        };
        //appleList.stream().sorted(comparator).collect(Collectors.toList());
        Collections.sort(appleList, comparator);
    }

    public static void main2(String[] args) {
        final int[] i = new int[1];

        appleList.stream().sorted((e1, e2)->{
            int index = i[0];
            index ++;
            i[0]=index;

            System.out.println(i[0]);
            int w1 = e1.getWeight();
            int w2 = e2.getWeight();
            return w1 - w2;

        }).collect(Collectors.toList());

    }

    public static void main1(String[] args) {
        Map<String, Double> collect = appleList.stream()
                .collect(Collectors.groupingBy(Apple::getColor, Collectors.averagingDouble(Apple::getWeight)));

        collect.forEach((k,v)-> System.out.printf("%s:%s\n",k ,v));
    }

}
