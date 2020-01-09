package com.gxx.pushlibrary.rom.huawei;


import android.content.Context;
import android.text.TextUtils;

import com.gxx.pushlibrary.R;
import com.gxx.pushlibrary.handle.PushReceiverHandleManager;
import com.gxx.pushlibrary.model.ReceiverInfo;
import com.gxx.pushlibrary.rom.BasePushTargetInit;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.push.HmsMessaging;

import static com.gxx.pushlibrary.base.PushConstant.HUAWEI;

public class HuaWeiInit extends BasePushTargetInit implements IHuaWeiPushListener {
    private String appId = null;
    private String token = null;
    /**
     * 推送初始化
     * @param context
     */
    public HuaWeiInit(Context context) {
        super(context);
        try {
            appId = AGConnectServicesConfig.fromContext(context).getString("client/app_id");
            token = HmsInstanceId.getInstance(context).getToken(appId, "HCM");
            if (!TextUtils.isEmpty(token)){
                ReceiverInfo aliasInfo = new ReceiverInfo();
                aliasInfo.setTitle(mContext.getString(R.string.tip_setalias));
                aliasInfo.setContent(token);
                aliasInfo.setPushTarget(HUAWEI);
                PushReceiverHandleManager.getInstance().onNotificationAlias(mContext,aliasInfo);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addTags(String tags) {
        try {
            HmsMessaging.getInstance(mContext)
                    .subscribe(tags)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTags(String tags) {
        try {
            HmsMessaging.getInstance(mContext)
                    .unsubscribe(tags)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteToken() {
        new Thread() {
            @Override
            public void run() {
                try {
                    // read from agconnect-services.json
                    String appId = AGConnectServicesConfig.fromContext(mContext).getString("client/app_id");
                    HmsInstanceId.getInstance(mContext).deleteToken(appId, "HCM");
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
