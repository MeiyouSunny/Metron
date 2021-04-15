package com.alaer.lib.api;

import android.service.autofill.UserData;

import likly.reverse.Call;
import likly.reverse.annotation.BaseUrl;
import likly.reverse.annotation.CallExecuteListener;
import likly.reverse.annotation.FormBody;
import likly.reverse.annotation.GET;
import likly.reverse.annotation.POST;
import likly.reverse.annotation.Part;
import likly.reverse.annotation.Query;
import likly.reverse.annotation.ServiceInvokeListener;

/**
 * 请求定义
 */
@BaseUrl(AppConfig.BASE_URL)
@ServiceInvokeListener(OnApiServiceRequestListener.class)
@CallExecuteListener(ApiCallExecuteListener.class)
@SuppressWarnings("all")
public interface ApiService {

//    @GET("/query")
//    Call<String> request(@Query("username") String userName, Callback<String> callback);
//
//    @GET("/query")
//    Call<String> request(@QueryMap Map<String, String> params, Callback<String> callback);

    /**
     * 登录
     */
    @FormBody
    @POST("/user/signin")
    Call<UserData> login(@Part("phone") String phone, @Part("password") String password, @Part("validate") String validate,
                         @Part("captchaId") String captchaId, @Part("source") String source, @Part("diallingCode") String diallingCode,
                         Callback<UserData> callback);

    /**
     * 获取验证码
     */
    @GET("/user/vcode")
    Call<String> getVCode(@Query("diallingCode") String diallingCode, @Query("email") String email, @Query("captchaId") String captchaId,
                          @Query("validate") String validate, @Query("type") String type,
                          Callback<String> callback);

    /**
     * 注册
     */
    @FormBody
    @POST("/user/signup")
    Call<UserData> regist(@Part("phone") String phone, @Part("vcode") String vcode, @Part("password") String password, @Part("inviteCode") String inviteCode,
                          @Part("validate") String validate, @Part("captchaId") String captchaId, @Part("diallingCode") String diallingCode,
                          Callback<UserData> callback);

    /**
     * 重置密码
     */
    @FormBody
    @POST("/user/reset")
    Call<String> resetPwd(@Part("phone") String phone, @Part("phoneCode") String phoneCode, @Part("password") String password,
                          @Part("validate") String validate, @Part("captchaId") String captchaId, @Part("diallingCode") String diallingCode,
                          Callback<String> callback);

    /**
     * 获取用户信息
     */
    @GET("/mining/team/userinfo")
    Call<String> getUserInfo(@Query("uid") int uid, @Query("teamUuid") String teamUuid, @Query("token") String token,
                             Callback<String> callback);

}
