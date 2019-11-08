package cn.quyf.demo.gof.chain;

public abstract class Handler {

	protected Handler successor;
	
	public void setSuccssor(Handler successor){
		this.successor = successor;
	}
	
	public abstract void hanldRequest();
}
