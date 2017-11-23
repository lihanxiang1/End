package com.bwie.end.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.end.R;
import com.bwie.end.bean.ShopBean02;
import com.bwie.end.callback.NewCallBack;
import com.bwie.end.presenter.Mypersenter;

public class ZhuceActivity extends AppCompatActivity implements NewCallBack.ZhuCeCallback {

    private EditText zc_phone;
    private EditText zc_pwd;
    private Mypersenter mypersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);

        mypersenter = new Mypersenter(this);

        zc_phone = (EditText) findViewById(R.id.zc_phone);
        zc_pwd = (EditText) findViewById(R.id.zc_pwd);
    }

    public void zhuceyonghu(View view){
        mypersenter.Zhuce(zc_phone.getText().toString(),zc_pwd.getText().toString());
    }

    @Override
    public void ZhuPhoneEmpty() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ZhuceActivity.this,"手机号为空",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void ZhuPwdEmpty() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ZhuceActivity.this,"密码为空",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void Zhucesuccessa(final ShopBean02 bean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ZhuceActivity.this,bean.getMsg(),Toast.LENGTH_SHORT).show();
                if(bean.getMsg().equals("注册成功")){
                    finish();
                }
            }
        });
    }

    @Override
    public void Zhuceerrora(final Integer objects) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ZhuceActivity.this,objects,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
