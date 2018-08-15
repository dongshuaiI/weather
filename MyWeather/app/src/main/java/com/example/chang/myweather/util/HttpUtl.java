package com.example.chang.myweather.util;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtl {

    public static void sendOkHttpRequest(String address, Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request build = new Request.Builder().url(address).build();
        client.newCall(build).enqueue(callback);
    }

}
