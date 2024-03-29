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

import ${basepackage}.${subpkg}.${module}.model.data.${className}GetListData;
import ${basepackage}.${subpkg}.${module}.model.data.${className}GetOneData;
import ${basepackage}.${subpkg}.${module}.model.entity.${className}Entity;
import ${basepackage}.${subpkg}.${module}.model.param.ParamMapper;
import ${basepackage}.${subpkg}.${module}.model.param.${className}AddParam;
import ${basepackage}.${subpkg}.${module}.model.param.${className}DelParam;
import ${basepackage}.${subpkg}.${module}.model.param.${className}GetListParam;
import ${basepackage}.${subpkg}.${module}.model.param.${className}GetOneParam;
import ${basepackage}.${subpkg}.${module}.model.param.${className}UpdParam;
import ${basepackage}.${subpkg}.${module}.model.query.${className}GetListQuery;
import ${basepackage}.${subpkg}.${module}.model.query.${className}GetOneQuery;
import ${basepackage}.${subpkg}.${module}.service.${className}Service;

import chok2.devwork.pojo.ChokDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "${apiGroup}-${className}")
@RestController(value = "${apiGroup}${className}Controller")
@RequestMapping("${apiGroupPath}/${classNameLowerCase}")
public class ${className}Controller
{
	// --------------------------------------------------------------------------------------- //
	// value: 指定请求的实际地址， 比如 /action/info之类
	// method： 指定请求的method类型， GET、POST、PUT、DELETE等
	// consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
	// produces: 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回
	// params： 指定request中必须包含某些参数值是，才让该方法处理
	// headers： 指定request中必须包含某些指定的header值，才能让该方法处理请求
	// --------------------------------------------------------------------------------------- //

	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ${className}Service service;

	@Operation(summary = "新增")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokDto<Object> add(@RequestBody @Validated ${className}AddParam param)
	{
		${className}Entity entity = ParamMapper.INSTANCE.paramToEntity(param);
		return service.add(entity);
	}

	@Operation(summary = "删除")
	@RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokDto<Object> del(@RequestBody @Validated ${className}DelParam param)
	{
		return service.del(param.getTcRowidArray());
	}

	@Operation(summary = "修改")
	@RequestMapping(value = "/upd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokDto<Object> upd(@RequestBody @Validated ${className}UpdParam param)
	{
		${className}Entity entity = ParamMapper.INSTANCE.paramToEntity(param);
		return service.upd(entity);
	}

	@Operation(summary = "明细")
	@RequestMapping(value = "/getOne", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokDto<${className}GetOneData> getOne(@RequestBody @Validated ${className}GetOneParam param)
	{
		${className}GetOneQuery query = ParamMapper.INSTANCE.paramToQuery(param);
		return service.getOne(query);
	}

	@Operation(summary = "列表")
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ChokDto<List<${className}GetListData>> getList(@RequestBody @Validated ${className}GetListParam param)
	{
		${className}GetListQuery query = ParamMapper.INSTANCE.paramToQuery(param);
		return service.getList(query);
	}
}
