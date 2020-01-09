package com.gxx.pushlibrary.handle;

import android.content.Context;

import com.gxx.pushlibrary.model.ReceiverInfo;

/**
 * @author yolo.huang
 * @date 2018/12/27
 */

public interface IPushReceiverListener {
    /**
     * 其它事件操作
     *
     * @param context
     * @param info
     */
    void onOtherMethodsOption(Context context, ReceiverInfo info);

    /**
     * 收到通知后会调用此接口
     *
     * @param context
     * @param info
     */
    void onReceiveNotification(Context context, ReceiverInfo info);


    /**
     * 点击通知后会调用此接口
     *
     * @param context
     * @param info
     */
    void onReceiveNotificationClick(Context context, ReceiverInfo info);


    /**
     * 收到消息后会调用此接口
     * 透传消息
     * @param context
     * @param info
     */
    void onReceiveMessage(Context context, ReceiverInfo info);


    /**
     * @date :2020/1/8 0008
     * @author : gaoxiaoxiong
     * @description:别名设置监听
     **/
    void onAlimsResult(Context context,ReceiverInfo info);

    /**
     * 初始化成功后会调用此接口
     *
     * @param context
     * @param info
     */
    void onInitResult(Context context, ReceiverInfo info);

}
