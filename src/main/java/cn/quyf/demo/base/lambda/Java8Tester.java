package cn.quyf.demo.base.lambda;

import org.apache.kafka.common.protocol.types.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by quyf on 2018/7/15.
 * lambda 表达式
 * (parameters) -> expression
 * 或
 * (parameters) ->{ statements; }
 *
 */
public class Java8Tester {

    public static void main(String[] args) {
        List<String> names1 = new ArrayList<String>();
        names1.add("Google ");
        names1.add("Runoob ");
        names1.add("Taobao ");
        names1.add("Baidu ");
        names1.add("Sina ");

        List<String> names2 = new ArrayList<String>();
        names2.add("Google ");
        names2.add("Runoob ");
        names2.add("Taobao ");
        names2.add("Baidu ");
        names2.add("Sina ");

        Java8Tester tester = new Java8Tester();
        System.out.println("使用 Java 7 语法: ");

        tester.testSortJdk7(names1);
        System.out.println(names1);
        System.out.println("使用 Java 8 语法: ");

        tester.testSortdk8(names2);
        System.out.println(names2);

       new Thread(()->{
           System.out.println("helloworld");
       }).start();

        names2.forEach(a-> System.out.println(a));

        names2.forEach(a->{
            System.out.println(a+"--hrllo world");
        });

        tester.map(names1);
    }

    private void testSortJdk7(List<String> list){
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    private void testSortdk8(List<String> list){
        Collections.sort(list, (s1, s2) -> s1.compareTo(s2));
    }

    private void map(List<String> list){
        list.stream().map(x->x+10).forEach(x-> System.out.println(x));
    }

}
