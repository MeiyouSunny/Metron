package com.alaer.lib.api;

import com.alaer.lib.api.bean.IncomeLastest;
import com.alaer.lib.api.bean.IncomeList;
import com.alaer.lib.api.bean.MinterSeries;
import com.alaer.lib.api.bean.PollNewInfo;
import com.alaer.lib.api.bean.TokenInfo;
import com.alaer.lib.api.bean.UserInfo;
import com.alaer.lib.api.bean.WithdrawStats;

import java.util.List;

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
    @POST("/auth/login")
    Call<TokenInfo> login(@Part("username") String username, @Part("password") String password,
                          Callback<TokenInfo> callback);

    /**
     * 获取用户信息
     */
    @GET("/user/profile")
    Call<UserInfo> getUserInfo(Callback<UserInfo> callback);

    /**
     * 获取矿机系列及其型号列表
     */
    @GET("/miner/series")
    Call<List<MinterSeries>> getMinterSeries(Callback<List<MinterSeries>> callback);

    /**
     * 获取矿池货币最新统计信息
     */
    @GET("/poll/stats")
    Call<PollNewInfo> pollNewInfo(Callback<PollNewInfo> callback);

    /**
     * 收益统计图
     *
     * @param currency BTC/ETH
     * @param zoom     h: 时时(最近24小时内)，d: 每日（最近7天）
     */
    @GET("/user/income/trend")
    Call<String> incomeTrend(@Query("currency") String currency, @Query("zoom") String zoom, Callback<String> callback);

    /**
     * 用户最新收益
     */
    @GET("/user/income/latest")
    Call<IncomeLastest> incomeLatest(@Query("currency") String currency, Callback<IncomeLastest> callback);

    /**
     * 提币统计
     *
     * @param type BTC/ETH
     */
    @GET("/user/withdraw/stats")
    Call<WithdrawStats> withdrawStats(@Query("currency") String currency, Callback<WithdrawStats> callback);

    /**
     * 收益列表
     *
     * @param type BTC/ETH
     */
    @GET("/user/income/list")
    Call<IncomeList> incomeList(String type, Callback<IncomeList> callback);

}
