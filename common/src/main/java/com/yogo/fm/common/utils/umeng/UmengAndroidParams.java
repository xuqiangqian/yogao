package com.yogo.fm.common.utils.umeng;

/**
 * @author qiqiang
 * @Description 友盟android推送参数
 * @date 2018-05-25
 */
public class UmengAndroidParams {
    /**
     * 必填，应用唯一标识
     */
    private String appkey;
    /**
     * 必填，时间戳，10位或者13位均可，时间戳有效期为10分钟
     */
    private String timestamp;
    /**
     * 必填，消息发送类型,其值可以为:
     * unicast-单播
     * listcast-列播，要求不超过500个device_token
     * filecast-文件播，多个device_token可通过文件形式批量发送
     * broadcast-广播
     * groupcast-组播，按照filter筛选用户群, 请参照filter参数
     * customizedcast，通过alias进行推送，包括以下两种case:
     * - alias: 对单个或者多个alias进行推送
     * - file_id: 将alias存放到文件后，根据file_id来推送
     */
    private String type;
    /**
     * 当type=unicast时, 必填, 表示指定的单个设备
     * 当type=listcast时, 必填, 要求不超过500个, 以英文逗号分隔
     */
    private String device_tokens;
    private UmengPayLoad payLoad;
}
