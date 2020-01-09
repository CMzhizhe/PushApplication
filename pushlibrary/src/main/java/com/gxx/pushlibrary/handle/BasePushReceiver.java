package com.gxx.pushlibrary.handle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.gxx.pushlibrary.model.ReceiverInfo;

import static com.gxx.pushlibrary.base.PushConstant.PUSH_INTENT_RECEIVER_INFO;
import static com.gxx.pushlibrary.base.PushConstant.PUSH_RECEIVE_ALIAS;
import static com.gxx.pushlibrary.base.PushConstant.PUSH_RECEIVE_INIT_RESULT;
import static com.gxx.pushlibrary.base.PushConstant.PUSH_RECEIVE_MESSAGE;
import static com.gxx.pushlibrary.base.PushConstant.PUSH_RECEIVE_NOTIFICATION;
import static com.gxx.pushlibrary.base.PushConstant.PUSH_RECEIVE_NOTIFICATION_CLICK;
import static com.gxx.pushlibrary.base.PushConstant.PUSH_RECEIVE_OTHER_OPTIONS;

/**
 * @author yolo.huang
 * @date 2018/12/27
 */
public abstract class BasePushReceiver extends BroadcastReceiver implements IPushReceiverListener {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        ReceiverInfo info = (ReceiverInfo) intent.getSerializableExtra(PUSH_INTENT_RECEIVER_INFO);
        if (PUSH_RECEIVE_INIT_RESULT.equals(action)) {
            onInitResult(context, info);
        } else if (PUSH_RECEIVE_NOTIFICATION.equals(action)) {
            onReceiveNotification(context, info);
        } else if (PUSH_RECEIVE_NOTIFICATION_CLICK.equals(action)) {
            onReceiveNotificationClick(context, info);
        } else if (PUSH_RECEIVE_MESSAGE.equals(action)) {
            onReceiveMessage(context, info);
        } else if (PUSH_RECEIVE_OTHER_OPTIONS.equals(action)){
            onOtherMethodsOption(context,info);
        }else if (PUSH_RECEIVE_ALIAS.equals(action)){
            onAlimsResult(context,info);
        }
    }
}
