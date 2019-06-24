package com.thomas.test;

import android.content.Context;
import android.net.ConnectivityManager;

public class Utils {

    public static boolean hasNetwork(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null;
    }
}
