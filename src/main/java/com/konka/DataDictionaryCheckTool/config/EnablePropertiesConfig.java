package com.konka.DataDictionaryCheckTool.config;

import com.konka.DataDictionaryCheckTool.properties.VariableProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * @version 1.0.0
 * @Description 使VariableProperties生效
 * @create 2019-08-21 09:32
 * @since 1.0.0
 **/
@Configuration
@EnableConfigurationProperties(VariableProperties.class)
public class EnablePropertiesConfig {
}
