package com.example.shubhz.cellu;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button allInformation,internetConnectivity,moileInformation,signalStrength;
    Intent inte;
    Conn c=new Conn(this);
    
    final int requestCode=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allInformation=findViewById(R.id.allInformation);
        internetConnectivity=findViewById(R.id.isConnected);
        moileInformation=findViewById(R.id.mobileInformation);
        signalStrength=findViewById(R.id.signalStrength);
        requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},requestCode);
    }


    public void setNew(View view) {
        allInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPage("allInformation");
            }

        });
        moileInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPage("mobileInformation");
            }
        });
        signalStrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPage("s");
            }
        });
        internetConnectivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPage("isConnected");
            }
        });
    }

    private void callPage(String value) {
        switch (value)
        {
            case "allInformation":
                inte=new Intent(this,Main2Activity.class);
                startActivity(inte);
                break;
            case "mobileInformation":
                inte=new Intent(this,MobileInformation.class);
                startActivity(inte);
                break;
            case "s":
                inte=new Intent(this,SignalStrengthClass.class);
                startActivity(inte);
                break;
            case "isConnected":
                if(c.isConnected())
                {
                    Toast.makeText(this, "CONNECTED", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this, "NOT CONNECTED", Toast.LENGTH_SHORT).show();


                break;
            default:
                inte=new Intent(this,Main2Activity.class);
                startActivity(inte);
                break;
        }

    }
}
