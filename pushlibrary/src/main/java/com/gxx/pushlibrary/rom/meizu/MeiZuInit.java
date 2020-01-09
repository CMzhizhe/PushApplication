package com.gxx.pushlibrary.rom.meizu;

import android.content.Context;

import com.gxx.pushlibrary.PushTargetManager;
import com.gxx.pushlibrary.R;
import com.gxx.pushlibrary.handle.PushReceiverHandleManager;
import com.gxx.pushlibrary.model.ReceiverInfo;
import com.gxx.pushlibrary.rom.BasePushTargetInit;
import com.gxx.pushlibrary.util.ApplicationUtil;
import com.meizu.cloud.pushsdk.PushManager;
import com.xiaomi.mipush.sdk.MiPushClient;

import static com.gxx.pushlibrary.base.PushConstant.MEIZU;

public class MeiZuInit extends BasePushTargetInit implements IMeiZuPushListener {
    private String APP_ID = null;
    private String APP_KEY = null;

    /**
     * 推送初始化
     * @param context
     */
    public MeiZuInit(Context context) {
        super(context);
        APP_ID = ApplicationUtil.getMetaData(context, "MEIZU_APPID");
        APP_KEY = ApplicationUtil.getMetaData(context, "MEIZU_APPKEY");
        PushManager.register(context, APP_ID, APP_KEY);
        ReceiverInfo aliasInfo = new ReceiverInfo();
        aliasInfo.setTitle(mContext.getString(R.string.tip_init_success));
        aliasInfo.setPushTarget(MEIZU);
        PushReceiverHandleManager.getInstance().onInitPush(mContext, aliasInfo);
    }

    @Override
    public void setAlias(String alias) {
        PushManager.subScribeAlias(mContext, APP_ID, APP_KEY, PushManager.getPushId(mContext), alias);
        ReceiverInfo aliasInfo = new ReceiverInfo();
        aliasInfo.setTitle(mContext.getString(R.string.tip_setalias));
        aliasInfo.setContent(alias);
        aliasInfo.setPushTarget(MEIZU);
        PushReceiverHandleManager.getInstance().onNotificationAlias(mContext,aliasInfo);
    }

    @Override
    public void addTags(String tags) {
        PushManager.subScribeTags(mContext,APP_ID,APP_KEY,PushManager.getPushId(mContext),tags);

    }

    @Override
    public void deleteTags(String tags) {
        PushManager.unSubScribeTags(mContext,APP_ID,APP_KEY,PushManager.getPushId(mContext),tags);
    }

    @Override
    public void deleteAlias(String alias) {
        PushManager.unSubScribeAlias(mContext,APP_ID,APP_KEY,PushManager.getPushId(mContext),alias);
    }


}
