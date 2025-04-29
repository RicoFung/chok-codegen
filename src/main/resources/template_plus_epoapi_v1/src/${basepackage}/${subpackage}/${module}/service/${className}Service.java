<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = table.classNameLowerCase>
<#assign subpkg = subpackage?replace("/",".")>
<#assign splitIndex = subpkg?index_of(".")>
<#assign prefix = subpkg?substring(splitIndex+1)>
package ${basepackage}.${subpkg}.${module}.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${basepackage}.${subpkg}.${module}.dao.${className}Dao;
import ${basepackage}.${subpkg}.${module}.model.result.${className}Result;
import ${basepackage}.${subpkg}.${module}.model.entity.${className};
import ${basepackage}.${subpkg}.${module}.model.param.QueryListParam;

@Service(value = "${apiGroup}${className}Service")
public class ${className}Service
{
	@SuppressWarnings("unused")
	private final static Logger logger = LoggerFactory.getLogger(${className}Service.class);
	
	@Autowired
	private ${className}Dao dao;

	public List<${className}Result> queryList(QueryListParam param) 
	{
		return dao.queryList(param);
	}
	
	public Integer queryCount(QueryListParam param)
	{
		return dao.queryCount(param);
	}
}
