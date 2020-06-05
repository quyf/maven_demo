package cn.quyf.demo.base.thread.threadlocal;

import org.apache.kafka.common.protocol.types.Field;

/**
 * @author quyf
 * @date 2020/5/20 9:19
 * @desc 参考
 * https://zhuanlan.zhihu.com/p/101780720
 * https://juejin.im/post/5d15d36df265da1bc37f229a
 **/
public class InheritableThreadLocalDemo {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    public static ThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static String get(ThreadLocal<String> tl) {
        return tl.get();
    }

    public static void set(ThreadLocal<String> tl, String value) {
        tl.set(value);
    }

    public static void main(String[] args) {

        for (int i=0;i<5;i++){
            set(threadLocal, "hello");
            set(inheritableThreadLocal, "world" +i);
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ",get="+ get(threadLocal));

                    System.out.println(Thread.currentThread().getName() + ",inheritable get="+ get(inheritableThreadLocal));
                }
            });
            t.start();
        }
    }


}
