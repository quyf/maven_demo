package cn.quyf.demo.base.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quyf
 * @date 2019/11/1 9:40
 * -Xms15M -Xmx15M -XX:MetaspaceSize=25M -XX:MaxMetaspaceSize=25M -XX:+PrintGCDetails
 */
public class GcDemo {

    private static List<String> list = new ArrayList<String>();
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000);
        
        Integer i = 0;
        while (true){
            //list.add(String.valueOf(i).intern());
            String.valueOf(i).intern();
            //System.out.println(i);
            i++;
        }
    }
}
