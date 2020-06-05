package cn.quyf.demo.base;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.UUID;

/**
 * @author quyf
 * @date 2020/4/1 14:07
 * @desc TODO
 **/
public class BigDecimalDemo {

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
        BigDecimal a = new BigDecimal(10);
        a.add(new BigDecimal(1));
        System.out.println(a);
        //2038-01-19 03:14:07.999999
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,2038);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 20);

        System.out.println(cal.getTime().getTime());
        System.out.println(Long.MAX_VALUE);
        Long userId = 14447713204L;
        System.out.println(userId % 8);
        System.out.println(userId / 8 % 100);

        int upgradeDays = 97;
        BigDecimal vipTypeAmount = new BigDecimal(15);//15
        BigDecimal baseVipTypeAmount = new BigDecimal(12);;
        System.out.println("比较：" + vipTypeAmount.compareTo(baseVipTypeAmount));
        //计算补差价
        BigDecimal remainVipTypeAmount = vipTypeAmount.divide(new BigDecimal(31), 5, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(upgradeDays));
        BigDecimal remainBaseVipTypeAmount = baseVipTypeAmount.divide(new BigDecimal(31), 5, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(upgradeDays));

        BigDecimal bigDecimal = remainVipTypeAmount.subtract(remainBaseVipTypeAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal);


        BigDecimal ac = new BigDecimal(3.46);
        System.out.println(a.setScale(0, BigDecimal.ROUND_HALF_UP));
    }
}
