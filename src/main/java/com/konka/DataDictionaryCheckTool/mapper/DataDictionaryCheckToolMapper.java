package com.konka.DataDictionaryCheckTool.mapper;

import com.konka.DataDictionaryCheckTool.model.po.DataDictionaryCheckTool;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Version 1.0.0
 * @Description 巡检记录Mapper
 * @Author imp
 * @Since 1.0.0
 * @Create 2019-09-06 15:48
 **/
@Repository
public interface DataDictionaryCheckToolMapper {
    
    /**
     * 保存数据
     * @param DataDictionaryCheckTool 数据字典
     * @return 影响行数
     */
    Integer save(DataDictionaryCheckTool DataDictionaryCheckTool);
    
    /**
     * 根据数据库名获取数据字典
     * @param schemaName 数据库名
     * @return DataDictionaryCheckTool 数据字典列表
     */
    List<DataDictionaryCheckTool> get(String schemaName);
    
    /**
     * 根据数据库表名获取数据字典
     * @param DataDictionaryCheckTool 数据库字段model
     * @return DataDictionaryCheckTool 数据字典列表
     */
    List<DataDictionaryCheckTool> getByTable(DataDictionaryCheckTool DataDictionaryCheckTool);
    
    /**
     * 根据数据库字段名获取数据字典
     * @param DataDictionaryCheckTool 数据库字段model
     * @return DataDictionaryCheckTool 数据字典列表
     */
    DataDictionaryCheckTool getByColumn(DataDictionaryCheckTool DataDictionaryCheckTool);
}
