package com.metron.coin.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ViewUtil {

    public static void gotoActivity(Context context, Class<? extends Activity> activityDes) {
        context.startActivity(new Intent(context, activityDes));
    }

}
