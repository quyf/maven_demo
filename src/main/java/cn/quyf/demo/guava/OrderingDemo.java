package cn.quyf.demo.guava;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class OrderingDemo {

	public static void main(String[] args) {
		List<String> list = Lists.newArrayList();
		list.add("peida");
        list.add("jerry");
        list.add("harry");
        list.add("eva");
        list.add("jhon");
        list.add("neron");
        System.out.println("list:"+ list);
        
        List<String> orderList = Ordering.natural().nullsLast().sortedCopy( list);
        System.out.println(orderList);
	}
}
