package com.gxx.pushlibrary;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.gxx.pushlibrary.rom.BasePushTargetInit;
import com.gxx.pushlibrary.rom.huawei.HuaWeiInit;
import com.gxx.pushlibrary.rom.jiguang.JPushInit;
import com.gxx.pushlibrary.rom.meizu.MeiZuInit;
import com.gxx.pushlibrary.rom.xiaomi.XiaomiInit;
import com.gxx.pushlibrary.util.ApplicationUtil;
import com.gxx.pushlibrary.util.PushLogUtil;
import com.gxx.pushlibrary.util.RomUtils;
import com.heytap.mcssdk.PushManager;
import com.vivo.push.PushClient;

import static com.gxx.pushlibrary.base.PushConstant.HUAWEI;
import static com.gxx.pushlibrary.base.PushConstant.JPUSH;
import static com.gxx.pushlibrary.base.PushConstant.MEIZU;
import static com.gxx.pushlibrary.base.PushConstant.OPPO;
import static com.gxx.pushlibrary.base.PushConstant.VIVO;
import static com.gxx.pushlibrary.base.PushConstant.XIAOMI;


/**
 * @author yolo.huang
 * @date 2018/12/21
 */

public class PushTargetManager {
    public static PushTargetManager pushTargetManager;
    /**
     * 当前的推送平台，默认为极光 JPUSH
     */
    private BasePushTargetInit mPushTarget;

    public static PushTargetManager getInstance() {
        if (pushTargetManager == null) {
            synchronized (PushTargetManager.class) {
                if (pushTargetManager == null) {
                    pushTargetManager = new PushTargetManager();
                }
            }
        }
        return pushTargetManager;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Application context) {
        setDebug(true);
        int phoneType = getPhoneType(context.getApplicationContext());
        //判断各大平台的ID是否为空
        boolean isEmptyKey = true;
        switch (phoneType) {
            case XIAOMI:
                if (!TextUtils.isEmpty(ApplicationUtil.getMetaData(context, "XIAOMI_APPID"))) {
                    isEmptyKey = false;
                }
                break;
            case MEIZU:
                if (!TextUtils.isEmpty(ApplicationUtil.getMetaData(context, "MEIZU_APPID"))) {
                    isEmptyKey = false;
                }
                break;
            case HUAWEI:
                if (!TextUtils.isEmpty(ApplicationUtil.getMetaData(context, "HUAWEI_APPID"))) {
                    isEmptyKey = false;
                }
                break;
        }

        if (isEmptyKey) {
            if (!TextUtils.isEmpty(ApplicationUtil.getMetaData(context, "JPUSH_APPKEY_VALUEV"))) {
                phoneType = JPUSH;
            } else {
                return;
            }
        }
        switch (phoneType) {
      /*
            case OPPO:
                mPushTarget = new OppoInit(context);
                break;
            case VIVO:
                mPushTarget = new VivoInit(context);
                break;*/
            case MEIZU:
                mPushTarget = new MeiZuInit(context);
                break;
            case XIAOMI:
                mPushTarget = new XiaomiInit(context);
                break;
            case HUAWEI:
                mPushTarget = new HuaWeiInit(context);
                break;
            default:
                mPushTarget = new JPushInit(context);
                break;
        }
    }

    /**
     * @date :2020/1/3 0003
     * @author : gaoxiaoxiong
     * @description:设置日志模式
     **/
    public void setDebug(boolean debug) {
        PushLogUtil.setDebug(debug);
    }


    private int getPhoneType(Context context) {
        int phoneType;
        if (RomUtils.isOPPORom() && PushManager.isSupportPush(context)) {
            phoneType = OPPO;
        } else if (RomUtils.isHuaweiRom() ) {
            phoneType = HUAWEI;
        } else if (RomUtils.isMiuiRom() ) {
            phoneType = XIAOMI;
        } else if (RomUtils.isVivoRom() && PushClient.getInstance(context).isSupport() ) {
            phoneType = VIVO;
        } else if (RomUtils.isMeiZuRom()) {
            phoneType = MEIZU;
        } else {
            phoneType = JPUSH;
        }
        return phoneType;
    }

    public BasePushTargetInit getmPushTarget() {
        return mPushTarget;
    }

}
