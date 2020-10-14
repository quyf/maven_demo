package cn.quyf.open.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * @author quyf
 * @date 2020/10/14 19:08
 * @desc TODO
 **/
public class ConfigDemo {
    public static void main(String[] args) {
        Config config = ConfigFactory.load("typesafe/registry.conf");
        System.out.println(config.getConfig("registry").getConfig("nacos").getString("cluster"));
    }
}
