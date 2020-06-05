package cn.quyf.demo.base;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author quyf
 * @date 2020/3/25 16:37
 * @desc TODO
 **/
public class DateDemo {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        String a = "";
        List<Integer> collect = Arrays.stream(a.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(collect);

        String pm = null;
        String sp = "wxpay";
        System.out.println(sp.equalsIgnoreCase(pm));
        if(StringUtils.isEmpty(pm)){
            System.out.println(sp);
        }

        Date now = new Date();
        Date beginDate = DateUtils.addDays(now, 3-1);
        Date endDate = DateUtils.addDays(now, 3);
        System.out.println(beginDate);

        System.out.println(endDate);
    }

    public static void testYyFormat(){
        Date now = new Date();

        int all = 1;
        long begin = System.currentTimeMillis();
        for(int i=0;i<all;i++){
            String yy = DateFormatUtils.format(now, "yyMMddHHmm")+ThreadLocalRandom.current().nextInt(10000);
            System.out.println(yy);
        }
        long end = System.currentTimeMillis();
        System.out.println((end-begin) +"ms");

        LocalDateTime localDateTime = LocalDateTime.now();
        begin = System.currentTimeMillis();
        for(int i=0;i<all;i++){
            String yyMMddHHmm = localDateTime.format(DateTimeFormatter.ofPattern("yyMMddHHmm"))+ThreadLocalRandom.current().nextInt(10000);
            System.out.println(yyMMddHHmm);
        }
        end = System.currentTimeMillis();
        System.out.println((end-begin) +"ms");
    }
}
