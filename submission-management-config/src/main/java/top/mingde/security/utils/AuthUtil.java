package top.mingde.security.utils;

import com.alibaba.fastjson.JSONObject;
import top.mingde.tool.core.interceptor.AuthContextHolder;

import java.util.Optional;

public class AuthUtil {

    /**
     * 获取上下文中的用户名
     * @return
     */
    public static String getUsername() {
        return Optional.ofNullable(getUser())
                .orElse(new JSONObject()).getString("username");
    }

    /**
     * 获得上下文用户信息
     * @return
     */
    public static JSONObject getUser(){
        return  AuthContextHolder.getInstance().authUser();
    }

    /**
     * 获取用户ID
     * @return
     */
    public static Long getUserId() {
        return Optional.ofNullable(getUser())
                .orElse(new JSONObject()).getLong("id");
    }

    /**
     * 获取租户ID
     * @return
     */
    public static Long getTenantId() {
        return Optional.ofNullable(getUser())
                .orElse(new JSONObject()).getLong("tenantId");
    }

    /**
     * 获得用户管理员级别
     * @return
     */
    public static boolean getSuperFlag(){
        return Optional.ofNullable(getUser())
                .orElse(new JSONObject()).getBoolean("superFlag");
    }

    /**
     * 获取用户联系方式
     * @return
     */
    public static String getPhone(){
        return Optional.ofNullable(getUser())
                .orElse(new JSONObject()).getString("phone");
    }

    /**
     * 获取用户名称
     * @return
     */
    public static String getName(){
        return Optional.ofNullable(getUser())
                .orElse(new JSONObject()).getString("name");
    }


    /**
     * 是否管理员
     * @return
     */
    public static boolean isSuper(){
        return Optional.ofNullable(getUser())
                .orElse(new JSONObject()).getBoolean("admin");
    }

    /**
     * 是否密码初始化
     * @return
     */
    public static boolean isInit(){
        return Optional.ofNullable(getUser())
                .orElse(new JSONObject()).getBoolean("initStatus");
    }


}
