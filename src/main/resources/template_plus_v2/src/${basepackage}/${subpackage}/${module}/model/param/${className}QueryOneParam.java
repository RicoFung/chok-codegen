<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.param;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.security.core.SpringSecurityCoreVersion;

public class ${className}QueryOneParam implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private java.lang.String[] dynamicColumns;

	private DynamicWhere dynamicWhere;

	// ********************************************************************************************
	// 2.表单参数
	// ********************************************************************************************
	
	public static class DynamicWhere implements Serializable
	{
		private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
		
<#list table.columns as column>
	    // ${column.columnAlias!}       db_column: ${column.sqlName} 
		private ${column.javaType} ${column.columnNameLower};
</#list>
		
<#list table.columns as column>
		public ${column.javaType} get${column.columnName}() 
		{
			return this.${column.columnNameLower};
		}
		public void set${column.columnName}(${column.javaType} value) 
		{
			this.${column.columnNameLower} = value;
		}
</#list>
	
		@Override
		public String toString()
		{
			return "DynamicWhere [" + 
<#list table.columns as column>
<#if column_index = 0>
					"${column.columnNameLower}=" + ${column.columnNameLower}
<#else>
					+ ", ${column.columnNameLower}=" + ${column.columnNameLower}
</#if>
</#list>
					+ "]";
		}
	}
	
	public String[] getDynamicColumns()
	{
		return dynamicColumns;
	}
	
	public void setDynamicColumns(String[] dynamicColumns)
	{
		this.dynamicColumns = dynamicColumns;
	}
	
	public DynamicWhere getDynamicWhere()
	{
		return dynamicWhere;
	}

	public void setDynamicWhere(DynamicWhere dynamicWhere)
	{
		this.dynamicWhere = dynamicWhere;
	}
	
	@Override
	public String toString()
	{
		return "${className}QueryOneParam [dynamicColumns=" + Arrays.toString(dynamicColumns) + ", dynamicWhere=" + dynamicWhere.toString() + "]";
	}
}
