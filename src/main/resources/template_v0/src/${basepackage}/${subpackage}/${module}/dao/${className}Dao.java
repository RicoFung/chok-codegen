<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign classNameLowerCase = table.classNameLowerCase>  
<#assign subpkg = subpackage?replace("/",".")>
<#assign prefix = module>
package ${basepackage}.${subpkg}.${module}.dao;

import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import chok.devwork.springboot.BaseDao;
import ${basepackage}.${subpkg}.${module}.entity.${className};

@Repository(value = "${prefix}${className}Dao")
public class ${className}Dao extends BaseDao<${className}, Long>
{
	@Resource//(name = "firstSqlSessionTemplate")
	private SqlSession sqlSession;

	@Override
	protected SqlSession getSqlSession()
	{
		return sqlSession;
	}
	
	@Override
	public Class<${className}> getEntityClass()
	{
		return ${className}.class;
	}
}
