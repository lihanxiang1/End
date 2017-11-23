package com.bwie.end.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.end.R;
import com.bwie.end.adapter.RecyAdapter;
import com.bwie.end.bean.CartBean;
import com.bwie.end.presenter.MyPresenter;

public class SeocndActivity extends AppCompatActivity implements ViewCallBack {

    private RecyclerView recyclerView;
    private TextView total_price;
    private TextView total_num;
    private CheckBox quanxuan;
    private MyPresenter myPresenter;
    private RecyAdapter recyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seocnd);

        //http://120.27.23.105/product/getCarts?uid=100

        recyclerView = (RecyclerView) findViewById(R.id.recycler_View);
        total_price = (TextView) findViewById(R.id.total_price);
        total_num = (TextView) findViewById(R.id.total_num);
        quanxuan = (CheckBox) findViewById(R.id.quanxuan);

        quanxuan.setTag(1);//1为不选中
        LinearLayoutManager manager = new LinearLayoutManager(SeocndActivity.this,LinearLayoutManager.VERTICAL,false);
        //new出适配器
        recyAdapter = new RecyAdapter(this);
        myPresenter = new MyPresenter(this);
        //调用presenter里面的请求数据的方法
        myPresenter.getData();

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(recyAdapter);

        //调用recyAdapter里面的接口,设置 全选按钮 总价 总数量
        recyAdapter.setUpdateListener(new RecyAdapter.UpdateListener() {
            @Override
            public void setTotal(String total, String num, boolean allCheck) {
                //设置ui的改变
                total_num.setText("共"+num+"件商品");//总数量
                total_price.setText("总价 :¥"+total+"元");//总价
                if(allCheck){
                    quanxuan.setTag(2);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_selected);
                }else{
                    quanxuan.setTag(1);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_unselected);
                }
                quanxuan.setChecked(allCheck);
            }
        });

        //这里只做ui更改, 点击全选按钮,,调到adapter里面操作
        quanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用adapter里面的方法 ,,把当前quanxuan状态传递过去
                int tag = (int) quanxuan.getTag();
                if(tag==1){
                    quanxuan.setTag(2);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_selected);
                }else{
                    quanxuan.setTag(1);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_unselected);
                }
                recyAdapter.quanXuan(quanxuan.isChecked());
            }
        });
    }

    //实现接口,重写的方法
    @Override
    public void success(CartBean cartBean) {
        //拿到返回来的数据 ,, 传给适配器数据
        recyAdapter.add(cartBean);
    }

    @Override
    public void failure(final Exception e) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SeocndActivity.this,"error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //调用p层的解除绑定
        myPresenter.detach();
    }
}
