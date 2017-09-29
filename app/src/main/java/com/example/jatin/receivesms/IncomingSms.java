package com.example.jatin.receivesms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by jatin on 28/9/17.
 */

public class IncomingSms extends BroadcastReceiver {


    public void onReceive(Context context, Intent intent) {

        HashMap<Integer,String> subscriptionDetails= new HashMap<Integer, String>();

        final Bundle bundle = intent.getExtras();

        int subsriptionId=bundle.getInt("subscription",-1);
        SubscriptionManager manager=SubscriptionManager.from(context);
        List<SubscriptionInfo> subscriptionInfos= manager.getActiveSubscriptionInfoList();
        for(int i=0; i<subscriptionInfos.size();i++)
        {
            SubscriptionInfo lsuSubscriptionInfo = subscriptionInfos.get(i);
            String carrierName= (String) lsuSubscriptionInfo.getCarrierName();
            int subscriptionId=lsuSubscriptionInfo.getSubscriptionId();
            subscriptionDetails.put(subscriptionId,carrierName);
        }
        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    String carrierName=subscriptionDetails.get(subsriptionId);
                    Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);

                    MainActivity.updateRecyclerView(phoneNumber,message,carrierName);
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context,
                            "senderNum: "+ senderNum + ", message: " + message, duration);
                    toast.show();

                }
            }

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }
    }
}
