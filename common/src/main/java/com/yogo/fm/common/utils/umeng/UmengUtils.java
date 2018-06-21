package com.yogo.fm.common.utils.umeng;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yogo.fm.common.response.FmResponse;
import com.yogo.fm.common.utils.HttpClientUtils;
import com.yogo.fm.common.utils.MD5Utils;
import com.yogo.fm.common.utils.SpringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-24
 */
@Component
public class UmengUtils {

    public static void AndroidUnicast(String deviceToken,UmengPayLoad payLoad) throws Exception {
        UmengConfig config = SpringUtils.getBean(UmengConfig.class);
        long timestamp = System.currentTimeMillis();
        JSONObject params = new JSONObject();
        String sign = getSign("POST",config.getHttpsUrl(),JSONObject.toJSONString(params),config.getMasterSecret());
        FmResponse post = HttpClientUtils.post(config.getHttpsUrl() + "?sign=" + sign, params);
        System.out.println(JSON.toJSONString(post));
    }

    public void AndroidListCast(List<String> deviceTokens, JSONObject payLoad) {

    }

    public void AndroidBroad(JSONObject payLoad) {

    }

    public void AndroidGroupCast(JSONObject payLoad) {

    }

    private static String getSign (String method,String url,String postBody,String masterSecret){
        return MD5Utils.md5Hex(method+url+postBody+masterSecret);
    }


}
