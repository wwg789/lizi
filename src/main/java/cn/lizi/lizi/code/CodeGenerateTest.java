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

    private static final String URL = "jdbc:mysql://localhost:3306/db_0?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "wuweigang";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    
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

        String tableName = "xt_forun_parant_info";
        String modelPath = "D:\\ACODE";
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");

        //生成代码
        ArrayList<ColumnClass> ziduan = new ArrayList<>();
        TemplateGenModel templateGenModel = generateEntityFile(resultSet,ziduan);
        generateFileByTemplate(templateGenModel, modelPath);
        //打印xml语句
        if(null != templateGenModel){
            prient(ziduan,tableName);
        }
    }

    private static void prient(ArrayList<ColumnClass> ziduan,String tableName) {
        //ResultMap
        createResultMap(ziduan);
        //新增
        createAdd(ziduan,tableName);
        //修改
        createUpdate(ziduan,tableName);
        //根据ID查询Model
        createQueryModel(ziduan,tableName);
        //根据所有字段查询 List
        creteQueryList(ziduan,tableName);
    }

    //根据所有字段查询 List
    private static void creteQueryList(ArrayList<ColumnClass> ziduan,String tableName) {
        System.out.print("<!-- ================================================== -->\n\n");
        StringBuilder sb = new StringBuilder();
        sb.append("    <select id=\"替换方法名称\" resultType=\"替换表名称\">\n");
        sb.append("    SELECT\n");
        sb.append("    <include refid=\"searchInfoSql\"/>\n");
        sb.append("    FROM "+ tableName +"\n");
        sb.append("    WHERE 1\n");
        sb.append("    <trim prefix=\"\" suffixOverrides=\"\">");
        ziduan.forEach(key ->{
            sb.append("     <if test=\""+ key.getChangeColumnName() +" != null\"> AND "+ key.getColumnName() +" = #{" + key.getChangeColumnName() + "}</if>\n");
        });
        sb.append("    </trim>\n");
        sb.append("    ORDER BY createTime\n");
        sb.append("    </select>\n");
        System.out.print(sb.toString());
    }

    //查询一个model
    private static void createQueryModel(ArrayList<ColumnClass> ziduan,String tableName) {
        System.out.print("<!-- ================================================== -->\n\n");
        StringBuilder sb = new StringBuilder();
        sb.append("    <select id=\"方法名称\" resultType=\"类名称\">\n");
        sb.append("    SELECT\n");
        sb.append("    <include refid=\"searchInfoSql\"/>\n");
        sb.append("    FROM "+ tableName +"\n");
        sb.append("    WHERE 1\n");
        sb.append("    <trim prefix=\"\" suffixOverrides=\"\">");
        ziduan.forEach(key ->{
            sb.append("     <if test=\""+ key.getChangeColumnName() +" != null\"> AND "+ key.getColumnName() +" = #{" + key.getChangeColumnName() + "}</if>\n");
        });
        sb.append("    </trim>\n");
        sb.append("    </select>\n");
        System.out.print(sb.toString());

    }

    private static void createUpdate(ArrayList<ColumnClass> ziduan,String tableName) {
        System.out.print("<!-- ================================================== -->\n\n");
        StringBuilder sb = new StringBuilder();
        sb.append("    <update id=\"updataInvoice\">\n");
        sb.append("    UPDATE\n");
        sb.append("    "+ tableName +"\n");
        sb.append("    <set>\n");
        sb.append("    <trim prefix=\"\" suffixOverrides=\",\">\n");
        ziduan.forEach(key ->{
            sb.append("     <if test=\""+ key.getChangeColumnName() +" != null\">"+key.getColumnName()+"=#{"+key.getChangeColumnName()+"}</if>\n");
        });
        sb.append("    </trim>\n");
        sb.append("    </set>\n");
        sb.append("    WHERE\n");
        sb.append("    id = #{id}\n");
        sb.append("    </update>\n");
        System.out.print(sb.toString());
    }

    private static void createAdd(ArrayList<ColumnClass> ziduan,String tableName) {
        System.out.print("<!-- ================================================== -->\n\n");
        StringBuilder sb = new StringBuilder();
        sb.append("    <insert id=\"方法名称\" keyProperty=\"id\" useGeneratedKeys=\"true\" parameterType=\"替换表名\" >\n");
        sb.append("    INSERT INTO "+ tableName +"\n");
        sb.append("    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n");
        ziduan.forEach(key ->{
            sb.append("     <if test=\""+ key.getChangeColumnName() +" != null\"> "+ key.getColumnName()+",</if>\n");
        });
        sb.append("    </trim>\n");
        sb.append("    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n");
        ziduan.forEach(key ->{
            sb.append("     <if test=\""+ key.getChangeColumnName() +" != null\"> #{"+ key.getChangeColumnName()+"},</if>\n");
        });
        sb.append("    </trim>\n");
        sb.append("    </insert>\n");
        System.out.print(sb.toString());

    }

    private static void createResultMap(ArrayList<ColumnClass> ziduan) {
        System.out.print("<!-- ================================================== -->\n\n");
        StringBuilder sb = new StringBuilder();
        sb.append("    <sql id=\"searchInfoSql\">\n");
        ziduan.forEach(key ->{
            sb.append("       "+ key.getColumnName() +" AS "+ key.getChangeColumnName() +",\n");
        });
        String substring = sb.substring(0, sb.length() - 2) + "\n    </sql>\n";
        System.out.print(substring);
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
    public static TemplateGenModel generateEntityFile(ResultSet resultSet,List<ColumnClass> ziduan) throws Exception {

        final String templateName = "Entity1.ftl";

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
            ziduan.add(columnClass);
        }
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("model_column", columnClassList);
        return new TemplateGenModel(templateName, dataMap);
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
        return sb.toString().replaceAll("_", "");
    }

    //获取文件
    private static void generateFileByTemplate(TemplateGenModel genModel, String modelPath) throws Exception {
        Template template = FreeMarkerTemplateUtils.getTemplate(genModel.getTemplateName());
        File pageFile = new File(modelPath);
        if (!pageFile.exists()) {
            pageFile.mkdirs();
        }
        File file = new File(pageFile, "Model.java");
        FileOutputStream fos = new FileOutputStream(file);
        genModel.getDataMap().put("table_name_small", "olayc_model");
        genModel.getDataMap().put("table_name", "olaycModel");
        genModel.getDataMap().put("author", "123");
        genModel.getDataMap().put("date", DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:dd"));
        genModel.getDataMap().put("package_name", "olayc_mis");
        genModel.getDataMap().put("table_annotation", "333333");
        genModel.getDataMap().put("business_package_name", "4444444");

        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(genModel.getDataMap(), out);
    }


}
