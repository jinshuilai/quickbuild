package com.lai.framework.base;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository {


    /**
     * @param sql  原生sql语句
     * @param param 动态执行参数 type:List
     * @return 返回执行的结果集条数
     */
    public int executeUpdateBySQL(String sql,List<Object> param);
    /**
     * @param sql  原生sql语句
     * @param param 动态执行参数 type:Map
     * @return 返回执行的结果集条数
     */
    public int executeUpdateBySQL(String sql,Map<String,Object> param);
    /**
     * @param hql  hql语句
     * @param param 动态执行参数 type:List
     * @return 返回执行的结果集条数
     */
    public int executeUpdateByHql(String hql,List<Object> param);
    /**
     * @param hql  hql语句
     * @param param 动态执行参数 type:Map
     * @return 返回执行的结果集条数
     */
    public int executeUpdateByHql(String hql,Map<String,Object> param);

    /**
     * @param hql hql语句
     * @param param 动态查询参数 type:List
     * @param t 单实例类型
     * @return 单实例结果集
     */
    public List findByHql(String hql,List<Object> param,Class t);

    /**
     * @param hql  hql语句
     * @param param 动态查询参数 type:List
     * @param t  单实例类型
     * @param pageNo   页码数
     * @param pageSize  每页条数
     * @return 单实例结果集
     */
    public List findByHqlWithPage(String hql,List<Object> param,Class t,int pageNo,int pageSize);

    /**
     * @param hql  hql 语句
     * @param param  动态查询参数 type:List
     * @return 自定义字段返回结果集
     */
    public List<Object[]> findByHql(String hql,List<Object> param);

    /**
     * @param hql hql语句
     * @param param  动态查询参数 type:List
     * @param pageNo  页码数
     * @param pageSize 每页条数
     * @return 自定义字段返回结果集
     */
    public List<Object[]> findByHqlWithPage(String hql,List<Object> param,int pageNo,int pageSize);

    /**
     * @param sql  原生sql语句
     * @param param  动态查询参数 type:List
     * @return  执行原生sql返回结果集
     */
    public List findBySQL(String sql,List<Object> param,Class t);

    /**
     * @param sql 原生sql语句
     * @param param 动态查询参数 type:List
     * @param pageNo 页码数
     * @param pageSize 每页条数
     * @return
     */
    public List findBySQLWithPage(String sql,List<Object> param ,int pageNo,int pageSize,Class t);


}