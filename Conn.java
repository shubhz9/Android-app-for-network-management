package com.example.shubhz.cellu;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Conn {

    Context context;
    public Conn(Context context) {
        super();
        this.context=context;
    }

    public boolean isConnected(){
        ConnectivityManager connect;
        connect = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if(connect != null)
        {
            NetworkInfo info= connect.getActiveNetworkInfo();
            if (info != null)
            {
                if(info.getState() == NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        return false;
    }

}
