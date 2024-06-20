<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import ${basepackage}.${subpkg}.${module}.model.param.${className}CreateParam;
import ${basepackage}.${subpkg}.${module}.model.param.${className}ModifyParam;
import ${basepackage}.${subpkg}.${module}.model.param.${className}QueryListParam;
import ${basepackage}.${subpkg}.${module}.model.param.${className}QueryOneParam;
import ${basepackage}.${subpkg}.${module}.model.request.${className}CreateRequest;
import ${basepackage}.${subpkg}.${module}.model.request.${className}ModifyRequest;
import ${basepackage}.${subpkg}.${module}.model.request.${className}QueryListRequest;
import ${basepackage}.${subpkg}.${module}.model.request.${className}QueryOneRequest;

@Mapper
public interface ModelMapper
{
	ModelMapper INSTANCE = Mappers.getMapper( ModelMapper.class );
	
<#if table.pkCount gte 1>
<#list table.compositeIdColumns as column>
	@Mapping(target = "${column.columnNameLower}", ignore = true)
</#list>
</#if>
	${className}CreateParam requestToParam(${className}CreateRequest request);
	
	${className}ModifyParam requestToParam(${className}ModifyRequest request);
	
	${className}QueryOneParam requestToParam(${className}QueryOneRequest request);
	
	${className}QueryListParam requestToParam(${className}QueryListRequest request);
	
}
