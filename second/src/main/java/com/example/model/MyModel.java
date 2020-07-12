package com.example.model;

import com.example.api.ApiService;
import com.example.bean.ListBean;
import com.example.bean.TitleBean;
import com.example.view.ICallBack;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyModel implements IModel {
    @Override
    public void getTitleResult(final ICallBack callBack) {
        new Retrofit.Builder()
                .baseUrl(ApiService.titleUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class)
                .getTitleBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TitleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TitleBean value) {
                        List<TitleBean.DataBean> data = value.getData();
                        callBack.onSuccess(data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onField(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getListResult(int cid, int page, final ICallBack callBack) {
        new Retrofit.Builder()
                .baseUrl(ApiService.listUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getListData(cid,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListBean value) {
                        List<ListBean.DataBean.DatasBean> datas = value.getData().getDatas();
                        callBack.onSuccess(datas);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onField(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
