package com.example.shubhz.cellu;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SignalStrengthClass extends AppCompatActivity {

    TextView tv;
    Intent intent = getIntent();
    TelephonyManager manager;
    int msignal=1;
    MyPhoneStateListener mstate;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signal_strength_class);
        manager=(TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        mstate=new MyPhoneStateListener();
        manager.listen(mstate,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
        //SignalStrength signalStrength=manager.getSignalStrength();
        tv=findViewById(R.id.tx2);
        tv.setText("\n\tSignal Strength\n\nGSM SIGNAL STRENGTH :  "+msignal);//+signalStrength.getGsmSignalStrength());

    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private class MyPhoneStateListener extends PhoneStateListener{




        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            msignal=signalStrength.getGsmSignalStrength();


        }
    }
}
