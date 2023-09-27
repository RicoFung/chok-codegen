<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import ${basepackage}.${subpkg}.${module}.model.entity.${className};
import ${basepackage}.${subpkg}.${module}.model.param.${className}QueryListParam;
import ${basepackage}.${subpkg}.${module}.model.param.${className}QueryOneParam;

@Mapper
public interface RequestMapper
{
	RequestMapper INSTANCE = Mappers.getMapper( RequestMapper.class );
	
<#if table.pkCount gte 1>
<#list table.compositeIdColumns as column>
	@Mapping(target = "${column.columnNameLower}", ignore = true)
</#list>
</#if>
	${className} requestToEntity(${className}CreateRequest request);
	
	${className} requestToEntity(${className}ModifyRequest request);
	
	${className}QueryOneParam requestToParam(${className}QueryOneRequest request);
	
	${className}QueryListParam requestToParam(${className}QueryListRequest request);
	
}
