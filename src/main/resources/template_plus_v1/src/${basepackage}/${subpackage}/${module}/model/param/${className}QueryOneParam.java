<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.param;

import java.io.Serializable;
import java.util.Arrays;

public class ${className}QueryOneParam implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private java.lang.String[] dynamicColumns;

<#if table.pkCount gte 1>
<#list table.compositeIdColumns as column>
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
		return "${className}QueryOneParam [dynamicColumns=" + Arrays.toString(dynamicColumns) + <#list table.compositeIdColumns as column>", ${column.columnNameLower}=" + ${column.columnNameLower}</#list> + "]";
	}
}
