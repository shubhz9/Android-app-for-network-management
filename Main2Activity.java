package com.example.shubhz.cellu;

import android.Manifest;
import android.Manifest.permission;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.CellInfo;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    Intent intent=getIntent();
    TextView tv;
    public String info;
    public static String minfo;
    public String phoneType;
    public static final int PERMISSION_READ_STATE=123;
    public final Context context=this;
    Intent inte;
    Button b;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b=findViewById(R.id.backb);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirect();
            }
        });
        show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case PERMISSION_READ_STATE:{
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {

                }
                else {
                    Toast.makeText(context, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void redirect() {
        inte=new Intent(this,MainActivity.class);
        startActivity(inte);
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void show()
    {
        int permissionstate=ContextCompat.checkSelfPermission(this,permission.READ_PHONE_STATE);
        int requestCode=1;

        if(permissionstate==PackageManager.PERMISSION_GRANTED)
        {
            TelephonyManager manager=(TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

            int phoneTyp=manager.getPhoneType();
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
            boolean isRoaming= manager.isNetworkRoaming();
            String PhoneType= phoneType;
            String IMEINumber= manager.getDeviceId();
            String SimSerialNumber= manager.getSimSerialNumber();
            String NetworkCountryISO= manager.getNetworkCountryIso();
            String SIMCountryISO=manager.getSimCountryIso();
            String SoftwareVersion= manager.getDeviceSoftwareVersion();
            String VoiceMailNumber= manager.getVoiceMailNumber();
            minfo="Mobile and SIM Information";
            info="Phone info\n";
            info+="\n Phone network type  :  "+PhoneType;
            info+="\n IMEI NUMBER   :  "+IMEINumber;
            info+="\n Sim Serial Number   :  "+SimSerialNumber;
            info+="\n Software Version   :  "+SoftwareVersion;
            info+="\n Voice Mail Number   :  "+VoiceMailNumber;
            info+="\n SIm Country ISO   :  "+SIMCountryISO;
            info+="\n Network Country ISO   :  "+NetworkCountryISO;
            info+="\n Network Operator  :  "+manager.getNetworkOperatorName();
            info+="\n Network Access Identifier   :  "+manager.getNai();
            info+="\n Data Network Type   :  "+manager.getDataNetworkType();

            minfo+="\n Phone network type  :  "+PhoneType;
            minfo+="\n IMEI NUMBER   :  "+IMEINumber;
            minfo+="\n Sim Serial Number   :  "+SimSerialNumber;
            minfo+="\n SIm Country ISO   :  "+SIMCountryISO;
            minfo+="\n Network Country ISO   :  "+NetworkCountryISO;

            if (isRoaming){
                info+="\n In Roaming ";
                minfo+="\n IN ROAMING";
            }else {
                info += "\n Not In Roaming ";
                minfo+="\n NOT IN ROAMING";
            }

            tv=(TextView) findViewById(R.id.textView2);
            tv.setText(info);

        }
    }


}
