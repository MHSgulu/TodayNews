package com.design.todaynews.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.design.todaynews.R;
import com.design.todaynews.model.HotSpot;

import java.util.List;

public class NewsHotListAdapter extends RecyclerView.Adapter<NewsHotListAdapter.ViewHolder> {
    private final List<HotSpot.ResultBean.ShowapiResBodyBean.ListBean> mlistBeans;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public NewsHotListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot_news_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsHotListAdapter.ViewHolder holder, int position) {
        HotSpot.ResultBean.ShowapiResBodyBean.ListBean listBean = mlistBeans.get(position);

        holder.tvRank.setText(listBean.getNum());
        holder.tvTitle.setText(listBean.getName());
        holder.tvNum.setText(listBean.getLevel());

        if (listBean.getTrend().equals("rise")) {
            holder.ivTrend.setBackgroundResource(R.drawable.up);
        } else {
            holder.ivTrend.setBackgroundResource(R.drawable.decline);
        }


        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> {
                int pos = holder.getLayoutPosition();
                onItemClickListener.onItemClick(holder.itemView, pos);
            });
        }


    }

    @Override
    public int getItemCount() {
        return mlistBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvRank;
        private final TextView tvTitle;
        private final TextView tvNum;
        private final ImageView ivTrend;

        ViewHolder(View view) {
            super(view);
            tvRank = view.findViewById(R.id.tv_hotSpot_rank);
            tvTitle = view.findViewById(R.id.tv_hotSpot_name);
            tvNum = view.findViewById(R.id.tv_hotSpot_num);
            ivTrend = view.findViewById(R.id.iv_tv_hotSpot_trend);

        }
    }

    public NewsHotListAdapter(List<HotSpot.ResultBean.ShowapiResBodyBean.ListBean> listBeans) {
        mlistBeans = listBeans;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
