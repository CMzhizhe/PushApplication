package com.gxx.pushlibrary.rom.xiaomi;

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.gson.Gson;
import com.gxx.pushlibrary.handle.PushReceiverHandleManager;
import com.gxx.pushlibrary.model.ReceiverInfo;
import com.gxx.pushlibrary.util.PushLogUtil;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.gxx.pushlibrary.base.PushConstant.JPUSH;
import static com.gxx.pushlibrary.base.PushConstant.XIAOMI;

/**
 * 1、PushMessageReceiver 是个抽象类，该类继承了 BroadcastReceiver。<br/>
 * 2、需要将自定义的 DemoMessageReceiver 注册在 AndroidManifest.xml 文件中：
 * <pre>
 * {@code
 *  <receiver
 *      android:name="com.xiaomi.mipushdemo.DemoMessageReceiver"
 *      android:exported="true">
 *      <intent-filter>
 *          <action android:name="com.xiaomi.mipush.RECEIVE_MESSAGE" />
 *      </intent-filter>
 *      <intent-filter>
 *          <action android:name="com.xiaomi.mipush.MESSAGE_ARRIVED" />
 *      </intent-filter>
 *      <intent-filter>
 *          <action android:name="com.xiaomi.mipush.ERROR" />
 *      </intent-filter>
 *  </receiver>
 *  }</pre>
 * 3、DemoMessageReceiver 的 onReceivePassThroughMessage 方法用来接收服务器向客户端发送的透传消息。<br/>
 * 4、DemoMessageReceiver 的 onNotificationMessageClicked 方法用来接收服务器向客户端发送的通知消息，
 * 这个回调方法会在用户手动点击通知后触发。<br/>
 * 5、DemoMessageReceiver 的 onNotificationMessageArrived 方法用来接收服务器向客户端发送的通知消息，
 * 这个回调方法是在通知消息到达客户端时触发。另外应用在前台时不弹出通知的通知消息到达客户端也会触发这个回调函数。<br/>
 * 6、DemoMessageReceiver 的 onCommandResult 方法用来接收客户端向服务器发送命令后的响应结果。<br/>
 * 7、DemoMessageReceiver 的 onReceiveRegisterResult 方法用来接收客户端向服务器发送注册命令后的响应结果。<br/>
 * 8、以上这些方法运行在非 UI 线程中。
 *
 * @author mayixiang
 */


/**
 * @date :2020/1/3 0003
 * @author : gaoxiaoxiong
 * @description:小米接收推送
 **/
public class XiaoMiMessageReceiver extends PushMessageReceiver {
    private String mRegId;
    private String mTopic;
    private String mAlias;
    private String mAccount;
    private String mStartTime;
    private String mEndTime;
    private final String XM_onReceivePassThroughMessage="onReceivePassThroughMessage";
    private final String XM_onNotificationMessageClicked="onNotificationMessageClicked";
    private final String XM_onNotificationMessageArrived="onNotificationMessageArrived";
    /**
     * @date :2020/1/3 0003
     * @author : gaoxiaoxiong
     * @description:方法用来接收服务器向客户端发送的透传消息，
     * 进程被杀了，是拿不到消息的
     **/
    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage message) {
        PushLogUtil.e("onReceivePassThroughMessage is called. " + message.toString());
        convert2MessageReceiverInfo(XM_onReceivePassThroughMessage,context,message);
    }

    /**
     * @date :2020/1/3 0003
     * @author : gaoxiaoxiong
     * @description:方法用来接收服务器向客户端发送的通知消息，这个回调方法会在用户手动点击通知后触发。
     **/
    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage message) {
        PushLogUtil.e("onNotificationMessageClicked is called. " + message.toString());
        convert2MessageReceiverInfo(XM_onNotificationMessageClicked, context,  message);
    }

    /**
     * @date :2020/1/3 0003
     * @author : gaoxiaoxiong
     * @description:方法用来接收服务器向客户端发送的通知消息,这个回调方法是在通知消息到达客户端时触发。另外应用在前台时不弹出通知的通知消息到达客户端也会触发这个回调函数。
     **/
    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage message) {
        PushLogUtil.e("onNotificationMessageArrived is called. " + message.toString());
        convert2MessageReceiverInfo(XM_onNotificationMessageArrived, context,  message);
    }

    /**
     * @date :2020/1/3 0003
     * @author : gaoxiaoxiong
     * @description:方法用来接收客户端向服务器发送命令后的响应结果。
     **/
    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {
        PushLogUtil.e("onCommandResult is called. " + message.toString());
        String command = message.getCommand();
        List<String> arguments = message.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        String cmdArg2 = ((arguments != null && arguments.size() > 1) ? arguments.get(1) : null);
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                mRegId = cmdArg1;
            }
        }
    }

    /**
     * @date :2020/1/3 0003
     * @author : gaoxiaoxiong
     * @description:方法用来接收客户端向服务器发送注册命令后的响应结果。
     **/
    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
        PushLogUtil.e("onReceiveRegisterResult is called. " + message.toString());

    }

    @Override
    public void onRequirePermissions(Context context, String[] permissions) {
        super.onRequirePermissions(context, permissions);
        PushLogUtil.e("onRequirePermissions is called. need permission" + arrayToString(permissions));
    }

    @SuppressLint("SimpleDateFormat")
    private static String getSimpleDate() {
        return new SimpleDateFormat("MM-dd hh:mm:ss").format(new Date());
    }


    private void convert2MessageReceiverInfo(String type,Context context, MiPushMessage miPushMessage){
        ReceiverInfo info = new ReceiverInfo();
        info.setPushTarget(XIAOMI);
        if (type.equals(XM_onNotificationMessageArrived)){
            info.setTitle(miPushMessage.getTitle());
            info.setContent(miPushMessage.getContent());
            String json =new Gson().toJson(miPushMessage.getExtra());
            info.setExtra(json);
            PushReceiverHandleManager.getInstance().onNotificationReceived(context, info);
        }else if (type.equals(XM_onNotificationMessageClicked)){//消息click
            info.setTitle(miPushMessage.getTitle());
            info.setContent(miPushMessage.getContent());
            String json =new Gson().toJson(miPushMessage.getExtra());
            info.setExtra(json);
            PushReceiverHandleManager.getInstance().onNotificationOpened(context,info);
        } else if (type.equals(XM_onReceivePassThroughMessage)){//透传
            info.setContent(miPushMessage.getContent());
            String json =new Gson().toJson(miPushMessage.getExtra());
            info.setExtra(json);
            PushReceiverHandleManager.getInstance().onMessageReceived(context,info);
        }
    }


    public String arrayToString(String[] strings) {
        String result = " ";
        for (String str : strings) {
            result = result + str + " ";
        }
        return result;
    }

}
