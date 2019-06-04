package cn.lizi.lizi.model.code;

import lombok.Data;

/**
 * @author why
 * 数据库字段封装类
 * Created by Ay on 2017/5/3.
 */
@Data
public class ColumnClass {

    /**
     * 数据库字段名称
     **/
    private String columnName;
    /**
     * 数据库字段类型
     **/
    private String columnType;
    /**
     * 数据库字段首字母小写且去掉下划线字符串
     **/
    private String changeColumnName;
    /**
     * 数据库字段注释
     **/
    private String columnComment;

    private String charOctetLength;

}
