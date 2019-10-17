package com.konka.DataDictionaryCheckTool.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.NotNull;

/**
 * @ClassName LinkInDTO
 * @Description
 * @Author
 * @Date 2019/9/26 14:15
 * @Version V1.0
 **/
@Data
@ApiModel("数据库连接模块")
public class LinkInDTO {

    
    @ApiModelProperty(value = "数据源链接：ip:port，必填", required = true, example = "172.20.4.235:3306")
    @NotNull(message = "数据库链接不能为空")
    private String datasourceUrl;
    
    @ApiModelProperty(value = "数据源数据库，库名，必填", required = true, example = "framework-generator")
    @NotNull(message = "数据库库名不能为空")
    private String datasourceSchemaName;
    
    @ApiModelProperty(value = "数据库登录用户名，必填", required = true, example = "root")
    @NotNull(message = "数据库登录用户名")
    private String datasourceUsername;
    
    @ApiModelProperty(value = "数据库登录用户密码，必填", required = true, example = "test")
    @NotNull(message = "数据库登录用户密码不能为空")
    private String datasourcePassword;
    
    /**
     * 设置开始表格
     */
    @ApiModelProperty(value = "设置开始表格,即从第几个表格开始校验，默认为5", example = "5")
    private Integer tableNum ;
}