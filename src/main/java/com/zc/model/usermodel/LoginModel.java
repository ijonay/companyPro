/** 
* @Title LoginModel.java
* @author 张镇强/zhangzq@heptax.com
* @date：2016年4月27日 
* Copyright 2016 知藏. All right reserved.
*  [描述]
*/
package com.zc.model.usermodel;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author 张镇强 / zhangzq@heptax.com
 *
 */
public class LoginModel {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
