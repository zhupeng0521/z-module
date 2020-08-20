package com.module.zhupeng.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * GenericsUtils
 * <p>date : 2019-09-19</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class GenericsUtils {
    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     * 如public BookManager extends GenricManager<Book>
     *
     * @param clazz The class to introspect
     * @return the first generic declaration, or <code>Object.class</code> if cannot be determined
     */
    public static Class getSuperClassGenericType(Class clazz) {
        return getSuperClassGenericType(clazz, 0);
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     * 如public BookManager extends GenricManager<Book>
     *
     * @param clazz The class to introspect
     * @return the first generic declaration, or <code>null</code> if cannot be determined
     */
    public static Class getSuperClassGenericType(Class clazz, boolean isNull) {
        return getSuperClassGenericType(clazz, 0, isNull);
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     * 如public BookManager extends GenricManager<Book>
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     */
    public static Class getSuperClassGenericType(Class clazz, int index) throws IndexOutOfBoundsException {
        return getSuperClassGenericType(clazz, index, true);
    }


    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     * 如public BookManager extends GenricManager<Book>
     *
     * @param clazz  clazz The class to introspect
     * @param index  the Index of the generic declaration,start from 0.
     * @param isNull false 未找到返回Object.class, true，则返回NULL
     */
    public static Class getSuperClassGenericType(Class clazz, int index, boolean isNull) throws IndexOutOfBoundsException {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            if (isNull) {
                return null;
            }
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            if (isNull) {
                return null;
            }
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            if (isNull) {
                return null;
            }
            return Object.class;
        }
        return (Class) params[index];
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic declaration,start from 0.
     * @return 未找到返回 null
     * @throws IndexOutOfBoundsException
     */
    public static Class getParameterType(Class clazz, int index) throws IndexOutOfBoundsException {

        Type[] params = clazz.getTypeParameters();

        if (index >= params.length || index < 0) {
            return null;
        }
        if (!(params[index] instanceof Class)) {
            return null;
        }
        return (Class) params[index];
    }


}
