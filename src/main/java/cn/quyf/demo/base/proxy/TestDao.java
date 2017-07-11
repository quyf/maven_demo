package cn.quyf.demo.base.proxy;

public class TestDao {

	public static void main(String[] args) {
		CglibProxy proxy = new CglibProxy();
		PersonDao personDao = (PersonDao)proxy.getProxy(PersonDao.class);
		personDao.getAllPerson();
		System.out.println("--------------------------------------");
		personDao.deleteAllPerson();
	}
}
