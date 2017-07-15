package cn.quyf.demo.base.proxy.jdk;

import cn.quyf.demo.base.proxy.HelloService;
import cn.quyf.demo.base.proxy.HelloServiceImpl;

public class JdkProxyTest {

	public static void main(String[] args) {
		JDKProxy proxy = new JDKProxy();
		HelloService impl = (HelloService) proxy.newProxy(new HelloServiceImpl());
		impl.sayHello();
	}
}
