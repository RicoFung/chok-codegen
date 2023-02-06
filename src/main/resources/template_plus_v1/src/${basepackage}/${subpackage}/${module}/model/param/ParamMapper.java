<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign subpkg = subpackage?replace("/",".")>
package ${basepackage}.${subpkg}.${module}.model.param;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.domain.tbdemo.model.entity.${className}Entity;
import com.domain.tbdemo.model.query.${className}GetListQuery;
import com.domain.tbdemo.model.query.${className}GetOneQuery;

@Mapper
public interface ParamMapper
{
	ParamMapper INSTANCE = Mappers.getMapper( ParamMapper.class );
	
<#if table.pkCount gte 1>
<#list table.compositeIdColumns as column>
	@Mapping(target = "${column.columnNameLower}", ignore = true)
</#list>
</#if>
	${className}Entity paramToEntity(${className}CreateParam param);
	
	${className}Entity paramToEntity(${className}ModifyParam param);
	
	${className}GetOneQuery paramToQuery(${className}GetOneParam param);
	
	${className}GetListQuery paramToQuery(${className}GetListParam param);
	
}
