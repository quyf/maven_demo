package cn.quyf.demo.base.thread;

/**
 * @author quyf
 * @date 2019/7/8 13:02
 */
public class HowToStopThreadQuestion {

    public static void main(String[] args) throws InterruptedException {
        Action action = new Action();
        Thread thread1 = new Thread(action);
        thread1.start();
        //thread1.join();
        Thread.sleep(100);
        action.setStop(true);
        System.out.println("main...");
    }
    
    private static class Action implements Runnable{
        
        private boolean stop = false;
        @Override
        public void run() {
            while(!stop){
                System.out.println("线程正在执行,"+Thread.currentThread().getName());
            }
        }

        public boolean isStop() {
            return stop;
        }

        public void setStop(boolean stop) {
            this.stop = stop;
        }
    }
}
