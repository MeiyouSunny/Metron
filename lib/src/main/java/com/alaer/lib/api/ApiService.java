package com.alaer.lib.api;

import com.alaer.lib.api.bean.TokenInfo;
import com.alaer.lib.api.bean.UserInfo;

import likly.reverse.Call;
import likly.reverse.annotation.BaseUrl;
import likly.reverse.annotation.CallExecuteListener;
import likly.reverse.annotation.FormBody;
import likly.reverse.annotation.GET;
import likly.reverse.annotation.POST;
import likly.reverse.annotation.Part;
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
    @POST("/auth/login")
    Call<TokenInfo> login(@Part("username") String username, @Part("password") String password,
                          Callback<TokenInfo> callback);

    /**
     * 获取用户信息
     */
    @GET("/user/profile")
    Call<UserInfo> getUserInfo(Callback<UserInfo> callback);

}
