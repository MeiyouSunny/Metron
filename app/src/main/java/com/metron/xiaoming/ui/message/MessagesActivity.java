package com.metron.xiaoming.ui.message;

import com.alaer.lib.api.ApiUtil;
import com.alaer.lib.api.Callback;
import com.alaer.lib.api.bean.MessageList;
import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseTitleActivity;
import com.metron.xiaoming.databinding.ActivityMessagesBinding;
import com.metron.xiaoming.util.ViewUtil;

/**
 * 消息列表
 */
public class MessagesActivity extends BaseTitleActivity<ActivityMessagesBinding> {

    @Override
    protected String title() {
        return "通知";
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_messages;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        markAllRead();
        queryMessages();
    }

    private void markAllRead() {
        ApiUtil.apiService().markAllRead(new Callback<String>() {
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
            }
        });
    }

    private void queryMessages() {
        ApiUtil.apiService().messageList(200, new Callback<MessageList>() {
            @Override
            public void onResponse(MessageList response) {
                ViewUtil.showListData(bindRoot.repeatView, response.rows);
            }
        });
    }

}