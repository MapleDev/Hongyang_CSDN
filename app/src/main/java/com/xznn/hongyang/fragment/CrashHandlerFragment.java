package com.xznn.hongyang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xznn.hongyang.R;
import com.xznn.hongyang.base.BaseFragment;

/**
 * Created by wuming on 16/10/16.
 */

public class CrashHandlerFragment extends BaseFragment {


    @Override
    public int setResId() {
        return R.layout.fragment_crash_handler;
    }

    @Override
    public void initView(View view, @Nullable Bundle savedInstanceState) {
        int i = 1 / 0;
    }
}
