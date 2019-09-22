package com.sinanbasormanci.samplewebsocket.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.provider.Settings;

import androidx.core.content.ContextCompat;

import com.sinanbasormanci.samplewebsocket.Base.BaseActivity;

import java.util.Locale;

/**
 * Created by sinanbasormanci.com on 2019-09-21.
 */
public class AppUtil {
    public static boolean isNetworkConnected(BaseActivity context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public static int colorIU(Context context, int i){
        return ContextCompat.getColor(context,i);
    }

    public static String deviceID(BaseActivity context){
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static final Drawable getDrawable(Context context, int id) {
        return ContextCompat.getDrawable(context, id);
    }

    public static String getDisplayLanguage(){
        return Locale.getDefault().getLanguage();
    }
}
