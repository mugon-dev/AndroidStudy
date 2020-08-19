package com.example.broadcastreceiverapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    public static final String TAG="SmsReceiver";
    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i(TAG,"onReceiver() 메소드 호출");
        //intent가 없기때문에 bundle 상태로 데이터 받아옴
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle); //번들에서 데이터 추출
        if (messages != null && messages.length > 0) { //메시지가 있으면
            String sender = messages[0].getOriginatingAddress(); //메시지 0번째에 send 데이터가 들어있음
            Log.i(TAG, "SMS sender: " + sender);
            String contents=messages[0].getMessageBody().toString();
            Log.i(TAG, "SMS contents: " + contents);
            Date receiverDate = new Date(messages[0].getTimestampMillis());
            Log.i(TAG, "SMS received date: " + receiverDate);
            sendToActivity(context,sender,contents,receiverDate);
        }
    }
    //메시지 받는 방법 (정해져 있음)
    private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object[] objs= (Object[]) bundle.get("puds"); //번들로부터 pdus이름으로 데이터를 오브젝트 형태로 가져옴
        SmsMessage[] messages = new SmsMessage[objs.length]; //메시지 배열 생성
        int smsCount = objs.length;
        for (int i = 0; i < smsCount; i++) { //받아진 개수만큼 반복
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // 지금 현재 버전(API)이 23이상이면
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]); //바이트 형으로 타입캐스팅해서 가져와서 메시지 배열에 입력
            }
        }
        return messages;
    }

    private void sendToActivity(Context context, String sender, String contents, Date receivedDate) {
        Intent myIntent=new Intent(context,SmsActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                Intent.FLAG_ACTIVITY_CLEAR_TOP|
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        myIntent.putExtra("sender",sender);
        myIntent.putExtra("contents",contents);
        myIntent.putExtra("receivedDate",format.format(receivedDate));
        context.startActivity(myIntent);
    }
}
