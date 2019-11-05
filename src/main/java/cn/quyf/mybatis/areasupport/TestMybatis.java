package cn.quyf.mybatis.areasupport;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class TestMybatis {

	public static void main(String[] args) throws Exception {
		String resource = "cn/quyf/mybatis/areasupport/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		System.out.println(sqlSession.selectList("select * from area").size());
	}
	
//	public void seletBySession(){
//		SqlSession session = sqlSessionFactory.openSession();
//		try {
//		  T blog = <T>session.selectOne(
//		    "org.mybatis.example.BlogMapper.selectBlog", 101);
//		} finally {
//		  session.close();
//		}
//	}
//	
//	public void selectByMapper(){
//		SqlSession session = sqlSessionFactory.openSession();
//		try {
//		  BlogMapper mapper = session.getMapper(BlogMapper.class);
//		  Blog blog = mapper.selectBlog(101);
//		} finally {
//		  session.close();
//		}
//	}
}
