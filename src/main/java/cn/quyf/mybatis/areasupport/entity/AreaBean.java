package cn.quyf.mybatis.areasupport.entity;

public class AreaBean {
	private Integer id;
	private String name;
	private String nameTraditional;
	private String nameEn;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameTraditional() {
		return nameTraditional;
	}
	public void setNameTraditional(String nameTraditional) {
		this.nameTraditional = nameTraditional;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	@Override
	public String toString() {
		return "AreaBean [id=" + id + ", name=" + name + ", nameTraditional=" + nameTraditional + ", nameEn=" + nameEn
				+ "]";
	}
	
}
