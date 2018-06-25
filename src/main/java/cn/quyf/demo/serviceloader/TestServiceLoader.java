package cn.quyf.demo.serviceloader;

import java.util.ServiceLoader;

/**
 * https://www.cnblogs.com/sparkbj/articles/6208328.html
 * @author quyf
 * @date 2018年6月25日
 */

/**
1、 创建一个接口文件
2、在resources资源目录下创建META-INF/services文件夹
3、在services文件夹中创建文件，以接口全名命名
4、创建接口实现类
 */
public class TestServiceLoader {
	public static void main(String[] args) {
		ServiceLoader<IService> serviceLoader = ServiceLoader.load(IService.class);
		for(IService  service : serviceLoader){
			service.sayHello();
		}
	}
}
