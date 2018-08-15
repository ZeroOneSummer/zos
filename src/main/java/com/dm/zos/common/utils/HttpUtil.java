package com.dm.zos.common.utils;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpUtil {


    public static String get(String url) throws IOException {
        //get请求
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println("响应码:" + response.code());
            System.out.println("响应:" + response.body().string());
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static String postJson(String url, Object data) throws IOException {
        //json post请求
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(10, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(10, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(10, TimeUnit.SECONDS);
        //允许请求重定向，默认是true
        okHttpBuilder.followRedirects(true);
        OkHttpClient okHttpClient = okHttpBuilder.build();


        MediaType JSONMediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSONMediaType, JSONObject.toJSONString(data));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

    }
}
