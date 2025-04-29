<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign classNameLowerCase = table.classNameLowerCase>  
<#assign classNameFirstLower = table.classNameFirstLower>  
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.request;

import javax.validation.Valid;

import org.springframework.security.core.SpringSecurityCoreVersion;

import com.base.http.query.EpoRequest;
import com.base.http.query.EpoRequestEsbInfo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "${className}QueryListRequest 列表入参")
public class ${className}QueryListRequest extends EpoRequest
{
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	
	@Valid
	private EpoRequestEsbInfo esbInfo;
	
	@Valid
	private ${className}QueryListRequestInfo requestInfo;
	
	public EpoRequestEsbInfo getEsbInfo()
	{
		return esbInfo;
	}
	
	public void setEsbInfo(EpoRequestEsbInfo esbInfo)
	{
		super.setEsbInfo(esbInfo);
		this.esbInfo = esbInfo;
	}
	
	public ${className}QueryListRequestInfo getRequestInfo()
	{
		return requestInfo;
	}
	
	public void setRequestInfo(${className}QueryListRequestInfo requestInfo)
	{
		super.setRequestInfo(requestInfo);
		this.requestInfo = requestInfo;
	}

}
