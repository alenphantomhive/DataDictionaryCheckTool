package com.konka.datademo.properties;

import lombok.Data;

/**
 * @author hp
 * @version 1.0.0
 * @Description 可变数据字典导入模板列数配置
 * @create 2019-08-21 09:24
 * @since 1.0.0
 **/
@Data
public class ImportDataProperties {



    /**
     * 上传文件后缀
     */
    private String fileType = "docx";
    
    
    /**
     * 设置开始表格
     */
    private Integer tableNum = 5;

    /**
     * 字段名称
     */
    private Integer nameCellNum = 0;


    /**
     * 字段类型
     */
    private Integer typeCellNum = 1;


    /**
     * 为空
     */
    private Integer nullAbleCellNum = 2;


    /**
     * 数据库表名称
     */
    private Integer tableNameCellNum = 1;




}
