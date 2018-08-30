package com.sign.myutils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


/**
 * Created by cys on 2018/8/30 0030.
 * 监听网络连接
 */
public class NetStateReceiver extends BroadcastReceiver {

    /**
     * 在manifest注册
     * <receiver android:name=".receiver.NetStateReceiver">
     * <intent-filter>
     * <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
     * </intent-filter>
     * </receiver>
     */

    private static final String TAG = "NetStateReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            final NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            boolean mobileConn;
            if (mobileInfo != null && mobileInfo.isConnected()) {
                mobileConn = true;
            } else {
                mobileConn = false;
            }
            boolean wifiConn;
            if (wifiInfo != null && wifiInfo.isConnected()) {
                wifiConn = true;
            } else {
                wifiConn = false;
            }
            if (!mobileConn && !wifiConn) {
                Log.d(TAG, "connection_change  to : " + false);
            } else {
                Log.d(TAG, "connection_change  to : " + true);
            }
        }
    }
}
