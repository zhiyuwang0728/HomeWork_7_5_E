package com.example.api;

import com.example.bean.ListBean;
import com.example.bean.TitleBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    String titleUrl = "https://www.wanandroid.com/";

    @GET("project/tree/json")
    Observable<TitleBean> getTitleBean();

    String listUrl = "https://www.wanandroid.com/project/";

    @GET("list/{path}/json?")
    Observable<ListBean> getListData(@Path("path") int page,@Query("cid") int id);

}
