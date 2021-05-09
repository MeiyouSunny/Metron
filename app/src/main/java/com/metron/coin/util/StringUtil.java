package com.metron.coin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

    // 2021-04-23T00:00:00.000Z --> yyyy年MM月dd日 HH:mm
    public String formatTimeString(String time) {
        try {
            time = time.replace("T", " ");
            time = time.replace("Z", "");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date date = format.parse(time);
            format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return time;
    }

}
