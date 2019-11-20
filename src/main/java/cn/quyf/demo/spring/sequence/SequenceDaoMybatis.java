package cn.quyf.demo.spring.sequence;

import cn.quyf.demo.spring.sequence.SequenceConst.TableName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author quyf
 * @date 2019/11/20 17:50
 */
@Repository
public class SequenceDaoMybatis implements	ISequenceDao {
    private static Log logger = LogFactory.getLog(SequenceDaoMybatis.class);
    private static final String updateSql = "update sequence set max_id = max_id +step,modify_time=now() where id=?  and table_name=? and max_id=?";
    private static final String querySql = "select id,max_id,step from sequence where id=? and table_name=?";

    private Map<String, Long> tableMaxId = new HashMap<String, Long>(1);
    private Map<String, Long> tableNextId = new HashMap<String, Long>(1);

    /**
     * 绕开Mybatis，直接从数据源中获取连接
     */
    @Autowired
    //private DruidDataSource dataSource;
    private DataSource dataSource;

    private Lock sequenceLock = new ReentrantLock();


    /**
     * 获取大表的下一个可用ID
     */
    @Override
    public Long getNextId(TableName tableName) {
        sequenceLock.lock();
        try {
            long maxId = tableMaxId.get(tableName.getValue()) == null ? 0 : tableMaxId.get(tableName.getValue());
            long nextId = tableNextId.get(tableName.getValue()) == null ? 0 : tableNextId.get(tableName.getValue());
            long newNextId = 0;
            // 当前可用ID小于缓冲区的最大ID则直接使用
            if (nextId < maxId) {
                newNextId = nextId + 1;
                tableNextId.put(tableName.getValue(), newNextId);
                return newNextId;
            }

            // 当缓冲区ID达到最大ID的时候需要清空缓冲区，并且重新申请新ID缓冲区间
            newNextId = lockSequenceRange(tableName);
            tableNextId.put(tableName.getValue(), newNextId);
            return newNextId;
        } finally {
            sequenceLock.unlock();
        }
    }

    /**
     * 重新申请新ID缓冲区间
     */
    private long lockSequenceRange(TableName tableNameConst) {
        String tableName = tableNameConst.getValue();
        long id = tableNameConst.getId();
        long nextId = 0;
        Connection con = null;
        try {
            con = this.dataSource.getConnection();
            for (int i = 0; i < 10; i++) {
                SequenceBean seqBean = querySequenceBean(con, tableName, id);
                if (seqBean == null) {
                    throw new RuntimeException("Y_Sequence not existed table_name=" + tableName + " and id=" + id + " record,not generate id.");
                }

                //如果有并发冲突则下一次再尝试获取新的ID缓冲区
                int updateCount = updateSequenceBean(con, seqBean, tableName, id);
                if (updateCount > 0) {

                    nextId = seqBean.getMaxId();
                    long maxId = nextId + seqBean.getStep();
                    tableMaxId.put(tableName, maxId);

                    logger.info("Sequence range get success.[tableName]=" + tableName + ";[id]=" + id + ";[nextId]=" + nextId + ";[maxId]=" + maxId);
                    return nextId;
                }

                //遇到并发冲突则等待(random(10)*10 + 20)ms后再重新尝试
                try {
                    logger.info("Generate bigtable id buffer failed,existed concurrent.tableName=" + tableName + ";id=" + id);
                    Thread.sleep(new Random(System.currentTimeMillis()).nextInt(10) * 10 + 20);
                } catch (InterruptedException e) {
                    logger.info("Generate bigtable id buffer failed.tableName=" + tableName + ";id=" + id);
                }
            }
        } catch (SQLException e) {
            logger.error("lockSequenceRange error,id=" + id + ";tableName=" + tableName + ".errorMsg=" + e.getMessage(), e);
        } finally {
            close(con);
        }

        //连续尝试10次都失败则记录错误，并且抛出异常,申请ID则失败
        throw new RuntimeException("Generate bigtable id buffer failed,exceed max try count.tableName=" + tableName + ";id=" + id);
    }

    private int updateSequenceBean(Connection con, SequenceBean seqBean, String tableName, long id) throws SQLException {
        int updateCount = 0;
        PreparedStatement updatePst = null;
        try {
            updatePst = con.prepareCall(updateSql);
            updatePst.setLong(1, id);
            updatePst.setString(2, tableName);
            updatePst.setLong(3, seqBean.getMaxId());
            updateCount = updatePst.executeUpdate();
        } catch (Exception e) {
            logger.error("update t_sequence error,id=" + id + ";tableName=" + tableName + ".errorMsg=" + e.getMessage(), e);
        } finally {
            close(updatePst);
        }
        return updateCount;
    }

    private SequenceBean querySequenceBean(Connection con, String tableName, long id) throws SQLException {
        SequenceBean seqBean = null;
        ResultSet rs = null;
        PreparedStatement queryPst = null;
        try {
            queryPst = con.prepareCall(querySql);
            queryPst.setLong(1, id);
            queryPst.setString(2, tableName);
            rs = queryPst.executeQuery();
            while (rs.next()) {
                seqBean = new SequenceBean();
                seqBean.setId(id);
                seqBean.setTableName(tableName);
                seqBean.setMaxId(rs.getLong("max_id"));
                seqBean.setStep(rs.getInt("step"));
            }
        } catch (Exception e) {
            logger.error("Query t_sequence error,id=" + id + ";tableName=" + tableName + ".errorMsg=" + e.getMessage(), e);
        } finally {
            close(rs);
            close(queryPst);
        }
        return seqBean;
    }

    private void close(Connection con) {
        if (con == null) {
            return;
        }

        try {
            con.close();
        } catch (SQLException e) {
            logger.error("Close Connection error.errorMsg=" + e.getMessage(), e);
        }
    }

    private void close(ResultSet rs) {
        if (rs == null) {
            return;
        }

        try {
            rs.close();
        } catch (SQLException e) {
            logger.error("Close ResultSet error.errorMsg=" + e.getMessage(), e);
        }
    }


    private void close(PreparedStatement pst) {
        if (pst == null) {
            return;
        }

        try {
            pst.close();
        } catch (SQLException e) {
            logger.error("Close PreparedStatement error.errorMsg=" + e.getMessage(), e);
        }
    }
}