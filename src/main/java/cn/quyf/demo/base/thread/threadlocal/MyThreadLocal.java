package cn.quyf.demo.base.thread.threadlocal;

public class MyThreadLocal {

    //private static  ThreadLocal<Integer> intLocal=new ThreadLocal<Integer>();
    private static ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    private static ThreadLocal<String> stringLocal = new ThreadLocal<String>();

    public void set() {
        //将线程的id设置到ThreadLocal
        longLocal.set(Thread.currentThread().getId());
        //将线程的Name设置到ThreadLocal
        stringLocal.set(Thread.currentThread().getName());
    }

    public String getString() {
        //从ThreadLocal中取出线程的变量副本
        return stringLocal.get();
    }

    public Long getLong() {
        //从ThreadLocal中取出变量副本
        return longLocal.get();
    }

    public static void main(String[] args) {
       final MyThreadLocal threadLocal = new MyThreadLocal();
//        threadLocal.set();
//        String string = threadLocal.getString();
//        System.out.println(Thread.currentThread().getName() + ": " + string);
//        Long aLong = threadLocal.getLong();
//        System.out.println(Thread.currentThread().getId() + ": " + aLong);


        Thread thread = new Thread() {
            public void run() {
            	//如果不在这里set 那就threadLocal初始值为null
                //threadLocal.set();
                System.out.println(Thread.currentThread().getName() + ": " + threadLocal.getString());
                System.out.println(Thread.currentThread().getId() + ": " + threadLocal.getLong());
            }
        };
        Thread thread2 = new Thread() {
            public void run() {
                threadLocal.set();
                System.out.println(Thread.currentThread().getName() + ": " + threadLocal.getString());
                System.out.println(Thread.currentThread().getId() + ": " + threadLocal.getLong());
            }
        };
        thread.start();
        thread2.start();
        try {
            thread.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}