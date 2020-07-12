package com.example.presenter;

import com.example.bean.ListBean;
import com.example.bean.TitleBean;
import com.example.model.MyModel;
import com.example.view.ICallBack;
import com.example.view.IView;

import java.util.List;

public class MyPresenter implements IPresenter {

    private final MyModel myModel;
    private final IView view;

    public MyPresenter(IView view) {
        myModel = new MyModel();
        this.view=view;
    }

    @Override
    public void onTitle() {
        myModel.getTitleResult(new ICallBack<List<TitleBean.DataBean>>() {


            @Override
            public void onSuccess(List<TitleBean.DataBean> data) {
                view.onTitleSuccess(data);
            }

            @Override
            public void onField(String message) {
                view.onField(message);
            }
        });
    }

    @Override
    public void onList(int cid,int page) {
       myModel.getListResult(cid, page, new ICallBack<List<ListBean.DataBean.DatasBean>>() {

           @Override
           public void onSuccess(List<ListBean.DataBean.DatasBean> data) {
               view.onListSuccess(data);
           }

           @Override
           public void onField(String message) {
               view.onField(message);
           }
       });
    }
}
