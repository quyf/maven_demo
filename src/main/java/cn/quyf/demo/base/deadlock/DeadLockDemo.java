package cn.quyf.demo.base.deadlock;

public class DeadLockDemo {

	static String A = "a";
	static String B = "b";
	
	public static void main(String[] args) {
		new DeadLockDemo().deadLock();
	}
	
	private void deadLock(){
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				synchronized( A ){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					synchronized(B){
						System.out.println("b");
					}
				}
			}
			
		});
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				synchronized( B ){
					synchronized(A){
						System.out.println("A");
					}
				}
			}
			
		});
		t1.start();
		t2.start();
	}
}
