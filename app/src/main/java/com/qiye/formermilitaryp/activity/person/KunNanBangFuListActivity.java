package com.qiye.formermilitaryp.activity.person;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.qiye.formermilitaryp.R;
import com.qiye.formermilitaryp.adapter.my.KunNanBangFuRvAdapter;
import com.qiye.formermilitaryp.base.BaseActivity;
import com.qiye.formermilitaryp.bean.response.KunNanBangfuListBean;
import com.qiye.formermilitaryp.utils.LogUtils;
import com.qiye.formermilitaryp.utils.ToastUtil;
import com.qiye.formermilitaryp.utils.networkRequest2.ApiConfig;
import com.qiye.formermilitaryp.utils.networkRequest2.NetApi;
import com.qiye.formermilitaryp.utils.networkRequest2.OnSuccessAndFaultListener;
import com.qiye.formermilitaryp.utils.networkRequest2.OnSuccessAndFaultSub;
import com.qiye.formermilitaryp.view.NoScrollLinearLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 困难帮扶列表页面
 */
public class KunNanBangFuListActivity extends BaseActivity implements View.OnClickListener {

    int status = 0;//0 待解决 1 已解决
    private View v_daiJieJue_underline;
    private View v_yiJieJue_underline;

    private int Page = 1;
    private XRefreshView xRefreshView;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kun_nan_bang_fu_list);
        initView();
    }

    private void initView() {
        TextView tv_public_title_title=f(R.id.tv_public_title_title);
        tv_public_title_title.setText("我的困难帮扶");

        TextView tv_public_title_right = findViewById(R.id.tv_public_title_right);
        tv_public_title_right.setVisibility(View.VISIBLE);
        tv_public_title_right.setText("添加");
        tv_public_title_right.setOnClickListener(this);
        //顶部选择模块
        TextView tv_daiJieJue = f(R.id.tv_daiJieJue);
        TextView tv_yiJieJue = f(R.id.tv_yiJieJue);
        tv_daiJieJue.setOnClickListener(this);
        tv_yiJieJue.setOnClickListener(this);
        v_daiJieJue_underline = f(R.id.v_daiJieJue_underline);
        v_yiJieJue_underline = f(R.id.v_yiJieJue_underline);

        //列表
        rv = f(R.id.rv);
        xRefreshView = findViewById(R.id.xRefreshView);
        NoScrollLinearLayoutManager gridLayoutManager = new NoScrollLinearLayoutManager(KunNanBangFuListActivity.this);
        gridLayoutManager.setScrollEnabled(false);
        rv.setLayoutManager(gridLayoutManager);

        //xRefreshView.startRefresh();
        xRefreshView.setPullRefreshEnable(true);//下拉刷新
        xRefreshView.setPullLoadEnable(true);//上拉加载
        xRefreshView.setAutoLoadMore(false);//自动加载
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setAutoLoadMore(false);//底部自动加载
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                /**
                 * 下拉刷新
                 */
                dataList.clear();
                Page = 1;
                getDataList();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                /**
                 * 上拉加载
                 */
                Page = Page + 1;
                getDataList();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_public_title_right://添加
                Intent intent=new Intent(KunNanBangFuListActivity.this,KunNanBangFuAddActivity.class);
                intent.putExtra("flag","0");
                startActivity(intent);
                break;
            case R.id.tv_daiJieJue://待解决
                createTopCheckModuleUi(0);
                Page = 1;
                getDataList();
                break;
            case R.id.tv_yiJieJue://已解决
                createTopCheckModuleUi(1);
                Page = 1;
                getDataList();
                break;
        }
    }

    //构建顶部选择模块ui
    private void createTopCheckModuleUi(int status) {
        this.status = status;
        v_daiJieJue_underline.setVisibility(status == 0 ? View.VISIBLE : View.INVISIBLE);
        v_yiJieJue_underline.setVisibility(status == 1 ? View.VISIBLE : View.INVISIBLE);
    }

    private List<KunNanBangfuListBean.DataBean.ListBean> dataList = new ArrayList<>();

    //获取数据
    private void getDataList() {
        Map<String, String> map = new HashMap<>();
        map.put("pageNum", Page + "");
        map.put("pageSize", "10");
        map.put("supportType", (status == 0 ? 1 : 0) + "");
        NetApi.selectSupportList(map, new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LogUtils.print_e("困难帮扶", "status=" + status + "," + result);
                xRefreshView.stopRefresh();
                xRefreshView.stopLoadMore();
                KunNanBangfuListBean bean = new Gson().fromJson(result, KunNanBangfuListBean.class);
                if (Page == 1) {
                    dataList.clear();
                }
                String toastMsg = (Page == 1 ? "暂无数据" : "暂无更多");
                if (bean.getData().getList() == null) {
                    ToastUtil.showLong(KunNanBangFuListActivity.this,toastMsg);
                    Page = Page - 1;
                } else if (bean.getData().getList().size() == 0) {
                    ToastUtil.showLong(KunNanBangFuListActivity.this,toastMsg);
                    Page = Page - 1;
                } else {
                    dataList.addAll(bean.getData().getList());
                }
                rvSetAdapter(dataList);//设置列表适配器
            }

            @Override
            public void onFault(String errorMsg) {
                mmdialog.showError(errorMsg);
                xRefreshView.stopRefresh();
                xRefreshView.stopLoadMore();
            }
        }));
    }

    private void rvSetAdapter(final List<KunNanBangfuListBean.DataBean.ListBean> list) {
        KunNanBangFuRvAdapter adapter = new KunNanBangFuRvAdapter(KunNanBangFuListActivity.this, list);
        rv.setAdapter(adapter);
        adapter.setItemClickListener(new KunNanBangFuRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String id=list.get(position).getId();
                if (TextUtils.isEmpty(id)){
                    mmdialog.showSuccess("该困难帮扶不存在");
                    return;
                }
                Intent intent=new Intent(KunNanBangFuListActivity.this,KunNanBangFuShowOneActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        Page = 1;
        getDataList();
    }
}
