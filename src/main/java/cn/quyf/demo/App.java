package cn.quyf.demo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        Map<Long, BigDecimal> map = new TreeMap<>();
        map.put(2L, new BigDecimal(4));
        map.put(1L, new BigDecimal(5));
        System.out.println(map);


        BigDecimal b = new BigDecimal(1);
        System.out.println(b.compareTo(BigDecimal.ZERO));

        String c1 = "ALTER TABLE vip_order_%s ADD COLUMN `market_coupon_ids` VARCHAR(255) NULL DEFAULT NULL COMMENT '优惠券ID列表' COLLATE 'utf8mb4_unicode_ci';";
        String c2 = "ALTER TABLE vip_order_%s ADD COLUMN `features` VARCHAR(500) NULL DEFAULT NULL COMMENT 'json格式' COLLATE 'utf8mb4_unicode_ci';";
        for (int i = 0; i < 1; i++) {
            System.out.printf(c1, i);
            System.out.println();
            System.out.printf(c2, i);
            System.out.println();
            System.out.println();
        }
    }
}
