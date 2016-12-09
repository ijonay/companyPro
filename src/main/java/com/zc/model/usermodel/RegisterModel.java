package com.zc.model.usermodel;

import com.zc.bean.Users;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * Created by 张镇强 on 2016/10/21 15:43.
 */

public class RegisterModel {


    private String company;
    @NotBlank(message = "用户名不能为空")
    @Size(max = 20)
    private String userName;

    @Size(max = 20)
    private String nickName;
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 25)
    private String password;

    @NotBlank(message = "确认密码不能为空")
    @Size(min = 8, max = 25)
    private String confirmPassword;


    public Users toEntity() {
        Users result = new Users();
        result.setUserName(this.userName);
        result.setPassword(this.password);
        result.setNickName(this.nickName);
        result.setCompany(company);

        return result;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


}
