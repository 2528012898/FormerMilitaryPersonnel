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
import com.qiye.formermilitaryp.bean.response.HomeMenuBean;

import java.util.ArrayList;
import java.util.List;

public class HomeGuangRongBangRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private List<HomeGuangRongBangListBean.DataBean> data = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mItemClickListener;

    public HomeGuangRongBangRvAdapter(Context context, List<HomeGuangRongBangListBean.DataBean> list) {
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
        View view = View.inflate(mContext, R.layout.home_guangrongbang_rv_item, null);
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
         count = 3;

        return count;
    }



    public static class MyHolder extends RecyclerView.ViewHolder {
        private final ImageView iv;
        private final TextView tv1;
        private final TextView tv2;


        public MyHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
        }
    }


    private void initView(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        myHolder.itemView.setTag(position);

        String name=data.get(position).getName();
        if (name==null)name="";
        String place=data.get(position).getPlace();
        if (place==null)place="";

        myHolder.tv1.setText(name);
        myHolder.tv2.setText(place);

        //Glide.with(mContext).load(imgUrl).into(myHolder.iv);



    }
}