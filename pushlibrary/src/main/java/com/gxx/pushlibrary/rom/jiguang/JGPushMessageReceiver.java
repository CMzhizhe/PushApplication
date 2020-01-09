package com.gxx.pushlibrary.rom.jiguang;

import android.content.Context;
import android.content.Intent;

import com.gxx.pushlibrary.handle.PushReceiverHandleManager;
import com.gxx.pushlibrary.model.ReceiverInfo;
import com.gxx.pushlibrary.util.PushLogUtil;

import cn.jpush.android.api.CmdMessage;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

import static com.gxx.pushlibrary.base.PushConstant.JPUSH;


public class JGPushMessageReceiver extends JPushMessageReceiver {
    private final String JG_onMessage = "onMessage";
    private final String JG_onNotifyMessageOpened = "onNotifyMessageOpened";
    private final String JG_onNotifyMessageArrived = "onNotifyMessageArrived";

    /**
     * @date :2020/1/2 0002
     * @author : gaoxiaoxiong
     * @description:收到自定义消息回调 自定义消息不会显示到通知上面
     **/
    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        PushLogUtil.e("[onMessage] " + customMessage);
        convert2MessageReceiverInfo(context, JG_onMessage, null, customMessage, null, null);
    }

    /**
     * @date :2020/1/2 0002
     * @author : gaoxiaoxiong
     * @description:点击通知回调
     **/
    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage message) {
        PushLogUtil.e("[onNotifyMessageOpened] " + message);
       /* try{
            //打开自定义的Activity
            Intent i = new Intent(context, TestActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(JPushInterface.EXTRA_NOTIFICATION_TITLE,message.notificationTitle);
            bundle.putString(JPushInterface.EXTRA_ALERT,message.notificationContent);
            i.putExtras(bundle);
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
            context.startActivity(i);
        }catch (Throwable throwable){

        }*/
    }

    @Override
    public void onMultiActionClicked(Context context, Intent intent) {
        PushLogUtil.e("[onMultiActionClicked] 用户点击了通知栏按钮");
        String nActionExtra = intent.getExtras().getString(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA);

        //开发者根据不同 Action 携带的 extra 字段来分配不同的动作。
        if (nActionExtra == null) {
            PushLogUtil.e("ACTION_NOTIFICATION_CLICK_ACTION nActionExtra is null");
            return;
        }
        if (nActionExtra.equals("my_extra1")) {
            PushLogUtil.e("[onMultiActionClicked] 用户点击通知栏按钮一");
        } else if (nActionExtra.equals("my_extra2")) {
            PushLogUtil.e("[onMultiActionClicked] 用户点击通知栏按钮二");
        } else if (nActionExtra.equals("my_extra3")) {
            PushLogUtil.e("[onMultiActionClicked] 用户点击通知栏按钮三");
        } else {
            PushLogUtil.e("[onMultiActionClicked] 用户点击通知栏按钮未定义");
        }
    }

    /**
     * @date :2020/1/2 0002
     * @author : gaoxiaoxiong
     * @description:收到通知回调
     **/
    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage message) {
        PushLogUtil.e("[onNotifyMessageArrived] " + message);
        convert2MessageReceiverInfo(context, JG_onNotifyMessageArrived, null, null, null, message);
    }

    /**
     * @date :2020/1/2 0002
     * @author : gaoxiaoxiong
     * @description:清除通知回调
     **/
    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage message) {
        PushLogUtil.e("[onNotifyMessageDismiss] " + message);
    }

    /**
     * @date :2020/1/2 0002
     * @author : gaoxiaoxiong
     * @description:注册成功回调
     **/
    @Override
    public void onRegister(Context context, String registrationId) {
        PushLogUtil.e("[onRegister] " + registrationId);
    }

    /**
     * @date :2020/1/2 0002
     * @author : gaoxiaoxiong
     * @description:长连接状态回调
     **/
    @Override
    public void onConnected(Context context, boolean isConnected) {
        PushLogUtil.e("[onConnected] " + isConnected);
    }

    /**
     * @date :2020/1/2 0002
     * @author : gaoxiaoxiong
     * @description:注册失败回调
     **/
    @Override
    public void onCommandResult(Context context, CmdMessage cmdMessage) {
        PushLogUtil.e("[onCommandResult] " + cmdMessage);
    }

    /**
     * @date :2020/1/2 0002
     * @author : gaoxiaoxiong
     * @description: tag 增删查改的操作会在此方法中回调结果。
     **/
    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onTagOperatorResult(context, jPushMessage);
    }

    /**
     * @date :2020/1/2 0002
     * @author : gaoxiaoxiong
     * @description: 查询某个 tag 与当前用户的绑定状态的操作会在此方法中回调结果。
     **/
    @Override
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onCheckTagOperatorResult(context, jPushMessage);
    }

    /**
     * @date :2020/1/2 0002
     * @author : gaoxiaoxiong
     * @description: alias 相关的操作会在此方法中回调结果。
     **/
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onAliasOperatorResult(context, jPushMessage);
    }

    /**
     * @date :2020/1/2 0002
     * @author : gaoxiaoxiong
     * @description:设置手机号码会在此方法中回调结果。
     **/
    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onMobileNumberOperatorResult(context, jPushMessage);
    }


    /**
     * @date :2020/1/2 0002
     * @author : gaoxiaoxiong
     * @description:类型转换
     **/
    private void convert2MessageReceiverInfo(Context context, String type, Intent intent, CustomMessage customMessage, JPushMessage jPushMessage, NotificationMessage notificationMessage) {
        ReceiverInfo info = new ReceiverInfo();
        info.setPushTarget(JPUSH);
       if (type.equals(JG_onMessage) && customMessage != null) {
            info.setTitle(customMessage.title);
            info.setContent(customMessage.message);
            info.setExtra(customMessage.extra);
            PushReceiverHandleManager.getInstance().onMessageReceived(context, info);
        } else if (notificationMessage != null && type.equals(JG_onNotifyMessageArrived)) {
            info.setTitle(notificationMessage.notificationTitle);
            info.setContent(notificationMessage.notificationContent);
            info.setExtra(notificationMessage.notificationExtras);
            if (type.equals(JG_onNotifyMessageArrived)) {
                PushReceiverHandleManager.getInstance().onNotificationReceived(context, info);
            }
        }
    }
}
