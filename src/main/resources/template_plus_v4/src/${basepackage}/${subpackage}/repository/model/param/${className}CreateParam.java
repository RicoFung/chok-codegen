<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.repository.model.param;

import java.io.Serializable;
import org.springframework.security.core.SpringSecurityCoreVersion;
import ${basepackage}.${subpkg}.repository.model.entity.${className};
/**
<#include "/java_description.include">
 */
public class ${className}CreateParam extends ${className}
{
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
}
