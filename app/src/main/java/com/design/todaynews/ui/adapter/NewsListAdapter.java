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
import com.design.todaynews.model.News;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter <NewsListAdapter.ViewHolder>{

    private Context mContext;
    private List<News.ResultBeanX.ResultBean.ListBean> mlistBeans;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public NewsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsListAdapter.ViewHolder holder, int position)
    {
        News.ResultBeanX.ResultBean.ListBean listBean = mlistBeans.get(position);


        Glide.with(mContext).load(listBean.getPic()).into(holder.ivCover);
        holder.tvTitle.setText(listBean.getTitle());
        holder.tvTime.setText(listBean.getTime());
        holder.tvSource.setText(listBean.getSrc());


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
            ivCover = view.findViewById(R.id.iv_cover);
            tvTime = view.findViewById(R.id.tv_time);
            tvTitle = view.findViewById(R.id.tv_title);
            tvSource = view.findViewById(R.id.tv_source);

        }
    }

    public NewsListAdapter(List<News.ResultBeanX.ResultBean.ListBean> listBeans){
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
