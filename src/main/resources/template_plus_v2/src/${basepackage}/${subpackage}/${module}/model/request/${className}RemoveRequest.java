<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.request;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "${className}RemoveRequest 删除入参")
public class ${className}RemoveRequest implements Serializable
{
	private static final long serialVersionUID = 1L;

<#if table.pkCount gte 1>
<#list table.compositeIdColumns as column>
	@NotNull(message = "${column.columnAlias}Array必传！")
    @Size(min = 1, message = "最少选一条记录！")
	private String[] ${column.columnNameLower}Array;
</#list>
</#if>

<@generateJavaColumns/>
}

<#macro generateJavaColumns>
<#list table.compositeIdColumns as column>
	public String[] get${column.columnName}Array() 
	{
		return this.${column.columnNameLower}Array;
	}
	public void set${column.columnName}Array(String[] value) 
	{
		this.${column.columnNameLower}Array = value;
	}
</#list>
</#macro>