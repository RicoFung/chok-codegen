<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "${className}UpdDTO 修改入参")
public class ${className}UpdDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	<#if table.pkCount gte 1>
	<#list table.compositeIdColumns as column>
	// ${column.columnAlias!}       db_column: ${column.sqlName} 
	@ApiModelProperty(value = "${column.columnAlias!}", example = "\"\"", position = ${column_index})
	@NotNull(message = "${column.columnNameLower}(${column.columnAlias}) 不能为空！")
	private ${column.javaType} ${column.columnNameLower};
	</#list>
	</#if>
	
    <#list table.notPkColumns as column>
    // ${column.columnAlias!}       db_column: ${column.sqlName} 
	@ApiModelProperty(value = "${column.columnAlias!}", example = "\"\"", position = ${column_index+1})
	//	@NotNull(message = "${column.columnNameLower}(${column.columnAlias}) 不能为空！")
	private ${column.javaType} ${column.columnNameLower};
    </#list>

<@generateJavaColumns/>
}

<#macro generateJavaColumns>
	<#list table.columns as column>
		<#if column.isDateTimeColumn>
	public String get${column.columnName}String() 
	{
		return DateUtil.format(get${column.columnName}(), "yyyy-MM-dd HH:mm:ss");
	}
	public void set${column.columnName}String(String value) 
	{
		set${column.columnName}(DateConvertUtils.parse(value, FORMAT_${column.constantName},${column.javaType}.class));
	}
	
		</#if>	
	public void set${column.columnName}(${column.javaType} value) 
	{
		this.${column.columnNameLower} = value;
	}
	
	public ${column.javaType} get${column.columnName}() 
	{
		return this.${column.columnNameLower};
	}
	</#list>
</#macro>
