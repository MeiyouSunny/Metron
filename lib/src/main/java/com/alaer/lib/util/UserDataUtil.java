package com.alaer.lib.util;

import com.alaer.lib.api.bean.TokenInfo;
import com.alaer.lib.api.bean.UserInfo;

import likly.dollar.$;

public class UserDataUtil {
    private static final UserDataUtil instance = new UserDataUtil();

    public static UserDataUtil instance() {
        return instance;
    }

    private UserDataUtil() {
    }

    private TokenInfo tokenInfo;
    private UserInfo userInfo;

    public String getTokenInfo() {
        if (tokenInfo == null)
            return "";
        return tokenInfo.type + " " + tokenInfo.token;
    }

    public void setTokenInfo(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

//    // 是否渠道专员
//    public boolean isChannelUser() {
//        return userInfo != null && userInfo.role == 2;
//    }

    public static void setIsChannelUser(boolean isChannelUser) {
        $.config().putBoolean("channelUser", isChannelUser);
    }

    public static boolean isChannelUser() {
        return $.config().getBoolean("channelUser", false);
    }

}