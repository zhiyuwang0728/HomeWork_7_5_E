package com.example.view;

public interface ICallBack<T> {

    void onSuccess(T data);

    void onField(String message);
}
