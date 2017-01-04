package com.xznn.hongyang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xznn.hongyang.R;
import com.xznn.hongyang.base.BaseFragment;
import com.xznn.hongyang.bean.HeadlineBean;
import com.xznn.hongyang.utils.UIUtils;
import com.xznn.hongyang.widget.TaobaoHeadline;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuming on 16/10/16.
 */

public class TBHeadlineFragment extends BaseFragment {

    private TaobaoHeadline taobaoHeadline;

    @Override
    public int setResId() {
        return R.layout.fragment_taobao_headline;
    }

    @Override
    public void initView(View view, @Nullable Bundle savedInstanceState) {
        List<HeadlineBean> data = new ArrayList<>();
        data.add(new HeadlineBean("热门", "袜子裤子只要998～只要998～"));
        data.add(new HeadlineBean("推荐", "秋冬上心，韩流服饰，一折起"));
        data.add(new HeadlineBean("好货", "品牌二手车"));
        data.add(new HeadlineBean("省钱", "MadCatz MMO7 游戏鼠标键盘套装"));

        taobaoHeadline = (TaobaoHeadline) view.findViewById(R.id.fragment_taobao_headline_headline);
        taobaoHeadline.setData(data);
        taobaoHeadline.setHeadlineClickListener(new TaobaoHeadline.HeadlineClickListener() {
            @Override
            public void onHeadlineClick(HeadlineBean bean) {
                UIUtils.showToast(bean.getTitle() + ":" + bean.getContent());
            }

            @Override
            public void onMoreClick() {
                UIUtils.showToast("更多");
            }
        });
    }
}
