package com.gxx.pushlibrary.rom.jiguang;

import java.util.Set;

public interface IJGPushListener {
    /**
     * @date :2020/1/3 0003
     * @author : gaoxiaoxiong
     * @description:设置别名
     * @param sequence 用户自定义的操作序列号，同操作结果一起返回，用来标识一次操作的唯一性
     * @param alias 设备别名
     **/
    void setAlias(String alias,int sequence);


    /**
     * @date :2020/1/3 0003
     * @author : gaoxiaoxiong
     * @description:新增标签
     * @param sequence 用户自定义的操作序列号，同操作结果一起返回，用来标识一次操作的唯一性。
     * @param tags 该设备的标签，一个设备可以有很多个标签
     * 使用该功能需要先注册激光推送
     **/
    void addTags(int sequence,Set<String> tags);

    /**
     * @date :2020/1/3 0003
     * @author : gaoxiaoxiong
     * @description:删除指定标签
     **/
    void deleteTags(int sequence,Set<String> tags);

    /**
     * @date :2020/1/3 0003
     * @author : gaoxiaoxiong
     * @description:清除所有标签
     **/
    void cleanTags(int sequence);


    /**
     * @date :2020/1/7 0007
     * @author : gaoxiaoxiong
     * @description:删除别名
     **/
    void deleteAlias(int sequence);
}
