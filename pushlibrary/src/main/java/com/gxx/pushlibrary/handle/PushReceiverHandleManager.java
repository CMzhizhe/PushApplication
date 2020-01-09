package com.gxx.pushlibrary.handle;

import android.content.Context;
import android.content.Intent;

import com.gxx.pushlibrary.model.ReceiverInfo;
import com.gxx.pushlibrary.util.PushLogUtil;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import static com.gxx.pushlibrary.base.PushConstant.PUSH_INTENT_RECEIVER_INFO;
import static com.gxx.pushlibrary.base.PushConstant.PUSH_RECEIVE_ALIAS;
import static com.gxx.pushlibrary.base.PushConstant.PUSH_RECEIVE_INIT_RESULT;
import static com.gxx.pushlibrary.base.PushConstant.PUSH_RECEIVE_MESSAGE;
import static com.gxx.pushlibrary.base.PushConstant.PUSH_RECEIVE_NOTIFICATION;
import static com.gxx.pushlibrary.base.PushConstant.PUSH_RECEIVE_NOTIFICATION_CLICK;
import static com.gxx.pushlibrary.base.PushConstant.PUSH_RECEIVE_OTHER_OPTIONS;

/**
 * @date :2020/1/2 0002
 * @author : gaoxiaoxiong
 * @description:统一处理收到的推送
 **/
public class PushReceiverHandleManager {
    private static PushReceiverHandleManager instance;
    public static PushReceiverHandleManager getInstance() {
        if (instance == null) {
            synchronized (PushReceiverHandleManager.class) {
                if (instance == null) {
                    instance = new PushReceiverHandleManager();
                }
            }
        }
        return instance;
    }

    /**
     * @date :2020/1/3 0003
     * @author : gaoxiaoxiong
     * @description:推送初始化成功后调用
     **/
    public void onInitPush(Context context,ReceiverInfo info){
        info.setType(PUSH_RECEIVE_INIT_RESULT);
        sendPushData(context, PUSH_RECEIVE_INIT_RESULT, info);
    }


    /**
     * 接收到消息消息，没有显示在通知栏
     * @param context
     * @param info
     */
    public void onMessageReceived(Context context, ReceiverInfo info) {
        info.setType(PUSH_RECEIVE_MESSAGE);
        sendPushData(context, PUSH_RECEIVE_MESSAGE, info);
    }

    /**
     * 接收到通知，会主动显示在通知栏的
     * @param context
     * @param info
     */
    public void onNotificationReceived(Context context, ReceiverInfo info) {
        info.setType(PUSH_RECEIVE_NOTIFICATION);
        sendPushData(context, PUSH_RECEIVE_NOTIFICATION, info);
    }

    /**
     * 用户点击了通知
     * @param context
     * @param info
     */
    public void onNotificationOpened(Context context, ReceiverInfo info) {
        info.setType(PUSH_RECEIVE_NOTIFICATION_CLICK);
        sendPushData(context, PUSH_RECEIVE_NOTIFICATION_CLICK, info);
    }


    /**
     * @date :2020/1/6 0006
     * @author : gaoxiaoxiong
     * @description:其它事件操作返回
     **/
    public void onNotificationOtherOptions(Context context, ReceiverInfo info){
        info.setType(PUSH_RECEIVE_OTHER_OPTIONS);
        sendPushData(context,PUSH_RECEIVE_OTHER_OPTIONS,info);
    }

    /**
     * @date :2020/1/8 0008
     * @author : gaoxiaoxiong
     * @description:设置别名
     **/
    public void onNotificationAlias(Context context,ReceiverInfo info){
        info.setType(PUSH_RECEIVE_ALIAS);
        sendPushData(context,PUSH_RECEIVE_ALIAS,info);
    }


    /**
     * @date :2020/1/2 0002
     * @author : gaoxiaoxiong
     * @description:发送广播
     **/
    private void sendPushData(Context context, String action, ReceiverInfo data) {
        Intent intent = new Intent(action);
        intent.putExtra(PUSH_INTENT_RECEIVER_INFO, data);
        intent.setPackage(context.getPackageName());
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
