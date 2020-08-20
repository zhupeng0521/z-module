package com.module.zhupeng.data.repository;

import com.digiwin.bpm.module.common.data.Paging;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.util.Assert;

import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * BaseEntityRepository
 * <p>date : 2019-11-07</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public abstract class TemplateRepository<K extends Serializable, E extends Serializable>
        implements ICrudRepository<K, E> {

    private static final Logger logger = LoggerFactory.getLogger(TemplateRepository.class);

    /**
     * 注入 SessionFactory
     */
    private HibernateTemplate hibernateTemplate;

    /**
     * 实体类对应的Class对象
     */
    private Class<E> clazz;

    /**
     * 主键字段名称集合
     */
    private List<String> idFieldNames;

    /**
     * 保留指定clazz值的接口【通过子类显示调用父类的构造函数来指定】
     *
     * @param clazz
     */
    public TemplateRepository(Class<E> clazz) {
        this.initCrudRepository(clazz);
    }

    /**
     * 注入 HibernateTemplate 与 E 实体类
     *
     * @param template HibernateTemplate
     */
    public TemplateRepository(HibernateTemplate template) {
        this.hibernateTemplate = template;
    }

    /**
     * 注入 HibernateTemplate 与 E 实体类
     *
     * @param clazz    Class
     * @param template HibernateTemplate
     */
    public TemplateRepository(Class<E> clazz, HibernateTemplate template) {
        initCrudRepository(clazz);
        this.hibernateTemplate = template;
    }

    private void initCrudRepository(Class<E> clazz) {

        this.clazz = clazz;
        this.idFieldNames = new ArrayList<>(4);
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                idFieldNames.add(field.getName());
            }
        }

    }

    /**
     * 由session工厂获取当前session对象
     *
     * @return 当前session对象
     */
    @Override
    public Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    /**
     * 将实体对象保存到数据库中
     *
     * @param entity 待保存的实体对象
     * @return 实体对象的ID
     */
    @Override
    public K save(E entity) {
        return (K) this.hibernateTemplate.save(entity);
    }

    /**
     * 将实体对象【集合】保存到数据库中
     *
     * @param entities 实体对象【集合】
     */
    @Override
    public void save(Collection<E> entities) {
        for (E entity : entities) {
            this.save(entity);
        }
    }

    /**
     * 更新记录
     *
     * @param entity 待更新记录对应的实体对象
     * @return 更新后的实体对象
     */
    @Override
    public E update(E entity) {
        this.hibernateTemplate.update(entity);
        return entity;
    }

    /**
     * 更新 ，通过HQL更新，主键为条件
     *
     * @param entity 待更新记录对应的实体对象
     * @return 返回影响行数
     */
    @Override
    public int updateByHQL(E entity) {
        return updateByHQL(RepositoryUtils.getEntityFieldMap(this.clazz, entity));
    }

    /**
     * 更新 ，通过HQL更新，主键为条件
     *
     * @param entities 待更新记录对应的实体对象集合
     * @return 返回影响行数
     */
    @Override
    public int updateByHQL(Collection<E> entities) {
        int iCount = 0;
        for (E entity : entities) {
            iCount += this.updateByHQL(entity);
        }
        return iCount;
    }

    /**
     * 通过HQL更新数据
     *
     * @param parameters     更新字段与值
     * @param conditionNames 更新条件字段
     * @return 返回影响行数
     */
    @Override
    public int updateByHQL(Map<String, Object> parameters, String... conditionNames) {
        return RepositoryUtils.createUpdateQuery(getSession(),
                this.clazz.getSimpleName(),
                this.idFieldNames,
                parameters,
                conditionNames).setProperties(parameters).executeUpdate();
    }

    /**
     * 通过HQL更新数据
     *
     * @param parameters     Map-List集合 更新字段与值
     * @param conditionNames 更新条件字段
     * @return 返回影响行数
     */
    @Override
    public int updateByHQL(Collection<Map<String, Object>> parameters, String... conditionNames) {
        int iCount = 0;
        for (Map<String, Object> map : parameters) {
            iCount += this.updateByHQL(map, conditionNames);
        }
        return iCount;
    }


    /**
     * 删除一个实体对象对应的表记录
     *
     * @param entity 待删除的实体对象
     */
    @Override
    public void delete(E entity) {
        this.hibernateTemplate.delete(entity);
    }

    /**
     * 删除一组记录
     *
     * @param entities 待删除记录集合
     */
    @Override
    public void delete(Collection<E> entities) {
        this.hibernateTemplate.deleteAll(entities);
    }

    /**
     * 根据id删除一条记录
     *
     * @param id 待删除记录id
     * @return 是否删除成功（id是否有效）
     */
    @Override
    public boolean deleteById(K id) {
        E entity = this.getById(id);
        if (null == entity) {
            throw new RuntimeException(String.format("数据中不存在id=%s的数据", id));
        }
        this.hibernateTemplate.delete(entity);
        return true;
    }

    /**
     * 根据id删除一条记录
     *
     * @param ids 待删除记录id集合
     * @return 是否删除成功（id是否有效）
     */
    @Override
    public boolean deleteById(Collection<K> ids) {
        for (K id : ids) {
            this.deleteById(id);
        }
        return true;
    }

    /**
     * 删除，通过HQL删除
     *
     * @param parameters 删除条件集合
     * @return 返回影响的行数
     */
    @Override
    public int deleteByHQL(Map<String, Object> parameters) {
       /* Assert.notEmpty(parameters, "Map<String, Object> parameters 不可以为空！！！");

        String deleteHQL = String.format("delete from %s o where %s",
                this.clazz.getSimpleName(), RepositoryUtils.buildWhereHQL(parameters));*/

        return this.deleteByHQL(RepositoryUtils.buildWhereHQL(parameters), parameters);
    }

    /**
     * 删除，通过HQL删除，自定义SQL
     *
     * @param whereHQL   自定义条件 o.userName=:userName (and|or) o.userName=:userName ^……
     * @param parameters 删除条件集合
     * @return
     */
    @Override
    public int deleteByHQL(String whereHQL, Map<String, Object> parameters) {

        Assert.notEmpty(parameters, "Map<String, Object> parameters 不可以为空！！！");

        String deleteHQL = String.format("delete from %s o where %s",
                this.clazz.getSimpleName(), whereHQL);

        return RepositoryUtils.createUpdateQuery(getSession(), deleteHQL, parameters).executeUpdate();
    }

    /**
     * 获取实体对象
     *
     * @param id 主键
     * @return 实体对象
     */
    @Override
    public E getById(K id) {
        return this.hibernateTemplate.get(this.clazz, id);
    }

    /**
     * 获取实体对象,通过HQL方式
     *
     * @param id 主键值
     * @return 实体对象
     */
    @Override
    public E findById(K id) {
        return RepositoryUtils.findById(getSession(), clazz, idFieldNames, id);
    }

    /**
     * 获取总记录数
     *
     * @return 总数
     */
    @Override
    public long count() {
        Criteria criteria = RepositoryUtils.getCriteria(this.clazz, getSession());
        Object object = criteria.setProjection(Projections.rowCount()).uniqueResult();
        long totalCount = 0;
        if (object != null) {
            totalCount = Long.parseLong(object.toString());
        }
        return totalCount;
    }

    /**
     * 加载所有记录集合
     *
     * @return 所有记录集合
     */
    @Override
    public List<E> load() {
        return RepositoryUtils.getCriteria(this.clazz, getSession()).list();
    }

    /**
     * 分页加载记录集合
     *
     * @param index 起始行1，2，……
     * @param size  返回行数
     * @return 结果集合
     */
    @Override
    public List<E> load(int index, int size) {
        return RepositoryUtils.getCriteria(this.clazz, getSession(), index - 1, size).list();
    }

    /**
     * 分页加载记录集合
     *
     * @param paging 分页 & 排序
     * @return 结果集合
     */
    @Override
    public List<E> load(Paging paging) {
        // 查询总数
        paging.setRecordCount(Integer.parseInt(String.valueOf(this.count())));
        // 设置排序
        return RepositoryUtils.getCriteria(this.clazz, getSession(), paging).list();
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
    public List<E> query(String whereHQL, Map<String, Object> parameters, Paging paging) {
        /*String sqlHQL = String.format("from %s o where %s ", this.clazz.getSimpleName(), whereHQL);
        //分页HQL
        String orderByHQL = "";
        if (null != paging) {
            //查询记录总数
            Query queryCount = this.getSession().createQuery(String.format("select count(o) %s", sqlHQL));
            queryCount.setProperties(parameters);
            int recordCount = Integer.parseInt(String.valueOf(queryCount.uniqueResult()));
            paging.setRecordCount(recordCount);
            orderByHQL = RepositoryUtils.buildOrderBy(paging.getOrderBy());
        }
        Query query = this.getSession().createQuery(String.format("select o %s %s", sqlHQL, orderByHQL), this.clazz);
        query.setProperties(parameters);
        if (null != paging) {
            query.setFirstResult(paging.getRecordIndex()).setMaxResults(paging.getPageSize());
        }
        return query.list();*/
        return RepositoryUtils.query(getSession(), this.clazz, whereHQL, parameters, paging);
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
    public List<E> query(String whereHQL, Map<String, Object> parameters) {
        return this.query(whereHQL, parameters, null);
    }

    /**
     * HQL查询
     *
     * @param parameters 参数集合
     * @param paging     分页
     * @return 数据集合
     */
    @Override
    public List<E> query(Map<String, Object> parameters, Paging paging) {
        return this.query(RepositoryUtils.buildWhereHQL(parameters), parameters, paging);
    }

    /**
     * HQL查询
     *
     * @param parameters 参数集合
     * @return 数据集合
     */
    @Override
    public List<E> query(Map<String, Object> parameters) {
        return this.query(parameters, null);
    }

    /**
     * 分页查询所有记录
     *
     * @param paging 分页
     * @return 数据集合
     */
    @Override
    public List<E> query(Paging paging) {
        return this.load(paging);
    }

    @Override
    public <V> List<V> queryVO(String hql, Class<V> vClass) {
        return null;
    }

    @Override
    public <V> List<V> queryVO(String hql, Map<String, Object> parameters, Class<V> vClass) {
        return null;
    }

    @Override
    public <V> List<V> queryVO(String hql, Map<String, Object> parameters, Paging paging, Class<V> vClass) {
        return null;
    }


}