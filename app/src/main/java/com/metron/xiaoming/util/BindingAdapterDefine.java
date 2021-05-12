package com.metron.xiaoming.util;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class BindingAdapterDefine {

    @BindingAdapter({"drawableRes"})
    public static void getTransImageView(View view, int res) {
        view.setBackgroundResource(res);
    }

}