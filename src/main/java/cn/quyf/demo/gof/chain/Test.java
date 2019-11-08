package cn.quyf.demo.gof.chain;

public class Test {

	public static void main(String[] args) {
		 //先要组装职责链
        Handler h1 = new HanlderA();
        Handler h2 = new HanlderB();

        h1.setSuccssor(h2);    
        //然后提交请求
        h1.hanldRequest();
	}
}
