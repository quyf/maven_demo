package cn.quyf.demo.base.thread;

public class VolatileDemo {

	public static void main(String[] args) throws Exception{
		ThreadVolatileThread t1 = new ThreadVolatileThread();
		t1.start();
		Thread.sleep(3000);
		t1.setFlag(false);
		System.out.println("flag 已经设置成false");
		
		Thread.sleep(2000);
		System.out.println(t1.flag);
	}
}


class ThreadVolatileThread extends Thread{
	//这里加与不加 volatile  运行结果差别很大
	public volatile boolean flag = true;
	
	public void run(){
		System.out.println("线程开始了，，，");
		while( flag ){
			
		}
		System.out.println("线程结束了，，，，");
	}
	
	public void setFlag(boolean flag){
		this.flag = flag;
	}
}