package cn.quyf.demo.json;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author quyf
 * @date 2020/5/14 17:02
 * @desc TODO
 **/
public class JsonArrayDemo {

    public static void main(String[] args) {

        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("5");
        list1.add("6");

        List<String> list2 = new ArrayList<String>();
        list2.add("2");
        list2.add("3");
        list2.add("7");
        list2.add("8");
        list2.retainAll(Collections.EMPTY_LIST);
        System.out.println(list2);

        List<String> list = Lists.newArrayList("1","2","3");

        System.out.println(JSONArray.toJSONString(list));

        List<String> stringList = JSONArray.parseArray(JSONArray.toJSONString(list), String.class);
        System.out.println(stringList);

    }
}
