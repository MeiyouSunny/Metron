package com.metron.coin.ui.home;

import android.view.MenuItem;

import com.alaer.lib.util.UserDataUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.metron.coin.R;
import com.metron.coin.base.BaseBindFragment;
import com.metron.coin.base.BaseViewBindActivity;
import com.metron.coin.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import likly.dollar.$;

public class HomeActivity extends BaseViewBindActivity<ActivityHomeBinding> {

    public static HomeActivity instance;

    @Override
    public void onViewCreated() {
        instance = this;
        init();
    }

    protected void init() {
        boolean isChannelUser = UserDataUtil.instance().isChannelUser();

        getSupportActionBar().hide();
        BottomNavigationView mBottomNav = findViewById(R.id.nav_view);
        mBottomNav.inflateMenu(isChannelUser ? R.menu.bottom_nav_menu_ : R.menu.bottom_nav_menu);
        ViewPager mPager = findViewById(R.id.pager);

        List<Fragment> tabFragments = new ArrayList<>(4);
        tabFragments.add(new HomeFragment());
        tabFragments.add(new ProfitFragment());
        if (isChannelUser)
            tabFragments.add(new OrderChannelFragment());
        else
            tabFragments.add(new MinterFragment());

        tabFragments.add(new MineFragment());
        HomeTabAdapter tabAdapter = new HomeTabAdapter(getSupportFragmentManager(), tabFragments);
        mPager.setAdapter(tabAdapter);
        mPager.setOffscreenPageLimit(4);
        mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                ((BaseBindFragment) tabFragments.get(position)).load();
            }
        });

        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_home:
                        mPager.setCurrentItem(0);
                        break;
                    case R.id.tab_profit:
                        mPager.setCurrentItem(1);
                        break;
                    case R.id.tab_minter:
                        mPager.setCurrentItem(2);
                        break;
                    case R.id.tab_mine:
                        mPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void onBackPressed() {
        backToExit();
    }

    private long exitTime = 0;

    private void backToExit() {
        // 再次点击返回键退出程序
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            $.toast().text("再次按下返回键退出程序!").show();
            exitTime = System.currentTimeMillis();
        } else {
//            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}