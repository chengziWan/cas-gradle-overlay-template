package com.suresec.util;

import org.apache.shiro.crypto.hash.ConfigurableHashService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;

/**
 * @author Wan CC
 * @create 2020-07-31 15:29
 * @Description: 密码加盐
 */
public class PasswordSaltUtil
{
    /**
     * 静态盐值
     */
    private static final String STATIC_SALT = ".";
    /**
     * 对处理盐值后的算法
     */
    private static final  String ALGORITHM_NAME = "MD5";

    /**
     * 对登录密码盐值处理
     * @param username 账号
     * @param password 密码
     * @throws Exception
     */
    public static String encryPassword(String username,String password) throws Exception {
        ConfigurableHashService hashService = new DefaultHashService();
        // 静态盐值
        hashService.setPrivateSalt(ByteSource.Util.bytes(STATIC_SALT));
        hashService.setHashAlgorithmName(ALGORITHM_NAME);
        // 加密迭代次数
        hashService.setHashIterations(2);
        HashRequest request = new HashRequest.Builder()
                .setSalt(username)
                .setSource(password)
                .build();
        String res =  hashService.computeHash(request).toHex();
        System.out.println(res);
        return  res;
    }
    public static void main(String[] args) {
        try {
            PasswordSaltUtil.encryPassword("test","123456");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
