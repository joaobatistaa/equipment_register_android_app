package com.android.inventariocmrm;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;

import java.io.IOException;


public class NetworkCheck {

    private Context context;

    // constructor
    public NetworkCheck(Context context) {
        this.context = context;
    }

    public void registerNetworkCallback() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkRequest.Builder builder = new NetworkRequest.Builder();

            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback(){
                @Override
                public void onAvailable(Network network) {
                    Variables.isNetworkConnected = true; // Global Static Variable
                }
                @Override
                public void onLost(Network network) {
                    Variables.isNetworkConnected = false; // Global Static Variable
                }
            }

            );
            Variables.isNetworkConnected = false;
        }catch (Exception e){
            Variables.isNetworkConnected = false;
        }
    }


}
