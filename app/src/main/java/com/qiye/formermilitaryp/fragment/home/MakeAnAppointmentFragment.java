package com.qiye.formermilitaryp.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiye.formermilitaryp.R;
import com.qiye.formermilitaryp.activity.LoginActivity;
import com.qiye.formermilitaryp.base.BaseFragment;
import com.qiye.formermilitaryp.utils.LogUtils;
import com.qiye.formermilitaryp.utils.SpUtil;
import com.qiye.formermilitaryp.utils.networkRequest2.CookieUtil;
import com.qiye.formermilitaryp.utils.networkRequest2.NetApi;
import com.qiye.formermilitaryp.utils.networkRequest2.OnSuccessAndFaultListener;
import com.qiye.formermilitaryp.utils.networkRequest2.OnSuccessAndFaultSub;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * 首页预约碎片
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MakeAnAppointmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MakeAnAppointmentFragment extends BaseFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private Context context;

    public MakeAnAppointmentFragment() {
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
    public static MakeAnAppointmentFragment newInstance(String param1, String param2) {
        MakeAnAppointmentFragment fragment = new MakeAnAppointmentFragment();
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
        View view = inflater.inflate(R.layout.fragment_makeanappointment, container, false);
        context=getActivity();
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        //控件
        view.findViewById(R.id.tv_exitLogin).setOnClickListener(this);
        view.findViewById(R.id.tv_selectMeTrainingList).setOnClickListener(this);

        createV_statusBarHeight(view.findViewById(R.id.v_statusBar));//构建自定义状态栏的高度
    }


    private void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_exitLogin:
                CookieUtil.removeCookie();
                SpUtil.spSave(context,SpUtil.storageFlieName,SpUtil.spSaveUserIdKeyName,"");//用户登录状态注销
                SpUtil.spSave(context,SpUtil.storageFlieName,SpUtil.spSaveUserNameKeyName,"");
                SpUtil.spSave(context,SpUtil.storageFlieName,SpUtil.spSaveUserTelKeyName,"");
                mmdialog.showSuccess("退出成功");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(context,LoginActivity.class));
                    }
                },1000);
                break;
            case R.id.tv_selectMeTrainingList://查询我参加的培训
                selectMeTrainingList();
                break;
        }
    }


    //查询我参加的培训
    private void selectMeTrainingList(){
        Map<String ,String> map=new HashMap<>();
        map.put("pageNum","1");
        map.put("pageSize","10");
        NetApi.selectMeTrainingList(map,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LogUtils.print_e("查询我参加的培训",result);
            }

            @Override
            public void onFault(String errorMsg) {
                mmdialog.showSuccess(errorMsg);
            }
        }));
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}
