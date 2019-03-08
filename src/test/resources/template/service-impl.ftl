package ${basePackage}.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lai.base.BaseServiceImpl;
import ${basePackage}.repository.${modelNameUpperCamel}Repository;
import ${basePackage}.pojo.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
/**
 * Created by ${author} on ${date}.
 */
@Service
public class ${modelNameUpperCamel}ServiceImpl extends BaseServiceImpl<${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {
    
    @Autowired
    private ${modelNameUpperCamel}Repository ${modelNameLowerCamel}Repository;
    
    @Override
	protected JpaRepository<${modelNameUpperCamel}, Integer> getRepository() {
		return ${modelNameLowerCamel}Repository;
	}

	public Page<${modelNameUpperCamel}> findByPage(Integer start,Integer size, ${modelNameUpperCamel} pojo) {
		//order by
		Sort sort = new Sort(Direction.DESC,"seqid");
		PageRequest page = new PageRequest(start, size,sort);
		
		 Specification<${modelNameUpperCamel}> specification = (Root<${modelNameUpperCamel}> root,
		                CriteriaQuery<?> query, CriteriaBuilder cb)-> {
		            List<Predicate> list = new ArrayList<Predicate>();
		            
		            //==========查询条件=================== String Integer Date
		            <#list columns as col>
						<#if col.type == "datatime">
						if(pojo.get${col.filedUp}() != null){
			            	Date now = pojo.get${col.filedUp}End() == null ? new Date() : pojo.get${col.filedUp}End();
			                Predicate p = cb.between(root.get("${col.filed}").as(Date.class), pojo.get${col.filedUp}(), now);
			                list.add(p);
		            	}
						<#elseif col.type == 'varchar'>
						if(StringUtils.hasText(pojo.get${col.filedUp}())){
			            	Predicate p = cb.equal(root.get("${col.filed}").as(String.class), pojo.get${col.filedUp}());
			            	list.add(p);
			            }
						<#elseif col.type == 'int'>
						if(pojo.get${col.filedUp}() != null){
			            	Predicate p = cb.equal(root.get("${col.filed}").as(Integer.class), pojo.get${col.filedUp}());
			            	list.add(p);
			            }
						<#else>
						
						</#if>
					</#list>
		            
	                //=====================================
		            
		            return cb.and(list.toArray(new Predicate[0]));
		};
		
		return ${modelNameLowerCamel}Repository.findAll(specification, page);
	}

}
