package com.design.todaynews.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.design.todaynews.R;
import com.design.todaynews.model.NewsCategory;

import java.util.List;

public class NewsCategoryListAdapter extends RecyclerView.Adapter <NewsCategoryListAdapter.ViewHolder>{

    private Context mContext;
    private final List<NewsCategory.ResultBean.ShowapiResBodyBean.ListBean> mlistBeans;
    private OnItemClickListener onItemClickListener;

    private final List<String> newsCategoryBackgroundImage;

    @NonNull
    @Override
    public NewsCategoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_category_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsCategoryListAdapter.ViewHolder holder, int position) {
        Glide.with(mContext).load(newsCategoryBackgroundImage.get(position)).into(holder.ivCover);


        NewsCategory.ResultBean.ShowapiResBodyBean.ListBean bean = mlistBeans.get(position);
        holder.tvCategoryTitle.setText(bean.getName());

        // 如果设置了回调，则设置点击事件
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> {
                int pos = holder.getLayoutPosition();
                onItemClickListener.onItemClick(holder.itemView, pos);
            });
        }


    }

    @Override
    public int getItemCount()
    {
        return mlistBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvCategoryTitle;

        ViewHolder(View view) {
            super(view);
            ivCover = view.findViewById(R.id.iv_cover);
            tvCategoryTitle = view.findViewById(R.id.tv_category_title);

        }
    }

    public NewsCategoryListAdapter(List<NewsCategory.ResultBean.ShowapiResBodyBean.ListBean> dataList1,List<String> dataList2){
        mlistBeans = dataList1;
        newsCategoryBackgroundImage = dataList2;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }



}
