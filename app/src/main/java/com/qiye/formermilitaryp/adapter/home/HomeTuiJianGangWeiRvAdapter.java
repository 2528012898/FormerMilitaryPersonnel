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
import com.qiye.formermilitaryp.bean.response.HomeTuiJianGangWeiListBean;

import java.util.ArrayList;
import java.util.List;

public class HomeTuiJianGangWeiRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private List<HomeTuiJianGangWeiListBean.DataBean.ListBean> data = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mItemClickListener;

    public HomeTuiJianGangWeiRvAdapter(Context context, List<HomeTuiJianGangWeiListBean.DataBean.ListBean> list) {
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
        View view = View.inflate(mContext, R.layout.home_tuijiangangwei_rv_item, null);
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
         count = 2;

        return count;
    }



    public static class MyHolder extends RecyclerView.ViewHolder {
        private final ImageView iv;
        private final TextView tv_post;
        private final TextView tv_money;
        private final TextView tv_workExperience;
        private final TextView tv_welfare1;
        private final TextView tv_welfare2;
        private final TextView tv_company;


        public MyHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv_post = itemView.findViewById(R.id.tv_post);
            tv_money = itemView.findViewById(R.id.tv_money);
            tv_workExperience = itemView.findViewById(R.id.tv_workExperience);
            tv_welfare1 = itemView.findViewById(R.id.tv_welfare1);
            tv_welfare2 = itemView.findViewById(R.id.tv_welfare2);
            tv_company = itemView.findViewById(R.id.tv_company);
        }
    }


    private void initView(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        myHolder.itemView.setTag(position);

        String imgUrl=data.get(position).getThumbnail();
        if (imgUrl==null)imgUrl="";
        Glide.with(mContext).load(imgUrl).into(myHolder.iv);

        String post=data.get(position).getTitle();
        if (post==null)post="";
        myHolder.tv_post.setText(post);

        String money=data.get(position).getWage();
        if (money==null)money="";
        myHolder.tv_money.setText(money);

        String workExperience=data.get(position).getSubtitle();
        if (workExperience==null)workExperience="";
        myHolder.tv_workExperience.setText(workExperience);

        myHolder.tv_welfare2.setVisibility(View.GONE);
        if (data.get(position).getNewTags()!=null&&data.get(position).getNewTags().size()!=0){
            myHolder.tv_welfare1.setVisibility(View.VISIBLE);
            String welfare1=data.get(position).getNewTags().get(0);
            if (welfare1==null)welfare1="";
            myHolder.tv_welfare1.setText(welfare1);
            if (data.get(position).getNewTags().size()>=2){
                myHolder.tv_welfare2.setVisibility(View.VISIBLE);
                String welfare2=data.get(position).getNewTags().get(1);
                if (welfare2==null)welfare2="";
                myHolder.tv_welfare2.setText(welfare2);
            }
        }else{//无福利
            myHolder.tv_welfare1.setVisibility(View.GONE);
        }

        /*String company=data.get(position).get();
        if (company==null)company="";
        myHolder.tv_company.setText(company);//接口中无公司字段*/

    }
}