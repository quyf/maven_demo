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

        Date now = new Date();
        now.setMonth(7);
        now.setDate(20);

        Date beginDate = DateUtils.addDays(now, 62);

        System.out.println(DateFormatUtils.format(now, "yyyy-MM-dd"));

        System.out.println(DateFormatUtils.format(beginDate, "yyyy-MM-dd"));
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
