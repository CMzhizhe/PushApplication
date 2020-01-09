package com.gxx.pushapplication;

import android.content.Context;
import android.util.Log;

import com.gxx.pushlibrary.handle.BasePushReceiver;
import com.gxx.pushlibrary.model.ReceiverInfo;

/**
 * @date :2020/1/2 0002
 * @author : gaoxiaoxiong
 * @description:推送消息处理类
 * LocalBroadcastManager.getInstance.sendBroadcast 发送的广播，只能通过LocalBroadcastManager.getInstance(this).registerReceiver接收到，注销别名同理
 **/
public class PushBroadcastReceiver extends BasePushReceiver {
    @Override
    public void onOtherMethodsOption(Context context, ReceiverInfo info) {
        Log.i("PushBroadcastReceiver",info.toString());
    }

    @Override
    public void onReceiveNotification(Context context, ReceiverInfo info) {

    }

    @Override
    public void onReceiveNotificationClick(Context context, ReceiverInfo info) {

    }

    @Override
    public void onReceiveMessage(Context context, ReceiverInfo info) {

    }

    @Override
    public void onAlimsResult(Context context, ReceiverInfo info) {

    }


    @Override
    public void onInitResult(Context context, ReceiverInfo info) {
        Log.e("onInitResult",info.toString());
    }


}
