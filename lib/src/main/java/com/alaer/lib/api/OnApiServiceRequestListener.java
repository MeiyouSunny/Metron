package com.alaer.lib.api;

import com.alaer.lib.util.UserDataUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import likly.reverse.OnServiceInvokeListener;
import likly.reverse.RequestHolder;

public class OnApiServiceRequestListener implements OnServiceInvokeListener {

    @Override
    public RequestHolder onServiceInvoke(Method method, RequestHolder requestHolder) {

        Map<String, String> params = requestHolder.headers();
        if (params == null)
            params = new HashMap<>();

        // 添加Token
        params.put("Authorization", UserDataUtil.instance().getTokenInfo());

        requestHolder.headers(params);

        return requestHolder;
    }

}
