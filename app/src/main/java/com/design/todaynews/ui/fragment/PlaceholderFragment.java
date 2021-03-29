package com.design.todaynews.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.design.todaynews.R;
import com.design.todaynews.common.Constant;
import com.design.todaynews.model.News;
import com.design.todaynews.ui.activity.NewsDetailActivity;
import com.design.todaynews.ui.adapter.NewsListAdapter;
import com.design.todaynews.util.network.SingletonRetrofit;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class PlaceholderFragment extends Fragment {

    private static final String TAG = "PlaceholderFragment";
    private static final String ARG_SECTION_NUMBER = "section_number";
    private Context mContext;
    private int index = 0;
    private String channel;
    private List<News.ResultBeanX.ResultBean.ListBean> mDataList = new ArrayList<>();
    private NewsListAdapter mAdapter;

    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "生命周期点位：  onCreate");
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "生命周期点位：  onCreateView");
        View root = inflater.inflate(R.layout.fragment_placeholder, container, false);
        mContext = getContext();

        refreshLayout = root.findViewById(R.id.refresh_layout);
        recyclerView = root.findViewById(R.id.recycle_list);


        initArguments();
        initOnListener();
        getNewsListData(10, 0);
        return root;
    }


    /**
     * 初始化参数
     */
    private void initArguments() {
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        switch (index) {
            case 1:
                channel = getString(R.string.tab_title_1);
                break;
            case 2:
                channel = getString(R.string.tab_title_2);
                break;
            case 3:
                channel = getString(R.string.tab_title_3);
                break;
            case 4:
                channel = getString(R.string.tab_title_4);
                break;
            case 5:
                channel = getString(R.string.tab_title_5);
                break;
            case 6:
                channel = getString(R.string.tab_title_6);
                break;
            case 7:
                channel = getString(R.string.tab_title_7);
                break;
            case 8:
                channel = getString(R.string.tab_title_8);
                break;
            case 9:
                channel = getString(R.string.tab_title_9);
                break;
            case 10:
                channel = getString(R.string.tab_title_10);
                break;
            case 11:
                channel = getString(R.string.tab_title_11);
                break;
            case 12:
                channel = getString(R.string.tab_title_12);
                break;
            case 13:
                channel = getString(R.string.tab_title_13);
                break;
            case 14:
                channel = getString(R.string.tab_title_14);
                break;
            case 15:
                channel = getString(R.string.tab_title_15);
                break;
            case 16:
                channel = getString(R.string.tab_title_16);
                break;
            case 17:
                channel = getString(R.string.tab_title_17);
                break;
        }
    }


    /**
     * 初始化监听器
     */
    private void initOnListener() {

        //下拉刷新监听
         refreshLayout.setOnRefreshListener(refreshLayout -> {
            //获取全部新闻列表数据
            getNewsListData(40, 0);
            refreshLayout.finishRefresh(2500, true, true);
            //refreshLayout.autoRefreshAnimationOnly();
            //mAdapter.notifyDataSetChanged();
        });

        //上拉加载监听
         refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            //获取剩余新闻列表数据
            getNewsListData(30, 10);
            refreshLayout.finishLoadMore(2500, true, true);
            //refreshLayout.finishLoadMoreWithNoMoreData();
        });

    }


    /**
     * 获取新闻列表数据
     *
     * @param num   数量
     * @param start 开始的位置
     */
    private void getNewsListData(int num, final int start) {
        Observer newsObserver = new Observer<News>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final News news) {
                if (news.getCode().equals(Constant.QUERY_SUCCESS_CODE)) {
                    if (start == 0) {
                        mDataList = news.getResult().getResult().getList();
                        mAdapter = new NewsListAdapter(mDataList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                        recyclerView.setAdapter(mAdapter);
                    } else {
                        mDataList.addAll(news.getResult().getResult().getList());
                        mAdapter.notifyDataSetChanged();
                    }

                    mAdapter.setOnItemClickListener((view, position) -> {
                        Intent intent = new Intent(mContext, NewsDetailActivity.class);
                        intent.putExtra(Constant.NewsTitle, mDataList.get(position).getTitle());
                        intent.putExtra(Constant.NewsSrc, mDataList.get(position).getSrc());
                        intent.putExtra(Constant.NewsTime, mDataList.get(position).getTime());
                        intent.putExtra(Constant.NewsContent, mDataList.get(position).getContent());
                        startActivity(intent);
                    });


                } else if (news.getCode().equals(Constant.ERROR_CODE_LIMIT)) {
                    Toast.makeText(mContext, "新闻数据的调用次数超过每天限量1000次/天，请明天继续", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "code：" + news.getCode() + "请前往数据提供平台参照公共参数错误码", Toast.LENGTH_SHORT).show();
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
        SingletonRetrofit.getInstance().getNews(newsObserver, channel, String.valueOf(num), String.valueOf(start), Constant.JDAPI_KEY);
    }


}