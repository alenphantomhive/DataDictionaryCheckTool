package com.konka.DataDictionaryCheckTool.model.po;

import lombok.Data;

/**
 * @ClassName Link
 * @Description
 * @Author
 * @Date 2019/9/24 17:11
 * @Version V1.0
 **/
@Data
public class Link {
    /**
     * 数据库链接
     */
    String url;
    
    /**
     * 用户
     */
    String user;
    
    /**
     * 密码
     */
    String pwd;
}