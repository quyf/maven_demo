package cn.quyf.demo.base.Proxy;

import java.lang.reflect.Proxy;

public class ProxyTest {

	public static void main(String[] args) {
		HelloHandle hand = new HelloHandle(new HelloWorldImpl());
		HelloWorld proxy = (HelloWorld) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), new Class[]{HelloWorld.class},hand);
		
		proxy.sayHello();	
	}
}
