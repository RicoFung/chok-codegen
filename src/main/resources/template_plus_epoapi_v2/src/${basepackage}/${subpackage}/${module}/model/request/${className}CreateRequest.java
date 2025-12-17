<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.request;

import javax.validation.Valid;

import org.springframework.security.core.SpringSecurityCoreVersion;

import com.base.http.processgenerics.EpoRequest;
import com.base.http.processgenerics.EpoRequestEsbInfo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "${className}CreateRequest 新增入参")
public class ${className}CreateRequest extends EpoRequest<${className}CreateRequestInfo>
{
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	@Valid
	private EpoRequestEsbInfo esbInfo;
	
	@Valid
	private ${className}CreateRequestInfo requestInfo;

	public EpoRequestEsbInfo getEsbInfo()
	{
		return esbInfo;
	}
	
	public void setEsbInfo(EpoRequestEsbInfo esbInfo)
	{
		super.setEsbInfo(esbInfo);
		this.esbInfo = esbInfo;
	}

	public ${className}CreateRequestInfo getRequestInfo()
	{
		return requestInfo;
	}
	
	public void setRequestInfo(${className}CreateRequestInfo requestInfo)
	{
		super.setRequestInfo(requestInfo);
		this.requestInfo = requestInfo;
	}
}
