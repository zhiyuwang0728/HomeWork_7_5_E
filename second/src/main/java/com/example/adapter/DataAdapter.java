package com.example.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.ListBean;
import com.example.second.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    List<ListBean.DataBean.DatasBean> data=new ArrayList<>();
    Context context;

    public DataAdapter(Context context) {
        this.context = context;
    }

    public void onRefresh(List<ListBean.DataBean.DatasBean> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void onLoadMore(List<ListBean.DataBean.DatasBean> data){
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.some, viewGroup, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ListBean.DataBean.DatasBean datasBean = data.get(i);
        viewHolder.textView.setText(datasBean.getSuperChapterName());
        Glide.with(context).load(datasBean.getEnvelopePic()).into(viewHolder.imageView);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickItem!=null){
                    clickItem.onClick(datasBean.getLink());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mImg)
        ImageView imageView;
        @BindView(R.id.mTitle)
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    ClickItem clickItem;

    public void setClickItem(ClickItem clickItem) {
        this.clickItem = clickItem;
    }

    public interface ClickItem{
        void onClick(String link);
    }
}
