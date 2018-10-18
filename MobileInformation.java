package com.example.shubhz.cellu;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shubhz.cellu.Main2Activity;

public class MobileInformation extends AppCompatActivity {

    TextView tv;
    Intent intent = getIntent();
    private String phoneType;
    private String minfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_information);
        tv = findViewById(R.id.textView);
        show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            show();
        }
        else {
            Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
        }
    }

    private void show() {
        int permissionstate= ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if(permissionstate == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager manager= (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            minfo = "\n MOBILE and SIM Information   \n";
            int phoneTyp=manager.getPhoneType();
            int simstat=manager.getSimState();
            String simState;
            switch (phoneTyp){
                case (TelephonyManager.PHONE_TYPE_CDMA):
                {
                    phoneType="CDMA";
                    break;
                }
                case (TelephonyManager.PHONE_TYPE_GSM):{
                    phoneType="GSM";
                    break;
                }
                case(TelephonyManager.PHONE_TYPE_SIP) :
                {
                    phoneType="SIP";
                    break;
                }
                case (TelephonyManager.PHONE_TYPE_NONE):
                {
                    phoneType="NONE";
                    break;
                }
            }
            switch (simstat)
            {
                case TelephonyManager.SIM_STATE_READY:
                    simState="READY";
                    break;
                case TelephonyManager.SIM_STATE_ABSENT:
                    simState="ABSENT";
                    break;
                case TelephonyManager.SIM_STATE_CARD_IO_ERROR:
                    simState="IO ERROR ";
                    break;
                case  TelephonyManager.SIM_STATE_NOT_READY:
                    simState="NOT READY";
                    break;
                case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                    simState="PIN REQUIRED";
                    break;
                case TelephonyManager.SIM_STATE_UNKNOWN:
                    simState="UNKNOWN";
                    break;
                    default:
                        simState="UNKNOWN";
                        break;
                }
            boolean isRoaming=manager.isNetworkRoaming();
            minfo+="\n Phone network type  :  "+phoneType;
            minfo+="\n IMEI NUMBER   :  "+manager.getDeviceId();
            minfo+="\n Sim Serial Number   :  "+manager.getSimSerialNumber();
            minfo+="\n SIm Country ISO   :  "+manager.getSimCountryIso();
            minfo+="\n Network Country ISO   :  "+manager.getSimCountryIso();
            minfo+="\n Sim State Information   :  "+simState;

            if (isRoaming){
                minfo+="\n IN ROAMING";
            }else {
                minfo+="\n NOT IN ROAMING";
            }
            tv.setText(minfo);
        }
        else {
            finish();
        }
    }
}
