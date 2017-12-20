package cn.quyf.demo.base.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.quyf.demo.base.proxy.HelloService;
import cn.quyf.demo.base.proxy.HelloServiceImpl;

public class JdkProxyTest {

	public static void main(String[] args) {
//		JDKProxy proxy = new JDKProxy();
//		HelloService impl = (HelloService) proxy.newProxy(new HelloServiceImpl());
//		impl.sayHello();
		/*
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass().getClassLoader() ，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数realSubject.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
         */
		HelloService t = (HelloService)Proxy.newProxyInstance(HelloService.class.getClassLoader(), 
				new Class<?>[]{HelloService.class}, 
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						return method.invoke(new HelloServiceImpl(), args);
					}
				});
		t.sayHello();
	}
	
}
