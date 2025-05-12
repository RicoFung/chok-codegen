<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.repository.model.result;

import org.springframework.security.core.SpringSecurityCoreVersion;

import ${basepackage}.${subpkg}.repository.model.entity.${className};

public class ${className}Result extends ${className}
{
	private static final long serialVersionUID = 1L;
}
