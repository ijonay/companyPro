/**
 * @title CustomRealm.java
 * @author zhanbq/战博奇
 * @date：2016年2月16日 下午4:51:53
 * Copyright 2016 知藏. All right reserved.
 */
package com.zc.shiro;

import com.zc.bean.Users;
import com.zc.model.usermodel.UserSessionModel;
import com.zc.service.UsersService;
import com.zc.utility.Constants;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomRealm extends AuthorizingRealm {

    private static final Logger logger = Logger.getLogger(CustomRealm.class);

    @Resource
    private UsersService userService;

    /*
     *
     * bq zhanbq
     * 
     * @see
     * org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache
     * .shiro.subject.PrincipalCollection) 2016年2月16日
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {

        return null;
    }

    /*
     *
     * bq zhanbq
     * 
     * @see
     * org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.
     * apache.shiro.authc.AuthenticationToken) 2016年2月16日
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken authToken = (UsernamePasswordToken) token;

        try {
            Users users = userService.getByUserName(authToken.getUsername());
            if (users != null) {
                String dbPassword = users.getPassword();
                String dbZcSalt = users.getSalt();
                ByteSource dbsalt = ByteSource.Util.bytes(users.getUserName() + dbZcSalt);
                SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                        users.getUserName(), dbPassword, dbsalt, getName());
                Subject subject = SecurityUtils.getSubject();
                subject.getSession().setAttribute(Constants.CURRENT_USER, new UserSessionModel(users));

                return simpleAuthenticationInfo;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @return String
     */

    private String getZc_username() {

        return null;
    }

    // @PostConstruct
    public void setCredentialMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();

        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(5);

        setCredentialsMatcher(credentialsMatcher);
    }

}
