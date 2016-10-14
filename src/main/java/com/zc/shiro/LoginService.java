/** 
* @Title LoginService.java
* @author 张镇强/zhangzq@heptax.com
* @date：2016年4月27日 
* Copyright 2016 知藏. All right reserved.
*  [描述]
*/
package com.zc.shiro;

import com.zc.model.usermodel.LoginStatus;

/**
 * 登录接口
 * 
 * @author 张镇强 / zhangzq@heptax.com
 *
 */
public interface LoginService {
    /**
     * 获取登录状态
     * 
     * @author: 张镇强/zhangzq@heptax.com
     * @return
     * @throws Exception
     */
    LoginStatus getStatus();

    LoginStatus login(String username, String password);
}
