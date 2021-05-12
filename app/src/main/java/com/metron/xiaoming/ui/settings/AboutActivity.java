package com.metron.xiaoming.ui.settings;

import android.view.View;

import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseTitleActivity;
import com.metron.xiaoming.databinding.ActivityAboutBinding;
import com.metron.xiaoming.ui.webpage.WebPageActivity;

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