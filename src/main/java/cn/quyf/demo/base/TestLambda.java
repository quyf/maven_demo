package cn.quyf.demo.base;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author quyf
 * @date 2020/3/20 10:03
 * @desc TODO
 **/
public class TestLambda {


    public static void main(String[] args) {
        BigDecimal zero = BigDecimal.ZERO;
        BigDecimal a = new BigDecimal(45);
        System.out.println(zero.compareTo(a));
        System.out.println(BigDecimal.ZERO.equals(zero));
        List<Item> itList = new ArrayList<>();
        BigDecimal bigDecimal = itList.stream().map(Item::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
        System.out.println(bigDecimal);

        List<Scope> scopeList = new ArrayList<>();
        scopeList.add(new Scope("BRAND", "oppo"));
        scopeList.add(new Scope("BRAND", "realme"));
        scopeList.add(new Scope("BRAND", "oneplus"));
        scopeList.add(new Scope("BUSINESS", "1"));
        scopeList.add(new Scope("BUSINESS", "2"));
        scopeList.add(new Scope("BUSINESS", "3"));

        scopeList.add(new Scope("USER_TAG", "3"));
        scopeList.add(new Scope("USER_TAG", "3"));
        scopeList.add(new Scope("USER_TAG", "3"));

        Map<String, List<Scope>> collect1 = scopeList.stream().collect(
                Collectors.groupingBy(Scope::getTag,  Collectors.toList())
        );

        System.out.println(collect1);

        //System.out.println(scopeList.subList(0,3).size());
        Map<String, List<String>> collect = scopeList.stream().collect(
                Collectors.groupingBy(Scope::getTag, Collectors.mapping(Scope::getTagValue, Collectors.toList()))
        );

        System.out.println(collect);

        //3 apple, 2 banana, others 1
        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orang", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );

        //每个名字出现的次数
        Map<String, Long> counting = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.counting()));

        System.out.println(counting);
        //每个名字 的数量总和
        Map<String, Integer> sum = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));

        System.out.println(sum);

        Map<BigDecimal, List<Item>> groupByPriceMap =
                items.stream().collect(Collectors.groupingBy(Item::getPrice));

        System.out.println(groupByPriceMap);

        // group by price, uses 'mapping' to convert List<Item> to Set<String>
        Map<BigDecimal, Set<String>> result =
                items.stream().collect(
                        Collectors.groupingBy(Item::getPrice,
                                Collectors.mapping(Item::getName, Collectors.toSet())
                        )
                );

        System.out.println(result);
    }


}

class Item {
    private String name;
    private int qty;
    private BigDecimal price;

    public Item(String name, int qty, BigDecimal price) {
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}


class Scope{
    private String tag;
    private String tagValue;

    public Scope(String tag, String tagValue){
        this.tag = tag;
        this.tagValue = tagValue;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    @Override
    public String toString() {
        return "Scope{" +
                "tag='" + tag + '\'' +
                ", tagValue='" + tagValue + '\'' +
                '}';
    }
}
