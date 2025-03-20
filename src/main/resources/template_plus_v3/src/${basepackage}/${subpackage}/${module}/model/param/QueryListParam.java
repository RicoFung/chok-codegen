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
import org.springframework.security.core.SpringSecurityCoreVersion;

public class QueryListParam implements Serializable 
{
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private DynamicWhere dynamicWhere = new DynamicWhere(); // 固定实例化，方便链式调用
    private String[] dynamicColumns;
    private List<Map<String, Object>> dynamicOrder;
    private Integer page;
    private Integer pagesize;

    // 1. 链式 by 查询条件
    <#list table.notPkColumns as column>
    public QueryListParam by${column.columnName}(${column.javaType} ${column.columnNameLower}) 
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

	public QueryListParam setDynamicWhere(DynamicWhere dynamicWhere)
	{
		this.dynamicWhere = dynamicWhere;
        return this;
	}
	
	public String[] getDynamicColumns()
	{
		return dynamicColumns;
	}
	
	public QueryListParam setDynamicColumns(String[] dynamicColumns)
	{
		this.dynamicColumns = dynamicColumns;
        return this;
	}
	
	public List<Map<String, Object>> getDynamicOrder()
	{
		return dynamicOrder;
	}
	
	public QueryListParam setDynamicOrder(List<Map<String, Object>> dynamicOrder)
	{
		this.dynamicOrder = dynamicOrder;
        return this;
	}
	
	public Integer getPage()
	{
		return page;
	}

	public QueryListParam setPage(Integer page)
	{
		this.page = page;
        return this;
	}

	public Integer getPagesize()
	{
		return pagesize;
	}

	public QueryListParam setPagesize(Integer pagesize)
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

        <#list table.notPkColumns as column>
        public ${column.javaType} get${column.columnName}() 
        {
            return ${column.columnNameLower};
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
        return "${className}.QueryListParam [dynamicColumns=" + Arrays.toString(dynamicColumns) + 
               ", dynamicOrder=" + dynamicOrder + 
               ", dynamicWhere=" + dynamicWhere.toString() + 
               ", page=" + page + ", pagesize=" + pagesize + "]";
    }
}
