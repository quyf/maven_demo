package cn.quyf.demo.base.lock;

/**
 * java -v -public xxx.class 查看编译情况
 * I:\gitproject\maven_demo\target\classes\cn\quyf\demo\base\lock>javap -v -public SynchronizedCompileQuestion
 * @author quyf
 * @date 2019/7/9 9:25
 */
public class SynchronizedCompileQuestion {

    public static void main(String[] args) {
        
    }
    
    public static void testLockClass(){
        synchronized (SynchronizedCompileQuestion.class){
            String name = "world";
            System.out.println("hello," + name);
        }
        testSync();
    }

    public synchronized static void testSync() {
        String name = "world";
        System.out.println("hello," + name);
    }
}
