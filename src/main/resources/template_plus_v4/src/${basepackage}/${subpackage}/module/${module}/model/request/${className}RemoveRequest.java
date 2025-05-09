<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.request;

import java.io.Serializable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.security.core.SpringSecurityCoreVersion;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "${className}RemoveRequest 删除入参")
public class ${className}RemoveRequest implements Serializable
{
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

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