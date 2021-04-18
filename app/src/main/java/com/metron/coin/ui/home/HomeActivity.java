package com.metron.coin.ui.home;

import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.metron.coin.R;
import com.metron.coin.base.BaseViewBindActivity;
import com.metron.coin.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class HomeActivity extends BaseViewBindActivity<ActivityHomeBinding> {

    @Override
    public void onViewCreated() {
        init();
    }

    protected void init() {

        getSupportActionBar().hide();
        BottomNavigationView mBottomNav = findViewById(R.id.nav_view);
        ViewPager mPager = findViewById(R.id.pager);
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.tab_home, R.id.tab_profit, R.id.tab_minter, R.id.tab_mine)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_home_tab);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);

        List<Fragment> tabFragments = new ArrayList<>(4);
        tabFragments.add(new HomeFragment());
        tabFragments.add(new ProfitFragment());
        tabFragments.add(new MinterFragment());
        tabFragments.add(new MineFragment());
        HomeTabAdapter tabAdapter = new HomeTabAdapter(getSupportFragmentManager(), tabFragments);
        mPager.setAdapter(tabAdapter);
        mPager.setOffscreenPageLimit(4);

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mBottomNav.setSelectedItemId(R.id.tab_home);
                        break;
                    case 1:
                        mBottomNav.setSelectedItemId(R.id.tab_profit);
                        break;
                    case 2:
                        mBottomNav.setSelectedItemId(R.id.tab_minter);
                        break;
                    case 3:
                        mBottomNav.setSelectedItemId(R.id.tab_mine);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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

}