package com.design.todaynews.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.design.todaynews.R;
import com.design.todaynews.common.Constant;
import com.design.todaynews.ui.adapter.NewsCategoryPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class NewsCategoryRankActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private TextView tvRankName;

    private List<String> newsCategoryIDList = new ArrayList<>();
    private List<String> newsCategoryNameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_category_rank);
        viewPager = findViewById(R.id.view_pager);
        //viewPager.setOffscreenPageLimit(0);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setElevation(8); //设置浮动高度   可能是默认的appBar高度

        LinearLayout llBack = findViewById(R.id.ll_back);
        llBack.setOnClickListener(v -> NewsCategoryRankActivity.this.finish());

        tvRankName = findViewById(R.id.tv_rankName);

        if (getIntent() != null){
            tvRankName.setText(getIntent().getStringExtra(Constant.NewsCategoryTitle));

            newsCategoryIDList = getIntent().getStringArrayListExtra(Constant.NewsCategoryIDList);
            newsCategoryNameList = getIntent().getStringArrayListExtra(Constant.NewsCategoryNameList);

            initTab();
        }


        //getWindow().setStatusBarColor(getColor(R.color.white)); //设置系统状态栏颜色


    }

    private void initTab() {
        viewPager.setAdapter(new NewsCategoryPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, newsCategoryNameList, newsCategoryIDList));
        tabLayout.setupWithViewPager(viewPager);
    }



}