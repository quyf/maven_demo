package cn.quyf.demo.base.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloHandle implements InvocationHandler{

	private Object srcObj;
	
	public HelloHandle(Object srcObj) {
		this.srcObj = srcObj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("HelloHandle,invoke()");
		return method.invoke(srcObj, null);
	}

}
