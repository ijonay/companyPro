package com.zc.utility;

import org.apache.commons.lang3.StringUtils;

/**
 * 对org.apache.commons.lang3.StringUtils库的封装
 * Created by 张镇强 on 2016/10/14 16:54.
 */
public interface StringHelper {
    static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }
}
