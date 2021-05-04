package com.alaer.lib.api;

import com.alaer.lib.api.bean.IncomeLastest;
import com.alaer.lib.api.bean.IncomeList;
import com.alaer.lib.api.bean.IncomeTrend;
import com.alaer.lib.api.bean.MinterList;
import com.alaer.lib.api.bean.MinterSeries;
import com.alaer.lib.api.bean.MinterStats;
import com.alaer.lib.api.bean.OrderList;
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
import likly.reverse.annotation.PUT;
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
     * 发送验证码
     */
    @FormBody
    @POST("/sms/send")
    Call<String> sendSms(@Part("countryCode") String countryCode, @Part("mobile") String mobile,
                         Callback<String> callback);

    /**
     * 注册
     */
    @FormBody
    @POST("/user/signup")
    Call<String> signup(@Part("mobile") String mobile, @Part("password") String password, @Part("smsCode") String smsCode,
                        Callback<String> callback);

    /**
     * 重置密码
     */
    @FormBody
    @POST("/user/password/forgot")
    Call<String> resetPwd(@Part("mobile") String mobile, @Part("password") String password, @Part("smsCode") String smsCode,
                          Callback<String> callback);

    /**
     * 修改密码
     */
    @FormBody
    @PUT("/user/password")
    Call<String> modifyPwd(@Query("mobile") String mobile, @Query("password") String password, @Query("smsCode") String smsCode,
                           Callback<String> callback);

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
    Call<IncomeTrend> incomeTrend(@Query("currency") String currency, @Query("zoom") String zoom, Callback<IncomeTrend> callback);

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
    Call<IncomeList> incomeList(@Query("currency") String currency, Callback<IncomeList> callback);

    /**
     * 我的矿机列表
     *
     * @param currency BTC/ETH
     */
    @GET("/user/workers/list")
    Call<MinterList> workerList(@Query("currency") String currency, Callback<MinterList> callback);

    /**
     * 我的矿机统计
     *
     * @param currency BTC/ETH
     */
    @GET("/user/workers/stats")
    Call<List<MinterStats>> workerStats(@Query("currency") String currency, Callback<List<MinterStats>> callback);

    /**
     * 我的订单列表
     *
     * @param currency BTC/ETH
     */
    @GET("/user/order/list")
    Call<OrderList> orderList(@Query("pageSize") int pageSize, Callback<OrderList> callback);

}
