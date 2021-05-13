package com.metron.xiaoming.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

    // 2021-04-23T00:00:00.000Z --> yyyy年MM月dd日 HH:mm
    public String formatTimeString(String time) {
        try {
            time = time.replace("T", " ");
            time = time.replace("Z", "");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date date = format.parse(time);
            format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return time;
    }

    public static String parseStringToStar(String content) {
        if (TextUtils.isEmpty(content))
            return "";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < content.length(); i++) {
            builder.append("*");
        }

        return builder.toString();
    }

    public static String getVersionName(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}
