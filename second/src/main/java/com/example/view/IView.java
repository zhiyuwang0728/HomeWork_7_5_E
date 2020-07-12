package com.example.view;

import com.example.bean.ListBean;
import com.example.bean.TitleBean;

import java.util.List;

public interface IView {

    void onTitleSuccess(List<TitleBean.DataBean> bean);

    void onListSuccess(List<ListBean.DataBean.DatasBean> data);

    void onField(String message);
}
