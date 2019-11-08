package cn.quyf.util.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author quyf
 * @date 2019/11/4 17:25
 */
public class JolDemo {
    
    static Object gen(){
        Map<String, Object> map = new HashMap<>();
        map.put("a", new Integer(1));
        map.put("b", "b");
        map.put("c", new Date());

        for (int i = 0; i < 10; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
        return map;
    }
    
    static void print(String msg){
        System.out.println(msg);
        System.out.println("================");
    }

    public static void main(String[] args) {
        Object gen = gen();
        int hashCode = gen.hashCode();
        System.out.println( Integer.toBinaryString(hashCode));
        System.out.println( Integer.toBinaryString(hashCode >>> 16));
        System.out.println( Integer.toBinaryString(hashCode^ (hashCode >>> 16)));
        System.out.println( Integer.toBinaryString((hashCode^ (hashCode >>> 16))&0x7fffffff));
        //System.out.println(Integer.parseInt("0x7fffffff", 16));
        //Map<String, Object> gen = new HashMap<>();
       // System.out.println(gen.hashCode());
        //print(ClassLayout.parseInstance(gen).toPrintable());
        //print(GraphLayout.parseInstance(gen).toPrintable());
    }
}
