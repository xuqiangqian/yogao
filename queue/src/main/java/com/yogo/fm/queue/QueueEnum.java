package com.yogo.fm.queue;

/**
 * @author qiqiang
 * @Description
 * @date 2018-06-07
 */
public enum QueueEnum {
    /**
     *
     */
    NULL("null.queue","null"),
    ACCOUNT_ADD("account.add.queue","account.add");
    /**
     * 队列名称
     */
    private String name;
    /**
     * 队列路由键
     */
    private String routingKey;

    QueueEnum(String name, String routingKey) {
        this.name = name;
        this.routingKey = routingKey;
    }

    public String getName() {
        return name;
    }

    public String getRoutingKey() {
        return routingKey;
    }
}
