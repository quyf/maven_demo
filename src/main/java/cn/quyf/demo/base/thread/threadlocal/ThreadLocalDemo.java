package cn.quyf.demo.base.thread.threadlocal;

public class ThreadLocalDemo extends Thread{

	private Resource res;
	
	public ThreadLocalDemo(Resource res){
		this.res = res;
		//this.res.setNum();
	}
	
	public void run(){
		//每个线程都要初始化一下值，要么就在创建ThreadLocal时候 初始化
		res.setNum();
		for(int i=0;i<3;i++){
			System.out.println(Thread.currentThread().getName()+"==="+res.getNum());
		}
	}
	public static void main(String[] args) {
		Resource res = new Resource();
		//res.setNum();
		ThreadLocalDemo t1 = new ThreadLocalDemo(res);
		ThreadLocalDemo t2 = new ThreadLocalDemo(res);
		t1.start();
		t2.start();
	}
}

class Resource{
	private static ThreadLocal<Long> th = new ThreadLocal<Long>()
//	{
//		@Override
//		protected Integer initialValue() {
//			return 0;
//		}
//	}
	;
	
	public Resource(){
		//th.set(new Long(0L));
	}
	public void setNum(){
		th.set(new Long(0));
	}
	public long getNum(){
		long count = th.get()+1;
		th.set(count);
		return count;
	}
}
