package cn.quyf.gpmybatis.my;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/12.
 */
public class TestMapperXml {
    public static final String nameSpace = "cn.quyf.gpmybatis.my.TestMapper";

    public static Map<String,String> methodSqlMapping = new HashMap<String,String>();

    static{
        methodSqlMapping.put("selectByPrimaryKey", "select * from test where id = %d");
    }
}
