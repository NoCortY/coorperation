package com.objectspace.coorperation.config;

public final class ConstantValue {

    /**
     * 用户：超级管理员
     */
    public final static String SUPER_ADMIN = "1";
    /**
     * 用户：普通用户
     */
    public final static String SIMPLE_USER = "0";

    /**
     * 账号状态：正常
     */
    public final static boolean NORMAL_ACCOUNT = true;
    /**
     * 账号状态：被封禁
     */
    public final static boolean BINDED_ACCOUNT = false;
    /**
     * 密码加密类型
     */
    public final static String PASSWORD_ENCRYPTION_TYPE = "md5";

    /**
     * 反复加密次数
     */
    public final static Integer PASSWORD_ENCRYPTION_TIME = 3;

    /**
     * 后端给前端是否成功的标识
     */
    public final static String TO_FRONTEND_FLAG = "successFlag";

    /**
     * 后端给前端的额外信息
     */
    public final static String TO_FRONTEND_MASSAGE = "message";
}
