/** 
 * @title EnumHelper.java
 * @author zhuzhzh/zhuzz@heptax.com
 * @date：2016年3月10日 下午4:04:01 
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.utility;

public class EnumHelper {

    /**
     * 根据枚举名称获取枚举
     * 
     * @param type
     * @param name
     * @return
     * @创建人 zhuzhzh @创建时间 2016年4月7日 下午4:19:16
     */
    public static <T extends Enum<T>> T getEnumByName(Class<T> type,
            String name) {

        for (T status : type.getEnumConstants()) {

            if (status.name().toLowerCase().equals(name.toLowerCase())) {
                return status;
            }

        }

        return null;
    }

    /**
     * 根据值或序号获取枚举 枚举对象必须包getValue方法
     * 
     * @param type
     * @param val
     * @return
     * @创建人 zhuzhzh @创建时间 2016年4月7日 下午4:16:38
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>, F> T getEnumByValOrOrdinal(Class<T> type,
            F val) {

        F obj = null;

        for (T item : type.getEnumConstants()) {

            obj = (F) ReflectHelper.getValueByFieldName(item, "value");

            if (obj == null) {

                try {
                    obj = (F) (Integer) item.ordinal();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }

            if (obj.equals(val)) {
                return item;
            }
        }

        return null;
    }

}
