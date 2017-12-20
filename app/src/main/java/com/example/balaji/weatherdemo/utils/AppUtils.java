package com.example.balaji.weatherdemo.utils;

import android.content.Context;

import com.example.balaji.weatherdemo.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by balaji on 20/12/17.
 */

public class AppUtils {

    public static String convertTimeStampToDate(long timeStamp) {
        Date date = new Date(timeStamp * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        return sdf.format(date);
    }

    public static String getTemp(Context context, double temp, boolean isMin) {
        StringBuilder builder = new StringBuilder();
        if (isMin) {
            builder.append(context.getString(R.string.min_temp));
        } else {
            builder.append(context.getString(R.string.max_temp));
        }
        builder.append(" ");
        builder.append(String.valueOf(temp));
        return builder.toString();
    }
}
