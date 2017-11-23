package com.bwie.end.model;

import com.bwie.end.bean.CartBean;

/**
 * Created by Lonely on 2017/11/23.
 */
public interface ModelCallBack {
    public void success(CartBean cartBean);
    public void failure(Exception e);
}
