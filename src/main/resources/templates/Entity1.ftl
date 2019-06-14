package ${business_package_name}.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 描述：${table_annotation}模型
*
* @author w
* @date ${date}
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${table_name} {

<#if model_column?exists>
    <#list model_column as model>

    //${model.columnComment!}
    <#if (model.columnName?lower_case = 'id')>
    <#if (model.columnType?lower_case = 'bigint')>
    private Long ${model.changeColumnName?uncap_first};
    <#else >
    private Integer ${model.changeColumnName?uncap_first};
    </#if>
    <#else>
            <#if (model.columnType?lower_case = 'tinyint' || model.columnType?lower_case = 'bit')>
    private Integer ${model.changeColumnName?uncap_first};
            <#elseif (model.columnType?lower_case = 'smallint' || model.columnType?lower_case = 'mediumint' || model.columnType?lower_case = 'int' || model.columnType?lower_case = 'integer')>
    private Integer ${model.changeColumnName?uncap_first};
            <#elseif (model.columnType?lower_case = 'bigint')>
    private Long ${model.changeColumnName?uncap_first};
            <#elseif (model.columnType?lower_case = 'float')>
    private Float ${model.changeColumnName?uncap_first};
            <#elseif (model.columnType?lower_case = 'double')>
    private Double ${model.changeColumnName?uncap_first};
            <#elseif (model.columnType?lower_case = 'decimal')>
    private Decimal ${model.changeColumnName?uncap_first};
            <#elseif (model.columnType?lower_case = 'varchar' || model.columnType?lower_case = 'text')>
    private String ${model.changeColumnName?uncap_first};
            <#elseif model.columnType?lower_case = 'datetime' || model.columnType?lower_case = 'date' || model.columnType?lower_case = 'time' || model.columnType?lower_case = 'year' || model.columnType?lower_case = 'timestamp'>
    private Date ${model.changeColumnName?uncap_first};
            <#else>
            </#if>
        </#if>
    </#list>
</#if>
}