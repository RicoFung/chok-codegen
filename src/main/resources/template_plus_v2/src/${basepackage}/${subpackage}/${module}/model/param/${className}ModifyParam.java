<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.param;

import java.io.Serializable;
import org.springframework.security.core.SpringSecurityCoreVersion;
import ${basepackage}.${subpkg}.${module}.model.entity.${className};
/**
<#include "/java_description.include">
 */
public class ${className}ModifyParam extends ${className}
{
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
}