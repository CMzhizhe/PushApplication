package com.gxx.pushlibrary.rom.huawei;


public interface IHuaWeiPushListener {

    /**
     * @date :2020/1/3 0003
     * @author : gaoxiaoxiong
     * @description:新增标签
     * @param tags 该设备的标签，一个设备可以有很多个标签
     *
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
     * @description:删除token
     **/
    void deleteToken();
}
