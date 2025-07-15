<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.repository.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
<#include "/java_description.include">
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}




