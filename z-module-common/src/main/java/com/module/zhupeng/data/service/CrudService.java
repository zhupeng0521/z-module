package com.module.zhupeng.data.service;

import com.digiwin.bpm.module.common.data.Paging;
import com.digiwin.bpm.module.common.data.QueryResult;
import com.digiwin.bpm.module.common.data.annotation.SaveEntityData;
import com.digiwin.bpm.module.common.data.annotation.UpdateEntityData;
import com.digiwin.bpm.module.common.data.annotation.UpdateMapData;
import com.digiwin.bpm.module.common.data.repository.ICrudRepository;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CrudService
 * <p>date : 2019-11-08</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class})
public abstract class CrudService<K extends Serializable, E extends Serializable>
        implements ICrudService<K, E> {


    /**
     * 子类需要实现此抽象方法，1：防止基类中注入！ 2：为基类提供基础的CRUD
     *
     * @return CrudRepository
     */
    public abstract ICrudRepository<K, E> getRepository();


    /**
     * 将实体对象保存到数据库中
     *
     * @param entity 待保存的实体对象
     * @return 实体对象的ID
     */
    @Override
    @SaveEntityData
    public K save(E entity) {
        return getRepository().save(entity);
    }

    /**
     * 将实体对象【集合】保存到数据库中
     *
     * @param entities 实体对象【集合】
     */
    @Override
    @SaveEntityData
    public void save(Collection<E> entities) {
        getRepository().save(entities);
    }

    /**
     * 更新记录
     *
     * @param entity 待更新记录对应的实体对象
     * @return 更新后的实体对象
     */
    @Override
    @UpdateEntityData
    public E update(E entity) {
        return getRepository().update(entity);
    }


    /**
     * 更新 ，通过HQL更新，主键为条件
     *
     * @param entity 待更新记录对应的实体对象
     * @return 返回影响行数
     */
    @Override
    @UpdateEntityData
    public int updateByHQL(E entity) {
        return getRepository().updateByHQL(entity);
    }

    /**
     * 更新 ，通过HQL更新，主键为条件
     *
     * @param entities 待更新记录对应的实体对象集合
     * @return 返回影响行数
     */
    @Override
    @UpdateEntityData
    public int updateByHQL(Collection<E> entities) {
        return getRepository().updateByHQL(entities);
    }


    /**
     * 通过HQL更新数据
     *
     * @param parameters     Map 更新字段与值
     * @param conditionNames 更新条件字段
     * @return 返回影响行数
     */
    @Override
    @UpdateMapData
    public int update(Map<String, Object> parameters, String... conditionNames) {
        return getRepository().updateByHQL(parameters, conditionNames);
    }


    /**
     * 通过HQL更新数据
     *
     * @param parameters     Map 更新字段与值
     * @param conditionNames 更新条件字段
     * @return 返回影响行数
     */
    @Override
    @UpdateMapData
    public int updateByHQL(Map<String, Object> parameters, String... conditionNames) {
        return getRepository().updateByHQL(parameters, conditionNames);
    }

    /**
     * 通过HQL更新数据
     *
     * @param parameters     Map-List集合 更新字段与值
     * @param conditionNames 更新条件字段
     * @return 返回影响行数
     */
    @Override
    @UpdateMapData
    public int updateByHQL(Collection<Map<String, Object>> parameters, String... conditionNames) {
        return getRepository().updateByHQL(parameters, conditionNames);
    }


    /**
     * 删除一个实体对象对应的表记录
     *
     * @param entity 待删除的实体对象
     */
    @Override
    public void delete(E entity) {
        getRepository().delete(entity);
    }

    /**
     * 删除一组记录
     *
     * @param entities 待删除记录集合
     */
    @Override
    public void delete(Collection<E> entities) {
        getRepository().delete(entities);
    }

    /**
     * 根据id删除一条记录
     *
     * @param id 待删除记录id
     * @return 是否删除成功（id是否有效）
     */
    @Override
    public boolean deleteById(K id) {
        return getRepository().deleteById(id);
    }

    /**
     * 删除，通过HQL删除
     *
     * @param parameters 删除条件集合
     * @return
     */
    @Override
    public int delete(Map<String, Object> parameters) {
        return getRepository().deleteByHQL(parameters);
    }

    /**
     * 删除，通过HQL删除，自定义SQL
     *
     * @param whereHQL   自定义条件 o.userName=:userName (and|or) o.userName=:userName ^……
     * @param parameters 删除条件集合
     * @return
     */
    @Override
    public int delete(String whereHQL, Map<String, Object> parameters) {
        return getRepository().deleteByHQL(whereHQL, parameters);
    }

    /**
     * 获取实体对象
     *
     * @param id 主键
     * @return 实体对象
     */
    @Override
    public E getById(K id) {
        return this.getRepository().getById(id);
    }

    /**
     * 获取实体对象
     *
     * @param id 主键
     * @return 实体对象
     */
    @Override
    public E findById(K id) {
        return getRepository().findById(id);
    }

    /**
     * 获取总记录数
     *
     * @return 总数
     */
    @Override
    public long count() {
        return getRepository().count();
    }


    /**
     * 加载所有记录集合
     *
     * @return 所有记录集合
     */
    public List<E> load() {
        return this.getRepository().load();
    }

    /**
     * HQL查询，分页获取记录
     *
     * @param whereHQL   条件语句,不带where关键字,条件语句只能使用参数变量,
     *                   如:o.username=:username and o.password=:password
     * @param parameters 参数集合
     * @param paging     分页 & 排序
     * @return 数据集合
     */
    public List<E> query(String whereHQL, Map<String, Object> parameters, Paging paging) {
        return this.getRepository().query(whereHQL, parameters, paging);
    }

    /**
     * HQL查询
     *
     * @param whereHQL   条件语句,不带where关键字,条件语句只能使用参数变量,
     *                   如:o.username=:username and o.password=:password
     * @param parameters 参数集合
     * @return 数据集合
     */
    public List<E> query(String whereHQL, Map<String, Object> parameters) {
        return this.getRepository().query(whereHQL, parameters);
    }

    /**
     * HQL查询
     *
     * @param parameters 参数集合
     * @param paging     分页
     * @return 数据集合
     */
    public List<E> query(Map<String, Object> parameters, Paging paging) {
        return this.getRepository().query(parameters, paging);
    }

    /**
     * HQL查询
     *
     * @param parameters 参数集合
     * @return 数据集合
     */
    public List<E> query(Map<String, Object> parameters) {
        return this.getRepository().query(parameters);
    }

    /**
     * 分页查询所有记录
     *
     * @param paging 分页
     * @return 数据集合
     */
    public List<E> query(Paging paging) {
        return this.getRepository().query(paging);
    }


    /**
     * HQL查询，分页获取记录
     *
     * @param whereHQL   条件语句,不带where关键字,条件语句只能使用参数变量,
     *                   如:o.username=:username and o.password=:password
     * @param parameters 参数集合
     * @param paging     分页 & 排序
     * @return 数据集合
     */
    @Override
    public QueryResult<E> queryResult(String whereHQL, Map<String, Object> parameters, Paging paging) {
        QueryResult<E> result = new QueryResult<>();
        result.setResult(getRepository().query(whereHQL, parameters, paging));
        result.setPaging(paging);
        result.setRecordCount(paging.getRecordCount());
        return result;
    }


    /**
     * HQL查询
     *
     * @param parameters 参数集合
     * @param paging     分页
     * @return 数据集合
     */
    @Override
    public QueryResult<E> queryResult(Map<String, Object> parameters, Paging paging) {
        QueryResult<E> result = new QueryResult<>();
        result.setResult(this.query(parameters, paging));
        result.setPaging(paging);
        result.setRecordCount(paging.getRecordCount());
        return result;
    }

    /**
     * HQL查询
     *
     * @param whereHQL   条件语句,不带where关键字,条件语句只能使用参数变量,
     *                   如:o.username=:username and o.password=:password
     * @param parameters 参数集合
     * @return 数据集合
     */
    @Override
    public QueryResult<E> queryResult(String whereHQL, Map<String, Object> parameters) {
        return this.queryResult(whereHQL, parameters, null);
    }

    /**
     * 分页查询所有记录
     *
     * @param paging 分页
     * @return 数据集合
     */
    @Override
    public QueryResult<E> queryResult(Paging paging) {
        QueryResult<E> result = new QueryResult<>();
        result.setResult(getRepository().query(paging));
        result.setPaging(paging);
        result.setRecordCount(paging.getRecordCount());
        return result;
    }

    @Override
    public int execute(String hql, Map<String, Object> parameters) {
        Query query = this.getRepository().getSession().createQuery(hql);
        query.setProperties(parameters);
        return query.executeUpdate();
    }

    @Override
    public int execute(String hql) {
        return this.execute(hql, new HashMap<>());
    }

    @Override
    public int executeNative(String sql, Map<String, Object> parameters) {
        Query query = this.getRepository().getSession().createNativeQuery(sql);
        query.setProperties(parameters);
        return query.executeUpdate();
    }

    @Override
    public int executeNative(String sql) {
        return this.executeNative(sql, new HashMap<>());
    }

    @Override
    public <V> List<V> queryVO(String hql, Class<V> vClass) {
        return this.getRepository().queryVO(hql, vClass);
    }

    @Override
    public <V> List<V> queryVO(String hql, Map<String, Object> parameters, Class<V> vClass) {
        return this.getRepository().queryVO(hql, parameters, vClass);
    }

    @Override
    public <V> List<V> queryVO(String hql, Map<String, Object> parameters, Paging paging, Class<V> vClass) {
        return this.getRepository().queryVO(hql, parameters, paging, vClass);
    }

}
