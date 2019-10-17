package com.konka.DataDictionaryCheckTool.service.impl;

import com.konka.DataDictionaryCheckTool.exception.BusinessException;
import com.konka.DataDictionaryCheckTool.exception.ExceptionCode;
import com.konka.DataDictionaryCheckTool.mapper.DataDictionaryCheckToolMapper;
import com.konka.DataDictionaryCheckTool.model.po.DataDictionaryCheckTool;
import com.konka.DataDictionaryCheckTool.model.po.ErrList;
import com.konka.DataDictionaryCheckTool.model.request.LinkInDTO;
import com.konka.DataDictionaryCheckTool.properties.VariableProperties;
import com.konka.DataDictionaryCheckTool.service.DataDictionaryCheckToolService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName DataDictionaryCheckToolServiceImpl
 * @Description
 * @Author
 * @Date 2019/9/19 14:51
 * @Version V1.0
 **/
@Service
@Slf4j
public class DataDictionaryCheckToolServiceImpl implements DataDictionaryCheckToolService {
    

    @Autowired
    private DataDictionaryCheckToolMapper DataDictionaryCheckToolMapper;
    
    /**
     * 可变配置
     */
    @Autowired
    private VariableProperties variableProperties;
    
    
    /**
     *
     * @param file Word文件
     * @return 导入操作成功返回信息
     */
    @Override
    public List<ErrList> importFile(LinkInDTO link,MultipartFile file) {
        List<ErrList> errList = new ArrayList<>();
        String originalFilename = file.getOriginalFilename();
        log.info("fileName = {}", originalFilename);
        String fileType = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());
        
        //校验文件后缀名
        if(fileType.equals(variableProperties.getImportDataProperties().getFileType())){
            try {
                //处理docx格式 即office2007以后版本
                XWPFDocument docx =new XWPFDocument(file.getInputStream());
                Iterator<XWPFTable> it = docx.getTablesIterator();
                // 设置需要读取的表格  set是设置需要读取的第几个表格
                int set = variableProperties.getImportDataProperties().getTableNum();
                if(link.getTableNum()!=null) {
                    set=link.getTableNum();}
                int num =set;
                // 过滤前面不需要的表格
                    for (int i = 0; i < set-1; i++) {
                    it.hasNext();
                    it.next();
                    }
                if(!it.hasNext()){
                    throw new BusinessException(ExceptionCode.DataDictionaryCheckTool.SERVER_NOT_FOUND_CODE, ExceptionCode.DataDictionaryCheckTool.SERVER_NOT_FOUND_MSG);
                }
                    //遍历所有表格
                    while(it.hasNext()){
                        XWPFTable table=it.next();
                        log.info("导入第几个表格 = {}",num);
                        List<DataDictionaryCheckTool> dataList = new ArrayList<>();
                        
                        //获取的行数
                        int rowSize = table.getNumberOfRows() ;
                        log.info("导入数据总行数 = {}",rowSize-4);
                        //获取数据库名字
                        if(rowSize<4){
                            throw new BusinessException(ExceptionCode.DataDictionaryCheckTool.TABLE_ERROR_CODE, ExceptionCode.DataDictionaryCheckTool.TABLE_ERROR_MSG);
                        }
                        
                        String tableName = table.getRow(rowSize - 4).getTableCells().get(variableProperties.getImportDataProperties().getTableNameCellNum()).getText().trim();
                        log.info("tableName = {}",tableName);
                        //获取数据库数据字典
                        DataDictionaryCheckTool datas = new DataDictionaryCheckTool();
                        datas.setTableName(tableName);
                        datas.setSchemaName(link.getDatasourceSchemaName());
                        List<DataDictionaryCheckTool> baseList = DataDictionaryCheckToolMapper.getByTable(datas);
                        if(baseList.isEmpty()){
                            log.info("查询数据字典失败，数据库表名 = {}",tableName);
                            ErrList errlist = new ErrList();
                            errlist.setTableId(num);
                            errlist.setRowId(rowSize-4);
                            errlist.setTableName(tableName);
                            errlist.setMsg("数据库表名错误");
                            errList.add(errlist);
                            //* put("/n"+"第几个表"+"  "+num+"第几行"+(rowSize-4)+"  ","数据库表名错误"+tableName+"/n");
                            }
                        else {
                        log.info("baseList = {}",baseList);
                        //遍历所有数据行,第0行为标题行，后4行为数据库属性，略过
                        for (int j = 1; j < rowSize-4; j++) {
                            XWPFTableRow row = table.getRow(j);
                            //略过空行
                           if (row == null) {
                                continue;
                            }
                            //读取每一列数据
                            List<XWPFTableCell> cells = row.getTableCells();
                            DataDictionaryCheckTool data = new DataDictionaryCheckTool();
                            data.setTableName(tableName);
                            String name = cells.get(variableProperties.getImportDataProperties().getNameCellNum()).getText().trim();
                            log.info("name = {}",name);
                            data.setName(name);
                            data.setSchemaName(link.getDatasourceSchemaName());
                            DataDictionaryCheckTool base = DataDictionaryCheckToolMapper.getByColumn(data);
                            if(base==null){
                                log.info("查询数据字典数据库成功，数据库表名 = {}",tableName);
                                log.info("查询数据字典数据库字段名失败，数据库字段名 = {}",name);
                                ErrList errlist = new ErrList();
                                errlist.setTableId(num);
                                errlist.setRowId(j);
                                errlist.setTableName(tableName);
                                errlist.setColumnName(name);
                                errlist.setMsg("数据库字段名错误");
                                errList.add(errlist);
                                log.info("errList = {}",errList);
                               // put("/n"+"第几个表"+num+"第几行"+j,"数据库字段名错误"+"数据库表名"+tableName+"数据库字段名"+name+"/n");
                            }
                            log.info("base = {}",base);
                            String type = cells.get(variableProperties.getImportDataProperties().getTypeCellNum()).getText().trim();
                            log.info("type = {}",type);
                            data.setType(type);
                            if(base!=null&&base.getType().equals(type)){
                                log.info("查询数据字典字段类型不匹配，数据库字段名 = {}",name);
                                ErrList errlist = new ErrList();
                                errlist.setTableId(num);
                                errlist.setRowId(j);
                                errlist.setTableName(tableName);
                                errlist.setColumnName(name);
                                errlist.setColumnType(base.getType());
                                errlist.setMsg("数据库字段类型错误");
                                errList.add(errlist);
                                log.info("errList = {}",errList);
                                //put("/n"+"第几个表"+"  "+num+"第几行"+j+"  ","数据库字段类型错误"+"  "+"数据库表名"+tableName+"数据库字段名"+"  "+name+"字段类型"+type+"/n");
                            }
                            String nullAble =cells.get(variableProperties.getImportDataProperties().getNullAbleCellNum()).getText().trim();
                            log.info("nullAble = {}",nullAble);
                            data.setNullAble(nullAble);
                            if(base!=null&&"YES".equals(base.getNullAble())&&"是".equals(nullAble)){
                                log.info("数据库字段名可为空 = {}",nullAble);
                            }
                                    else if(base!=null&&"NO".equals(base.getNullAble())&&"否".equals(nullAble)){
                                log.info("数据库字段可为空 = {}",nullAble);
                            }
                                        else if (base!=null){
                                log.info("数据库字段名可为空 = {}",nullAble);
                                ErrList errlist = new ErrList();
                                errlist.setTableId(num);
                                errlist.setRowId(j);
                                errlist.setTableName(tableName);
                                errlist.setColumnName(name);
                                errlist.setNullAble(base.getNullAble());
                                errlist.setMsg("数据库字段是否可为空错误");
                                errList.add(errlist);
                                log.info("errList = {}",errList);
                                //put("/n"+"第几个表"+"  "+num+"第几行"+j+"  ","数据库字段是否可为空错误"+"  "+"数据库表名"+tableName+"数据库字段名"+""+name+"字段可为空"+nullAble+"/n");
                            }
                            log.info("当前获取的数据字典信息 = {}",data);
                            dataList.add(data);
                        }
                        }
                        log.info("dataList = {}",dataList);
                        log.info("errList = {}",errList);
                         num ++;
                        }
            } catch (IOException e) {
                e.printStackTrace();
                throw new BusinessException(ExceptionCode.DataDictionaryCheckTool.TABLE_ERROR_CODE, ExceptionCode.DataDictionaryCheckTool.TABLE_ERROR_MSG);
            }
        }
        else{
            throw new BusinessException(ExceptionCode.DataDictionaryCheckTool.FILE_ERROR_CODE, ExceptionCode.DataDictionaryCheckTool.FILE_ERROR_MSG);
            // "文件格式不对!请传后缀为“docx”的文件";
        }
        return errList;
    }
    
    
