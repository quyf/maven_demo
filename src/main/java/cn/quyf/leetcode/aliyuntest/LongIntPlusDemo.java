package cn.quyf.leetcode.aliyuntest;

/**
 * @author quyf
 * @date 2019/11/27 9:22
 */
public class LongIntPlusDemo {

    public static void main1(String[] args) {
        int num = 2147483647 ;
        num += 2L;
        //-2147483647
        System.out.println(num);
        System.out.println(Integer.MAX_VALUE);
    }

    public static void main2(String[] args) {
        int num = 2147483647 ;
        long temp = num + 2L ;
        System.out.println(num) ;
        System.out.println(temp) ;
    }

    public static void main(String args[]) {
        int num = 68 ;
        char c = (char) num ;
        System.out.println(c) ;
    }
}
