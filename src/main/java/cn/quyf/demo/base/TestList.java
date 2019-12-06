package cn.quyf.demo.base;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author quyf
 * @date 2019/12/4 15:10
 */
public class TestList {

    public static void main(String[] args) {
        List<Long> list = Lists.newArrayList(1417L,1412L,1491L,1413L,1403L);
        list.add(0, 1421L);
        System.out.println(list);
        list.remove(list.size() - 1);
        System.out.println(list);
    }
}
