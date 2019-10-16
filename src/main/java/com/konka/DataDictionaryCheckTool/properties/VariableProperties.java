package com.konka.DataDictionaryCheckTool.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hp
 * @version 1.0.0
 * @Description 可变配置配置类
 * @create 2019-08-21 09:24
 * @since 1.0.0
 **/
@ConfigurationProperties(prefix = "data.variable")
@Data
public class VariableProperties {
    
    

    /**
     * 可变数据字典导入模板列数配置
     */
    private ImportDataProperties importDataProperties= new ImportDataProperties();

}
