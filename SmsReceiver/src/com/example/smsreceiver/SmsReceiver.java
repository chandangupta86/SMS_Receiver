package com.example.smsreceiver;



import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.NameValuePair;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;


public class SmsReceiver extends BroadcastReceiver 
{
	// All available column names in SMS table
    // [_id, thread_id, address, 
	// person, date, protocol, read, 
	// status, type, reply_path_present, 
	// subject, body, service_center, 
	// locked, error_code, seen]
	/*
	 *  0: _id
		1: thread_id
		2: address
		3: person
		4: date
		5: protocol
		6: read   
		7: status
		8: type
		9: reply_path_present
		10: subject
		11: body
		12: service_center
		13: locked
	 * 
	 * */
	public static final String SMS_EXTRA_NAME = "pdus";
	public static final String SMS_URI = "content://sms";
	
	public static final String ADDRESS = "address";
    public static final String PERSON = "person";
    public static final String DATE = "date";
    public static final String READ = "read";
    public static final String STATUS = "status";
    public static final String TYPE = "type";
    public static final String BODY = "body";
    public static final String SEEN = "seen";
    
    public static final int MESSAGE_TYPE_INBOX = 1;
    public static final int MESSAGE_TYPE_SENT = 2;
    
    public static final int MESSAGE_IS_NOT_READ = 0;
    public static final int MESSAGE_IS_READ = 1;
    
    public static final int MESSAGE_IS_NOT_SEEN = 0;
    public static final int MESSAGE_IS_SEEN = 1;
	
   
    private Context mContext;
    
    private int surveyId;
    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    InputStream is;
	StringBuilder sb;    //for getting the server response
	Boolean isdataTransmitted=true;
    
	@Override
	/*public void onReceive(Context context, Intent intent)
    {
        Bundle myBundle = intent.getExtras();
        SmsMessage [] messages = null;
        String strMessage = "";

        if (myBundle != null)
        {
            Object [] pdus = (Object[]) myBundle.get("pdus");
            messages = new SmsMessage[pdus.length];

            for (int i = 0; i < messages.length; i++)
            {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                strMessage += "SMS From: " + messages[i].getOriginatingAddress();
                strMessage += " : ";
                strMessage += messages[i].getMessageBody();
                strMessage += "\n";
            }

            Toast.makeText(context, strMessage, Toast.LENGTH_SHORT).show();
        }
    }*/
	public void onReceive( Context context, Intent intent ) 
	{
		// Get SMS map from Intent
		
        Bundle extras = intent.getExtras();
        mContext=context;
        String messages = "";
        long parserResult = -1;
        if ( extras != null )
        {
            // Get received SMS array
            Object[] smsExtra = (Object[]) extras.get( SMS_EXTRA_NAME );
            for ( int i = 0; i < smsExtra.length; ++i )
            {
            	SmsMessage sms = SmsMessage.createFromPdu((byte[])smsExtra[i]);
            	
            	String body = sms.getMessageBody().toString();
            	String address = sms.getOriginatingAddress();
            	Log.e("testingstep","step 1. Sms is received");
            	Log.e("test","msgbody is --"+body+ " and number is "+address);
            	messages=body;
            	if(address.trim().equalsIgnoreCase("15555215556"))
            	{
            		abortBroadcast();
            		Log.e("test","aborted..............");
            	}
            	else
            	{
            		Log.e("test","not aborted..............");
            	}

               
           }


            
           }

         
	}

	
	
}
