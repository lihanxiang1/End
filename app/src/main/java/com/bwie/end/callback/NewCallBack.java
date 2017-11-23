package com.bwie.end.callback;

import com.bwie.end.bean.PhoneBean;
import com.bwie.end.bean.ShopBean02;

/**
 * Created by Lonely on 2017/11/22.
 */
public interface NewCallBack {

    public interface LoginCallback{
        public void LoginPhoneEmpty();
        public void LoginPwdEmpty();
        public void Loginsuccessa(PhoneBean bean);
        public void Loginerrora(Integer objects);
    }
    public interface LoginSuccess{
        public void Loginsuccess(PhoneBean bean);
        public void Loginerror(Integer objects);
    }
    public interface ZhuCeCallback{
        public void ZhuPhoneEmpty();
        public void ZhuPwdEmpty();
        public void Zhucesuccessa(ShopBean02 bean);
        public void Zhuceerrora(Integer objects);
    }
    public interface ZhuceSuccess{
        public void zhucesuccess(ShopBean02 bean);
        public void Zhuceerror(Integer objects);
    }

}
