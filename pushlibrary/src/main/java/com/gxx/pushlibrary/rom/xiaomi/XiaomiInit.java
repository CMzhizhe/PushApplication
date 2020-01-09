package com.gxx.pushlibrary.rom.xiaomi;

import android.content.Context;
import android.text.TextUtils;

import com.gxx.pushlibrary.R;
import com.gxx.pushlibrary.handle.PushReceiverHandleManager;
import com.gxx.pushlibrary.model.ReceiverInfo;
import com.gxx.pushlibrary.rom.BasePushTargetInit;
import com.gxx.pushlibrary.util.ApplicationUtil;
import com.xiaomi.mipush.sdk.MiPushClient;

import static com.gxx.pushlibrary.base.PushConstant.XIAOMI;

public class XiaomiInit extends BasePushTargetInit implements IXiaoMiPushListener {
    /**
     * 推送初始化
     *
     * @param context
     */
    public XiaomiInit(Context context) {
        super(context);
        String APP_ID = ApplicationUtil.getMetaData(context, "XIAOMI_APPID");
        String APP_KEY = ApplicationUtil.getMetaData(context, "XIAOMI_APPKEY");
        MiPushClient.registerPush(context, APP_ID, APP_KEY);
        ReceiverInfo aliasInfo = new ReceiverInfo();
        aliasInfo.setTitle(mContext.getString(R.string.tip_init_success));
        aliasInfo.setPushTarget(XIAOMI);
        PushReceiverHandleManager.getInstance().onInitPush(mContext, aliasInfo);
    }

    @Override
    public void setAlias(String alias) {
        if (!TextUtils.isEmpty(alias)) {
            MiPushClient.setAlias(mContext, alias, null);
            ReceiverInfo aliasInfo = new ReceiverInfo();
            aliasInfo.setTitle(mContext.getString(R.string.tip_setalias));
            aliasInfo.setContent(alias);
            aliasInfo.setPushTarget(XIAOMI);
            PushReceiverHandleManager.getInstance().onNotificationAlias(mContext,aliasInfo);
        }
    }

    @Override
    public void addTags(String tags) {
        MiPushClient.subscribe(mContext, tags, null);
    }

    @Override
    public void deleteTags(String tags) {
        MiPushClient.unsubscribe(mContext, tags, null);
    }

    @Override
    public void deleteAlias(String alias) {
        MiPushClient.unsetAlias(mContext,alias,null);
    }


}
