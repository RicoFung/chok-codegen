<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import ${basepackage}.${subpkg}.${module}.model.entity.${className};
import ${basepackage}.${subpkg}.${module}.model.param.${className}GetListParam;
import ${basepackage}.${subpkg}.${module}.model.param.${className}GetOneParam;

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
	
	${className}GetOneParam requestToParam(${className}GetOneRequest request);
	
	${className}GetListParam requestToParam(${className}GetListRequest request);
	
}
