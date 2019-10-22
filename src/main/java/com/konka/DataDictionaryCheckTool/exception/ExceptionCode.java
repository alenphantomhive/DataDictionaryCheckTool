package com.konka.DataDictionaryCheckTool.exception;

/**
 * 全局异常码
 *
 * @author framework-generator
 * @version 1.0.0
 * @date 2018-07-18
 */
public interface ExceptionCode {

    /**
     * 系统异常码
     *
     * 其他模块，同样以interface声明常量，便于swagger上的直接引用
     *
     * 系统异常码以 100001开始，前两位表示项目id，中间两位，表示模块id，后两位位表示异常码
     *
     */
    interface System {

        // 成功
        int SUCCESS_CODE = 0;
        String SUCCESS_MSG = "success";

        int SYSTEM_ERROR_CODE = 200001;
        String SYSTEM_ERROR_MSG = "系统错误";

        int PARAM_TYPE_ERROR_CODE = 200002;
        String PARAM_TYPE_ERROR_MSG = "参数类型错误";

    }
    
    /**
     * 项目模块
     */
    interface Project {
        
        int ILLEGAL_PARAM_CODE = 9111001;
        String ILLEGAL_PARAM_MSG = "参数不合法";
        
        int FRAMEWORK_GENERATE_ERROR_CODE = 9111002;
        String FRAMEWORK_GENERATE_ERROR_MSG = "查询错误：";
        
    }
    
    

    
    
    /**
     * 登陆模块
     */
    interface Login {
        
        int ILLEGAL_PARAM_CODE = 9113001;
        String ILLEGAL_PARAM_MSG = "参数不合法";
        
        int LOGIN_FAULT_CODE = 9113002;
        String LOGIN_FAULT_MSG = "登陆失败";
        
        
    }
    
    /**
     * 数据字典
     */
    interface DataDictionaryCheckTool {
        
        int ILLEGAL_PARAM_CODE = 9118001;
        String ILLEGAL_PARAM_MSG = "数据参数不合法";
        
        int SAVE_ERROR_CODE = 9118002;
        String SAVE_ERROR_MSG = "创建数据失败";
        
        int UPDATE_ERROR_CODE = 9118003;
        String UPDATE_ERROR_MSG = "更新数据失败";
        
        int SERVER_NOT_FOUND_CODE = 9118004;
        String SERVER_NOT_FOUND_MSG = "获取数据信息失败";
        
        int DELETE_ERROR_CODE = 9118005;
        String DELETE_ERROR_MSG = "删除数据失败";
        
        int FILE_ERROR_CODE = 9118006;
        String FILE_ERROR_MSG = "文件格式不对!请传后缀为“docx”的文件";
    
        int TABLE_ERROR_CODE = 9118007;
        String TABLE_ERROR_MSG = "表格格式不对!请查看表格格式是否与模板相同";
        
        
    }
}
