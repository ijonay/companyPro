package com.zc.utility;

import com.alibaba.fastjson.JSONArray;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;
import com.zc.model.usermodel.UserSessionModel;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 张镇强 on 2016/8/16 18:08.
 */
public class CommonHelper {

    private final static Logger logger = LoggerFactory.getLogger(CommonHelper.class);

    /**
     * 获取当前用户Id，如果获取不到，返回-1
     *
     * @return 当前用户Id，如果获取失败，返回-1
     * @author: 张镇强/zhangzq@heptax.com
     */
    public static int getCurrentUserId() {
        try {
            UserSessionModel currentUser = (UserSessionModel) SecurityUtils.getSubject().getSession()
                    .getAttribute(Constants.CURRENT_USER);
            if (currentUser != null) {
                return currentUser.getUserId();
            } else {
                logger.error("getCurrentUserId:当前用户为空");
            }
        } catch (Exception e) {
            logger.error("获取当前用户Id出现异常:{}", e.getStackTrace());
        }

        return -1;
    }

    /**
     * 获取当前用户登录名，如果获取不到，返回null
     *
     * @return 当前用户Id，如果获取失败，返回-1
     * @author: 张镇强/zhangzq@heptax.com
     */
    public static String getCurrentUserLoginName() {
        try {
            UserSessionModel currentUser = (UserSessionModel) SecurityUtils.getSubject().getSession()
                    .getAttribute(Constants.CURRENT_USER);
            if (currentUser != null) {
                return currentUser.getUserName();
            } else {
                logger.error("getCurrentUserLoginName:当前用户为空");
            }

        } catch (Exception e) {
            logger.error("获取当前用户登录名称出现异常:{}", e.getStackTrace());
        }

        return null;
    }

    public static int compare(float left, float right) {
        float res = left - right;
        if (res > 0) {
            return 1;
        } else if (res < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    public static float[] stringToFloatArray(String str) {
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
    public static boolean isAPIRequest(String requestPath) {
        String patternStr = "/{1,2}api/.+";
        Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(requestPath).find();
    }
}
