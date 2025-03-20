<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>

package ${basepackage}.${subpkg}.${module}.model.param;

import java.io.Serializable;
import java.util.Arrays;
import org.springframework.security.core.SpringSecurityCoreVersion;

import QueryListParam.DynamicWhere;

public class QueryOneParam implements Serializable 
{
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private DynamicWhere dynamicWhere = new DynamicWhere(); // 固定实例化，方便链式调用
    private String[] dynamicColumns;

    // 1. 链式 by 查询条件
    <#list table.columns as column>
    public QueryOneParam by${column.columnName}(${column.javaType} ${column.columnNameLower}) 
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

    // ✅ 3. `DynamicWhere` 内部类，包含查询字段
    public static class DynamicWhere implements Serializable 
    {
        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        <#list table.columns as column>
        private ${column.javaType} ${column.columnNameLower};
        </#list>

        <#list table.columns as column>
        public ${column.javaType} get${column.columnName}() 
        {
            return ${column.columnNameLower};
        }
        </#list>

        @Override
        public String toString() 
        {
            return "DynamicWhere [" +
            <#list table.columns as column>
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
        return "${className}.QueryOneParam [dynamicColumns=" + Arrays.toString(dynamicColumns) + 
               ", dynamicWhere=" + dynamicWhere.toString() + "]";
    }
}
