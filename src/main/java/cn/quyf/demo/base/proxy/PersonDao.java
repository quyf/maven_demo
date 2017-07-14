package cn.quyf.demo.base.proxy;

public class PersonDao {
    public void getAllPerson(){
        System.out.println("查询数据库得到所有的Person");
    }
    
    public void deleteAllPerson(){
        System.out.println("删除数据库所有的Person");
    }
}
