package cn.quyf.simplespringmvc.service.impl;

import cn.quyf.simplespringmvc.annotation.Service;
import cn.quyf.simplespringmvc.service.HelloService;

@Service("helloServiceImpl")
public class HelloServiceImpl implements HelloService{

	@Override
	public String sayHello() {
		return "hello world";
	}

}
