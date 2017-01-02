package cn.quyf.demo.classloader;

import java.lang.reflect.Method;

/**
 * ClassLoader 用来动态加载class类到内存中用的
 * Java默认提供三个ClassLoader
 * 1、Bootstrap ClassLoader 启动类加载器 最顶层的加载器 ,负责加载jdk的核心类库
 * 2、Extension ClassLoader 扩展类加载器,负责加载java的扩展类库，默认加载jre/lit/ext下所有的jar
 * 3、App ClassLoader 应用类加载器，负责加载应用程序classpath目录下的所有jar 和class文件
 *	除了Java提供的三个默认的类加载器，用户也可以自定义自己的类加载器，但这些自定义的ClassLoader必须继承java.lang.ClassLoader,
 *	也包括 Extension ClassLoader和App ClassLoader都继承自ClassLoader。
 *	BootstrapClassLoader 不继承ClassLoader,它不是一个普通的Java类，底层有C++编写，已在JVM内核中。
 *	当JVM启动后，Bootstrap ClassLoader也随着启动，负责加载完核心类库后，并构造Extension ClassLoader和App ClassLoader类加载器。
 * 
 * @author quyf
 * @date 2016-12-9
 */
public class ClassLoaderTest {

	public static void main(String[] args) throws Exception {
		System.out.println(System.getProperty("sun.boot.class.path"));
		System.out.println(System.getProperty("java.ext.dirs"));  
		System.out.println(System.getProperty("java.class.path"));  
		
		ClassLoader loader = ClassLoaderTest.class.getClassLoader();
		//System.out.println( loader.getResource(""));
		while( loader!=null ){
			//System.out.println( loader.toString());
			loader = loader.getParent();
		}
		ClassLoaderTest t = new ClassLoaderTest();
		t.testClassIdentify();
	}
	
	public void testClassIdentify() throws Exception{
		String projectPath = System.getProperty("user.dir");
		String classPath = projectPath + "\\target\\classes\\";
		FileSystemClassLoader loader = new FileSystemClassLoader(classPath);
		String className = "cn.quyf.demo.classloader.Sample";
		Class<?> c = loader.loadClass(className);
		Object obj = c.newInstance();
		Method method = c.getMethod("sayHello", java.lang.String.class);
		method.invoke(obj, "quyf");
		
		
	}
}
