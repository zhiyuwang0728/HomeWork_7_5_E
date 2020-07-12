package com.example.model;

import com.example.view.ICallBack;

public interface IModel {

    void getTitleResult(ICallBack callBack);

    void getListResult(int cid,int page, ICallBack callBack);
}
