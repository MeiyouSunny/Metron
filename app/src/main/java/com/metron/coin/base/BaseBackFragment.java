package com.metron.coin.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.metron.coin.R;

import androidx.databinding.ViewDataBinding;

public abstract class BaseBackFragment<T extends ViewDataBinding> extends BaseBindFragment<T> implements View.OnClickListener {

    protected abstract String rightTitle();

    protected void onTitleRightClick() {
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        TextView rightTitle = bindRoot.getRoot().findViewById(R.id.title_right);
        rightTitle.setText(rightTitle());
        rightTitle.setOnClickListener(this);
        ImageView back = bindRoot.getRoot().findViewById(R.id.title_left);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.title_left) {
            getActivity().onBackPressed();
        } else if (view.getId() == R.id.title_right) {
            onTitleRightClick();
        }
    }

}
