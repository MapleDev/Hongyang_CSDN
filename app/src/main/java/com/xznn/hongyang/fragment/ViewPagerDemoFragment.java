package com.xznn.hongyang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xznn.hongyang.R;
import com.xznn.hongyang.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuming on 16/10/16.
 */

public class ViewPagerDemoFragment extends BaseFragment {
    private static final String TAG = "ViewPagerDemoFragment";

    private ViewPager mViewPager;
    private View page1, page2, page3; // ViewPager包含的页面
    private List<View> pageList; // ViewPager包含的页面列表，一般给adapter传的是一个list

    @Override
    public int setResId() {
        return R.layout.fragment_view_pager_demo;
    }

    @Override
    public void initView(View view, @Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        page1 = inflater.inflate(R.layout.item_page1, null);
        page2 = inflater.inflate(R.layout.item_page2, null);
        page3 = inflater.inflate(R.layout.item_page3, null);

        pageList = new ArrayList<View>();
        pageList.add(page1);
        pageList.add(page2);
        pageList.add(page3);

        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);

        ViewPagerDemoAdapter adapter = new ViewPagerDemoAdapter();
        mViewPager.setAdapter(adapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(TAG, "onPageScrolled: position == " + position + ", positionOffset == " + positionOffset + ", positionOffsetPixels == " + positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                Log.w(TAG, "onPageSelected: position == " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e(TAG, "onPageSelected: state == " + state);
            }
        });

        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                Log.v(TAG, "ViewPagerDemoFragment{} ... transformPage() --> position = " + position);
                page.setAlpha( 1 - Math.abs(position));
            }
        });

    }

    class ViewPagerDemoAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) { // return a key
//            return super.instantiateItem(container, position);
            container.addView(pageList.get(position));
            return pageList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
//            container.removeView(pageList.get(position));
            container.removeView((View)object);
        }
    }
}
