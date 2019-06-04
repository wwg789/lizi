package cn.lizi.lizi.code;


import cn.lizi.lizi.utils.DateUtils;
import cn.lizi.lizi.model.code.ColumnClass;
import cn.lizi.lizi.model.code.FreeMarkerTemplateUtils;
import cn.lizi.lizi.model.code.TemplateGenModel;
import freemarker.template.Template;
import org.springframework.util.StringUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

public class CodeGenerateTest {

    private static final String URL = "jdbc:mysql://rm-2ze77uf6sbwdcxe917o.mysql.rds.aliyuncs.com:3306/carhailing";
    private static final String USER = "carhailing";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String PASSWORD = "ola_carhailing_2018";
    
    
    public String tableName = "";
    public String changeTableName = "";
    public String AUTHOR = "";
    public String CURRENT_DATE = "";
    public String packageName = "";
    public String tableAnnotation = "";
    public String businessBasePackage = "";

    public static void main(String[] args) {
        try {
            codeGenrate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void codeGenrate() throws Exception {

        String tableName = "olayc_pay_info";
        String basePath = "C:/Users/OLAYC_PC/Desktop/code";

        //Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
        ResultSet allResultSet = databaseMetaData.getColumns(null, "%", tableName, "%");

        String finaldPath = basePath + "olaycactivityinfo";
        String finalChangeTN = replaceUnderLineAndUpperCase(tableName);

        String idType = getIdType(allResultSet);
        if (StringUtils.isEmpty(finaldPath) || StringUtils.isEmpty(finalChangeTN)) {
            throw new IllegalArgumentException("参数错误");
        }

        //生成代码
        TemplateGenModel templateGenModel = generateEntityFile(resultSet, finaldPath, finalChangeTN);
        generateFileByTemplate(templateGenModel, idType);
    }

        private static String getIdType(ResultSet resultSet) throws Exception {
            String idType = "Integer";
            while (resultSet.next()) {
                if (resultSet.getString("COLUMN_NAME").equals("id")) {
                    if (resultSet.getString("TYPE_NAME").toLowerCase().equals("bigint")) {
                        idType = "Long";
                    }
                }
            }
            return idType;
        }

    /**
     * 根据数据库对象创建java实体类对象
     *
     * @param resultSet 查询数据库返回对象
     * @throws Exception 抛出异常
     */
    public static TemplateGenModel generateEntityFile(ResultSet resultSet, String diskPath, String changeTableName) throws Exception {

        final String suffix = ".java";
        final String pagePath = diskPath + File.separator + "entity" + File.separator;
        File pageFile = new File(pagePath);
        if (!pageFile.exists()) {
            pageFile.mkdirs();
        }
        final String path = diskPath + File.separator + "entity" + File.separator + changeTableName + suffix;
        final String templateName = "Entity1.ftl";
        File mapperFile = new File(path);
        List<ColumnClass> columnClassList = new ArrayList<>();
        ColumnClass columnClass = null;
        while (resultSet.next()) {
            columnClass = new ColumnClass();
            String length = resultSet.getString("CHAR_OCTET_LENGTH");
            //获取字段名称
            columnClass.setColumnName(resultSet.getString("COLUMN_NAME"));
            //获取字段类型
            columnClass.setColumnType(resultSet.getString("TYPE_NAME"));
            //转换字段名称，如 sys_name 变成 SysName
            columnClass.setChangeColumnName(replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME")));
            //字段在数据库的注释
            columnClass.setColumnComment(resultSet.getString("REMARKS"));
            if (length != null) {
                columnClass.setCharOctetLength(length);
            }
            columnClassList.add(columnClass);
        }
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("model_column", columnClassList);
        return new TemplateGenModel(templateName, mapperFile, dataMap);
    }



    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String replaceUnderLineAndUpperCase(String str) {
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        int count = sb.indexOf("_");
        while (count != 0) {
            int num = sb.indexOf("_", count);
            count = num + 1;
            if (num != -1) {
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count, count + 1, ia + "");
            }
        }
        String result = sb.toString().replaceAll("_", "");
        return StringUtils.capitalize(result);
    }

    //获取文件
    private static void generateFileByTemplate(TemplateGenModel genModel, String idType) throws Exception {
        Template template = FreeMarkerTemplateUtils.getTemplate(genModel.getTemplateName());
        FileOutputStream fos = new FileOutputStream(genModel.getMapperFile());
        genModel.getDataMap().put("table_name_small", "olayc_model");
        genModel.getDataMap().put("table_name", "olaycModel");
        genModel.getDataMap().put("author", "123");
        genModel.getDataMap().put("date", DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:dd"));
        genModel.getDataMap().put("package_name", "olayc_mis");
        genModel.getDataMap().put("table_annotation", "333333");
        genModel.getDataMap().put("business_package_name", "4444444");
        genModel.getDataMap().put("id_type", idType);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(genModel.getDataMap(), out);
    }


}
