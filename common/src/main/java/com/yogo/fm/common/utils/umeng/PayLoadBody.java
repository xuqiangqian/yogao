package com.yogo.fm.common.utils.umeng;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-24
 */

public class PayLoadBody {
    /**
     * 必填，通知栏提示文字
     */
    String ticker;
    /**
     * 必填，通知标题
     */
    String title;
    /**
     * 必填，通知文字描述
     */
    String text;
    /**
     * "go_app": 打开应用
     * "go_url": 跳转到URL
     * "go_activity": 打开特定的activity
     * "go_custom": 用户自定义内容。
     */
    String after_open;
    /**
     * 可选，收到通知是否震动，默认为"true"
     */
    String play_vibrate;
    /**
     * 可选，收到通知是否闪灯，默认为"true"
     */
    String play_lights;
    /**
     * 可选，收到通知是否发出声音，默认为"true"
     */
    String play_sound;
}