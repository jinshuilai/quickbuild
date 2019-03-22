package com.lai.framework.base;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	protected abstract JpaRepository<T,Integer> getRepository();
	
	@Override
	@Transactional
	public void deletes(String ids) {
		if(StringUtils.hasText(ids)){
			String[] idarry = ids.split(",");
			for (String id : idarry) {
				getRepository().deleteById(Integer.valueOf(id));
			}
		}
	}
	
	@Override
	public void delete(Integer id) {
		getRepository().deleteById(id);
	}

	@Override
	public void save(T obj) {
		getRepository().save(obj);
	}
	
	@Override
	public void saveBath(List<T> objs) {
		getRepository().saveAll(objs);
	}

	@Override
	public T find(Integer id) {
		return getRepository().getOne(id);
	}
}
