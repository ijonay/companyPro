package com.zc.utility;

import com.zc.bean.Users;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by 张镇强 on 2016/10/14 18:09.
 */
public interface PasswordHelper {
    RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    String algrithmName = "MD5";
    int hashInterations = 5;

    static void encryptPassword(Users user) {
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(
                algrithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getUserName() + user.getSalt()),
                hashInterations).toHex();

        user.setPassword(newPassword);
    }

    static boolean checkUserPassword(Users user,String password) {
        String newPassword = new SimpleHash(
                algrithmName,
                password,
                ByteSource.Util.bytes(user.getUserName() + user.getSalt()),
                hashInterations).toHex();
        return StringUtils.equals( newPassword, user.getPassword() );
    }
}
