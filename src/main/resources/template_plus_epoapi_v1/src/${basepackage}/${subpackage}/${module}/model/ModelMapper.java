<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ${basepackage}.${subpkg}.${module}.model.param.QueryListParam;
import ${basepackage}.${subpkg}.${module}.model.request.${className}QueryListRequestInfo;

@Mapper
public interface ModelMapper
{
	ModelMapper INSTANCE = Mappers.getMapper( ModelMapper.class );
	
	QueryListParam requestToParam(${className}QueryListRequestInfo request);
	
}
