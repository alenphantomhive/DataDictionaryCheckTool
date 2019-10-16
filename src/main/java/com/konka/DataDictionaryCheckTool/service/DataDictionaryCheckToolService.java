package com.konka.DataDictionaryCheckTool.service;

import com.konka.DataDictionaryCheckTool.model.po.ErrList;
import com.konka.DataDictionaryCheckTool.model.request.LinkInDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Version 1.0.0
 * @Description 数据字典Service
 * @Author imp
 * @Since 1.0.0
 * @Create 2019-09-06 15:45
 **/
public interface DataDictionaryCheckToolService {
    /**
     * 导入数据字典
     *
     * @param file 文件
     * @return 导入操作成功返回信息
     */
     List<ErrList> importFile(MultipartFile file) ;
    
    /**
     * 连接数据库
     * @param link 登陆
     * @param file 文件
     * @return 登陆信息
     */
    List<ErrList> link(LinkInDTO link, MultipartFile file);
}
