package com.example.jatin.receivesms;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_CODE = 200;
    private static List<Details> messageList=new ArrayList<>();

    private static RecyclerViewAdapter recyclerViewAdapter;

    private RecyclerView recyclerView;

    public static void updateRecyclerView(String phoneNumber,String message,String carrierName){

            Details mDetails=new Details(carrierName,message,phoneNumber);
            messageList.add(0,mDetails);
            recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED ||ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED)
        requestPermissions();
        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        recyclerViewAdapter=new RecyclerViewAdapter(messageList);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    private void requestPermissions() {

        String perms[]={"android.permission.RECEIVE_SMS","android.permission.READ_SMS","android.permission.READ_PHONE_STATE"};
        ActivityCompat.requestPermissions(this,perms,REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case 200:

                for(int i=0;i<grantResults.length;i++) {
                    if (grantResults.length > 0
                            && grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getApplicationContext(), "Need permission to run the app", Toast.LENGTH_SHORT);
                    }
                }
        }
    }
}

