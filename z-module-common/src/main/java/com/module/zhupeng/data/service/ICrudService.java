package com.module.zhupeng.data.service;

import com.digiwin.bpm.module.common.data.Paging;
import com.digiwin.bpm.module.common.data.QueryResult;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * ICrudService
 * <p>date : 2019-11-08</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public interface ICrudService<K extends Serializable, E extends Serializable> {
    /**
     * 将实体对象保存到数据库中
     *
     * @param entity 待保存的实体对象
     * @return 实体对象的ID
     */
    K save(E entity);

    /**
     * 将实体对象【集合】保存到数据库中
     *
     * @param entities 实体对象【集合】
     */
    void save(Collection<E> entities);

    /**
     * 更新记录
     *
     * @param entity 待更新记录对应的实体对象
     * @return 更新后的实体对象
     */
    E update(E entity);


    /**
     * 通过HQL更新数据
     *
     * @param parameters     Map 更新字段与值
     * @param conditionNames 更新条件字段
     * @return 返回影响行数
     */
    int update(Map<String, Object> parameters, String... conditionNames);


    /**
     * 更新 ，通过HQL更新，主键为条件
     *
     * @param entity 待更新记录对应的实体对象
     * @return 返回影响行数
     */
    int updateByHQL(E entity);

    /**
     * 更新 ，通过HQL更新，主键为条件
     *
     * @param entities 待更新记录对应的实体对象集合
     * @return 返回影响行数
     */
    int updateByHQL(Collection<E> entities);

    /**
     * 通过HQL更新数据
     *
     * @param parameters     Map 更新字段与值
     * @param conditionNames 更新条件字段
     * @return 返回影响行数
     */
    int updateByHQL(Map<String, Object> parameters, String... conditionNames);

    /**
     * 通过HQL更新数据
     *
     * @param parameters     Map-List集合 更新字段与值
     * @param conditionNames 更新条件字段
     * @return 返回影响行数
     */
    int updateByHQL(Collection<Map<String, Object>> parameters, String... conditionNames);


    /**
     * 删除一个实体对象对应的表记录
     *
     * @param entity 待删除的实体对象
     */
    void delete(E entity);

    /**
     * 删除一组记录
     *
     * @param entities 待删除记录集合
     */
    void delete(Collection<E> entities);

    /**
     * 根据id删除一条记录
     *
     * @param id 待删除记录id
     * @return 是否删除成功（id是否有效）
     */
    boolean deleteById(K id);

    /**
     * 删除，通过HQL删除
     *
     * @param parameters 删除条件集合
     * @return
     */
    int delete(Map<String, Object> parameters);

    /**
     * 删除，通过HQL删除，自定义SQL
     *
     * @param whereHQL   自定义条件 o.userName=:userName (and|or) o.userName=:userName ^……
     * @param parameters 删除条件集合
     * @return
     */
    int delete(String whereHQL, Map<String, Object> parameters);


    /**
     * 获取实体对象
     *
     * @param id 主键
     * @return 实体对象
     */
    E getById(K id);

    /**
     * 获取实体对象
     *
     * @param id 主键
     * @return 实体对象
     */
    E findById(K id);

    /**
     * 获取总记录数
     *
     * @return 总数
     */
    long count();

    /**
     * 加载所有记录集合
     *
     * @return 所有记录集合
     */
    List<E> load();

    /**
     * HQL查询，分页获取记录
     *
     * @param whereHQL   条件语句,不带where关键字,条件语句只能使用参数变量,
     *                   如:o.username=:username and o.password=:password
     * @param parameters 参数集合
     * @param paging     分页 & 排序
     * @return 数据集合
     */
    List<E> query(String whereHQL, Map<String, Object> parameters, Paging paging);

    /**
     * HQL查询
     *
     * @param whereHQL   条件语句,不带where关键字,条件语句只能使用参数变量,
     *                   如:o.username=:username and o.password=:password
     * @param parameters 参数集合
     * @return 数据集合
     */
    List<E> query(String whereHQL, Map<String, Object> parameters);

    /**
     * HQL查询
     *
     * @param parameters 参数集合
     * @param paging     分页
     * @return 数据集合
     */
    List<E> query(Map<String, Object> parameters, Paging paging);

    /**
     * HQL查询
     *
     * @param parameters 参数集合
     * @return 数据集合
     */
    List<E> query(Map<String, Object> parameters);

    /**
     * 分页查询所有记录
     *
     * @param paging 分页
     * @return 数据集合
     */
    List<E> query(Paging paging);


    /**
     * HQL查询，分页获取记录
     *
     * @param whereHQL   条件语句,不带where关键字,条件语句只能使用参数变量,
     *                   如:o.username=:username and o.password=:password
     * @param parameters 参数集合
     * @param paging     分页 & 排序
     * @return 数据集合
     */
    QueryResult<E> queryResult(String whereHQL, Map<String, Object> parameters, Paging paging);


    /**
     * HQL查询
     *
     * @param parameters 参数集合
     * @param paging     分页
     * @return 数据集合
     */
    QueryResult<E> queryResult(Map<String, Object> parameters, Paging paging);

    /**
     * HQL查询
     *
     * @param whereHQL   条件语句,不带where关键字,条件语句只能使用参数变量,
     *                   如:o.username=:username and o.password=:password
     * @param parameters 参数集合
     * @return 数据集合
     */
    QueryResult<E> queryResult(String whereHQL, Map<String, Object> parameters);

    /**
     * 分页查询所有记录
     *
     * @param paging 分页
     * @return 数据集合
     */
    QueryResult<E> queryResult(Paging paging);

    /**
     * 执行 hql
     *
     * @param hql        HQL语句
     * @param parameters 参数
     * @return 返回影响的行数
     */
    int execute(String hql, Map<String, Object> parameters);

    /**
     * 执行 hql
     *
     * @param hql HQL语句
     * @return 返回影响的行数
     */
    int execute(String hql);


    /**
     * 执行 sql
     *
     * @param sql        sql语句
     * @param parameters 参数
     * @return 返回影响的行数
     */
    int executeNative(String sql, Map<String, Object> parameters);

    /**
     * 执行 sql
     *
     * @param sql sql语句
     * @return 返回影响的行数
     */
    int executeNative(String sql);

    <V> List<V> queryVO(String hql, Class<V> vClass);

    <V> List<V> queryVO(String hql, Map<String, Object> parameters, Class<V> vClass);

    <V> List<V> queryVO(String hql, Map<String, Object> parameters, Paging paging, Class<V> vClass);


}
