package com.metron.coin.ui.settings;

import android.view.View;

import com.metron.coin.R;
import com.metron.coin.base.BaseTitleActivity;
import com.metron.coin.databinding.ActivityAboutBinding;
import com.metron.coin.ui.webpage.WebPageActivity;

/**
 * 关于
 */
public class AboutActivity extends BaseTitleActivity<ActivityAboutBinding> {

    @Override
    protected String title() {
        return "关于";
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            // TODO
            case R.id.userAgreement:
                WebPageActivity.start(this, "https://app.tokensky.cn/articles/zh_CN/agreement-zh_CN.html", "用户协议");
                break;
            case R.id.privacyPolicy:
                WebPageActivity.start(this, "https://app.tokensky.cn/articles/zh_CN/agreement-zh_CN.html", "隐私政策");
                break;
        }
    }

}