package com.konka.datademo.controller;

import com.konka.datademo.common.Response;
import com.konka.datademo.exception.ExceptionCode;
import com.konka.datademo.model.request.LinkInDTO;
import com.konka.datademo.service.DataBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName DataBaseController
 * @Description
 * @Author imp
 * @Date 2019/9/19 14:24
 * @Version V1.0
 **/
@RestController
@Slf4j
@RequestMapping("/data")
@Api(tags = "数据字典管理")
public class DataBaseController {
    
    
    /**
     * Sevice层接口
     */
    @Autowired
    private DataBaseService databaseService;
    
    

    
    /**
     * 通过Word导入数据字典
     */
    @PostMapping("/import")
    @ApiOperation("通过Word导入数据字典")
    @ApiResponses({
            @ApiResponse(code = ExceptionCode.DataBase.ILLEGAL_PARAM_CODE, message = ExceptionCode.DataBase.ILLEGAL_PARAM_MSG),
            @ApiResponse(code =ExceptionCode.DataBase.SERVER_NOT_FOUND_CODE,message =ExceptionCode.DataBase.SERVER_NOT_FOUND_MSG),
            @ApiResponse(code = ExceptionCode.DataBase.ILLEGAL_PARAM_CODE,message =ExceptionCode.DataBase.FILE_ERROR_MSG)
    })
    public Response<List> importFile(MultipartFile file){
        return new Response<>(databaseService.importFile(file));
    }
    
    /**
     * 连接数据库
     */
    @PostMapping("/link")
    @ApiOperation("连接数据库")
    @ApiResponses({
            @ApiResponse(code = ExceptionCode.DataBase.ILLEGAL_PARAM_CODE, message = ExceptionCode.DataBase.ILLEGAL_PARAM_MSG),
            @ApiResponse(code=ExceptionCode.Login.ILLEGAL_PARAM_CODE, message = ExceptionCode.Login.ILLEGAL_PARAM_MSG),
            @ApiResponse(code=ExceptionCode.Login.LOGIN_FAULT_CODE, message = ExceptionCode.Login.LOGIN_FAULT_MSG),
            @ApiResponse(code =ExceptionCode.DataBase.SERVER_NOT_FOUND_CODE,message =ExceptionCode.DataBase.SERVER_NOT_FOUND_MSG),
            @ApiResponse(code = ExceptionCode.DataBase.ILLEGAL_PARAM_CODE,message =ExceptionCode.DataBase.FILE_ERROR_MSG)
    })
    public Response<List> link(LinkInDTO link, MultipartFile file) {
        return new Response<>(databaseService.link(link,file));
    }
    
}