<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.request;

import java.io.Serializable;
import java.util.Arrays;
import javax.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "${className}QueryOneRequest 详情入参")
public class ${className}QueryOneRequest implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Schema(title = "动态列", example = "[<#list table.columns as column>\"${column.columnNameLower}\"<#if column_has_next>,</#if></#list>]")
	private java.lang.String[] dynamicColumns;

<#if table.pkCount gte 1>
<#list table.compositeIdColumns as column>
	@Schema(title = "主键", example = "", required = true)
	@NotNull(message = "${column.columnAlias}不能为空！")
	private String ${column.columnNameLower};
</#list>
</#if>

	public String[] getDynamicColumns()
	{
		return dynamicColumns;
	}
	public void setDynamicColumns(String[] dynamicColumns)
	{
		this.dynamicColumns = dynamicColumns;
	}

<#list table.compositeIdColumns as column>
	public String get${column.columnName}() 
	{
		return this.${column.columnNameLower};
	}
	public void set${column.columnName}(String value) 
	{
		this.${column.columnNameLower} = value;
	}
</#list>	
	
	@Override
	public String toString()
	{
		return "${className}QueryOneRequest [dynamicColumns=" + Arrays.toString(dynamicColumns) + <#list table.compositeIdColumns as column>", ${column.columnNameLower}=" + ${column.columnNameLower}</#list> + "]";
	}
}
