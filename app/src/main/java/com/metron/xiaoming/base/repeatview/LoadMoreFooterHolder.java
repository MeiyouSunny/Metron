package com.metron.xiaoming.base.repeatview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.metron.xiaoming.R;

import likly.view.repeat.FooterHolder;

public class LoadMoreFooterHolder extends FooterHolder<Void> {

    @Override
    public View onCreateView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_load_more, parent, false);
    }

}
