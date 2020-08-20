package com.module.zhupeng.data.repository;

import com.module.zhupeng.data.Paging;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * IBaseRepository
 * <p>date : 2019-11-07</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public interface ICrudRepository<K extends Serializable, E extends Serializable> {

    /**
     * 由session工厂获取当前session对象
     *
     * @return 当前session对象
     */
    Session getSession();

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
     * 根据id删除一条记录
     *
     * @param ids 待删除记录id集合
     * @return 是否删除成功（id是否有效）
     */
    boolean deleteById(Collection<K> ids);

    /**
     * 删除，通过HQL删除
     *
     * @param parameters 删除条件集合
     * @return 返回影响行数
     */
    int deleteByHQL(Map<String, Object> parameters);

    /**
     * 删除，通过HQL删除，自定义SQL
     *
     * @param whereHQL   自定义条件 o.userName=:userName (and|or) o.userName=:userName ^……
     * @param parameters 删除条件集合
     * @return 返回影响行数
     */
    int deleteByHQL(String whereHQL, Map<String, Object> parameters);

    /**
     * 获取实体对象
     *
     * @param id 主键值
     * @return 实体对象
     */
    E getById(K id);

    /**
     * 获取实体对象,通过HQL方式
     *
     * @param id 主键值
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
     * 分页加载记录集合
     *
     * @param index 起始行1，2，……
     * @param size  返回行数
     * @return 结果集合
     */
    List<E> load(int index, int size);

    /**
     * 分页加载记录集合
     *
     * @param paging 分页 & 排序
     * @return 结果集合
     */
    List<E> load(Paging paging);

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

    <V> List<V> queryVO(String hql, Class<V> vClass);

    <V> List<V> queryVO(String hql, Map<String, Object> parameters, Class<V> vClass);

    <V> List<V> queryVO(String hql, Map<String, Object> parameters, Paging paging, Class<V> vClass);

}