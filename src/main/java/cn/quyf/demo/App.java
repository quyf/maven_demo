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

        String c1 = "CREATE TABLE `game_month_card_order_%s` (\n" +
                "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',\n" +
                "  `order_no` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单号',\n" +
                "  `user_id` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户id',\n" +
                "  `plan_code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '购买方案code',\n" +
                "  `order_status` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT 'INIT' COMMENT '订单状态',\n" +
                "  `open_time` timestamp NULL DEFAULT NULL COMMENT '开通时间',\n" +
                "  `open_days` int(11) DEFAULT '0' COMMENT '开通时长(天)',\n" +
                "  `pay_no` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '支付流水号',\n" +
                "  `pay_status` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '支付状态',\n" +
                "  `pay_method` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '支付方式',\n" +
                "  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '支付金额(元)',\n" +
                "  `currency` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT 'RMB' COMMENT '支付货币类型',\n" +
                "  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',\n" +
                "  `order_desc` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单描述',\n" +
                "  `country` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT 'CN' COMMENT '国家',\n" +
                "  `imei` varchar(2048) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机imei',\n" +
                "  `client_ip` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '客户端ip',\n" +
                "  `model` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机机型',\n" +
                "  `brand` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '品牌',\n" +
                "  `request_context` varchar(2000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求上下文',\n" +
                "  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                "  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,\n" +
                "  PRIMARY KEY (`id`) USING BTREE,\n" +
                "  UNIQUE KEY `uk_order_no` (`order_no`) USING BTREE COMMENT '订单号唯一索引',\n" +
                "  KEY `idx_user_id` (`user_id`),\n" +
                "  KEY `idx_create_time` (`create_time`)\n" +
                ") ENGINE=InnoDB  COLLATE=utf8mb4_unicode_ci COMMENT='游戏月卡订单表';";
        for (int i = 0; i < 10; i++) {
            System.out.printf(c1, i);
            System.out.println();
        }
    }
}
