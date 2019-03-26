package com.qiye.formermilitaryp.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiye.formermilitaryp.R;
import com.qiye.formermilitaryp.activity.person.KunNanBangFuListActivity;
import com.qiye.formermilitaryp.base.BaseFragment;
import com.qiye.formermilitaryp.utils.SpUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private TextView tv_name;
    private TextView tv_tel;

    public MyFragment() {
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
    public static MyFragment newInstance(String param1, String param2) {
        MyFragment fragment = new MyFragment();
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
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        createV_statusBarHeight(view.findViewById(R.id.v_statusBar));//构建自定义状态栏的高度

        tv_name = view.findViewById(R.id.tv_name);
        tv_tel = view.findViewById(R.id.tv_tel);

        view.findViewById(R.id.tv_wodekunnanbangfu).setOnClickListener(this);
    }


    private void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_wodekunnanbangfu://我的困难帮扶
                startActivity(new Intent(getActivity(),KunNanBangFuListActivity.class));
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        //用户信息展示
        String userName= SpUtil.spGet(getActivity(),SpUtil.storageFlieName,SpUtil.spSaveUserNameKeyName,"");
        String userTel= SpUtil.spGet(getActivity(),SpUtil.storageFlieName,SpUtil.spSaveUserTelKeyName,"");
        tv_name.setText(userName);
        tv_tel.setText(userTel);
    }


}
