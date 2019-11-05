package cn.quyf.mybatis.areasupport.mapper;

import cn.quyf.mybatis.areasupport.entity.AreaBean;

public interface AreaMapper {

	/**
	 * 这里可用sql注解，如果不用sql注解，那么就要在Mapper.xml文件中 namespace记录Mapper类的全路径了
	 * @param id
	 * @return
	 */
	// @Select("SELECT * FROM area WHERE id = #{id}")
	 AreaBean selectById(int id);
}
