<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.entity;

import java.io.Serializable;
/**
<#include "/java_description.include">
 */
public class ${className} implements Serializable
{
	private static final long serialVersionUID = 1L;

<#if table.pkCount gte 1>
<#list table.compositeIdColumns as column>
	// ${column.columnAlias!}       db_column: ${column.sqlName} 
	private String ${column.columnNameLower};
</#list>
</#if>

<#list table.notPkColumns as column>
    // ${column.columnAlias!}       db_column: ${column.sqlName} 
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
<#if column.isDateTimeColumn>
	public String get${column.columnName}String() 
	{
		return DateConvertUtils.format(get${column.columnName}(), FORMAT_${column.constantName});
	}
	public void set${column.columnName}String(String value) 
	{
		set${column.columnName}(DateConvertUtils.parse(value, FORMAT_${column.constantName},${column.javaType}.class));
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


