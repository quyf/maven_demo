package cn.quyf.demo.spring.sequence;

/**
 * @author quyf
 * @date 2019/11/20 17:48
 */
public interface ISequenceDao {
    Long getNextId(SequenceConst.TableName tableName);
}
