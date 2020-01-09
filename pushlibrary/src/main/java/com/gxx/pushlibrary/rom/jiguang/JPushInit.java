package com.gxx.pushlibrary.rom.jiguang;

import android.app.Application;
import android.text.TextUtils;

import com.gxx.pushlibrary.R;
import com.gxx.pushlibrary.handle.PushReceiverHandleManager;
import com.gxx.pushlibrary.model.ReceiverInfo;
import com.gxx.pushlibrary.rom.BasePushTargetInit;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;

import static com.gxx.pushlibrary.base.PushConstant.JPUSH;

public class JPushInit extends BasePushTargetInit implements IJGPushListener {
    public JPushInit(Application application) {
        super(application);
        JPushInterface.init(application);
        ReceiverInfo aliasInfo = new ReceiverInfo();
        aliasInfo.setTitle(mContext.getString(R.string.tip_init_success));
        aliasInfo.setPushTarget(JPUSH);
        PushReceiverHandleManager.getInstance().onInitPush(mContext, aliasInfo);
    }

    @Override
    public void setAlias(String alias, int sequence) {
        if (TextUtils.isEmpty(alias)){
            return;
        }
        JPushInterface.setAlias(mContext, sequence, alias);
        ReceiverInfo aliasInfo = new ReceiverInfo();
        aliasInfo.setTitle(mContext.getString(R.string.tip_setalias));
        aliasInfo.setContent(alias);
        aliasInfo.setPushTarget(JPUSH);
        PushReceiverHandleManager.getInstance().onNotificationAlias(mContext,aliasInfo);
    }


    @Override
    public void addTags(int sequence, Set<String> tags) {
        if (tags != null && tags.size() > 0) {
            JPushInterface.addTags(mContext, sequence, tags);
        }
    }

    @Override
    public void deleteTags(int sequence, Set<String> tags) {
        if (tags != null && tags.size() > 0) {
            JPushInterface.deleteTags(mContext, sequence, tags);
        }
    }

    @Override
    public void cleanTags(int sequence) {
        JPushInterface.cleanTags(mContext, sequence);
    }

    @Override
    public void deleteAlias(int sequence) {
        JPushInterface.deleteAlias(mContext,sequence);
    }


}