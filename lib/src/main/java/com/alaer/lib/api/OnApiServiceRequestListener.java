package com.alaer.lib.api;

import java.lang.reflect.Method;

import likly.reverse.OnServiceInvokeListener;
import likly.reverse.RequestHolder;

public class OnApiServiceRequestListener implements OnServiceInvokeListener {

    @Override
    public RequestHolder onServiceInvoke(Method method, RequestHolder requestHolder) {
        return requestHolder;
    }

}
