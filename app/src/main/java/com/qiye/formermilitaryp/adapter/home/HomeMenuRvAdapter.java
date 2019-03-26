package com.qiye.formermilitaryp.adapter.home;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qiye.formermilitaryp.R;
import com.qiye.formermilitaryp.bean.response.HomeMenuBean;

import java.util.ArrayList;
import java.util.List;

public class HomeMenuRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private List<HomeMenuBean.DataBean.ListBean> data = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mItemClickListener;

    public HomeMenuRvAdapter(Context context, List<HomeMenuBean.DataBean.ListBean> list) {
        this.mContext = context;
        this.data = list;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


//    public Careful_RecycleViewAdapter(Context mContext, List<String> goodsList) {
//        this.data = goodsList;
//        this.mContext = mContext;
//    }

/*
    public Today_RecycleViewAdapter(List<String> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }*/


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.home_menu_rv_item, null);
        RecyclerView.ViewHolder holder = new MyHolder(view);
        view.setOnClickListener(this);
        return holder;

    }


    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        initView(holder, position);
    }


    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        int count = (data == null ? 0 : data.size());
//        int count = 10;

        return count;
    }



    public static class MyHolder extends RecyclerView.ViewHolder {
        private final ImageView iv;
        private final TextView tv;


        public MyHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }


    private void initView(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        myHolder.itemView.setTag(position);

        String text=data.get(position).getMenuName();
        if (text==null)text="";
        String imgUrl=data.get(position).getImg();
        if (imgUrl==null)imgUrl="";

        myHolder.tv.setText(text);

        Glide.with(mContext).load(imgUrl).into(myHolder.iv);



    }
}