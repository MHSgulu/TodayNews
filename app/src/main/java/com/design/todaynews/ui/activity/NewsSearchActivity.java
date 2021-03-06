package com.design.todaynews.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.design.todaynews.R;
import com.design.todaynews.common.Constant;
import com.design.todaynews.model.NewsSearch;
import com.design.todaynews.ui.adapter.NewsSearchListAdapter;
import com.design.todaynews.util.network.SingletonRetrofit;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class NewsSearchActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SearchNewsActivity";
    private Context mContext;

    private List<NewsSearch.ResultBeanX.ResultBean.ListBean> mDataList = new ArrayList<>();
    private NewsSearchListAdapter mAdapter;

    private EditText etInput;
    private LinearLayout llSearch, llBack;
    private ProgressBar progressBar;
    private ImageView ivReset;
    private RecyclerView searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = NewsSearchActivity.this;
        setContentView(R.layout.activity_search_news);

        llBack = findViewById(R.id.ll_back);
        llBack.setOnClickListener(this);
        ivReset = findViewById(R.id.iv_reset);
        ivReset.setOnClickListener(this);
        llSearch = findViewById(R.id.ll_search);
        llSearch.setOnClickListener(this);

        etInput = findViewById(R.id.et_input);
        progressBar = findViewById(R.id.progress_bar);
        searchList = findViewById(R.id.search_list);


        String string = getIntent().getStringExtra(Constant.HotWordName);
        if (string != null) {
            etInput.setText(string);
            llSearch.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(mContext, "?????????????????????????????????", Toast.LENGTH_SHORT).show();
            //????????????
            searchNews();
        }


        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().isEmpty()) {
                    ivReset.setVisibility(View.GONE);
                } else {
                    ivReset.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    ivReset.setVisibility(View.GONE);
                } else {
                    ivReset.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 8) {
                    Toast.makeText(mContext, "???????????????????????????8?????????", Toast.LENGTH_SHORT).show();
                }
                if (s.toString().isEmpty()) {
                    ivReset.setVisibility(View.GONE);
                } else {
                    ivReset.setVisibility(View.VISIBLE);
                }
            }
        });


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.ll_back: //??????
                finish();
                break;

            case R.id.iv_reset:
                etInput.setText("");
                break;

            case R.id.ll_search:
                //trim()???????????????????????????????????????
                //?????????????????????????????????????????????????????????????????????????????? ????????????????????????????????????????????????????????? ???????????????
                if (TextUtils.isEmpty(etInput.getText().toString().trim())) {
                    Toast.makeText(mContext, "???????????????", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //????????????????????????????????????????????????????????? ???????????? ??????UI??????????????????????????????????????????
                    llSearch.setEnabled(false);
                    searchList.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    //Toast.makeText(mContext, "?????????????????????????????????????????????????????????,?????????????????????????????????", Toast.LENGTH_SHORT).show();
                    //????????????
                    searchNews();
                }
                break;

        }
    }

    private void searchNews() {

        Observer<NewsSearch> newsSearchObserver = new Observer<NewsSearch>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NewsSearch newsSearch) {
                if (newsSearch.getCode().equals(Constant.QUERY_SUCCESS_CODE) || newsSearch.getMsg().equals("????????????")) {
                    //????????????????????????????????????????????????????????? ???????????? ??????UI????????????????????????????????????
                    llSearch.setEnabled(true);
                    progressBar.setVisibility(View.GONE);
                    searchList.setVisibility(View.VISIBLE);
                    Toast.makeText(mContext, "????????????" + newsSearch.getResult().getResult().getNum() + "??????????????????", Toast.LENGTH_SHORT).show();
                    mDataList.clear();
                    mDataList = newsSearch.getResult().getResult().getList();
                    mAdapter = new NewsSearchListAdapter(mDataList);
                    searchList.setLayoutManager(new LinearLayoutManager(mContext));
                    searchList.setAdapter(mAdapter);
                } else if (newsSearch.getCode().equals(Constant.ERROR_CODE_LIMIT)) {
                    Toast.makeText(mContext, "?????????????????????????????????????????????1000???/?????????????????????", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "code???" + newsSearch.getCode() + "??????????????????????????????????????????????????????", Toast.LENGTH_SHORT).show();
                }


                mAdapter.setOnItemClickListener(new NewsSearchListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // Toast.makeText(mContext, "??????position???"+position, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, NewsDetailActivity.class);
                        intent.putExtra(Constant.NewsTitle, mDataList.get(position).getTitle());
                        intent.putExtra(Constant.NewsSrc, mDataList.get(position).getSrc());
                        intent.putExtra(Constant.NewsTime, mDataList.get(position).getTime());
                        intent.putExtra(Constant.NewsContent, mDataList.get(position).getContent());
                        startActivity(intent);
                    }
                });


            }

            @Override
            public void onError(Throwable e) {
                llSearch.setEnabled(true);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().getSearchNews(newsSearchObserver, etInput.getText().toString(), Constant.JDAPI_KEY);

    }


}
