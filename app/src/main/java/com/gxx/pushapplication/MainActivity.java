package com.gxx.pushapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;

import com.gxx.pushlibrary.PushTargetManager;
import com.gxx.pushlibrary.rom.BasePushTargetInit;
import com.gxx.pushlibrary.rom.huawei.HuaWeiInit;
import com.gxx.pushlibrary.rom.jiguang.JPushInit;
import com.gxx.pushlibrary.rom.meizu.MeiZuInit;
import com.gxx.pushlibrary.rom.xiaomi.XiaomiInit;

import static com.gxx.pushlibrary.base.PushConstant.PUSH_RECEIVE_INIT_RESULT;
import static com.gxx.pushlibrary.base.PushConstant.PUSH_RECEIVE_MESSAGE;
import static com.gxx.pushlibrary.base.PushConstant.PUSH_RECEIVE_NOTIFICATION;
import static com.gxx.pushlibrary.base.PushConstant.PUSH_RECEIVE_NOTIFICATION_CLICK;
import static com.gxx.pushlibrary.base.PushConstant.PUSH_RECEIVE_OTHER_OPTIONS;

//目前，所有的jar包都是 2020年最新的。
public class MainActivity extends AppCompatActivity {
    private PushBroadcastReceiver pushBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //这里初始化各种平台的对象
        PushTargetManager.getInstance().init(this.getApplication());
        //拿到对应的对象
        BasePushTargetInit basePushTargetInit = PushTargetManager.getInstance().getmPushTarget();
        String aligis="abcd";
        if (basePushTargetInit!=null && !TextUtils.isEmpty(aligis)){
            if (basePushTargetInit instanceof JPushInit){
                JPushInit jPushInit = (JPushInit) basePushTargetInit;
                jPushInit.deleteAlias(0);
            }else if (basePushTargetInit instanceof HuaWeiInit){
                HuaWeiInit huaWeiInit = (HuaWeiInit) basePushTargetInit;
                huaWeiInit.deleteToken();
            }else if (basePushTargetInit instanceof XiaomiInit){
                XiaomiInit xiaomiInit = (XiaomiInit) basePushTargetInit;
                xiaomiInit.deleteAlias(aligis);
            }else if (basePushTargetInit instanceof MeiZuInit){
                MeiZuInit meiZuInit = (MeiZuInit) basePushTargetInit;
                meiZuInit.deleteAlias(aligis);
            }
        }

        //注册广播监听
        if (pushBroadcastReceiver==null){
            pushBroadcastReceiver = new PushBroadcastReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PUSH_RECEIVE_INIT_RESULT);//初始化成功回调
        intentFilter.addAction(PUSH_RECEIVE_MESSAGE);//得到透传消息
        intentFilter.addAction(PUSH_RECEIVE_NOTIFICATION);//收到后台推送
        intentFilter.addAction(PUSH_RECEIVE_NOTIFICATION_CLICK);//用户点击推送
        intentFilter.addAction(PUSH_RECEIVE_OTHER_OPTIONS);//其它事情操作
        LocalBroadcastManager.getInstance(this).registerReceiver(pushBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pushBroadcastReceiver!=null){
            LocalBroadcastManager.getInstance(this).unregisterReceiver(pushBroadcastReceiver);
            pushBroadcastReceiver = null;
        }
    }
}
