package com.design.todaynews.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.design.todaynews.R;
import com.design.todaynews.common.Constant;
import com.design.todaynews.model.HotSpot;
import com.design.todaynews.ui.activity.NewsSearchActivity;
import com.design.todaynews.ui.adapter.NewsHotListAdapter;
import com.design.todaynews.util.network.SingletonRetrofit;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class NewsCategoryRankFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;

    private static final String TAG = "NewsCategoryRankFragment";
    private Context mContext;
    private RecyclerView recyclerView;
    private List<HotSpot.ResultBean.ShowapiResBodyBean.ListBean> mDataList = new ArrayList<>();
    private NewsHotListAdapter mAdapter;

    public NewsCategoryRankFragment() {

    }


    public static NewsCategoryRankFragment newInstance(String param1) {
        NewsCategoryRankFragment fragment = new NewsCategoryRankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            requestData();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news_category_rank, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        return root;
    }

    private void requestData() {
        Observer<HotSpot> hotSpotObserver = new Observer<HotSpot>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(final HotSpot hotSpot) {
                if (hotSpot.getCode().equals(Constant.QUERY_SUCCESS_CODE) || hotSpot.getMsg().equals("查询成功")) {
                    //Log.d(TAG, "111   mDataList  " + mDataList);
                    mDataList = hotSpot.getResult().getShowapi_res_body().getList();
                    //Log.d(TAG, "2222   mDataList  " + mDataList);
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
        SingletonRetrofit.getInstance().getHotSpotRanking(hotSpotObserver, mParam1, Constant.JDAPI_KEY);
    }
}