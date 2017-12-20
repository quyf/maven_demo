package cn.quyf.demo.base.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor{
	
	/**
	 * obj:需要代理的对象
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("++++++before " + methodProxy.getSuperName() + "++++++"); 
		System.out.println(method.getName());  
		Object rt = methodProxy.invokeSuper(obj, args);
		System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");  
		return rt;
	}

}
