<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.module.${module}.model.request;

import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.security.core.SpringSecurityCoreVersion;

import io.swagger.v3.oas.annotations.media.Schema;

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