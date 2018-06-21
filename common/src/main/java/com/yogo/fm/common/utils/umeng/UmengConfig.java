package com.yogo.fm.common.utils.umeng;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-24
 */
@Component
@ConfigurationProperties(prefix = "umeng")
@PropertySource("classpath:config/application-dev.yml")
public class UmengConfig {
    private String appKey;
    private String masterSecret;
    private String httpsUrl;

    public String getAppKey() {
        return appKey;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public String getHttpsUrl() {
        return httpsUrl;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }

    public void setHttpsUrl(String httpsUrl) {
        this.httpsUrl = httpsUrl;
    }
}
