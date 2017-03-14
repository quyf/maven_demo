package cn.quyf.demo.base.thread;

public class MianShi implements Runnable{
	
	public MianShi(String name){
		Thread.currentThread().setName(name);
	}
	public static Integer i = 0;
	public static synchronized void inc(){
		i++;
		System.out.println(Thread.currentThread().getName()+"=="+ i );
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		for(int i=0;i<50;i++){
			inc();
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new MianShi("1"));
		t1.start();
		Thread t2 = new Thread(new MianShi("2"));
		t2.start();
	}
}
