package cn.quyf.gpmybatis.mapper;

import cn.quyf.gpmybatis.my.TestMapperXml;
import cn.quyf.gpmybatis.session.GpSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/6/12.
 */
public class GpMapperProxy<T> implements InvocationHandler {

    private final GpSqlSession sqlSession;
    private final Class<T> mapperInterfaceClz;

    public GpMapperProxy(GpSqlSession sqlSession, Class<T> mapperInterfaceClz) {
        this.sqlSession = sqlSession;
        this.mapperInterfaceClz = mapperInterfaceClz;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       if( method.getDeclaringClass().getName().equals(TestMapperXml.nameSpace)){
           String sql = TestMapperXml.methodSqlMapping.get(method.getName());
           System.out.println(String.format("SQL [ %s ], parameter [%s] ", sql, args[0]));
           return sqlSession.selectOne(sql, String.valueOf(args[0]));
       }
        return null;
    }
}
