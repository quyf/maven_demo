package cn.quyf.gpmybatis.my;

import cn.quyf.gpmybatis.executor.GpExecutor;
import cn.quyf.gpmybatis.session.GpSqlSession;

/**
 * Created by Administrator on 2018/6/12.
 */
public class Bootstrap {
    public static void main(String[] args) {
        GpSqlSession sqlSession = new GpSqlSession();
        TestMapper test = sqlSession.getMapper(TestMapper.class);
        System.out.println( test.selectByPrimaryKey(1));

    }
}
