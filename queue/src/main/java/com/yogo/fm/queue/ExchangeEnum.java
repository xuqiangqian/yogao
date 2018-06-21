package com.yogo.fm.queue;

/**
 * @author qiqiang
 * @Description 队列交换配置信息
 * @date 2018-06-07
 */
public enum ExchangeEnum {
    /**
     * 管理员修改或添加消息
     */
    NULL("null"),
    ACCOUNT_EXCHANGE("account.exchange")
    ;
    private String value;


    ExchangeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
