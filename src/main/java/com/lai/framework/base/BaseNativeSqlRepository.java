package com.lai.framework.base;

import org.springframework.stereotype.Service;

import com.lai.domain.UserAccount;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.util.List;

/**
 * @Description: JPA使用原生SQL基类
 * @Auth: yuyang
 * @Date: 2017/1/18 19:30
 * @Version: 1.0
 **/
@Service
public class BaseNativeSqlRepository<T> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    /**
     * 查询多个属性
     * 返回List<obj>对象形式的List，Object为Class格式对象
     * @param sql   原生SQL语句
     * @param obj   Class格式对象
     * @return
     */
    public List<T> queryList(String sql){
        EntityManager em=emf.createEntityManager();
        Query query=em.createNativeQuery(sql);
        List list = query.getResultList();
        em.close();
        return  list;
    }

    /**
     * 查询单个属性
     * 返回obj
     * @param sql  原生SQL语句
     * @return
     */
    public Object queryOne(String sql,Class clazz){
        EntityManager em=emf.createEntityManager();
        Query query=em.createNativeQuery(sql,clazz);
        Object obj = query.getSingleResult();
        em.close();
        return  obj;
    }

}
