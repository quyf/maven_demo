package cn.quyf.demo.base.gc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author quyf
 * @date 2019/11/1 9:40
 * -Xms15M -Xmx15M -XX:MetaspaceSize=25M -XX:MaxMetaspaceSize=25M -XX:+PrintGCDetails
 */
public class HeapDemo {

    private byte[] a = new byte[1024*100];
    public static void main(String[] args) throws InterruptedException {

        ArrayList<HeapDemo> list = new ArrayList<>();
        while (true){
            list.add(new HeapDemo());
            Thread.sleep(100);
        }
    }
}
