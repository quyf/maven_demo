package cn.quyf.demo.base.mbeans;

public interface MyMBean {

	public void sayHello();
	
	//如果把下面几个get set方法屏蔽掉，那么jconsole管理MBean的界面就获取不到对象的属性
	public int getId() ;

	public void setId(int id) ;

	public String getName() ;

	public void setName(String name) ;

	public int getAge() ;

	public void setAge(int age) ;
	
}
