package com.alaer.lib.util;

import com.alaer.lib.api.bean.TokenInfo;
import com.alaer.lib.api.bean.UserInfo;

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

}