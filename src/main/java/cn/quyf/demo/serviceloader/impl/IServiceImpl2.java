package cn.quyf.demo.serviceloader.impl;

import cn.quyf.demo.serviceloader.IService;

public class IServiceImpl2 implements IService{

	@Override
	public void sayHello() {
		System.out.println("hello world - HK");
	}

}
