package com.gxx.pushlibrary.rom.huawei;

import android.text.TextUtils;

import com.gxx.pushlibrary.R;
import com.gxx.pushlibrary.handle.PushReceiverHandleManager;
import com.gxx.pushlibrary.model.ReceiverInfo;
import com.gxx.pushlibrary.util.PushLogUtil;
import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;

import static com.gxx.pushlibrary.base.PushConstant.HUAWEI;

public class HuaWeiPushService extends HmsMessageService {
    private final static String CODELABS_ACTION = "com.huawei.codelabpush.action";
    private String TAG = HuaWeiPushService.class.getSimpleName();

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        if (!TextUtils.isEmpty(token)) {
            ReceiverInfo aliasInfo = new ReceiverInfo();
            aliasInfo.setTitle(this.getString(R.string.tip_setalias));
            aliasInfo.setContent(token);
            aliasInfo.setPushTarget(HUAWEI);
            PushReceiverHandleManager.getInstance().onInitPush(this, aliasInfo);
        }
        PushLogUtil.e("[onNewToken]" + token);
    }

    /**
     * @date :2020/1/2 0002
     * @author : gaoxiaoxiong
     * @description:接收后台传递的消息
     **/
    @Override
    public void onMessageReceived(RemoteMessage message) {
        super.onMessageReceived(message);
        PushLogUtil.e("[onMessageReceived]" + message.toString());
    }

    @Override
    public void onMessageSent(String msgId) {
        PushLogUtil.e("[onMessageSent]" + msgId);
    }


    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
        PushLogUtil.e("[onSendError]" + s);
    }
}
