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
import ${basepackage}.${subpkg}.${module}.model.request.${className}CreateRequestInfo;
import ${basepackage}.${subpkg}.${module}.model.request.${className}ModifyRequest;

@Mapper
public interface ModelMapper
{
	ModelMapper INSTANCE = Mappers.getMapper( ModelMapper.class );
	
<#if table.pkCount gte 1>
<#list table.compositeIdColumns as column>
	@Mapping(target = "${column.columnNameLower}", ignore = true)
</#list>
</#if>
	${className}CreateParam map(${className}CreateRequestInfo requestInfo);
	
	${className}ModifyParam map(${className}ModifyRequest request);
	
}
