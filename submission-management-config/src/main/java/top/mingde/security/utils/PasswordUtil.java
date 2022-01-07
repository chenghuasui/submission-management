package top.mingde.security.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 用户密码加密工具
 */
public class PasswordUtil {

    /**
     * 获取加密方式
     * @return
     */
    public static PasswordEncoder get(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 加密密码
     * @param password
     * @return
     */
    public static String entrypt(String password){
        return get().encode(password);
    }

    /**
     * 密码匹配
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    public static boolean matches(String rawPassword, String encodedPassword){
        return get().matches(rawPassword, encodedPassword);
    }
}