@Override
    public List<ErrList> link(LinkInDTO link, MultipartFile file) {
    // 链接数据库，获取表名列表
    String driver = "com.mysql.jdbc.Driver";
    
    String url = "jdbc:mysql://" + link.getDatasourceUrl() + "/" + link.getDatasourceSchemaName();
    
    String username =link.getDatasourceUsername();
    
    String password =link.getDatasourcePassword();
    
    String sql = "show tables;";
    
    ArrayList<String> tableNameList = new ArrayList<>();
    
    try {
        Class.forName(driver);
    } catch (ClassNotFoundException e) {
        throw new BusinessException(ExceptionCode.Project.FRAMEWORK_GENERATE_ERROR_CODE, ExceptionCode.Project.FRAMEWORK_GENERATE_ERROR_MSG + "加载驱动类错误");
    }
    
    try (Connection connection = DriverManager.getConnection(url, username, password);
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         ResultSet resultSet = preparedStatement.executeQuery()){
        
        while (resultSet.next()) {
            
            tableNameList.add(resultSet.getString(1));
            
        }
        
    } catch (Exception e) {
        
        log.error("数据库连接失败", e);
        throw new BusinessException(ExceptionCode.Project.FRAMEWORK_GENERATE_ERROR_CODE, ExceptionCode.Project.FRAMEWORK_GENERATE_ERROR_MSG + "数据库连接失败");
        
    }
    if(file==null){
        log.error("文件为空");
        throw new BusinessException(ExceptionCode.Project.FRAMEWORK_GENERATE_ERROR_CODE, ExceptionCode.Project.FRAMEWORK_GENERATE_ERROR_MSG + "数据库连接成功，但未提交文件");
    }
    
    return this.importFile(link,file);
}
}
