package com.metron.xiaoming.ui.dialog;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.metron.xiaoming.R;
import com.metron.xiaoming.base.BaseDialogHolder;
import com.metron.xiaoming.databinding.DialogCustomerServiceBinding;

/**
 * 联系客服
 */
public class DialogCustomerService extends BaseDialogHolder<DialogCustomerServiceBinding> {

    public DialogCustomerService() {
        super(R.layout.dialog_customer_service);
    }

    @Override
    public void onViewCreated(View view) {
        super.onViewCreated(view);
    }

    @Override
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btnCall:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:15928155557");
                intent.setData(data);
                getContext().startActivity(intent);
                dismiss();
                break;
        }
    }

}
