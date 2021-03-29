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
import com.design.todaynews.model.NewsSearch;

import java.util.List;

public class NewsSearchListAdapter extends RecyclerView.Adapter <NewsSearchListAdapter.ViewHolder> {

    private Context mContext;
    private List<NewsSearch.ResultBeanX.ResultBean.ListBean> mlistBeans;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position)
    {
        NewsSearch.ResultBeanX.ResultBean.ListBean listBean = mlistBeans.get(position);

        holder.tvTitle.setText(listBean.getTitle());
        holder.tvTime.setText(listBean.getTime());
        holder.tvSource.setText(listBean.getSrc());
        Glide.with(mContext).load(listBean.getPic()).into(holder.ivCover);


        // 如果设置了回调，则设置点击事件
        if (onItemClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }


    }

    @Override
    public int getItemCount()
    {
        return mlistBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivCover;
        TextView tvTitle;
        TextView tvTime;
        TextView tvSource;

        ViewHolder(View view)
        {
            super(view);
            tvTitle = view.findViewById(R.id.tv_title);
            ivCover = view.findViewById(R.id.iv_cover);
            tvTime = view.findViewById(R.id.tv_time);
            tvSource = view.findViewById(R.id.tv_source);

        }
    }

    public NewsSearchListAdapter(List<NewsSearch.ResultBeanX.ResultBean.ListBean> listBeans){
        mlistBeans = listBeans;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
