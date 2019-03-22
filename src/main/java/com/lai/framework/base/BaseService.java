package com.lai.framework.base;

import java.util.List;

import org.springframework.data.domain.Page;

public interface BaseService<T> {

	Page<T> findByPage(Integer start, Integer size, T obj);

	void deletes(String ids);
	
	void delete(Integer id);

	void save(T obj);
	
	void saveBath(List<T> objs);

	T find(Integer id);
}
