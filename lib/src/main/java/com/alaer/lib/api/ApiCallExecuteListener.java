package com.alaer.lib.api;

import org.json.JSONException;
import org.json.JSONObject;

import likly.dollar.$;
import likly.reverse.JsonParseException;
import likly.reverse.OnCallExecuteListener;
import likly.reverse.Response;

public class ApiCallExecuteListener implements OnCallExecuteListener {
    @Override
    public void onStart() {
    }

    @Override
    public void onResponse(Response response) {
    }

    @Override
    public void onParseResponseStart() {
    }

    @Override
    public String onParseJson(String s) throws JsonParseException {
        try {
            return parseJsonData(s);
        } catch (JSONException e) {
            $.debug().tag("ERROR:").e(e.toString());
            throw new JsonParseException(e);
        }
    }

    @Override
    public void onResponseResult(Object o) {
    }

    @Override
    public void onParseResponseFinish() {
    }

    @Override
    public void onFinish() {
    }

    @Override
    public void onCancel() {
    }

    /**
     * 解析返回数据
     */
    private String parseJsonData(String json) throws JSONException {
        $.debug().e("Response: " + json);
        JSONObject jsonObject = new JSONObject(json);
        final boolean result = jsonObject.optBoolean("success");
        if (!result) {
            final String errorMsg = jsonObject.optString("message");
            final int errorCode = jsonObject.optInt("code");
            throw new ServiceError(errorCode, errorMsg);
        }
        final String data = jsonObject.optString("data");

        return data;
    }

}
