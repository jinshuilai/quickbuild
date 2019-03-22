package com.lai.framework.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Service;

@Service
public class BaseRepositoryImpl implements BaseRepository {
	
	@PersistenceUnit
    private EntityManagerFactory emf;

	@Override
	public int executeUpdateBySQL(String sql, List<Object> param) {
		EntityManager em=emf.createEntityManager();
		Query query = em.createNativeQuery(sql);
		if (param != null && !param.isEmpty()) {
			for (int i = 0; i < param.size(); i++) {
				query.setParameter(i + 1, param.get(i));
			}
		}
		return query.executeUpdate();
	}

	@Override
	public int executeUpdateBySQL(String sql, Map<String, Object> param) {
		EntityManager em=emf.createEntityManager();
		Query query = em.createNativeQuery(sql);
		if (param != null && !param.isEmpty()) {
			for (String name : param.keySet()) {
				query.setParameter(name, param.get(name));
			}
		}
		return query.executeUpdate();
	}

	@Override
	public int executeUpdateByHql(String hql, List<Object> param) {
		EntityManager em=emf.createEntityManager();
		Query query = em.createQuery(hql);
		if (param != null && !param.isEmpty()) {
			for (int i = 0; i < param.size(); i++) {
				query.setParameter(i + 1, param.get(i));
			}
		}
		return query.executeUpdate();
	}

	@Override
	public int executeUpdateByHql(String hql, Map<String, Object> param) {
		EntityManager em=emf.createEntityManager();
		Query query = em.createQuery(hql);
		if (param != null && !param.isEmpty()) {
			for (String name : param.keySet()) {
				query.setParameter(name, param.get(name));
			}
		}
		return query.executeUpdate();
	}

	@Override
	public List findByHql(String hql, List<Object> param, Class t) {
		EntityManager em=emf.createEntityManager();
		Query query = em.createQuery(hql, t);
		if (param != null && !param.isEmpty()) {
			for (int i = 0; i < param.size(); i++) {
				query.setParameter(i + 1, param.get(i));
			}
		}
		return query.getResultList();
	}

	@Override
	public List findByHqlWithPage(String hql, List<Object> param, Class t, int pageNo, int pageSize) {
		EntityManager em=emf.createEntityManager();
		Query query = em.createQuery(hql, t);
		if (param != null && !param.isEmpty()) {
			for (int i = 0; i < param.size(); i++) {
				query.setParameter(i + 1, param.get(i));
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}
		}
		return query.getResultList();
	}

	@Override
	public List<Object[]> findByHql(String hql, List<Object> param) {
		EntityManager em=emf.createEntityManager();
		Query query = em.createQuery(hql);
		if (param != null && !param.isEmpty()) {
			for (int i = 0; i < param.size(); i++) {
				query.setParameter(i + 1, param.get(i));
			}
		}
		return query.getResultList();
	}

	@Override
	public List<Object[]> findByHqlWithPage(String hql, List<Object> param, int pageNo, int pageSize) {
		EntityManager em=emf.createEntityManager();
		Query query = em.createQuery(hql);
		if (param != null && !param.isEmpty()) {
			for (int i = 0; i < param.size(); i++) {
				query.setParameter(i + 1, param.get(i));
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}
		}
		return query.getResultList();
	}

	@Override
	public List findBySQL(String sql, List<Object> param,Class t) {
		EntityManager em=emf.createEntityManager();
		Query query = em.createNativeQuery(sql);
		query.unwrap(NativeQueryImpl.class).setResultTransformer(new AliasToBeanResultTransformer(t));
		if (param != null && !param.isEmpty()) {
			for (int i = 0; i < param.size(); i++) {
				query.setParameter(i + 1, param.get(i));
			}
		}
		return query.getResultList();
	}

	@Override
	public List findBySQLWithPage(String sql, List<Object> param, int pageNo, int pageSize,Class t) {
		EntityManager em=emf.createEntityManager();
		Query query = em.createNativeQuery(sql);
		query.unwrap(NativeQueryImpl.class).setResultTransformer(new AliasToBeanResultTransformer(t));
		if (param != null && !param.isEmpty()) {
			for (int i = 0; i < param.size(); i++) {
				query.setParameter(i + 1, param.get(i));
			}
		}
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	
}
