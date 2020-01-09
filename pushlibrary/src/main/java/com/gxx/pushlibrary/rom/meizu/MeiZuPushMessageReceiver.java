package com.gxx.pushlibrary.rom.meizu;

import android.content.Context;

import com.gxx.pushlibrary.R;
import com.gxx.pushlibrary.util.PushLogUtil;
import com.meizu.cloud.pushsdk.MzPushMessageReceiver;
import com.meizu.cloud.pushsdk.handler.MzPushMessage;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;

public class MeiZuPushMessageReceiver extends MzPushMessageReceiver {
    /**
     * 调用订阅方法后，会在此方法回调结果
     * 订阅方法：PushManager.register(context, appId, appKey)
     * @param context
     * @param registerStatus
     */
    @Override
    public void onRegisterStatus(Context context, RegisterStatus registerStatus) {
        PushLogUtil.e("[onRegisterStatus]"+registerStatus.toString());
    }

    /**
     * 调用取消订阅方法后，会在此方法回调结果
     * 取消订阅方法：PushManager.unRegister(context, appId, appKey)
     * @param context
     * @param unRegisterStatus
     */
    @Override
    public void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus) {
        PushLogUtil.e("[onUnRegisterStatus]"+unRegisterStatus.toString());
    }

    /**
     * 调用开关转换或检查开关状态方法后，会在此方法回调开关状态
     * 通知栏开关转换方法：PushManager.switchPush(context, appId, appKey, pushId, pushType, switcher)
     * 检查开关状态方法：PushManager.checkPush(context, appId, appKey, pushId)
     * @param context
     * @param pushSwitchStatus
     */

    @Override
    public void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus) {
        PushLogUtil.e("[onPushStatus]"+pushSwitchStatus.toString());
    }

    /**
     * 调用标签订阅、取消标签订阅、取消所有标签订阅和获取标签列表方法后，会在此方法回调标签相关信息
     * 标签订阅方法：PushManager.subScribeTags(context, appId, appKey, pushId, tags)
     * 取消标签订阅方法：PushManager.unSubScribeTags(context, appId, appKey, pushId,tags)
     * 取消所有标签订阅方法：PushManager.unSubScribeAllTags(context, appId, appKey, pushId)
     * 获取标签列表方法：PushManager.checkSubScribeTags(context, appId, appKey, pushId)
     * @param context
     * @param subTagsStatus
     */
    @Override
    public void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus) {
        PushLogUtil.e("[onSubTagsStatus]"+subTagsStatus.toString());
    }

    /**
     * 调用别名订阅、取消别名订阅和获取别名方法后，会在此方法回调别名相关信息
     * 别名订阅方法：PushManager.subScribeAlias(context, appId, appKey, pushId, alias)
     * 取消别名订阅方法：PushManager.unSubScribeAlias(context, appId, appKey, pushId, alias)
     * 获取别名方法：PushManager.checkSubScribeAlias(context, appId, appKey, pushId)
     * @param context
     * @param subAliasStatus
     */
    @Override
    public void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus) {
        PushLogUtil.e("[onSubAliasStatus]"+subAliasStatus.toString());
    }

    /**
     * 兼容旧版本 Flyme 系统中设置消息弹出后状态栏中的小图标
     * 同时请在相应分辨率 drawable 的文件夹内放置一张名称务必为 mz_push_notification_small_icon 的图片
     * @param pushNotificationBuilder
     */
    @Override
    public void onUpdateNotificationBuilder(PushNotificationBuilder pushNotificationBuilder){
        pushNotificationBuilder.setStatusBarIcon(R.drawable.mz_push_notification_small_icon);
        PushLogUtil.e("[onUpdateNotificationBuilder]"+pushNotificationBuilder.toString());
    }

    /**
     * 当用户点击通知栏消息后会在此方法回调
     * @param context
     * @param mzPushMessage
     */
    @Override
    public void onNotificationClicked(Context context, MzPushMessage mzPushMessage) {
        PushLogUtil.e("[onNotificationClicked]"+mzPushMessage.toString());
    }
    /**
     * 当推送的通知栏消息展示后且应用进程存在时会在此方法回调
     * @param context
     * @param mzPushMessage
     */
    @Override
    public void onNotificationArrived(Context context, MzPushMessage mzPushMessage) {
        PushLogUtil.e("[onNotificationArrived]"+mzPushMessage.toString());
    }

}
