package com.metron.xiaoming.util;

import java.text.NumberFormat;

public class NumberUtils {
    private static NumberUtils ourInstance;

    public static NumberUtils instance() {
        if (ourInstance == null)
            ourInstance = new NumberUtils();
        return ourInstance;
    }

    private NumberUtils() {
    }

    // 保留8位小数
    public String parseFloat8(float value) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(8);
        nf.setGroupingUsed(false);
        return String.format("%.8f", Float.valueOf(nf.format(value)));
    }

    public float parseFloat(float value) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(8);
        nf.setGroupingUsed(false);
//        nf.setRoundingMode(RoundingMode.DOWN);
        return Float.valueOf(nf.format(value));
    }

    public String parseNumber(float value) {
        float newValue = parseFloat(value);
        int intValue = (int) newValue;
        if ((float) intValue == newValue)
            return String.valueOf(intValue) + ".00";

        String result = String.valueOf(newValue);
        if (result.contains(".")) {
            if (result.split("\\.")[1].length() == 1)
                return result + "0";
        }

        return String.valueOf(newValue);
    }

}
