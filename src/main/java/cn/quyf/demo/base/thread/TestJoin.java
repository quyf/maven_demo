package cn.quyf.demo.base.thread;

public class TestJoin {

	public static void main(String[] args) throws InterruptedException {
		for( int i=0;i<10 ;i++){
			System.out.println( Thread.currentThread().getName()+"--"+i);
			JoinThread join = new JoinThread();
			join.start();
			//主线程在此将JoinThread线程join进来，则主线程必须等待JoinThread线程执行完毕才会继续执行当前线程
			join.join();
		}
	}
}

class JoinThread extends Thread{
	public void run(){
		for(int i=0;i<5;i++){
			System.out.println( this.getName()+"================"+i);
		}
	}
}
