package com.example.second;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.adapter.HomeAdapter;
import com.example.bean.ListBean;
import com.example.bean.TitleBean;
import com.example.fragment.HomeFragment;
import com.example.presenter.MyPresenter;
import com.example.view.IView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.mTab)
    TabLayout mTab;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    private MyPresenter myPresenter;
    private HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initPresenter();
    }

    private void initPresenter() {
        myPresenter = new MyPresenter(this);
        myPresenter.onTitle();
    }

    private void initView() {

    }

    @Override
    public void onTitleSuccess(List<TitleBean.DataBean> bean) {
        List<Fragment> data = new ArrayList<>();
        for (int i = 0; i < bean.size(); i++) {

            HomeFragment homeFragment = new HomeFragment();
            Bundle bundle = new Bundle();
            int id = bean.get(i).getId();
            bundle.putInt("cid", id);
            homeFragment.setArguments(bundle);

            data.add(homeFragment);
        }
        homeAdapter = new HomeAdapter(getSupportFragmentManager(), data);
        mViewPager.setAdapter(homeAdapter);

        mTab.setupWithViewPager(mViewPager);

        for (int i = 0; i < bean.size(); i++) {
            String name = bean.get(i).getName();
            mTab.getTabAt(i).setText(name);
        }
    }

    @Override
    public void onListSuccess(List<ListBean.DataBean.DatasBean> data) {

    }

    @Override
    public void onField(String message) {

    }
}
