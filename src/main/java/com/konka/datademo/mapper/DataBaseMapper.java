package com.konka.datademo.mapper;

import com.konka.datademo.model.po.DataBase;
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
public interface DataBaseMapper {
    
    /**
     * 保存数据
     * @param dataBase 数据字典
     * @return 影响行数
     */
    Integer save(DataBase dataBase);
    
    /**
     * 根据数据库名获取数据字典
     * @param schemaName 数据库名
     * @return dataBase 数据字典列表
     */
    List<DataBase> get(String schemaName);
    
    /**
     * 根据数据库表名获取数据字典
     * @param tableName 数据库表名
     * @return dataBase 数据字典列表
     */
    List<DataBase> getByTable(String tableName);
    
    /**
     * 根据数据库字段名获取数据字典
     * @param dataBase 数据库字段model
     * @return dataBase 数据字典列表
     */
    DataBase getByColumn(DataBase dataBase);
}
