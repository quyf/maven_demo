package cn.quyf.demo.spring.sequence;

import java.util.Date;

/**
 * @author quyf
 * @date 2019/11/20 17:50
 */
public class SequenceBean {
    private Long id;

    private String tableName;   //表名
    private Long maxId;         //最大id
    private Integer step;       //id缓冲区步长
    private Date modifyTime;    //更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getMaxId() {
        return maxId;
    }

    public void setMaxId(Long maxId) {
        this.maxId = maxId;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
