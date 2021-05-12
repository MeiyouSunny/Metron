package com.metron.xiaoming.base.repeatview;

import likly.view.repeat.FooterAdapter;

public class LoadMoreFooterAdapter extends FooterAdapter<LoadMoreFooterHolder> {

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public LoadMoreFooterHolder onCreateHolder(int holderType) {
        return new LoadMoreFooterHolder();
    }

    @Override
    public void onBindHolder(LoadMoreFooterHolder holder, int position) {
    }

}
