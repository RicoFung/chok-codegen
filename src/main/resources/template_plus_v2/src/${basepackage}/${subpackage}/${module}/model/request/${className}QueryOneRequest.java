<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.request;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.security.core.SpringSecurityCoreVersion;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "${className}QueryOneRequest 详情入参")
public class ${className}QueryOneRequest implements Serializable
{
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	
	@Schema(title = "动态列", example = "[<#list table.columns as column>\"${column.columnNameLower}\"<#if column_has_next>,</#if></#list>]")
	private java.lang.String[] dynamicColumns;
	
	private DynamicWhere dynamicWhere;

	// ********************************************************************************************
	// 2.表单参数
	// ********************************************************************************************
	@Schema(title = "动态过滤", name="DynamicWhere(${className}QueryOneRequest)", description="DynamicWhere(${className}QueryOneRequest)")
	public static class DynamicWhere implements Serializable
	{
		private static final long serialVersionUID = 1L;
		
<#list table.columns as column>
	    // ${column.columnAlias!}       db_column: ${column.sqlName} 
		@Schema(title = "${column.columnAlias!}", example = "")
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
		return "${className}QueryOneRequest [dynamicColumns=" + Arrays.toString(dynamicColumns) + ", dynamicWhere=" + dynamicWhere.toString() + "]";
	}
}
