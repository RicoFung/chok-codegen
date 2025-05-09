<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.security.core.SpringSecurityCoreVersion;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "${className}ModifyRequest 修改入参")
public class ${className}ModifyRequest implements Serializable
{
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

<#if table.pkCount gte 1>
<#list table.compositeIdColumns as column>
	// ${column.columnAlias!}       db_column: ${column.sqlName} 
	@Schema(title = "${column.columnAlias!}", example = "")
	@NotNull(message = "${column.columnAlias}必传！")
	@NotBlank(message = "${column.columnAlias}不能为空！")
	private String ${column.columnNameLower};
</#list>
</#if>
	
<#list table.notPkColumns as column>
    // ${column.columnAlias!}       db_column: ${column.sqlName} 
    @Schema(title = "${column.columnAlias!}", example = "")
	//	@NotNull(message = "${column.columnNameLower}(${column.columnAlias})必传！")
	private ${column.javaType} ${column.columnNameLower};
</#list>

<@generateJavaColumns/>
}

<#macro generateJavaColumns>
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
<#list table.notPkColumns as column>
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
