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
import ${basepackage}.${subpkg}.${module}.model.data.${className}GetListData;
import ${basepackage}.${subpkg}.${module}.model.data.${className}GetOneData;
import ${basepackage}.${subpkg}.${module}.model.entity.${className}Entity;
import ${basepackage}.${subpkg}.${module}.model.query.${className}GetListQuery;
import ${basepackage}.${subpkg}.${module}.model.query.${className}GetOneQuery;

import chok.devwork.pojo.ChokDto;

@CacheConfig(cacheNames = {"CACHE_${className}"})
@Service(value = "${apiGroup}${className}Service")
public class ${className}Service
{
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ${className}Dao dao;

	@Caching(evict = { @CacheEvict(allEntries = true) })
	public ChokDto<Object> add(${className}Entity entity)
	{
		dao.add(entity);
		return new ChokDto<Object>();
	}

	@Caching(evict = { @CacheEvict(allEntries = true) })
	public ChokDto<Object> del(String[] ids)
	{
		dao.del(ids);
		return new ChokDto<Object>();
	}

	@Caching(evict = { @CacheEvict(value = {"CACHE_${className}"}, allEntries = true) })
	public ChokDto<Object> upd(${className}Entity entity)
	{
		dao.upd(entity);
		return new ChokDto<Object>();
	}	

	@Cacheable(key = "#query")
	public ChokDto<${className}GetOneData> getOne(${className}GetOneQuery query) 
	{
		${className}GetOneData data = dao.getOne(query);
		return new ChokDto<${className}GetOneData>(data);
	}

	@Cacheable(key = "#query")
	public ChokDto<List<${className}GetListData>> getList(${className}GetListQuery query) 
	{
		List<${className}GetListData> data = dao.getList(query);
		return new ChokDto<List<${className}GetListData>>(data);
	}
}
