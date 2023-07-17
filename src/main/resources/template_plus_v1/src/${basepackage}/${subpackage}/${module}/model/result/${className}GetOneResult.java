<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.result;

import ${basepackage}.${subpkg}.${module}.model.entity.${className};

public class ${className}GetOneResult extends ${className}
{
	private static final long serialVersionUID = 1L;
}
