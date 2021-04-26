package com.metron.coin.ui.home;

import android.view.View;

import com.metron.coin.R;
import com.metron.coin.base.BaseBindFragment;
import com.metron.coin.databinding.FragmentMineBinding;
import com.metron.coin.ui.account.AccountInfoActivity;
import com.metron.coin.ui.extract.ExtractCoinActivity;
import com.metron.coin.ui.extract.ExtractMoneyActivity;
import com.metron.coin.ui.settings.SettingActivity;

public class MineFragment extends BaseBindFragment<FragmentMineBinding> {

    @Override
    public int initLayoutResId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.accountInfo:
                toPage(AccountInfoActivity.class);
                break;
            case R.id.settings:
                toPage(SettingActivity.class);
                break;
            case R.id.extractCoin:
                toPage(ExtractCoinActivity.class);
                break;
            case R.id.extractMoney:
                toPage(ExtractMoneyActivity.class);
                break;
        }
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
    }

//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_mine, container, false);
//        final TextView textView = root.findViewById(R.id.text);
//        textView.setText("我的");
//
//        return root;
//    }
}