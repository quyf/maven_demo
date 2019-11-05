package cn.quyf.demo.base.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {
	private Object target;//需要代理的目标对象   
	
	public Object newProxy(Object target){//将目标对象传入进行代理  
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), this);//返回代理对象
	}
	/**
	 * proxy  代理的真实对象
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("invoke");
		return method.invoke(target, args);
	}

}
