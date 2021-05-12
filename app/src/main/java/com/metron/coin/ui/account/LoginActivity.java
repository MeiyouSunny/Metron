package com.metron.coin.ui.account;

import com.metron.coin.R;
import com.metron.coin.base.BaseViewBindActivity;
import com.metron.coin.databinding.ActivityLoginBinding;

import androidx.navigation.fragment.NavHostFragment;

public class LoginActivity extends BaseViewBindActivity<ActivityLoginBinding> {
    public static final int TYPE_LOGIN = 1;
    public static final int TYPE_REGIST = 2;

    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        int type = getIntent().getIntExtra("type", TYPE_LOGIN);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            if (type == TYPE_LOGIN)
                navHostFragment.getNavController().setGraph(R.navigation.login_nav);
            else
                navHostFragment.getNavController().setGraph(R.navigation.regist_nav);
        }
    }

}