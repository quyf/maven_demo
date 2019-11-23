package cn.quyf.demo.base.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamDemo {

    private static List<Apple> appleList = new ArrayList<>();

    static{
        appleList.add(new Apple(1,"red", 200, "江西"));
        appleList.add(new Apple(2,"green", 400, "湖南"));
        appleList.add(new Apple(3,"red", 300, "广东"));
        appleList.add(new Apple(4,"yellow", 100, "湖北"));
        appleList.add(new Apple(5,"green", 500, "江西"));
    }

    public static void main(String[] args) {
        Map<String, Double> collect = appleList.stream()
                .collect(Collectors.groupingBy(Apple::getColor, Collectors.averagingDouble(Apple::getWeight)));

        collect.forEach((k,v)-> System.out.printf("%s:%s\n",k ,v));
    }

}
