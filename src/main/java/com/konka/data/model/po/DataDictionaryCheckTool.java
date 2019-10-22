package com.konka.data.model.po;

import lombok.Data;


/**
 * @ClassName DataDictionaryCheckTool
 * @Description po
 * @Author imp
 * @Date 2019/9/19 14:48
 * @Version V1.0
 **/
@Data
public class DataDictionaryCheckTool {
 
 /**
  * 数据库名称
  */
    private String schemaName;
    
    /**
     * 数据库表名称
     */
    private String tableName;
    
    /**
     * 字段名称
     */
    private String name;
    /**
     * 字段类型
     */
    private String type;
    
    /**
     * 是否为空
     */
    private String nullAble;
    
    
}