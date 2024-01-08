<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.request;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.hutool.core.date.DateUtil;

@Schema(description = "${className}CreateRequest 新增入参")
public class ${className}CreateRequest implements Serializable
{
	private static final long serialVersionUID = 1L;

<#list table.notPkColumns as column>
    // ${column.columnAlias!}       db_column: ${column.sqlName} 
	@Schema(title = "${column.columnAlias!}", example = "")
	@NotNull(message = "${column.columnAlias}必传！")
	@NotBlank(message = "${column.columnAlias}不能为空！")
	private ${column.javaType} ${column.columnNameLower};
</#list>

<@generateJavaColumns/>
}

<#macro generateJavaColumns>
<#list table.notPkColumns as column>
<#if column.isDateTimeColumn>
	public String get${column.columnName}String() 
	{
		return DateUtil.format(get${column.columnName}(), "yyyy-MM-dd HH:mm:ss");
	}
	public void set${column.columnName}String(String value) 
	{
		set${column.columnName}(DateUtil.parse(value, "yyyy-MM-dd HH:mm:ss"));
	}
	</#if>	
	public ${column.javaType} get${column.columnName}() 
	{
		return this.${column.columnNameLower};
	}
	public void set${column.columnName}(${column.javaType} value) 
	{
		this.${column.columnNameLower} = value;
	}
</#list>
</#macro>