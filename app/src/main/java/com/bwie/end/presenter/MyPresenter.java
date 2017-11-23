package com.bwie.end.presenter;

import com.bwie.end.bean.CartBean;
import com.bwie.end.model.ModelCallBack;
import com.bwie.end.model.Mymodel;
import com.bwie.end.view.ViewCallBack;

/**
 * Created by Lonely on 2017/11/23.
 */
public class MyPresenter {
    Mymodel myModel = new Mymodel();
    ViewCallBack viewCallBack;
    public MyPresenter(ViewCallBack viewCallBack) {
        this.viewCallBack = viewCallBack;
    }

    //调用model 层的请求数据
    public void getData(){
        myModel.getData(new ModelCallBack() {
            @Override
            public void success(CartBean cartBean) {
                if(viewCallBack!=null) {
                    viewCallBack.success(cartBean);
                }
            }

            @Override
            public void failure(Exception e) {
                if(viewCallBack!=null) {
                    viewCallBack.failure(e);
                }
            }
        });
    }

    /**
     * 防止内存泄露
     * */
    public void detach(){
        viewCallBack=null;
    }
}
