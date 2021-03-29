package com.design.todaynews.ui.activity;


import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.design.todaynews.R;
import com.design.todaynews.common.Constant;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.Objects;


public class NewsDetailActivity extends AppCompatActivity {

    private TextView tvTitle, tvTime, tvSource;
    private HtmlTextView tvContent;
    private LinearLayout llBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        tvTitle = findViewById(R.id.tv_title);
        tvSource = findViewById(R.id.tv_source);
        tvTime = findViewById(R.id.tv_time);
        tvContent = findViewById(R.id.tv_content);

        if (getIntent() != null) {
            tvTitle.setText(getIntent().getStringExtra(Constant.NewsTitle));
            tvSource.setText(getIntent().getStringExtra(Constant.NewsSrc));
            tvTime.setText(getIntent().getStringExtra(Constant.NewsTime));
            tvContent.setHtml(Objects.requireNonNull(getIntent().getStringExtra(Constant.NewsContent)), new HtmlHttpImageGetter(tvContent));
        }

        llBack = findViewById(R.id.ll_back);
        llBack.setOnClickListener(v -> NewsDetailActivity.this.finish());

    }


}
