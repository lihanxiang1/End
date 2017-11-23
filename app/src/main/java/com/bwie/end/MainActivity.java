package com.bwie.end;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.end.bean.PhoneBean;
import com.bwie.end.callback.NewCallBack;
import com.bwie.end.presenter.Mypersenter;
import com.bwie.end.view.SeocndActivity;
import com.bwie.end.view.ZhuceActivity;

public class MainActivity extends AppCompatActivity implements NewCallBack.LoginCallback {

    private EditText phone_text;
    private EditText pwd_text;
    private Mypersenter mypersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone_text = (EditText) findViewById(R.id.dl_phone);
        pwd_text = (EditText) findViewById(R.id.dl_pwd);

        mypersenter = new Mypersenter(this);

    }

    public void denglu(View view){
        mypersenter.Login(phone_text.getText().toString(),pwd_text.getText().toString());
    }
    public void zhuce(View view){
        Intent intent = new Intent(MainActivity.this, ZhuceActivity.class);
        startActivity(intent);
    }

    @Override
    public void LoginPhoneEmpty() {
        Toast.makeText(MainActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LoginPwdEmpty() {
        Toast.makeText(MainActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Loginsuccessa(final PhoneBean bean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,bean.getMsg(),Toast.LENGTH_SHORT).show();
                if(bean.getMsg().equals("登录成功")){
//                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,SeocndActivity.class);
                    startActivity(intent);
                }
               /* if(bean.indexOf("登录成功")>=0){
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,SeocndActivity.class);
                    startActivity(intent);
                }*/
            }
        });
    }

    @Override
    public void Loginerrora(final Integer objects) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,objects,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
