package cn.quyf.mybatis.areasupport;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import cn.quyf.mybatis.areasupport.entity.AreaBean;
import cn.quyf.mybatis.areasupport.mapper.AreaMapper;

public class TestSelect {
	
	public static void main(String[] args) throws IOException {
		System.out.println( getByMapper(1).toString());
	}
	
	private static AreaBean getByMapper(Integer id){
		SqlSession sqlSession = BaseDao.getSqlSessionFactory().openSession();
		AreaMapper areaMapper = sqlSession.getMapper( AreaMapper.class);
		AreaBean area = areaMapper.selectById(id);
		
		sqlSession.close();
		return area;
	}
	
}
