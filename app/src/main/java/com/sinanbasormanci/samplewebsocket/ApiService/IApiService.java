package com.sinanbasormanci.samplewebsocket.ApiService;

import com.sinanbasormanci.samplewebsocket.Model.MData;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by sinanbasormanci.com on 2019-09-21.
 */
public interface IApiService {

    @GET("mock-api/db")
    Observable<Response<MData>> getData();

}
