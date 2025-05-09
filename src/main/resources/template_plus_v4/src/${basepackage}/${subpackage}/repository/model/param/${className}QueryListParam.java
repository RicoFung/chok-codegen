<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign classNameLowerCase = table.classNameLowerCase>  
<#assign classNameFirstLower = table.classNameFirstLower>  
<#assign subpkg = subpackage?replace("/",".")>

package ${basepackage}.${subpkg}.repository.model.param;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.SpringSecurityCoreVersion;

public class ${className}QueryListParam implements Serializable 
{
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private DynamicWhere dynamicWhere = new DynamicWhere(); // 固定实例化，方便链式调用
    private String[] dynamicColumns;
    private List<Map<String, Object>> dynamicOrder;
    private Integer page;
    private Integer pagesize;

    // 1. 链式 by 查询条件
    <#list table.notPkColumns as column>
    public ${className}QueryListParam by${column.columnName}(${column.javaType} ${column.columnNameLower}) 
    {
        this.dynamicWhere.${column.columnNameLower} = ${column.columnNameLower};
        return this;
    }
    </#list>

    // 2. 链式 set 列、排序、分页
	public DynamicWhere getDynamicWhere()
	{
		return dynamicWhere;
	}

	public ${className}QueryListParam setDynamicWhere(DynamicWhere dynamicWhere)
	{
		this.dynamicWhere = dynamicWhere;
        return this;
	}
	
	public String[] getDynamicColumns()
	{
		return dynamicColumns;
	}
	
	public ${className}QueryListParam setDynamicColumns(String[] dynamicColumns)
	{
		this.dynamicColumns = dynamicColumns;
        return this;
	}
	
	public List<Map<String, Object>> getDynamicOrder()
	{
		return dynamicOrder;
	}
	
	public ${className}QueryListParam setDynamicOrder(List<Map<String, Object>> dynamicOrder)
	{
		this.dynamicOrder = dynamicOrder;
        return this;
	}
	
	public Integer getPage()
	{
		return page;
	}

	public ${className}QueryListParam setPage(Integer page)
	{
		this.page = page;
        return this;
	}

	public Integer getPagesize()
	{
		return pagesize;
	}

	public ${className}QueryListParam setPagesize(Integer pagesize)
	{
		this.pagesize = pagesize;
        return this;
	}    
    
    // 3. `DynamicWhere` 内部类，包含查询字段
    public static class DynamicWhere implements Serializable 
    {
        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        <#list table.notPkColumns as column>
        private ${column.javaType} ${column.columnNameLower};
        </#list>
		
		<#if table.pkCount gte 1>
		<#list table.compositeIdColumns as column>
		private String[] ${column.columnNameLower}Array;
		</#list>
		</#if>	

        <#list table.notPkColumns as column>
        public ${column.javaType} get${column.columnName}() 
        {
            return ${column.columnNameLower};
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
                <#if column_index == 0>
                "${column.columnNameLower}=" + ${column.columnNameLower}
                <#else>
                + ", ${column.columnNameLower}=" + ${column.columnNameLower}
                </#if>
            </#list>
            + "]";
        }
    }

    @Override
    public String toString() 
    {
        return "${className}QueryListParam [dynamicColumns=" + Arrays.toString(dynamicColumns) + 
               ", dynamicOrder=" + dynamicOrder + 
               ", dynamicWhere=" + dynamicWhere.toString() + 
               ", page=" + page + ", pagesize=" + pagesize + "]";
    }
}
