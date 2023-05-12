<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign classNameLowerCase = table.classNameLowerCase>  
<#assign classNameFirstLower = table.classNameFirstLower>  
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.param;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ${className}GetListParam implements Serializable
{
	private static final long serialVersionUID = 1L;

	// ********************************************************************************************
	// 1.默认参数
	// ********************************************************************************************

	private java.lang.String[] dynamicColumns;
	
	private List<Map<String, Object>> dynamicOrder;
	
	private DynamicWhere dynamicWhere;

	private int page;

	private int pagesize;

	// ********************************************************************************************
	// 2.表单参数
	// ********************************************************************************************
	
	public static class DynamicWhere implements Serializable
	{
		private static final long serialVersionUID = 1L;
		
<#list table.notPkColumns as column>
	    // ${column.columnAlias!}       db_column: ${column.sqlName} 
		private ${column.javaType} ${column.columnNameLower};
</#list>
		
<#if table.pkCount gte 1>
<#list table.compositeIdColumns as column>
		// ${column.columnAlias!}       db_column: ${column.sqlName} 
		private String[] ${column.columnNameLower}Array;
</#list>
</#if>	
		
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

		@Override
		public String toString()
		{
			return "DynamicWhere [" + 
<#list table.notPkColumns as column>
<#if column_index = 0>
					"${column.columnNameLower}=" + ${column.columnNameLower}
<#else>
					+ ", ${column.columnNameLower}=" + ${column.columnNameLower}
</#if>
</#list>
<#list table.compositeIdColumns as column>
					+ ", ${column.columnNameLower}Array=" + Arrays.toString(${column.columnNameLower}Array)
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
	
	public List<Map<String, Object>> getDynamicOrder()
	{
		return dynamicOrder;
	}
	
	public void setDynamicOrder(List<Map<String, Object>> dynamicOrder)
	{
		this.dynamicOrder = dynamicOrder;
	}
	
	public DynamicWhere getDynamicWhere()
	{
		return dynamicWhere;
	}

	public void setDynamicWhere(DynamicWhere dynamicWhere)
	{
		this.dynamicWhere = dynamicWhere;
	}
	
	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public int getPagesize()
	{
		return pagesize;
	}

	public void setPagesize(int pagesize)
	{
		this.pagesize = pagesize;
	}

	@Override
	public String toString()
	{
		return "${className}GetListParam [dynamicColumns=" + Arrays.toString(dynamicColumns) + ", dynamicOrder="
				+ dynamicOrder + ", dynamicWhere=" + dynamicWhere.toString() + ", page=" + page + ", pagesize=" + pagesize + "]";
	}
}
