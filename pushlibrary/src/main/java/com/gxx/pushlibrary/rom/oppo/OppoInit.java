package com.gxx.pushlibrary.rom.oppo;

import android.content.Context;

import com.google.gson.Gson;
import com.gxx.pushlibrary.R;
import com.gxx.pushlibrary.handle.PushReceiverHandleManager;
import com.gxx.pushlibrary.model.ReceiverInfo;
import com.gxx.pushlibrary.rom.BasePushTargetInit;
import com.gxx.pushlibrary.util.ApplicationUtil;
import com.heytap.mcssdk.PushManager;
import com.heytap.mcssdk.callback.PushCallback;
import com.heytap.mcssdk.mode.ErrorCode;
import com.heytap.mcssdk.mode.SubscribeResult;

import java.util.List;

import static com.gxx.pushlibrary.base.PushConstant.OPPO;

public class OppoInit extends BasePushTargetInit implements OppoPushListener{
    /**
     * 推送初始化
     *
     * @param context
     */
    public OppoInit(Context context) {
        super(context);
        String appKey = ApplicationUtil.getMetaData(context, "OPPO_APP_KEY");
        String appSecret = ApplicationUtil.getMetaData(context, "OPPO_APP_SECRET");
        PushManager.getInstance().register(context, appKey, appSecret, pushCallback);
        PushManager.getInstance().requestNotificationPermission();
        ReceiverInfo info = new ReceiverInfo();
        info.setTitle(mContext.getString(R.string.tip_init_success));
        info.setPushTarget(OPPO);
        PushReceiverHandleManager.getInstance().onInitPush(mContext,info);
    }

    private PushCallback pushCallback = new PushCallback() {
        @Override
        public void onRegister(int i, String regId) {
            //s 注册id，用来唯一标识设备的
            if (i == ErrorCode.SUCCESS) {
                //注册成功
                ReceiverInfo info = new ReceiverInfo();
                info.setTitle(mContext.getString(R.string.tip_setalias));
                info.setContent(regId);
                info.setPushTarget(OPPO);
                PushReceiverHandleManager.getInstance().onNotificationAlias(mContext,info);
            }
        }

        @Override
        public void onUnRegister(int i) {

        }

        @Override
        public void onGetAliases(int i, List<SubscribeResult> list) {

        }

        @Override
        public void onSetAliases(int i, List<SubscribeResult> list) {

        }

        @Override
        public void onUnsetAliases(int i, List<SubscribeResult> list) {

        }

        @Override
        public void onSetUserAccounts(int i, List<SubscribeResult> list) {

        }

        @Override
        public void onUnsetUserAccounts(int i, List<SubscribeResult> list) {

        }

        @Override
        public void onGetUserAccounts(int i, List<SubscribeResult> list) {

        }

        @Override
        public void onSetTags(int i, List<SubscribeResult> list) {

        }

        @Override
        public void onUnsetTags(int i, List<SubscribeResult> list) {

        }

        @Override
        public void onGetTags(int i, List<SubscribeResult> list) {

        }

        @Override
        public void onGetPushStatus(int i, int i1) {

        }

        @Override
        public void onSetPushTime(int i, String s) {

        }

        @Override
        public void onGetNotificationStatus(int i, int i1) {

        }
    };

    @Override
    public void setAliases(List<String> aliases) {
        PushManager.getInstance().setAliases(aliases);
        ReceiverInfo aliasInfo = new ReceiverInfo();
        aliasInfo.setTitle(mContext.getString(R.string.tip_setalias));
        aliasInfo.setContent(new Gson().toJson(aliases));
        aliasInfo.setPushTarget(OPPO);
        PushReceiverHandleManager.getInstance().onNotificationAlias(mContext,aliasInfo);
    }

    @Override
    public void setTags(List<String> tags) {
        PushManager.getInstance().setTags(tags);
    }

    @Override
    public void unsetTags(List<String> tags) {
        PushManager.getInstance().setTags(tags);
    }

    @Override
    public void unsetAliases(List<String> aliases) {
        PushManager.getInstance().unsetAliases(aliases);
    }
}
