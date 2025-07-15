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

import cn.dev33.satoken.util.SaResult;
import ${basepackage}.${subpkg}.repository.dao.${className}Dao;
import ${basepackage}.${subpkg}.repository.model.result.${className}Result;
import ${basepackage}.${subpkg}.repository.model.entity.${className};
import ${basepackage}.${subpkg}.repository.model.param.${className}CreateParam;
import ${basepackage}.${subpkg}.repository.model.param.${className}ModifyParam;
import ${basepackage}.${subpkg}.repository.model.param.${className}QueryListParam;
import ${basepackage}.${subpkg}.repository.model.param.${className}QueryOneParam;


@CacheConfig(cacheNames = {"CACHE_${className}"})
@Service(value = "${apiGroup}${className}Service")
public class ${className}Service
{
	@SuppressWarnings("unused")
	private final static Logger logger = LoggerFactory.getLogger(${className}Service.class);
	
	@Autowired
	private ${className}Dao dao;

	@Caching(evict = { @CacheEvict(allEntries = true) })
	public SaResult create(${className}CreateParam entity)
	{
		dao.create(entity);
		return SaResult.ok();
	}

	@Caching(evict = { @CacheEvict(allEntries = true) })
	public SaResult remove(String[] ids)
	{
		dao.remove(ids);
		return SaResult.ok();
	}

	@Caching(evict = { @CacheEvict(value = {"CACHE_${className}"}, allEntries = true) })
	public SaResult modify(${className}ModifyParam entity)
	{
		dao.modify(entity);
		return SaResult.ok();
	}	

	@Cacheable(key = "#p0.toString()")
	public SaResult queryOne(${className}QueryOneParam param) 
	{
		${className}Result result = dao.queryOne(param);
		return SaResult.data(result);
	}

	@Cacheable(key = "#p0.toString()")
	public SaResult queryList(${className}QueryListParam param) 
	{
		List<${className}Result> result = dao.queryList(param);
		return SaResult.data(result);
	}
}
