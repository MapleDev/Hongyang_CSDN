package com.xznn.hongyang.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.xznn.hongyang.R;
import com.xznn.hongyang.base.BaseFragment;
import com.xznn.hongyang.utils.UIUtils;


public class NavigationFragment extends BaseFragment implements View.OnClickListener {

    @Override
    public int setResId() {
        return R.layout.fragment_navigation;
    }

    @Override
    public void initView(View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.fragment_navi_checkupdate).setOnClickListener(this);
        view.findViewById(R.id.navi_fragment_contact).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.navi_fragment_contact:
                new AlertDialog.Builder(context)
                        .setTitle("个人主页")
                        .setMessage("www.xznn.com")
                        .setPositiveButton("确定", null).create().show();
                break;
            case R.id.fragment_navi_checkupdate:
                UIUtils.showToast("该功能正在完善中...");
                break;
        }
    }
}
