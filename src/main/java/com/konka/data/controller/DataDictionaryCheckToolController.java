package com.konka.data.controller;

import com.konka.data.common.Response;
import com.konka.data.exception.ExceptionCode;
import com.konka.data.model.request.LinkInDTO;
import com.konka.data.service.DataDictionaryCheckToolService;
import com.konka.data.utils.ParamValidationUtil;
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
 * @ClassName DataDictionaryCheckToolController
 * @Description
 * @Author imp
 * @Date 2019/9/19 14:24
 * @Version V1.0
 **/
@RestController
@Slf4j
@RequestMapping("/data")
@Api(tags = "数据字典管理")
public class DataDictionaryCheckToolController {
    
    
    /**
     * Sevice层接口
     */
    @Autowired
    private DataDictionaryCheckToolService DataDictionaryCheckToolService;
    
    

    
    
    /**
     * 数据字典检查
     */
    @PostMapping("")
    @ApiOperation("数据字典检查")
    @ApiResponses({
            @ApiResponse(code = ExceptionCode.DataDictionaryCheckTool.ILLEGAL_PARAM_CODE, message = ExceptionCode.DataDictionaryCheckTool.ILLEGAL_PARAM_MSG),
            @ApiResponse(code=ExceptionCode.Login.ILLEGAL_PARAM_CODE, message = ExceptionCode.Login.ILLEGAL_PARAM_MSG),
            @ApiResponse(code=ExceptionCode.Login.LOGIN_FAULT_CODE, message = ExceptionCode.Login.LOGIN_FAULT_MSG),
            @ApiResponse(code =ExceptionCode.DataDictionaryCheckTool.SERVER_NOT_FOUND_CODE,message =ExceptionCode.DataDictionaryCheckTool.SERVER_NOT_FOUND_MSG),
            @ApiResponse(code = ExceptionCode.DataDictionaryCheckTool.ILLEGAL_PARAM_CODE,message =ExceptionCode.DataDictionaryCheckTool.FILE_ERROR_MSG),
            @ApiResponse(code = ExceptionCode.DataDictionaryCheckTool.ILLEGAL_PARAM_CODE,message =ExceptionCode.DataDictionaryCheckTool.TABLE_ERROR_MSG)
    })
    public Response<List> link(LinkInDTO link, MultipartFile file) {
        //校验参数
        ParamValidationUtil.check(link, ExceptionCode.Login.ILLEGAL_PARAM_CODE, ExceptionCode.Login.ILLEGAL_PARAM_MSG);
        return new Response<>(DataDictionaryCheckToolService.link(link,file));
    }
    
}
