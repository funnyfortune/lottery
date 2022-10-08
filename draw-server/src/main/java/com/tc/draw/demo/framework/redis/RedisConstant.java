package com.tc.draw.demo.framework.redis;

public class RedisConstant {

    public static final String DRAW_LOTTERY_RES = "draw:lottery:res";

    private RedisConstant() {
    }

    /**
     * KEY
     */

    public final static String DEPT = "draw:cache:dept";



    /**
     * 验证码缓存，时效5分钟
     */
    public final static String CACHE_ADMIN_CAPTCHA = "draw:cache:captcha";

    /**
     * 验证码错误的次数
     */
    public final static String CACHE_ADMIN_ERR_COUNT = "draw:cache:err:count";

}
