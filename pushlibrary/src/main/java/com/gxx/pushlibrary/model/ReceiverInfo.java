package com.gxx.pushlibrary.model;

import java.io.Serializable;

/**
 * @date :2020/1/2 0002
 * @author : gaoxiaoxiong
 * @description:接收到的推送的消息
 **/
public class ReceiverInfo implements Serializable {
    /**
     * 推送平台
     */
    private int pushTarget ;
    /**
     * 标题
     */
    private String title = "";
    /**
     * 内容
     */
    private String content = "";

    /**
     * 额外数据
     */
    private String extra = "";

    /**
     * 消息类型
     */
    private String type;

    /**
     * 方法
     */
    private String method;


    public ReceiverInfo() {
    }

    public ReceiverInfo(int pushTarget, String title, String content, String extra, String type, String method) {
        this.pushTarget = pushTarget;
        this.title = title;
        this.content = content;
        this.extra = extra;
        this.type = type;
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPushTarget() {
        return pushTarget;
    }

    public void setPushTarget(int pushTarget) {
        this.pushTarget = pushTarget;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "ReceiverInfo{" +
                "pushTarget=" + pushTarget +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", extra='" + extra + '\'' +
                ", type='" + type + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
