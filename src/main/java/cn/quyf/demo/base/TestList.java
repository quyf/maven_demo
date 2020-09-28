package cn.quyf.demo.base;

import com.google.common.collect.Lists;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author quyf
 * @date 2019/12/4 15:10
 */
public class TestList {

    public static void main(String[] args) {
        String ff="{\"downloadUrl\":\"\",\"linkDetail\":[{\"linkType\":\"WEBVIEW\",\"osVersion\":\"\",\"packageName\":\"\",\"appVersion\":\"\",\"linkUrl\":\"https://uc-html.ucnewtest.wanyol.com/vip/vip_equityInfo.html?isGradualToolbar=true&isTranslucentBar=true&LoadInCurrentPage=false&giftBatchId=%s\"}]}";
        System.out.println(String.format(ff, "12333"));
        System.out.println(Collections.emptyList().size());
        List<Long> list = Lists.newArrayList(1417L,1412L,1491L,1413L,1403L);

        int index = (int) (Math.random()* list.size());
        System.out.println(list.get(index));
//        List<Long> collect = list.stream().sorted(new Comparator<Long>() {
//            @Override
//            public int compare(Long o1, Long o2) {
//                return (int)(o1 - o2);
//            }
//        }).collect(Collectors.toList());
//        System.out.println(list);
//        System.out.println(collect);
    }
}
