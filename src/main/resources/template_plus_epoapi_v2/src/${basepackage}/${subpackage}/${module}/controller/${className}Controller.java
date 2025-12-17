<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign classNameLowerCase = table.classNameLowerCase>  
<#assign classNameFirstLower = table.classNameFirstLower>  
<#assign subpkg = subpackage?replace("/",".")>
<#assign splitIndex = subpkg?index_of(".")>
<#assign prefix = subpkg?substring(splitIndex+1)>
package ${basepackage}.${subpkg}.${module}.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ${basepackage}.${subpkg}.${module}.model.ModelMapper;
import ${basepackage}.${subpkg}.${module}.model.request.${className}CreateRequest;
import ${basepackage}.${subpkg}.${module}.model.request.${className}CreateRequestInfo;
import ${basepackage}.${subpkg}.${module}.model.request.${className}ModifyRequest;
import ${basepackage}.${subpkg}.${module}.model.param.${className}CreateParam;
import ${basepackage}.${subpkg}.${module}.model.param.${className}ModifyParam;
import ${basepackage}.${subpkg}.${module}.service.${className}Service;

import com.base.controller.BaseEpoRestController;
import com.base.handler.ProcessGenericsHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Tag(name = "${apiGroup}-${className}")
@RestController(value = "${apiGroup}${className}Controller")
@RequestMapping("${apiGroupPath}/${classNameLowerCase}")
public class ${className}Controller extends BaseEpoRestController
{
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ${className}Service service;

	@Operation(summary = "新增", security = @SecurityRequirement(name = "basicAuth"))
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public com.base.http.processgenerics.EpoResponse<?> create(@RequestBody @Validated ${className}CreateRequest request) throws Exception
	{
		${className}CreateParam param = ModelMapper.INSTANCE.map(request.getRequestInfo());
		
		return executeProcessGenerics(request, new ProcessGenericsHandler()
		{
			@Override
			public com.base.http.processgenerics.EpoResponse<Object> execute(Object requestDto) throws Exception
			{
				return service.create(param);
			}
		});
	}

	@Operation(summary = "修改", security = @SecurityRequirement(name = "basicAuth"))
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public com.base.http.processgenerics.EpoResponse<?> modify(@RequestBody @Validated ${className}ModifyRequest request) throws Exception
	{
		${className}ModifyParam param = ModelMapper.INSTANCE.map(request);
		
		return executeProcessGenerics(request, new ProcessGenericsHandler()
		{
			@Override
			public com.base.http.processgenerics.EpoResponse<Object> execute(Object requestDto) throws Exception
			{
				return service.modify(param);
			}
		});
	}
}
