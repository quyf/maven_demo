package cn.quyf.demo.base.proxy;

public class HelloProxy implements HelloService {

	private HelloServiceImpl impl;
	
	public HelloProxy(HelloServiceImpl impl) {
		this.impl = impl;
	}
	@Override
	public void sayHello() {
		System.out.println("before sayHello");
		impl.sayHello();
		System.out.println("after sayHello");
	}

	@Override
	public String name() {
		System.out.println("before name");
		return impl.name();
	}

	public static void main(String[] args) {
		HelloServiceImpl impl = new HelloServiceImpl();
		HelloProxy proxy = new HelloProxy(impl);
		proxy.sayHello();
		System.out.println(proxy.name());
	}
}
