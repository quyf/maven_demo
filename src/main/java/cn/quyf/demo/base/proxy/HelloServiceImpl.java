package cn.quyf.demo.base.proxy;

public class HelloServiceImpl implements HelloService {

	@Override
	public void sayHello() {
		System.out.println("hello world");
	}

	@Override
	public String name() {
		return "hello world name";
	}

}
