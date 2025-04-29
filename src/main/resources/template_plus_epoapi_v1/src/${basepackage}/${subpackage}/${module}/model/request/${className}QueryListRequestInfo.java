<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign classNameLowerCase = table.classNameLowerCase>  
<#assign classNameFirstLower = table.classNameFirstLower>  
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.request;

import java.util.Arrays;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.SpringSecurityCoreVersion;

import com.base.http.query.EpoRequestRequestInfo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "${className}QueryListRequestInfo 列表入参")
public class ${className}QueryListRequestInfo extends EpoRequestRequestInfo
{
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

<#list table.notPkColumns as column>
    // ${column.columnAlias!}       db_column: ${column.sqlName} 
	@Schema(title = "${column.columnAlias!}", example = "")
	private ${column.javaType} ${column.columnNameLower};
</#list>
	
<#if table.pkCount gte 1>
<#list table.compositeIdColumns as column>
	// ${column.columnAlias!}       db_column: ${column.sqlName} 
	@Schema(title = "${column.columnAlias!}Array")
	private String[] ${column.columnNameLower}Array;
</#list>
</#if>	
	
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

}
