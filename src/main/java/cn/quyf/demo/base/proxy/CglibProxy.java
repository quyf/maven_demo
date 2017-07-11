package cn.quyf.demo.base.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
/**
 * http://www.cnblogs.com/HigginCui/p/6307262.html
 * @author quyf
 * @date 2017年7月10日
 */
public class CglibProxy implements MethodInterceptor{

	private Enhancer enhancer = new Enhancer();
	public Object getProxy(Class clazz){
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		Monitor.begin();
		Object rt = proxy.invokeSuper(obj, args);
		Monitor.end();
		return rt;
	}

}
