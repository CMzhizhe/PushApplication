package com.gxx.pushlibrary.rom;

import android.content.Context;



/**
 * 初始化推送平台的基类
 * Created by luoming on 2018/5/28.
 */
public abstract class BasePushTargetInit{
    protected Context mContext;
    /**
     * 推送初始化
     */
    public BasePushTargetInit(Context context) {
        this.mContext = context;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
}
