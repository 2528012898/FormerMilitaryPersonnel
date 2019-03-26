package com.qiye.formermilitaryp.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.qiye.formermilitaryp.R;
import com.qiye.formermilitaryp.adapter.home.HomeGongZuoDongTaiRvAdapter;
import com.qiye.formermilitaryp.adapter.home.HomeGuangRongBangRvAdapter;
import com.qiye.formermilitaryp.adapter.home.HomeMenuRvAdapter;
import com.qiye.formermilitaryp.adapter.home.HomeTuiJianGangWeiRvAdapter;
import com.qiye.formermilitaryp.adapter.home.HomeZhengCeGongShiRvAdapter;
import com.qiye.formermilitaryp.base.BaseFragment;
import com.qiye.formermilitaryp.bean.response.HomeBannerBean;
import com.qiye.formermilitaryp.bean.response.HomeGongZuoDongTaiListBean;
import com.qiye.formermilitaryp.bean.response.HomeGuangRongBangListBean;
import com.qiye.formermilitaryp.bean.response.HomeMenuBean;
import com.qiye.formermilitaryp.bean.response.HomeTuiJianGangWeiListBean;
import com.qiye.formermilitaryp.bean.response.HomeZhengCeGongShiListBean;
import com.qiye.formermilitaryp.json.home.HomeGuangRongBangJson;
import com.qiye.formermilitaryp.utils.LogUtils;
import com.qiye.formermilitaryp.utils.networkRequest2.ApiConfig;
import com.qiye.formermilitaryp.utils.networkRequest2.NetApi;
import com.qiye.formermilitaryp.utils.networkRequest2.OnSuccessAndFaultListener;
import com.qiye.formermilitaryp.utils.networkRequest2.OnSuccessAndFaultSub;
import com.qiye.formermilitaryp.view.NoScrollGridLayoutManager;
import com.qiye.formermilitaryp.view.NoScrollLinearLayoutManager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private Banner banner;
    private RecyclerView rv_menu;
    private RecyclerView rv_guangRongBang;
    private RecyclerView rv_gongZuoDongTai;
    private RecyclerView rv_tuiJianGangWei;
    private RecyclerView rv_zhengCeGongShi;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        banner = view.findViewById(R.id.banner);

        //菜单
        rv_menu = view.findViewById(R.id.rv_Menu);
        NoScrollGridLayoutManager noScrollGridLayoutManager = new NoScrollGridLayoutManager(getActivity(), 4);
        noScrollGridLayoutManager.setScrollEnabled(false);
        rv_menu.setLayoutManager(noScrollGridLayoutManager);

        rv_guangRongBang = view.findViewById(R.id.rv_guangRongBang);
        NoScrollGridLayoutManager noScrollGridLayoutManager2 = new NoScrollGridLayoutManager(getActivity(), 3);
        noScrollGridLayoutManager2.setScrollEnabled(false);
        rv_guangRongBang.setLayoutManager(noScrollGridLayoutManager2);

        rv_gongZuoDongTai = view.findViewById(R.id.rv_gongZuoDongTai);

        rv_tuiJianGangWei = view.findViewById(R.id.rv_tuiJianGangWei);

        rv_zhengCeGongShi = view.findViewById(R.id.rv_zhengCeGongShi);
        NoScrollLinearLayoutManager noScrollLinearLayoutManager = new NoScrollLinearLayoutManager(getActivity());
        noScrollLinearLayoutManager.setScrollEnabled(false);
        rv_zhengCeGongShi.setLayoutManager(noScrollLinearLayoutManager);

    }


    private void initData() {

    }


    /**
     * 数据请求
     */
    private void getBanner() {
        NetApi.homeBanner(new HashMap(), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LogUtils.print_e("home banner", result);
                HomeBannerBean bean = new Gson().fromJson(result, HomeBannerBean.class);
                createBanner(bean.getData());
            }

            @Override
            public void onFault(String errorMsg) {
                LogUtils.print_e("home banner err", errorMsg);
            }
        }));
    }

    private void getMenu() {
        NetApi.selectHomeMenu(new HashMap(), new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LogUtils.print_e("home menu", result);
                HomeMenuBean bean = new Gson().fromJson(result, HomeMenuBean.class);
                if (bean.getData() == null) return;
                if (bean.getData().getList() == null) return;
                if (bean.getData().getList().size() == 0) return;
                HomeMenuRvAdapter homeMenuRvAdapter = new HomeMenuRvAdapter(getActivity(), bean.getData().getList());
                rv_menu.setAdapter(homeMenuRvAdapter);
                homeMenuRvAdapter.setItemClickListener(new HomeMenuRvAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }
                });

            }

            @Override
            public void onFault(String errorMsg) {
                LogUtils.print_e("home menu err", errorMsg);
            }
        }));
    }

    private void getGuangRongBang() {
        String result = HomeGuangRongBangJson.json;
        HomeGuangRongBangListBean homeGuangRongBangListBean = new Gson().fromJson(result, HomeGuangRongBangListBean.class);
        if (homeGuangRongBangListBean.getData() == null) return;
        if (homeGuangRongBangListBean.getData().size() == 0) return;
        HomeGuangRongBangRvAdapter homeGuangRongBangRvAdapter = new HomeGuangRongBangRvAdapter(getActivity(), homeGuangRongBangListBean.getData());
        rv_guangRongBang.setAdapter(homeGuangRongBangRvAdapter);
        homeGuangRongBangRvAdapter.setItemClickListener(new HomeGuangRongBangRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

    }

    private void getGongZuoDongTai() {
        Map<String, String> map = new HashMap();
        map.put("pageNum", "1");
        map.put("pageSize", "3");
        NetApi.selectHomeGongZuoDongTai(map, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LogUtils.print_e("home gongZuoDongTai", result);
                HomeGongZuoDongTaiListBean bean = new Gson().fromJson(result, HomeGongZuoDongTaiListBean.class);
                if (bean.getData() == null) return;
                if (bean.getData().getList() == null) return;
                if (bean.getData().getList().size() == 0) return;
                HomeGongZuoDongTaiRvAdapter homeGongZuoDongTaiRvAdapter = new HomeGongZuoDongTaiRvAdapter(getActivity(), bean.getData().getList());
                rv_gongZuoDongTai.setAdapter(homeGongZuoDongTaiRvAdapter);
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
                rv_gongZuoDongTai.setLayoutManager(manager);
                homeGongZuoDongTaiRvAdapter.setItemClickListener(new HomeGongZuoDongTaiRvAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }
                });

            }

            @Override
            public void onFault(String errorMsg) {
                LogUtils.print_e("home gongZuoDongTai err", errorMsg);
            }
        }));
    }

    private void getTuiJianGangWei() {
        Map<String, String> map = new HashMap();
        map.put("pageNum", "1");
        map.put("pageSize", "2");
        NetApi.selectHomeTuiJianGangWei(map, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LogUtils.print_e("home tuiJianGangWei", result);
                HomeTuiJianGangWeiListBean bean = new Gson().fromJson(result, HomeTuiJianGangWeiListBean.class);
                if (bean.getData() == null) return;
                if (bean.getData().getList() == null) return;
                if (bean.getData().getList().size() == 0) return;
                HomeTuiJianGangWeiRvAdapter homeTuiJianGangWeiRvAdapter = new HomeTuiJianGangWeiRvAdapter(getActivity(), bean.getData().getList());
                rv_tuiJianGangWei.setAdapter(homeTuiJianGangWeiRvAdapter);
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
                rv_tuiJianGangWei.setLayoutManager(manager);
                homeTuiJianGangWeiRvAdapter.setItemClickListener(new HomeTuiJianGangWeiRvAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }
                });

            }

            @Override
            public void onFault(String errorMsg) {
                LogUtils.print_e("home tuiJianGangWei err", errorMsg);
            }
        }));
    }

    private void getZhengCeGongShi() {
        Map<String, String> map = new HashMap();
        map.put("pageNum", "1");
        map.put("pageSize", "10");
        NetApi.selectHomeZhengCeGongShi(map, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LogUtils.print_e("home zhengCeGongShi", result);
                HomeZhengCeGongShiListBean bean = new Gson().fromJson(result, HomeZhengCeGongShiListBean.class);
                if (bean.getData() == null) return;
                if (bean.getData().getList() == null) return;
                if (bean.getData().getList().size() == 0) return;
                HomeZhengCeGongShiRvAdapter  homeZhengCeGongShiRvAdapter=new HomeZhengCeGongShiRvAdapter(getActivity(),bean .getData().getList());
                rv_zhengCeGongShi.setAdapter(homeZhengCeGongShiRvAdapter);
                homeZhengCeGongShiRvAdapter.setItemClickListener(new HomeZhengCeGongShiRvAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick ( int position){

                    }
                });

            }

            @Override
            public void onFault(String errorMsg) {
                LogUtils.print_e("home zhengCeGongShi err", errorMsg);
            }
        }));
    }

    /**
     * banner
     */
    private void createBanner(List<HomeBannerBean.DataBean> data) {
        if (data == null) return;
        if (data.size() == 0) return;
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        List<String> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            String imgUrl = data.get(i).getImgUrl();
            if (imgUrl == null) imgUrl = "";
            list.add(imgUrl);
        }
        banner.setImages(list);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //必须最后调用的方法，启动轮播图。
                .start();
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            String imgUrl = (String) path;
            if (!imgUrl.startsWith("http")) {
                imgUrl = ApiConfig.BASE_URL + imgUrl;
            }
            Glide.with(context).load(imgUrl).into(imageView);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }


    @Override
    public void onResume() {
        super.onResume();
        getBanner();
        getMenu();
        getGuangRongBang();
        getGongZuoDongTai();
        getTuiJianGangWei();
        getZhengCeGongShi();
    }


}
