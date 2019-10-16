package com.konka.DataDictionaryCheckTool.model.response;

/**
 * @ClassName ErrListOutDTO
 * @Description
 * @Author
 * @Date 2019/9/26 14:17
 * @Version V1.0
 **/

public class ErrListOutDTO {
    /**
     * 表格序号
     */
    private  Integer tableId;
    
    /**
     * 表格行数
     */
    private  Integer rowId;
    
    /**
     * 数据库表名称
     */
    private String tableName;
    
    /**
     * 字段名称
     */
    private String columnName;
    /**
     * 字段类型
     */
    private String columnType;
    /**
     * 是否为空
     */
    private String nullAble;
    
    /**
     * 错误信息
     */
    private String msg;
}