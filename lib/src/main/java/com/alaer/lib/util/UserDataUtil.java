package com.alaer.lib.util;

import com.alaer.lib.api.bean.TokenInfo;

public class UserDataUtil {
    private static final UserDataUtil instance = new UserDataUtil();

    public static UserDataUtil instance() {
        return instance;
    }

    private UserDataUtil() {
    }

    private TokenInfo tokenInfo;

    public String getTokenInfo() {
        if (tokenInfo == null)
            return "";
        return tokenInfo.type + " " + tokenInfo.token;
    }

    public void setTokenInfo(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

}