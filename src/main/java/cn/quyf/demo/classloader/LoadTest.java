package cn.quyf.demo.classloader;

public class LoadTest {

	public static void main(String[] args) throws ClassNotFoundException, Exception, IllegalAccessException {
		//使用Class.forName()来加载类，默认会执行初始化块 
		//		Class c = Class.forName("cn.quyf.demo.classloader.Test");
//		c.newInstance();
		// //使用ClassLoader.loadClass()来加载类，不会执行初始化块 
		Thread.currentThread().getContextClassLoader().loadClass("cn.quyf.demo.classloader.Test");
		ClassLoader l = LoadTest.class.getClassLoader();
		Class<?> cl = l.loadClass("cn.quyf.demo.classloader.Test");
		cl.newInstance();
	}
}
