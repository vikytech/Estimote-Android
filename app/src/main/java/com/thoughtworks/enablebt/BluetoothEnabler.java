package com.thoughtworks.enablebt;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

public class BluetoothEnabler extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String TW_DATA_SSID = "\"twdata\"";
        String TW_GUEST_SSID = "\"twguest\"";

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            String deviceConnectedWiFiSSID = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getExtraInfo();
            Log.i("Connected to WiFi-SSID:", String.valueOf(deviceConnectedWiFiSSID));
            if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                if (String.valueOf(deviceConnectedWiFiSSID).equals(TW_DATA_SSID) || String.valueOf(deviceConnectedWiFiSSID).equals(TW_GUEST_SSID)) {
                    BluetoothAdapter.getDefaultAdapter().enable();
                    Log.i("Bluetooth Enabled: ", String.valueOf(BluetoothAdapter.getDefaultAdapter().isEnabled()));
                }
            }
        }
    }
}
