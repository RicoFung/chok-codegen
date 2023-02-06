<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.data;

import ${basepackage}.${subpkg}.${module}.model.entity.${className}Entity;

public class ${className}GetListData extends ${className}Entity
{
	private static final long serialVersionUID = 1L;
}
