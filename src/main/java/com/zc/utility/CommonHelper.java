package com.zc.utility;

import com.alibaba.fastjson.JSONArray;

import java.math.BigDecimal;
import java.util.List;

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
}
