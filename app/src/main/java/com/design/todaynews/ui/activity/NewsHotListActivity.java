package com.design.todaynews.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.design.todaynews.R;
import com.design.todaynews.common.Constant;
import com.design.todaynews.model.HotSpot;
import com.design.todaynews.ui.adapter.NewsHotListAdapter;
import com.design.todaynews.util.network.SingletonRetrofit;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class NewsHotListActivity extends AppCompatActivity {

    private static final String TAG = "NewsHotListActivity";
    private Context mContext;

    private List<HotSpot.ResultBean.ShowapiResBodyBean.ListBean> mDataList = new ArrayList<>();
    private NewsHotListAdapter mAdapter;

    private LinearLayout llBack;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_news_list);
        mContext = NewsHotListActivity.this;

        llBack = findViewById(R.id.ll_back);
        llBack.setOnClickListener(v -> finish());

        recyclerView = findViewById(R.id.hotSpot_List);

        //热词排行
        queryHotSpotRankingList();

    }

    private void queryHotSpotRankingList() {

        Observer<HotSpot> hotSpotObserver = new Observer<HotSpot>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(final HotSpot hotSpot) {
                if (hotSpot.getCode().equals(Constant.QUERY_SUCCESS_CODE) || hotSpot.getMsg().equals("查询成功")) {
                    mDataList = hotSpot.getResult().getShowapi_res_body().getList();
                    mAdapter = new NewsHotListAdapter(mDataList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                    recyclerView.setAdapter(mAdapter);
                } else if (hotSpot.getCode().equals(Constant.ERROR_CODE_LIMIT)) {
                    Toast.makeText(mContext, "实时热点数据的调用次数超过每天限量3000次/天，请明天继续", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "code：" + hotSpot.getCode() + "请前往数据提供平台参照公共参数错误码", Toast.LENGTH_SHORT).show();
                }

                mAdapter.setOnItemClickListener((view, position) -> {
                    Intent intent = new Intent(mContext, NewsSearchActivity.class);
                    intent.putExtra(Constant.HotWordName, mDataList.get(position).getName());
                    startActivity(intent);
                });


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
        SingletonRetrofit.getInstance().getHotSpotRanking(hotSpotObserver, "1", Constant.JDAPI_KEY);


    }
}
