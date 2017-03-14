package cn.quyf.demo.base.thread;

public class TestYield {
	public static void main(String[] args) {
		MyThread t1 = new MyThread("t1");
		MyThread t2 = new MyThread("t2");
		t1.start();
		t2.start();
	}
}

class MyThread extends Thread{
	public MyThread(String name) {
		super( name );
	}
	public void run(){
		for( int i=0;i<100;i++){
			System.out.println( getName()+": "+i);
			if( i % 10==0 ){
				this.yield();
			}
		}
	}
}