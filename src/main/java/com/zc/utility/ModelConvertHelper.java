/**
 * @title ModelConvertUtil.java
 * @author zhuzhzh/zhuzz@heptax.com
 * @date：2016年3月18日 下午5:04:11
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.utility;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModelConvertHelper {

    /**
     * 批量实体转化方法 将T类型Model值转化到F类型Model中
     *
     * @param tList 需要转化的实体集合
     * @param f     F实体 （不是集合）
     * @return
     * @创建人 zhuzhzh @创建时间 2016年3月18日 下午5:52:02
     */
    public static <T, F> List<F> ConvertModelCollection(List<T> tList, F f) {
        if (tList.size() < 1)
            return null;

        List<F> fList = new ArrayList<>();

        tList.forEach(t -> fList.add(ConvertModel(t, f)));

        return fList;
    }


    /**
     * 实体转化方法 将T类型Model值转化到F类型Model中
     *
     * @param t 需要转化的数据
     * @param f 转化后的实体类型
     * @return
     * @创建人 zhuzhzh @创建时间 2016年4月6日 下午4:33:39
     */
    public static <T, F> F ConvertModel(T t, F f) {

        if (Objects.isNull(t))
            return null;

        if (Objects.isNull(f))
            return null;

        Class<?> cls = t.getClass();

        try {

            f = (F) f.getClass().newInstance();

        } catch (InstantiationException | IllegalAccessException e1) {

        }
        ConvertField(t, f, cls);
        ConvertSetOrGet(t, f, cls);
        return f;
    }

    private static <F, T> void ConvertField(T t, F f, Class<?> cls) {
        for (Field field : f.getClass().getFields()) {
            Field curField = null;
            try {

                curField = cls.getField(field.getName());

                Object val = curField.get(t);

                field.set(f, val);

            } catch (Exception e) {

            }
        }
    }

    private static <F, T> void ConvertSetOrGet(T t, F f, Class<?> cls) {


        for (Method method : f.getClass().getDeclaredMethods()) {
            String methodName = method.getName();
            if (methodName.indexOf("set") == 0) {
                String name = methodName.substring(3, methodName.length());
                Method curGet = null;
                try {

                    curGet = cls.getDeclaredMethod("get" + name);

                    method.invoke(f, curGet.invoke(t));

                } catch (Exception e) {

                }

            }
        }

    }
}
