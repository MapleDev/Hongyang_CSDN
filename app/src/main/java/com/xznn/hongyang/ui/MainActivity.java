package com.xznn.hongyang.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.xznn.hongyang.R;
import com.xznn.hongyang.adapter.TextHolderAdatpter;
import com.xznn.hongyang.bean.TextBean;
import com.xznn.hongyang.fragment.TBHeadlineFragment;
import com.xznn.hongyang.fragment.ViewPagerDemoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TextHolderAdatpter.TextHolderClickListener {

    private final String TAG = MainActivity.class.getSimpleName();
    private TextHolderAdatpter adapter;
    private FragmentManager fragmentManager;
    private DrawerLayout drawerLayout;
    //toolbar相关
    private TextView toolbar_title_tv;
    private TextView toolbar_back_tv;
    private ImageView toolbar_profile_iv;
    private List<TextBean> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置contentView
        beforeSetContentView();
        setContentView(R.layout.activity_main);
        afterSetContentView();
        //初始化数据
        initData();
        //初始化View
        initView();
    }

    protected void beforeSetContentView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明底部导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void afterSetContentView() {
        toolbar_title_tv = (TextView) findViewById(R.id.activity_main_toolbar_title_tv);
        toolbar_back_tv = (TextView) findViewById(R.id.activity_main_toolbar_back_tv);
        toolbar_profile_iv = (ImageView) findViewById(R.id.activity_main_toolbar_profile_iv);

        toolbar_title_tv.setText("软件主界面");
        toolbar_back_tv.setVisibility(View.GONE);
        toolbar_profile_iv.setVisibility(View.GONE);
        toolbar_back_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar_profile_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        hideBackButton();
    }

    protected void initData() {
        mData = new ArrayList();
        mData.add(new TextBean("浅谈 MVP in Android", "http://blog.csdn.net/lmj623565791/article/details/46596109"));
        mData.add(new TextBean("京东头条控件", "模仿京东头条，上下无限滚动"));
        mData.add(new TextBean("ViewPagerDemo", "http://www.imooc.com/article/2580"));


        adapter = new TextHolderAdatpter(this, mData);
        adapter.setTextHolderClickListener(this);
    }

    protected void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void onTextClick(TextBean bean) {

        switch (mData.indexOf(bean)) {
            case 0:
                openFragment(new TBHeadlineFragment(), bean.getTitle());
                break;
            case 1:
                openFragment(new TBHeadlineFragment(), bean.getTitle());
                break;
            case 2:
                openFragment(new ViewPagerDemoFragment(), bean.getTitle());
                break;


        }
    }

    private void openFragment(Fragment fragment, String fragmentName) {
        fragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
        showBackButton(fragmentName);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hideBackButton();
    }

    private void showBackButton(String fragmentName) {
        toolbar_back_tv.setVisibility(View.VISIBLE);
        toolbar_profile_iv.setVisibility(View.GONE);
        toolbar_title_tv.setText(fragmentName);
        if (drawerLayout != null) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    private void hideBackButton() {
        toolbar_back_tv.setVisibility(View.GONE);
        toolbar_profile_iv.setVisibility(View.VISIBLE);
        toolbar_title_tv.setText("软件主界面");
        if (drawerLayout != null) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);
        }
    }}
