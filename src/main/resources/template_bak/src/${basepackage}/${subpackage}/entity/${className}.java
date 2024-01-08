<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.entity;

import java.io.Serializable;
/**
<#include "/java_description.include">
 */
public class ${className} implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	<#list table.columns as column>
    // ${column.columnAlias!}       db_column: ${column.sqlName} 
	private ${column.javaType} ${column.columnNameLower};
	</#list>

<@generateConstructor className/>
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


