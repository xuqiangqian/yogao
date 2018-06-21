package com.yogo.fm.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.yogo.fm.common.response.FmResponse;
import com.yogo.fm.common.response.FmResponseCode;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qiqiang
 * @Description httpClient请求工具类
 * @date 2018-05-24
 */
public class HttpClientUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
    private static HttpClient client = HttpClients.createDefault();

    /**
     * 带参数的post请求
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static FmResponse post(String url, JSONObject params) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(5000)
                .setSocketTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();
        if (params != null && !params.isEmpty()) {
            StringEntity httpEntity = new StringEntity(params.toJSONString(), "utf-8");
            httpPost.setEntity(httpEntity);
        }
        httpPost.setConfig(config);
        HttpResponse response = client.execute(httpPost);
        return getFmResponse(response);
    }

    /**
     * 包装返回结果
     * @param response
     * @return
     * @throws IOException
     */
    private static FmResponse getFmResponse(HttpResponse response) throws IOException {
        HttpEntity resEntity = response.getEntity();
        String message = EntityUtils.toString(resEntity, "utf-8");
        logger.info("response body : " + message);
        if (response.getStatusLine().getStatusCode() == FmResponseCode.OK.code) {
            return FmResponse.ok(message);
        } else {
            return FmResponse.error("请求失败");
        }
    }

    /**
     * 不带参数的post请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static FmResponse post(String url) throws Exception {
        return post(url, null);
    }

    /**
     * 带参数的get请求
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static FmResponse get(String url, JSONObject params) throws Exception {
        if (params != null && !params.isEmpty()) {
            List<NameValuePair> requestParams = new ArrayList<>();
            params.forEach((key, value) -> requestParams.add(new BasicNameValuePair((String) key, (String) value)));
            url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(requestParams, Consts.UTF_8));
        }
        RequestConfig config = RequestConfig.custom().setConnectTimeout(5000)
                .setSocketTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config);
        HttpResponse response = client.execute(httpGet);
        return getFmResponse(response);
    }

    /**
     * 不带参数的get请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static FmResponse get(String url) throws Exception {
        return get(url, null);
    }

    public static void main(String[] args) throws Exception {
        JSONObject object = new JSONObject();
        object.put("username", "admin");
        object.put("password", "000000");
        System.out.println(get("http://www.baidu.com", object));
    }
}
