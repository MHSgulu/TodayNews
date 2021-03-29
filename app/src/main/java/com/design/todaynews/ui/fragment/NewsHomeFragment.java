package com.design.todaynews.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.design.todaynews.R;
import com.design.todaynews.common.Constant;
import com.design.todaynews.model.NewsChannel;
import com.design.todaynews.ui.activity.NewsHotListActivity;
import com.design.todaynews.ui.activity.NewsSearchActivity;
import com.design.todaynews.ui.adapter.NewsSectionsPagerAdapter;
import com.design.todaynews.util.network.SingletonRetrofit;
import com.google.android.material.tabs.TabLayout;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class NewsHomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private Context mContext;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "生命周期点位：  NewsHomeFragment onCreate");
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "生命周期点位：  NewsHomeFragment onCreateView");
        View root = inflater.inflate(R.layout.fragment_news_home, container, false);
        mContext = root.getContext();
        viewPager = root.findViewById(R.id.view_pager);
        tabLayout = root.findViewById(R.id.tabs);
        tabLayout.setElevation(10); //设置浮动高度

        LinearLayout llSearchBox = root.findViewById(R.id.ll_search_box);
        llSearchBox.setOnClickListener(v -> startActivity(new Intent(mContext, NewsSearchActivity.class)));
        LinearLayout llHotDotRank = root.findViewById(R.id.ll_hotdotRank);
        llHotDotRank.setOnClickListener(v -> startActivity(new Intent(mContext, NewsHotListActivity.class)));

        initTab();
        requireActivity().getWindow().setStatusBarColor(requireActivity().getColor(R.color.colorNavigationA)); //设置系统状态栏颜色
        return root;
    }

    //初始化Tab
    private void initTab() {

        Observer<NewsChannel> observer = new Observer<NewsChannel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(NewsChannel newsChannel) {
                if (newsChannel.getCode().equals(Constant.QUERY_SUCCESS_CODE)) {
                    if (!newsChannel.getResult().getResult().isEmpty()) {
                        viewPager.setAdapter(new NewsSectionsPagerAdapter(getChildFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, newsChannel.getResult().getResult()));
                        tabLayout.setupWithViewPager(viewPager);
                    }
                } else if (newsChannel.getCode().equals(Constant.ERROR_CODE_LIMIT)) {
                    Toast.makeText(mContext, "新闻频道分类数据的调用次数超过每天限量1000次/天，请明天继续", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().getNewsChannel(observer, Constant.JDAPI_KEY);

    }


}