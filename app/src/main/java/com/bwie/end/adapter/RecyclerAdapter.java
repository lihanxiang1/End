package com.bwie.end.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bwie.end.R;
import com.bwie.end.callback.DianjiCallBack;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;
import java.util.Random;

/**
 * Created by Lonely on 2017/11/23.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ImageViewHolder> {

    Context context;
    List<String> listbig;
    //定义宽度
    private int itemWidth;

    public RecyclerAdapter(Context context, List<String> listbig) {
        this.listbig = listbig;
        this.context = context;

        //设置宽度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        itemWidth = width / 3;//定义固定的宽度
    }
    @Override
    //创建viewholder
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建一个view
        View view = View.inflate(context, R.layout.layout_item,null);

        //newviewholder将引入的布局视图传进去
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);
        return imageViewHolder;
    }

    @Override
    //绑定view 显示数据
    public void onBindViewHolder(ImageViewHolder holder, final int position) {
        //设置高
        ViewGroup.LayoutParams params = holder.itemimageview.getLayoutParams();

        //初始高度300
        int itemHeight = 300;

        itemHeight = new Random().nextInt(500);
        if(itemHeight < 300){
            itemHeight = 300;
        }

        //给imageview设置宽高
        params.width = itemWidth;
        params.height = itemHeight;

        holder.itemimageview.setLayoutParams(params);

        //显示图片
        ImageLoader.getInstance().displayImage(listbig.get(position),holder.itemimageview);

        //设置点击事件,
        holder.itemimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dianjiCallBack.dianji(v,position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listbig.size();//集合的长度
    }

    //必须写的viewholder
    static class ImageViewHolder extends RecyclerView.ViewHolder {

        private final ImageView itemimageview;

        public ImageViewHolder(View itemView) {
            super(itemView);
            //itemview是传进来的view视图,获取里面的id
            itemimageview = (ImageView) itemView.findViewById(R.id.item_imageview);
        }
    }


    //接口的实现
    DianjiCallBack dianjiCallBack;
    public void setDianjiCallBack(DianjiCallBack dianjiCallBack){
        this.dianjiCallBack = dianjiCallBack;
    }
}
