package com.qiye.formermilitaryp.adapter.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qiye.formermilitaryp.R;
import com.qiye.formermilitaryp.bean.response.HomeGuangRongBangListBean;
import com.qiye.formermilitaryp.bean.response.HomeZhengCeGongShiListBean;

import java.util.ArrayList;
import java.util.List;

public class HomeZhengCeGongShiRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private List<HomeZhengCeGongShiListBean.DataBean.ListBean> data = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mItemClickListener;

    public HomeZhengCeGongShiRvAdapter(Context context, List<HomeZhengCeGongShiListBean.DataBean.ListBean> list) {
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
        View view = View.inflate(mContext, R.layout.home_zhengcegongshi_rv_item, null);
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
         //count = 3;

        return count;
    }



    public static class MyHolder extends RecyclerView.ViewHolder {
        private final ImageView iv;
        private final TextView tv_title;
        private final TextView tv_title_bottom;
        private final TextView tv_time;


        public MyHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_title_bottom = itemView.findViewById(R.id.tv_title_bottom);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }


    private void initView(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        myHolder.itemView.setTag(position);

        String imgUrl=data.get(position).getImgUrl();
        if (imgUrl==null)imgUrl="";
        Glide.with(mContext).load(imgUrl).into(myHolder.iv);

        String title=data.get(position).getTitle();
        if (title==null)title="";
        myHolder.tv_title.setText(title);
        myHolder.tv_title_bottom.setText(title);


        int time=data.get(position).getMinutes();
        if (time<0)time=0-time;
        myHolder.tv_time.setText(time+"分钟前");


    }
}