package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapter.DataAdapter;
import com.example.bean.Event;
import com.example.bean.ListBean;
import com.example.bean.TitleBean;
import com.example.presenter.MyPresenter;
import com.example.second.R;
import com.example.second.WebActivity;
import com.example.view.IView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements IView {

    @BindView(R.id.mRecycler)
    RecyclerView mRecycler;
    Unbinder unbinder;
    int index = -1;
    @BindView(R.id.mRefresh)
    SmartRefreshLayout mRefresh;
    private MyPresenter myPresenter;
    int page=1;
    private DataAdapter dataAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        Bundle arguments = getArguments();
        int cid = arguments.getInt("cid");
        Log.d(TAG, "onCreateView: "+cid);
        index = cid;
        initView();
        initPresenter();
        return inflate;
    }

    private void initPresenter() {
        myPresenter = new MyPresenter(this);
        myPresenter.onList(page,index);
    }

    private void initView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        dataAdapter = new DataAdapter(getActivity());
        mRecycler.setAdapter(dataAdapter);

        dataAdapter.setClickItem(new DataAdapter.ClickItem() {
            @Override
            public void onClick(String link) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("link", link);
                startActivity(intent);
            }
        });

        mRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initPresenter();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                initPresenter();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onTitleSuccess(List<TitleBean.DataBean> bean) {

    }

    @Override
    public void onListSuccess(List<ListBean.DataBean.DatasBean> data) {
        Log.d(TAG, "onListSuccess: "+data.size());
        if(page==1){
            dataAdapter.onRefresh(data);
            mRefresh.finishRefresh();
        }else{
            dataAdapter.onLoadMore(data);
            mRefresh.finishLoadMore();
        }
    }

    @Override
    public void onField(String message) {

    }

    private static final String TAG = "HomeFragment";
}
