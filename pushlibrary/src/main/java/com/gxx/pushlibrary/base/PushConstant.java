package com.gxx.pushlibrary.base;

/**
 * @author : gaoxiaoxiong
 * @date :2020/1/2 0002
 * @description:一些静态常量配置
 **/
public class PushConstant {
    public static final int JPUSH = 0, HUAWEI = 1, OPPO = 2, XIAOMI = 3, VIVO = 4, MEIZU = 5;

    public static final String PUSH_RECEIVE_INIT_RESULT = "com.gxx.pushlibrary.ACTION_RECEIVE_INIT_RESULT";//初始化成功之后调用
    public static final String PUSH_RECEIVE_NOTIFICATION = "com.gxx.pushlibrary.ACTION_RECEIVE_NOTIFICATION";// 接收到通知，会主动显示在通知栏的
    public static final String PUSH_RECEIVE_NOTIFICATION_CLICK = "com.gxx.pushlibrary.ACTION_RECEIVE_NOTIFICATION_CLICK";//用户点击了通知
    public static final String PUSH_RECEIVE_MESSAGE = "com.gxx.pushlibrary.ACTION_RECEIVE_MESSAGE";//接收到消息推送，不会显示在通知栏
    public static final String PUSH_RECEIVE_OTHER_OPTIONS="com.gxx.pushlibrary.OTHER_OPTIONS";//其它事件操作
    public static final String PUSH_RECEIVE_ALIAS="com.gxx.pushlibrary.ACTION_RECEIVE_ALIAS";//设置别名

    public static final String PUSH_INTENT_RECEIVER_INFO = "receiverInfo";
}
