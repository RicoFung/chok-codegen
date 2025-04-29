<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign classNameLowerCase = table.classNameLowerCase>  
<#assign classNameFirstLower = table.classNameFirstLower>  
<#assign subpkg = subpackage?replace("/",".")>
<#assign splitIndex = subpkg?index_of(".")>
<#assign prefix = subpkg?substring(splitIndex+1)>
package ${basepackage}.${subpkg}.${module}.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ${basepackage}.${subpkg}.${module}.model.ModelMapper;
import ${basepackage}.${subpkg}.${module}.model.result.${className}Result;
import ${basepackage}.${subpkg}.${module}.model.request.${className}QueryListRequest;
import ${basepackage}.${subpkg}.${module}.model.param.QueryListParam;
import ${basepackage}.${subpkg}.${module}.service.${className}Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.base.controller.BaseEpoRestController;
import com.base.handler.QueryHandler;
import com.base.http.query.EpoResponse;
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

	@SuppressWarnings("unchecked")
	@Operation(summary = "列表", security = @SecurityRequirement(name = "basicAuth"))
	@RequestMapping(value = "/queryList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public EpoResponse<List<${className}Result>> queryList(@RequestBody @Validated ${className}QueryListRequest request) throws JsonProcessingException
	{
		QueryListParam param = ModelMapper.INSTANCE.requestToParam(request.getRequestInfo());
		return (EpoResponse<List<${className}Result>>) executeQuery(request, new QueryHandler()
		{
			@Override
			public List<${className}Result> executeQueryList(Object request)
			{
				return service.queryList(param);
			}

			@Override
			public Integer executeQueryCount(Object request)
			{
				return service.queryCount(param);
			}
		});
	}
}
