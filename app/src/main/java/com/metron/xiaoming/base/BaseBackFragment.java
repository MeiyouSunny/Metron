package com.metron.xiaoming.base;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.metron.xiaoming.R;

import androidx.databinding.ViewDataBinding;

public abstract class BaseBackFragment<T extends ViewDataBinding> extends BaseBindFragment<T> implements View.OnClickListener {

    protected abstract String rightTitle();

    protected void onTitleRightClick() {
    }

    protected String title() {
        return "";
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        TextView title = bindRoot.getRoot().findViewById(R.id.title);
        if (!TextUtils.isEmpty(title()))
            title.setText(title());
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
