package cn.quyf.demo.base.thread;

public class IdGeneratorThread {

	public int value = 0;
	
	public int getNextId(){
		return ++value;
	}
	public static void main(String[] args) {
		IdGeneratorThread gen = new IdGeneratorThread();
		
		IdThread t1 = new IdThread(gen);
		IdThread t2 = new IdThread(gen);
		IdThread t3 = new IdThread(gen);
		IdThread t4 = new IdThread(gen);
		t1.start();t2.start();t3.start();t4.start();
	}
}

class IdThread extends Thread{
	private IdGeneratorThread gen;
	public IdThread(IdGeneratorThread gen){
		this.gen = gen;
	}
	public void run(){
		String tName = Thread.currentThread().getName();
		for(int i=0;i<200;i++){
			System.out.println(tName+"==="+ gen.getNextId());
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}