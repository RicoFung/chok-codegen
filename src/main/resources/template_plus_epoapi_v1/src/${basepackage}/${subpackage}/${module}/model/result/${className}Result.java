<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.result;

import org.springframework.security.core.SpringSecurityCoreVersion;

import ${basepackage}.${subpkg}.${module}.model.entity.${className};

public class ${className}Result extends ${className}
{
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
}
