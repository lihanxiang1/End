package com.bwie.end.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwie.end.R;
import com.bwie.end.adapter.MutiAdapter;
import com.bwie.end.adapter.RecyclerAdapter;
import com.bwie.end.callback.DianjiCallBack;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

public class RecycleviewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> list;
    private SpringView springView;
    private List<String> listbig = new ArrayList<>();
    private MutiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        springView = (SpringView) findViewById(R.id.springview);
        list = new ArrayList<>();
        //集合里装着图片的网址
        list.add("http://f10.baidu.com/it/u=2881303562,336932824&fm=72");
        list.add("http://f11.baidu.com/it/u=681755471,2018070071&fm=72");
        list.add("http://f10.baidu.com/it/u=960650584,863938083&fm=72");
        list.add("http://img0.imgtn.bdimg.com/it/u=783060973,4278100629&fm=27&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=3743124979,3234956668&fm=27&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=3468613159,957707785&fm=27&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=2971205354,485034289&fm=27&gp=0.jpg");
        list.add("http://f10.baidu.com/it/u=2881303562,336932824&fm=72");
        list.add("http://f11.baidu.com/it/u=681755471,2018070071&fm=72");
        list.add("http://f10.baidu.com/it/u=960650584,863938083&fm=72");
        list.add("http://img0.imgtn.bdimg.com/it/u=783060973,4278100629&fm=27&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=3743124979,3234956668&fm=27&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=3468613159,957707785&fm=27&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=2971205354,485034289&fm=27&gp=0.jpg");
        listbig.addAll(list);

        //new适配器,,RecyclerAdapter里面是普通的
        final RecyclerAdapter adapter = new RecyclerAdapter(RecycleviewActivity.this,listbig);
        //MutiAdapter是多条目的
//        adapter = new MutiAdapter(RecycleviewActivity.this,listbig);
        //设置布局管理器

//        recyclerView.setLayoutManager(new GridLayoutManager(RecycleviewActivity.this,3));//九宫格布局
        //瀑布式布局
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL));

//        recyclerView.setLayoutManager(new LinearLayoutManager(RecycleviewActivity.this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        //接口的实现,new接口
        adapter.setDianjiCallBack(new DianjiCallBack() {
            @Override
            public void dianji(View view, int position) {
                Toast.makeText(RecycleviewActivity.this,position+"",Toast.LENGTH_SHORT).show();
            }
        });
        springView.setHeader(new DefaultHeader(this));
        springView.setFooter(new DefaultFooter(this));

        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                springView.onFinishFreshAndLoad();
                listbig.addAll(0,list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadmore() {
                //上拉加载
                springView.onFinishFreshAndLoad();
                listbig.addAll(list);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
