/**
 * @title Param.java
 * @author zhuzhzh/zhuzz@heptax.com
 * @date：2016年3月10日 下午12:41:09
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.utility;

import java.util.Arrays;

public class ParamHelper {



    /**
     * 检查非法Integer参数 有非法值就返回true
     *
     * @param checkInts
     * @return
     * @创建人 zhuzhzh @创建时间 2016年3月30日 下午4:37:27
     */
    public static boolean isInValidInt(Integer... checkInts) {
        if (checkInts == null) {
            return true;
        }
        return Arrays.stream(checkInts).anyMatch(p -> p == null || p < 1);
    }

    public static boolean isValidInt(Integer... checkInts) {
        return !isInValidInt(checkInts);
    }

    /**
     * 检查非法String参数 有非法值就返回true
     *
     * @param checkStrs
     * @return
     * @创建人 zhuzhzh @创建时间 2016年5月24日 上午11:34:45
     */
    public static boolean isInValidStr(String... checkStrs) {
        if (checkStrs == null) {
            return true;
        }
        return Arrays.stream(checkStrs)
                .anyMatch(p -> p == null || "".equals(p));
    }

    public static boolean isValidStr(String... checkStrs) {
        return !isInValidStr(checkStrs);
    }

    /**
     * 字符串非法检测
     *
     * @param errorMsg
     * @param checkStrs
     * @创建人 xyzhuzhou @创建时间 2016年7月14日 下午6:47:48
     */
    public static void lllegalStr(String errorMsg, String checkStrs) {

        if (isInValidStr(checkStrs)) {

            throw new RuntimeException(errorMsg + "不能为空！");

        }

    }

    public static void lllegalObj(String errorMsg, Object obj) {

        if (obj == null) {

            throw new RuntimeException(errorMsg + "不能为空！");

        }
    }

    /**
     * Integer 参数比较
     *
     * @param arg0
     * @param arg1
     * @return
     * @创建人 zhuzhzh @创建时间 2016年5月18日 下午2:50:27
     */
    public static boolean compare(Integer arg0, Integer arg1) {
        if (arg0 == null && arg1 == null) {
            return true;
        } else if (arg0 == null || arg1 == null) {
            return false;
        } else {
            return arg0.equals(arg1);
        }
    }

    /**
     * 默认值判断
     *
     * @param t1 输入值
     * @param t2 默认值
     * @return
     * @创建人 zhuzhzh @创建时间 2016年6月7日 上午11:26:54
     */
    public static <T> T defaultVal(T t1, T t2, T... t3) {

        if (t1 == null) {
            if (t2 != null) {
                return t2;
            }
        }

        if (t3 != null && t3.length > 0) {
            for (T t : t3) {
                if (t != null) {
                    return t;
                }
            }
        }
        return t1;
    }

}
