package com.metron.xiaoming.adapter;

import com.alaer.lib.api.bean.MessageList;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.repeatview.BaseViewHolder;
import com.metron.xiaoming.databinding.ItemMessageBinding;
import com.metron.xiaoming.util.StringUtil;

public class MessageAdapter extends BaseViewHolder<ItemMessageBinding, MessageList.Message> {

    private int[] icons = {R.drawable.ic_message_1, R.drawable.ic_message_1, R.drawable.ic_message_2, R.drawable.ic_message_3};

    @Override
    protected void onBindData(MessageList.Message message) {
        bindRoot.setIcons(icons);
        bindRoot.setUtil(new StringUtil());
        bindRoot.setMessage(message);
        bindRoot.executePendingBindings();
    }

    @Override
    protected int getViewHolderLayout() {
        return R.layout.item_message;
    }

}
