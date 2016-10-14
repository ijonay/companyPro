package com.zc.utility;

import com.alibaba.fastjson.JSONArray;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by 张镇强 on 2016/8/16 18:08.
 */
public interface CommonHelper {
    static int compare(float left, float right) {
        float res = left - right;
        if (res > 0) {
            return 1;
        } else if (res < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    static float[] stringToFloatArray(String str) {
        try {
            List<BigDecimal> a = (List<BigDecimal>) JSONArray.parse(str);
            float[] res = new float[a.size()];
            for (int i = 0; i < a.size(); i++) {
                res[i] = a.get(i).floatValue();
            }
            return res;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 根据路径判断请求是不是API请求
     *
     * @param requestPath
     * @return
     * @author: 张镇强/zhangzq@heptax.com
     */
    static boolean isAPIRequest(String requestPath) {
        String patternStr = "/{1,2}api/.+";
        Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(requestPath).find();
    }
}
