package cn.quyf.demo.base;

import org.apache.kafka.common.protocol.types.Field;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * @author quyf
 * @date 2020/4/27 16:11
 * @desc TODO
 **/
public class EqualsDemo {

    public static void main(String[] args) {
        System.out.println();
        String s = "a,b,c";
        if (Arrays.asList(s.split(",")).contains("d")) {
            System.out.println("ccc");
        }
    }
}
