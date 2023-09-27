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
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import ${basepackage}.${subpkg}.${module}.dao.${className}Dao;
import ${basepackage}.${subpkg}.${module}.model.result.${className}QueryListResult;
import ${basepackage}.${subpkg}.${module}.model.result.${className}QueryOneResult;
import ${basepackage}.${subpkg}.${module}.model.entity.${className};
import ${basepackage}.${subpkg}.${module}.model.param.${className}QueryListParam;
import ${basepackage}.${subpkg}.${module}.model.param.${className}QueryOneParam;

import chok2.devwork.pojo.ChokDto;

@CacheConfig(cacheNames = {"CACHE_${className}"})
@Service(value = "${apiGroup}${className}Service")
public class ${className}Service
{
	@SuppressWarnings("unused")
	private final static Logger logger = LoggerFactory.getLogger(${className}Service.class);
	
	@Autowired
	private ${className}Dao dao;

	@Caching(evict = { @CacheEvict(allEntries = true) })
	public ChokDto<Object> create(${className} entity)
	{
		dao.create(entity);
		return new ChokDto<Object>();
	}

	@Caching(evict = { @CacheEvict(allEntries = true) })
	public ChokDto<Object> remove(String[] ids)
	{
		dao.remove(ids);
		return new ChokDto<Object>();
	}

	@Caching(evict = { @CacheEvict(value = {"CACHE_${className}"}, allEntries = true) })
	public ChokDto<Object> modify(${className} entity)
	{
		dao.modify(entity);
		return new ChokDto<Object>();
	}	

	@Cacheable(key = "#param")
	public ChokDto<${className}QueryOneResult> queryOne(${className}QueryOneParam param) 
	{
		${className}QueryOneResult result = dao.queryOne(param);
		return new ChokDto<${className}QueryOneResult>(result);
	}

	@Cacheable(key = "#param")
	public ChokDto<List<${className}QueryListResult>> queryList(${className}QueryListParam param) 
	{
		List<${className}QueryListResult> result = dao.queryList(param);
		return new ChokDto<List<${className}QueryListResult>>(result);
	}
}
