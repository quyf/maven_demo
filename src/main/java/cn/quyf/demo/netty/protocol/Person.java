package cn.quyf.demo.netty.protocol;

import java.io.Serializable;

public class Person implements Serializable{
	private static final long serialVersionUID = 4425836815968472260L;

	private String	name;
	private String	sex;
	private int		age;
	
	public String toString() {
		return "Person [name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
