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

    private Integer page;
    private Integer pagesize;

	public Integer getPage()
	{
		return page;
	}

	public void setPage(Integer page)
	{
		this.page = page;
	}

	public Integer getPagesize()
	{
		return pagesize;
	}

	public void setPagesize(Integer pagesize)
	{
		this.pagesize = pagesize;
	}    

    <#list table.notPkColumns as column>
    private ${column.javaType} ${column.columnNameLower};
    </#list>

    <#list table.notPkColumns as column>
    public ${column.javaType} get${column.columnName}() 
    {
        return ${column.columnNameLower};
    }
    </#list>
}
