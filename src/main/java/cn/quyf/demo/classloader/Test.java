package cn.quyf.demo.classloader;

public class Test {

	static{
		System.out.println("test static...");
	}
	public Test(){
		System.out.println(" test construct ...");
	}
}
