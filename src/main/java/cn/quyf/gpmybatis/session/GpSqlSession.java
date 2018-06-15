package cn.quyf.gpmybatis.session;

import cn.quyf.gpmybatis.executor.GpExecutor;
import cn.quyf.gpmybatis.executor.GpSimpleExecutor;
import cn.quyf.gpmybatis.mapper.GpMapperProxy;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2018/6/12.
 */
public class GpSqlSession {
    //private GpConfigure configure;
    private GpExecutor executor = new GpSimpleExecutor();

    public GpSqlSession(){}

    public GpSqlSession(GpExecutor executor) {
        this.executor = executor;
    }

    public <T> T selectOne(String statement, Object param){
        return executor.query(statement, param);
    }

    public <T> T getMapper(Class<T> clz){
        return (T)Proxy.newProxyInstance(clz.getClassLoader(),new Class[]{clz},
                new GpMapperProxy(this, clz));
    }

}
