package com.bwie.end.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.end.R;
import com.bwie.end.callback.DianjiCallBack;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Lonely on 2017/11/23.
 */
public class MutiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<String> listbig;
    private ViewHolder1 holder1;
    private ViewHolder2 holder2;

    public MutiAdapter(Context context, List<String> listbig) {
        this.context = context;
        this.listbig = listbig;
    }

    //创建布局的
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == 0){
            //如果类型是0
            View view = View.inflate(context, R.layout.muti_item1,null);
            holder1 = new ViewHolder1(view);
            return holder1;
        }else{
            View view = View.inflate(context,R.layout.muti_item2,null);
            holder2 = new ViewHolder2(view);
            return holder2;
        }

    }
    //绑定 显示view
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //判断是哪个viewholder
        if(holder instanceof ViewHolder1){
            //将holder强转成viewholder1
            ViewHolder1 viewHolder1 = (ViewHolder1) holder;
            //设置显示的内容
            ImageLoader.getInstance().displayImage(listbig.get(position),viewHolder1.item1_imageview);

            viewHolder1.item1_imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //接口回调
                    dianjiCallBack.dianji(v,position);
                }
            });
        }else{
            //强转
            ViewHolder2 viewHolder2 = (ViewHolder2) holder;
            //设置显示的内容
            viewHolder2.item2_textview.setText("这是第"+(position+1)+"个");
            ImageLoader.getInstance().displayImage(listbig.get(position),viewHolder2.item2_imageview);

            //给控件设置点击事件
            viewHolder2.item2_imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dianjiCallBack.dianji(v,position);
                }
            });
        }
    }

    //条目的数量
    @Override
    public int getItemCount() {
        return listbig.size();//集合的长度
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;//两种类型,,就是0或1
    }


    //两种条目的viewholder
    static class ViewHolder1 extends RecyclerView.ViewHolder {

        private final ImageView item1_imageview;

        public ViewHolder1(View itemView) {
            super(itemView);
            item1_imageview = (ImageView) itemView.findViewById(R.id.item1_imageview);
        }
    }


    static class ViewHolder2 extends RecyclerView.ViewHolder{

        private final ImageView item2_imageview;
        private final TextView item2_textview;

        public ViewHolder2(View itemView) {
            super(itemView);
            item2_imageview = (ImageView) itemView.findViewById(R.id.item2_imageview);
            item2_textview = (TextView) itemView.findViewById(R.id.item2_textview);
        }
    }

    //接口的实现
    DianjiCallBack dianjiCallBack;
    public void setDianjiCallBack(DianjiCallBack dianjiCallBack){
        this.dianjiCallBack = dianjiCallBack;
    }
}
