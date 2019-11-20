package cn.quyf.demo.spring.sequence;

/**
 * @author quyf
 * @date 2019/11/20 17:48
 */
public class SequenceConst {

    public static enum TableName{
        /**订单表*/
        ORDER("order",1),
        ;

        private String value;
        private long id;

        private TableName(String value,int id) {
            this.value = value;
            this.id = id;
        }

        public String getValue() {
            return this.value;
        }

        public long getId() {
            return this.id;
        }
    }
}
