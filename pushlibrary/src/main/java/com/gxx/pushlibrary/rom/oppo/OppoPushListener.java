package com.gxx.pushlibrary.rom.oppo;

import java.util.List;

public interface OppoPushListener {
    /**
     * @date :2020/1/8 0008
     * @author : gaoxiaoxiong
     * @description:设置别名
     **/
    void setAliases(List<String> aliases);


    /**
     * 为某个用户设置订阅topic
     *
     * @param tags 用户设置的订阅topic
     */
    @Deprecated
    void setTags(List<String> tags);

    /**
     * 取消某个用户的订阅topic
     *
     * @param tags 用户设置的订阅topic
     */
    @Deprecated
    void unsetTags(List<String> tags);

    /**
     * 取消指定用户的aliases
     *
     * @param aliases 用户设置的别名
     */
    void unsetAliases(List<String> aliases);
}
