package com.sinanbasormanci.samplewebsocket.ApiService;


/**
 * Created by sinanbasormanci.com on 2019-09-21.
 */
public interface IApiResp<T>{
    void onSuccess(T t);
    void onUnauthorized();
    void onError();
}