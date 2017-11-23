package com.bwie.end.view;

import com.bwie.end.bean.CartBean;

/**
 * Created by Lonely on 2017/11/23.
 */
public interface ViewCallBack {
    public void success(CartBean cartBean);
    public void failure(Exception e);
}
