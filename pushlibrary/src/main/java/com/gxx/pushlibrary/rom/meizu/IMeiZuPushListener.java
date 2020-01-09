package com.gxx.pushlibrary.rom.meizu;

public interface IMeiZuPushListener {

    /**
     * @date :2020/1/3 0003
     * @author : gaoxiaoxiong
     * @description:设置别名
     * @param alias 设备别名
     **/
    void setAlias(String alias);

    /**
     * @date :2020/1/3 0003
     * @author : gaoxiaoxiong
     * @description: 添加别名
     * @param tags 该设备的标签，一个设备可以有很多个标签
     * 使用该功能需要先注册激光推送
     **/
    void addTags(String tags);

    /**
     * @date :2020/1/3 0003
     * @author : gaoxiaoxiong
     * @description:删除指定标签
     **/
    void deleteTags(String tags);

    /**
     * @date :2020/1/7 0007
     * @author : gaoxiaoxiong
     * @description:删除别名
     **/
    void deleteAlias(String alias);
}
