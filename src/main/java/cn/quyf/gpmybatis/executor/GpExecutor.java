package cn.quyf.gpmybatis.executor;

/**
 * Created by Administrator on 2018/6/12.
 */
public interface GpExecutor {
    <T> T query(String statement, Object params);
}
