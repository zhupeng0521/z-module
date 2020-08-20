package com.module.zhupeng.data.repository;

import com.module.zhupeng.data.Paging;
import com.module.zhupeng.util.LogUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.query.Query;
import org.springframework.util.Assert;

import javax.persistence.Column;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * RepositoryUtils
 * <p>date : 2020-01-08</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
class RepositoryUtils {

    /**
     * 将实体对象转换为Map
     *
     * @param clazz  实体类型
     * @param entity 实体对象
     * @return Map结果集
     */
    static Map<String, Object> getEntityFieldMap(Class<?> clazz, Object entity) {
        Map<String, Object> filedMap = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                try {
                    field.setAccessible(true);
                    Object v = field.get(entity);
                    if (null == v) {
                        continue;
                    }
                    filedMap.put(field.getName(), v);
                } catch (IllegalAccessException e) {
                    LogUtils.getLogger().info("RepositoryUtils-getEntityFieldMap:", e);
                }
            }
        }
        return filedMap;
    }


    /**
     * 构建排序语句 order by o.xxxx desc,o.xxx.asc
     *
     * @param orderBy 排序属性与asc/desc, Key为属性,Value为asc/desc
     * @return 排序SQL
     */
    static String buildOrderBy(LinkedHashMap<String, String> orderBy) {
        /*StringBuilder sb = new StringBuilder();
        if (null != orderBy && !orderBy.isEmpty()) {
            sb.append(" order by ");
            for (Map.Entry<String, String> entry : orderBy.entrySet()) {
                sb.append(String.format(" o.%s %s,", entry.getKey(), entry.getValue()));
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();*/
        return buildOrderBy(orderBy, "o");
    }

    static String buildOrderBy(LinkedHashMap<String, String> orderBy, String prefixString) {
        StringBuilder sb = new StringBuilder();
        if (null != orderBy && !orderBy.isEmpty()) {
            String prefix = "";
            if (prefixString.length() > 0) {
                prefix = prefixString + ".";
            }
            sb.append(" order by ");
            for (Map.Entry<String, String> entry : orderBy.entrySet()) {
                sb.append(String.format(" %s%s %s,", prefix, entry.getKey(), entry.getValue()));
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 创建 whereHQL ：o.username=:username and o.password=:password
     *
     * @param parameters 参数
     * @return HQL：o.username=:username and o.password=:password
     */
    static String buildWhereHQL(Map<String, Object> parameters) {
        List<String> whereHQL = new ArrayList<>(8);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            whereHQL.add(String.format("o.%s=:%s", entry.getKey(), entry.getKey()));
        }
        return whereHQL.stream().collect(Collectors.joining(" and "));
    }

    /**
     * 获取 Criteria
     *
     * @param clazz   对象类型
     * @param session Session
     * @return 返回 Criteria
     */
    static Criteria getCriteria(Class<?> clazz, Session session) {
        return DetachedCriteria.forClass(clazz).getExecutableCriteria(session);
    }

    /**
     * 获取 Criteria
     *
     * @param clazz   对象类型
     * @param session Session
     * @param paging  分页及排序
     * @return 返回 Criteria
     */
    static Criteria getCriteria(Class<?> clazz, Session session, Paging paging) {
        Criteria criteria = getCriteria(clazz, session, paging.getOrderBy());
        //设置分页
        criteria.setFirstResult(paging.getRecordIndex());
        criteria.setMaxResults(paging.getPageSize());
        return criteria;
    }

    /**
     * 获取 Criteria
     *
     * @param clazz   对象类型
     * @param session Session
     * @param order   排序
     * @return 返回 Criteria
     */
    static Criteria getCriteria(Class<?> clazz, Session session, LinkedHashMap<String, String> order) {
        Criteria criteria = getCriteria(clazz, session);
        for (Map.Entry<String, String> entry : order.entrySet()) {
            if ("DESC".equals(entry.getValue().toUpperCase())) {
                criteria.addOrder(Order.desc(entry.getKey()));
            } else {
                criteria.addOrder(Order.asc(entry.getKey()));
            }
        }
        return criteria;
    }


    /**
     * 获取 Criteria
     *
     * @param clazz       对象类型
     * @param session     Session
     * @param firstResult firstResult
     * @param maxResult   maxResult
     * @return 返回 Criteria
     */
    static Criteria getCriteria(Class<?> clazz, Session session, int firstResult, int maxResult) {
        Criteria criteria = getCriteria(clazz, session);
        //设置分页
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResult);
        return criteria;
    }

    /**
     * 获取 DetachedCriteria
     *
     * @param clazz 对象类型
     * @return 返回 DetachedCriteria
     */
    static DetachedCriteria getDetachedCriteria(Class<?> clazz) {
        return DetachedCriteria.forClass(clazz);
    }


    /**
     * createQuery
     *
     * @param session Session
     * @param hql     HQL
     * @return Query
     */
    static Query createQuery(Session session, Class<?> clazz, String hql) {
        return session.createQuery(hql, clazz);
    }

    /**
     * createQuery
     *
     * @param session Session
     * @param hql     HQL
     * @return Query
     */
    static Query createQuery(Session session, Class<?> clazz, String hql, Map<String, Object> parameters) {
        return session.createQuery(hql, clazz).setProperties(parameters);
    }

    /**
     * createQuery
     *
     * @param session Session
     * @param hql     HQL
     * @return Query
     */
    static Query createUpdateQuery(Session session, String hql) {
        return session.createQuery(hql);
    }

    static Query createUpdateQuery(Session session, String hql, Map<String, Object> parameters) {
        return createUpdateQuery(session, hql).setProperties(parameters);
    }

    /**
     * createQuery
     *
     * @param session    Session
     * @param entityName 实体名称
     * @param setHQL     更新HQL
     * @param whereHQL   条件HQL
     * @return Query
     */
    static Query createUpdateQuery(Session session, String entityName, List<String> setHQL, List<String> whereHQL) {
        Assert.notEmpty(whereHQL, "更新HQL的条件不能为空！！！");
        Assert.notEmpty(setHQL, "更新字段不能为空！！！");

        String updateHQL = String.format("update %s o set %s where %s",
                entityName,
                setHQL.stream().collect(Collectors.joining(", ")),
                whereHQL.stream().collect(Collectors.joining(" and ")));

        return createUpdateQuery(session, updateHQL);
    }

    /**
     * createQuery
     *
     * @param session        Session
     * @param entityName     实体名称
     * @param idFieldNames   主键字段
     * @param parameters     参数
     * @param conditionNames 条件栏位
     * @return Query
     */
    static Query createUpdateQuery(Session session, String entityName, List<String> idFieldNames,
                                   Map<String, Object> parameters, String... conditionNames) {
        if (null == conditionNames || conditionNames.length == 0) {
            return createQueryNoCondition(session, entityName, idFieldNames, parameters);
        }

        List<String> whereFieldNames = Arrays.asList(conditionNames);

        List<String> setHQL = new ArrayList<>(16);
        List<String> whereHQL = new ArrayList<>(16);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            String hql = String.format("o.%s=:%s", entry.getKey(), entry.getKey());
            if (whereFieldNames.contains(entry.getKey())) {
                whereHQL.add(hql);
            } else {
                setHQL.add(hql);
            }
        }
        Assert.state(whereHQL.size() == whereFieldNames.size(), "条件栏位缺少赋值！！！");

        return createUpdateQuery(session, entityName, setHQL, whereHQL);

    }

    /**
     * createQuery
     *
     * @param session      Session
     * @param entityName   实体名称
     * @param idFieldNames 主键字段
     * @param parameters   参数
     * @return Query
     */
    private static Query createQueryNoCondition(Session session, String entityName, List<String> idFieldNames,
                                                Map<String, Object> parameters) {
        boolean isExist = idFieldNames.stream().allMatch(idName -> parameters.containsKey(idName));
        Assert.state(isExist, "主键值未设定！！");
        String[] conditionNames = new String[idFieldNames.size()];
        conditionNames = idFieldNames.toArray(conditionNames);
        return createUpdateQuery(session, entityName, idFieldNames, parameters, conditionNames);
    }

    static <E> E findById(Session session, Class<E> clazz, List<String> idFieldNames, Serializable id) {
        Assert.notEmpty(idFieldNames, String.format("实体类 %s 未设置主键@Id！！", clazz.getSimpleName()));
        String idName = idFieldNames.get(0);
        String selectHQL = String.format("from %s o where o.%s=:%s", clazz.getSimpleName(), idName, idName);
        List<E> list = session.createQuery(selectHQL, clazz)
                .setParameter(idName, id).list();
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    static <E> List<E> query(Session session, Class<E> clazz, String whereHQL, Map<String, Object> parameters, Paging paging) {

        String sqlHQL = String.format("from %s o where %s ", clazz.getSimpleName(), whereHQL);
        //分页HQL
        String orderByHQL = "";
        if (null != paging) {
            //查询记录总数
            Query queryCount = session.createQuery(String.format("select count(o) %s", sqlHQL));
            queryCount.setProperties(parameters);
            int recordCount = Integer.parseInt(String.valueOf(queryCount.uniqueResult()));
            paging.setRecordCount(recordCount);
            orderByHQL = buildOrderBy(paging.getOrderBy());
        }
        Query<E> query = session.createQuery(String.format("select o %s %s", sqlHQL, orderByHQL), clazz);
        query.setProperties(parameters);
        if (null != paging) {
            query.setFirstResult(paging.getRecordIndex()).setMaxResults(paging.getPageSize());
        }
        return query.getResultList();
    }
}
