package com.bwie.end.presenter;

import android.text.TextUtils;

import com.bwie.end.bean.PhoneBean;
import com.bwie.end.bean.ShopBean02;
import com.bwie.end.callback.NewCallBack;
import com.bwie.end.model.Mymodel;

/**
 * Created by Lonely on 2017/11/22.
 */
public class Mypersenter {
    private  NewCallBack.LoginCallback callback;
    public Mypersenter(NewCallBack.LoginCallback callback) {
        this.callback =callback;
    }
    public NewCallBack.ZhuCeCallback zhuce;
    public Mypersenter(NewCallBack.ZhuCeCallback zhuce) {
        this.zhuce=zhuce;
    }

    public void Login(String phone, String pwd) {
        if(TextUtils.isEmpty(phone)){
            callback.LoginPhoneEmpty();
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            callback.LoginPwdEmpty();
            return;
        }
        Mymodel.getdata(phone,pwd, new NewCallBack.LoginSuccess() {

            @Override
            public void Loginsuccess(PhoneBean bean) {
                callback.Loginsuccessa(bean);
            }

            @Override
            public void Loginerror(Integer objects) {
                callback.Loginerrora(objects);
            }
        });
    }


    public  void Zhuce(String phone, String pwd) {
        if(TextUtils.isEmpty(phone)){
            zhuce.ZhuPhoneEmpty();
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            zhuce.ZhuPwdEmpty();
            return;
        }
        Mymodel.Zhuce(phone,pwd, new NewCallBack.ZhuceSuccess() {
            @Override
            public void zhucesuccess(ShopBean02 bean) {
                zhuce.Zhucesuccessa(bean);
            }

            @Override
            public void Zhuceerror(Integer objects) {
                zhuce.Zhuceerrora(1);
            }
        });
    }
}
