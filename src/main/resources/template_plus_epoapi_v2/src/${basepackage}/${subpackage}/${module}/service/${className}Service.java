<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = table.classNameLowerCase>
<#assign subpkg = subpackage?replace("/",".")>
<#assign splitIndex = subpkg?index_of(".")>
<#assign prefix = subpkg?substring(splitIndex+1)>
package ${basepackage}.${subpkg}.${module}.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${basepackage}.${subpkg}.${module}.dao.${className}Dao;
import ${basepackage}.${subpkg}.${module}.model.param.${className}CreateParam;
import ${basepackage}.${subpkg}.${module}.model.param.${className}ModifyParam;

import com.base.http.processgenerics.EpoResponse;

@Service(value = "${apiGroup}${className}Service")
public class ${className}Service
{
	@SuppressWarnings("unused")
	private final static Logger logger = LoggerFactory.getLogger(${className}Service.class);
	
	@Autowired
	private ${className}Dao dao;

	public com.base.http.processgenerics.EpoResponse<Object> create(${className}CreateParam param)
	{
		dao.create(param);
		return new com.base.http.processgenerics.EpoResponse<Object>();
	}

	public com.base.http.processgenerics.EpoResponse<Object> modify(${className}ModifyParam param)
	{
		dao.modify(param);
		return new com.base.http.processgenerics.EpoResponse<Object>();
	}
}
