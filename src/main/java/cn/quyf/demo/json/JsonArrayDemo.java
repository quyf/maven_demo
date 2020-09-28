package cn.quyf.demo.json;

import cn.quyf.demo.App;
import cn.quyf.demo.base.lambda.Apple;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author quyf
 * @date 2020/5/14 17:02
 * @desc TODO
 **/
public class JsonArrayDemo {

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(10.20);
        System.out.println(a.setScale(0));
        Map<String, Apple> map = new HashMap<>();
        Apple a1 = new Apple(1,"coloe",1,"1");
        Apple a2 = new Apple(2,"coloe",1,"2");
        map.put("1", a1);
        map.put("2", a2);
        System.out.println(JSON.toJSONString(map));

        HashMap<String, Apple> hashMap = JSON.parseObject(JSON.toJSONString(map), HashMap.class);

        System.out.println(hashMap);
        System.out.println(hashMap.get("1"));
//        List<String> list1 = new ArrayList<String>();
//        list1.add("1");
//        list1.add("2");
//        list1.add("3");
//        list1.add("5");
//        list1.add("6");
//
//        List<String> list2 = new ArrayList<String>();
//        list2.add("2");
//        list2.add("3");
//        list2.add("7");
//        list2.add("8");
//        list2.retainAll(Collections.EMPTY_LIST);
//        System.out.println(list2);
//
//        List<String> list = Lists.newArrayList("1","2","3");
//
//        System.out.println(JSONArray.toJSONString(list));
//
//        List<String> stringList = JSONArray.parseArray(JSONArray.toJSONString(list), String.class);
//        System.out.println(stringList);

    }
}
