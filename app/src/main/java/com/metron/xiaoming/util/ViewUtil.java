package com.metron.xiaoming.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.alaer.lib.api.AppConfig;
import com.bumptech.glide.Glide;

import java.util.List;

import likly.view.repeat.RepeatView;

public class ViewUtil {

    public static void gotoActivity(Context context, Class<? extends Activity> activityDes) {
        context.startActivity(new Intent(context, activityDes));
    }

    public static void gotoActivity(Context context, Class<? extends Activity> activityDes, String key, String value) {
        Intent intent = new Intent(context, activityDes);
        intent.putExtra(key, value);
        context.startActivity(intent);
    }

    public static void gotoActivity(Context context, Class<? extends Activity> activityDes, String key, int value) {
        Intent intent = new Intent(context, activityDes);
        intent.putExtra(key, value);
        context.startActivity(intent);
    }

    public static String getText(TextView textView) {
        if (textView == null)
            return "";
        return textView.getText().toString().trim();
    }

    public static void showImage(Context context, ImageView imageView, String imageUrl) {
        if (imageView == null || TextUtils.isEmpty(imageUrl))
            return;
        Glide.with(context)
                .load(AppConfig.HOST + imageUrl)
                .dontAnimate()
                .into(imageView);
    }

    public static void showListData(RepeatView repeatView, List data) {
        if (CollectionUtils.isEmpty(data))
            repeatView.layoutAdapterManager().showEmptyView();
        else
            repeatView.viewManager().bind(data);
    }

}
