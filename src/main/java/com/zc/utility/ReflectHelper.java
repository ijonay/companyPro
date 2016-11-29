/** 
* @title ReflectHelper.java
* @author zhuzhzh/zhuzz@heptax.com
* @date：2016年6月6日 下午7:03:48 
* Copyright 2016 知藏. All right reserved.
* 反射帮助类
*/
package com.zc.utility;

import java.lang.reflect.Field;

public class ReflectHelper {

    /**
     * 获取对象属性成员通过属性name
     * 
     * @param obj
     * @param fieldName
     * @return
     * @创建人 zhuzhzh @创建时间 2016年6月6日 下午6:47:37
     */
    public static Field getFieldByFieldName(Object obj, String fieldName) {
        for (Class<?> supperClass = obj
                .getClass(); supperClass != Object.class; supperClass = supperClass
                        .getSuperclass()) {
            try {
                return supperClass.getDeclaredField(fieldName);
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 获取对象属性value通过属性名称
     * 
     * @param obj
     * @param fieldName
     * @return
     * @创建人 zhuzhzh @创建时间 2016年6月6日 下午6:58:43
     */
    public static Object getValueByFieldName(Object obj, String fieldName) {

        Field field = getFieldByFieldName(obj, fieldName);

        return getValueByField(obj, field);

    }

    /**
     * 获取对象属性value通过属性对象
     * 
     * @param obj
     * @param field
     * @return
     * @创建人 xyzhuzhou @创建时间 2016年7月26日 下午4:32:05
     */
    public static Object getValueByField(Object obj, Field field) {
        Object value = null;
        if (field != null) {
            try {
                if (field.isAccessible()) {
                    value = field.get(obj);
                } else {
                    field.setAccessible(true);
                    value = field.get(obj);
                    field.setAccessible(false);
                }
            } catch (Exception e) {
            }
        }
        return value;
    }

    /**
     * 设置对象属性value通过属性名称
     * 
     * @param obj
     * @param fieldName
     * @param value
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @创建人 zhuzhzh @创建时间 2016年6月6日 下午7:02:36
     */
    public static void setValueByFieldName(Object obj, String fieldName,
            Object value)
            throws IllegalArgumentException, IllegalAccessException {
        Field field = getFieldByFieldName(obj, fieldName);
        if (field.isAccessible()) {
            field.set(obj, value);
        } else {
            field.setAccessible(true);
            field.set(obj, value);
            field.setAccessible(false);
        }
    }

}
