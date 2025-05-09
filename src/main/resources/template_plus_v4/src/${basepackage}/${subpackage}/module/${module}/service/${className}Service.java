<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = table.classNameLowerCase>
<#assign subpkg = subpackage?replace("/",".")>
<#assign splitIndex = subpkg?index_of(".")>
<#assign prefix = subpkg?substring(splitIndex+1)>
package ${basepackage}.${subpkg}.module.${module}.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import ${basepackage}.${subpkg}.repository.dao.${className}Dao;
import ${basepackage}.${subpkg}.repository.model.result.${className}Result;
import ${basepackage}.${subpkg}.repository.model.entity.${className};
import ${basepackage}.${subpkg}.repository.model.param.${className}CreateParam;
import ${basepackage}.${subpkg}.repository.model.param.${className}ModifyParam;
import ${basepackage}.${subpkg}.repository.model.param.${className}QueryListParam;
import ${basepackage}.${subpkg}.repository.model.param.${className}QueryOneParam;

import chok2.devwork.pojo.ChokResponse;

@CacheConfig(cacheNames = {"CACHE_${className}"})
@Service(value = "${apiGroup}${className}Service")
public class ${className}Service
{
	@SuppressWarnings("unused")
	private final static Logger logger = LoggerFactory.getLogger(${className}Service.class);
	
	@Autowired
	private ${className}Dao dao;

	@Caching(evict = { @CacheEvict(allEntries = true) })
	public ChokResponse<Object> create(${className}CreateParam entity)
	{
		dao.create(entity);
		return new ChokResponse<Object>();
	}

	@Caching(evict = { @CacheEvict(allEntries = true) })
	public ChokResponse<Object> remove(String[] ids)
	{
		dao.remove(ids);
		return new ChokResponse<Object>();
	}

	@Caching(evict = { @CacheEvict(value = {"CACHE_${className}"}, allEntries = true) })
	public ChokResponse<Object> modify(${className}ModifyParam entity)
	{
		dao.modify(entity);
		return new ChokResponse<Object>();
	}	

	@Cacheable(key = "#p0.toString()")
	public ChokResponse<${className}Result> queryOne(${className}QueryOneParam param) 
	{
		${className}Result result = dao.queryOne(param);
		return new ChokResponse<${className}Result>(result);
	}

	@Cacheable(key = "#p0.toString()")
	public ChokResponse<List<${className}Result>> queryList(${className}QueryListParam param) 
	{
		List<${className}Result> result = dao.queryList(param);
		return new ChokResponse<List<${className}Result>>(result);
	}
}
