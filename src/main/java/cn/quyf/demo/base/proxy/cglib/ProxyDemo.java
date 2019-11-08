package cn.quyf.demo.base.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

public class ProxyDemo {

	public static void main(String[] args) {
		CglibProxy cglib = new CglibProxy();
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(UserServiceImpl.class);
		enhancer.setCallback(cglib);
		UserService userService = (UserService)enhancer.create();
		userService.getAge(20);
	}
}
