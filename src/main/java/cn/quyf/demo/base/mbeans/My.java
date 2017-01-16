package cn.quyf.demo.base.mbeans;

/**
 * Standard MBean由一个接口和一个实现类组成。
 * 命名规范 :
 * 接口 : XXXMBean
 * 实现类 : XXX
 * 
 * @author quyf
 *
 */
public class My implements MyMBean{

	private int id;
	private String name;
	private int age;
	
	public My(){
		this.id = 1024;
		this.name = "quyf";
		this.age = 27;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
    public void sayHello() {  
        System.out.println("hello, my MyMBean");  
    }  
}
