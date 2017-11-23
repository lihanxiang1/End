package com.bwie.end.model;

import com.bwie.end.bean.CartBean;
import com.bwie.end.bean.PhoneBean;
import com.bwie.end.bean.ShopBean02;
import com.bwie.end.callback.NewCallBack;
import com.bwie.end.okhttp.AbstractUiCallBack;
import com.bwie.end.okhttp.OkhttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lonely on 2017/11/22.
 */
public class Mymodel {

    public static void getdata(String phone, String pwd, final NewCallBack.LoginSuccess loginSuccess) {
        Map<String , String> map = new HashMap<>();
        map.put("mobile" , phone);
        map.put("password" , pwd);
        //?mobile="+phone+"&password="+pwd
        OkhttpUtils.getInstance().asy(map , "http://120.27.23.105/user/login", new AbstractUiCallBack<PhoneBean>() {
            @Override
            public void success(PhoneBean phoneBean) {
                loginSuccess.Loginsuccess(phoneBean);
            }

            @Override
            public void failure(Exception e) {
                loginSuccess.Loginerror(1);
            }
        });


    }

    public static void Zhuce(String phone, String pwd, final NewCallBack.ZhuceSuccess zhuceSuccess) {
        Map<String , String> map = new HashMap<>();
        map.put("mobile" , phone);
        map.put("password" , pwd);
        OkhttpUtils.getInstance().asy(map, "http://120.27.23.105/user/reg", new AbstractUiCallBack<ShopBean02>() {
            @Override
            public void success(ShopBean02 shopBean02) {
                zhuceSuccess.zhucesuccess(shopBean02);
            }

            @Override
            public void failure(Exception e) {
                zhuceSuccess.Zhuceerror(1);
            }
        });

    }
    public void getData(final ModelCallBack modelCallBack){
        //访问接口
        String path = "http://120.27.23.105/product/getCarts?uid=100";
        OkhttpUtils.getInstance().asy(null, path, new AbstractUiCallBack<CartBean>() {

            @Override
            public void success(CartBean cartBean) {
                modelCallBack.success(cartBean);
            }

            @Override
            public void failure(Exception e) {
                modelCallBack.failure(e);
            }
        });
    }
    /*OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。

        FormBody build = new FormBody.Builder()
                .add("mobile", phone)
                .add("password", pwd)
                .build();
        //传递键值对参数
        Request request = new Request.Builder()//创建Request 对象。
                .url("http://120.27.23.105/user/login")
                .post(build)//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                loginSuccess.Loginerror(1);
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                String string = response.body().string();
                loginSuccess.Loginsuccess(string);
            }
        });//回调方法的使用与get异步请求相同，此时略。*/
    /*OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。

        FormBody build = new FormBody.Builder()
                .add("mobile", phone)
                .add("password", pwd)
                .build();
        //传递键值对参数
        final Request request = new Request.Builder()//创建Request 对象。
                .url("http://120.27.23.105/user/reg")
                .post(build)//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                zhuceSuccess.Zhuceerror(1);
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                String string = response.body().string();
                zhuceSuccess.zhucesuccess(string);
            }
        });*/
}
