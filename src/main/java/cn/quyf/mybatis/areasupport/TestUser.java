package cn.quyf.mybatis.areasupport;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.quyf.mybatis.areasupport.entity.User;

public class TestUser {
	public static void main(String[] args) {
		SqlSession session = BaseDao.getSqlSessionFactory().openSession();
		User user = session.selectOne("user.findUserById", 1);
		System.out.println( user.toString());
		
		List<User> list = session.selectList("user.findUserByName", "quaa");
		System.out.println( list.size());
		System.out.println(list);
		
		 // 插入用户对象
        User newUser = new User();
        newUser.setUsername("王小军");
        newUser.setBirthday(new Date());
        newUser.setSex("1");
        newUser.setAddress("河南郑州");
        session.insert("user.insert", newUser);
        // 提交事务
        session.commit();
        // 获取用户信息主键
        System.out.println(newUser.getId());
        
        
        // 关闭会话
		session.close();
	}
}
